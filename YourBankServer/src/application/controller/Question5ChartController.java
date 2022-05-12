package application.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
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
 *  This controller class is used to control the necessary aspect of the question 5 chart gui window
 *  e.g. displaying a bar chart with the necessary data for question 5
 * @author Uzoma Oseji - 1715756
 *
 */
public class Question5ChartController implements Initializable
{
	
	@FXML private AnchorPane paneQuestion5Chart;
    @FXML private BarChart<?, ?> question5BarChart;
    @FXML private CategoryAxis x;
    @FXML private NumberAxis y;
    @FXML private Label lblResponses;

    int totalNumOfResponses = 0;
	int numOfExtremelySatisfied = 0;
	int numOfSomewhatSatisfied = 0;
	int numOfNotAtAllSatisfied = 0;
	
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
				if (singleSurvey.getAnswerToQuestion5().equals("Extremely satisfied")) 
				{
					numOfExtremelySatisfied = numOfExtremelySatisfied + 1;
				}
				
				if (singleSurvey.getAnswerToQuestion5().equals("Somewhat satisfied"))
				{
					numOfSomewhatSatisfied = numOfSomewhatSatisfied + 1;
				}
				
				if (singleSurvey.getAnswerToQuestion5().equals("Not at all satisfied"))
				{
					numOfNotAtAllSatisfied = numOfNotAtAllSatisfied + 1;
				}
				
			}
			
			@SuppressWarnings("rawtypes")
			XYChart.Series series = new XYChart.Series();
			
			series.getData().add(new XYChart.Data<>("Extremely satisfied", (int)numOfExtremelySatisfied));
			series.getData().add(new XYChart.Data<>("Somewhat satisfied", (int)numOfSomewhatSatisfied));
			series.getData().add(new XYChart.Data<>("Not at all satisfied", (int)numOfNotAtAllSatisfied));
			
			// Exporting results
			SurveyAnalysis.numOfExtremelySatisfied = numOfExtremelySatisfied;
			SurveyAnalysis.numOfSomewhatSatisfied = numOfSomewhatSatisfied;
			SurveyAnalysis.numOfNotAtAllSatisfied = numOfNotAtAllSatisfied;
			
			question5BarChart.getData().addAll(series);
		} 
		catch (Exception ex) 
		{
			ServerAlert.serverError();
			System.out.println("A problem occured: " + ex.toString());
			ex.printStackTrace();
		}
	}
	

	/**
	 * Exports the survey results to a text file for record
	 * @param event on-click action event which is invoked whenever the button is fired
	 */
    @FXML
    private void exportResults(ActionEvent event) 
    {
    	String result = ServerAlert.exportSurveyResultConfirmation();
    	
    	if (result.equals("Yes"))
		{			
    		try 
    		{
    			FileWriter fWriter = new FileWriter("src/application/fileTXT/SurveyResults.txt");
    			PrintWriter pWriter = new PrintWriter(fWriter);
    			
    			String str = String.format("<--Basic Survey Analysis-->\n\n"
    					
    					+ "Total Number of Responses: %s\n\n"
    					
    					+ "1) What type of account are you applying for?\n"
    					+ "Personal - %s | Business - %s\n\n"
    					
    					+ "2) What type of product are you applying for?\n"
    					+ "Current Account - %s | Loan - %s | Overdraft - %s\n\n"
    					
    					+ "3) Based on your experience, how likely would you recommend Your Bank to a friend?\n"
    					+ "Extremely likely - %s | Somewhat likely - %s | Not at all likely - %s\n\n"
    					
    					+ "4) How easy was our portal to use today?\n"
    					+ "Very easy - %s | Somewhat easy - %s | Very difficult - %s\n\n"
    					
    					+ "5) How satisfied are you with the overall service rendered to you today?\n"
    					+ "Extremely satisfied - %s | Somewhat satisfied - %s | Not at all satisfied - %s",
    					
    					SurveyAnalysis.totalNoOfResponses,
    					SurveyAnalysis.numOfPersonal, SurveyAnalysis.numOfBusiness,
    					SurveyAnalysis.numOfCurrentAccount, SurveyAnalysis.numOfLoan, SurveyAnalysis.numOfOverdraft,
    					SurveyAnalysis.numOfExtremelyLikely, SurveyAnalysis.numOfSomewhatLikely, SurveyAnalysis.numOfNotAtAllLikely,
    					SurveyAnalysis.numOfVeryEasy, SurveyAnalysis.numOfSomewhatEasy, SurveyAnalysis.numOfVeryDifficult,
    					SurveyAnalysis.numOfExtremelySatisfied, SurveyAnalysis.numOfSomewhatSatisfied, SurveyAnalysis.numOfNotAtAllSatisfied);
    			
    			pWriter.println(str);
    			
    			System.out.println("Survey Export Successful");
    			System.out.println("Location: ./YourBankServer/src/application/fileTXT/SurveyResults.txt");
    			
    			pWriter.close();
    			
    			
    			// Making a copy of the now created file (i.e. SurveyResults.txt) and sending it (YourBankMaven) project directory
    			
    			File originalFile = new File("src/application/fileTXT/SurveyResults.txt");
    			File newFile = new File("../YourBankMaven/SurveyResults.txt");
    			
    			if (newFile.exists())
    			{
    				newFile.delete();
    				Files.copy(originalFile.toPath(), newFile.toPath());	
				}
    			else
    			{
    				Files.copy(originalFile.toPath(), newFile.toPath());
    			}
    			
    			System.out.println("SurveyResults.txt Sent to Maven Project");
    			System.out.println("Location: ./YourBankMaven/SurveyResults.txt" + "\n");
    			
    			goHome(event);
    			
    		} 
    		catch (IOException e)
    		{
    			System.out.println("ERROR EXPORTING RESULTS");
    		}
		}
    	
    }

	
	/**
	 * Redirects to question 4 chart gui window 
	 * @param event on-click action event which is invoked whenever the button is fired
	 * @throws IOException
	 */
	@FXML
	private void goBack(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Question4Chart.fxml"));
		paneQuestion5Chart.getChildren().setAll(pane);
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
		paneQuestion5Chart.getChildren().setAll(pane);
	}

}
