package com.aaf.chegaai.presentation.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.aaf.chegaai.databinding.ActivityRideRequestBinding
import androidx.appcompat.app.AppCompatActivity
import com.aaf.chegaai.R
import com.aaf.chegaai.presentation.viewmodel.RideRequestViewModel
import com.aaf.chegaai.utils.LoadAlert
import com.aaf.chegaai.utils.RideResult
import com.aaf.chegaai.utils.goToWithExtras
import com.aaf.chegaai.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RideRequestActivity : AppCompatActivity() {

    private val binding by lazy{ ActivityRideRequestBinding.inflate(layoutInflater) }
    private val rideRequestViewModel: RideRequestViewModel by viewModels()
    private val loadAlert by lazy{ LoadAlert(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        inits()
    }

    private fun inits() {
        initsObserver()
        initClickEvent()
    }

    private fun initsObserver() {
        // Observa os resultados da validação
        rideRequestViewModel.validationResult.observe(this) { validation ->
            with(binding) {
                // Atualiza os erros no TextInputLayout com base na validação
                tilUserId.error = if (validation.customerId) null else getString(R.string.invalid_user_id)
                tilOrigin.error = if (validation.origin) null else getString(R.string.invalid_address_origin)
                tilDestination.error = if (validation.destination) null else getString(R.string.invalid_address_destinaation)
            }
        }

        rideRequestViewModel.isFormValid.observe(this) { isValid ->
            if (isValid) {
                val userId = binding.tieUserId.text.toString()
                val origin = binding.tieOrigin.text.toString()
                val destination = binding.tieDestination.text.toString()

                // Envia a requisição
                rideRequestViewModel.fetchRideEstimateIfValid(userId, origin, destination)
            } else {
                Toast.makeText(this, "Preencha todos os campos corretamente!", Toast.LENGTH_SHORT).show()
            }
        }

        rideRequestViewModel.rideEstimate.observe(this) { result ->
            when (result) {
                is RideResult.Loading -> loadAlert.showLoad(getString(R.string.loading))
                is RideResult.Success -> {
                    loadAlert.close()
                    val estimate = result.data
                    Log.i("API RideRequestActivity", "Estimativa recebida: $estimate")

                    val bundle = Bundle().apply {
                        putParcelable("rideEstimate", estimate)
                    }
                    goToWithExtras(RideOptionsActivity::class.java, extras = bundle)
                   /* val intent = Intent(this, RideOptionsActivity::class.java)
                    intent.putExtra("rideEstimate", estimate)
                    startActivity(intent)*/
                }
                is RideResult.Error ->  loadAlert.close()
            }
        }
    }

    private fun initClickEvent() {
        with(binding){
            btnCost.setOnClickListener { view ->

                view.hideKeyboard()
                tilUserId.clearFocus()
                tilOrigin.clearFocus()
                tilDestination.clearFocus()

                val userId      = tieUserId.text.toString()
                val origin      = tieOrigin.text.toString()
                val destination = tieDestination.text.toString()

                rideRequestViewModel.validateAndProceed(userId, origin, destination)
            }
        }
    }
}





