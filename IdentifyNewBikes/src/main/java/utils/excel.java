package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class excel {
	public static int rowCount = 0;
	public static void writeToExcel(String str, int rowNo, int cellNo) throws IOException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\output.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		//Get the current count of rows in excel file

	    //int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();

	    //Get the first row from the sheet

	    Row row = sheet.getRow(0);

	    //Create a new row and append it at last of sheet

	    if (rowNo < rowCount) {
	    	Cell cell = sheet.getRow(rowNo).createCell(cellNo);
	    	cell.setCellValue(str);
	    }
	    else {
	    Row newRow = sheet.createRow(rowNo);
	    Cell cell = newRow.createCell(cellNo);
	    cell.setCellValue(str);
	    rowCount++;
	    }
	    
	            
        
        FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "\\output.xlsx");
		workbook.write(fos);
		fos.close();
	}
}
