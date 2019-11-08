package dad.geofx.main;

import dad.geofx.root.RootController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GeoApp extends Application {

	private RootController rootController;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
	
		rootController = new RootController();
		Scene scene = new Scene(rootController.getRootView(), 512, 256);
		
		primaryStage.setTitle("IP Info");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}

}
