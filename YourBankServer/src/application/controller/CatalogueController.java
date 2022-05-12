package application.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.rmi.Naming;
import java.util.ResourceBundle;
import application.gui.StartServerGUI;
import application.model.ServerAlert;
import application.model.ServerTimer;
import application.rmi.interphace.SurveyInterface;
import application.rmi.service.SurveyServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *  This controller class is used to control the necessary aspect of the server catalogue gui window
 * @author Uzoma Oseji - 1715756
 *
 */
public class CatalogueController implements Initializable
{
	@FXML private AnchorPane paneCatalogue;
	@FXML private TextField txtServerStatus;
    @FXML private Button btnServerSwitch;
    @FXML private Button btnAnalyseSurveyResult;
    @FXML private Label lblInactive;

    ServerTimer timer = new ServerTimer();

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		System.out.println("Now Loading Server....");
		
		timer.start(lblInactive);
		
		try 
		{
			SurveyInterface service =  (SurveyInterface) Naming.lookup("rmi://localhost/SurveyService");
			
			if (service.isServerReady() == true)
			{				
				txtServerStatus.setText("Connected");
				txtServerStatus.setStyle("-fx-background-color: #dddada; -fx-text-inner-color: green;");
				
				btnServerSwitch.setText("Stop Server");
				btnServerSwitch.setStyle("-fx-text-fill: #e40808;");
				
				btnAnalyseSurveyResult.setDisable(false);
			}
		} 
		catch (Exception ex) 
		{
			txtServerStatus.setText("Not Connected");
			txtServerStatus.setStyle("-fx-background-color: #dddada; -fx-text-inner-color: red;");
			
			btnServerSwitch.setText("Start Server");
			btnServerSwitch.setStyle("-fx-text-fill: #1aab6b;");
			
			btnAnalyseSurveyResult.setDisable(true);
			
			//Uncomment code for full system error analysis
			//System.out.println(ex.toString());
			//ex.printStackTrace();
		}
		
		System.out.println("Loading Complete.");
		
	}

	

	
	/**
	 * Serves as a switch (on/off) for the server.
	 * @param event on-click action event which is invoked whenever the button is fired
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	@FXML
	private void serverSwitch(ActionEvent event) throws IOException, URISyntaxException
	{
		if (txtServerStatus.getText().equals("Connected"))
		{
			String result = ServerAlert.shutDownServerConfirmation();
			
			if (result.equals("Yes")) 
			{
				System.out.println("Shutting Down Server...");
				SurveyServer.stopServer();
				System.exit(0);
			}
		}
		else 
		{
			System.out.println("Switching On Server...");
			SurveyServer.startServer();
			refresh();
		}
	}
	
	/**
	 * Redirects to question 1 chart gui window 
	 * @param event on-click action event which is invoked whenever the button is fired
	 * @throws IOException
	 */
	@FXML
	private void goToQuestion1ChartPane(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Question1Chart.fxml"));
		paneCatalogue.getChildren().setAll(pane);
	}
	
	/**
	 * Redirects to server information gui window
	 * @param event on-click action event which is invoked whenever the button is fired
	 * @throws IOException
	 */
	@FXML
	private void goToServerInfoPane(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/ServerInfo.fxml"));
		paneCatalogue.getChildren().setAll(pane);
	}
	
	/**
	 * Refreshes the server catalogue gui window
	 * @throws IOException
	 */
	@FXML
	private void refresh() throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Catalogue.fxml"));
		paneCatalogue.getChildren().setAll(pane);
	}
	
	/**
	 * Logs out and redirects to the server login gui window
	 * @param event on-click action event which is invoked whenever the button is fired
	 * @throws IOException
	 */
	@FXML
	private void logOut(ActionEvent event) throws IOException
	{
		((Node)event.getSource()).getScene().getWindow().hide();
		
		StartServerGUI mainGUI = new StartServerGUI();
		
		Stage primaryStage = new Stage();
		
		mainGUI.start(primaryStage);
	}
	
	
	
}
