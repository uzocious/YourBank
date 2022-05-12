package application.rmi.interphace;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * RMI interface to retrieve surveys answered from the client to the server and 
 * save cookies in the server.
 * @author Uzoma Oseji - 1715756
 */
public interface SurveyInterface extends Remote 
{
	/**
	 * Sets a list of survey answered
	 * @param singleSurveyAnswered: a single survey answered
	 * @throws RemoteException
	 */
	public void setMultipleSurvey(SurveyAnswers singleSurveyAnswered) throws RemoteException;
	
	/**
	 * Returns a list of all the surveys that have been answered
	 * @return 
	 * @throws RemoteException
	 */
	public ArrayList<SurveyAnswers> getMultipleSurvey() throws RemoteException;
	 
	/**
	 * A boolean method that returns true if the server is connected
	 * @return
	 * @throws RemoteException
	 */
	public boolean isServerReady() throws RemoteException;
	
	/**
	 * Sets a session cookie string in the server
	 * @throws RemoteException
	 */
	public void setSessionCookies() throws RemoteException;
	
}
