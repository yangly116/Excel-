package com.iceberg.buildFile.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.iceberg.buildFile.server.AContextServer;
import com.iceberg.buildFile.service.ParseFactoryService;
import com.iceberg.buildFile.service.ParseFileServic;

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
@Service("ParseFactoryService")
public class ParseFactoryServiceImpl implements ParseFactoryService{
	public Map<String, Object> mapFactoryImpl;

	public Map<String, Object> getMapFactoryImpl() {
		return mapFactoryImpl;
	}

	public void setMapFactoryImpl(Map<String, Object> mapFactoryImpl) {
		this.mapFactoryImpl = mapFactoryImpl;
	}
	@Override
	public ParseFileServic getParseFile(String key){
		ParseFileServic parseFile = (ParseFileServic) mapFactoryImpl.get(key);
		return parseFile;
	}
}
