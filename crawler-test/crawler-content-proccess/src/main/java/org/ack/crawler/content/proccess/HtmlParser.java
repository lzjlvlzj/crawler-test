package org.ack.crawler.content.proccess;

import java.util.List;

/**
 * html解析
 * 
 * @author ack
 *
 */
public interface HtmlParser {

	/**
	 * 抓取单章内容
	 * 
	 * @param tg
	 * @param id
	 * @return
	 */
	public String queryOneSection(String tg, String id);

	/**
	 * 获得文章名称
	 * 
	 * @return
	 */
	public String queryFileName(String html, String id);

	/**
	 * 获得章节列表
	 * 
	 * @param html
	 * @return
	 */
	public List<String> querySectionLists(String html);
}
