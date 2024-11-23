package com.nick.mowen.fleetio.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.nick.mowen.fleetio.data.Vehicle

@Composable
fun VehicleListItem(vehicle: Vehicle) {
    Card(modifier = Modifier.padding(8.dp)) {
        ListItem(
            leadingContent = {
                AsyncImage(
                    vehicle.imageUrl,
                    "Vehicle Image",
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .border(2.dp, vehicle.getStatusColor(), shape = RoundedCornerShape(8.dp))
                )
            },
            headlineContent = {
                Text(
                    vehicle.getTitle(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            },
            supportingContent = {
                Text(vehicle.vehicleStatus)
            }
        )
    }
}

@Composable
@Preview
fun VehicleListItemPreview() {

}