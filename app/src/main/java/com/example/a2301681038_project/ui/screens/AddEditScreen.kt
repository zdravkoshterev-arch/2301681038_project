package com.example.a2301681038_project.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.a2301681038_project.data.local.ServiceRecord
import com.example.a2301681038_project.viewmodel.ServiceViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreen(
    viewModel: ServiceViewModel,
    recordId: Int?,
    onNavigateBack: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var serviceName by remember { mutableStateOf("") }
    var mileage by remember { mutableStateOf("") }
    var cost by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var date by remember { mutableStateOf(System.currentTimeMillis()) }

    var existingRecord by remember { mutableStateOf<ServiceRecord?>(null) }

    LaunchedEffect(recordId) {
        if (recordId != null && recordId != -1) {
            val record = viewModel.getRecordById(recordId)
            if (record != null) {
                existingRecord = record
                serviceName = record.serviceName
                mileage = record.mileage.toString()
                cost = record.cost.toString()
                notes = record.notes
                date = record.date
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(if (recordId == null || recordId == -1) "Add Record" else "Edit Record") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    if (existingRecord != null) {
                        IconButton(onClick = {
                            scope.launch {
                                viewModel.delete(existingRecord!!)
                                onNavigateBack()
                            }
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete")
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = serviceName,
                onValueChange = { serviceName = it },
                label = { Text("Service Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = mileage,
                onValueChange = { mileage = it },
                label = { Text("Mileage") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = cost,
                onValueChange = { cost = it },
                label = { Text("Cost") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )

            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text("Notes") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            Button(
                onClick = {
                    if (serviceName.isBlank() || mileage.isBlank() || cost.isBlank()) {
                        scope.launch {
                            snackbarHostState.showSnackbar("Please fill all required fields")
                        }
                        return@Button
                    }

                    val mileageInt = mileage.toIntOrNull() ?: 0
                    val costDouble = cost.toDoubleOrNull() ?: 0.0

                    val record = ServiceRecord(
                        id = existingRecord?.id ?: 0,
                        serviceName = serviceName,
                        mileage = mileageInt,
                        cost = costDouble,
                        date = date,
                        notes = notes
                    )

                    scope.launch {
                        if (existingRecord == null) {
                            viewModel.insert(record)
                        } else {
                            viewModel.update(record)
                        }
                        onNavigateBack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save")
            }
        }
    }
}
