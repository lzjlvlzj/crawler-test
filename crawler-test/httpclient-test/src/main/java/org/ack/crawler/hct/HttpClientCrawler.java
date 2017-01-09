package org.ack.crawler.hct;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.ack.crawler.content.proccess.DefaultHtmlParser;
import org.ack.crawler.content.proccess.HtmlParser;
import org.ack.crawler.hct.util.HttpUtil;
import org.ack.crawler.out.FileOutput;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

public class HttpClientCrawler {

	Header[] headers = null;

	public HttpClientCrawler() {
		headers = new Header[8];
		headers[0] = new BasicHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		headers[1] = new BasicHeader("Accept-Encoding", "gzip, deflate, sdch");
		headers[2] = new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8");
		headers[3] = new BasicHeader("Cache-Control", "max-age=0");
		headers[4] = new BasicHeader("Connection", "keep-alive");
		headers[5] = new BasicHeader("Host", "http://www.biquge.com");
		headers[6] = new BasicHeader(
				"Cookie",
				"obj=1; __cfduid=dd03559650cd1c3d6ddbac513315a1afd1482919440; bdshare_firstime=1482919620121; CNZZDATA1260112074=82626329-1482919005-http%253A%252F%252Fwww.so.com%252F%7C1483594007");
		headers[7] = new BasicHeader(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
	}

	public HttpClientCrawler(Header[] headers) {
		this.headers = headers;
	}

	public void getMethod(String url) {
		String host = getHostUrl(url);
		String html = HttpUtil.MethodGet(url, headers);
		html = changeCharset(html);
		System.out.println(html);
		HtmlParser hp = new DefaultHtmlParser();
		// 获得文件名称
		String fileName = hp.queryFileName(html, "info");
		System.out.println("文章名称 :　" + fileName);
		// 获得章节列表url
		List<String> list = hp.querySectionLists(html);
		// 绝对路径
		String absoluteFileName = "K:\\test\\crawler-test\\" + fileName
				+ ".txt";
		FileOutput fp = new FileOutput();
		System.out.println("总共章节数 : " + list.size() + "章");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 循环抓取单章
		for (int i = 0; i < list.size(); i++) {
			System.out.println("当前抓取 : 第" + (i + 1) + "章");
			String sectionUrl = host + list.get(i);
			String sectionHtml = HttpUtil.MethodGet(sectionUrl, headers);
			sectionHtml = changeCharset(sectionHtml);
			String section = hp.queryOneSection(sectionHtml, "content");
			fp.out2Txt(section, absoluteFileName);
		}

	}

	public String getHostUrl(String url) {
		int n = 0;
		int p = 0;
		for (int i = 0; i < url.length(); i++) {
			char c = url.charAt(i);
			if (n == 3) {
				p = i;
				break;
			}
			if ('/' == c) {
				n++;
			}
		}
		return url.substring(0, p);
	}

	public String GetOneSection(String url) {
		HtmlParser hp = new DefaultHtmlParser();
		String sectionHtml = HttpUtil.MethodGet(url, headers);
		sectionHtml = changeCharset(sectionHtml);
		String section = hp.queryOneSection(sectionHtml, "content");
		return section;
	}

	private String changeCharset(String html) {
		try {
			html = new String(html.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return html;
	}
}
