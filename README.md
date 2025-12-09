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

Instalación: “Requiere JDK 21”

Compilación: javac src/main/java/cr/ufide/sandwich/*.java src/main/java/cr/ufide/sandwich/ui/*.java

Ejecución: java -cp src/main/java cr.ufide.sandwich.Main


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




# Avance III y final 

# "The Sandwich Guy — Proyecto Final"

# Estructura_datos: "SC304-PF-Grupo4"

# descripcion: |
    Aplicación de escritorio que simula el juego de naipes The Sandwich Guy.

    El sistema permite crear partidas nuevas, cargar partidas existentes,

    barajar cartas, validar sándwiches, descartar, y determinar estados de victoria o derrota.

integrantes:
  - nombre: "Kevin Gutiérrez Chinchilla"
    carne: "FI24039518"
    github: "@gukevn"
    email_git: "gukev@gmail.com"

  - nombre: "Freddy Alfaro Álvarez"
    carne: "FI24042010"
    github: "@Falfaro04"
    email_git: "freddyprube123@gmail.com"

# Objetivo: |
  Desarrollar una aplicación de escritorio del juego The Sandwich Guy aplicando:
  - Programación orientada a objetos
  - Estructuras de datos
  - Interfaz gráfica con Java Swing
  - Persistencia mediante archivos XML
  - Manipulación dinámica de cartas y estructuras

# Editores_IDEs:
  - "Visual Studio Code"
  - "Java SE 21 (JDK 21) — OpenJDK"

# Instructivo:
  instalacion:
    - "Instalar Java SE 21 (JDK 21)"
    - "Clonar o descargar el repositorio"
    - "Abrir el proyecto en Visual Studio Code"

## Compilación

Para compilar el proyecto desde la terminal (ubicado en `src/main/java`):

javac cr/ufide/sandwich/*.java cr/ufide/sandwich/ui/*.java

## Ejecución

java cr.ufide.sandwich.Main


## Ejecución
java cr.ufide.sandwich.Main


## Ejecución
java cr.ufide.sandwich.Main






# Estructuras_de_datos:
  carta:
    tipo: "Clase propia"
    descripcion: "Representa una carta con valor, palo, nombre y color"
  caja:
    tipo: "Lista doble"
    descripcion: "Contiene las 52 cartas ordenadas inicialmente"
  mazo:
    tipo: "Pila"
    descripcion: "Recibe cartas mezcladas aleatoriamente"
  mano:
    tipo: "Lista circular"
    descripcion: "Contiene hasta 8 cartas boca arriba"
  pozo:
    tipo: "Cola"
    descripcion: "Contiene cartas descartadas"
  arbol_permutaciones:
    tipo: "Árbol binario"
    descripcion: "Guarda las 6 permutaciones y el número de cartas a tomar del Mazo"

## Funcionalidades implementadas

- Crear partida nueva
- Barajar cartas de Caja a Mazo
- Repartir Mano inicial con 8 cartas
- Representación visual de Caja, Mazo, Mano y Pozo
- Generación de las 6 permutaciones para una tripleta de cartas
- Validación de sándwich para esa tripleta
- Descarte de cartas válidas al Pozo
- Recarga de cartas desde el Mazo según el tipo de sándwich
- Detección de victoria cuando el Mazo queda vacío
- Detección de derrota cuando no existe ningún sándwich posible en la Mano

## Funcionalidades planificadas

- Selección manual de 3 cartas en la Mano
- Guardar partida en archivo XML
- Cargar partida desde archivo XML



referencias:
  - "Java SE 21 Documentation"
  - "Java Swing Components"
  - "java.xml module"
  - "Fisher-Yates Shuffle"
  - "Listas, colas, pilas, listas circulares, árboles"

prompts_IA:
  - entrada: "Explica cómo validar un sándwich en The Sandwich Guy"
    salida: "Describe reglas y validación"
  - entrada: "¿Cómo modelar Caja, Mano, Mazo y Pozo?"
    salida: "Explica qué estructura usar y por qué"
  - entrada: "Genera las permutaciones de una tripleta"
    salida: "Muestra las 6 permutaciones y reglas del Mazo"

    LINKS: PENDIENTES

# Notas_finales: |
  Este es el archivo YAML consolidado del Proyecto Final.
  Incluye todos los elementos requeridos por el profesor:
  documentación, estructura, funcionalidades, avances, referencias
  y evidencias de uso de IA.



# Imagenes
<img width="707" height="599" alt="image" src="https://github.com/user-attachments/assets/d6dc2551-690f-4272-b1da-a587253ea7b8" />
<img width="698" height="598" alt="image" src="https://github.com/user-attachments/assets/58c3965a-29ca-45aa-ab69-5d8036f6b029" />
<img width="697" height="600" alt="image" src="https://github.com/user-attachments/assets/0d0ca127-b61d-4ef0-8682-0e0240ac0293" />
<img width="896" height="611" alt="image" src="https://github.com/user-attachments/assets/ea974cea-792a-4b46-9a22-e8682af682f6" />
<img width="893" height="596" alt="image" src="https://github.com/user-attachments/assets/9e63a51b-ed2d-45df-b1e7-1a3bf5fb6dcb" />
<img width="911" height="605" alt="image" src="https://github.com/user-attachments/assets/cbbe87c8-84c5-4ad7-af16-0b1c24e24f2b" />
<img width="729" height="700" alt="image" src="https://github.com/user-attachments/assets/15ff408f-17b1-4563-a008-f46234f58973" />



