package com.iceberg.buildFile.entity;
/** 
 * table字段
 * @author 作者 E-mail:1164180794@qq.com 
 * @version 创建时间：Jul 9, 2017 2:04:36 PM  
 */
public class Field {
	/**字段标记*/
	private String tab;
	/**字段名称*/
	private String name;
	/**字段类型*/
	private String type;
	/**字段是否为空：Y，可为空，其它为非空*/
	private String isNull;
	/**备注*/
	private String memo;
	/**注释*/
	private String comment;
	/**扩展属性*/
	private String extAttr;
	public String getTab() {
		return tab;
	}
	public void setTab(String tab) {
		this.tab = tab;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIsNull() {
		return isNull;
	}
	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getExtAttr() {
		return extAttr;
	}
	public void setExtAttr(String extAttr) {
		this.extAttr = extAttr;
	}
	@Override
	public String toString() {
		return "Field [tab=" + tab + ", name=" + name + ", type=" + type + ", isNull=" + isNull + ", memo=" + memo
				+ ", comment=" + comment + ", extAttr=" + extAttr + "]";
	}

	
}
