package com.example.myapplication.features.device_details.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.features.device_details.models.Info


@Composable
fun MyExpandedList(moreSpecs: List<Info>, tileTitle: String) {
    var expanded by remember { mutableStateOf(true) }

    val rotateState = animateFloatAsState(
        targetValue = if (expanded) 180F else 0F, label = "This Is label",
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Box(modifier = Modifier
            .clickable {
                expanded = !expanded
            }
            .background(color = MaterialTheme.colorScheme.primaryContainer)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = tileTitle,
                    modifier = Modifier.fillMaxWidth(0.92F),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Icon(
                    Icons.Default.ArrowDropDown,
                    "",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.rotate(rotateState.value)

                )
            }
        }
        AnimatedVisibility(
            visible = expanded,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(16.dp)
            ) {
                moreSpecs.forEach {
                    info->  Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${info.title} :",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = " ${info.data[0]}")
                }
                }
            }
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(MaterialTheme.colorScheme.primaryContainer)
//                    .padding(16.dp)
//            ) {
//                items(moreSpecs.count()) { index ->
//                    Row(
//                        horizontalArrangement = Arrangement.SpaceBetween
//                    ) {
//                        Text(
//                            text = "${moreSpecs[index].title} :",
//                            color = MaterialTheme.colorScheme.errorContainer,
//                            fontWeight = FontWeight.Bold
//                        )
//                        Text(text = " ${moreSpecs[index].data[0]}")
//                    }
//                }
//            }

        }
    }
}