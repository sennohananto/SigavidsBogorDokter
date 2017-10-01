package besidev.sigavidsbogor.services;

import android.os.AsyncTask;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleBrowserClientRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.Date;
import com.google.api.services.people.v1.model.Person;

import java.io.IOException;
import java.util.Arrays;

import besidev.sigavidsbogor.SigavidsApp;
import besidev.sigavidsbogor.helpers.AppConstants;
import besidev.sigavidsbogor.helpers.AppHelpers;
import besidev.sigavidsbogor.helpers.PreferenceManager;

import static besidev.sigavidsbogor.helpers.AppConstants.GOOGLE_CLIENT_ID;
import static besidev.sigavidsbogor.helpers.AppConstants.GOOGLE_CLIENT_SECRET;

/**
 * Created by Senno Hananto on 30/09/2017.
 */

public class GoogleAdditionalDetailTask extends AsyncTask<GoogleSignInAccount,Void,Person> {
    @Override
    protected void onPostExecute(Person person) {
        super.onPostExecute(person);
        AppHelpers.LogCat("==>MASUK onPostExecute");
        String profileGender = null, profileBirthday = null, profileYearOfBirth = null, profileAbout = null, profileCover = null;
        if (person != null) {
            if (person.getGenders() != null && person.getGenders().size() > 0) {
                PreferenceManager.setGender(person.getGenders().get(0).getValue(), SigavidsApp.getAppContext());
                AppHelpers.LogCat("Genders : "+ person.getGenders().get(0).getValue());
                profileGender = person.getGenders().get(0).getValue();
                //profileGender = person.getGenders().get(0).getValue();
            }
            if (person.getBirthdays() != null && person.getBirthdays().get(0).size() > 0) {
//              yyyy-MM-dd
                AppHelpers.LogCat("Tgllhr MENTAH : "+person.getBirthdays().get(1).getDate());
                Date dobDate = person.getBirthdays().get(1).getDate();
                if (dobDate.getYear() != null) {
                    PreferenceManager.setBirthday(dobDate.getDay() + "-" + dobDate.getMonth() + "-" + dobDate.getYear(),SigavidsApp.getAppContext());
                    AppHelpers.LogCat("Ultah : "+dobDate.getDay() + "-" + dobDate.getMonth() + "-" + dobDate.getYear());
                }
            }
            if (person.getAddresses() != null){
                AppHelpers.LogCat("Address (type): "+ person.getAddresses().get(0).getFormattedType());
                AppHelpers.LogCat("Address (value): "+ person.getAddresses().get(0).getFormattedValue());
            }
            if (person.getPhoneNumbers() != null){
                AppHelpers.LogCat("PhoneNumbers : "+ person.getPhoneNumbers().get(0).getFormattedType());
            }
        }
    }

    @Override
    protected Person doInBackground(GoogleSignInAccount... params) {
        Person profile = null;
        AppHelpers.LogCat("===>MASUK doInBackground");
        try {
            HttpTransport httpTransport = new NetHttpTransport();
            JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();

            //Redirect URL for web based applications.
            // Can be empty too.
            String redirectUrl = "urn:ietf:wg:oauth:2.0:oob";
            String scope = "https://www.googleapis.com/auth/contacts.readonly";
            String scope1 = "https://www.googleapis.com/auth/user.phonenumbers.read";
            String scope2 = "https://www.googleapis.com/auth/user.addresses.read";
            String authorizationUrl = new GoogleBrowserClientRequestUrl(AppConstants.GOOGLE_CLIENT_ID,redirectUrl, Arrays.asList(scope,scope1,scope2)).build();
            AppHelpers.LogCat("AuthorizationURL : "+ authorizationUrl);

//            String redirectUrl =  "https://people.googleapis.com/v1/";
//            String redirectUrl =  "https://people.googleapis.com/v1/";
            // Exchange auth code for access token
            GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
                    httpTransport,
                    jsonFactory,
                    GOOGLE_CLIENT_ID,
                    GOOGLE_CLIENT_SECRET,
                    params[0].getServerAuthCode(),redirectUrl).execute();

            GoogleCredential credential = new GoogleCredential.Builder()
                    .setClientSecrets(GOOGLE_CLIENT_ID, GOOGLE_CLIENT_SECRET)
                    .setTransport(httpTransport)
                    .setJsonFactory(jsonFactory)
                    .build()
                    .setFromTokenResponse(tokenResponse);
            PeopleService peopleService = new PeopleService.Builder(httpTransport, jsonFactory, credential).build();
            // Get the user's profile
            profile = peopleService.people().get("people/me").setPersonFields("names,addresses,birthdays,ageRanges,genders,phoneNumbers").execute();
        } catch (IOException e) {
            AppHelpers.LogCat("doInBackground : "+e.getMessage());
            e.printStackTrace();
        }
        return profile;
    }
}
