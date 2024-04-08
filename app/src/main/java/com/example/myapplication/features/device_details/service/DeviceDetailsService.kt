package com.example.myapplication.features.device_details.service

import com.example.myapplication.features.device_details.models.DeviceDetailsRequestBody
import com.example.myapplication.features.device_details.models.DeviceDetailsResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface DeviceDetailsService {

    @POST("AKfycbxNu27V2Y2LuKUIQMK8lX1y0joB6YmG6hUwB1fNeVbgzEh22TcDGrOak03Fk3uBHmz-/exec")
    fun getDeviceDetails(
        @Body request: DeviceDetailsRequestBody
    ): Call<DeviceDetailsResponse>

}