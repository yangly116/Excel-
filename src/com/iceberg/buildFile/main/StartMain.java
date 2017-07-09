package com.iceberg.buildFile.main;

import java.io.File;

import org.junit.Test;

import com.iceberg.buildFile.entity.Ibatis;
import com.iceberg.buildFile.entity.Table;
import com.iceberg.buildFile.test.TestIbatisWork;
import com.iceberg.buildFile.workshop.IbatisMake;
import com.iceberg.buildFile.workshop.TableMake;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * @author 作者 杨文培
 * 
 * @since：Jul 8, 2017 12:22:07 PM
 * 
 */
public class StartMain {
	public static void main(String[] args) {
		StartMain startMain = new StartMain();
		Table table = startMain.callTableWork();
		startMain.callIbatisWork(table);
	}
	public Table callTableWork(){
		File sourceFile = new File(TestIbatisWork.class.getResource("/table/ERPC_WST_PAY.xlsx").getFile());
		File outFile = new File(System.getProperty("user.dir")+"/produce/ERPC_WST_PAY.sql");
		TableMake tableMake = new TableMake();
		tableMake.work(sourceFile, outFile);
		Table table = tableMake.getTable();
		System.out.println("成功生成表结构文件！");
		return table;
	}
	public void callIbatisWork(Table table){
		File sourceFile = new File(TestIbatisWork.class.getResource("/ibatis/ibatis.xml").getFile());
		File outFile = new File(System.getProperty("user.dir")+"/produce/pro-ibatis.xml");
		Ibatis ibatis = new Ibatis();
		ibatis.setId("insertInterfaceData");
		IbatisMake ibatisMake = new IbatisMake(table,ibatis);
		ibatisMake.work(sourceFile, outFile);
		System.out.println("成功生成ibatis文件！");
	}
	
}
