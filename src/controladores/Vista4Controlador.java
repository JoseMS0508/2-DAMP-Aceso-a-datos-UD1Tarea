package controladores;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import modelo.GestorBiblioteca;
import modelo.Libro;
import modelo.Prestamo;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Vista4Controlador {

	private Vista1Controlador controladorVista1;
	private Stage stage;
	
    @FXML
    private Button btnVolverVista4;
    @FXML
    private TableColumn<Prestamo, String> colFechasPrestamo;
    
    @FXML
    private TableColumn<Prestamo, String> colUsuario;

    @FXML
    private TableView<Prestamo> tblHistorialPrestamos;

    @FXML
    private TextField txtFieldVista4Autor;

    @FXML
    private TextField txtFieldVista4Isbn;

    @FXML
    private TextField txtFieldVista4Titulo;
    
    private ObservableList<Prestamo> prestamos;
    private ArrayList<Prestamo> historialPrestamos;

    //Pongo este codigo para que el observable list de la tabla se inicialice
    public Vista4Controlador() {
    	prestamos = FXCollections.observableArrayList();
    }
    
    //Aqui ponemos los atributos a los que hace referencia la columna de la tabla, en la que se van a mostrar objetos "Prestamo"
    @FXML
    public void initialize() {
        this.colUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        this.colFechasPrestamo.setCellValueFactory(new PropertyValueFactory<>("fechasFormatoStringDelPrestamo"));
    }
    
    //Codigo para volver a la vista 2 desde la vista 4
    @FXML
    void vuelveAVista2DesdeVista4(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/Vista2.fxml"));
    	Parent root = loader.load();
    	Vista2Controlador controladorVista2 = loader.getController();
    	Scene scene = new Scene(root);
    	Stage stage = new Stage();
    	stage.setScene(scene);
    	controladorVista2.initDesdeVista4(stage);
    	stage.show();
    	this.stage.close();
    }

	public void init(Stage stage, Vista1Controlador vista1Controlador) {
		this.controladorVista1 = vista1Controlador;
		this.stage = stage;
	}
	
	//Codigo para cuando se carga la pantalla desde la vista 2, inicializa la tabla
	//del historial de prestamos 
	public void initDesdeVista2(Stage stage2, Libro libroSeleccionado) {
		this.stage = stage2;
		this.txtFieldVista4Titulo.setText(libroSeleccionado.getTitulo());
		this.txtFieldVista4Autor.setText(libroSeleccionado.getAutor());
		this.txtFieldVista4Isbn.setText(Integer.toString(libroSeleccionado.getIsbn()));
		GestorBiblioteca gestor = new GestorBiblioteca();
		this.historialPrestamos = gestor.cargaHistorialPrestamosDeUnLibro(libroSeleccionado);
        prestamos.addAll(historialPrestamos);
        tblHistorialPrestamos.setItems(prestamos);
	}

}