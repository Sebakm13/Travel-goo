package com.example.travelgo.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.TravelExplore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.travelgo.ui.screens.*
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier


data class BottomNavItem(val route: String, val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost(navController: NavHostController) {
    var reservas by remember { mutableStateOf(listOf<String>()) }

    NavHost(navController, startDestination = NavRoutes.LOGIN) {
        composable(NavRoutes.LOGIN) {
            LoginScreen(navController)
        }

        composable(NavRoutes.HOME) {
            BottomNavScaffold(
                reservas = reservas,
                onAddReserva = { nueva ->
                    reservas = reservas + nueva
                }
            )
        }
    }
}

@Composable
fun BottomNavScaffold(
    reservas: List<String>,
    onAddReserva: (String) -> Unit
) {
    val navController = rememberNavController()
    val items = listOf(
        BottomNavItem("home", "Inicio", Icons.Default.Home),
        BottomNavItem("reservas", "Reservas", Icons.Default.TravelExplore),
        BottomNavItem("perfil", "Perfil", Icons.Default.Person)
    )

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = MaterialTheme.colorScheme.primary) {
                val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
                items.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController,
            startDestination = "home",
            Modifier.padding(padding)
        ) {
            composable("home") {
                DetallePaqueteScreen(onBack = {}, onReservar = onAddReserva)
            }
            composable("reservas") {
                ReservasScreen(reservas)
            }
            composable("perfil") {
                PerfilScreen(onBack = {})
            }
        }
    }
}
