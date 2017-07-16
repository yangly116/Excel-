package com.iceberg.buildFile.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.iceberg.buildFile.service.ScanFileService;
import com.iceberg.buildFile.util.StringUtil;

/** 
 * 
 * @author 作者：杨文培 
 * @version 创建时间：Jul 16, 2017 12:32:48 PM  
 */
@Service("ScanFileService")
public class ScanFileServiceImpl implements ScanFileService {

	@Override
	public boolean scanfFile(String filePath, List<File> lFiles) throws FileNotFoundException, IOException {
		try {

            File file = new File(filePath);
            if (!file.isDirectory()) {
                    lFiles.add(file);
            } else if (file.isDirectory()) {
                    String[] filelist = file.list();
                    for (int i = 0; i < filelist.length; i++) {
                            File readfile = new File(filePath + "\\" + filelist[i]);
                            if (!readfile.isDirectory()) {
                                    lFiles.add(readfile);
                            } else if (readfile.isDirectory()) {
                            	scanfFile(filePath + "\\" + filelist[i],lFiles);
                            }
                    }

            }

    } catch (FileNotFoundException e) {
            System.out.println("readfile()   Exception:" + e.getMessage());
    }
    return true;
	}
	@Override
	public boolean scanfFile(String filePath, List<File> lFiles,String filePFix) throws FileNotFoundException, IOException {
		try {
            File file = new File(filePath);
            if (!file.isDirectory()) {
                    lFiles.add(file);
            } else if (file.isDirectory()) {
                    String[] filelist = file.list();
                    for (int i = 0; i < filelist.length; i++) {
                            File readfile = new File(filePath + "\\" + filelist[i]);
                            if (!readfile.isDirectory()) {
								if(filePFix.equals(StringUtil.getFileNamePfix(readfile.getName()))){
									lFiles.add(readfile);
								}
                            } else if (readfile.isDirectory()) {
                            	scanfFile(filePath + "\\" + filelist[i],lFiles,filePFix);
                            }
                    }

            }

    } catch (FileNotFoundException e) {
            System.out.println("readfile()   Exception:" + e.getMessage());
    }
    return true;
	}
}
