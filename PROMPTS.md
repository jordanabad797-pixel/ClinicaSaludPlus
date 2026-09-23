# PROMPTS.md - Clinica Salud+ (rama mejora-ia)

Documentacion de los prompts usados con IA (Gemini) para la Fase 2 de la tarea.

---

## Prompt 1: Rediseno visual - Inicio, Perfil medico, Agendar cita, Confirmacion

Que le pedi: Mejorar la presentacion visual de InicioScreen, PerfilMedicoScreen, AgendarCitaScreen y ConfirmacionScreen con un tema morado (color principal 0xFF6A1B9A, acento claro 0xFFF3E5F5): encabezado morado en Inicio, icono circular morado en Perfil del medico, chips seleccionados en morado solido en Agendar cita, e icono de check sobre fondo verde claro en Confirmacion. Sin tocar la logica de navegacion existente.

Commit: 01-mejora-ia - Rediseno visual con tema morado en pantallas del flujo principal

---

## Prompt 2: Rediseno visual - Drawer y Mis Citas

Que le pedi: Agregar un avatar circular con iniciales JA y resaltar el item seleccionado del Drawer con fondo morado claro; en MisCitasScreen, agregar un borde de color a la izquierda de cada tarjeta de cita (verde si Confirmada, gris si Completada) y mostrar el estado como una pildora de color.

Commit: 02-mejora-ia - Rediseno visual del Drawer y MisCitasScreen con estados diferenciados

---

## Prompt 3: Mejora funcional - Cancelar cita con AlertDialog

Que le pedi: Agregar boton de cancelar en cada cita Confirmada dentro de MisCitasScreen, que al presionarlo muestre un AlertDialog pidiendo confirmacion (Si cancelar / No), y que al confirmar elimine la cita de la lista usando mutableStateListOf para que Compose recomponga la UI.

Commit: 03-mejora-ia - Funcionalidad para cancelar citas con AlertDialog de confirmacion
