package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Libro {
	
	private String titulo;
	private String autor;
	private int isbn;
	private ArrayList<Prestamo> historialPrestamos;
	private boolean estaPrestado;
	
	public Libro(String titulo, String autor, int isbn) {
		this.titulo = titulo;
		this.autor = autor;
		this.isbn = isbn;
		this.historialPrestamos = new ArrayList<>();
		this.estaPrestado = false;
	}
	
	public Libro(String titulo, String autor, int isbn, boolean estaPrestado) {
		this.titulo = titulo;
		this.autor = autor;
		this.isbn = isbn;
		this.historialPrestamos = new ArrayList<>();
		this.estaPrestado = estaPrestado;
	}

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public List<Prestamo> getHistorialPrestamos() {
		return historialPrestamos;
	}
	public void setHistorialPrestamos(ArrayList<Prestamo> historialPrestamos) {
		this.historialPrestamos = historialPrestamos;
	}
	public boolean estaPrestado() {
		return estaPrestado;
	}
	public void setEstaPrestado(boolean estaPrestado) {
		this.estaPrestado = estaPrestado;
	}
	
	public void añadirPrestamo(Prestamo prestamo) {
		this.historialPrestamos.add(prestamo);
	}

	@Override
	public String toString() {
		return "Libro [titulo=" + titulo + ", autor=" + autor + ", isbn=" + isbn + ", historialPrestamos="
				+ imprimeHistorialPrestamos() + ", estaPrestado=" + estaPrestado + "]";
	}

	private String imprimeHistorialPrestamos() {
		for (Prestamo prestamo: historialPrestamos) {
			prestamo.toString();
		}
		return null;
	}
	
}
