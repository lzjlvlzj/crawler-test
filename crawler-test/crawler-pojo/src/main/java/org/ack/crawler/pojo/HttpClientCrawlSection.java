package org.ack.crawler.pojo;

import java.io.Serializable;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * HttpClient抓取章节抽象
 * 
 * @author ack
 *
 */
public class HttpClientCrawlSection extends Section implements Serializable,
		Delayed {

	private static final long serialVersionUID = 1468643253637464350L;

	private long delayTime;

	public HttpClientCrawlSection(long delayTime) {
		this.delayTime = delayTime;
	}
	public HttpClientCrawlSection() {
		this.delayTime = 300000L;
	}

	@Override
	public int compareTo(Delayed o) {
		if (null == o || !(o instanceof HttpClientCrawlSection)) {
			return -1;
		}
		HttpClientCrawlSection hc = (HttpClientCrawlSection) o;
		int n0 = getSectionUrlNumber(this.getUrl());
		int n1 = getSectionUrlNumber(hc.getUrl());
		if (n0 > n1) {
			return -1;
		} else if (n0 == n1) {
			return 0;
		} else {
			return 1;
		}
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit
				.convert(delayTime - System.nanoTime(), TimeUnit.NANOSECONDS);
	}

	/**
	 * 获得章节url的数字
	 * 
	 * @param url
	 * @return
	 */
	public int getSectionUrlNumber(String url) {
		int p = url.indexOf(".");
		int start = (url.indexOf("/") == 0 ? 1 : 0);
		String n = url.substring(start, p);
		return Integer.parseInt(n);
	}
}
