package com.nick.mowen.fleetio.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TabGroup(modifier: Modifier = Modifier, tabs: List<String>, currentTab: String, onSelectTab: (String) -> Unit) {
    Row(modifier, horizontalArrangement = Arrangement.SpaceEvenly) {
        tabs.map { tab ->
            if (tab == currentTab) {
                Button(shape = RoundedCornerShape(8.dp), onClick = {}) {
                    Text(tab)
                }
            } else {
                OutlinedButton(shape = RoundedCornerShape(8.dp), onClick = { onSelectTab(tab) }) {
                    Text(tab)
                }
            }
        }
    }
}

@Composable
@Preview
private fun TabGroupPreview() {
    TabGroup(tabs = listOf("One", "Two"), currentTab = "One") {

    }
}