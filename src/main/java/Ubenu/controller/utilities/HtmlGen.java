package Ubenu.controller.utilities;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.core.io.ClassPathResource;

public class HtmlGen {
	
	public static Element getElement(String path, String element) throws IOException {
		File htmlBase = new ClassPathResource(path).getFile();
		Document doc = Jsoup.parse(htmlBase, "UTF-8");
		return doc.selectFirst(element);
	}
	
	public static Document getDocument(String path) throws IOException {
		File htmlBase = new ClassPathResource(path).getFile();
		return Jsoup.parse(htmlBase, "UTF-8");

	}
	
	public static void fillElement(Element parent, Element...children ) {
		for (Element e : children) {
			parent.appendChild(e);
		}
	}


}
