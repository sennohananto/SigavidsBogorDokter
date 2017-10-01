package besidev.sigavidsbogor;

import android.app.Application;
import android.content.Context;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Senno Hananto on 30/09/2017.
 */

public class SigavidsApp extends Application {
    public static SigavidsApp mInstance;
    private static Context AppContext;
    public static GoogleApiClient mGoogleApiClient;

    @Override
    public void onCreate() {
        super.onCreate();

//        PreferenceManager.setToken("111", getApplicationContext());
        super.onCreate();

//        AppHelper.LogCat("-->MASUK CREATE");

//        MultiDex.install(this);
        AppContext = getApplicationContext();
//        if (PreferenceManager.getToken(this) != null) {
//        }
//        AppHelper.LogCat("-->LEWAT CREATE");
        mInstance = this;
//        init();
    }

    public static GoogleApiClient getmGoogleApiClient() {
        return mGoogleApiClient;
    }

    public static void setmGoogleApiClient(GoogleApiClient mGoogleApiClient) {
        SigavidsApp.mGoogleApiClient = mGoogleApiClient;
    }

    public static Context getAppContext() {
        return AppContext;
    }

    public static SigavidsApp getInstance() {
        return mInstance;
    }
}
