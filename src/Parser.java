import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Parser{
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
			Elements links = body.select("a[href]");
						
			for(Element link : links){
				if(link.text().contains("?")){
					String URL = link.attr("href").concat("&"+builtURL);
					//Still doesn't work attr escapes urls
                    link.attr("href",URL);
					System.out.println(link.text());
					link.attr("href", "hey");

				}
				else{
					String URL = link.attr("href").concat("?"+builtURL);
					link.attr("href",URL);
					System.out.println(link.text());
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
