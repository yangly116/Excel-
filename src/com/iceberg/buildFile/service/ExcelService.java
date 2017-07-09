package com.iceberg.buildFile.service;

import java.io.File;
import java.util.List;
import java.util.Map;


/** 
 * 
 * @author 作者：杨文培 
 * @version 创建时间：Jul 9, 2017 2:44:07 PM  
 */
public interface ExcelService {
	public List<List<List<String>>> getExcelAllData(File file);
}
