package org.ack.crawler.hct;

import static org.junit.Assert.*;

import org.junit.Test;

public class HttpClientCrawlerTest {

	@Test
	public void testGetMethod() {
	   String url = "http://www.qu.la/book/250/194065.html";
	   url = "http://www.qu.la/book/14";
	   HttpClientCrawler hcc = new HttpClientCrawler();
	   hcc.getMethod(url);
	}
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
