package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class GestorBiblioteca {
	
	private ArrayList<Libro> libros = new ArrayList<>();
	
	public GestorBiblioteca() {
		this.libros = cargaLibros();
	}
	
	public ArrayList<Libro> cargaLibros() {
		ArrayList<Libro> librosEnArchivo = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader("Libros.txt"))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] valores = linea.split("/");
				if (valores.length == 4) {
					Libro libroAAñadir = new Libro(valores[0].trim(), valores[1].trim(), Integer.parseInt(valores[2].trim()), Boolean.parseBoolean(valores[3].trim()));
					librosEnArchivo.add(libroAAñadir);
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return librosEnArchivo;
	}
	
	public ArrayList<Libro> getLibros() {
		return libros;
	}

	public void setLibros(ArrayList<Libro> libros) {
		this.libros = libros;
	}

	public void añadirLibro(Libro libroAAñadir) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("Libros.txt",true))) {
			bw.write(libroAAñadir.getTitulo() + "/" + libroAAñadir.getAutor() + "/" + libroAAñadir.getIsbn() + "/" + "false\n");
			bw.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Este metodo guarda los libros segun estan para poder registrar que esta prestado y asi
	//que el boton solicitar prestamo no este activo, guardandolo en el archivo libros, registrando el 
	//atributo "prestado" a true en el fichero
	public void actualizaLibros(ArrayList<Libro> listadoLibrosActualizado) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\\\Users\\\\josem\\\\OneDrive\\\\Documentos" +
				 "\\\\FP DAM UTAD\\\\2º\\\\Acceso a datos\\\\Libros.txt"))) {
		    for (Libro libro : listadoLibrosActualizado) {
		        bw.write(libro.getTitulo() + "/" + libro.getAutor() + "/" + libro.getIsbn() + "/" + libro.estaPrestado() + "\n");
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	//Con este metodo guardamos en el archivo prestamos el nuevo prestamo que se haya hecho
	public void añadirPrestamo(Libro libroSeleccionado, Prestamo prestamoAAñadir) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("Historial prestamos.txt", true))) {
			for (Prestamo prestamo : libroSeleccionado.getHistorialPrestamos()) {
				LocalDate[] fechas = prestamo.getFechasDelPrestamo();
				String linea = libroSeleccionado.getTitulo() + "/" + libroSeleccionado.getAutor() + "/" + libroSeleccionado.getIsbn() + "/"
						+ prestamo.getUsuario() + "/" + fechas[0] + ":" + fechas[1];
				bw.write(linea);
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void guardarHistorialPrestamos() {
	    try (BufferedWriter bw = new BufferedWriter(new FileWriter("Historial prestamos.txt", true))) {
	        for (Libro libro : libros) {
	            for (Prestamo prestamo : libro.getHistorialPrestamos()) {
	                LocalDate[] fechas = prestamo.getFechasDelPrestamo();
	                String linea = libro.getTitulo() + "/" + libro.getAutor() + "/" + libro.getIsbn() + "/" + prestamo.getUsuario() +
	                               fechas[0] + ":" + fechas[1];
	                bw.write(linea);
	                bw.newLine();
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public LocalDate pasaStringALocalDate(String fechaStringEnviada) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaEnLocalDate = LocalDate.parse(fechaStringEnviada, formatter);
        return fechaEnLocalDate;
	}
	
	public ArrayList<Prestamo> cargaHistorialPrestamosDeUnLibro(Libro libroMostrarHistorial) {
		ArrayList<Prestamo> historialPrestamos = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader("Historial prestamos.txt"))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] valores = linea.split("/");
				if (valores.length == 5 && libroMostrarHistorial.getTitulo().equals(valores[0]) && libroMostrarHistorial.getAutor().equals(valores[1])) {
					LocalDate[] fechasPrestamo = new LocalDate[2];
					String[] fechas = valores[4].split(":");
					String fechaInicio = fechas[0];
					String fechaFin = fechas[1];
					fechasPrestamo[0] = pasaStringALocalDate(fechaInicio);
					fechasPrestamo[1] = pasaStringALocalDate(fechaFin);
					Prestamo prestamo = new Prestamo(fechasPrestamo[0], fechasPrestamo[1], valores[3]);
					historialPrestamos.add(prestamo);
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return historialPrestamos;
	}

}
