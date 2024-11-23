package com.nick.mowen.fleetio.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nick.mowen.fleetio.R
import com.nick.mowen.fleetio.data.VehicleStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehicleFilterBottomSheet(showBottomSheet: Boolean, onUpdateFilter: (String, Set<VehicleStatus>) -> Unit, onDismiss: () -> Unit) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val nameFilter = remember { mutableStateOf("") }
    val statusFilter = remember { mutableStateOf(VehicleStatus.entries.toSet()) }

    if (showBottomSheet) {
        ModalBottomSheet(onDismissRequest = onDismiss, sheetState = sheetState) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) { Text(stringResource(R.string.title_filter_vehicles), fontWeight = FontWeight.Bold, fontSize = 18.sp) }

                Text(stringResource(R.string.title_filter_by_name))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = nameFilter.value,
                    onValueChange = { nameFilter.value = it },
                    label = { Text(stringResource(R.string.action_filter_name)) }
                )

                Text(stringResource(R.string.title_filter_by_status))
                VehicleStatus.entries.map { status ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                statusFilter.value = statusFilter.value.updateValue(status, (status in statusFilter.value).not())
                            },
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Checkbox(status in statusFilter.value, { isChecked ->
                            statusFilter.value = statusFilter.value.updateValue(status, isChecked)
                        })
                        Text(
                            status.webStr, modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                    }
                }

                Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                    Button(
                        {
                            onUpdateFilter(nameFilter.value, statusFilter.value)
                            onDismiss()
                        },
                        shape = RoundedCornerShape(8.dp)
                    ) { Text(stringResource(R.string.action_apply_filter)) }
                    Spacer(Modifier.size(8.dp))
                    OutlinedButton({
                        nameFilter.value = ""
                        statusFilter.value = VehicleStatus.entries.toSet()
                    }, shape = RoundedCornerShape(8.dp)) { Text(stringResource(R.string.action_reset_filter)) }
                }
            }
        }
    }
}

fun <T> Set<T>.updateValue(value: T, add: Boolean): Set<T> {
    val copy = this.toMutableSet()

    if (add) {
        copy.add(value)
    } else {
        copy.remove(value)
    }

    return copy
}