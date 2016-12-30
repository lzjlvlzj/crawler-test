package org.ack.crawler.content.proccess.filter;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringFilterTest {

	@Test
	public void filterOnlyNode() {
		String node = "script";
		String content = "<script>readx();</script>&nbsp;&nbsp;&nbsp;&nbsp;第1章高三开学";
		StringFilter sf = new StringFilter();
		content = sf.filterOnlyNode(content, node);
		System.out.println(content);
	}
	
	@Test
	public void filterNode() {
		String node = "script";
		String content = "<script>readx();</script>&nbsp;&nbsp;&nbsp;&nbsp;第1章高三开学<script>readx22();</script>";
		StringFilter sf = new StringFilter();
		content = sf.filterNode(content, node);
		System.out.println(content);
	}
	
	@Test
	public void test2() {
		String content = "<script>readx();</script>&nbsp;&nbsp;&nbsp;&nbsp;第1章高三开学";
		content = content.replaceAll("script", "");
		System.out.println(content);
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
