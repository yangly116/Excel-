package com.iceberg.buildFile.workshop;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.iceberg.buildFile.entity.Field;
import com.iceberg.buildFile.entity.Ibatis;
import com.iceberg.buildFile.entity.Table;
import com.iceberg.buildFile.myenum.ParseKeyEnum;
import com.iceberg.buildFile.parseFactory.ParseFactory;
import com.iceberg.buildFile.parseFactory.ParseFile;
import com.iceberg.buildFile.util.BFileUtil;
import com.iceberg.buildFile.util.ParmUtil;
/**
 * <p>Title: </p>
 *
 * <p>Description:Production IbatisFile  </p>
 *
 * @author 作者 杨文培
 * 
 * @since：Jul 8, 2017 8:09:33 AM
 * 
 */
public class IbatisMake extends BaseWork{
	private Table table;
	private Ibatis ibatis;
	public IbatisMake(Table table,Ibatis ibatis) {
		super();
		this.table = table;
		this.ibatis = ibatis;
	}
	public IbatisMake() {
	}
	/**
	 * @Description:开始工作
	 * @author 作者 杨文培
	 * @since：Jul 8, 2017 8:17:15 AM
	 */
	@Override
	public void work(File sourceFile,File outFile){
		init(sourceFile, outFile);
		BFileUtil.clearFile(outFile);//清空文件内容
		Map<String, Object> ibatisMap = getParseFile(ParseKeyEnum.ib_insert.getText());
		writeHeadBy1((String)ibatisMap.get("1"));
		writeFieldBy2((String)ibatisMap.get("2"));
		writeBy3((String)ibatisMap.get("3"));
		writeValues4((String)ibatisMap.get("4"));
		writeBy5((String)ibatisMap.get("5"));
	}
	/**
	 * @Description:写入insert头部文本
	 * @param source
	 * @author 作者 杨文培
	 * @since：Jul 8, 2017 11:59:52 AM
	 */
	private String writeHeadBy1(String source){
		List<String> ltargs = new ArrayList<>();
		List<String> lparms = new ArrayList<>();
		lparms.add("pk"); ltargs.add(table.getPk());
		lparms.add("seq"); ltargs.add(table.getSeq());
		lparms.add("tableName"); ltargs.add(table.getTableName());
		lparms.add("id"); ltargs.add(ibatis.getId());
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
	private void writeFieldBy2(String source){
		//List<String> lFields = table.getFields();
		Map<String, Field> fieldMap = table.getFieldMap();
		for (Map.Entry<String, Field> entry : fieldMap.entrySet()) {
			Field field = entry.getValue();
			List<String> ltargs = new ArrayList<>();
			List<String> lparms = new ArrayList<>();
			lparms.add("pk"); ltargs.add(table.getPk());
			lparms.add("isNot"); ltargs.add(ibatis.getIsNot());
			lparms.add("property"); ltargs.add(field.getTab());
			String successStr = ParmUtil.replaceparm(source, ltargs, lparms);//替换参数
			BFileUtil.write(outFile, successStr);
		}
	}
	private void writeBy3(String source){
		String successStr = ParmUtil.replaceparm(source, table.getPk(), "pk");//替换参数
		BFileUtil.write(outFile, successStr);
	}
	private void writeValues4(String source){
		//List<String> lFields = table.getFields();
		Map<String, Field> fieldMap = table.getFieldMap();
		for (Map.Entry<String, Field> entry : fieldMap.entrySet()) {
			Field field = entry.getValue();
			String fieldStr = field.getTab();
			String successStr = ParmUtil.replaceparm(source, fieldStr, "property");
			BFileUtil.write(outFile, successStr);
		}
	}
	private void writeBy5(String source){
		BFileUtil.write(outFile, source);
	}
	/*--------------------------------------------------------------------*/
	public Table getTable() {
		return table;
	}
	public void setTable(Table table) {
		this.table = table;
	}
	public File getSourceFile() {
		return sourceFile;
	}
	public void setSourceFile(File sourceFile) {
		this.sourceFile = sourceFile;
	}
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

	
}
