package org.ack.crawler.pojo;

import java.io.Serializable;
import java.util.List;

public class Book implements Serializable{
	
	private static final long serialVersionUID = -2661893359032827838L;
	
	private String bookName;                     // 书名
	private String author;                       // 作者
	private String status;                       // 状态
	private String introduction;                 // 简介
	private List<Section> sections;              // 章节

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

}
