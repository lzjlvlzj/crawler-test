package org.ack.crawler.content.proccess;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 默认解析
 * 
 * @author ack
 *
 */
public class DefaultHtmlParser extends AbstractHtmlParser {

	@Override
	public String queryOneSection(String html, String id) {
		Document doc = Jsoup.parse(html);
		Elements e = doc.getElementsByClass("bookname");
		Element sectionName = e.first().getElementsByTag("h1").first();
		String name = sectionName.text() + System.getProperty("line.separator");
		Element content = doc.getElementById(id);
		String txt = content.text().trim();
		String s = name + txt;
		/*
		StringFilter sf = new StringFilter();
		s = sf.filterBlank(s);
		s = sf.filterOnlyNode(s, "br");
		s = sf.filterNode(s, "script");
		*/
		return s;
	}

	@Override
	public String queryFileName(String html, String id) {
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById(id);
		// <h1>标签
		String s = element.child(0).text();
		return s;
	}

	@Override
	public List<String> querySectionLists(String html) {
		Document doc = Jsoup.parse(html);
		Elements dds = doc.getElementsByTag("dd");
		ListIterator<Element> its = dds.listIterator();
		List<String> list = new ArrayList<String>();
		while(its.hasNext()){
			Element dd = its.next();
			Element a = dd.getElementsByTag("a").first();
			if(null == a){
				continue;
			}
			String url = a.attr("href");
			list.add(url);
		}
		return list;
	}

}
