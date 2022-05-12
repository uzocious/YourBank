package application.controller;

import java.io.IOException;
import java.rmi.Naming;

import application.gui.StartGUI;
import application.model.SurveyAlert;
import application.rmi.interphace.SurveyAnswers;
import application.rmi.interphace.SurveyInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;


/**
 * A controller class used to implement the survey GUI.
 * @author Uzoma Oseji - 1715756
 *
 */
public class GUIController {
	
	// JavaFX component variables
	@FXML private AnchorPane paneQuestion1;
	@FXML private AnchorPane paneQuestion2;
	@FXML private AnchorPane paneQuestion3;
	@FXML private AnchorPane paneQuestion4;
	@FXML private AnchorPane paneQuestion5;
	@FXML private RadioButton rdQ1Option1;
	@FXML private RadioButton rdQ1Option2;
	@FXML private RadioButton rdQ2Option1;
	@FXML private RadioButton rdQ2Option2;
	@FXML private RadioButton rdQ2Option3;
	@FXML private RadioButton rdQ3Option1;
	@FXML private RadioButton rdQ3Option2;
	@FXML private RadioButton rdQ3Option3;
	@FXML private RadioButton rdQ4Option1;
	@FXML private RadioButton rdQ4Option2;
	@FXML private RadioButton rdQ4Option3;
	@FXML private RadioButton rdQ5Option1;
	@FXML private RadioButton rdQ5Option2;
	@FXML private RadioButton rdQ5Option3;


