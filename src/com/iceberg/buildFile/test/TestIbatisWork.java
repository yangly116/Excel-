package com.iceberg.buildFile.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.iceberg.buildFile.entity.Ibatis;
import com.iceberg.buildFile.entity.Table;
import com.iceberg.buildFile.parseFactory.ParseFactory;
import com.iceberg.buildFile.server.AContextServer;
import com.iceberg.buildFile.util.StringUtil;
import com.iceberg.buildFile.workshop.IbatisMake;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * @author 作者 杨文培
 * 
 * @since：Jul 8, 2017 12:23:34 PM
 * 
 */
public class TestIbatisWork {
	public static IbatisMake ibatisMake;
	File sourceFile = new File(TestIbatisWork.class.getResource("/ibatis/ibatis.xml").getFile());
	File outFile = new File(System.getProperty("user.dir")+"/produce/pro-ibatis.xml");
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Table table = getTable();
		Ibatis ibatis = getIbatis();
		ibatisMake = new IbatisMake(table,ibatis);
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
		ibatisMake.work(sourceFile, outFile);
	}
	public static Table getTable(){
		File tableFile = new File(TestIbatisWork.class.getResource("/table/table.txt").getFile());
		String tableStr = null;
		try {
			tableStr = FileUtils.readFileToString(tableFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> fields = StringUtil.splitToList(tableStr, ",");
		Table table = new Table();
		table.setPk("pId");
		table.setTableName("ERPC_WST_PAY");
		table.setSeq("SEQ_ERPC_WST_PAY");
		table.setFields(fields);
		return table;
	}
	public static Ibatis getIbatis(){
		Ibatis ibatis = new Ibatis();
		ibatis.setId("insertInterfaceData");
		return ibatis;
	}
}
