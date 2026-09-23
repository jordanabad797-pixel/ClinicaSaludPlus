package jordan.abad.clinicasaludplus.navigation

// Desarrollado por: Jordan Abad
sealed class Screen(val route: String) {
    object Inicio : Screen("inicio")

    object PerfilMedico : Screen("perfil_medico/{medicoId}") {
        fun createRoute(medicoId: Int): String = "perfil_medico/$medicoId"
    }

    object AgendarCita : Screen("agendar_cita/{medicoId}") {
        fun createRoute(medicoId: Int): String = "agendar_cita/$medicoId"
    }

    object Confirmacion : Screen("confirmacion/{medicoId}/{fecha}/{hora}") {
        fun createRoute(medicoId: Int, fecha: String, hora: String): String {
            val fechaEnc = android.net.Uri.encode(fecha)
            val horaEnc = android.net.Uri.encode(hora)
            return "confirmacion/$medicoId/$fechaEnc/$horaEnc"
        }
    }

    object MisCitas : Screen("mis_citas")

    object HistorialMedico : Screen("historial_medico")

    object Perfil : Screen("perfil")
}