package com.nick.mowen.fleetio.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nick.mowen.fleetio.components.VehicleImage
import com.nick.mowen.fleetio.data.Vehicle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehicleDetailsScreen(vehicle: Vehicle) {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, "")
            }
        }, title = {
            Row(
                modifier = Modifier.fillMaxWidth(0.8f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                VehicleImage(vehicle)
                Text(vehicle.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
        })
    }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {

        }
    }
}