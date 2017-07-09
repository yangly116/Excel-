package com.iceberg.buildFile.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.iceberg.buildFile.main.Setting;

/** 
 * 
 * @author 作者：杨文培 E-mail:1164180794@qq.com 
 * @version 创建时间：Jul 6, 2017 10:55:58 PM  
 */
public class BFileUtil {
	private static File rootFile = new File(BFileUtil.class.getResource("/").getFile());
	private static String rootPath = rootFile.getAbsolutePath();
	private static File newFile;
	private static boolean append = true;
	public static void write(File file,String str) {
		try {
			if(!file.exists()){
				file.createNewFile();
			}
			FileUtils.writeStringToFile(file, str, Setting.encoding, append);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void write(String fileName,String str) {
		newFile = new File(rootPath+File.separator+fileName);
		write(newFile, str);
	}
	public static void clearFile(File file){
		try {
			if(!file.exists()){
				file.createNewFile();
			}
			FileUtils.writeStringToFile(file, "", Setting.encoding, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void clearFile(String fileName){
		newFile = new File(rootPath+File.separator+fileName);
		clearFile(newFile);
	}
	public static File createFile(String path){
		File file = new File(path);
		createFile(file);
		return file;
			
	}
	public static File createFile(File file){
		try {
			if(!file.exists()){
				file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
		
	}
	
	
	/*------------------------------------------------------------*/
	public static String getRootPath() {
		return rootPath;
	}
	public static void setRootPath(String rootPath) {
		BFileUtil.rootPath = rootPath;
	}
	
}
