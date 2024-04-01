package com.example.myapplication.features.allbrandslist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun GridBox(brandName:String,onTap: () -> Unit){
    Box(
        modifier = Modifier
            .clickable {
                onTap()
            }
            .height(200.dp)
            .background(MaterialTheme.colorScheme.primary)
            .clip(shape = RoundedCornerShape(16.dp))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .height(100.dp)
                    .width(100.dp)
                    .background(MaterialTheme.colorScheme.inversePrimary, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = brandName[0].uppercase(), fontSize = 50.sp ,modifier = Modifier.align(
                    Alignment.Center), color = Color.White)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = brandName, color = MaterialTheme.colorScheme.inversePrimary)
        }
    }
}
