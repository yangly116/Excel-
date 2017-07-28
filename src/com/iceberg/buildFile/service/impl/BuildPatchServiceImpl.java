package com.iceberg.buildFile.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.stereotype.Service;

import com.iceberg.buildFile.main.Setting;
import com.iceberg.buildFile.service.BuildPatchService;
import com.iceberg.buildFile.service.ScanFileService;
import com.iceberg.buildFile.util.BFileUtil;

/** 
 * 
 * @author 作者：杨文培 
 * @version 创建时间：Jul 22, 2017 5:44:04 PM  
 */
@Service("BuildPatchServiceImpl")
public class BuildPatchServiceImpl implements BuildPatchService {
	@Resource
	private CreatFileServiceImpl creatFileServiceImpl;
	@Resource
	private ScanFileService scanFileService;
	File patchFile = new File(Setting.scriptPath+File.separator+"脚本"+File.separator+"PATCH.PDC");
	/* (non-Javadoc)
	 * @see com.iceberg.buildFile.service.BuildPatchService#buildPatch(java.lang.String)
	 */
	@Override
	public void buildPatch(String outStr) {
		creatFileServiceImpl.createScriptDirectory();
		BFileUtil.write(patchFile, outStr);
	}

	/* (non-Javadoc)
	 * @see com.iceberg.buildFile.service.BuildPatchService#cleanPatch()
	 */
	@Override
	public void cleanPatch() {
		creatFileServiceImpl.createScriptDirectory();
		BFileUtil.clearFile(patchFile);
	}

	/* (non-Javadoc)
	 * @see com.iceberg.buildFile.service.BuildPatchService#cleanScript()
	 */
	@Override 
	public void cleanScript() {
		File scriptFile = new File(Setting.scriptPath);
		if(scriptFile.exists()){
			if(scriptFile.isDirectory()){
				try {
					FileUtils.deleteDirectory(scriptFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void refreshScript() {
		File patchFile = creatFileServiceImpl.getPatchFile();
		BFileUtil.clearFile(patchFile);
		List<File> lFiles = new ArrayList<>();
		try {
			List<String> diretorys = new ArrayList<>();//不扫描的目录
			diretorys.add("fdlk");
			scanFileService.scanfFileNoPFix(Setting.scriptPath, lFiles,".PDC",diretorys);
			for (int i = 0; i < lFiles.size(); i++) {
				File file = lFiles.get(i);
				String filePath = file.getAbsolutePath();
				filePath = filePath.substring(Setting.scriptPath.length()+1, filePath.length());
				String parentPath = file.getParent()+File.separator+filePath;
				parentPath = parentPath.substring(file.getParent().length()+1, parentPath.length());
				BFileUtil.write(patchFile, "@"+parentPath+";\r\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
