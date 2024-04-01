package com.example.myapplication.features.brand_devices.service

import com.example.myapplication.features.brand_devices.models.BrandDevicesResponse
import com.example.myapplication.features.brand_devices.models.DeviceListRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface BrandDevicesService {
    @POST("AKfycbxNu27V2Y2LuKUIQMK8lX1y0joB6YmG6hUwB1fNeVbgzEh22TcDGrOak03Fk3uBHmz-/exec")
    fun getBrandDevices(
        @Body request: DeviceListRequest
    ): Call<BrandDevicesResponse>
}