package com.iceberg.buildFile.util;

import com.iceberg.buildFile.entity.Fdlk;
import com.iceberg.buildFile.entity.Field;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author 作者 杨文培
 * 
 * @since：2017年7月27日 上午11:43:52
 * 
 */

public class CastUtil {
	public static Fdlk FieldToFdlk(Field field){
		Fdlk fdlk = new Fdlk();
		fdlk.setField(field.getTab());
		fdlk.setType(getFdlkType(field.getType()));
		fdlk.setRequired(getRequired(field.getIsNull()));
		fdlk.setLength(getLength(field.getType()));
		fdlk.setDateformat(getDateformat(field.getType()));
		return fdlk;
	}
	private static String getDateformat(String type){
		if(StringUtil.isEmpty(type)){
			return null;
		}
		String first = type.substring(0,1);
		if("D".equalsIgnoreCase(first)){
			return "yyyy-MM-dd";
		}
		return null;
	}
	private static String getLength(String type){
		if(StringUtil.isEmpty(type)){
			return "";
		}
		String first = type.substring(0,1);
		String pfix = "";
		if(type.length()>1){
			pfix = type.substring(1,type.length());
		}
		if("D".equalsIgnoreCase(first)){
			return "8";
		}else if("C".equalsIgnoreCase(first)){
			return pfix;
		}else if("N".equalsIgnoreCase(first)){
			if("".equalsIgnoreCase(pfix)){
				return null;
			}else{
				return "16";
			}
		}else if("I".equalsIgnoreCase(first)){
			return null;
		}
		return "";
	}
	private static String getRequired(String type){
		if("Y".equalsIgnoreCase(type)){
			return "true";
		}else{
			return "false";
		}
	}
	private static String getFdlkType(String type){
		if(StringUtil.isEmpty(type)){
			return "";
		}
		String first = type.substring(0,1);
		String pfix = "";
		if(type.length()>1){
			pfix = type.substring(1,type.length());
		}
		if("D".equalsIgnoreCase(first)){
			return "date";
		}else if("C".equalsIgnoreCase(first)){
			return "string";
		}else if("N".equalsIgnoreCase(first)){
			return "integer";
		}else if("I".equalsIgnoreCase(first)){
			return "integer";
		}
		return "";
	}
}
