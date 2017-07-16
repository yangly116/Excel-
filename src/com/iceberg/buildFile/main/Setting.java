package com.iceberg.buildFile.main;

import java.io.File;

/** 
 * 単例 
 * @author 作者：杨文培  E-mail:1164180794@qq.com 
 * @version 创建时间：Jul 6, 2017 11:29:40 PM  
 */
public class Setting {
	public static Setting init = new Setting();
	public static String encoding = "gbk";
	/**设置模板文件读取的位置*/
	public static String scanfFilePath = System.getProperty("user.dir")+File.separator+"template";
	/**设置产品生成的根位置*/
	public static String produceRoot = System.getProperty("user.dir")+File.separator+"produce";
	
	public static String config = System.getProperty("user.dir")+File.separator+"config";
	/**展示信息*/
	public static String showMS = "欢迎使用BDF";
	private Setting() {//私有构造方法
	}
	public static Setting getInit() {
		return init;
	}
	public static void setInit(Setting init) {
		Setting.init = init;
	}
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		Setting.encoding = encoding;
	}
	public static String getScanfFilePath() {
		return scanfFilePath;
	}
	public static void setScanfFilePath(String scanfFilePath) {
		Setting.scanfFilePath = scanfFilePath;
	}
	public static String getProduceRoot() {
		return produceRoot;
	}
	public static void setProduceRoot(String produceRoot) {
		Setting.produceRoot = produceRoot;
	}
	public static String getShowMS() {
		return showMS;
	}
	public static void setShowMS(String showMS) {
		Setting.showMS = showMS;
	}
	
}
