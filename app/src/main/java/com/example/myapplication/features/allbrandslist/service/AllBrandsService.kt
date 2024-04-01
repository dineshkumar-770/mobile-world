package com.example.myapplication.features.allbrandslist.service

import com.example.myapplication.features.allbrandslist.models.AllBrandsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AllBrandsService {
    @GET("AKfycbxNu27V2Y2LuKUIQMK8lX1y0joB6YmG6hUwB1fNeVbgzEh22TcDGrOak03Fk3uBHmz-/exec")
    fun getAllBrands(
        @Query("route") route : String
    ): Call<AllBrandsResponse>
}