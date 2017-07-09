package com.iceberg.buildFile.entity;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * @author 作者 杨文培
 * 
 * @since：Jul 8, 2017 5:24:56 PM
 * 
 */
public class Ibatis {
	/**sql标签ID*/
	private String id;
	private String parameterClass="java.util.Map";
	private String resultClass="java.util.Map";
	/**非空判断*/
	private String isNot = "isNotNull";
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParameterClass() {
		return parameterClass;
	}
	public void setParameterClass(String parameterClass) {
		this.parameterClass = parameterClass;
	}
	public String getResultClass() {
		return resultClass;
	}
	public void setResultClass(String resultClass) {
		this.resultClass = resultClass;
	}
	public String getIsNot() {
		return isNot;
	}
	public void setIsNot(String isNot) {
		this.isNot = isNot;
	}
	
}
