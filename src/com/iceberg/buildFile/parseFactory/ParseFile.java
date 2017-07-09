package com.iceberg.buildFile.parseFactory;

import java.io.File;
import java.util.Map;


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
public interface ParseFile {
	public Map<String, Object> parseFile(File file);
}
