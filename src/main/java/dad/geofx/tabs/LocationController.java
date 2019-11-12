package dad.geofx.tabs;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.geofx.utils.LocationObject;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.converter.NumberStringConverter;

public class LocationController implements Initializable {

	// View : FXML
	//--------------------------------------------------------------
	
	@FXML
	private GridPane view;
	
	@FXML 
	private ImageView flagImg;
	
	@FXML
	private Label latitudLbl, longitudLbl;
	
	@FXML
	private Label locationLbl;
	
	@FXML
	private Label conLocationLbl, zipLbl;
	
	@FXML
	private Label langLbl, timeLbl;
	
	@FXML
	private Label callLbl, currencyLbl;
	
	//--------------------------------------------------------------

	// Model
	private LocationModel model = new LocationModel();
	
	public LocationController() throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GeoUILocation.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// Realizamos los bindings correspondientes
		
		Bindings.bindBidirectional(latitudLbl.textProperty(), model.latitudProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(longitudLbl.textProperty(), model.longitudProperty(), new NumberStringConverter());
		
	//	flagImg.imageProperty().bind(model.flagIconProperty());
		
		locationLbl.textProperty().bind( Bindings.concat(model.paisProperty(), " (", model.pais_codProperty(), ")"));
		conLocationLbl.textProperty().bind( Bindings.concat(  model.ciudadProperty(), " (", model.estadoProperty(), ")"));
		zipLbl.textProperty().bind(model.zipProperty());
		callLbl.textProperty().bind(Bindings.concat("+", model.callProperty()));
		
	}

	/**
	 * Realizamos el "Setup" de los datos correspondientes a la localización
	 * @param info
	 */
	public void setupLocationData(LocationObject info) {
		
		model.setLatitud(info.getLatitude());
		model.setLongitud(info.getLongitude());
		
		model.setPais(info.getCountry_name());
		model.setPais_cod(info.getCountry_code());
		
		model.setCiudad(info.getCity());
		model.setEstado(info.getRegion_name());
		
		model.setCall(info.getLocation().getCalling_code());
		
		model.setZip(info.getZip());
		
	}
	
	
	public GridPane getRootView() {
		return view;
	}
}
