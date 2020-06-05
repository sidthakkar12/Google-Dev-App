package com.googledev.siddharththakkar.utils

import android.content.Context
import android.os.Handler
import android.widget.Toast
import com.googledev.siddharththakkar.R

var mDoubleBackToExitPressedOnce = false

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun quitApp(activity: BaseActivity) {
    if (mDoubleBackToExitPressedOnce) {
        activity.finish()
    }
    if (!mDoubleBackToExitPressedOnce) {
        mDoubleBackToExitPressedOnce = true
        try {
            showToast(
                activity,
                activity.getString(R.string.str_back_press_toast)
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    Handler().postDelayed(Runnable { mDoubleBackToExitPressedOnce = false }, 2000)
}