package utilities;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import pojo.NameData;

public class DataProviders {
	
	@DataProvider(name="nameDataFromJson")
	public Object[][] getNameDataFromJson() {
		
		try {
			List<NameData> data = JsonReader.getNameData();
			
			Object[][] testData= new Object[data.size()][1];
			
			for(int i=0; i<data.size(); i++) {
				testData[i][0]=data.get(i);
			}
			return testData;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@DataProvider(name="nameDataFromExcel")
	public Object[][] getNameDataFromExcel() {
		
		try {
			ExcelUtil xl= new ExcelUtil(FilePaths.EXCEL_TESTDATA_PATH, "Details");
			int rows= xl.getRowCount();
			int columns= xl.getCellCount();
			
			Object[][] testData = new Object[rows-1][columns];
			
			for(int i=1; i<rows; i++) {
				for(int j=0; j<columns; j++) {
					testData[i-1][j]=xl.getCellData(i,j);
				}
			}
			xl.close();
			return testData;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@DataProvider(name="testDataFromDB")
	public Object[][] getDataFromDB() {
		
		String query= "SELECT * FROM table_name";
		List<Map<String,String>> data= DBUtil.getDataFromDB(query);
		Object[][] testData= new Object[data.size()][2];
		
		for(int i=0; i<data.size(); i++) {
			testData[i][0]= data.get(i).get("uname");
			testData[i][1]= data.get(i).get("pwd");
		}
		
		return testData;
	}

}
