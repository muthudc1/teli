package com.example.teli.Storage

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    fun saveUserId(userId: String) {
        val editor = sharedPreferences.edit()
        editor.putString("user_id", userId)
        editor.apply()
    }

    fun getUserId(): String {
        return sharedPreferences.getString("user_id", "") ?: ""
    }

    // Add other functions to save/retrieve different data as needed
}