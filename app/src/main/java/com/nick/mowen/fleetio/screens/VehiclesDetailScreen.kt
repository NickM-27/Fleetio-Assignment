package com.nick.mowen.fleetio.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nick.mowen.fleetio.R
import com.nick.mowen.fleetio.components.LazyLoadingColumn
import com.nick.mowen.fleetio.components.TabGroup
import com.nick.mowen.fleetio.components.VehicleImage
import com.nick.mowen.fleetio.components.VehicleListItem
import com.nick.mowen.fleetio.data.Vehicle
import com.nick.mowen.fleetio.data.VehicleComment
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehicleDetailsScreen(vehicle: Vehicle, commentsState: StateFlow<List<VehicleComment>?>, onNavigateBack: () -> Unit) {
    var currentTab by remember { mutableStateOf("Details") }

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(navigationIcon = {
            IconButton(onClick = onNavigateBack) {
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
            TabGroup(Modifier.fillMaxWidth(), listOf("Details", "Comments"), currentTab) {
                currentTab = it
            }

            if (currentTab == "Details") {
                VehicleDetailsLayout(vehicle)
            } else {
                VehicleCommentsLayout(commentsState)
            }
        }
    }
}

@Composable
fun VehicleDetailsLayout(vehicle: Vehicle) {
    Column {
        ListItem(
            leadingContent = { Icon(Icons.Filled.Info, "") },
            headlineContent = { Text(stringResource(R.string.title_vin)) },
            supportingContent = { Text(vehicle.vin) }
        )

        if (vehicle.hasLicensePlate()) {
            ListItem(
                leadingContent = { Icon(Icons.Filled.Info, "") },
                headlineContent = { Text(stringResource(R.string.title_license_plate)) },
                supportingContent = { Text(vehicle.licensePlate ?: "") }
            )
        }

        ListItem(
            leadingContent = { Icon(Icons.Filled.Info, "") },
            headlineContent = { Text(stringResource(R.string.title_primary_meter)) },
            supportingContent = { Text(vehicle.getPrimaryMeter()) }
        )

        if (vehicle.hasSecondaryMeter()) {
            ListItem(
                headlineContent = { Text(stringResource(R.string.title_secondary_meter)) },
                supportingContent = { Text(vehicle.getSecondaryMeter()) }
            )
        }

        ListItem(
            leadingContent = { Icon(Icons.Filled.Info, "") },
            headlineContent = { Text(stringResource(R.string.title_vehicle_status)) },
            supportingContent = { Text(vehicle.vehicleStatus) }
        )
    }
}

@Composable
fun VehicleCommentsLayout(commentsState: StateFlow<List<VehicleComment>?>) {
    val comments = commentsState.collectAsState()
    LazyLoadingColumn(loading = comments.value == null, listItems = comments.value ?: emptyList(),
        itemKey = { vehicle -> vehicle.id },
        itemContent = { comment -> Text(comment.comment) },
        loadingItem = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        },
        canLoadMore = false,
        loadMore = {})
}

