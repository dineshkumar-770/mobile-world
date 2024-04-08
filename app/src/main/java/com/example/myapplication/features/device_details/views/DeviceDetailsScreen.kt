package com.example.myapplication.features.device_details.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.myapplication.features.device_details.components.MyExpandedList
import com.example.myapplication.features.device_details.service.DeviceDetailsManager

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun DeviceDetailsScreen(
    deviceDetails: DeviceDetailsManager = viewModel(),
    deviceName: String,
) {
    val deviceInfo by deviceDetails.deviceDetails

    Scaffold(
        topBar = {
            TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),

                title = {
                    Text(deviceName.toString())
                })
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                when {
                    deviceInfo.loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .width(40.dp)
                                .align(Alignment.Center),
                            color = MaterialTheme.colorScheme.secondary,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant,
                        )
                    }

                    deviceInfo.error != null -> {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(text = deviceInfo.error.toString())
                        }

                    }

                    deviceInfo.data != null -> {
                        LazyColumn {
                            item {
                                PageView(imagesList = deviceInfo.data!!.data.pictures)
                            }
                            items(deviceInfo.data!!.data.moreSpecification.count()) { index: Int ->
                                MyExpandedList(
                                    tileTitle = deviceInfo.data!!.data.moreSpecification[index].title,
                                    moreSpecs = deviceInfo.data!!.data.moreSpecification[index].data
                                )
                            }
                            item {
                                PageView(imagesList = deviceInfo.data!!.data.pictures)
                            }
                        }

                    }
                }
            }

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PageView(imagesList: List<String>) {
    val pageState = rememberPagerState {
        imagesList.size
    }
    HorizontalPager(
        state = pageState,
        pageSize = PageSize.Fill,
        key = { imagesList[it] }
    ) { index ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp) // Fixed height of 250dp
        ) {
            // Adjust AsyncImage to fill the bounds of the Box
            AsyncImage(
                model = imagesList[index],
                contentDescription = "",
                modifier = Modifier.fillMaxSize(), // Fill the bounds of the Box
                alignment = Alignment.Center,
                contentScale = ContentScale.Fit
            )
        }
    }
}