package com.aaf.chegaai.presentation.ui.activities
/**
 * Created by Andréa Fonsêca on 08/12/2024
 * Copyright (c) 2024 Andréa A. Fonsêca
 * Licensed under the MIT License.
 * See LICENSE file for details.
 **/


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aaf.chegaai.R
import com.aaf.chegaai.data.model.Option
import com.aaf.chegaai.data.model.PATCH.DriverDTO
import com.aaf.chegaai.data.model.PATCH.RideConfirmRequestDTO
import com.aaf.chegaai.databinding.ActivityRideOptionsBinding
import com.aaf.chegaai.domain.model.Driver
import com.aaf.chegaai.domain.model.RideConfirmation
import com.aaf.chegaai.domain.model.RideEstimate
import com.aaf.chegaai.domain.model.RideOption
import com.aaf.chegaai.presentation.ui.adapter.DriverAdapter
import com.aaf.chegaai.presentation.viewmodel.RideOptionsViewModel
import com.aaf.chegaai.utils.LoadAlert
import com.aaf.chegaai.utils.RideResult
import com.aaf.chegaai.utils.goToWithExtras
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.max

@AndroidEntryPoint
class RideOptionsActivity : AppCompatActivity() {

    private val binding by lazy { ActivityRideOptionsBinding.inflate(layoutInflater) }
    private val rideOptionsViewModel: RideOptionsViewModel by viewModels()
    private lateinit var driverAdapter: DriverAdapter
    private val loadAlert by lazy{ LoadAlert(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val rideEstimate = intent.getParcelableExtra<RideEstimate>("rideEstimate")
        setupRecyclerView()
        loadMap(rideEstimate?.route)

        rideEstimate?.let {
            driverAdapter = DriverAdapter(it.options) { driver ->
                onDriverChosen(driver, rideEstimate)
            }
            binding.rvDrivers.adapter = driverAdapter
        }

    }

    private fun setupRecyclerView() {
        binding.rvDrivers.layoutManager = LinearLayoutManager(this)
    }

    private fun loadMap(route: String?) {

        val origin = "Av. Paulista, 1538" // Substituir pelo endereço digitado
        val destination = "Av. Brasil, 2033" // Substituir pelo endereço digitado

        val gmmIntentUri = Uri.parse(
            "https://www.google.com/maps/dir/?api=1&origin=$origin&destination=$destination")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps") // Para abrir diretamente no app do Google Maps

        if (mapIntent.resolveActivity(packageManager) != null) {
            startActivity(mapIntent)
        }



        val staticMapUrl = "https://maps.googleapis.com/maps/api/staticmap?" +
                "center=-23.561684,-46.655981" +
                "&zoom=15" +
                "&size=600x400" +
                "&maptype=roadmap" +
                "&markers=color:red|label:A|-23.561684,-46.655981" +
                "&key=API_KEY"
        // Exiba o mapa estático usando Glide ou Picasso
        Glide.with(this)
            .load(route) // URL do mapa estático retornado pela API
            .into(binding.ivMap)
    }

    /*driveId X distância permitida
    val driverDistanceMap = mapOf(
    1 to 1,  // driverId 1 aceita 1 km - 1000m
    2 to 5,  // driverId 2 aceita 5 km
    3 to 10  // driverId 3 aceita 10 km)
    * */

    private fun onDriverChosen(driver: RideOption, rideEstimate: RideEstimate) {

        val driveDistanceMap = mapOf(
            1 to 1,  // driverId 1 aceita 1 km
            2 to 5,  // driverId 2 aceita 5 km
            3 to 10  // driverId 3 aceita 10 km
        )

        val maxDistance = driveDistanceMap[driver.driverId]
        if (maxDistance != null && rideEstimate.distance > maxDistance){
            Toast.makeText(
                this,
                "${driver.driverName} não aceita distâncias maiores que $maxDistance km",
                     Toast.LENGTH_SHORT
            ).show()
            return
        }

        val driverDTO = DriverDTO(
            id = driver.driverId,
            name = driver.driverName
        )

        val confirmRequest = RideConfirmRequestDTO(
            customer_id = "",
            origin = rideEstimate.origin,
            destination = rideEstimate.destination,
            driver = driverDTO,
            distance = rideEstimate.distance,
            duration = rideEstimate.duration.toString(),
            value = driver.tripValue
        )
        rideOptionsViewModel.confirmRide(confirmRequest)

        rideOptionsViewModel.confirmationResult.observe(this) { result ->
            when (result) {
                is RideResult.Loading -> loadAlert.showLoad(getString(R.string.loading))
                is RideResult.Success -> {
                    val confirmRide = result.data
                    Log.i("API OptionsActivity", "confirmação recebida $confirmRide")

                    /*val bundle = Bundle().apply {
                        putExtra("confirmedRide", confirmRide)
                    }
                    goToWithExtras(RideHistoryActivity::class.java, extras = bundle)*/
                    val intent = Intent(this, RideHistoryActivity::class.java)
                    //intent.putExtra("confirmedRide", confirmRide)
                    startActivity(intent)
                }
                is RideResult.Error -> {
                    loadAlert.close()
                    Toast.makeText(this, result.errorCode, Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}
