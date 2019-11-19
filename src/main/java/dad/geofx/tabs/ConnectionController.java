package dad.geofx.tabs;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * Controlador de la pestaña "Conexión". Debido a que la API Rest en 
 * versión gratuita no nos ofrece información sobre esta categoría,
 * todos los valores serán valores desconocidos.
 * 
 * @author David Fernández Nieves
 *
 */
public class ConnectionController implements Initializable {

	// View : FXML
	//-------------------------------------------------------
	
	@FXML 
	private GridPane view;
	
	@FXML
	private Label ipTxt;
	
	@FXML
	private Label ispTxt;
	
	@FXML
	private Label typeTxt, asnTxt;
	
	@FXML
	private Label hostTxt;
	
	//-------------------------------------------------------
	
	// Model
	//-------------------------------------------------------
	
	private StringProperty ip = new SimpleStringProperty("N/A");
	private StringProperty isp = new SimpleStringProperty("N/A");
	private StringProperty type = new SimpleStringProperty("N/A");
	private StringProperty asn = new SimpleStringProperty("N/A");
	private StringProperty host = new SimpleStringProperty("N/A");
	
	//-------------------------------------------------------
	
	public ConnectionController() throws IOException {
	
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GeoUIConnection.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		// Bindings
		ipTxt.textProperty().bind(ip);
		ispTxt.textProperty().bind(isp);
		typeTxt.textProperty().bind(type);
		asnTxt.textProperty().bind(asn);
		hostTxt.textProperty().bind(host);
	}
	
	public void setupConnection(String ipType) {
		
		// Sólo podemos recibir el tipo de la ip
		ip.set(ipType);
	}
	
	public GridPane getRootView() {
		return view;
	}

}
