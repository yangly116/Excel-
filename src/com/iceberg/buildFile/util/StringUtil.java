package com.iceberg.buildFile.util;



import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.iceberg.buildFile.entity.Fdlk;
import com.iceberg.buildFile.entity.Field;
import com.iceberg.buildFile.enums.FdlkMappingEnum;

/** 
 * 
 * @author 作者：杨文培 E-mail:1164180794@qq.com 
 * @version 创建时间：Jul 6, 2017 10:17:46 PM  
 */
public class StringUtil {
	private static Log log = LogFactory.getLog(StringUtil.class);
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
		String resStr = StringUtil.fullFieldType(type);
		if(resStr==null){
			return type;
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
	public static String fullFieldType(String type){
		if(StringUtil.isEmpty(type)){
			return null;
		}
		String regex = "^[I|i|C|c|N|n|D|d]{1}[0-9]{0,100}.{0,1}[0-9]{0,100}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(type);
		while(matcher.find()){
			String matcherStr = matcher.group();
			//System.out.println(matcherStr);
			if (matcherStr.length()!=type.length()) {
				//System.out.println("return null");
				return null;
			}else{
				return type;
			}
		}
		return null;
	}
	public static String fullFieldType_FDLK(String type){
		if(StringUtil.isEmpty(type)){
			return null;
		}
		String regex = "^[I|i|C|c|N|n|D|d]{1}[0-9]{0,100}.{0,1}[0-9]{0,100}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(type);
		while(matcher.find()){
			String matcherStr = matcher.group();
			//System.out.println(matcherStr);
			if (matcherStr.length()!=type.length()) {
				type = mapingFdlk(type);
			}else{
				return type;
			}
		}
		type = mapingFdlk(type);
		return type;
	}
	private static String mapingFdlk(String type){
		String typeR = type.toUpperCase();
		//System.out.println("typeR:"+typeR);
		String regex = "^[INTEGER|NVARCHAR2|VARCHAR2|NUMBER|DATE]{0,100}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(typeR);
		while(matcher.find()){
			String matcherStr = matcher.group();
			//System.out.println("matcherStr:"+matcherStr);
			//log.info("matcherStr:"+matcherStr);
			if(FdlkMappingEnum.getType(matcherStr)!=null){
				typeR = typeR.replaceAll(matcherStr, FdlkMappingEnum.getType(matcherStr).getText());
				typeR = typeR.replaceAll("\\(|\\)","");
				typeR = typeR.replaceAll(",", ".");
			}else{
				log.info("未匹配到的字段："+typeR);
			}
		}
		return typeR;
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
			return "";
		}
		String first = type.substring(0,1);
		String pfix = "";
		if(type.length()>1){
			pfix = type.substring(1,type.length());
		}
		if("Y".equalsIgnoreCase(first)){
			return "";
		}else if("N".equalsIgnoreCase(first)){
			return "NOT NULL";
		}else {
			return "";
		}
	}
	public static String getAlertFieldIsNull(String type){
		if(isEmpty(type)){
			return "";
		}
		String first = type.substring(0,1);
		String pfix = "";
		if(type.length()>1){
			pfix = type.substring(1,type.length());
		}
		if("Y".equalsIgnoreCase(first)){
			return "NULL";
		}else if("N".equalsIgnoreCase(first)){
			return "NOT NULL";
		}else {
			return "";
		}
	}
	public static String getAlertFieldIsN(String type){
		if(isEmpty(type)){
			return "";
		}
		String first = type.substring(0,1);
		String pfix = "";
		if(type.length()>1){
			pfix = type.substring(1,type.length());
		}
		if("Y".equalsIgnoreCase(first)){
			return "N";
		}else if("N".equalsIgnoreCase(first)){
			return "Y";
		}else {
			return "";
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
