import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;


public class Parser {
    private String st_origDoc, st_source, st_med, st_term, st_content, st_name;

    public Parser() {


    }

    public String getSt_origDoc() {
        return st_origDoc;
    }

    public void setSt_origDoc(String st_origDoc) {
        this.st_origDoc = st_origDoc;
    }

    public String getSt_parsedDoc() {
        File f = new File(getSt_origDoc());
        Document doc = Jsoup.parse("http://www.google.com");
        if (f.isFile()) {
            try {
                doc = Jsoup.parse(f, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            doc = Jsoup.parse(getSt_origDoc(), "UTF-8");
        }


        Element body = doc.body();

        Elements links = body.getElementsByAttribute("href");

        for (Element link : links) {
            if (link.text().contains("?")) {
                String URL = link.attr("href").concat("&" + getBuiltURL());
                link.attr("href", URL);
            } else {
                String URL = link.attr("href").concat("?" + getBuiltURL());
                link.attr("href", URL);
            }
        }

        return doc.html();
    }

    public String getSt_source() {
        return st_source;
    }

    public void setSt_source(String st_source) {
        this.st_source = st_source;
    }

    public String getSt_med() {
        return st_med;
    }

    public void setSt_med(String st_med) {
        this.st_med = st_med;
    }

    public String getSt_term() {
        return st_term;
    }

    public void setSt_term(String st_term) {
        this.st_term = st_term;
    }

    public String getSt_content() {
        return st_content;
    }

    public void setSt_content(String st_content) {
        this.st_content = st_content;
    }

    public String getSt_name() {
        return st_name;
    }

    public void setSt_name(String st_name) {
        this.st_name = st_name;
    }

    private String getBuiltURL() {
        String builtURL = "";
        if (!st_source.isEmpty()) {
            builtURL += "utm_source=" + getSt_source();
        }
        if (!st_med.isEmpty()) {
            builtURL += "&utm_medium=" + getSt_med();
        }
        if (!st_term.isEmpty()) {
            builtURL += "&utm_term=" + getSt_term();
        }
        if (!st_content.isEmpty()) {
            builtURL += "&utm_content=" + getSt_content();
        }
        if (!st_name.isEmpty()) {
            builtURL += "&utm_campaign=" + getSt_name();
        }
        return builtURL;
    }


}
