package com.example.myexperiment.common

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService


/**
 * Created by nampham on 6/2/21.
 */

 fun closeKeyboard(act : AppCompatActivity) {
    val view = act.currentFocus
    view?.let {
        val manager: InputMethodManager? = act.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?
        manager?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}