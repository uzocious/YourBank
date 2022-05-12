package application.gui;

import application.model.ServerAlert;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * A simple graphical user interface to implement a survey server. 
 * @author Uzoma Oseji - 1715756
 *
 */
public class StartServerGUI extends Application  
{
	/**
	 * Starts Survey Server GUI
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/Start.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Your Bank - Server");
			primaryStage.getIcons().add(new Image(StartServerGUI.class.getResourceAsStream("../image/yourbank_logo.png")));
			primaryStage.setOnCloseRequest(e -> {
				e.consume();
				ServerAlert.closeCurrentWindowConfirmation(primaryStage);
				});
			primaryStage.show();
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
