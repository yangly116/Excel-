package com.iceberg.buildFile.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/** 
 * 
 * @author 作者：杨文培 E-mail:1164180794@qq.com 
 * @version 创建时间：Jul 6, 2017 10:17:46 PM  
 */
public class StringUtil {
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
		return str;
	}
	public static String getFieldType(String type){
		String first = type.substring(0,1);
		String pfix = "";
		if(type.length()>1){
			pfix = type.substring(1,type.length());
		}
		Map<String, String> map = new HashMap<>();
		map.put("D", "DATE");
		map.put("C", "NVARCHAR2");
		map.put("N", "NUMBER");
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
		}
		return "";
	}
}
