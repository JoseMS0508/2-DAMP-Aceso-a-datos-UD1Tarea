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

## Modelo del Proyecto: Gestión de Biblioteca

El modelo del proyecto está compuesto por tres clases principales que permiten gestionar una biblioteca y sus préstamos. A continuación, se describe cada una de estas clases:

1. Clase: GestorBiblioteca
Esta clase es la responsable de administrar los libros y sus préstamos. Sus principales características son:

 - Atributos:

libros: Una lista de objetos Libro que representan los libros en la biblioteca.

 - Métodos:

cargaLibros(): Lee el archivo "Libros.txt" y carga los libros en la lista libros.

getLibros() y setLibros(): Métodos getter y setter para la lista de libros.

añadirLibro(Libro libroAAñadir): Añade un libro al archivo "Libros.txt".

actualizaLibros(ArrayList<Libro> listadoLibrosActualizado): Actualiza el archivo "Libros.txt" con el listado de libros proporcionado.

añadirPrestamo(Libro libroSeleccionado, Prestamo prestamoAAñadir): Añade un préstamo al historial de préstamos de un libro en el archivo "Historial prestamos.txt".

guardarHistorialPrestamos(): Guarda el historial completo de préstamos en el archivo "Historial prestamos.txt".

pasaStringALocalDate(String fechaStringEnviada): Convierte una cadena en un objeto LocalDate.

cargaHistorialPrestamosDeUnLibro(Libro libroMostrarHistorial): Devuelve el historial de préstamos de un libro específico desde el archivo "Historial prestamos.txt".


2. Clase: Libro
Representa un libro dentro de la biblioteca con sus detalles y préstamos asociados.

 - Atributos:

titulo: El título del libro.

autor: El autor del libro.

isbn: El número ISBN del libro.

historialPrestamos: Una lista de objetos Prestamo que representa el historial de préstamos de ese libro.

estaPrestado: Un booleano que indica si el libro está prestado o no.

 - Métodos:

Getters y setters para cada atributo.

añadirPrestamo(Prestamo prestamo): Añade un préstamo al historial de préstamos del libro.

toString(): Devuelve una representación en cadena del libro, incluido su historial de préstamos.


3. Clase: Prestamo
Representa un préstamo de un libro a un usuario durante un período de tiempo específico.

 - Atributos:

fechasDelPrestamo: Un array que contiene la fecha de inicio y de fin del préstamo.

usuario: El usuario que tomó prestado el libro.

fechasFormatoStringDelPrestamo: Representación en cadena de las fechas del préstamo.

 - Métodos:

Getters y setters para cada atributo.

toString(): Devuelve una representación en cadena del préstamo.
