package org.ack.crawler.content.proccess.filter;

/**
 * 过来掉不需要字符
 * 
 * @author ack
 *
 */
public class StringFilter {

	/**
	 * 只过滤节点
	 * 
	 * @param content
	 * @param node
	 * @return
	 */
	public String filterOnlyNode(String content, String node) {
		String start = "<" + node + ">";
		String end = "</" + node + ">";
		return content.replaceAll(start, "").replaceAll(end, "");
	}

	/**
	 * 过滤节点及其内部内容
	 * 
	 * @param content
	 * @param node
	 * @return
	 */
	public String filterNode(String content, String node) {
		String start = "<" + node + ">";
		String end = "</" + node + ">";
		int len = end.length();
		StringBuffer sb = new StringBuffer();
		int startIndex = content.indexOf(start);
		int endIndex = content.indexOf(end);
		if (startIndex < 0 || endIndex < 0) {
			return content;
		}
		for (int i = 0; i < content.length(); i++) {
			char c = content.charAt(i);
			if (i >= startIndex && i < endIndex + len) {
				continue;
			}
			sb.append(c);
		}
		content = sb.toString();
		return filterNode(content, node);
	}

	/**
	 * 过滤空白字符
	 * 
	 * @param content
	 * @param node
	 * @return
	 */
	public String filterBlank(String content) {
		String regex = "&nbsp;";
		return content.replaceAll(regex, "");
	}
}
