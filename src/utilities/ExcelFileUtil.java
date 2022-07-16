package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {
	XSSFWorkbook wb;
	//constructor for reading excel path
public	ExcelFileUtil (String excelpath) throws Throwable
{
	FileInputStream fi = new FileInputStream(excelpath);
	wb=new XSSFWorkbook(fi);
}
	//method for row count
	public int rowcount(String sheetname)
	{
	return wb.getSheet(sheetname).getLastRowNum();
	}
	//method to coount no of cells in a row
	public int cellcount(String sheetname)
	{
		return wb.getSheet(sheetname).getRow(0).getLastCellNum();
	}
	//method for cell data
	public String getcelldata(String sheetname,int row,int column)
	{
		{
		String data="";
		if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			int celldata=(int)wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
			data=String.valueOf(celldata);
		}
		else
		{
			data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
		}
		return data;
		}
	}
		//method for setcelldata
		public void setcelldata(String sheetname,int row,int column,String status,String writeexcel) throws Throwable
		{
		//get sheet from workbook 
			XSSFSheet ws = wb.getSheet(sheetname);
			//get row from sheet 
			XSSFRow rownum=ws.getRow(row);
			//create cell in a row
			XSSFCell cell = rownum.createCell(column);
			//write status
			cell.setCellValue(status);
			if (status.equalsIgnoreCase("pass"))
			{
				XSSFCellStyle style = wb.createCellStyle();
				XSSFFont font = wb.createFont();
				font.setColor(IndexedColors.BRIGHT_GREEN.getIndex());
				font.setBold(true);
				font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
				style.setFont(font);
				rownum.getCell(column).setCellStyle(style);
			}
			FileOutputStream fo = new FileOutputStream(writeexcel);
			wb.write(fo);
			
		}
		
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	

	
	












	