package controladores;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.GestorBiblioteca;
import modelo.Libro;

public class Vista3Controlador {

	private Vista1Controlador controladorVista1;
	private Stage stage;
	@FXML
	private Button btnVista3Añadir;

	@FXML
	private Button btnVista3Volver;

	@FXML
	private TextField txtFieldVista3Autor;

	@FXML
	private TextField txtFieldVista3Isbn;

	@FXML
	private TextField txtFieldVista3Titulo;

	/**
	 * Este codigo ejecuta lo que se aplica cuando se hace click en el boton añadir.
	 * 
	 * @param event
	 */
	@FXML
	void añadeLibroABiblioteca(ActionEvent event) {
		Libro libroAAñadir = new Libro(txtFieldVista3Titulo.getText(), txtFieldVista3Autor.getText(),
				Integer.parseInt(txtFieldVista3Isbn.getText()));
		GestorBiblioteca gestorBiblioteca = new GestorBiblioteca();
		try {
			gestorBiblioteca.añadirLibro(libroAAñadir);
			txtFieldVista3Autor.setText("");
			txtFieldVista3Titulo.setText("");
			txtFieldVista3Isbn.setText("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Este es el codigo para volver a la vista 1 tras añadir un nuevo libro a la
	 * biblioteca.
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void vuelveAVista1DesdeVista3(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/Vista1.fxml"));
		Parent root = loader.load();
		Vista1Controlador controladorVista1 = loader.getController();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		controladorVista1.initDesdeVista3(stage);
		stage.show();
		this.stage.close();
	}

	public void init(Stage stage, Vista1Controlador vista1Controlador) {
		this.controladorVista1 = vista1Controlador;
		this.stage = stage;

	}
}