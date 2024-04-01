package com.example.myapplication.features.brand_devices.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BrandDevicesRetrofit {
    private val BASEURL = "https://script.google.com/macros/s/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASEURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retroFitService: BrandDevicesService by lazy {
        retrofit.create(BrandDevicesService :: class.java)
    }
}