package utilities;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pojo.NameData;

public class JsonReader {
	
	public static List<NameData> getNameData() throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		
		File file = new File(FilePaths.JSON_TESTDATA_PATH);
		
		return mapper.readValue(file, new TypeReference<List<NameData>>() {});
	}

}
