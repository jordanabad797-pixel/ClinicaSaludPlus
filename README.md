# Clínica Salud+

App de reserva de citas médicas — Tarea integradora Semanas 1 a 6 (sin MVVM).
Curso: Programación en Móviles — Docente: Juan León S.
Alumno: Jordan Abad

## Rubro elegido
**Opción A — Clínica Salud+** (Reserva de citas médicas, navegación secundaria con Drawer)

## Requisitos funcionales implementados

- **Inicio (InicioScreen)**: LazyRow con chips de especialidad (Todas, Cardiología, Pediatría) y LazyColumn con lista de médicos (4 médicos de ejemplo), cada tarjeta con nombre, especialidad y calificación.
- **Perfil del médico (PerfilMedicoScreen)**: recibe el médico elegido por parámetro de navegación (medicoId: Int); botón "Agendar cita".
- **Agendar cita (AgendarCitaScreen)**: selección de fecha (3 opciones) y hora (3 opciones), ambas de selección única mediante FilterChip (funcionan como RadioButton).
- **Confirmación (ConfirmacionScreen)**: resumen de la cita agendada (médico, fecha, hora); botón para ver "Mis citas". Guarda la cita en la lista en memoria.
- **Menú lateral / Drawer (AppNavigation)**: ícono en la topBar de Inicio, Mis citas, Historial médico y Perfil; 4 destinos disponibles.
- **Mis citas (MisCitasScreen)**: LazyColumn con las citas agendadas, cada una con su estado (Confirmada / Completada) diferenciado visualmente por color.
- **Historial médico (HistorialMedicoScreen)**: lista de citas completadas.
- **Perfil (PerfilScreen)**: datos básicos del paciente.

## Flujo de navegación secuencial
Inicio -> Perfil del médico -> Agendar cita -> Confirmación -> Mis citas

## Arquitectura
- Sin ViewModel / sin MVVM — estado manejado con remember / mutableStateOf.
- Navegación con Jetpack Navigation Compose (NavHost, sealed class Screen).
- Modelos de datos: Medico.kt, Cita.kt (con listas de ejemplo en memoria).

## Ramas del repositorio
- main: desarrollo completo sin IA (8 commits).
- mejora-ia: mejora funcional con ayuda de IA (ver PROMPTS.md).
