package com.iceberg.buildFile.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/** 
 * 获取Html节点标签
 * @author 作者 E-mail:1164180794@qq.com 
 * @version 创建时间：2016年12月18日 下午3:35:16  
 */
public class HtmlNodeUtil {
	public static Properties properties;
	static{
		properties = new Properties();
		File file = new File(HtmlNodeUtil.class.getResource("/htmlNode.properties").getFile());
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			properties.load(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
