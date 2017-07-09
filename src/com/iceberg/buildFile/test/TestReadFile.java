package com.iceberg.buildFile.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.iceberg.buildFile.util.MatcherUtil;


/** 
 * 
 * @author 作者 E-mail:1164180794@qq.com 
 * @version 创建时间：Jul 8, 2017 10:32:39 PM  
 */
public class TestReadFile {
	public static File sourceFile = new File(TestIbatisWork.class.getResource("/table/table.xml").getFile());
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		StringBuilder builder = null;
		Stack<String> stack = new Stack<>();
		Map<String, String> map = new HashMap<>();
		try {
			List<String> list = FileUtils.readLines(sourceFile);
			String top = null;
			for (int i = 0; i < list.size(); i++) {
				String strLine = list.get(i);
				if(isStart(strLine)){//判断开始标签
					top = MatcherUtil.getIdContent(strLine, "id");
					stack.push(top);
					builder = new StringBuilder();
				}
				if(top != null && builder!=null){
					if(!isStart(strLine)&&!isEnd(strLine)){
						builder.append(strLine+"\r\n");
					}
				}
				if(isEnd(strLine)){//判断结束标签
					stack.pop();
					map.put(top, builder.toString());
					builder = new StringBuilder();
					top = null;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(map);
	}
	public static String getSplitId(String tag){
		String splitId = "";
		return splitId;
	}
	public static boolean isStart(String tag){
		return MatcherUtil.isIndexOf(tag);
	}
	public static boolean isEnd(String tag){
		boolean boo = false;
		int index = tag.indexOf("[split/]");
		if(index != -1){
			boo = true;
		}
		return boo;
	}
	public static void main(String[] args) {
		test2();
	}
	public static void test2(){
		String tag = "[split/]";
		String regex = "\\[split\\s{1,}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(tag);
		if(matcher.find()){
			
		}
	}
	public static void test1(){
		String attrName = "id";
		String source = "[split id  =\"field\"]";
		//String regex = "^("+attrName+"=\"field\")$";
		String regex = "\\s{0,}"+attrName+"\\s{0,}=\"[a-zA-Z0-9-_.]{0,}\"";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		if(matcher.find()){
			String regStr = matcher.group();//匹配到的字符串
			System.out.println("HH:"+regStr);
			int indexStart = regStr.indexOf("\"");
			int indexEnd = regStr.indexOf("\"",indexStart+1);
			String resultStr = regStr.substring(indexStart+1, indexEnd);
			System.out.println(resultStr);
		}
	}
}
