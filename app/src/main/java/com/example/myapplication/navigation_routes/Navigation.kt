package com.example.myapplication.navigation_routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.features.allbrandslist.Views.AllBrandsScreen
import com.example.myapplication.features.allbrandslist.service.AllBrandsManager
import com.example.myapplication.features.brand_devices.service.BrandDevicesManager
import com.example.myapplication.features.brand_devices.views.BrandDevicesScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    val brandDevicesViewModel = remember { BrandDevicesManager() }
    val allBrandsData = remember {
        AllBrandsManager()
    }

    NavHost(navController = navController, startDestination = MyRoutes.AllBrandsScreen.routes) {
        composable(MyRoutes.AllBrandsScreen.routes){
            AllBrandsScreen(navController,brandDevicesViewModel,allBrandsData)
        }

        composable("${MyRoutes.BrandDevicesScreen.routes}/{brandName}/{brandID}", arguments = listOf(
            navArgument(name = "brandName"){
                type = NavType.StringType
            },
            navArgument(name = "brandID"){
                type = NavType.IntType
            }
        )){
            val brandName = it.arguments!!.getString("brandName")
            val brandID = it.arguments!!.getInt("brandID")
            BrandDevicesScreen(brandName = brandName!!,brandID = brandID,brandDevicesViewModel)
        }
    }
}