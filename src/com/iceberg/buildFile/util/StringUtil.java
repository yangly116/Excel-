package com.iceberg.buildFile.util;


import java.util.ArrayList;
import java.util.List;

import com.iceberg.buildFile.entity.Fdlk;
import com.iceberg.buildFile.entity.Field;

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
		if(isEmpty(type)){
			return "";
		}
		String first = type.substring(0,1);
		String pfix = "";
		if(type.length()>1){
			pfix = type.substring(1,type.length());
		}
		if("D".equalsIgnoreCase(first)){
			return "DATE";
		}else if("C".equalsIgnoreCase(first)){
			return "NVARCHAR2"+"("+pfix+")";
		}else if("N".equalsIgnoreCase(first)){
			if("".equalsIgnoreCase(pfix)){
				return "NUMBER";
			}else{
				return "NUMBER"+"("+pfix.replace(".", ",")+")";
			}
		}else if("I".equalsIgnoreCase(first)){
			return "INTEGER";
		}
		return "";
	}
	public static String getFdlk(Field field){
		Fdlk fdlk = CastUtil.FieldToFdlk(field);
		StringBuffer stringBuffer = new StringBuffer();
		if(fdlk.getType() != null){
			stringBuffer.append("\"type\":\""+fdlk.getType()+"\","+"\r\n");
		}
		if(fdlk.getRequired() != null){
			stringBuffer.append("      \"required\":\""+fdlk.getRequired()+"\","+"\r\n");
		}
		if(fdlk.getLength() != null){
			stringBuffer.append("      \"length\":"+fdlk.getLength()+","+"\r\n");
		}
		if(fdlk.getDateformat() != null){
			stringBuffer.append("      \"dateformat\":\""+fdlk.getDateformat()+"\","+"\r\n");
		}
		return subLast(stringBuffer);
	}
	/**
	 * @Description:如果有最后一个","，则去掉
	 * @param stringBuffer
	 * @return
	 * @author 作者 杨文培
	 * @since：2017年7月27日 下午12:40:41
	 */
	public static String subLast(StringBuffer stringBuffer){
		if(stringBuffer == null || isEmpty(stringBuffer.toString())){
			return null;
		}
		String str = stringBuffer.toString().trim();
		String lastStr = str.substring(str.length()-1,str.length());
		if(",".equals(lastStr)){
			str = str.substring(0,str.length()-1);
		}
		return str;
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
		if("Y".equalsIgnoreCase(first)){
			return "";
		}else{
			return "NOT NULL";
		}
	}
	public static String getFieldExtAttr(String type){
		if(isEmpty(type)){
			return "";
		}
		return type;
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
	/**
	 * 
	 * @param lStrings
	 * @return
	 */
	public static String splitList(List<String> lStrings){
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < lStrings.size(); i++) {
			builder.append(lStrings.get(i)+",");
		}
		String str = builder.toString();
		str = str.substring(0, str.length()-1);
		return str;
	}
}
