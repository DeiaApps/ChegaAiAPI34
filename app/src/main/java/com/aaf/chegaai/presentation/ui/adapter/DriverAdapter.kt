package com.aaf.chegaai.presentation.ui.adapter
/**
 * Created by Andréa Fonsêca on 12/12/2024
 * Copyright (c) 2024 Andréa A. Fonsêca
 * Licensed under the MIT License.
 * See LICENSE file for details.
 **/

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.aaf.chegaai.databinding.ItemDriverBinding
import com.aaf.chegaai.domain.model.RideOption
import com.aaf.chegaai.utils.formatAsCoin

class DriverAdapter(
    private val drivers: List<RideOption>,

    private val onclickChoose: (RideOption) -> Unit
) : Adapter<DriverAdapter.DriverViewHolder>() {

    private var listRideOption = emptyList<RideOption>()
    fun adicionarLista( lista: List<RideOption>){
        listRideOption = lista
        notifyDataSetChanged()
    }

    inner class DriverViewHolder(//ViewHolder
        private val binding: ItemDriverBinding
    ) : ViewHolder( binding.root ) {     // ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(driver: RideOption){

            binding.tvDriverName.text = driver.driverName
            binding.tvDriverDescription.text = driver.driverDescription
            binding.tvVehicleDetails.text = driver.vehicle
            binding.tvNote.text = driver.driverNote.toString()
            binding.tvRating.text = driver.driverReviews
            binding.tvTripCost.text = driver.tripValue.formatAsCoin()

            binding.btnChoose.setOnClickListener {
                onclickChoose( driver )
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriverViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDriverBinding.inflate(inflater, parent, false)
        return DriverViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return drivers.size
    }

    override fun onBindViewHolder(holder: DriverViewHolder, position: Int) {
        holder.bind(drivers[position])
    }
}