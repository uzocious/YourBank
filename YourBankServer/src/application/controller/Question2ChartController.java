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
 *  This controller class is used to control the necessary aspect of the question 2 chart gui window
 *  e.g. displaying a bar chart with the necessary data for question 2
 * @author Uzoma Oseji - 1715756
 *
 */
public class Question2ChartController implements Initializable
{
	
	@FXML private AnchorPane paneQuestion2Chart;
    @FXML private BarChart<?, ?> question2BarChart;
    @FXML private CategoryAxis x;
    @FXML private NumberAxis y;
    @FXML private Label lblResponses;

    int totalNumOfResponses = 0;
	int numOfCurrentAccount = 0;
	int numOfLoan = 0;
	int numOfOverdraft = 0;
	
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
				if (singleSurvey.getAnswerToQuestion2().equals("Current Account")) 
				{
					numOfCurrentAccount = numOfCurrentAccount + 1;
				}
				
				if (singleSurvey.getAnswerToQuestion2().equals("Loan"))
				{
					numOfLoan = numOfLoan + 1;
				}
				
				if (singleSurvey.getAnswerToQuestion2().equals("Overdraft"))
				{
					numOfOverdraft = numOfOverdraft + 1;
				}
				
			}
			
			@SuppressWarnings("rawtypes")
			XYChart.Series series = new XYChart.Series();
			
			series.getData().add(new XYChart.Data<>("Current Account", (int)numOfCurrentAccount));
			series.getData().add(new XYChart.Data<>("Loan", (int)numOfLoan));
			series.getData().add(new XYChart.Data<>("Overdraft", (int)numOfOverdraft));
			
			// Exporting results
			SurveyAnalysis.numOfCurrentAccount = numOfCurrentAccount;
			SurveyAnalysis.numOfLoan = numOfLoan;
			SurveyAnalysis.numOfOverdraft = numOfOverdraft;
			
			question2BarChart.getData().addAll(series);
		} 
		catch (Exception ex) 
		{
			ServerAlert.serverError();
			System.out.println("A problem occured: " + ex.toString());
			ex.printStackTrace();
		}
	}

	/**
	 * Redirects to question 1 chart gui window 
	 * @param event on-click action event which is invoked whenever the button is fired
	 * @throws IOException
	 */
	@FXML
	private void goBack(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Question1Chart.fxml"));
		paneQuestion2Chart.getChildren().setAll(pane);
	}
	
	/**
	 * Redirects to question 3 chart gui window 
	 * @param event on-click action event which is invoked whenever the button is fired
	 * @throws IOException
	 */
	@FXML
	private void goNext(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Question3Chart.fxml"));
		paneQuestion2Chart.getChildren().setAll(pane);
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
		paneQuestion2Chart.getChildren().setAll(pane);
	}
	

}
