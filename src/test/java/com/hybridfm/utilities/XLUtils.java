package com.hybridfm.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {
	
	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	
	public static int getRowCount(String xlfile, String xlsheet)
	{
		int rowsnum=0;
		try {
			fis=new FileInputStream(xlfile);
			workbook=new XSSFWorkbook(fis);
			sheet=workbook.getSheet(xlsheet);
			rowsnum=sheet.getLastRowNum();
			workbook.close();
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowsnum;
	}
	
	
	public static int getCellCount(String xlfile,String xlsheet, int xlrownum)
	{
		int cellsnum=0;
		try {
			fis=new FileInputStream(xlfile);
			workbook=new XSSFWorkbook(fis);
			sheet=workbook.getSheet(xlsheet);
			row=sheet.getRow(xlrownum);
			cellsnum=row.getLastCellNum();
			workbook.close();
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cellsnum;
	}
	
	
	public static String getCellData(String xlfile,String xlsheet,int rowcount,int cellcount)
	{
		String data=null;
		try {
			fis=new FileInputStream(xlfile);
			workbook=new XSSFWorkbook(fis);
			sheet=workbook.getSheet(xlsheet);
			row=sheet.getRow(rowcount);
			cell=row.getCell(cellcount);
			try {
			DataFormatter formatter=new DataFormatter();
			String celldata=formatter.formatCellValue(cell);
			return celldata;
			}catch(Exception e)
			{
				data="";
			}
			workbook.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	
	public static void setCellData(String xlfile,String xlsheet,int rowcount,int cellcount, String data)
	{
		try {
		fis=new FileInputStream(xlfile);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet(xlsheet);
		row=sheet.getRow(rowcount);
		cell=row.createCell(cellcount);
		cell.setCellValue(data);
		fos=new FileOutputStream(xlfile);
		workbook.write(fos);
		workbook.close();
		fis.close();
		fos.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
