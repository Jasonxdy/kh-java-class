package crawlerTest;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

	public static void main(String[] args) {
		
		String url = "https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=%EC%82%AC%EA%B3%BC";
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements element = doc.select("div.group_item");
		
		System.out.println("============================================================");
		Iterator<Element> ie1 = element.select("div.tit").iterator();
		Iterator<Element> ie2 = element.select("div.price").iterator();
		
		while(ie1.hasNext()) {
			System.out.println(ie1.next().text() + "\t" +ie2.next().text());
		}
		System.out.println("============================================================");
	}

}
