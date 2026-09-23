package jordan.abad.clinicasaludplus.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import jordan.abad.clinicasaludplus.model.Cita
import jordan.abad.clinicasaludplus.model.listaCitasEjemplo
import jordan.abad.clinicasaludplus.model.listaMedicos
import jordan.abad.clinicasaludplus.navigation.Screen

// Desarrollado por: Jordan Abad
@Composable
fun ConfirmacionScreen(
    navController: NavController,
    medicoId: Int,
    fecha: String,
    hora: String
) {
    val medico = listaMedicos.first { it.id == medicoId }

    // Agrega la cita a la lista en memoria (simulación sin ViewModel)
    val yaExiste = listaCitasEjemplo.any {
        it.medico.id == medicoId && it.fecha == fecha && it.hora == hora
    }
    if (!yaExiste) {
        listaCitasEjemplo.add(
            Cita(
                id = listaCitasEjemplo.size + 1,
                medico = medico,
                fecha = fecha,
                hora = hora,
                estado = "Confirmada"
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Filled.CheckCircle,
            contentDescription = "Confirmado",
            tint = Color(0xFF4CAF50),
            modifier = Modifier.size(64.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "¡Cita agendada!", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = medico.nombre, style = MaterialTheme.typography.bodyLarge)
        Text(text = "$fecha, $hora", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                navController.navigate(Screen.MisCitas.route)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver mis citas")
        }
    }
}