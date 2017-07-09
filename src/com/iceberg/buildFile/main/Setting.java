package com.iceberg.buildFile.main;
/** 
 * 単例 
 * @author 作者：杨文培  E-mail:1164180794@qq.com 
 * @version 创建时间：Jul 6, 2017 11:29:40 PM  
 */
public class Setting {
	public static Setting init = new Setting();
	public static String encoding = "gbk";
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
	
}
