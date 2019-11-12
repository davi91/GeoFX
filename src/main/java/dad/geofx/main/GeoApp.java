package dad.geofx.main;

import com.mashape.unirest.http.Unirest;

import dad.geofx.root.RootController;
import dad.geofx.utils.UnirestObject;
import dad.geofx.utils.UnirestObjectMapper;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class GeoApp extends Application {

	// Ajustamos las claves de las API Rest
	//-------------------------------------------------
	public final static String IPIFY_KEY = "at_DqPRCZbMtAi6dBwZ7XUi5pqoW3pMU";
	public final static String IPAPI_KEY = "185a3f776b1de808b3d2e2cd61191a9e";
	//-------------------------------------------------
	
	private RootController rootController;
	
	private UnirestObject unirestObject;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
	
		// Ajustamos el Unirest
		unirestObject = new UnirestObject();
		Unirest.setObjectMapper(new UnirestObjectMapper());
		
		rootController = new RootController(this);
		Scene scene = new Scene(rootController.getRootView(), 512, 384);
		
		primaryStage.setTitle("IP Info");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Objeto encargado de gestionar las conexiones con las API Rest
	 * @return La referencia al objeto
	 */
	public UnirestObject getUnirestObject() {
		return this.unirestObject;
	}
	
	/**
	 * Lanzamiento de error en caso de fallo con la Unirest
	 * @param msg El contenido del mensaje
	 */
	public static void sendConnectionError(String msg) {
		
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Unirest");
		alert.setTitle("Error de conexión");
		alert.setContentText(msg);
		alert.showAndWait();
	}

}
