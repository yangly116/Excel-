package com.iceberg.buildFile.service;
/** 
 * 
 * @author 作者：杨文培 
 * @version 创建时间：Jul 15, 2017 9:37:50 PM  
 */

public interface StartService {
	/**
	 * 生成脚本
	 */
	public void start();
	/**
	 * 清空脚本
	 */
	public void cleanScript();
	/**
	 * 刷新脚本
	 */
	public void refreshScript();
}
