package jordan.abad.clinicasaludplus.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// Desarrollado por: Jordan Abad
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Inicio.route
    ) {
        // Las pantallas se agregarán en los siguientes commits
    }
}