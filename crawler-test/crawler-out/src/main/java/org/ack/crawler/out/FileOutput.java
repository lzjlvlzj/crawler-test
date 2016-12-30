package org.ack.crawler.out;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.ack.crawler.out.util.FileUtil;

/**
 * @author ack
 *
 */
public class FileOutput {

	/**
	 * 输出到txt
	 */
	public void out2Txt(String s, File file) {
		FileWriter fileWritter = null;
		BufferedWriter bufferWritter = null;
		try {
			fileWritter = new FileWriter(file, true);
			bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.newLine();
			bufferWritter.write(s);
			bufferWritter.flush();
			bufferWritter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * 输出到txt
	 */
	public void out2Txt(String s, String outputPath) {
		File file = FileUtil.createFile(outputPath);
		out2Txt(s,file);
	}
}
