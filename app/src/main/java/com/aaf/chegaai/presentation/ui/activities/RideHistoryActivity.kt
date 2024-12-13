package com.aaf.chegaai.presentation.ui.activities
/**
 * Created by Andréa Fonsêca on 08/12/2024
 * Copyright (c) 2024 Andréa A. Fonsêca
 * Licensed under the MIT License.
 * See LICENSE file for details.
 **/

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aaf.chegaai.R
import com.aaf.chegaai.databinding.ActivityRideHistoryBinding
import com.aaf.chegaai.databinding.ActivityRideOptionsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RideHistoryActivity : AppCompatActivity() {

    private val binding by lazy{ ActivityRideHistoryBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

            //val selectedRideOption = intent.getParcelableExtra<RideOption>("selectedRideOption")


    }
}
