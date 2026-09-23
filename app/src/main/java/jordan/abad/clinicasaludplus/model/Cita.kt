package jordan.abad.clinicasaludplus.model

// Desarrollado por: Jordan Abad
data class Cita(
    val id: Int,
    val medico: Medico,
    val fecha: String,
    val hora: String,
    val estado: String // "Confirmada" o "Completada"
)

// Lista mutable en memoria para simular "Mis citas" (sin ViewModel, solo remember)
val listaCitasEjemplo = mutableListOf(
    Cita(
        id = 1,
        medico = listaMedicos[0],
        fecha = "Viernes 27",
        hora = "10:30 am",
        estado = "Confirmada"
    ),
    Cita(
        id = 2,
        medico = listaMedicos[1],
        fecha = "Miércoles 15",
        hora = "3:00 pm",
        estado = "Completada"
    )
)