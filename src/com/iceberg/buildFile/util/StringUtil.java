package com.iceberg.buildFile.util;


import java.util.ArrayList;
import java.util.List;

/** 
 * 
 * @author 作者：杨文培 E-mail:1164180794@qq.com 
 * @version 创建时间：Jul 6, 2017 10:17:46 PM  
 */
public class StringUtil {
	public static boolean isEmpty(String str){
		boolean boo = false;
		if(str == null || "".equals(str.trim())){
			boo = true;
		}
		return boo;
	}
	public static List<String> splitToList(String string,String regex) {
		if(string == null){
			return new ArrayList<String>();
		}
		List<String> list = new ArrayList<String>();
		String[] strings = string.split(regex);
		for (int i = 0; i < strings.length; i++) {
			list.add(strings[i]);
		}
		return list;
	}
	public static String subFix(String str){
		str = str.substring(0, str.length()-1);
		return "	  "+str;
	}
	public static String replaSubFix(String str){
		str = str.substring(0, str.length()-1);
		return "	  "+str;
	}
	public static String getFieldType(String type){
		if(type == null){
			return "";
		}
		String first = type.substring(0,1);
		String pfix = "";
		if(type.length()>1){
			pfix = type.substring(1,type.length());
		}
		if("D".equals(first)){
			return "DATE";
		}else if("C".equals(first)){
			return "NVARCHAR2"+"("+pfix+")";
		}else if("N".equals(first)){
			if("".equals(pfix)){
				return "NUMBER";
			}else{
				return "NUMBER"+"("+pfix.replace(".", ",")+")";
			}
		}else if("I".equals(first)){
			return "INTEGER";
		}
		return "";
	}
	public static String getFieldIsNull(String type){
		if(isEmpty(type)){
			return "NOT NULL";
		}
		String first = type.substring(0,1);
		String pfix = "";
		if(type.length()>1){
			pfix = type.substring(1,type.length());
		}
		if("Y".equals(first)){
			return "";
		}else{
			return "NOT NULL";
		}
	}
	/**
	 * 获取文件名后缀 例如: aa.txt  返回 .txt
	 * @param fileName
	 * @return
	 */
	public static String getFileNamePfix(String fileName){
		int index = fileName.lastIndexOf(".");
		String fixName = fileName.substring(index, fileName.length());
		return fixName;
	}
}
