package com.iceberg.buildFile.parseFactory.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.iceberg.buildFile.parseFactory.ParseFile;
import com.iceberg.buildFile.service.ExcelService;

/** 
 * 解析Excel
 * @author 作者：杨文培 
 * @version 创建时间：Jul 9, 2017 3:09:22 PM  
 */
public class TableParseImpl implements ParseFile{
	@Resource
	private ExcelService excelService;
	@Override
	public Map<String, Object> parseFile(File file) {
		Map<String, Object> map = new HashMap<>();
		List<List<List<String>>> lListDatas = excelService.getExcelAllData(file);
		map.put("lListDatas", lListDatas);
		return map;
	}
}
