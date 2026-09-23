package jordan.abad.clinicasaludplus.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import jordan.abad.clinicasaludplus.model.listaMedicos
import jordan.abad.clinicasaludplus.navigation.Screen

// Desarrollado por: Jordan Abad
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendarCitaScreen(navController: NavController, medicoId: Int) {
    val medico = listaMedicos.first { it.id == medicoId }

    val fechas = listOf("Jue 26", "Vie 27", "Sáb 28")
    val horas = listOf("9:00", "13:30", "3:00")

    // Selección única (funciona como RadioButton aunque se vea como chip)
    var fechaSeleccionada by remember { mutableStateOf(fechas[1]) }
    var horaSeleccionada by remember { mutableStateOf(horas[1]) }

    val purpleDark = Color(0xFF6A1B9A)
    val purpleLight = Color(0xFFF3E5F5)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Agendar cita") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp)
        ) {
            Text(
                text = "Médico: ${medico.nombre}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Selecciona fecha",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(fechas) { fecha ->
                    val isSelected = fechaSeleccionada == fecha
                    FilterChip(
                        selected = isSelected,
                        onClick = { fechaSeleccionada = fecha },
                        label = {
                            Text(
                                text = fecha,
                                color = if (isSelected) Color.White else purpleDark,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = purpleDark,
                            selectedLabelColor = Color.White,
                            containerColor = Color.Transparent
                        ),
                        border = FilterChipDefaults.filterChipBorder(
                            enabled = true,
                            selected = isSelected,
                            borderColor = purpleLight,
                            selectedBorderColor = purpleDark
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Selecciona hora",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(horas) { hora ->
                    val isSelected = horaSeleccionada == hora
                    FilterChip(
                        selected = isSelected,
                        onClick = { horaSeleccionada = hora },
                        label = {
                            Text(
                                text = hora,
                                color = if (isSelected) Color.White else purpleDark,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = purpleDark,
                            selectedLabelColor = Color.White,
                            containerColor = Color.Transparent
                        ),
                        border = FilterChipDefaults.filterChipBorder(
                            enabled = true,
                            selected = isSelected,
                            borderColor = purpleLight,
                            selectedBorderColor = purpleDark
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    navController.navigate(
                        Screen.Confirmacion.createRoute(medico.id, fechaSeleccionada, horaSeleccionada)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = purpleDark,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Confirmar cita",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
