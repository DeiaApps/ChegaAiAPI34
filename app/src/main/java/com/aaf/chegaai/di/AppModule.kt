package com.aaf.chegaai.di
/**
 * Created by Andréa Fonsêca on 08/12/2024
 * Copyright (c) 2024 Andréa A. Fonsêca
 * Licensed under the MIT License.
 * See LICENSE file for details.
 **/

import com.aaf.chegaai.data.repository.RideRepositoryImpl
import com.aaf.chegaai.data.services.IRideAPI
import com.aaf.chegaai.domain.repository.IRideRepository
import com.aaf.chegaai.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAws() : IRideAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory( GsonConverterFactory.create())
            .build()
            .create(IRideAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideRideRepository( api: IRideAPI ) : IRideRepository {
        return RideRepositoryImpl( api )
    }
}