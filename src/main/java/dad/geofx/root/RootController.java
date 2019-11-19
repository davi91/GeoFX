package dad.geofx.root;

import java.io.IOException;

import dad.geofx.main.GeoApp;
import dad.geofx.tabs.ConnectionController;
import dad.geofx.tabs.LocationController;
import dad.geofx.tabs.SecurityController;
import dad.geofx.utils.LocationObject;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Se encargará de gestionar todas las pestañas y actúa como controlador principal
 * de todas ellas.
 * 
 * Sólo tendrá funcionalidad la pestaña la localización puesto que la versión gratuita
 * de al API Rest de "ipapi" sólo cubre esa categoría.
 * 
 * @author David Fernández Nieves
 *
 */
public class RootController {	

	// Tarea en segundo plano
	private Task<LocationObject> loadTask;
	private Task<String> ipTask;
	
	// Referencia a nuestra aplicación
	private GeoApp app;
	
	// Nuestras pestañas
	LocationController location_controller;
	ConnectionController connection_controller;
	SecurityController security_controller;
	
	// View
	private RootView view = new RootView();
	
	// Model
	private StringProperty ip = new SimpleStringProperty();
	
	private ObjectProperty<Node> tab_location = new SimpleObjectProperty<>();
	private ObjectProperty<Node> tab_connection = new SimpleObjectProperty<>();
	private ObjectProperty<Node> tab_security = new SimpleObjectProperty<>();
	
	public RootController(GeoApp app) {
		
		this.app = app;
		
		// Nada más inicializarse necesitamos llamar a la IP
		loadIPTask();
		
		// Cada vez que le demos a que revise la IP
		view.getCheckBt().setOnAction( evt -> onCheckAction());

		view.getIpTxt().textProperty().bind(ip);
		view.getTab_location().contentProperty().bind(tab_location);
		view.getTab_connection().contentProperty().bind(tab_connection);
		view.getTab_security().contentProperty().bind(tab_security);
		
		// Inicializamos los tabs
		try {
			location_controller = new LocationController();
			connection_controller = new ConnectionController();
			security_controller = new SecurityController();
			
			tab_location.set(location_controller.getRootView());
			tab_connection.set(connection_controller.getRootView());
			tab_security.set(security_controller.getRootView());
			
		} catch (IOException e) {
			sendInitializeError();
		}
	}
	
	private void loadIPTask() {
		
		// Pedimos la IP como tarea en segundo plano
		ipTask = new Task<String>() {

			@Override
			protected String call() throws Exception {
				return app.getUnirestObject().getUserIP();
			}
			
		};
		
		view.getCheckBt().disableProperty().bind(ipTask.runningProperty());
		
		ipTask.setOnSucceeded(e -> {
			ip.set((String)e.getSource().getValue());
			sendLocationInfoRequest();
		});
		
		ipTask.setOnFailed( e-> {
			sendInitializeError();
		});
		
		new Thread(ipTask).start();
		
	}
	
	private void onCheckAction() {
		loadIPTask();
	}
	
	private void sendLocationInfoRequest() {
		
		final String myIP = ip.get();
		
		if( myIP == null )
			return;
		
		loadTask = new Task<LocationObject>() {

			@Override
			protected LocationObject call() throws Exception {	
				return app.getUnirestObject().getLocationInfo(myIP);
			}
			
		};
		
		view.getCheckBt().disableProperty().bind(loadTask.runningProperty());
		
		loadTask.setOnSucceeded( e -> {
			
			location_controller.setupLocationData((LocationObject)e.getSource().getValue());
			connection_controller.setupConnection(((LocationObject)e.getSource().getValue()).getType()); // Lo único que le podemos pasar es el tipo de la IP	
			
		});
		
		loadTask.setOnFailed( e-> {
			sendInitializeError();
		});

		new Thread(loadTask).start();
	}

	private void sendInitializeError() {
		
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("Error al inicializar un componente");
		alert.showAndWait();
		Platform.exit();
	}
	
	public RootView getRootView() {
		return view;
	}
}
