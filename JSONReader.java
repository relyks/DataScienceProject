import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonReader {

  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }

  public static void main(String[] args) throws IOException, JSONException {
	  JSONObject json;
	  JSONObject response;
	  
	  // change the days per month XX
	  int daysInMonth = 31;
	  // change the year XXXX
	  String year = "2007";
	  // change the month XX
	  String month = "01";
	  
	  int i = 1;

	  String begin_date ="";
	  String end_date = "";
	  
	  for (i = 1; i < daysInMonth+1; i++) {

		  begin_date = year+month+"0"+i;
		  end_date = year+month+"0"+i;
		  
		  if (i>9) {
			begin_date= year+month+i;
			end_date= year+month+i;
		}
		  
	//  for (int pageNumber = 1; pageNumber < 2; pageNumber++) {
		    json = readJsonFromUrl("https://api.nytimes.com/svc/search/v2/articlesearch.json?api-key=6cfb3744a73c491bb6cc148943498e04&begin_date="+begin_date+"&end_date="+end_date+"&fl=snippet,headline&page="+1);
		    // System.out.println(json.toString());
		   //  System.out.println(json.get("response"));
		     response = (JSONObject) json.get("response");
		     System.out.println(response.get("docs"));
		     
		     //sleep
		     try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     //
		     		
//	}
	  
	}//end outer for


    
    
  }
}
