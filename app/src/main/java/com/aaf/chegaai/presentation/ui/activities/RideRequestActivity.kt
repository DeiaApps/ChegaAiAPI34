package com.aaf.chegaai.presentation.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.aaf.chegaai.databinding.ActivityRideRequestBinding
import androidx.appcompat.app.AppCompatActivity
import com.aaf.chegaai.R
import com.aaf.chegaai.domain.model.RideEstimateRequest
import com.aaf.chegaai.domain.usecase.ValidationResult
import com.aaf.chegaai.presentation.viewmodel.RideRequestViewModel
import com.aaf.chegaai.utils.LoadAlert
import com.aaf.chegaai.utils.RideResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RideRequestActivity : AppCompatActivity() {

    private val binding by lazy{ ActivityRideRequestBinding.inflate(layoutInflater) }
    private val rideRequestViewModel: RideRequestViewModel by viewModels()
    private val validationFields: RideRequestViewModel by viewModels()
    private val loadAlert by lazy{ LoadAlert(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Log.i("API RideRequestActivity", "Activity started successfully")
        inits()

        Log.i("API RideRequestActivity", "Final da onCreate")
    }

    private fun inits() {
        initsObserver()

    }

    private fun initsObserver() {
        rideRequestViewModel.rideEstimate.observe(this) { result ->
            when (result) {
                is RideResult.Loading -> {
                   loadAlert.showLoad(getString(R.string.loading))
                }
                is RideResult.Success -> {
                    loadAlert.close()
                    val estimate = result.data
                    if (estimate != null){
                        binding.tvCost.text =  estimate.toString()
                        Log.i("API RideRequestActivity", "Estimativa recebida: $estimate")

                        val intent = Intent(this, RideOptionsActivity::class.java).apply {
                            putExtra("RIDE_ESTIMATE", estimate)
                        }
                        startActivity(intent)
                        //showRideOptions(estimate)
                    } else{
                        loadAlert.close()
                        Toast.makeText(this, "Dados da estimativa estão vazios", Toast.LENGTH_SHORT).show()
                    }
                }
                is RideResult.Error -> {
                    loadAlert.close()
                    loadAlert.showLoad(result.errorDescription)
                    Log.e("API RideRequestActivity", "Erro: ${result.errorDescription}")
                }
            }
        }

        binding.btCost.setOnClickListener {

                val userId      = binding.tieUserId.text.toString()
                val origin      = binding.tieOrigin.text.toString()
                val destination = binding.tieDestination.text.toString()

            if (userId.isBlank() || origin.isBlank() || destination.isBlank()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            } else {
                rideRequestViewModel.fetchRideEstimate(userId, origin, destination)
            }
        }
    }
}





