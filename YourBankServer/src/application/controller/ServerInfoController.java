package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.model.ServerWebApi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * This controller class is used to control the necessary aspect of the server information gui window
 * @author Uzoma Oseji - 1715756
 *
 */
public class ServerInfoController implements Initializable
{
    @FXML private AnchorPane paneServerInfo;
    @FXML private Label lblIPAddress;
    @FXML private Label lblStatus;
    @FXML private Label lblCountry;
    @FXML private Label lblCountryCode;
    @FXML private Label lblRegion;
    @FXML private Label lblRegionName;
    @FXML private Label lblCity;
    @FXML private Label lblZip;
    @FXML private Label lblTimeZone;
    @FXML private Label lblOrganisation;
    
    ServerWebApi webApi = new ServerWebApi();
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		webApi.startWebApi();
		
		lblIPAddress.setText(webApi.getIp());
		lblStatus.setText(webApi.getStatus());
		lblCountry.setText(webApi.getCountry());
		lblCountryCode.setText(webApi.getCountryCode());
		lblRegion.setText(webApi.getRegion());
		lblRegionName.setText(webApi.getRegionName());
		lblCity.setText(webApi.getCity());
		lblZip.setText(webApi.getZip());
		lblTimeZone.setText(webApi.getTimezone());
		lblOrganisation.setText(webApi.getOrg());
	}
	

	/**
	 * Redirects to the server catalogue gui window 
	 * @param event on-click action event which is invoked whenever the button is fired
	 * @throws IOException
	 */
	@FXML
	private void goHome(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Catalogue.fxml"));
		paneServerInfo.getChildren().setAll(pane);
	}
}
