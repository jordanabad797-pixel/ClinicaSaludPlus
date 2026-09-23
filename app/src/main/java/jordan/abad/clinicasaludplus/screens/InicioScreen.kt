package jordan.abad.clinicasaludplus.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import jordan.abad.clinicasaludplus.model.listaMedicos
import jordan.abad.clinicasaludplus.navigation.Screen

// Desarrollado por: Jordan Abad
@Composable
fun InicioScreen(navController: NavController) {
    val especialidades = listOf("Todas", "Cardiología", "Pediatría")
    var especialidadSeleccionada by remember { mutableStateOf("Todas") }

    val medicosFiltrados = if (especialidadSeleccionada == "Todas") {
        listaMedicos
    } else {
        listaMedicos.filter { it.especialidad == especialidadSeleccionada }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Clínica Salud+",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "Hola, Jordan",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))

        // LazyRow de chips de especialidad
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(especialidades) { especialidad ->
                FilterChip(
                    selected = especialidadSeleccionada == especialidad,
                    onClick = { especialidadSeleccionada = especialidad },
                    label = { Text(especialidad) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Médicos disponibles",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        // LazyColumn de médicos
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(medicosFiltrados) { medico ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(Screen.PerfilMedico.createRoute(medico.id))
                        },
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(text = medico.nombre, style = MaterialTheme.typography.titleMedium)
                            Text(
                                text = medico.especialidad,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Calificación",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(text = " ${medico.calificacion}")
                    }
                }
            }
        }
    }
}