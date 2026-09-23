package jordan.abad.clinicasaludplus.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import jordan.abad.clinicasaludplus.model.Cita
import jordan.abad.clinicasaludplus.model.listaCitasEjemplo

// Desarrollado por: Jordan Abad
@Composable
fun MisCitasScreen() {
    // Control de estado para el diálogo de confirmación y cancelación de citas
    var mostrarDialogo by remember { mutableStateOf(false) }
    var citaACancelar by remember { mutableStateOf<Cita?>(null) }

    // Lista reactiva observable por Compose
    val citasState = remember {
        mutableStateListOf<Cita>().apply { addAll(listaCitasEjemplo) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Mis citas",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (citasState.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No tienes citas agendadas.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(citasState) { cita ->
                    val isConfirmada = cita.estado == "Confirmada"
                    val barColor = if (isConfirmada) Color(0xFF4CAF50) else Color(0xFF9E9E9E)
                    val pillBgColor = if (isConfirmada) Color(0xFFE8F5E9) else Color(0xFFEEEEEE)
                    val pillTextColor = if (isConfirmada) Color(0xFF2E7D32) else Color(0xFF616161)

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(IntrinsicSize.Min),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Borde izquierdo de color (barra vertical delgada)
                            Box(
                                modifier = Modifier
                                    .width(6.dp)
                                    .fillMaxHeight()
                                    .background(barColor)
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = cita.medico.nombre,
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "${cita.fecha}, ${cita.hora}",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    // Píldora de estado
                                    Surface(
                                        shape = RoundedCornerShape(16.dp),
                                        color = pillBgColor
                                    ) {
                                        Text(
                                            text = cita.estado,
                                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                            color = pillTextColor,
                                            style = MaterialTheme.typography.labelMedium,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }

                                    // Botón de cancelar (solo se muestra para citas "Confirmada")
                                    if (isConfirmada) {
                                        IconButton(
                                            onClick = {
                                                citaACancelar = cita
                                                mostrarDialogo = true
                                            },
                                            modifier = Modifier.size(32.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Filled.Close,
                                                contentDescription = "Cancelar cita",
                                                tint = MaterialTheme.colorScheme.error
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // Diálogo de confirmación para cancelar cita
    if (mostrarDialogo && citaACancelar != null) {
        val cita = citaACancelar!!
        AlertDialog(
            onDismissRequest = {
                mostrarDialogo = false
                citaACancelar = null
            },
            title = {
                Text(
                    text = "Cancelar cita",
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = "¿Estás seguro de que deseas cancelar tu cita con ${cita.medico.nombre} el ${cita.fecha} a las ${cita.hora}? Esta acción no se puede deshacer."
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        citasState.remove(cita)
                        listaCitasEjemplo.remove(cita)
                        mostrarDialogo = false
                        citaACancelar = null
                    }
                ) {
                    Text(
                        text = "Sí, cancelar",
                        color = MaterialTheme.colorScheme.error,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        mostrarDialogo = false
                        citaACancelar = null
                    }
                ) {
                    Text(text = "No")
                }
            }
        )
    }
}
