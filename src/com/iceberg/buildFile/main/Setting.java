package com.iceberg.buildFile.main;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.util.logging.resources.logging;

/** 
 * 単例 
 * @author 作者：杨文培  E-mail:1164180794@qq.com 
 * @version 创建时间：Jul 6, 2017 11:29:40 PM  
 */
public class Setting {
	private static Log log = LogFactory.getLog(Setting.class);
	public static Setting init = new Setting();
	public static String encoding = "gbk";
	/**Integer默认长度*/
	public static String IntegerLength = "64";
	/**FDLK日期默认类型*/
	public static String Dateformat = "yyyy-MM-dd";
	public static String userDir = System.getProperty("user.dir")+File.separator;
	/**设置模板文件读取的位置*/
	public static String scanfFilePath = userDir+"in";
	/**设置产品生成的根位置*/
	//public static String produceRoot = userDir+"produce";
	public static String scriptPath = userDir+"out";
	public static String config = userDir+"config";
	public static String logDir = userDir+"log";
	public static String dd;
	/**展示信息*/
	private static String showMS = "欢迎使用BDF！";
	private Setting() {//私有构造方法
	}
	public static Setting getInit() {
		System.setProperty("log_home", logDir);
		log.info("log_home:"+logDir);
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
	public static String getShowMS() {
		return showMS;
	}
	public static void setShowMS(String showMS) {
		log.info(showMS);
		Setting.showMS = showMS;
	}
	public static String getScriptPath() {
		return scriptPath;
	}
	public static void setScriptPath(String scriptPath) {
		Setting.scriptPath = scriptPath;
	}
	public static Log getLog() {
		return log;
	}
	public static void setLog(Log log) {
		Setting.log = log;
	}
	public static String getIntegerLength() {
		return IntegerLength;
	}
	public static void setIntegerLength(String integerLength) {
		IntegerLength = integerLength;
	}
	public static String getDateformat() {
		return Dateformat;
	}
	public static void setDateformat(String dateformat) {
		Dateformat = dateformat;
	}
	public static String getUserDir() {
		return userDir;
	}
	public static void setUserDir(String userDir) {
		Setting.userDir = userDir;
	}
	public static String getConfig() {
		return config;
	}
	public static void setConfig(String config) {
		Setting.config = config;
	}
	public static String getLogDir() {
		return logDir;
	}
	public static void setLogDir(String logDir) {
		Setting.logDir = logDir;
	}
	public static String getDd() {
		return dd;
	}
	public static void setDd(String dd) {
		Setting.dd = dd;
	}
	
}
