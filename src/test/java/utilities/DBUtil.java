package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtil {
	
	private static String url= "abc.com";
	private static String username= "testUser1";
	private static String password= "password@123";
	
	public static List<Map<String,String>> getDataFromDB(String query) {
		
		List<Map<String,String>> testData= new ArrayList<>();
		
		try {
			Connection conn= DriverManager.getConnection(url, username, password);
			Statement st= conn.createStatement();
			ResultSet rs= st.executeQuery(query);
			ResultSetMetaData metadata= rs.getMetaData();
			int colCount= metadata.getColumnCount();
			//List<Map<String,String>> testData= new ArrayList<>();
			
			while(rs.next()) {
				Map<String,String> rowData= new HashMap<>();
				
				for(int i=1; i<colCount; i++) {
					String colName= metadata.getColumnName(i);
					String value= rs.getString(i);
					rowData.put(colName, value);
				}
				testData.add(rowData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testData;
	}

}
