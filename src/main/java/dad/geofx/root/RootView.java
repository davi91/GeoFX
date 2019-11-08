package dad.geofx.root;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Nuestr vista principal
 * @author David Fernández Nieves
 *
 */
public class RootView extends BorderPane {
	
	// Tab
	private HBox ipBox;
	private TabPane tabPanel;
	private Tab tab_location;
	private Tab tab_connection;
	private Tab tab_security;
	
	// IP 
	private ImageView conImg;
	private TextField ipTxt;
	private Button checkBt;
	
	public RootView() {
		
		// Primero cargamos todo lo relativo a la IP
		conImg = new ImageView(getClass().getResource("/images/conIcon.png").toString());
		conImg.setFitWidth(24.f);
		conImg.setFitHeight(24.f);
		
		ipTxt = new TextField();
		ipTxt.setEditable(false);
		ipTxt.setPromptText("IP: xxx.xxx.xxx.xxx");
		
		checkBt = new Button("Consultar");
		checkBt.setDefaultButton(true);
		
		ipBox = new HBox(10, conImg, ipTxt, checkBt);
		ipBox.setFillHeight(true);
		ipBox.setAlignment(Pos.CENTER);
		
		// Ahora el TabPane
		tabPanel = new TabPane();
		tabPanel.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		tabPanel.setSide(Side.BOTTOM);
		
		tab_location = new Tab("Location");
		tab_connection = new Tab("Connection");
		tab_security = new Tab("Security");
		
		tabPanel.getTabs().addAll(tab_location, tab_connection, tab_security);
		
		setTop(ipBox);
		setBottom(tabPanel);
		setPadding(new Insets(5));
	}
}
