package besidev.sigavidsbogor;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Senno Hananto on 30/09/2017.
 */

public class PreferenceManager {
    private static SharedPreferences sharedPreferences;

    public static String getToken(Context context){
        sharedPreferences = context.getSharedPreferences(AppConstants.DATA_USER, 0);
        return sharedPreferences.getString("token", null);
    }

    public static boolean setToken(String token, Context context) {
        sharedPreferences = context.getSharedPreferences(AppConstants.DATA_USER, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", token);
        return editor.commit();
    }

    public static String getDisplayName(Context context){
        sharedPreferences = context.getSharedPreferences(AppConstants.DATA_USER,0);
        return sharedPreferences.getString("DisplayName",null);
    }

    public static boolean setDisplayName(String displayName, Context context) {
        sharedPreferences = context.getSharedPreferences(AppConstants.DATA_USER, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("DisplayName", displayName);
        return editor.commit();
    }

    public static String getEmail(Context context){
        sharedPreferences = context.getSharedPreferences(AppConstants.DATA_USER,0);
        return sharedPreferences.getString("Email",null);
    }

    public static boolean setEmail(String email, Context context) {
        sharedPreferences = context.getSharedPreferences(AppConstants.DATA_USER, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Email", email);
        return editor.commit();
    }

    public static String getPictureURL(Context context){
        sharedPreferences = context.getSharedPreferences(AppConstants.DATA_USER,0);
        return sharedPreferences.getString("PictureURL",null);
    }

    public static boolean setPictureURL(String pictureURL, Context context) {
        sharedPreferences = context.getSharedPreferences(AppConstants.DATA_USER, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("PictureURL", pictureURL);
        return editor.commit();
    }

    public static String getGender(Context context){
        sharedPreferences = context.getSharedPreferences(AppConstants.DATA_USER,0);
        return sharedPreferences.getString("Gender",null);
    }

    public static boolean setGender(String gender, Context context) {
        sharedPreferences = context.getSharedPreferences(AppConstants.DATA_USER, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Gender", gender);
        return editor.commit();
    }

    public static String getBirthday(Context context){
        sharedPreferences = context.getSharedPreferences(AppConstants.DATA_USER,0);
        return sharedPreferences.getString("Birthday",null);
    }

    public static boolean setBirthday(String birthday, Context context) {
        sharedPreferences = context.getSharedPreferences(AppConstants.DATA_USER, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Birthday", birthday);
        return editor.commit();
    }
}
