package application.model;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * This class is used to display different types of alerts for the client gui
 * @author Uzoma Oseji - 1715756
 *
 */
public class SurveyAlert 
{
	/**
	 * Confirmation alert for closing current window
	 * @param primaryStage
	 */
	public static void closeCurrentWindowConfirmation(Stage primaryStage) 
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Your Bank - Survey System");
		alert.setContentText("Are you sure you want to end this survey?");
		alert.setHeaderText(null);
		
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(SurveyAlert.class.getResource("../image/yourbank_logo.png").toString()));
		
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
		alert.setTitle("Your Bank - Survey System");
		alert.setContentText("Server Error" + "\r\n" + 
		"The server encountered an internal error and was unable to complete your request.");
		alert.setHeaderText(null);
		
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(SurveyAlert.class.getResource("../image/yourbank_logo.png").toString()));
		
		alert.showAndWait();
	}
}
