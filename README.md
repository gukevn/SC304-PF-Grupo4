# SC304-PF-Grupo4
PF Repo Grupo 4
## Avance I — Estructura y GUI base  
**Responsable:** Kevin Gutiérrez Chinchilla (Líder de grupo)  

- Se creó la estructura de paquetes `cr.ufide.sandwich` y `cr.ufide.sandwich.ui`.  
- Se implementó la interfaz inicial con cuatro secciones: **Caja**, **Mazo**, **Mano** y **Pozo**.  
- Se confirmó que la ventana principal compila y ejecuta correctamente.  
- Este avance sirve de base para integrar la lógica del juego, validaciones y persistencia en las siguientes fases.  

**Comandos de compilación y ejecución:**
```bash
javac --release 21 -d out src/main/java/cr/ufide/sandwich/*.java src/main/java/cr/ufide/sandwich/ui/*.java
java -cp out cr.ufide.sandwich.Main

## Organización del equipo y próximas tareas

**Kevin Gutiérrez (Líder):**
- Coordina commits y revisiones.
- Mantiene las ramas sincronizadas con `main`.
- Desarrolla las clases principales del juego (`Carta`, `Juego`, `Main`).

**Companero:**
- Implementará la lógica de las cartas y el mazo (barajar, repartir, validar sándwiches).

**Companero:**
- Implementará la clase `Jugador` y la lógica de manejo de mano (agregar y descartar cartas).

**Companero:**
- Implementará el sistema de interfaz gráfica avanzada: botones, acciones y eventos dentro de `VentanaJuego`.

**Companero:**
- Se encargará de la documentación (archivos en `docs/`) y de las pruebas iniciales en `src/test/java`.
