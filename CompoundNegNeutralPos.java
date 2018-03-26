
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CompoundNegNeutralPos {

	public static void main( String[] args ) throws APIException, AlgorithmException, FileNotFoundException
    {
    	//AlgorithmiaClient client = Algorithmia.client("simEPCftofHUh/ww05K6Pl+8ZAD1");
    	//AlgorithmiaClient client = Algorithmia.client("sim8wKXmQyPubl//6vw7KptC/EK1");
    	// AlgorithmiaClient client = Algorithmia.client("sim1oOYSb/5fRqav0CidbGofGNx1");
    	// AlgorithmiaClient client = Algorithmia.client("simYKTMo3VdL3tG/PeoB5et5WKb1");
    	// AlgorithmiaClient client = Algorithmia.client("simFrAkSME7Dg8uDBVs6wi3e+4v1");
    	// AlgorithmiaClient client = Algorithmia.client("simg6lqY2P51AyW5MTuluu8TxMS1");
    	 AlgorithmiaClient client = Algorithmia.client("simAb0Gm0qaYYHc4UuQ9Mtxv+pu1");
    	// AlgorithmiaClient client = Algorithmia.client("simyWmu2G2/Q6/nhtWU/qrTAL7q1");
    	// AlgorithmiaClient client = Algorithmia.client("simrz3fcDU9/OH96+043qZxQbOl1");
    	// AlgorithmiaClient client = Algorithmia.client("sims3tooYDAcNc+FyHtt/RvoZcJ1");
    	// AlgorithmiaClient client = Algorithmia.client("sim40Lv7lcWNTwmuVoA94KHfxcp1");
    	// AlgorithmiaClient client = Algorithmia.client("simKcGC4rOcQ+hC52CDE/Xr+HRD1");
    	// AlgorithmiaClient client = Algorithmia.client("simdzhcAf+vM8b7uTpfD6x3Kxq71");
    	// AlgorithmiaClient client = Algorithmia.client("simfdk/2/frbf0u1J1MsMLlTZed1");
    	// AlgorithmiaClient client = Algorithmia.client("simq956z3cxsTSyStFfg5IuHOwI1");
    	// AlgorithmiaClient client = Algorithmia.client("simVoMnWs2S8EYgSo/rUX/7w/YR1");
    	// AlgorithmiaClient client = Algorithmia.client("simTlSqzK5Wwhqex2uTL9m9pBzJ1");
        // AlgorithmiaClient client = Algorithmia.client("simA56hXU2FhnjMhejWQYJpG2jv1");
    	// AlgorithmiaClient client = Algorithmia.client("sim9O2ZkFSFG7SncYMHeYaCtpP+1");
    	 
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

         //   String superString = "";
            
            //write to this file
            PrintWriter writer = new PrintWriter("C:\\Users\\hp\\Desktop\\data science\\project\\testResult.txt", "UTF-8");


            
            while((line = bufferedReader.readLine()) != null) {
                //
            	line = line.replaceAll("\"", "");
            	line = line.replaceAll("'", "");
            	String input = "{"
            			 + "  \"sentence\": \""+line+"\""
            			 + "}";
System.out.println(input);
                AlgoResponse result = algo.pipeJson(input);


                writer.println(result.asJsonString());

            }   
            writer.close();            

System.out.println("done");
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
             ex.printStackTrace();
        }

    }
	
	
}
