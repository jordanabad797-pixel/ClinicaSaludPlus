package jordan.abad.clinicasaludplus.model

// Desarrollado por: Jordan Abad
data class Medico(
    val id: Int,
    val nombre: String,
    val especialidad: String,
    val calificacion: Double,
    val experiencia: String,
    val descripcion: String
)

// Datos de ejemplo (mínimo 3 médicos, mínimo 2 especialidades)
val listaMedicos = listOf(
    Medico(
        id = 1,
        nombre = "Dra. Ana Torres",
        especialidad = "Cardiología",
        calificacion = 4.9,
        experiencia = "12 años de experiencia",
        descripcion = "Especialista en arritmias e hipertensión, formación en la Clínica Mayo."
    ),
    Medico(
        id = 2,
        nombre = "Dr. Luis Vega",
        especialidad = "Pediatría",
        calificacion = 4.7,
        experiencia = "8 años de experiencia",
        descripcion = "Especialista en desarrollo infantil y vacunación."
    ),
    Medico(
        id = 3,
        nombre = "Dra. Rosa Díaz",
        especialidad = "Cardiología",
        calificacion = 4.8,
        experiencia = "10 años de experiencia",
        descripcion = "Especialista en enfermedades coronarias y ecocardiografía."
    ),
    Medico(
        id = 4,
        nombre = "Dr. Mario Chávez",
        especialidad = "Pediatría",
        calificacion = 4.6,
        experiencia = "6 años de experiencia",
        descripcion = "Especialista en nutrición infantil."
    )
)