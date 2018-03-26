
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
	  String APIkey = "6cfb3744a73c491bb6cc148943498e04";
	//  String APIkey = "a175e84ea7b54185862d3f98983ef4cb";
	//  String APIkey = "9294d52586a44086985dde97166d8450";
	//  String APIkey = "3a8c244c245545c0a496720ad19b8181";
	//  String APIkey = "b903c389595a45bb9460230e45b4dcb2";	  
	//  String APIkey = "50f924172e154d31b941f1de0160ecba";
	//  String APIkey = "040aed5f75bc44aa984b8c0b1e32f12c";
	//  String APIkey = "3963d975db2844c09a3cd2868fc293e0";
	//  String APIkey = "298415a17d4548518d613fe674c0075d";
    //  String APIkey = "774f898a3fc045f79b6087eb44d21f1a";
	//   String APIkey = "433d2860fd0448dd8e08b2d675ebac25";
	//    String APIkey = "333479159f054785b9c7f8e30bb4fbb1";
	//    String APIkey = "0867477a35ff48239e2a3994e74b401c";
	//    String APIkey = "ec08e80bdb7b449fbaeec3928caae792";
	//    String APIkey = "b923b57046e148bbb2799d4627c5097f";
	//    String APIkey = "633872ff6c2640ea85940af0e8a19dd5";
	//   String APIkey = "8d36ae417b45405896a9b272bffb48c7";
	//    String APIkey = "514b5651dd0146a287eff3bde72cca8a";
	//    String APIkey = "8203ea615c524f40815b3a9632bae813";
	//    String APIkey = "e8eec172d82946ec989e66e578dfb7a5";
	//   String APIkey = "afc84a55b95a445db17ac45b5d13a62d";
	  

	  // change the year XXXX
	  String year = "2018";
	  // change the month XX
	  String month = "03";
	  
	  // change the days per month XX
	//  int daysInMonth = 31;
	  // Jan 31, feb 28, march 31, april 30, may 31, june 30, july 31, aug 31, sept 30, oct 31, nov 30, dec  31
	  
	  String begin_date ="";
	  String end_date = "";
      //write to this file
      PrintWriter writer = new PrintWriter("C:\\Users\\hp\\Desktop\\data science\\project\\NYTScraping.txt", "UTF-8");

	//  for (int day = 1; day < daysInMonth+1; day++) {
// System.out.println("Day: "+day);

		//  begin_date = year+month+"0"+day;
		//  end_date = year+month+"0"+day;
		  begin_date = year+month+"01";
		  end_date = year+month+"31";
		  
//		  if (day>9) {
//			begin_date= year+month+day;
//		//	end_date= year+month+day;
//		}
		  
	  for (int pageNumber = 1; pageNumber < 150; pageNumber++) {
		  System.out.println("page: "+pageNumber);
		    json = readJsonFromUrl("https://api.nytimes.com/svc/search/v2/articlesearch.json?api-key="+APIkey+"&begin_date="+begin_date+"&end_date="+end_date+"&fl=snippet,headline&page="+pageNumber);
		    // System.out.println(json.toString());
		   //  System.out.println(json.get("response"));
		     response = (JSONObject) json.get("response");
		     System.out.println(response.get("docs"));
             writer.println(response.get("docs"));
		     
		     //sleep
		     try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		     //
		     		
	}

	  
//	}//end outer for

System.out.println("done");
    
    
  }
}