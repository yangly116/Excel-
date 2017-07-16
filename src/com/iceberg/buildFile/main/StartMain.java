package com.iceberg.buildFile.main;

import com.iceberg.buildFile.server.AContextServer;
import com.iceberg.buildFile.service.StartService;

/**
 *
 * @author 作者 杨文培
 * 
 * @since：Jul 8, 2017 12:22:07 PM
 * 
 */
public class StartMain {
	public static void main(String[] args) {
		StartService startService = (StartService) AContextServer.aContextServer.getAppContext().getBean("BDF.startService");
		startService.start();
	}
}
