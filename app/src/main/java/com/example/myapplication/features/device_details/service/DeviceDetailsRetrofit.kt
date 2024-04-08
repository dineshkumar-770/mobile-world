package com.example.myapplication.features.device_details.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DeviceDetailsRetrofit {
    private val BASEURL = "https://script.google.com/macros/s/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASEURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retroFitService: DeviceDetailsService by lazy {
        retrofit.create(DeviceDetailsService::class.java)
    }
}