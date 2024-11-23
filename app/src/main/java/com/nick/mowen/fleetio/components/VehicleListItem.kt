package com.nick.mowen.fleetio.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.nick.mowen.fleetio.R
import com.nick.mowen.fleetio.data.Vehicle

@Composable
fun VehicleListItem(vehicle: Vehicle) {
    Card(modifier = Modifier.padding(8.dp)) {
        ListItem(
            leadingContent = {
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
            },
            headlineContent = {
                Text(
                    vehicle.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            },
            supportingContent = {
                Text(vehicle.getDescription(), fontSize = 12.sp)
            }
        )
    }
}

@Composable
@Preview
fun VehicleListItemPreview() {
    VehicleListItem(Vehicle(123456, 987, "Test Vehicle", "Car", "Active", "Cool", "Cars", 2024, "Operator", null))
}