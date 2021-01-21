package com.madisadyk.mentorship_android_kotlin.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.madisadyk.mentorship_android_kotlin.R;
import static com.madisadyk.mentorship_android_kotlin.utils.Constant.USER_TOKEN_NAME;


public class SessionManager {
    private Context context;
    private SharedPreferences sharedPreferences;

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    public void saveToken(String token) {
        Editor editor = sharedPreferences.edit();
        editor.putString(USER_TOKEN_NAME, token);
        editor.apply();
    }

    public static String fetchToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_TOKEN_NAME, null);
    }

    public static void removeToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.putString(USER_TOKEN_NAME, null);
        editor.apply();
    }
}
