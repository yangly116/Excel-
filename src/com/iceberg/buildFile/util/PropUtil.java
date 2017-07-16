package com.iceberg.buildFile.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.iceberg.buildFile.main.Setting;

/** 
 * 获取Html节点标签
 * @author 作者 E-mail:1164180794@qq.com 
 * @version 创建时间：2016年12月18日 下午3:35:16  
 */
public class PropUtil {
	public static Properties properties;
	static{
		properties = new Properties();
		String filePath = Setting.config+File.separator+"prop.properties";
		System.out.println("propPath:"+filePath);
		File file = new File(filePath);
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			properties.load(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
