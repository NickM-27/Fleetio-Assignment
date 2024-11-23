package com.nick.mowen.fleetio.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nick.mowen.fleetio.data.Vehicle

@Composable
fun VehicleListItem(vehicle: Vehicle) {
    Card(modifier = Modifier.padding(8.dp)) {
        ListItem(
            headlineContent = {
                Text(
                    "${vehicle.id} [${vehicle.year} ${vehicle.make} ${vehicle.model}]",
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