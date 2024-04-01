package com.example.myapplication.features.brand_devices.views

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.features.brand_devices.component.DeviceInfoCard
import com.example.myapplication.features.brand_devices.service.BrandDevicesManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrandDevicesScreen(
    brandName: String, brandID: Int, brandDevices: BrandDevicesManager = viewModel()
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
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(12.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(brandDevicesState.data!!.data.deviceList.count()) { index ->
                                DeviceInfoCard(
                                    brandDevicesState.data!!.data.deviceList[index].deviceName,
                                    brandDevicesState.data!!.data.deviceList[index].deviceImage
                                )
                            }
                        }
                    }

                    brandDevicesState.error != null -> {
                        Text(text = brandDevicesState.error.toString())
                    }
                }
            }
        }

    }

}