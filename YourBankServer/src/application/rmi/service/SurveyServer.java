package application.rmi.service;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import application.rmi.interphace.SurveyInterface;

/**
 * This class starts and stops the server. 
 * @author Uzoma Oseji - 1715756
 *
 */
public class SurveyServer {

	/**
	 * Entry point of the server.
	 */
	public static void startServer()
	{
		 System.out.println("Attempting to start Survey Server...");
		
		 try
		 {
			SurveyService service = new SurveyService();
			 
			Registry registry = LocateRegistry.createRegistry(1099);
			
			registry.rebind("SurveyService", service);
			
			System.out.println("Service started. Welcome to the RMI Survey Service!");
			
		} 
		catch (RemoteException e)
		 {
			System.out.println("An error occured: "+e.toString());
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Exit point of the server.
	 */
	public static void stopServer()
	{
		System.out.println("Attempting to shut down Survey Server...");
		
        try {
        	
        	Registry registry = LocateRegistry.getRegistry();
        	
        	SurveyInterface service = new SurveyService();
        	
        	registry.unbind("SurveyService");
            UnicastRemoteObject.unexportObject(service, true);
            
            System.out.println("Server Shut Down");

        } catch (NoSuchObjectException e) 
        {
            e.printStackTrace();
        } catch (NotBoundException e) 
        {
            e.printStackTrace();
        } catch (RemoteException ex) {
        	ex.printStackTrace();
        }
        
	}
	
	
}