	// Static string variables
	public static String answerToQuestion1 = null;
	public static String answerToQuestion2 = null;
	public static String answerToQuestion3 = null;
	public static String answerToQuestion4 = null;
	public static String answerToQuestion5 = null;
		
	
	/**
	 * Shows scene (gui window) called 'Question 1'
	 * @param event on-click action event which is invoked whenever the button is fired
	 * @throws IOException
	 */
	@FXML
	private void showQuestion1(ActionEvent event) throws IOException
	{
		try 
		{
			SurveyInterface service =  (SurveyInterface) Naming.lookup("rmi://localhost/SurveyService");
			
			if (service.isServerReady() == true) 
			{
				service.setSessionCookies();
				
				closeScene(event);
				Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("../view/Question1.fxml"));
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.setResizable(false);
				primaryStage.setTitle("Your Bank - Survey System");
				primaryStage.getIcons().add(new Image(StartGUI.class.getResourceAsStream("../image/yourbank_logo.png")));
				primaryStage.setOnCloseRequest(e -> {
					e.consume();
					SurveyAlert.closeCurrentWindowConfirmation(primaryStage);
					});
				primaryStage.show();
			} 
		}
		catch (Exception ex) 
		{
			SurveyAlert.serverError();
			
			//Uncomment code for full system error analysis
			//System.out.println("A problem occured: " + ex.toString());
			//ex.printStackTrace();
		}
	}
	
	/**
	 * Shows scene (gui window) called 'Question 2'
	 * @param event on-click action event which is invoked whenever the button is fired
	 * @throws IOException
	 */
	@FXML
	private void showQuestion2(ActionEvent event)throws IOException
	{
		getAnswerToQuestion1();
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Question2.fxml"));
		paneQuestion1.getChildren().setAll(pane);
	}
	
	/**
	 * Shows scene (gui window) called 'Question 3'
	 * @param event on-click action event which is invoked whenever the button is fired
	 * @throws IOException
	 */
	@FXML
	private void showQuestion3(ActionEvent event)throws IOException
	{
		getAnswerToQuestion2();
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Question3.fxml"));
		paneQuestion2.getChildren().setAll(pane);
	}
	
	/**
	 * Shows scene (gui window) called 'Question 4'
	 * @param event on-click action event which is invoked whenever the button is fired
	 * @throws IOException
	 */
	@FXML
	private void showQuestion4(ActionEvent event)throws IOException
	{
		getAnswerToQuestion3();
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Question4.fxml"));
		paneQuestion3.getChildren().setAll(pane);
	}
	
	/**
	 * Shows scene (gui window) called 'Question 5'
	 * @param event on-click action event which is invoked whenever the button is fired
	 * @throws IOException
	 */
	@FXML
	private void showQuestion5(ActionEvent event)throws IOException
	{
		getAnswerToQuestion4();
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Question5.fxml"));
		paneQuestion4.getChildren().setAll(pane);
	}
	
	/**
	 * Shows scene (gui window) called 'Complete'
	 * @param event on-click action event which is invoked whenever the button is fired
	 * @throws IOException
	 */
	@FXML
	private void showComplete(ActionEvent event)throws IOException
	{
		getAnswerToQuestion5();
		postAnswersToServer();
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Completed.fxml"));
		paneQuestion5.getChildren().setAll(pane);
	}
	

	/**
	 * Closes current scene (gui window)
	 * @param event on-click action event which is invoked whenever the button is fired
	 */
	@FXML
	private void closeScene(ActionEvent event)
	{
		((Node)event.getSource()).getScene().getWindow().hide();
	}
	
	
	/**
	 * Gets answer to question 1
	 */
	private void getAnswerToQuestion1() 
	{
		if (rdQ1Option1.isSelected()) 
		{
			answerToQuestion1 = rdQ1Option1.getText();
		}
		
		if (rdQ1Option2.isSelected()) 
		{
			answerToQuestion1 = rdQ1Option2.getText();
		}
		
	}

	/**
	 * Gets answer to question 2
	 */
	private void getAnswerToQuestion2() 
	{
		if (rdQ2Option1.isSelected()) 
		{
			answerToQuestion2 = rdQ2Option1.getText();
		}
		
		if (rdQ2Option2.isSelected()) 
		{
			answerToQuestion2 = rdQ2Option2.getText();
		}
		
		if (rdQ2Option3.isSelected()) 
		{
			answerToQuestion2 = rdQ2Option3.getText();
		}
		
	}
	
	/**
	 * Gets answer to question 3
	 */
	private void getAnswerToQuestion3() 
	{
		if (rdQ3Option1.isSelected()) 
		{
			answerToQuestion3 = rdQ3Option1.getText();
		}
		
		if (rdQ3Option2.isSelected()) 
		{
			answerToQuestion3 = rdQ3Option2.getText();
		}
		
		if (rdQ3Option3.isSelected()) 
		{
			answerToQuestion3 = rdQ3Option3.getText();
		}
		
	}
	
	/**
	 * Gets answer to question 4
	 */
	private void getAnswerToQuestion4() 
	{
		if (rdQ4Option1.isSelected()) 
		{
			answerToQuestion4 = rdQ4Option1.getText();
		}
		
		if (rdQ4Option2.isSelected()) 
		{
			answerToQuestion4 = rdQ4Option2.getText();
		}
		
		if (rdQ4Option3.isSelected()) 
		{
			answerToQuestion4 = rdQ4Option3.getText();
		}
		
	}
	
	/**
	 *  Gets answer to question 5
	 */
	private void getAnswerToQuestion5() 
	{
		if (rdQ5Option1.isSelected()) 
		{
			answerToQuestion5 = rdQ5Option1.getText();
		}
		
		if (rdQ5Option2.isSelected()) 
		{
			answerToQuestion5 = rdQ5Option2.getText();
		}
		
		if (rdQ5Option3.isSelected()) 
		{
			answerToQuestion5 = rdQ5Option3.getText();
		}
		
	}
	
	
	/**
	 * Posts answers to the survey server via Remote Method Invocation (RMI)
	 */
	@FXML
	private void postAnswersToServer()
	{
		try 
		{
			SurveyAnswers singleSurey = new SurveyAnswers(answerToQuestion1, answerToQuestion2, answerToQuestion3,
					answerToQuestion4, answerToQuestion5);
			
			SurveyInterface service =  (SurveyInterface) Naming.lookup("rmi://localhost/SurveyService");
			
			service.setMultipleSurvey(singleSurey);
		
			System.out.println("Post Request Successful");
		} 
		catch (Exception ex) 
		{
			SurveyAlert.serverError();
			
			//Uncomment code for full system error analysis
			//System.out.println("A problem occured: " + ex.toString());
			//ex.printStackTrace();
		}
		
	}
	
	
	
	
}
