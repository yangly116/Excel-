package com.iceberg.buildFile.main;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/** 
 * 删除所有生成的文件
 * @author 作者 杨文培 E-mail:1164180794@qq.com 
 * @version 创建时间：Jul 7, 2017 12:05:19 AM  
 */
public class ClearAll {
	public static void test(String[] args) {
		File file = new File(Setting.produceRoot);
		try {
			FileUtils.deleteDirectory(file);
			file.mkdirs();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
