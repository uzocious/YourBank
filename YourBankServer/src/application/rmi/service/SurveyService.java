package application.rmi.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import application.rmi.interphace.SurveyAnswers;
import application.rmi.interphace.SurveyInterface;

/**
 * Implementation of the survey. Note that chosen answers are collected in this
 * object as well. That means that if the object is destroyed, for instance server restart
 * the collected data is all gone.
 * @author Uzoma Oseji - 1715756
 */
public class SurveyService extends UnicastRemoteObject implements SurveyInterface
{

	private static final long serialVersionUID = 6364053162581192653L;
	
	public String sessionCookies = null;
	
	private ArrayList<SurveyAnswers> multipleSurvey = new ArrayList<SurveyAnswers>();
	

	public SurveyService() throws RemoteException
	{}


	/**
	 * Implementation of remote interface method. 
	 */
	@Override
	public void setMultipleSurvey(SurveyAnswers singleSurveyAnswered) throws RemoteException 
	{
		multipleSurvey.add(singleSurveyAnswered);
		
	}


	/**
	 * Implementation of remote interface method. 
	 */
	@Override
	public ArrayList<SurveyAnswers> getMultipleSurvey() throws RemoteException 
	{
		return multipleSurvey;
	}


	/**
	 * Implementation of remote interface method. 
	 */
	@Override
	public boolean isServerReady() throws RemoteException {
		return true;
	}


	/**
	 * Implementation of remote interface method. 
	 */
	@Override
	public void setSessionCookies() throws RemoteException 
	{
		sessionCookies = "recpient-"+Math.random();
	}
	
	
}
