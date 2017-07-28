package com.iceberg.buildFile.service;

import java.io.File;

/** 
 * 
 * @author 作者：杨文培 
 * @version 创建时间：Jul 20, 2017 9:52:34 PM  
 */

public interface CreatFileService {
	/**
	 * 根据code创建不同的脚本文件
	 * @param code
	 */
	public void createOutFileDirectory(String fix);
}
