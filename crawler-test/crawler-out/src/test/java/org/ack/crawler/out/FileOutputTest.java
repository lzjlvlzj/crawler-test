package org.ack.crawler.out;

import static org.junit.Assert.*;

import org.junit.Test;

public class FileOutputTest {
	
	@Test
	public void testOut() {
		FileOutput fp = new FileOutput();
		String s = "“为什么？”季枫脸色灰白的问道，“慧慧，这是为什么？”     季枫的心一阵绞痛，几乎就要栽倒。     就在两个月前，两人还曾经海誓山盟，说好永不分开，可是，一个暑假过去，他竟然听到了分手的决绝话语，这让他如何心甘？     季枫呆呆的望着面前这个美丽的女孩，心中疼痛难忍。";
		String path = "K:\\test\\crawler-test\\test.txt";
		fp.out2Txt(s, path);
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
