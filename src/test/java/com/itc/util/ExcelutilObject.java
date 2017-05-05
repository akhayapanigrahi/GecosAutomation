package com.itc.util;

/*
* Copyright @ QA All rights Reserved.
* @version 1.0, April, 2017
* @author Akshaya Kumar Panigrahi
*
* Description: This class contains all the commonly used methods which will be used for Reading and write data for Excel sheet
* This code is implemented for .xlsx sheet. It might not work for .xls sheet.
*
*
*/
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.itc.common.GecosConstants;
public class ExcelutilObject {

	private static XSSFWorkbook ExcelWBook;
	private static XSSFSheet ExcelWSheet;
	 
	
	//private static XSSFWorkbook ExeclWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;

	
//This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method

public static void setExcelFile(String Path,String SheetName) throws Exception 
{

		try {

			// Open the Excel file

		FileInputStream ExcelFile = new FileInputStream(Path);

		// Access the required test data sheet

		ExcelWBook = new XSSFWorkbook(ExcelFile);

		ExcelWSheet = ExcelWBook.getSheet(SheetName);

		} 
		catch (Exception e)
		{

			throw (e);

		}

}

//This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num

public  static String getCellData(int RowNum, int ColNum) throws Exception{
	
	String CellData = null; 

		try{

			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			 
			 
            if (Cell.getCellType()==XSSFCell.CELL_TYPE_STRING) {


                  CellData = Cell.getStringCellValue();


            }else if (Cell.getCellType()==XSSFCell.CELL_TYPE_NUMERIC) {

                /*  This code is required when value of excel sheet is numeric value, not long value   */
                 
            	/*  double i = Cell.getNumericCellValue();

                  CellData = new Double(i).toString();
                  
               CellData = CellData.substring(0, CellData.lastIndexOf('.'));*/
            	
            	 /*  This code is required when value of excel sheet is long value   */
            	long i = (long) Cell.getNumericCellValue();

                CellData = new Long(i).toString();


            } 


			return CellData;

			}
		catch (Exception e)
		{

			return"";

			}

}

//This method is to write in the Excel cell, Row num and Col num are the parameters

public  static void setCellData(String Result,  int RowNum, int ColNum) throws Exception	{

		try{

			Row  = ExcelWSheet.getRow(RowNum);

		Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);

		if (Cell == null) {

			Cell = Row.createCell(ColNum);

			Cell.setCellValue(Result);

			} else {

				Cell.setCellValue(Result);

			}

// Constant variables Test Data path and Test Data file name

				FileOutputStream fileOut = new FileOutputStream(GecosConstants.Path_TestData);

				ExcelWBook.write(fileOut);

				fileOut.flush();

				fileOut.close();

			}catch(Exception e){

				throw (e);

		}

	}
}
