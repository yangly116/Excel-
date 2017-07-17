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
import com.iceberg.buildFile.main.Setting;
import com.iceberg.buildFile.service.BuildTableService;
import com.iceberg.buildFile.service.CallWorkService;
import com.iceberg.buildFile.service.ScanFileService;
import com.iceberg.buildFile.util.BFileUtil;
import com.iceberg.buildFile.util.PropUtil;
import com.iceberg.buildFile.util.MatcherUtil;
import com.iceberg.buildFile.util.ParmUtil;
import com.iceberg.buildFile.util.StringUtil;

/** 
 * table调度
 * @author 作者：杨文培 
 * @version 创建时间：Jul 15, 2017 8:41:21 PM  
 */

public class CallTableWorkServiceImpl implements CallWorkService{
	@Resource
	private ScanFileService scanFileService;
	@Resource
	private BuildTableService buildTableService;
	private File outFile;
	@Override
	public void callWork() {
		List<Table> lTables = buildTableService.getLtables();
		for (int i = 0; i < lTables.size(); i++) {
			wirteTable(lTables.get(i));
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
		File templateFile = new File(System.getProperty("user.dir")+"/template/table/table.xml");
		this.outFile = new File(Setting.produceRoot+File.separator+table.getTableName()+".sql");
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
						replaceLine(strLine, table);
					}
				}
				if (top != null && builder != null) {
					if (!MatcherUtil.isStart(strLine) && !MatcherUtil.isEnd(strLine)) {
						builder.append(strLine + "\r\n");
					}
				}
				if (MatcherUtil.isEnd(strLine)) {// 判断结束标签
					stack.pop();
					if("field".equals(top)||"comment".equals(top)){
						String strLineBf = writeField(table,builder.toString());
						if("field".equals(top)){
							strLineBf=StringUtil.subFix(strLineBf.trim());
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
		List<String> lRegexs = ParmUtil.getRegexs(StringUtil.splitToList(PropUtil.properties.getProperty("table"), ","));
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
		Map<String, Field> fieldMap = table.getFieldMap();
		StringBuilder builder = new StringBuilder();
		List<String> lRegexs = ParmUtil.getRegexs(StringUtil.splitToList(PropUtil.properties.getProperty("field"), ","));
		for (Map.Entry<String, Field> entry : fieldMap.entrySet()) {
			String tempStr = new String(tempLine);
			//String key =  entry.getKey();
			Field field = entry.getValue();
			//System.out.println("field:"+field);
			Map<String, String> map = getFieldMap(field,table);
			for(int i=0;i<lRegexs.size();i++){
				String regex = lRegexs.get(i);
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(tempLine);
				while(matcher.find()){
					String regStr = matcher.group();//匹配到的字符串
					//System.out.println("regStr:"+regStr);
					tempStr = tempStr.replaceAll(regex, map.get(regStr));
				}
			}
			builder.append(tempStr);
	    }
		return builder.toString();
	}
	private Map<String, String> getTableMap(Table table){
		Map<String, String> map = new HashMap<>();
		map.put("${tableName}", table.getTableName());
		map.put("${pk}", table.getPk());
		map.put("${tableComment}", table.getTableComment());
		return map;
	}
	private Map<String, String> getFieldMap(Field field,Table table){
		Map<String, String> map = new HashMap<>();
		map.put("${field}", field.getTab());
		map.put("${type}", StringUtil.getFieldType(field.getType()));
		map.put("${comment}", field.getComment());
		map.put("${tableName}", table.getTableName());
		return map;
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
