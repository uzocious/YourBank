package application.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import application.database.Administrator;
import application.database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * This controller class is used to control the necessary aspect of the server login gui window
 * @author Uzoma Oseji - 1715756
 *
 */
public class ServerGUIController implements Initializable
{
	@FXML private AnchorPane paneStart;
	@FXML private TextField txtUsername;
	@FXML private PasswordField txtPassword;
	@FXML private Label lblIncorrect;
	@FXML private Button btnLogin;
    @FXML private CheckBox chkRemember;
	
	Database database = new Database();
	
	ArrayList<String> credenetials = new ArrayList<String>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		lblIncorrect.setVisible(false);
		
		readRememberMyCredentials();
		
		if(credenetials.size() != 0)
		{
			txtUsername.setText(credenetials.get(0));
			txtPassword.setText(credenetials.get(1));
			
			chkRemember.setSelected(true);
		}
	}
	
	/**
	 * Logs the user in and redirects to server catalogue gui window
	 * if user details are correct
	 * @param event on-click action event which is invoked whenever the button is fired
	 * @throws IOException
	 */
	@FXML
	private void login(ActionEvent event) throws IOException
	{
		@SuppressWarnings("unused")
		boolean isDetailsWrong = true;
		
		String username = txtUsername.getText();
		String password = txtPassword.getText();
		
		// trims white spaces
		username = username.trim();
		
		for (Administrator admin : database.getAdministrators())
		{
			if (username.equals(admin.getUsername()) && password.equals(admin.getPassword())) 
			{
				System.out.println("Login Successful");
				
				writeRememberMyCredentials(username, password);

				isDetailsWrong = false;
				
				AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Catalogue.fxml"));
				paneStart.getChildren().setAll(pane);
				break;
			}
		}
		
		if (isDetailsWrong = true) 
		{
			lblIncorrect.setVisible(true);
		}		
	}
	
	
	/**
	 * Writes the credentials of the user to a text file
	 * @param username the users username
	 * @param password the users password
	 */
	private void writeRememberMyCredentials(String username, String password)
	{
		if (chkRemember.isSelected())
		{
			try 
			{
				FileWriter fWriter = new FileWriter("src/application/fileTXT/Credentials.txt");
				PrintWriter pWriter = new PrintWriter(fWriter);
				
				pWriter.println(username);
				pWriter.println(password);
				
				pWriter.close();
				
			} 
			catch (Exception e)
			{
				System.out.println("ERROR SAVING CREDENTIALS");
			}
		}
	}
	
	
	/**
	 * Reads the saved credentials of the user from a text file
	 */
	private void readRememberMyCredentials()
	{
		try
		{
			FileReader fReader = new FileReader("src/application/fileTXT/Credentials.txt");
			
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(fReader);
			
			while (scan.hasNextLine())
			{
				credenetials.add(scan.nextLine());
			}
		} 
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR REMEMBERING CREDENTIALS");
		}
	}
	

}
