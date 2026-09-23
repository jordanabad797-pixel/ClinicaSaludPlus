package jordan.abad.clinicasaludplus.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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

    val purpleDark = Color(0xFF6A1B9A)
    val lightGreenBackground = Color(0xFFE8F5E9)
    val successGreen = Color(0xFF2E7D32)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Ícono de check dentro de un círculo con fondo verde muy claro
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(lightGreenBackground, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = "Confirmado",
                tint = successGreen,
                modifier = Modifier.size(52.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "¡Cita agendada!",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = medico.nombre,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "$fecha, $hora",
            style = MaterialTheme.typography.bodyMedium,
            color = purpleDark,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                navController.navigate(Screen.MisCitas.route)
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
                text = "Ver mis citas",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
