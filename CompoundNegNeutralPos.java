import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.algorithmia.APIException;
import com.algorithmia.AlgorithmException;
import com.algorithmia.Algorithmia;
import com.algorithmia.AlgorithmiaClient;
import com.algorithmia.algo.AlgoResponse;
import com.algorithmia.algo.Algorithm;
import com.algorithmia.*;
import com.algorithmia.algo.*;

public class CompoundNegNeutralPos {

	public static void main( String[] args ) throws APIException, AlgorithmException
    {
    	AlgorithmiaClient client = Algorithmia.client("simEPCftofHUh/ww05K6Pl+8ZAD1");
    	Algorithm algo = client.algo("nlp/SocialSentimentAnalysis/0.1.4");
    	
    	String fileName = "C:\\Users\\hp\\Desktop\\data science\\project\\test.txt"; //name of file to open
        String line = null;        // This will reference one line at a time
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                //
            	line = line.replaceAll("\"", "");
            	String input = "{"
            			 + "  \"sentence\": \""+line+"\""
            			 + "}";
                AlgoResponse result = algo.pipeJson(input);
                System.out.println(result.asJsonString());
                //
                
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }

    }
	
	
}
