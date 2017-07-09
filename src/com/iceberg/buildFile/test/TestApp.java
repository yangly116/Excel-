package com.iceberg.buildFile.test;

import static org.junit.Assert.fail;

import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.iceberg.buildFile.parseFactory.ParseFactory;
import com.iceberg.buildFile.server.AContextServer;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * @author 作者 杨文培
 * 
 * @since：Jul 8, 2017 9:06:18 AM
 * 
 */
public class TestApp {
	public static AContextServer  aContextServer;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		aContextServer = AContextServer.aContextServer;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		ParseFactory parseFactory= (ParseFactory)aContextServer.getAppContext().getBean("BF.parseFactory");
		Map<String, Object> authorsInfo = parseFactory.getMapFactoryImpl();
		System.out.println("Article Author: ");
		for (String key : authorsInfo.keySet()) {
			System.out.println(key + " : "+authorsInfo.get(key));
		}
	}

}
