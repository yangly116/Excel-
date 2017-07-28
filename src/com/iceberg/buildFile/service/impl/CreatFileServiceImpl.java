package com.iceberg.buildFile.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;
import org.springframework.stereotype.Service;

import com.iceberg.buildFile.enums.OpTypeTableEnum;
import com.iceberg.buildFile.main.Setting;
import com.iceberg.buildFile.service.CreatFileService;

/** 
 * 创建脚本目录
 * @author 作者：杨文培 
 * @version 创建时间：Jul 20, 2017 10:00:55 PM  
 */
@Service("CreatFileService")
public class CreatFileServiceImpl implements CreatFileService {
	private File patchFile;	
	/* (non-Javadoc)
	 * @see com.iceberg.buildFile.service.CreatFileService#createOutFileDirectory(java.lang.String)
	 */
	@Override
	public void createOutFileDirectory(String fix){
		createScriptDirectory();
		//System.out.println("fix:"+fix);
		createDirectoryByCode(fix);
	}
	private void createDirectoryByCode(String dirName){
		File outFileDir = new File(Setting.scriptPath+File.separator+dirName);
		if(!outFileDir.exists()){
			outFileDir.mkdirs();
			//System.out.println("生成目录:"+outFileDir.getPath());
		}
	}
	public void createScriptDirectory() {
		File scriptFile = new File(Setting.scriptPath+File.separator+"脚本");
		if(!scriptFile.exists()){
			scriptFile.mkdirs();
			//System.out.println("生成目录:"+scriptFile.getPath());
		}
		patchFile = new File(Setting.scriptPath+File.separator+"脚本"+File.separator+"PATCH.PDC");
		if(!patchFile.exists()){
			try {
				patchFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public File getPatchFile() {
		createScriptDirectory();
		return patchFile;
	}
	public void setPatchFile(File patchFile) {
		this.patchFile = patchFile;
	}

}
