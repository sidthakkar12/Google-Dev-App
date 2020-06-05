package com.googledev.siddharththakkar.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class SharedPreferenceUtil(context: Context) {
    private val mSharedPreferences: SharedPreferences =
        context.getSharedPreferences(AppConstants.SHARED_PREFERENCE, Context.MODE_PRIVATE)
    private val prefsEditor: SharedPreferences.Editor
    fun setStringPreference(key: String, value: String) {
        prefsEditor.putString("" + key, value + "")
        prefsEditor.commit()
    }

    fun getStringPreference(key: String): String {
        return "" + mSharedPreferences.getString("" + key, "")
    }

    fun setBooleanPreference(key: String, value: Boolean?) {
        prefsEditor.putBoolean("" + key, value!!)
        prefsEditor.commit()
    }

    fun getBooleanPreference(key: String): Boolean {
        return mSharedPreferences.getBoolean("" + key, false)
    }

    fun setIntegerPreference(key: String, value: Int?) {
        prefsEditor.putInt("" + key, value!!)
        prefsEditor.commit()
    }

    fun getIntegerPreference(key: String): Int {
        return mSharedPreferences.getInt("" + key, -1)
    }

    fun clearPreference(key: String) {
        prefsEditor.remove("" + key)
        prefsEditor.commit()
    }

    fun checkKeyIsAvailableOrNot(key: String): Boolean {
        return mSharedPreferences.contains(key + "")
    }

    fun clearAllPreferences() {
        prefsEditor.clear()
        prefsEditor.commit()
    }

    companion object {
        private const val TAG = "SharedPreferenceUtil"
        var instance: PreferenceManager? = null
    }

    init {
        prefsEditor = mSharedPreferences.edit()
    }
}