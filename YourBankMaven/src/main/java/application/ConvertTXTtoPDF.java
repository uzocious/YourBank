package application;

import com.convertapi.client.Config;
import com.convertapi.client.ConvertApi;
import com.convertapi.client.Param;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;

/**
 * An API class that converts TEXT documents into PDF documents
 * @author Uzoma Oseji - 1715756
 */
public class ConvertTXTtoPDF
{
	/**
	 * Starts conversion
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException
    {
    	// Converting text file to pdf using Convert API
    	System.out.println("Converting txt...");
    	
    	File textFilePath = new File("SurveyResults.txt");
		File newPDFfilePath = new File("../YourBankServer/src/application/filePDF");
    	
		if (textFilePath.exists())
		{
			Config.setDefaultSecret("jPcNVpWHyuD4iiVk");
			
	    	ConvertApi.convert("txt", "pdf",
	    	    new Param("File", Paths.get(textFilePath.toURI())),
	    	    new Param("FileName", "SurveyResults.pdf")
	    	).get().saveFilesSync(Paths.get(newPDFfilePath.toURI()));
	    	
	    	System.out.println("Complete.");
		}
		else
		{
			System.out.println("Conversion error.");
			System.out.print( textFilePath + " can not be found.");
		}
        
        
        
    }
}
