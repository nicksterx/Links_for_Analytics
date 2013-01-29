import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Parser{
    /**
     * Parser handles the input of an HTML Document parses and then puts the output into the save file.
     * @param htmlDoc    Original Document
     * @param st_source  Source
     * @param st_med     Medium
     * @param st_term    Term
     * @param st_content Content Type (QR Code, Facebook, Bit.ly)
     * @param st_name    Name
     * @param Save       Document to save the parsed information
     */
	public Parser(File htmlDoc, String st_source, String st_med, String st_term, String st_content, String st_name, File Save) {
		String builtURL="";
		if(!st_source.isEmpty()){
			builtURL += "utm_source="+st_source;
		}
		if(!st_med.isEmpty()){
			builtURL += "&utm_medium="+st_med;
		}
		if(!st_term.isEmpty()){
			builtURL += "&utm_term="+st_term;
		}
		if(!st_content.isEmpty()){
			builtURL += "&utm_content="+st_content;
		}
		if(!st_name.isEmpty()){
			builtURL += "&utm_campaign="+st_name;
		}
		
		try {
			Document doc = Jsoup.parse(htmlDoc, "UTF-8", "http://www.affordable-solar.com");
			Element body = doc.body();

            Elements links = body.getElementsByAttribute("href");
						
			for(Element link : links){
				if(link.text().contains("?")){
					String URL = link.attr("href").concat("&"+builtURL);
                    link.attr("href", URL);
				}
				else{
					String URL = link.attr("href").concat("?"+builtURL);
					link.attr("href", URL);
				}
			}

			PrintWriter save = new PrintWriter(Save,"UTF-8");
			save.write(doc.html());
			save.flush();
			save.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
	}
}
