package com.example.travelgo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetallePaqueteScreen(
    onBack: () -> Unit,
    onReservar: (String) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del paquete") },
                navigationIcon = {
                    TextButton(onClick = onBack) {
                        Text("< Volver", color = MaterialTheme.colorScheme.onPrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("Aventura Eco Atacama", style = MaterialTheme.typography.headlineSmall)
            Text("3 días / 2 noches en San Pedro de Atacama")
            Spacer(Modifier.height(16.dp))
            Button(onClick = {
                onReservar("Aventura Eco Atacama")
                scope.launch {
                    snackbarHostState.showSnackbar("Reserva creada correctamente ✅")
                }
            }) {
                Text("Reservar")
            }
        }
    }
}
