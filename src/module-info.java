/**
 * 
 */
/**
 * @author josem
 *
 */
module Programa {
	requires javafx.controls;
	requires javafx.base;
	requires javafx.fxml;
	
	opens controladores to javafx.fxml;
	opens application to javafx.graphics, javafx.fxml;
	opens modelo to javafx.base;
}