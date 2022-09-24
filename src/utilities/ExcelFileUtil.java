package utilities;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {
Workbook wb;
//constructor for reading excel path
public ExcelFileUtil(String Excelpath) throws Throwable {
	FileInputStream fi=new FileInputStream(Excelpath);
	wb=WorkbookFactory.create(fi);
}
//count no of rows in sheet
public int rowCount(String sheetname) {
	return wb.getSheet(sheetname).getLastRowNum();
}
//count no of cells in first row
public int cellCount(String sheetname) {
	return wb.getSheet(sheetname).getRow(0).getLastCellNum();
}
public String getCellData(String sheetname,int row,int column) {
	String data="";
	if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
	{
		int celldata=(int) wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
		//convert integer into string
		 data=String.valueOf(celldata);
}else
{
	data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
}
	return data;		
}

//write cell data
public void setCellData(String sheetname,int row,int column,String status,String writeexcelpath) throws Throwable {
	//get sheet from workbook
	Sheet ws=wb.getSheet(sheetname);
	//get row from sheet
	Row rownum=ws.getRow(row);
	//create cell
	Cell cell=rownum.createCell(column);
	//write status
	cell.setCellValue(status);
	if(status.equalsIgnoreCase("PASS"))
	{
		CellStyle style=wb.createCellStyle();
		Font font=wb.createFont();
		font.setColor(IndexedColors.BRIGHT_GREEN.getIndex());
		font.setBold(true);
		font.setBoldweight(font.BOLDWEIGHT_BOLD);
		style.setFont(font);
		ws.getRow(row).getCell(column).setCellStyle(style);
	}else if(status.equalsIgnoreCase("FAIL")) {
		CellStyle style=wb.createCellStyle();
		Font font=wb.createFont();
		font.setColor(IndexedColors.RED.getIndex());
		font.setBold(true);
		font.setBoldweight(font.BOLDWEIGHT_BOLD);
		style.setFont(font);
		ws.getRow(row).getCell(column).setCellStyle(style);
	}else if(status.equalsIgnoreCase("BLOCKED"))
	{
		CellStyle style=wb.createCellStyle();
		Font font=wb.createFont();
		font.setColor(IndexedColors.BLUE.getIndex());
		font.setBold(true);
		font.setBoldweight(font.BOLDWEIGHT_BOLD);
		style.setFont(font);
		ws.getRow(row).getCell(column).setCellStyle(style);
	}
	FileOutputStream fo=new FileOutputStream(writeexcelpath);
	wb.write(fo);	
}


}

