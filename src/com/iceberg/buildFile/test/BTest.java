package com.iceberg.buildFile.test;

import org.junit.Test;

import com.iceberg.buildFile.server.AContextServer;
import com.iceberg.buildFile.service.StartService;

/** 
 * 删除所有生成的文件
 * @author 作者 杨文培 E-mail:1164180794@qq.com 
 * @version 创建时间：Jul 7, 2017 12:05:19 AM  
 */
public class BTest {
	@Test
	public void cleanScript() {
		StartService startService = (StartService) AContextServer.aContextServer.getAppContext().getBean("BDF.startService");
		//startService.cleanScript();
		//startService.start();
		startService.refreshScript();
	}
}
