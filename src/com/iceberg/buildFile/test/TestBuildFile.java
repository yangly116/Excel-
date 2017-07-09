package com.iceberg.buildFile.test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.iceberg.buildFile.main.Setting;
import com.iceberg.buildFile.util.BFileUtil;
import com.iceberg.buildFile.util.ParmUtil;
import com.iceberg.buildFile.util.StringUtil;

/** 
 * 
 * @author 作者 E-mail:1164180794@qq.com 
 * @version 创建时间：Jul 6, 2017 9:27:32 PM  
 */
public class TestBuildFile {
	public static void main(String[] args) {
		TestBuildFile testBuildFile = new TestBuildFile();
		testBuildFile.test2();
	}
	public void test1() {
		File ibatis = new File(TestBuildFile.class.getResource("/ibatis.xml").getFile());
		File ibatis2 = new File(TestBuildFile.class.getResource("/ibatis2.xml").getFile());
		File table = new File(TestBuildFile.class.getResource("/table.txt").getFile());
		File baseFile = new File(TestBuildFile.class.getResource("/").getFile());
		System.out.println("path:"+TestBuildFile.class.getResource("/").getFile());
		try {
			String tableStr = FileUtils.readFileToString(table);
			System.out.println("tableStr:\r\n"+tableStr);
			List<String> stables = StringUtil.splitToList(tableStr, ",");
			String fileName = "ERPC_WST.txt";
			BFileUtil.clearFile(fileName);
			for (int i = 0; i < stables.size(); i++) {
				String field = stables.get(i);
				String nodeStart = FileUtils.readFileToString(ibatis);
				String successStr = ParmUtil.replaceparm(nodeStart, field, "Properties");
				BFileUtil.write(fileName, successStr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void test2() {
		File ibatis = new File(TestBuildFile.class.getResource("/ibatis/ibatis.xml").getFile());
		try {
			String source = FileUtils.readFileToString(ibatis, Setting.encoding);
			//System.out.println("source:\r\n"+source);
			List<String> lStrings = StringUtil.splitToList(source, "\\[split\\]");
			for (String string : lStrings) {
				System.out.println("===start======");
				System.out.println(string);
				System.out.println("===end======");
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
