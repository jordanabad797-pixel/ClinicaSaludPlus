package jordan.abad.clinicasaludplus.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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

    val purpleDark = Color(0xFF6A1B9A)
    val purpleLight = Color(0xFFF3E5F5)
    val goldStar = Color(0xFFFFC107)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Encabezado con fondo morado oscuro
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = purpleDark)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "Clínica Salud+",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Hola, Jordan",
                    style = MaterialTheme.typography.bodyLarge,
                    color = purpleLight
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // LazyRow de chips de especialidad
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(especialidades) { especialidad ->
                val isSelected = especialidadSeleccionada == especialidad
                FilterChip(
                    selected = isSelected,
                    onClick = { especialidadSeleccionada = especialidad },
                    label = {
                        Text(
                            text = especialidad,
                            color = if (isSelected) purpleDark else MaterialTheme.colorScheme.onSurface,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = purpleLight,
                        selectedLabelColor = purpleDark
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Médicos disponibles",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Ícono circular morado a la izquierda
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(purpleLight, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Médico",
                                tint = purpleDark,
                                modifier = Modifier.size(28.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = medico.nombre,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = medico.especialidad,
                                style = MaterialTheme.typography.bodySmall,
                                color = purpleDark,
                                fontWeight = FontWeight.Medium
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = "Calificación",
                                tint = goldStar
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${medico.calificacion}",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}
