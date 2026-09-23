package jordan.abad.clinicasaludplus.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import jordan.abad.clinicasaludplus.model.listaCitasEjemplo

// Desarrollado por: Jordan Abad
@Composable
fun MisCitasScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Mis citas", style = MaterialTheme.typography.headlineMedium)

        androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(8.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(listaCitasEjemplo) { cita ->
                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = cita.medico.nombre, style = MaterialTheme.typography.titleMedium)
                        Text(
                            text = "${cita.fecha}, ${cita.hora}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        val colorEstado = if (cita.estado == "Confirmada") {
                            Color(0xFF4CAF50)
                        } else {
                            Color(0xFF9E9E9E)
                        }
                        Text(
                            text = cita.estado,
                            color = colorEstado,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            }
        }
    }
}