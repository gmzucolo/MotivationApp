package com.example.gmzucolo.motivationapp.security

import android.content.Context
import android.content.SharedPreferences

class SecurityPreferences(context: Context) {

    private val security: SharedPreferences =
        context.getSharedPreferences("Motivation", Context.MODE_PRIVATE)

    fun saveValue(key: String, value: String) = security.edit().putString(key, value).apply()

    fun getValue(key: String) = security.getString(key, "") ?: ""
}