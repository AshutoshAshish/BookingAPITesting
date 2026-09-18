package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	
	private FileInputStream fis;
	private FileOutputStream fos;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFRow row;
	private XSSFCell cell;
	
	public ExcelUtil(String filePath, String sheetname) throws IOException {
		fis = new FileInputStream(filePath);
		workbook= new XSSFWorkbook(fis);
		sheet= workbook.getSheet(sheetname);
	}
	
	public int getRowCount() throws IOException {
		return sheet.getLastRowNum();
	}
	
	public int getCellCount() throws IOException {
		return sheet.getRow(1).getLastCellNum();
	}
	
	public String getCellData(int rowNum, int cellNum) {
		cell= sheet.getRow(rowNum).getCell(cellNum);
		DataFormatter formatter= new DataFormatter();
		return formatter.formatCellValue(cell);
	}
	
	public void close() throws IOException {
		workbook.close();
		fis.close();
	}
}
