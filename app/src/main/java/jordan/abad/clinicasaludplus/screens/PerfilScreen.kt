package jordan.abad.clinicasaludplus.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Desarrollado por: Jordan Abad
@Composable
fun PerfilScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Mi perfil", style = MaterialTheme.typography.headlineMedium)
        Text(text = "Jordan Abad", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Paciente", style = MaterialTheme.typography.bodyMedium)
    }
}