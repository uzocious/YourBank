package application.model;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * This class is used to display different types of alerts for the server gui
 * @author Uzoma Oseji - 1715756
 *
 */
public class ServerAlert 
{
	/**
	 * Confirmation alert for closing current window
	 * @param primaryStage
	 */
	public static void closeCurrentWindowConfirmation(Stage primaryStage) 
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Your Bank - Server");
		alert.setContentText("Are you sure you want to close this system?");
		alert.setHeaderText(null);
		
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(ServerAlert.class.getResource("../image/yourbank_logo.png").toString()));
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			primaryStage.close();
		}
	}
	
	
	/**
	 * Error alert for server connection
	 */
	public static void serverError() 
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Your Bank - Server");
		alert.setContentText("Server Error" + "\r\n" + 
		"The server encountered an error and was unable to complete your request.");
		alert.setHeaderText(null);
		
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(ServerAlert.class.getResource("../image/yourbank_logo.png").toString()));
		
		alert.showAndWait();
	}
	
	
	/**
	 * Confirmation alert for shutting down the server
	 * @return
	 */
	public static String shutDownServerConfirmation() 
	{
		String confirmation = "";
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Your Bank - Server");
		alert.setContentText("You are about to shut down the server.\r\n" +
				"If you do, the system will be closed and you will have to restart it manually.\r\n");
		alert.setHeaderText(null);
		
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(ServerAlert.class.getResource("../image/yourbank_logo.png").toString()));
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			confirmation = "Yes";
		}
		return confirmation;
	}
	
	/**
	 * Confirmation alert for exporting survey results
	 * @return
	 */
	public static String exportSurveyResultConfirmation() 
	{
		String confirmation = "";
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Your Bank - Server");
		alert.setContentText("Are you sure you want to export the survey results?");
		alert.setHeaderText(null);
		
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(ServerAlert.class.getResource("../image/yourbank_logo.png").toString()));
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			confirmation = "Yes";
		}
		return confirmation;
	}
}
