package application.database;

/**
 * This class is used to store the username and password of an administrator.
 * @author Uzoma Oseji - 1715756
 *
 */
public class Administrator 
{
	private String _username;
	private String _password;

	public Administrator(String username, String password) 
	{
		_username = username;
		_password = password;
	}
	
	/**
	 * Returns the username
	 * @return
	 */
	public String getUsername() 
	{
		return _username;
	}
	
	/**
	 * Returns the password
	 * @return
	 */
	public String getPassword() 
	{
		return _password;
	}
}
