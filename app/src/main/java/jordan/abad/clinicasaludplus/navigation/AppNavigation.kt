package jordan.abad.clinicasaludplus.navigation

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import jordan.abad.clinicasaludplus.screens.AgendarCitaScreen
import jordan.abad.clinicasaludplus.screens.ConfirmacionScreen
import jordan.abad.clinicasaludplus.screens.HistorialMedicoScreen
import jordan.abad.clinicasaludplus.screens.InicioScreen
import jordan.abad.clinicasaludplus.screens.MisCitasScreen
import jordan.abad.clinicasaludplus.screens.PerfilMedicoScreen
import jordan.abad.clinicasaludplus.screens.PerfilScreen
import kotlinx.coroutines.launch

// Desarrollado por: Jordan Abad
private val drawerRoutes = listOf(
    Screen.Inicio.route,
    Screen.MisCitas.route,
    Screen.HistorialMedico.route,
    Screen.Perfil.route
)

private fun tituloSegunRuta(route: String?): String = when (route) {
    Screen.Inicio.route -> "Clínica Salud+"
    Screen.MisCitas.route -> "Mis citas"
    Screen.HistorialMedico.route -> "Historial médico"
    Screen.Perfil.route -> "Mi perfil"
    else -> ""
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(modifier = Modifier.padding(24.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(
                            modifier = Modifier
                                .size(48.dp)
                                .background(Color(0xFF6A1B9A), CircleShape),
                        ) {}
                        Spacer(modifier = Modifier.height(0.dp))
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = "Jordan Abad", style = MaterialTheme.typography.titleMedium)
                    Text(
                        text = "Paciente",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                HorizontalDivider()

                NavigationDrawerItem(
                    label = { Text("Inicio") },
                    icon = { Icon(Icons.Filled.Home, contentDescription = null) },
                    selected = currentRoute == Screen.Inicio.route,
                    onClick = {
                        navController.navigate(Screen.Inicio.route) {
                            popUpTo(Screen.Inicio.route) { inclusive = true }
                        }
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
                NavigationDrawerItem(
                    label = { Text("Mis citas") },
                    icon = { Icon(Icons.Filled.History, contentDescription = null) },
                    selected = currentRoute == Screen.MisCitas.route,
                    onClick = {
                        navController.navigate(Screen.MisCitas.route)
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
                NavigationDrawerItem(
                    label = { Text("Historial médico") },
                    icon = { Icon(Icons.Filled.History, contentDescription = null) },
                    selected = currentRoute == Screen.HistorialMedico.route,
                    onClick = {
                        navController.navigate(Screen.HistorialMedico.route)
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
                NavigationDrawerItem(
                    label = { Text("Perfil") },
                    icon = { Icon(Icons.Filled.Person, contentDescription = null) },
                    selected = currentRoute == Screen.Perfil.route,
                    onClick = {
                        navController.navigate(Screen.Perfil.route)
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                if (currentRoute in drawerRoutes) {
                    TopAppBar(
                        title = { Text(tituloSegunRuta(currentRoute)) },
                        navigationIcon = {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(Icons.Filled.Menu, contentDescription = "Menú")
                            }
                        }
                    )
                }
            }
        ) { padding ->
            NavHost(
                navController = navController,
                startDestination = Screen.Inicio.route,
                modifier = Modifier.padding(padding)
            ) {
                composable(Screen.Inicio.route) {
                    InicioScreen(navController)
                }
                composable(Screen.MisCitas.route) {
                    MisCitasScreen()
                }
                composable(Screen.HistorialMedico.route) {
                    HistorialMedicoScreen()
                }
                composable(Screen.Perfil.route) {
                    PerfilScreen()
                }
                composable(
                    route = Screen.PerfilMedico.route,
                    arguments = listOf(navArgument("medicoId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val medicoId = backStackEntry.arguments?.getInt("medicoId") ?: 0
                    PerfilMedicoScreen(navController, medicoId)
                }
                composable(
                    route = Screen.AgendarCita.route,
                    arguments = listOf(navArgument("medicoId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val medicoId = backStackEntry.arguments?.getInt("medicoId") ?: 0
                    AgendarCitaScreen(navController, medicoId)
                }
                composable(
                    route = Screen.Confirmacion.route,
                    arguments = listOf(
                        navArgument("medicoId") { type = NavType.IntType },
                        navArgument("fecha") { type = NavType.StringType },
                        navArgument("hora") { type = NavType.StringType }
                    )
                ) { backStackEntry ->
                    val medicoId = backStackEntry.arguments?.getInt("medicoId") ?: 0
                    val fecha = Uri.decode(backStackEntry.arguments?.getString("fecha") ?: "")
                    val hora = Uri.decode(backStackEntry.arguments?.getString("hora") ?: "")
                    ConfirmacionScreen(navController, medicoId, fecha, hora)
                }
            }
        }
    }
}