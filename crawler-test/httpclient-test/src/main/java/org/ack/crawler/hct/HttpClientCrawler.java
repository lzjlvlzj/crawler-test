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
	
    public void getMethod(String url){
    	Header[] headers = new Header[8];
    	
    	headers[0] = new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
    	headers[1] = new BasicHeader("Accept-Encoding", "gzip, deflate, sdch");
    	headers[2] = new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8");
    	headers[3] = new BasicHeader("Cache-Control", "max-age=0");
    	headers[4] = new BasicHeader("Connection", "keep-alive");
    	headers[5] = new BasicHeader("Host", "www.qu.la");
    	headers[6] = new BasicHeader("Cookie", "obj=1; bdshare_firstime=1479270000537; PPad_id_PP=1");
    	headers[7] = new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
    	String html = HttpUtil.MethodGet(url, headers);
    	html = changeCharset(html);
    	//System.out.println(html);
    	HtmlParser hp = new DefaultHtmlParser();
    	//获得文件名称
    	String fileName = hp.queryFileName(html, "info");
    	System.out.println("文章名称 :　" + fileName);
    	//获得章节列表url
    	List<String> list = hp.querySectionLists(html);
    	//绝对路径
    	String absoluteFileName = "K:\\test\\crawler-test\\" + fileName + ".txt";
    	FileOutput fp = new FileOutput();
    	System.out.println("总共章节数 : " + list.size() + "章");
    	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        //循环抓取单章
    	for(int i =0; i < list.size(); i++){
    		System.out.println("当前抓取 : 第" + ( i + 1 ) + "章");
    		String sectionUrl = url + "/" + list.get(i);
    		String sectionHtml = HttpUtil.MethodGet(sectionUrl, headers);
    		sectionHtml = changeCharset(sectionHtml);
    		String section = hp.queryOneSection(sectionHtml, "content");
    		fp.out2Txt(section, absoluteFileName);
    	}
    	
    }

	private String changeCharset(String html) {
		try {
    		html = new String(html.getBytes("iso-8859-1"),"gbk");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return html;
	}
}
