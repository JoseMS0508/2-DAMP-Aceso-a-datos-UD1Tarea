package controladores;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Vista1Controlador {

	private Stage stage;
    @FXML
    private Button btnAñadirLibro;

    @FXML
    private Button btnBuscarLibro;

    @FXML
    private Button btnSalirPrograma;

    /*
     * Meotodo del evento que controla que se abra la vista 2 desde la vista 1.
     */
    @FXML
    void abrirVista2DesdeVista1(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/Vista2.fxml"));
    	Parent root = loader.load();
    	Vista2Controlador controladorVista2 = loader.getController();
    	Scene scene = new Scene(root);
    	Stage stage = new Stage();
    	stage.setScene(scene);
    	controladorVista2.init(stage, this);
    	stage.show();
    	this.stage.close();
    }

	public void setStage(Stage primaryStage) {
		stage = primaryStage;
	}

	public void init(Stage stage2) {
		this.stage = stage2;
	}
	
	//Abre la vista 3 desde la vista 1
    @FXML
    void abrirVista3DesdeVista1(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/Vista3.fxml"));
    	Parent root = loader.load();
    	Vista3Controlador controladorVista3 = loader.getController();
    	Scene scene = new Scene(root);
    	Stage stage = new Stage();
    	stage.setScene(scene);
    	controladorVista3.init(stage, this);
    	stage.show();
    	this.stage.close();
    }

    //Codigo para cerrar el programa tras hacer click en el boton salir
    @FXML
    void cerrarPrograma(ActionEvent event) {
    	Platform.exit();
    }

	public void initDesdeVista3(Stage stage2) {
		this.stage = stage2;
		
	}

	public void initDesdeVista4(Stage stage) {
		this.stage = stage;
	}

}
