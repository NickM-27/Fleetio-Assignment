package com.nick.mowen.fleetio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nick.mowen.fleetio.data.VehiclesViewModel
import com.nick.mowen.fleetio.navigation.FleetioNavigation
import com.nick.mowen.fleetio.ui.theme.FleetioTheme

class MainActivity : ComponentActivity() {

    val vehiclesViewModel: VehiclesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FleetioTheme {
                FleetioNavigation(vehiclesViewModel)
            }
        }
    }
}