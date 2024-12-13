package com.aaf.chegaai.utils
/**
 * Created by Andréa Fonsêca on 10/12/2024
 * Copyright (c) 2024 Andréa A. Fonsêca
 * Licensed under the MIT License.
 * See LICENSE file for details.
 **/

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.hideKeyboard(){
    val inputMethodManager = context
        .getSystemService( Context.INPUT_METHOD_SERVICE ) as InputMethodManager

    inputMethodManager.hideSoftInputFromWindow(
        windowToken,0
    )
}

fun <T>Activity.goTo(destination: Class<T>, finalizeActivity: Boolean = true){
    startActivity(
        Intent(this, destination)
    )
    if (finalizeActivity) finish()
}

fun <T> Activity.goToWithExtras(
    destination: Class<T>,
    extras: Bundle? = null,
    finalizeActivity: Boolean = true
) {
    val intent = Intent(this, destination).apply {
        extras?.let { putExtras(it) }
    }
    startActivity(intent)
    if (finalizeActivity) finish()
}