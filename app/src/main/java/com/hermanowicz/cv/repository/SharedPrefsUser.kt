package com.hermanowicz.cv.repository

import android.content.SharedPreferences
import com.hermanowicz.cv.utils.keys.SharedPrefs


class SharedPrefsUser(private val sharedPreferences: SharedPreferences): UserRepository {
    override fun saveUserId(userId: String) {
        sharedPreferences.edit().putString(SharedPrefs.USER_ID, userId).apply()
    }

    override fun userId(): String {
        return sharedPreferences.getString(SharedPrefs.USER_ID, "") ?: ""
    }
}
