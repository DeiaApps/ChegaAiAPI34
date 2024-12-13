package com.aaf.chegaai.utils
/**
 * Created by Andréa Fonsêca on 09/12/2024
 * Copyright (c) 2024 Andréa A. Fonsêca
 * Licensed under the MIT License.
 * See LICENSE file for details.
 **/

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.aaf.chegaai.R


class LoadAlert (private val context: Context) {

    private var alertDialog: AlertDialog? = null

    fun showLoad(title: String) {
        if (alertDialog?.isShowing == true) return // evita recriar enquanto visível
        val viewLoad = View.inflate(context, R.layout.load_layout, null)
        val progressBar: ProgressBar = viewLoad.findViewById(R.id.progressBar)
        val messageTextView: TextView = viewLoad.findViewById(R.id.tvLoad)
        messageTextView.text = title

        val alertBuilder = AlertDialog.Builder(context)
            .setCancelable(false)
            .setView(viewLoad)

        alertDialog = alertBuilder.create()
        alertDialog?.show()
    }

    fun close() {
        alertDialog?.dismiss()
    }
}

