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
 *  This controller class is used to control the necessary aspect of the question 1 chart gui window
 *  e.g. displaying a bar chart with the necessary data for question 1
 * @author Uzoma Oseji - 1715756
 *
 */
public class Question1ChartController implements Initializable
{
	
	@FXML private AnchorPane paneQuestion1Chart;
    @FXML private BarChart<?, ?> question1BarChart;
    @FXML private CategoryAxis x;
    @FXML private NumberAxis y;
    @FXML private Label lblResponses;

    int totalNumOfResponses = 0;
	int numOfPersonal = 0;
	int numOfBusiness = 0;
	
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
				if (singleSurvey.getAnswerToQuestion1().equals("Personal")) 
				{
					numOfPersonal = numOfPersonal + 1;
				}
				
				if (singleSurvey.getAnswerToQuestion1().equals("Business"))
				{
					numOfBusiness = numOfBusiness + 1;
				}
			}
			
			@SuppressWarnings("rawtypes")
			XYChart.Series series = new XYChart.Series();
			
			series.getData().add(new XYChart.Data<>("Personal", (int)numOfPersonal));
			series.getData().add(new XYChart.Data<>("Business", (int)numOfBusiness));
			
			// Exporting results
			SurveyAnalysis.numOfPersonal = numOfPersonal;
			SurveyAnalysis.numOfBusiness = numOfBusiness;
			SurveyAnalysis.totalNoOfResponses = totalNumOfResponses;
			
			question1BarChart.getData().addAll(series);
		} 
		catch (Exception ex) 
		{
			ServerAlert.serverError();
			System.out.println("A problem occured: " + ex.toString());
			ex.printStackTrace();
		}
	}

	/**
	 * Redirects to the server catalogue gui window 
	 * @param event on-click action event which is invoked whenever the button is fired
	 * @throws IOException
	 */
	@FXML
	private void goBack(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Catalogue.fxml"));
		paneQuestion1Chart.getChildren().setAll(pane);
	}
	
	
	/**
	 * Redirects to question 2 chart gui window 
	 * @param event on-click action event which is invoked whenever the button is fired
	 * @throws IOException
	 */
	@FXML
	private void goNext(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Question2Chart.fxml"));
		paneQuestion1Chart.getChildren().setAll(pane);
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
		paneQuestion1Chart.getChildren().setAll(pane);
	}
	
	

}
