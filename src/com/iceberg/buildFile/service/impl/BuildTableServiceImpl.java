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
import com.iceberg.buildFile.enums.OpTypeTableEnum;
import com.iceberg.buildFile.main.Setting;
import com.iceberg.buildFile.service.BuildTableService;
import com.iceberg.buildFile.service.ExcelService;
import com.iceberg.buildFile.service.ScanFileService;
import com.iceberg.buildFile.util.StringUtil;

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
			for (int i = 0; i < lFiles.size(); i++) {
				List<List<List<String>>> lListDatas = excelService.getExcelAllData(lFiles.get(i));
				Map<String, Object> mapData = new HashMap<>();
				mapData.put("lListDatas", lListDatas);
				Table table = excelDataToTable(mapData);
				lTables.add(table);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(lTables.size()==0){
				Setting.showMS="未扫描到Excel模板文件!";
				System.out.println("本次扫描的目录为："+Setting.scanfFilePath);
			}else{
				Setting.showMS="本次扫描到 "+lTables.size()+" 个Excel模板文件!";
			}
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
			if(j==0){//读取表头行
				fillTableHead(table,lStrings);
			}
			if(j>=4){//读取字段
				Field field = new Field();
				if(lStrings.size()>=7){//读取表注释
					field.setComment(lStrings.get(0)+":"+lStrings.get(6));
				}else{
					field.setComment(lStrings.get(0));
				}
				field.setTab(lStrings.get(1));//读取字段名
				field.setType(lStrings.get(2));//读取类型
				if(lStrings.size()>=4){
					field.setIsNull(lStrings.get(3));//读取可空
				}
				if(lStrings.size()>=5){//读取主键
					if(!StringUtil.isEmpty(lStrings.get(4))&&"Y".equalsIgnoreCase(lStrings.get(4))){
						table.getlPks().add(lStrings.get(1));
					}
				}
				if(lStrings.size()>=6){//扩展属性
					field.setExtAttr(lStrings.get(5));//读取主键
				}
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
		table.setTableComment(lStrings.get(3));
		table.setSeq("SEQ_"+table.getTableName());
		table.setOpType(OpTypeTableEnum.getType(lStrings.get(6)).getText());
	}
	public ScanFileService getScanFileService() {
		return scanFileService;
	}
	public void setScanFileService(ScanFileService scanFileService) {
		this.scanFileService = scanFileService;
	}
	
}
