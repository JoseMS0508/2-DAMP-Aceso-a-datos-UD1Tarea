package controladores;

import java.io.IOException;
import javafx.scene.control.DatePicker;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import modelo.Libro;
import modelo.Prestamo;
import modelo.GestorBiblioteca;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Vista2Controlador {

	@FXML
	private TextField txtFieldVista2Autor;
	@FXML
	private TextField txtFieldVista2Isbn;
	@FXML
	private TextField txtFieldVista2Titulo;
	@FXML
	private Button btnBuscar;
	@FXML
	private TableView<Libro> tablaLibrosVista2;
	@FXML
	private TableColumn<Libro, String> colAutor;
	@FXML
	private TableColumn<Libro, Integer> colIsbn;
	@FXML
	private TableColumn<Libro, String> colTitulo;
	@FXML
	private TextField txtFieldUsuario;
	@FXML
	private DatePicker dpFechaFin;
	@FXML
	private DatePicker dpFechaInicio;
	@FXML
	private Button btnVista3SolicitarPrestamo;
	@FXML
	private Button bntVista3DevolverPrestamo;
	@FXML
	private Button btnVista3HistorialPrestamos;
	@FXML
	private Button btnVolverVista2;

	private Stage stage;
	private ObservableList<Libro> libros;

	// Este codigo lo meto para iniciarlizar el observable list, ya que me estaba
	// dando error si no lo inicializaba.
	public Vista2Controlador() {
		libros = FXCollections.observableArrayList();
	}

	/**
	 * Busca el libro o por ISBN, o introduciendo título, autor e isbn. Devuelve el
	 * listado de los libros que coincidan con los parámetros de búsqueda
	 * introducidos.
	 * 
	 * @param event
	 */
	@FXML
	void buscaLibro(ActionEvent event) {
		// Si no hay ningun texto en los textFields para buscar, salta una alerta.
		if (txtFieldVista2Titulo.getText().isBlank() && txtFieldVista2Autor.getText().isBlank()
				&& txtFieldVista2Isbn.getText().isBlank()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Aviso");
			alert.setHeaderText(null);
			alert.setContentText("Indique el <TÍTULO, AUTOR e ISBN> o <ISBN> para poder buscar.");
			alert.showAndWait();
			// Si hay contenido en los tres campos, busca libros por coincidencias con todos
			// los parametros
		} else if (!(txtFieldVista2Titulo.getText().isBlank()) && !(txtFieldVista2Autor.getText().isBlank())
				&& !(txtFieldVista2Isbn.getText().isBlank())) {
			ObservableList<Libro> librosMostrarResultadoBusqueda = FXCollections.observableArrayList();
			for (Libro libro : libros) {
				if (libro.getTitulo().trim().toLowerCase().equals(txtFieldVista2Titulo.getText().trim().toLowerCase())
						&& libro.getAutor().trim().toLowerCase()
								.equals(txtFieldVista2Autor.getText().trim().toLowerCase())) {
					librosMostrarResultadoBusqueda.add(libro);
				}
			}
			tablaLibrosVista2.setItems(librosMostrarResultadoBusqueda);
			txtFieldVista2Titulo.setText("");
			txtFieldVista2Autor.setText("");
			txtFieldVista2Isbn.setText("");
			// Buscamos libros con el mismo isbn introducido
		} else if (txtFieldVista2Titulo.getText().isBlank() && txtFieldVista2Autor.getText().isBlank()
				&& !(txtFieldVista2Isbn.getText().isBlank())) {
			ObservableList<Libro> librosMostrarResultadoBusqueda = FXCollections.observableArrayList();
			for (Libro libro : libros) {
				if (String.valueOf(libro.getIsbn()).equals(txtFieldVista2Isbn.getText())) {
					librosMostrarResultadoBusqueda.add(libro);
				}
			}
			tablaLibrosVista2.setItems(librosMostrarResultadoBusqueda);
			txtFieldVista2Titulo.setText("");
			txtFieldVista2Autor.setText("");
			txtFieldVista2Isbn.setText("");
		}
	}

	/**
	 * Este metodo solicita un prestamo, y hace que el boton solicitar prestamo se
	 * desactive hasta que no se devuelva.
	 * 
	 * @param event
	 */
	@FXML
	void solicitaPrestamo(ActionEvent event) {
		// Si no hay un USUARIO al que prestar lo indicamos
		if (txtFieldUsuario.getText().isBlank()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Aviso");
			alert.setHeaderText(null);
			alert.setContentText("Indique el USUARIO que va a solicitar el préstamo.");
			alert.showAndWait();
			// Si no hay unas FECHAS seleccionadas para pedir prestamo mandamos mensaje de
			// aviso
		} else if (dpFechaInicio.getValue() == null || dpFechaFin.getValue() == null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Aviso");
			alert.setHeaderText(null);
			alert.setContentText("Introduzca la FECHA DE INICIO y la FECHA DE FIN del préstamo.");
			alert.showAndWait();
			// Si no hay LIBRO seleccionado no podemos hacer prestamo y mandamos mensaje
		} else if (tablaLibrosVista2.getSelectionModel().getSelectedItem() == null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Aviso");
			alert.setHeaderText(null);
			alert.setContentText("Selecciona un libro para poder solicitar el préstamo.");
			alert.showAndWait();
			// Si tenemos todos los campos rellenados entonces si llevamos a cabo el
			// registro del prestamo
		} else {
			Libro libroSeleccionado = tablaLibrosVista2.getSelectionModel().getSelectedItem();
			libroSeleccionado.setEstaPrestado(true);

			Prestamo nuevoPrestamo = new Prestamo(dpFechaInicio.getValue(), dpFechaFin.getValue(),
					txtFieldUsuario.getText());
			btnVista3SolicitarPrestamo.setDisable(true);
			bntVista3DevolverPrestamo.setDisable(false);
			libroSeleccionado.añadirPrestamo(nuevoPrestamo);
			mandaActualizarLibros();
			GestorBiblioteca bibliotecaConNuevoPrestamo = new GestorBiblioteca();
			// Guardamos en el txt de prestamos el nuevo prestamo con el metodo
			// añadirPrestamo
			bibliotecaConNuevoPrestamo.añadirPrestamo(libroSeleccionado, nuevoPrestamo);
			dpFechaInicio.setValue(null);
			dpFechaFin.setValue(null);
			txtFieldUsuario.setText("");
			tablaLibrosVista2.requestFocus();
		}
	}

	/**
	 * Este metodo sirve para que en el fichero de Libros quede que el estado del
	 * libro tiene el atributo prestado en true, y asi no se pueda solicitar otro
	 * prestamo hasta que se devuelva el actual
	 */
	public void mandaActualizarLibros() {
		ArrayList<Libro> librosActualizados = new ArrayList<>(libros);
		GestorBiblioteca bibliotecaActualizada = new GestorBiblioteca();
		bibliotecaActualizada.actualizaLibros(librosActualizados);
	}

	/**
	 * Establece a false el atributo "prestado" del libro sobre el que se solicite
	 * el prestamo. Y actualiza el fichero que guarda los libros cambiadole ese
	 * valor.
	 * 
	 * @param event
	 */
	@FXML
	void devuelvePrestamo(ActionEvent event) {
		if (tablaLibrosVista2.getSelectionModel().getSelectedItem() == null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Aviso");
			alert.setHeaderText(null);
			alert.setContentText("Selecciona un libro para poder registrar su devolución.");
			alert.showAndWait();
		} else {
			Libro libroSeleccionado = tablaLibrosVista2.getSelectionModel().getSelectedItem();
			libroSeleccionado.setEstaPrestado(false);
			btnVista3SolicitarPrestamo.setDisable(false);
			bntVista3DevolverPrestamo.setDisable(true);
			mandaActualizarLibros();
			tablaLibrosVista2.requestFocus();
		}
	}

	/**
	 * Muestra la pantalla de ver el historial de prestamos de un libro concreto
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void verHistorialPrestamos(ActionEvent event) throws IOException {
		// Controlamos que tengamos un libro seleccionado.
		if (tablaLibrosVista2.getSelectionModel().getSelectedItem() == null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Aviso");
			alert.setHeaderText(null);
			alert.setContentText("Selecciona un libro para poder ver su historial de préstamos.");
			alert.showAndWait();
			// Si tenemos el libro seleccionado cargamos la ventana nueva pasandole los
			// parametros necesarios.
		} else {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/Vista4.fxml"));
			Parent root = loader.load();
			Vista4Controlador controladorVista4 = loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			Libro libroSeleccionado = tablaLibrosVista2.getSelectionModel().getSelectedItem();
			controladorVista4.initDesdeVista2(stage, libroSeleccionado);
			stage.show();
			this.stage.close();
		}
	}

	/*
	 * Establece el codigo para que se cargue el evento de darle a volver hacia la
	 * ventana1 desde la ventana 2
	 */
	@FXML
	void vuelveAVista1DesdeVista2(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/Vista1.fxml"));
		Parent root = loader.load();
		Vista1Controlador controladorVista1 = loader.getController();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		controladorVista1.init(stage);
		stage.show();
		this.stage.close();
	}

	public void init(Stage stage, Vista1Controlador vista1Controlador) {
		// this.controladorVista1 = vista1Controlador;
		this.stage = stage;
	}

	/*
	 * Metemos este metodo para que cuando se cargue la ventana al iniciar, podamos
	 * crear bien la tabla con los valores que muestra.
	 */
	@FXML
	public void initialize() {
		this.colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
		this.colIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
		this.colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));

		ArrayList<Libro> listaLibros = new ArrayList<>();
		GestorBiblioteca datosInicio = new GestorBiblioteca();
		listaLibros = datosInicio.getLibros();
		libros.addAll(listaLibros);
		tablaLibrosVista2.setItems(libros);

		tablaLibrosVista2.getSelectionModel().selectedItemProperty()
				.addListener((observable, valorAntesSeleccionado, nuevoValorSeleccionado) -> {
					if (nuevoValorSeleccionado == null || nuevoValorSeleccionado.estaPrestado()) {
						btnVista3SolicitarPrestamo.setDisable(true);
						bntVista3DevolverPrestamo.setDisable(false);
					} else {
						bntVista3DevolverPrestamo.setDisable(true);
						btnVista3SolicitarPrestamo.setDisable(false);
					}
				});
	}

	public void initDesdeVista4(Stage stage) {
		this.stage = stage;
	}
}