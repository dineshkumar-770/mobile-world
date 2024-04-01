package com.example.myapplication.features.allbrandslist.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AllBrandsRetrofit {
    private const val BASE_URL = "https://script.google.com/macros/s/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: AllBrandsService by lazy {
        retrofit.create(AllBrandsService :: class.java)
    }
}