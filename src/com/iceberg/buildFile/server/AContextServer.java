package com.iceberg.buildFile.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * @author 作者 杨文培
 * 
 * @since：Jul 8, 2017 8:54:52 AM
 * 
 */
public class AContextServer {
	private ApplicationContext appContext;
	public static AContextServer aContextServer = new AContextServer();
	private AContextServer() {
	}
	public ApplicationContext getAppContext() {
		if(appContext == null){
			appContext = new ClassPathXmlApplicationContext(new String[] {"BDF-applicationContext.xml"});
		}
		return appContext;
	}
	public void setAppContext(ApplicationContext appContext) {
		this.appContext = appContext;
	}
	public static AContextServer getaContextServer() {
		return aContextServer;
	}
	public static void setaContextServer(AContextServer aContextServer) {
		AContextServer.aContextServer = aContextServer;
	}
	
}
