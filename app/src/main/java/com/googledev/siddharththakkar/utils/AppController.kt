package com.googledev.siddharththakkar.utils

import android.app.Application

class AppController : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        mSharedPreferenceUtil = SharedPreferenceUtil(this)
    }

    companion object {

        private val TAG = AppController::class.java.simpleName

        @get:Synchronized
        var instance: AppController? = null
            private set
        private var mSharedPreferenceUtil: SharedPreferenceUtil? = null

        fun getmSharedPreferenceUtil(): SharedPreferenceUtil? {
            return mSharedPreferenceUtil
        }
    }
}
