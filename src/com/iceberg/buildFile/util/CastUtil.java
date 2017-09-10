package com.iceberg.buildFile.util;

import com.iceberg.buildFile.entity.Fdlk;
import com.iceberg.buildFile.entity.Field;
import com.iceberg.buildFile.main.Setting;

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
		field.setType(StringUtil.fullFieldType_FDLK(field.getType()));
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
			return Setting.Dateformat;
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
			return "10";
		}else if("C".equalsIgnoreCase(first)){
			return pfix;
		}else if("N".equalsIgnoreCase(first)){
			if("".equalsIgnoreCase(pfix)){
				return Setting.IntegerLength;
			}else{
				return pfix;//integer
			}
		}else if("I".equalsIgnoreCase(first)){
			return Setting.IntegerLength;
		}
		return "";
	}
	private static String getRequired(String type){
		if("Y".equalsIgnoreCase(type)||StringUtil.isEmpty(type)){
			return "false";
		}else{
			return "true";
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
			if("".equalsIgnoreCase(pfix)){
				return "integer";
			}else{
				return "double";//
			}
		}else if("I".equalsIgnoreCase(first)){
			return "integer";
		}
		return "";
	}
}
