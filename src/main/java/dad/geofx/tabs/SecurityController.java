package dad.geofx.tabs;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * Controlador de la pestaña "Seguridad". Como la API Rest 
 * en versión gratuita no nos aporta información sobre esta 
 * categoría todos los valores se pondrán por defecto en desconocido.
 * 
 * 
 * @author David Fernández Nieves
 *
 */
public class SecurityController implements Initializable {

	// View : FXML
	//----------------------------------------------
	
	@FXML
	private GridPane view;
	
	@FXML
	private Label secTxt;
	
	@FXML
	private CheckBox proxyBt, torBt, crawlerBt;
	
	@FXML
	private Label threatTxt;
	
	//----------------------------------------------

	// Model
	//----------------------------------------------

	private StringProperty security = new SimpleStringProperty("No se pudo determinar la seguirdad de la IP");
	private BooleanProperty proxy = new SimpleBooleanProperty(false);
	private BooleanProperty tor = new SimpleBooleanProperty(false);
	private BooleanProperty crawler = new SimpleBooleanProperty(false);
	private StringProperty threat = new SimpleStringProperty("N/A");
	//----------------------------------------------

	public SecurityController() throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GeoUISecurity.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// Bindings
		secTxt.textProperty().bind(security);
		proxyBt.selectedProperty().bind(proxy);
		torBt.selectedProperty().bind(tor);
		crawlerBt.selectedProperty().bind(crawler);
		threatTxt.textProperty().bind(threat);
	}
	
	public GridPane getRootView() {
		return view;
	}

}
