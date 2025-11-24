# The Sandwich Guy — Avance I

## Estructura de datos
SC304-PF-Grupo4

---

## Integrantes
- Kevin Gutiérrez Chinchilla — Carné: FI24039518 — GitHub: @gukevn — Email Git: gukev@gmail. 
- FREDDY ALFARO ALVAREZ — Carné: FI24042010 — GitHub: @Falfaro04 — Email Git: freddyprube123@gmail.com



---

## Objetivo

Debemos desarrollar una aplicación que simule un juego de naipes.  
El sistema permitirá al jugador interactuar con un conjunto de cartas, aplicando reglas específicas para formar combinaciones válidas y avanzar dentro del juego.  
El propósito es aplicar los principios de programación orientada a objetos, manejo de estructuras de datos, e implementación de entrada/salida de archivos para guardar y cargar partidas de forma dinámica.

---

## Editores/IDEs
- Visual Studio Code
- **Java SE 21 (JDK 21)** — OpenJDK

---

## Instructivo

### Instalación
1. Descargar y asegurarse de tener instalado Java SE21 (JDK 21).  
2. Clonar el repositorio desde GitHub (SC304-PF-Grupo4).  
3. Abrir el proyecto en Visual Studio Code.

### Compilación
1. Estando en el IDE, nos ubicamos en el archivo principal (Main).  
2. Usamos la opción "Run" para ejecutar (o en terminal se puede hacer usando `javac`).

### Ejecución
1. Una vez ejecutado usando "Run" o por medio de la terminal, el programa abrirá una interfaz, donde el usuario podrá interactuar con los menús.
2. Para ejecutar ingrese el siguiente comando: java -cp out cr.ufide.sandwich.Main





---
# Avance II

## Componentes solicitados

- **Caja**: estructura tipo lista; contiene inicialmente las 52 cartas.  
- **Mazo**: estructura tipo pila; recibe las cartas mezcladas.  
- **Mano**: estructura tipo lista; vacía en este avance.  
- **Pozo**: estructura tipo cola; vacío en este avance.

---

## Funcionalidad de "Barajar"

Se pasa **toda la Caja al Mazo** de forma **aleatoria**, cumpliendo el requisito del avance.

---

## Interfaz gráfica

La interfaz muestra:

- Panel de Caja con cantidad de cartas  
- Panel de Mazo  
- Panel de Mano  
- Panel de Pozo  
- Botón **"Barajar: Caja a Mazo"**  
- Cartas representadas visualmente con color, palo y valor
