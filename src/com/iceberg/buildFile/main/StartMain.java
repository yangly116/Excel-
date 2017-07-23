package com.iceberg.buildFile.main;

import com.iceberg.buildFile.server.AContextServer;
import com.iceberg.buildFile.service.StartService;

/** 
 * 
 * @author 作者：杨文培 
 * @version 创建时间：Jul 19, 2017 10:28:37 PM  
 */

public class StartMain {
	public static void main(String[] args) {
		StartService startService = (StartService) AContextServer.aContextServer.getAppContext().getBean("BDF.startService");
		startService.start();
	}
}
