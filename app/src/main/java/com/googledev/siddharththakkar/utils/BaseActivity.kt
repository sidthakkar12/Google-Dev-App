package com.googledev.siddharththakkar.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity

import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import androidx.fragment.app.FragmentTransaction
import com.googledev.siddharththakkar.R

open class BaseActivity : AppCompatActivity() {
    var sharedPreferenceUtil = AppController.getmSharedPreferenceUtil()
}
