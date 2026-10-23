package com.example.lab_7

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun DriversScreen(navController: NavController) {
    var driverName by remember {
        mutableStateOf("")
    }

    val drivers = remember {
        mutableStateListOf<String>()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Back button
        Button(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.testTag("backButton")
        ) {
            Text("Back to Home")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Title
        Text(
            text = "FormulaZero Drivers",
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Text field and Add button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextField(
                value = driverName,
                onValueChange = {
                    driverName = it
                },
                label = {
                    Text("Driver Name")
                },
                modifier = Modifier
                    .weight(1f)
                    .testTag("driverInput"),
                singleLine = true
            )

            Button(
                onClick = {
                    if (driverName.isNotBlank()) {
                        drivers.add(driverName.trim())
                        driverName = ""
                    }
                },
                modifier = Modifier.testTag("addDriverButton")
            ) {
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Clear all drivers button
        Button(
            onClick = {
                drivers.clear()
            },
            modifier = Modifier.testTag("clearDriversButton")
        ) {
            Text("Clear Drivers")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Driver list title
        Text(
            text = "Driver List",
            fontSize = 22.sp
        )

        // Display all drivers
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .testTag("driverList")
        ) {
            items(drivers) { driver ->
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = driver,
                    fontSize = 24.sp
                )
            }
        }
    }
}
