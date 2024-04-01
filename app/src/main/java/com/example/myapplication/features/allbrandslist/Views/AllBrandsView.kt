package com.example.myapplication.features.allbrandslist.Views

import android.annotation.SuppressLint
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapplication.features.allbrandslist.components.GridBox
import com.example.myapplication.features.allbrandslist.service.AllBrandsManager
import com.example.myapplication.features.brand_devices.models.DeviceListRequest
import com.example.myapplication.features.brand_devices.service.BrandDevicesManager
import com.example.myapplication.navigation_routes.MyRoutes
import com.example.myapplication.navigation_routes.Navigation
import kotlin.concurrent.thread

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SuspiciousIndentation")
@Composable
fun AllBrandsScreen(
    navController: NavController,
    brandDevices: BrandDevicesManager = viewModel(),
    allBrandsData: AllBrandsManager = viewModel()
) {
    val allBrandsState by allBrandsData.allBrandsP
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),

                title = {
                    Text("All Brands")
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                if (allBrandsState.loading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .width(40.dp)
                            .align(alignment = Alignment.Center),
                        color = MaterialTheme.colorScheme.secondary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                } else {
                    if (allBrandsState.data!!.isNotEmpty()) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(12.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(allBrandsState.data!!.count()) { index ->
                                GridBox(brandName = allBrandsState.data!![index].brand_name, onTap = {
                                    thread (start = true){
                                        val reqData = DeviceListRequest(
                                            "device-list-by-brand",
                                            allBrandsState.data?.get(index)!!.brand_id,
                                            allBrandsState.data!![index].brand_name,
                                            1
                                        )
                                        brandDevices.fetchBrandDevicesData(reqData)
                                        navController.navigate(
                                            "${MyRoutes.BrandDevicesScreen.routes}/" +
                                                    "${allBrandsState.data!![index].brand_name}/" +
                                                    "${allBrandsState.data?.get(index)!!.brand_id}"
                                        ) {
                                            launchSingleTop = true
                                        }
                                    }

                                })
                            }
                        }
                    } else {
                        Text(text = "${allBrandsState.error}")
                    }
                }

            }
        }

    }

}