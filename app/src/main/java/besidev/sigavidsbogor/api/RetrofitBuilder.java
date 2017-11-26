package besidev.sigavidsbogor.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import besidev.sigavidsbogor.helpers.AppConstants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Senno Hananto on 24/11/2017.
 */

public class RetrofitBuilder {
    public static Retrofit retrofit;
    public static void getRetrofitBuilder(){
        Gson gson = new GsonBuilder().create();
        retrofit = new Retrofit.Builder().baseUrl(AppConstants.base_url).addConverterFactory(GsonConverterFactory.create(gson)).build();
    }
}
