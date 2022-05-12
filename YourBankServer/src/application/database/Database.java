package application.database;

import java.util.ArrayList;

/**
 * This class is used to store a list of administrators 
 * @author Uzoma Oseji - 1715756
 *
 */
public class Database 
{
	ArrayList<Administrator> administrators = new ArrayList<Administrator>();
	
	public Database() 
	{
		insertIntoDatabase();
	}

	/**
	 *  Adds a new administrator into the database
	 */
	private void insertIntoDatabase() 
	{
		administrators.add(new Administrator("uzoma", "oseji"));
		administrators.add(new Administrator("admin1", "password"));
		administrators.add(new Administrator("admin2", "password"));
	}
	
	/**
	 * Returns the list of administrators
	 * @return
	 */
	public ArrayList<Administrator> getAdministrators() 
	{
		return administrators;
	}
}
