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
		tabPanel.setMaxWidth(Double.MAX_VALUE);
		tabPanel.setMaxHeight(Double.MAX_VALUE);
		
		setTop(ipBox);
		setBottom(tabPanel);
		setAlignment(tabPanel, Pos.CENTER);

		
		setStyle("-fx-background-color:  #d5e4e6");
		
		setPadding(new Insets(5));
	}

	public HBox getIpBox() {
		return ipBox;
	}

	public TabPane getTabPanel() {
		return tabPanel;
	}

	public Tab getTab_location() {
		return tab_location;
	}

	public Tab getTab_connection() {
		return tab_connection;
	}

	public Tab getTab_security() {
		return tab_security;
	}

	public ImageView getConImg() {
		return conImg;
	}

	public TextField getIpTxt() {
		return ipTxt;
	}

	public Button getCheckBt() {
		return checkBt;
	}
}
