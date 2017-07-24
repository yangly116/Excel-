package com.iceberg.buildFile.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import com.iceberg.buildFile.entity.Field;
import com.iceberg.buildFile.entity.Ibatis;
import com.iceberg.buildFile.entity.Table;
import com.iceberg.buildFile.main.Setting;
import com.iceberg.buildFile.service.BuildTableService;
import com.iceberg.buildFile.service.CallWorkService;
import com.iceberg.buildFile.service.CreatFileService;
import com.iceberg.buildFile.service.ParseFileServic;
import com.iceberg.buildFile.util.BFileUtil;
import com.iceberg.buildFile.util.ParmUtil;

/**
 *
 * @author 作者 杨文培
 * 
 * @since：Jul 8, 2017 8:09:33 AM
 * 
 */
public class CallIbatisWorkServiceImpl implements CallWorkService{
	private Ibatis ibatis = new Ibatis();
	private File outFile;
	@Resource
	private CreatFileService creatFileService;
	@Resource
	private BuildTableService buildTableService;
	@Resource
	private ParseFileServic parseFileServic;
	/**
	 * @Description:开始工作
	 * @author 作者 杨文培
	 * @since：Jul 8, 2017 8:17:15 AM
	 */
	@Override
	public void callWork(){
		List<Table> lTables = buildTableService.getLtables();
		for (int i = 0; i < lTables.size(); i++) {
			Table table = lTables.get(i);
			creatFileService.createOutFileDirectory(table.getOpType());//创建脚本文件
			this.outFile = new File(Setting.scriptPath+File.separator+table.getTableName()+"-ib.xml");
			BFileUtil.clearFile(outFile);//清空文件内容
			File templateFile = new File(System.getProperty("user.dir")+"/template/ibatis/ibatis.xml");
			Map<String, Object> ibatisMap = parseFileServic.parseIbatisFile(templateFile);
			writeHeadBy1((String)ibatisMap.get("1"),table);
			writeFieldBy2((String)ibatisMap.get("2"),table);
			writeBy3((String)ibatisMap.get("3"),table);
			writeValues4((String)ibatisMap.get("4"),table);
			writeBy5((String)ibatisMap.get("5"));
		}
		if(lTables!=null&&lTables.size()!=0){
			System.out.println("成功生成ibatis文件！");
		}
	}
	/**
	 * @Description:写入insert头部文本
	 * @param source
	 * @author 作者 杨文培
	 * @since：Jul 8, 2017 11:59:52 AM
	 */
	private String writeHeadBy1(String source,Table table){
		List<String> ltargs = new ArrayList<>();
		List<String> lparms = new ArrayList<>();
		//lparms.add("pk"); ltargs.add(table.getPk());
		lparms.add("seq"); ltargs.add(table.getSeq());
		lparms.add("tableName"); ltargs.add(table.getTableName());
		lparms.add("id"); ltargs.add("insert"+table.getTableName());
		lparms.add("parameterClass"); ltargs.add(ibatis.getParameterClass());
		
		String successStr = ParmUtil.replaceparm(source, ltargs, lparms);//替换参数
		BFileUtil.write(outFile, successStr);
		return successStr;
	}
	/**
	 * @Description:写入字段
	 * @param source
	 * @author 作者 杨文培
	 * @since：Jul 8, 2017 12:02:23 PM
	 */
	private void writeFieldBy2(String source,Table table){
		//List<String> lFields = table.getFields();
		Map<String, Field> fieldMap = table.getFieldMap();
		for (Map.Entry<String, Field> entry : fieldMap.entrySet()) {
			Field field = entry.getValue();
			List<String> ltargs = new ArrayList<>();
			List<String> lparms = new ArrayList<>();
			//lparms.add("pk"); ltargs.add(table.getPk());
			lparms.add("isNot"); ltargs.add(ibatis.getIsNot());
			lparms.add("property"); ltargs.add(field.getTab());
			String successStr = ParmUtil.replaceparm(source, ltargs, lparms);//替换参数
			BFileUtil.write(outFile, successStr);
		}
	}
	private void writeBy3(String source,Table table){
		//String successStr = ParmUtil.replaceparm(source, table.getPk(), "pk");//替换参数
		//BFileUtil.write(outFile, successStr);
	}
	private void writeValues4(String source,Table table){
		//List<String> lFields = table.getFields();
		Map<String, Field> fieldMap = table.getFieldMap();
		for (Map.Entry<String, Field> entry : fieldMap.entrySet()) {
			Field field = entry.getValue();
			//String fieldStr = field.getTab();
			List<String> ltargs = new ArrayList<>();
			List<String> lparms = new ArrayList<>();
			lparms.add("isNot"); ltargs.add(ibatis.getIsNot());
			lparms.add("property"); ltargs.add(field.getTab());
			String successStr = ParmUtil.replaceparm(source, ltargs, lparms);//替换参数
			//String successStr = ParmUtil.replaceparm(source, fieldStr, "property");
			BFileUtil.write(outFile, successStr);
		}
	}
	private void writeBy5(String source){
		BFileUtil.write(outFile, source);
	}
	/*--------------------------------------------------------------------*/
	public File getOutFile() {
		return outFile;
	}
	public void setOutFile(File outFile) {
		this.outFile = outFile;
	}
	public Ibatis getIbatis() {
		return ibatis;
	}
	public void setIbatis(Ibatis ibatis) {
		this.ibatis = ibatis;
	}
	public BuildTableService getBuildTableService() {
		return buildTableService;
	}
	public void setBuildTableService(BuildTableService buildTableService) {
		this.buildTableService = buildTableService;
	}
	public ParseFileServic getParseFileServic() {
		return parseFileServic;
	}
	public void setParseFileServic(ParseFileServic parseFileServic) {
		this.parseFileServic = parseFileServic;
	}

	
}
