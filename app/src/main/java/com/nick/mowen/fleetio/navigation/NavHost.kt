package com.nick.mowen.fleetio.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nick.mowen.fleetio.data.Vehicle

@Composable
fun FleetioNavigation() {
    NavHost(rememberNavController(), startDestination = Unit) {
        composable<Unit> {

        }
        composable<Vehicle> {

        }
    }
}