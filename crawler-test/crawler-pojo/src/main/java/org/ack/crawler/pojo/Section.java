package org.ack.crawler.pojo;

/**
 * 要抓取的对象
 * 
 * @author ack
 *
 */
public abstract class Section  {
	private String title;          // 章节标题
	private String content;        // 章节内容
	private String url;            // 章節url
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
