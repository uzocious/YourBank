package application.gui;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;



/**
 * A simple graphical user interface to implement a survey. 
 * @author Uzoma Oseji - 1715756
 *
 */
public class StartGUI extends Application {
	/**
	 * Starts Survey GUI
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/Start.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Your Bank - Survey System");
			primaryStage.getIcons().add(new Image(StartGUI.class.getResourceAsStream("../image/yourbank_logo.png")));
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
