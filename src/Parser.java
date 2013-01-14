import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Parser {
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
			doc.outputSettings().charset("UTF-8");
			Elements links = body.select("a[href]");
			
			for(int i = 0; i<links.size(); i++){
				String link = links.get(i).attr("href");
				if(link.contains("?")){
					links.set(i,links.get(i).attr("href",link+"&"+builtURL));
				}else{
					links.set(i,links.get(i).attr("href",link+"?"+builtURL));					
				}
			}
			PrintWriter save = new PrintWriter(Save,"UTF-8");
			save.write(doc.html());
			save.flush();
			save.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
}
