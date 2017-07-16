package com.iceberg.buildFile.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import com.iceberg.buildFile.entity.FileInfo;
import com.iceberg.buildFile.enums.UsuallyStrEnum;
import com.iceberg.buildFile.service.ExcelService;
import com.iceberg.buildFile.service.ParseFileServic;

/** 
 * 
 * @author 作者 E-mail:1164180794@qq.com 
 * @version 创建时间：2017年3月20日 下午9:27:15  
 */
@Service("ExcelService")
public class ExcelServiceImpl implements ExcelService{
	/**存放sheet*/
	private Vector<Sheet> vSheets = new Vector<Sheet>();
	/**存放表头行*/
	private List<String> columnHeaderList;
	/**存放Map类型数据*/
	private List<Map<String,String>> lmapDatas;
	/**存放List类型数据*/
	private List<List<String>> listData;
	public ExcelServiceImpl() {
	}
	@Override
	public List<List<List<String>>> getExcelAllData(File file){
		initParseExcel(file);
		List<List<List<String>>> lListDatas = getAllsheetData();
		return lListDatas;
	}
	public Map<String, Object> getFileContent(File file) {
		Map<String, Object> map = new HashMap<String, Object>();
		initParseExcel(file);
		StringBuilder builder = new StringBuilder();
		List<List<List<String>>> lListDatas = getAllsheetData();
		for (int i = 0; i < lListDatas.size(); i++) {
			List<List<String>> lists = lListDatas.get(i);
			System.out.println("rows:"+lists.size());
			for (int j = 0; j < lists.size(); j++) {
				List<String> lStrings = lists.get(j);
				for (int k = 0; k < lStrings.size(); k++) {
					String string = lStrings.get(k);
					//System.out.print(string+"|");
					builder.append(string.trim());
				}
				//System.out.println("换行！");
			}
		}
		String result = builder.toString();
		map.put(UsuallyStrEnum.FILECONTENT.getText(), result);
		return map;
	}
	/**
	 * 初始化
	 * @param filePath
	 */
	public void initParseExcel(File file){
		 Workbook workbook = initWorkbook(file);//初始化workBook
		 vSheets = initVSheets(workbook);
	}
	private Workbook initWorkbook(File file){
		FileInputStream fileInputStream = null;
		Workbook workBook = null;
		try {
			fileInputStream = new FileInputStream(file);
			workBook = WorkbookFactory.create(fileInputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fileInputStream != null){
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return workBook;
	}
	/**
	 * 初始化VSheets
	 */
	public Vector<Sheet> initVSheets(Workbook workbook){
		Vector<Sheet> vSheets = new Vector<Sheet>();
		 int sheetSize = workbook.getNumberOfSheets();
		 for(int i = 0;i<sheetSize;i++){
        	Sheet sheet = workbook.getSheetAt(i);
        	vSheets.add(sheet);
        }
		 return vSheets;
	}
	/**
	 * 获取所有行List数据
	 * @param sheet
	 * @return
	 */
	private List<List<String>> getListData(Sheet sheet){
		List<List<String>> listData = new ArrayList<List<String>>();
		int numOfRows = sheet.getLastRowNum() + 1;
        for (int i = 0; i < numOfRows; i++) {
        	List<String> lrowData = new ArrayList<String>();
            Row row = sheet.getRow(i);
            if (row != null) {
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    lrowData.add(this.getCellValue(cell));
                }
            }
            listData.add(lrowData);
        }
        return listData;
	}
	
	/**
	 * 获取表头行数据
	 * @param sheet
	 * @return
	 */
	private List<String> getColumnHeaderList(Sheet sheet){
		List<String> columnHeaderList = new ArrayList<String>();
		int numOfRows = sheet.getLastRowNum() + 1;
		for (int i = 0; i < numOfRows; i++) {
			Row row = sheet.getRow(i);
			if (row != null) {
				for (int j = 0; j < row.getLastCellNum(); j++) {
					Cell cell = row.getCell(j);
					if (i == 0) {
						columnHeaderList.add(getCellValue(cell));
					} 
				}
			}
		}
		return columnHeaderList;
	}
	/**
	 * 获取Map类型数据
	 * @param sheet
	 * @return
	 */
	private List<Map<String, String>> getLmapDatas(Sheet sheet) {
		List<Map<String, String>> lmapDatas = new ArrayList<Map<String, String>>();
		int numOfRows = sheet.getLastRowNum() + 1;
		for (int i = 0; i < numOfRows; i++) {
			Row row = sheet.getRow(i);
			Map<String, String> map = new HashMap<String, String>();
			if (row != null) {
				for (int j = 0; j < row.getLastCellNum(); j++) {
					Cell cell = row.getCell(j);
					if (i == 0) {
						columnHeaderList.add(getCellValue(cell));
					} else {
						map.put(columnHeaderList.get(j), this.getCellValue(cell));
					}
				}
			}
			if (i > 0) {
				lmapDatas.add(map);
			}
		}
		return lmapDatas;
	}
    /**
     * 初始化所有数据
     * @param sheet
     */
    private void initAllData(Sheet sheet) {
    	listData = getListData(sheet);
    	columnHeaderList = getColumnHeaderList(sheet);
    	lmapDatas = getLmapDatas(sheet);
    }
    private List<List<List<String>>> getAllsheetData(){
    	List<List<List<String>>> lListDatas = new ArrayList<List<List<String>>>();
    	Vector<Sheet> vSheets = getvSheets();
    	for(int i=0;i<vSheets.size();i++){
    		Sheet sheet = vSheets.get(i);
    		lListDatas.add(getListData(sheet));
    	}
    	return lListDatas;
    }
    private String getCellValue(Cell cell) {
        String cellValue = "";
        DataFormatter formatter = new DataFormatter();
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        cellValue = formatter.formatCellValue(cell);
                    } else {
                        double value = cell.getNumericCellValue();
                        int intValue = (int) value;
                        cellValue = value - intValue == 0 ? String.valueOf(intValue) : String.valueOf(value);
                    }
                    break;
                case Cell.CELL_TYPE_STRING:
                    cellValue = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    cellValue = String.valueOf(cell.getCellFormula());
                    break;
                case Cell.CELL_TYPE_BLANK:
                    cellValue = "";
                    break;
                case Cell.CELL_TYPE_ERROR:
                    cellValue = "";
                    break;
                default:
                    cellValue = cell.toString().trim();
                    break;
            }
        }
        return cellValue.trim();
    }

	public Vector<Sheet> getvSheets() {
		return vSheets;
	}
	public void setvSheets(Vector<Sheet> vSheets) {
		this.vSheets = vSheets;
	}
    
}
