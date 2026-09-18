package payloads;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import utilities.FilePaths;

public class BookingPayload {
	
	public static String getPostRequestBody() throws IOException {
		//return FileUtils.readFileToString(new File(FilePaths.POST_BODY_PATH), "UTF-8");
		return Files.readString(Paths.get(FilePaths.POST_BODY_PATH));
		//return new File(FilePaths.POST_BODY_PATH);
	}
	
	public static String getPutRequestBody() throws IOException {
		return FileUtils.readFileToString(new File(FilePaths.PUT_BODY_PATH), "UTF-8");
	}
	
	public static String getPatchRequestBody() {
		
		JSONObject data= new JSONObject();
		data.put("firstname", "Golu");
		data.put("lastname", "Polu");
		
		return data.toString();
	}

}
