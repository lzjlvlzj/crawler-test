package org.ack.crawler.pojo;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.concurrent.DelayQueue;

import org.junit.Test;

public class HttpClientCrawlSectionTest {

	@Test
	public void test() {
		DelayQueue<HttpClientCrawlSection>  dq=new DelayQueue<HttpClientCrawlSection>();
		
		HttpClientCrawlSection hcc1 = new HttpClientCrawlSection(0);
		HttpClientCrawlSection hcc2= new HttpClientCrawlSection(3);
		HttpClientCrawlSection hcc3 = new HttpClientCrawlSection(1);
		HttpClientCrawlSection hcc4 = new HttpClientCrawlSection(4);
		
		hcc1.setUrl("4783540.html");
		hcc2.setUrl("4783542.html");
		hcc3.setUrl("4783541.html");
		hcc4.setUrl("4783543.html");
		
		dq.add(hcc1);
		dq.add(hcc2);
		dq.add(hcc3);
		dq.add(hcc4);
		
		Iterator<HttpClientCrawlSection> it = dq.iterator();
		while(it.hasNext()){
			HttpClientCrawlSection hcc = it.next();
			String url = hcc.getUrl();
			System.out.println(url);
		}
		
		
		
		
		
	}
	@Test
	public void test1() {
		fail("Not yet implemented");
	}

}
