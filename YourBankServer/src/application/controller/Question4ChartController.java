package application.controller;

import java.io.IOException;
import java.net.URL;
import java.rmi.Naming;
import java.util.ResourceBundle;

import application.model.SurveyAnalysis;
import application.model.ServerAlert;
import application.rmi.interphace.SurveyAnswers;
import application.rmi.interphace.SurveyInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *  This controller class is used to control the necessary aspect of the question 4 chart gui window
 *  e.g. displaying a bar chart with the necessary data for question 4
 * @author Uzoma Oseji - 1715756
 *
 */
public class Question4ChartController implements Initializable
{
	@FXML private AnchorPane paneQuestion4Chart;
    @FXML private BarChart<?, ?> question4BarChart;
    @FXML private CategoryAxis x;
    @FXML private NumberAxis y;
    @FXML private Label lblResponses;

    int totalNumOfResponses = 0;
	int numOfVeryEasy = 0;
	int numOfSomewhatEasy = 0;
	int numOfVeryDifficult = 0;
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		try 
		{
			SurveyInterface service =  (SurveyInterface) Naming.lookup("rmi://localhost/SurveyService");
			
			totalNumOfResponses = service.getMultipleSurvey().size();
			
			if(totalNumOfResponses < 1) 
			{
				lblResponses.setText("N/A");
			}
			else 
			{
				lblResponses.setText(String.valueOf(totalNumOfResponses));
			}
			
			
			for (SurveyAnswers singleSurvey : service.getMultipleSurvey())
			{
				if (singleSurvey.getAnswerToQuestion4().equals("Very easy")) 
				{
					numOfVeryEasy = numOfVeryEasy + 1;
				}
				
				if (singleSurvey.getAnswerToQuestion4().equals("Somewhat easy"))
				{
					numOfSomewhatEasy = numOfSomewhatEasy + 1;
				}
				
				if (singleSurvey.getAnswerToQuestion4().equals("Very difficult"))
				{
					numOfVeryDifficult = numOfVeryDifficult + 1;
				}
				
			}
			
			@SuppressWarnings("rawtypes")
			XYChart.Series series = new XYChart.Series();
			
			series.getData().add(new XYChart.Data<>("Very easy", (int)numOfVeryEasy));
			series.getData().add(new XYChart.Data<>("Somewhat easy", (int)numOfSomewhatEasy));
			series.getData().add(new XYChart.Data<>("Very difficult", (int)numOfVeryDifficult));
			
			// Exporting results
			SurveyAnalysis.numOfVeryEasy = numOfVeryEasy;
			SurveyAnalysis.numOfSomewhatEasy = numOfSomewhatEasy;
			SurveyAnalysis.numOfVeryDifficult = numOfVeryDifficult;
			
			question4BarChart.getData().addAll(series);
		} 
		catch (Exception ex) 
		{
			ServerAlert.serverError();
			System.out.println("A problem occured: " + ex.toString());
			ex.printStackTrace();
		}
	}

	/**
	 * Redirects to question 3 chart gui window 
	 * @param event on-click action event which is invoked whenever the button is fired
	 * @throws IOException
	 */
	@FXML
	private void goBack(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Question3Chart.fxml"));
		paneQuestion4Chart.getChildren().setAll(pane);
	}
	
	/**
	 * Redirects to question 5 chart gui window 
	 * @param event on-click action event which is invoked whenever the button is fired
	 * @throws IOException
	 */
	@FXML
	private void goNext(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Question5Chart.fxml"));
		paneQuestion4Chart.getChildren().setAll(pane);
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
		paneQuestion4Chart.getChildren().setAll(pane);
	}

}
