package com.iceberg.buildFile.test;

import static org.junit.Assert.fail;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.iceberg.buildFile.myenum.UsuallyStrEnum;
import com.iceberg.buildFile.service.impl.ExcelServiceImpl;
import com.iceberg.buildFile.util.BFileUtil;

/** 
 * 
 * @author 作者 E-mail:1164180794@qq.com 
 * @version 创建时间：Jul 8, 2017 9:08:14 PM  
 */
public class TestParseExcel {
	public static File sourceFile = new File(TestIbatisWork.class.getResource("/table/ERPC_WST_PAY.xlsx").getFile());
	File outFile = new File(System.getProperty("user.dir")+"/produce/ERPC_WST_PAY.sql");
	public static ExcelServiceImpl tableParseExcelImpl = new ExcelServiceImpl();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
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
		Map<String, Object> map = tableParseExcelImpl.getFileContent(sourceFile);
		String	string = (String) map.get(UsuallyStrEnum.FILECONTENT.getText());
		BFileUtil.write(outFile, string);
		//System.out.println(string);
	}

}
