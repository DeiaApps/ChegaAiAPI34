package com.aaf.chegaai.presentation.ui.activities
/**
 * Created by Andréa Fonsêca on 08/12/2024
 * Copyright (c) 2024 Andréa A. Fonsêca
 * Licensed under the MIT License.
 * See LICENSE file for details.
 **/

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.aaf.chegaai.R
import com.aaf.chegaai.databinding.ActivityRideOptionsBinding
import com.aaf.chegaai.domain.model.RideEstimate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RideOptionsActivity : AppCompatActivity() {

    private val binding by lazy{ ActivityRideOptionsBinding.inflate(layoutInflater) }
   // private val rideRequestViewModel: RideRequestViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        val rideEstimate = intent.getSerializableExtra("RIDE_ESTIMATE") as? RideEstimate
        rideEstimate?.let { estimate ->
            // Use `estimate` para exibir os dados no mapa e a lista de motoristas
        }

        // Recuperando a lista de rides
     //   val rides: ArrayList<Ride>? = intent.getParcelableArrayListExtra("rides")

        binding.txtOption.text = rideEstimate.toString()
        // Agora você pode usar a lista de rides
        rideEstimate?.let {
            // Exemplo: Mostrar o custo de cada corrida
           // it.forEach { ride ->
                Log.i("Ride", "Custo: $rideEstimate")
            }
        }
    }

