package com.iceberg.buildFile.parseFactory;

import java.util.Map;

import com.iceberg.buildFile.server.AContextServer;

/**
 * <p>Title: </p>
 *
 * <p>Description:文件解析工厂</p>
 *
 * @author 作者 杨文培
 * 
 * @since：Jul 8, 2017 8:31:57 AM
 * 
 */
public class ParseFactory {
	public static Map<String, Object> mapFactoryImpl;

	public Map<String, Object> getMapFactoryImpl() {
		return mapFactoryImpl;
	}

	public void setMapFactoryImpl(Map<String, Object> mapFactoryImpl) {
		ParseFactory.mapFactoryImpl = mapFactoryImpl;
	}
	public static ParseFile getParseFile(String key){
		if(mapFactoryImpl == null){
			ParseFactory parseFactory= (ParseFactory)AContextServer.aContextServer.getAppContext().getBean("BF.parseFactory");
			mapFactoryImpl = parseFactory.getMapFactoryImpl();
		}
		ParseFile parseFile = (ParseFile) mapFactoryImpl.get(key);
		return parseFile;
	}
}
