# 2-DAMP-Aceso-a-datos-UD1Tarea

## Sistema de Gestión de Biblioteca
Este sistema permite a los usuarios interactuar con una interfaz gráfica para buscar libros, agregar nuevos títulos y registrar préstamos y devoluciones.

## Estructura del proyecto
El proyecto está estructurado en varios paquetes y clases:

## Paquetes:
 - application: Contiene la clase principal y la lógica de inicio del programa.
- controladores: Contiene los controladores asociados con cada vista.
- imagenes: Almacena las imágenes utilizadas en las vistas.
- modelo: Contiene las clases que representan los modelos de datos.
- vistas: Contiene la representación visual de las interfaces.

## Clases del modelo
 - GestorBiblioteca:

Es responsable de administrar la colección de libros.
Carga y guarda datos de libros y préstamos desde y hacia archivos de texto.
Implementa funciones de búsqueda de libros por título, autor o ISBN.
Permite agregar nuevos libros y actualizar la lista de libros.
Registra préstamos y devuelve información.

 - Libro:

Representa un libro con propiedades como título, autor, ISBN, y un registro de préstamos asociados.
Puede estar en estado prestado o disponible.
Contiene una lista de préstamos asociados con ese libro.

 - Prestamo:

Representa un préstamo con fechas de inicio y fin, y el nombre del usuario que tiene el préstamo.
Ofrece una representación de string del período del préstamo.
Controladores y Vistas
El proyecto tiene cuatro controladores correspondientes a cada una de las vistas. Las imágenes de las vistas se han proporcionado con el nombre que coincide con cada vista. Estas vistas proporcionan la interacción del usuario con el sistema para realizar diversas tareas, como buscar libros, agregar nuevos títulos, registrar préstamos y devoluciones, entre otros.
