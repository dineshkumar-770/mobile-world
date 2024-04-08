package com.example.myapplication.features.device_details.models

import com.google.gson.annotations.SerializedName

data class DeviceDetailsResponse(
    val status: Long,
    val message: String,
    val data: Data
)

data class Data(
    val key: String,

    @SerializedName("device_name")
    val deviceName: String,

    @SerializedName("device_image")
    val deviceImage: String,

    @SerializedName("display_size")
    val displaySize: String,

    @SerializedName("display_res")
    val displayRes: String,

    val camera: String,
    val video: String,
    val ram: String,
    val chipset: String,
    val battery: String,
    val batteryType: String,

    @SerializedName("release_date")
    val releaseDate: String,

    val body: String,

    @SerializedName("os_type")
    val osType: String,

    val storage: String,
    val comment: String,

    @SerializedName("more_specification")
    val moreSpecification: List<MoreSpecification>,

    val prices: Map<String, List<Price>>,
    val pictures: List<String>,

    @SerializedName("more_information")
    val moreInformation: MoreInformation
)

data class MoreInformation(
    @SerializedName("Related Devices")
    val relatedDevices: List<RelatedDevice>
)

data class RelatedDevice(
    @SerializedName("device_name")
    val deviceName: String,

    @SerializedName("device_image")
    val deviceImage: String,

    @SerializedName("key")
    val key: String
)

data class MoreSpecification(
    @SerializedName("title")
    val title: String,

    @SerializedName("data")
    val data: List<Info>
)

data class Info(
    val title: String,
    val data: List<String>
)

data class Price(
    val shopImage: String,
    val price: String,
    val buyURL: String
)

data class DeviceDetailsRequestBody(
    @SerializedName("route")
    val route: String,

    @SerializedName("key")
    val key: String
)