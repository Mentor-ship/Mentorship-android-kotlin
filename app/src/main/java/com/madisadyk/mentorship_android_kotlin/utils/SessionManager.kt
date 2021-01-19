package com.madisadyk.mentorship_android_kotlin.utils

import android.content.Context
import android.content.SharedPreferences
import com.madisadyk.mentorship_android_kotlin.R
import com.madisadyk.mentorship_android_kotlin.utils.Constant.Companion.USER_TOKEN_NAME

class SessionManager(context: Context?) {

    private var sharedPreferences: SharedPreferences ?=
        context!!.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    fun saveToken(token: String?) {
        val editor = sharedPreferences!!.edit()
        editor.putString(USER_TOKEN_NAME, token)
            .apply()
    }

    fun fetchToken(context: Context): String? {
        val sharedPreferences = context
            .getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        return sharedPreferences.getString(USER_TOKEN_NAME, null)
    }

    fun removeToken(context: Context) {
        val sharedPreferences = context
            .getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(USER_TOKEN_NAME, null)
            .apply()
    }
}