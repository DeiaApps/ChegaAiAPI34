package com.aaf.chegaai.utils

import android.app.Activity
import android.view.Gravity
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by Andréa Fonsêca on 11/12/2024
 * Copyright (c) 2024 Andréa A. Fonsêca
 * Licensed under the MIT License.
 * See LICENSE file for details.
 **/

fun Activity.displayMessage(txtMessage: String ){
    val toast = Toast.makeText(this, txtMessage, Toast.LENGTH_SHORT)
    toast.setGravity(Gravity.CENTER_VERTICAL,0, 0)
    toast.show()
    //cancelar após 2 segundos
    GlobalScope.launch (Dispatchers.Main){
        delay(2000)
        toast.cancel()
    }
}