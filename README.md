# Simulación de una taquilla de cine

Proyecto en Java que simula el funcionamiento de una taquilla de cine: venta de entradas, gestión de asientos y colas.

## Descripción

Este proyecto implementa una simulación sencilla de una taquilla de cine en Java. Permite modelar la venta de entradas para diferentes funciones, gestionar la disponibilidad de asientos y simular clientes que forman cola y compran entradas.

## Características

- Simulación de llegada de clientes y formación de colas.
- Gestión de asientos por sala/función.
- Venta de entradas (bloqueo de asientos vendidos).
- Registro sencillo de operaciones para probar distintos escenarios.

## Requisitos

- Java Development Kit (JDK) 8 o superior.
- Un IDE compatible con Java (Eclipse, IntelliJ IDEA, VS Code) o línea de comandos.

## Estructura sugerida del proyecto

La estructura puede variar; un ejemplo típico:

- src/        -> código fuente Java
- bin/        -> archivos compilados (opcional)
- README.md   -> este archivo

Ajusta las rutas según la estructura real del repositorio.

## Compilación y ejecución

Compilación desde línea de comandos (ejemplo genérico):

1. Compilar todos los .java dentro de src:

   javac -d bin $(find src -name "*.java")

2. Ejecutar la clase principal (reemplaza `paquete.Main` por la clase que contenga `public static void main`):

   java -cp bin paquete.Main

Si el proyecto no usa paquetes o tiene otra estructura, puedes compilar y ejecutar archivos individuales:

   javac src/NombreArchivo.java
   java -cp src NombreArchivo

## Uso

- Configura las salas, la capacidad y la secuencia de llegada de clientes según las clases y métodos disponibles en el código.
- Ejecuta la clase principal para iniciar la simulación. Observa la salida por consola o los logs para ver el comportamiento.

## Ejemplos

Incluye en el repositorio ejemplos de entradas o clases de prueba (tests) que muestren cómo crear funciones, clientes y ejecutar la simulación.

## Tests

Si tienes pruebas unitarias, ejecuta tu framework de pruebas (por ejemplo, JUnit) desde tu IDE o con Maven/Gradle si el proyecto está configurado.

## Contribuciones

Las contribuciones son bienvenidas. Abre un issue o un pull request con cambios claros y una descripción de lo que haces.

## Autor

Proyecto creado por [SrAlvarado](https://github.com/SrAlvarado).

## Licencia

Licencia MIT — cambia o especifica la licencia que prefieras para este proyecto.

---
Notas:
- Si quieres, puedo adaptar el README para incluir instrucciones exactas de compilación y ejecución si me indicas la clase principal o la estructura actual del proyecto.
