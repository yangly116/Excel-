package com.iceberg.buildFile.entity;
/** 
 * table字段
 * @author 作者 E-mail:1164180794@qq.com 
 * @version 创建时间：Jul 9, 2017 2:04:36 PM  
 */
public class Fdlk {
	/**字段标记*/
	private String field;
	/**字段类型*/
	private String type;
	/**是否必须"true,必须"*/
	private String required;
	/**字段长度*/
	private String length;
	/**日期类型*/
	private String dateformat;
	
	public String getDateformat() {
		return dateformat;
	}
	public void setDateformat(String dateformat) {
		this.dateformat = dateformat;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRequired() {
		return required;
	}
	public void setRequired(String required) {
		this.required = required;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	
}
