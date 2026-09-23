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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
        Text(
            text = "Mis citas",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(listaCitasEjemplo) { cita ->
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
                        }
                    }
                }
            }
        }
    }
}
