package modelo;

import java.time.LocalDate;
import java.util.Arrays;

public class Prestamo {

	private LocalDate[] fechasDelPrestamo;
	private String usuario;
	private String fechasFormatoStringDelPrestamo;
	
	public Prestamo() {
		fechasDelPrestamo = new LocalDate[2];
		this.usuario = " ";
		this.fechasFormatoStringDelPrestamo = " ";
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getFechasFormatoStringDelPrestamo() {
		setFechasFormatoStringDelPrestamo();
		return fechasFormatoStringDelPrestamo;
	}

	public void setFechasFormatoStringDelPrestamo() {
		this.fechasFormatoStringDelPrestamo = fechasDelPrestamo[0].toString() + " hasta " + fechasDelPrestamo[1].toString();
	}

	public Prestamo(LocalDate fechaInicio, LocalDate fechaFin, String usuario) {
		fechasDelPrestamo = new LocalDate[2];
		fechasDelPrestamo[0] = fechaInicio;
		fechasDelPrestamo[1] = fechaFin;
		this.usuario = usuario;
		this.fechasFormatoStringDelPrestamo = fechaInicio.toString() + " hasta " + fechaFin.toString();
		
	}

	public LocalDate[] getFechasDelPrestamo() {
		return fechasDelPrestamo;
	}

	public void setFechasDelPrestamo(LocalDate[] fechasDelPrestamo) {
		this.fechasDelPrestamo = fechasDelPrestamo;
	}
	
	@Override
	public String toString() {
		return "Prestamo [fechasDelPrestamo=" + Arrays.toString(fechasDelPrestamo) + ", usuario=" + usuario + "]";
	}

}
