package com.nick.mowen.fleetio.components

import androidx.compose.foundation.clickable
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
fun VehicleListItem(vehicle: Vehicle, onClick: () -> Unit) {
    Card(modifier = Modifier
        .padding(8.dp)
        .clickable { onClick() }) {
        ListItem(
            leadingContent = { VehicleImage(vehicle) },
            headlineContent = {
                Text(
                    vehicle.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            },
            supportingContent = {
                Text(vehicle.getDescription(), fontSize = 12.sp)
            },
        )
    }
}

@Composable
@Preview
private fun VehicleListItemPreview() {
    VehicleListItem(
        Vehicle(
            123456,
            987,
            "123456",
            "",
            "Test Vehicle",
            "Car",
            "Active",
            "Cool",
            "Cars",
            2024,
            "Operator",
            null,
            "",
            "",
            "",
            ""
        )
    ) {

    }
}