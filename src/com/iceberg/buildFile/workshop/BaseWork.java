package com.iceberg.buildFile.workshop;

import java.io.File;
import java.util.Map;

import com.iceberg.buildFile.myenum.ParseKeyEnum;
import com.iceberg.buildFile.parseFactory.ParseFactory;
import com.iceberg.buildFile.parseFactory.ParseFile;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * @author 作者 杨文培
 * 
 * @since：Jul 8, 2017 11:22:33 AM
 * 
 */
public abstract class BaseWork {
	protected File sourceFile;
	protected File outFile;
	public abstract void work(File sourceFile,File outFile);
	public Map<String, Object> getParseFile(String key){
		return parseFile(sourceFile,key);
	}
	public BaseWork() {
	}
	public void init(File sourceFile, File outFile) {
		this.sourceFile = sourceFile;
		this.outFile = outFile;		
	}
	public Map<String, Object> parseFile(File file,String key){
		if(file == null){
			try {
				throw new Exception("sourceFile未初始化！");
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		ParseFile parseFile = ParseFactory.getParseFile(key);
		return parseFile.parseFile(file);
	}
}
