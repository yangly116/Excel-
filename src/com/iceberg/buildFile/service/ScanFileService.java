package com.iceberg.buildFile.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/** 
 * 
 * @author 作者：杨文培 
 * @version 创建时间：Jul 16, 2017 12:30:40 PM  
 */
public interface ScanFileService {
	public boolean scanfFile(String filePath,List<File> lFiles) throws FileNotFoundException,IOException;
	/**
	 * 根据文件后缀名获取文件列表
	 * @param filePath
	 * @param lFiles
	 * @param filePFix
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public boolean scanfFile(String filePath, List<File> lFiles,String filePFix) throws FileNotFoundException, IOException ;
}
