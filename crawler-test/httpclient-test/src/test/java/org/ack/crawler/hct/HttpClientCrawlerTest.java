package org.ack.crawler.hct;

import static org.junit.Assert.*;

import org.junit.Test;

public class HttpClientCrawlerTest {

	@Test
	public void testGetMethod() {
	   String url = "http://www.qu.la/book/250/194065.html";
	   url = "http://www.biquge.com/19_19648/";
	   HttpClientCrawler hcc = new HttpClientCrawler(null);
	   hcc.getMethod(url);
	}
	
	@Test
	public void testGetOneSection() {
		String url = "http://www.biquge.com/19_19648/1871525.html";
		HttpClientCrawler hcc = new HttpClientCrawler(null);
		String content = hcc.GetOneSection(url);
		System.out.println(content);
	}
	
	@Test
	public void testGetHost() {
		HttpClientCrawler hcc = new HttpClientCrawler(null);
		String url = "http://www.biquge.com/19_19648/";
		String host = hcc.getHostUrl(url);
		System.out.println(host);
	}
	
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
