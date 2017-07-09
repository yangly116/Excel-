package com.iceberg.buildFile.parseFactory.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.iceberg.buildFile.main.Setting;
import com.iceberg.buildFile.parseFactory.ParseFile;
import com.iceberg.buildFile.util.StringUtil;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * @author 作者 杨文培
 * 
 * @since：Jul 8, 2017 9:37:14 AM
 * 
 */
public class IbatisParseImpl implements ParseFile{

	@Override
	public Map<String, Object> parseFile(File file) {
		String source = null;
		Map<String, Object> map = new HashMap<>();
		try {
			source = FileUtils.readFileToString(file, Setting.encoding);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> lStrings = StringUtil.splitToList(source, "\\[split\\]");
		for(int i=0;i<lStrings.size();i++){
			map.put(""+(i+1), lStrings.get(i));
		}
		return map;
		//System.out.println("source:\r\n"+source);
	}
	
}
