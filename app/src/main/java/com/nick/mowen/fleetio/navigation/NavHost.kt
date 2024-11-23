package com.nick.mowen.fleetio.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nick.mowen.fleetio.data.Vehicle
import com.nick.mowen.fleetio.data.VehiclesViewModel
import com.nick.mowen.fleetio.vehicles.VehiclesScreen

@Composable
fun FleetioNavigation(viewModel: VehiclesViewModel) {
    rememberNavController().let { navController ->
        NavHost(navController, startDestination = Unit) {
            composable<Unit> {
                viewModel.getVehicles()
                VehiclesScreen(viewModel.vehicles, viewModel.isLoading, viewModel.canLoadMoreVehicles, onLoadMore = {
                    viewModel.getMoreVehicles()
                }) { vehicle ->
                    navController.navigate(vehicle)
                }
            }
            composable<Vehicle> {

            }
        }
    }
}