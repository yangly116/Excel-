package com.iceberg.buildFile.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** 
 * 
 * @author 作者：杨文培 
 * @version 创建时间：Jul 11, 2017 9:59:59 PM  
 */

public class TestScanFile {
	public static void main(String[] args) {
		//File outFile = new File(System.getProperty("user.dir")+"/produce/pro-ibatis.xml");
		String filePath = System.getProperty("user.dir")+"/template";
		try {
			List<File> lFiles = new ArrayList<>();
			readfile(filePath,lFiles);
			for (int i = 0; i < lFiles.size(); i++) {
				File file = lFiles.get(i);
				System.out.println("File:"+file.getAbsolutePath()+File.separator+file.getName());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    /**
     * 读取某个文件夹下的所有文件
     */
    public static boolean readfile(String filePath,List<File> lFiles) throws FileNotFoundException, IOException {
            try {

                    File file = new File(filePath);
                    if (!file.isDirectory()) {
                            System.out.println("文件");
                            System.out.println("path=" + file.getPath());
                            System.out.println("absolutepath=" + file.getAbsolutePath());
                            System.out.println("name=" + file.getName());
                            lFiles.add(file);

                    } else if (file.isDirectory()) {
                            System.out.println("文件夹:"+file.toString());
                            String[] filelist = file.list();
                            for (int i = 0; i < filelist.length; i++) {
                                    File readfile = new File(filePath + "\\" + filelist[i]);
                                    if (!readfile.isDirectory()) {
                                            System.out.println("path=" + readfile.getPath());
                                            System.out.println("absolutepath="
                                                            + readfile.getAbsolutePath());
                                            System.out.println("name=" + readfile.getName());
                                            lFiles.add(readfile);

                                    } else if (readfile.isDirectory()) {
                                            readfile(filePath + "\\" + filelist[i],lFiles);
                                    }
                            }

                    }

            } catch (FileNotFoundException e) {
                    System.out.println("readfile()   Exception:" + e.getMessage());
            }
            return true;
    }
}
