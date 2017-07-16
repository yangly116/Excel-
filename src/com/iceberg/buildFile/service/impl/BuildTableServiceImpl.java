package com.iceberg.buildFile.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iceberg.buildFile.entity.Field;
import com.iceberg.buildFile.entity.Table;
import com.iceberg.buildFile.main.Setting;
import com.iceberg.buildFile.service.BuildTableService;
import com.iceberg.buildFile.service.ExcelService;
import com.iceberg.buildFile.service.ScanFileService;

/** 
 * 
 * @author 作者：杨文培 
 * @version 创建时间：Jul 16, 2017 1:55:22 PM  
 */
@Service("BuildTableService")
public class BuildTableServiceImpl implements BuildTableService {
	@Resource
	private ScanFileService scanFileService;
	@Resource
	private ExcelService excelService;
	@Override
	public List<Table> getLtables() {
		List<Table> lTables = new ArrayList<>();
		List<File> lFiles = new ArrayList<>();
		try {
			scanFileService.scanfFile(Setting.scanfFilePath, lFiles, ".xlsx");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < lFiles.size(); i++) {
			List<List<List<String>>> lListDatas = excelService.getExcelAllData(lFiles.get(i));
			Map<String, Object> mapData = new HashMap<>();
			mapData.put("lListDatas", lListDatas);
			Table table = excelDataToTable(mapData);
			lTables.add(table);
		}
		return lTables;
	}
	/**
	 * 将获取的Excel数据转换为table对象 
	 * @param mapData
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Table excelDataToTable(Map<String, Object> mapData){
		Table table = new Table();
		LinkedHashMap<String, Field> fieldMap = new LinkedHashMap<>();
		List<List<List<String>>> lListDatas = (List<List<List<String>>>) mapData.get("lListDatas");
		//for(int i=0;i<lListDatas.size();i++){//sheet页签
		List<List<String>> lists = lListDatas.get(0);//获取第一个页签
		for(int j=0;j<lists.size();j++){//row行
			List<String> lStrings = lists.get(j);
			if(j==0){
				fillTableHead(table,lStrings);
				continue;
			}
			if(j>=2){
				Field field = new Field();
				if(lStrings.size()>=5){
					field.setComment(lStrings.get(0)+":"+lStrings.get(4));
				}else{
					field.setComment(lStrings.get(0));
				}
				field.setTab(lStrings.get(1));
				field.setType(lStrings.get(2));
				fieldMap.put(lStrings.get(1), field);
			}
		}
		//}
		table.setFieldMap(fieldMap);
		return table;
	}
	/**
	 * 填充表头信息
	 * @param table
	 * @param lStrings
	 */
	private void fillTableHead(Table table,List<String> lStrings){
		table.setTableName(lStrings.get(1));
		table.setPk(lStrings.get(3));
		table.setTableComment(lStrings.get(4));
		table.setSeq("SEQ_"+table.getTableName());
	}
	public ScanFileService getScanFileService() {
		return scanFileService;
	}
	public void setScanFileService(ScanFileService scanFileService) {
		this.scanFileService = scanFileService;
	}
	
}
