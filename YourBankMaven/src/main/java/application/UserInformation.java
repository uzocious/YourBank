package application;

import com.convertapi.client.Config;
import com.convertapi.client.ConvertApi;
import com.convertapi.client.model.User;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Retrieve user information
 * @author Uzoma Oseji - 1715756
 */
public class UserInformation 
{
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException 
    {
        Config.setDefaultSecret("jPcNVpWHyuD4iiVk");
        User user = ConvertApi.getUser();

        System.out.println("API Key: " + user.ApiKey);
        System.out.println("Secret: " + user.Secret);

    }
}