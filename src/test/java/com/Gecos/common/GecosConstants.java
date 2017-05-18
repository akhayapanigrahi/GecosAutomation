package com.Gecos.common;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
public class GecosConstants {

	String TXT_LOGIN_MSG = "";
	public static final String Path_TestData = System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\testDataSheet.xlsx";
	
	
	public String getExcelRowData(String sheetName , int rowNum , int colNum) throws InvalidFormatException, IOException{
		FileInputStream fis = new FileInputStream(Path_TestData);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		String data = row.getCell(colNum).getStringCellValue();
		return data;
	}
	
	public int getRowcount(String sheetName) throws InvalidFormatException, IOException{
		FileInputStream fis = new FileInputStream(Path_TestData);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		int rowCount = sh.getLastRowNum();
		return rowCount;
	}
	
	public void setExcelData(String sheetName , int rowNum , int colNum , String data) throws InvalidFormatException, IOException{
		FileInputStream fis = new FileInputStream(Path_TestData);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		Cell cel = row.createCell(colNum);
		cel.setCellType(cel.CELL_TYPE_STRING);
		
	
	}
	
	
	
}
