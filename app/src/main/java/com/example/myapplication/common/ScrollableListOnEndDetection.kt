package com.example.myapplication.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
 import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
 import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapplication.features.brand_devices.component.DeviceInfoCard
import com.example.myapplication.features.brand_devices.models.Device
import com.example.myapplication.features.brand_devices.service.BrandDevicesManager
import com.example.myapplication.features.device_details.models.DeviceDetailsRequestBody
import com.example.myapplication.features.device_details.service.DeviceDetailsManager
import com.example.myapplication.navigation_routes.MyRoutes

@Composable
fun ScrollableListWithEndDetection(
    items: List<Device>,
    onEndReached: () -> Unit,
    brandName: String, brandID: Int, brandDevices: BrandDevicesManager = viewModel(),
    deviceDetails: DeviceDetailsManager = viewModel(),
    navController: NavController

) {
    val listState = rememberLazyGridState()
    var endReached by remember { mutableStateOf(false) }
    val brandDevicesState by brandDevices.brandDevicesState
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        state = listState
    ) {
        items(items.size) { index ->
            DeviceInfoCard(
                deviceImage = items[index].deviceImage,
                deviceName = items[index].deviceName,
                onClick =  {
                    deviceDetails.fetchDeviceDetails(
                        requestBody = DeviceDetailsRequestBody(
                            route = "device-detail",
                            key = brandDevicesState.data!!.data.deviceList[index].key
                        )
                    )
                    val completeName =
                        "$brandName ${brandDevicesState.data!!.data.deviceList[index].deviceName}"
                    navController.navigate("${MyRoutes.DeviceDetailsScreen.routes}/$completeName") {
                        launchSingleTop = true
                    }
                }
            )
        }
    }
    val isScrollInProgress = listState.isScrollInProgress
    val currentEndReached = lastVisibleItemIndex(listState) >= items.size - 1

    if (!endReached && isScrollInProgress && currentEndReached) {
        onEndReached()
        endReached = true
    }
}

private fun lastVisibleItemIndex(state: LazyGridState): Int {
    val lastVisibleItemIndex = state.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
    return lastVisibleItemIndex + 1
}