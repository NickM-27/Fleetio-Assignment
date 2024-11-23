package com.nick.mowen.fleetio.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.nick.mowen.fleetio.R
import com.nick.mowen.fleetio.data.Vehicle

@Composable
fun VehicleImage(vehicle: Vehicle) {
    if (vehicle.hasImage()) {
        AsyncImage(
            vehicle.imageUrl,
            stringResource(R.string.content_vehicle_image),
            modifier = Modifier
                .size(42.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(2.dp, vehicle.getStatusColor(), shape = RoundedCornerShape(8.dp))
        )
    } else {
        Column(
            modifier = Modifier
                .size(42.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(2.dp, vehicle.getStatusColor(), shape = RoundedCornerShape(8.dp))
                .background(Color.Gray),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(vehicle.getInitials())
        }
    }
}