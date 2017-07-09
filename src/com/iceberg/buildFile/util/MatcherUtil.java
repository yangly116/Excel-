package com.iceberg.buildFile.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

/**
 * 字符串匹配工具类
 * 
 * @author 作者 E-mail:1164180794@qq.com
 * @version 创建时间：2016年11月18日 上午12:15:43 </br>
 */
public class MatcherUtil {
	public static boolean isIndexOf(String tag) {
		// String tag = "[split/]";
		String regex = "\\[split\\s{1,}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(tag);
		return matcher.find();
	}

	/**
	 * 获取Id属性
	 * 
	 * @param source
	 * @param attrName
	 * @return
	 */
	public static String getIdContent(String source, String attrName) {
		String resultStr = null;
		// String source = "[split id =\"field\"]";
		String regex = "\\s{0,}" + attrName + "\\s{0,}=\"[a-zA-Z0-9-_.]{0,}\"";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		if (matcher.find()) {
			String regStr = matcher.group();// 匹配到的字符串
			int indexStart = regStr.indexOf("\"");
			int indexEnd = regStr.indexOf("\"", indexStart + 1);
			resultStr = regStr.substring(indexStart + 1, indexEnd).trim();
			if (resultStr == null) {
				return null;
			}
		}
		return resultStr;
	}

	/**
	 * map.put("start", start);//匹配到字符串的开始位置</br>
	 * map.put("end", end);//匹配到字符串的结束位置</br>
	 * map.put("regStr", regStr);//匹配到字符串前后0个或多个字符</br>
	 * 
	 * @param str
	 * @return lMaps
	 * 
	 */
	public List<Map<String, Object>> searchString(String resource, String searchStr) {
		int regLong = 10;// 匹配字符串前后10个字符
		List<Map<String, Object>> lMaps = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		String reg = ".{0," + regLong + "}" + searchStr + ".{0," + regLong + "}";
		Pattern pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);// 忽略大小写
		Matcher matcher = pattern.matcher(resource);
		while (matcher.find()) {
			map = new HashMap<String, Object>();
			String regStr = matcher.group();// 匹配到的字符串
			int start = matcher.start();
			int end = matcher.end();
			map.put("start", start);
			map.put("end", end);
			map.put("regStr", regStr);
			lMaps.add(map);
		}
		return lMaps;
	}

	/**
	 * 查询txt文件
	 * 
	 * @param file
	 * @param searchStr
	 * @return
	 * @throws IOException
	 */
	public List<Map<String, Object>> searchTextFile(File file, String searchStr) throws IOException {
		String resource = FileUtils.readFileToString(file);
		MatcherUtil matcherUtil = new MatcherUtil();
		return matcherUtil.searchString(resource, searchStr);
	}

	public static Map<String, String> getTemplateField(File templateFile) {
		StringBuilder builder = null;
		Stack<String> stack = new Stack<>();
		Map<String, String> map = new HashMap<>();
		try {
			List<String> list = FileUtils.readLines(templateFile);
			String top = null;
			for (int i = 0; i < list.size(); i++) {
				String strLine = list.get(i);
				if (isStart(strLine)) {// 判断开始标签
					top = MatcherUtil.getIdContent(strLine, "id");
					stack.push(top);
					builder = new StringBuilder();
				}
				if (top != null && builder != null) {
					if (!isStart(strLine) && !isEnd(strLine)) {
						builder.append(strLine + "\r\n");
					}
				}
				if (isEnd(strLine)) {// 判断结束标签
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
		return map;
	}
	public static boolean isStart(String tag){
		return MatcherUtil.isIndexOf(tag);
	}
	public static  boolean isEnd(String tag){
		boolean boo = false;
		int index = tag.indexOf("[split/]");
		if(index != -1){
			boo = true;
		}
		return boo;
	}
}
