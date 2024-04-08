package com.example.myapplication.features.brand_devices.views

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapplication.common.ScrollableListWithEndDetection
 import com.example.myapplication.features.brand_devices.service.BrandDevicesManager
import com.example.myapplication.features.device_details.models.DeviceDetailsRequestBody
import com.example.myapplication.features.device_details.service.DeviceDetailsManager
import com.example.myapplication.navigation_routes.MyRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrandDevicesScreen(
    brandName: String, brandID: Int, brandDevices: BrandDevicesManager = viewModel(),
    deviceDetails: DeviceDetailsManager = viewModel(),
    navController: NavController
) {
    val brandDevicesState by brandDevices.brandDevicesState
    Scaffold(
        topBar = {
            TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),

                title = {
                    Text(brandName)
                })
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                when {
                    brandDevicesState.loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .width(40.dp)
                                .align(alignment = Alignment.Center),
                            color = MaterialTheme.colorScheme.secondary,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant,
                        )
                    }

                    brandDevicesState.data != null -> {
                        ScrollableListWithEndDetection(
                            items = brandDevicesState.data!!.data.deviceList,
                            onEndReached = {
                            },
                            deviceDetails = deviceDetails,
                            brandDevices = brandDevices,
                            navController = navController,
                            brandID = brandID,
                            brandName = brandName
                        )
//                        LazyVerticalGrid(
//                            columns = GridCells.Fixed(2),
//                            modifier = Modifier.fillMaxSize(),
//                            contentPadding = PaddingValues(12.dp),
//                            verticalArrangement = Arrangement.spacedBy(10.dp),
//                            horizontalArrangement = Arrangement.spacedBy(10.dp)
//                        ) {
//                            items(brandDevicesState.data!!.data.deviceList.count()) { index ->
//                                DeviceInfoCard(
//                                    deviceName = brandDevicesState.data!!.data.deviceList[index].deviceName,
//                                    deviceImage = brandDevicesState.data!!.data.deviceList[index].deviceImage,
//                                    onClick = {
//                                        deviceDetails.fetchDeviceDetails(
//                                            requestBody = DeviceDetailsRequestBody(
//                                                route = "device-detail",
//                                                key = brandDevicesState.data!!.data.deviceList[index].key
//                                            )
//                                        )
//                                        val completeName =
//                                            "$brandName ${brandDevicesState.data!!.data.deviceList[index].deviceName}"
//                                        navController.navigate("${MyRoutes.DeviceDetailsScreen.routes}/$completeName") {
//                                            launchSingleTop = true
//                                        }
//                                    }
//                                )
//                            }
//                        }
                    }

                    brandDevicesState.error != null -> {
                        Text(text = brandDevicesState.error.toString())
                    }
                }
            }
        }

    }

}