package jordan.abad.clinicasaludplus.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jordan.abad.clinicasaludplus.model.listaCitasEjemplo

// Desarrollado por: Jordan Abad
@Composable
fun HistorialMedicoScreen() {
    val completadas = listaCitasEjemplo.filter { it.estado == "Completada" }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Historial médico", style = MaterialTheme.typography.headlineMedium)
        Text(
            text = "Citas completadas: ${completadas.size}",
            style = MaterialTheme.typography.bodyMedium
        )
        completadas.forEach { cita ->
            Text(text = "• ${cita.medico.nombre} — ${cita.fecha}")
        }
    }
}