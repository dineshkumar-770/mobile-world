package com.example.myapplication.navigation_routes

sealed class MyRoutes(val routes: String){
    data object AllBrandsScreen: MyRoutes("allBrandsScreen")
    data object BrandDevicesScreen: MyRoutes("brandDevicesScreen")

    data object DeviceDetailsScreen: MyRoutes("deviceDetailsScreen")
}