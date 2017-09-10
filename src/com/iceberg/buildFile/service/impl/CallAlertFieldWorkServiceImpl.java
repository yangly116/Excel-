package com.iceberg.buildFile.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;

import com.iceberg.buildFile.entity.Field;
import com.iceberg.buildFile.entity.Table;
import com.iceberg.buildFile.enums.OpTypeTableEnum;
import com.iceberg.buildFile.main.Setting;
import com.iceberg.buildFile.service.BuildPatchService;
import com.iceberg.buildFile.service.BuildTableService;
import com.iceberg.buildFile.service.CallWorkService;
import com.iceberg.buildFile.service.CreatFileService;
import com.iceberg.buildFile.service.ScanFileService;
import com.iceberg.buildFile.util.BFileUtil;
import com.iceberg.buildFile.util.PropUtil;
import com.iceberg.buildFile.util.MatcherUtil;
import com.iceberg.buildFile.util.ParmUtil;
import com.iceberg.buildFile.util.StringUtil;

/** 
 * 修改字段
 * @author 作者：杨文培 
 * @version 创建时间：Jul 15, 2017 8:41:21 PM  
 */

public class CallAlertFieldWorkServiceImpl implements CallWorkService{
	@Resource
	private CreatFileService creatFileService;
	@Resource
	private ScanFileService scanFileService;
	@Resource
	private BuildTableService buildTableService;
	@Resource
	private BuildPatchService buildPatchService;
	private File outFile;
	private String dir = "alert-field";
	private String pFix = dir+".sql";
	private String fix = "脚本"+File.separator+dir;
	@Override
	public void callWork() {
		List<Table> lTables = buildTableService.getLtables();
		for (int i = 0; i < lTables.size(); i++) {
			Table table = lTables.get(i);
			if(OpTypeTableEnum.Alert_Filed_4.getText().equals(table.getOpType())){
				creatFileService.createOutFileDirectory(fix);//创建脚本文件
				wirteTable(table);
				buildPatchService.buildPatch("@"+dir+File.separator+table.getTableName()+"-"+pFix+";\r\n");
			}
		}
		if(lTables!=null&&lTables.size()!=0){
			System.out.println("成功生成table文件！");
		}
	}
	public ScanFileService getScanFileService() {
		return scanFileService;
	}
	public void setScanFileService(ScanFileService scanFileService) {
		this.scanFileService = scanFileService;
	}
	private void wirteTable(Table table){
		File templateFile = new File(System.getProperty("user.dir")+"/template/table/alertField.xml");
		this.outFile = new File(Setting.scriptPath+File.separator+fix+File.separator+table.getTableName()+"-alert-field"+".sql");
		Stack<String> stack = new Stack<>();
		StringBuilder builder = null;
		BFileUtil.clearFile(outFile);//清空文件内容
		try {
			List<String> list = FileUtils.readLines(templateFile);
			String top = null;
			for(int i=0;i<list.size();i++){
				String strLine = list.get(i);
				if(MatcherUtil.isStart(strLine)){
					top = MatcherUtil.getIdContent(strLine, "id");
					stack.push(top);
					builder = new StringBuilder();
				}else{
					if(top == null){
						replaceLine(strLine, table);//替换非循环行数
					}
				}
				if (top != null && builder != null) {
					if (!MatcherUtil.isStart(strLine) && !MatcherUtil.isEnd(strLine)) {
						builder.append(strLine + "\r\n");
					}
				}
				if (MatcherUtil.isEnd(strLine)) {// 判断结束标签
					stack.pop();
					if("isField".equals(top)||"alertField".equals(top)){
						String strLineBf = writeField(table,builder.toString());
						if("isField".equals(top)){
							strLineBf=StringUtil.replaSubFix(strLineBf.trim());
						}
						BFileUtil.write(outFile, strLineBf+"\r\n");
					}
					builder = new StringBuilder();
					top = null;
				}
				//System.out.println(successLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private String replaceLine(String strLine,Table table){
		List<String> lRegexs = ParmUtil.getRegexs(StringUtil.splitToList(PropUtil.properties.getProperty("addField"), ","));
		Map<String, String> map = getTableMap(table);
		for (int i = 0; i < lRegexs.size(); i++) {
			String regex = lRegexs.get(i);
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(strLine);
			while(matcher.find()){
				String regStr = matcher.group();//匹配到的字符串
				//System.out.println("regStr:"+regStr);
				strLine = strLine.replaceAll(regex, map.get(regStr));
			}
		}
		BFileUtil.write(outFile, strLine+"\r\n");
		return strLine;
	}
	private String writeField(Table table,String tempLine){
		Map<String, Field> fieldMap = table.getFieldMap();//有序map
		StringBuilder builder = new StringBuilder();
		List<String> lRegexs = ParmUtil.getRegexs(StringUtil.splitToList(PropUtil.properties.getProperty("fieldAlert"), ","));
		for (Map.Entry<String, Field> entry : fieldMap.entrySet()) {
			String tempStr = new String(tempLine);
			//String key =  entry.getKey();
			Field field = entry.getValue();
			//System.out.println("field:"+field);
			Map<String, String> map = getFieldAlertMap(field,table);
			String isNULL = null;//是否有isNull
			String fielName = null;
			for(int i=0;i<lRegexs.size();i++){
				String regex = lRegexs.get(i);
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(tempLine);
				while(matcher.find()){
					String regStr = matcher.group();//匹配到的字符串
					String valueReg = map.get(regStr);
					//System.out.println("regStr:"+regStr);
					//System.out.println("valueReg:"+valueReg);
					tempStr = tempStr.replaceAll(regex,valueReg );
					if("NULL".equalsIgnoreCase(valueReg)||"NOT NULL".equalsIgnoreCase(valueReg)){//
						isNULL = valueReg;
					}
				}
			}
			if(isNULL != null){
				fielName = field.getName();
				tempStr = getAppendNull(tempStr, table, field);
			}
			builder.append(tempStr);
	    }
		return builder.toString();
	}
	private String getAppendNull(String tempStr,Table table,Field field){
		File templateFile = new File(System.getProperty("user.dir")+"/template/table/isNull.xml");
		try {
			String appendStr = FileUtils.readFileToString(templateFile);
			List<String> lRegexs = ParmUtil.getRegexs(StringUtil.splitToList(PropUtil.properties.getProperty("isN"), ","));
			Map<String, String> map = getIsNullFieldMap(field,table);
			for(int i=0;i<lRegexs.size();i++){
				String regex = lRegexs.get(i);
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(appendStr);
				while(matcher.find()){
					String regStr = matcher.group();//匹配到的字符串
					String valueReg = map.get(regStr);
					//System.out.println("regStrIsN:"+regStr);
					//System.out.println("valueRegIsN:"+valueReg);
					appendStr = appendStr.replaceAll(regex,valueReg );
				}
			}
			tempStr = appendStr+"\r\n"+tempStr+"         end if;"+"\r\n";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tempStr;
	}
	private Map<String, String> getTableMap(Table table){
		Map<String, String> map = new HashMap<>();
		map.put("${tableName}", table.getTableName());
		map.put("${fieldSize}", String.valueOf(table.getFieldMap().size()));
		return map;
	}
	private Map<String, String> getFieldMap(Field field,Table table){
		Map<String, String> map = new HashMap<>();
		map.put("${field}", field.getTab());
		map.put("${type}", StringUtil.getFieldType(field.getType()));
		map.put("${comment}", field.getComment());
		map.put("${tableName}", table.getTableName());
		map.put("${isNull}", StringUtil.getAlertFieldIsNull(field.getIsNull()));
		map.put("${extAttr}", StringUtil.getFieldExtAttr(field.getExtAttr()));
		return map;
	}
	private Map<String, String> getFieldAlertMap(Field field,Table table){
		Map<String, String> map = new HashMap<>();
		map.put("${field}", field.getTab());
		map.put("${type}", StringUtil.getFieldType(field.getType()));
		map.put("${comment}", field.getComment());
		map.put("${tableName}", table.getTableName());
		map.put("${isNull}", StringUtil.getAlertFieldIsNull(field.getIsNull()));
		map.put("${extAttr}", StringUtil.getFieldExtAttr(field.getExtAttr()));
		map.put("${fieldSize}", String.valueOf(table.getFieldMap().size()));
		return map;
	}
	private Map<String, String> getIsNullFieldMap(Field field,Table table){
		Map<String, String> map = new HashMap<>();
		map.put("${field}", field.getTab());
		map.put("${tableName}", table.getTableName());
		map.put("${isN}", StringUtil.getAlertFieldIsN(field.getIsNull()));
		return map;
	}
	public BuildTableService getBuildTableService() {
		return buildTableService;
	}
	public void setBuildTableService(BuildTableService buildTableService) {
		this.buildTableService = buildTableService;
	}
	public File getOutFile() {
		return outFile;
	}
	public void setOutFile(File outFile) {
		this.outFile = outFile;
	}

}
