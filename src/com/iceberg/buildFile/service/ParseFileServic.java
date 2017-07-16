package com.iceberg.buildFile.service;

import java.io.File;
import java.util.Map;

import com.iceberg.buildFile.entity.Table;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * @author 作者 杨文培
 * 
 * @since：Jul 8, 2017 10:26:51 AM
 * 
 */
public interface ParseFileServic {
	public Map<String, Object> parseIbatisFile(File file);
}
