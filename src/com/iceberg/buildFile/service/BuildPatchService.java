package com.iceberg.buildFile.service;

/** 
 * 
 * @author 作者：杨文培 
 * @version 创建时间：Jul 22, 2017 5:42:50 PM  
 */

public interface BuildPatchService {
	/**
	 * 构建patch文件
	 * @param outFileName
	 */
	public void buildPatch(String outFileName);
	/**
	 * 清空patch
	 */
	public void cleanPatch();
	/**
	 * 清空脚本
	 */
	public void cleanScript();
	/**
	 * 刷新脚本
	 */
	public void refreshScript();
}
