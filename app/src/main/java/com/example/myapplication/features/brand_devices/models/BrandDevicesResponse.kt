package com.example.myapplication.features.brand_devices.models

import com.google.gson.annotations.SerializedName

data class BrandDevicesResponse (
    val status: Int,
    val message: String,
    val data: DeviceList,
)

data class DeviceList(
    @SerializedName("device_list")
    val deviceList: List<Device>,

    @SerializedName("total_page")
    val totalPage: Int
)

data class Device(
    @SerializedName("device_name")
    val deviceName: String,

    @SerializedName("device_image")
    val deviceImage: String,

    val key: String
)

data class DeviceListRequest(
    @SerializedName("route")
    val route: String?= null,
    @SerializedName("brand_id")
    val brandId: Int?= null,
    @SerializedName("brand_name")
    val brandName: String?= null,
    @SerializedName("page")
    val page: Int?= null
)
