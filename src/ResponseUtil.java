

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.mockserver.model.Header;
import org.mockserver.model.HttpResponse;

import com.google.gson.Gson;

public class ResponseUtil {

	public static String getMockedResponse(String fileName, String newValue,
			String oldValue) {

		String jsonString = getMockedResponse(fileName);

		String responseBody = jsonString.replaceFirst(oldValue, newValue);

		return responseBody;

	}
	public static String getMockedResponseForJson(String fileName) {

		String jsonString = getMockedResponse(fileName);

		//String responseBody =getMockResponseBody(newValue, jsonString);

		return jsonString;

	}
	public static Object jsonToObject(String jsonString, Object obj) 
	{
		Gson gson = new Gson();
		//String fromObjectToJson = gson.toJson(obj); 	
		
		return gson.fromJson(jsonString, Object.class);
	}
	
	public static String objToJson(Object obj) 
	{
		Gson gson = new Gson();
		String fromObjectToJson = gson.toJson(obj); 	
		System.out.println("the response " +fromObjectToJson);
		return fromObjectToJson;
	}
	
	public static String getMockResponseBody(String newValue, String fromObjectToJson) 
	{
		String finalString = new StringBuilder().append(newValue).append("(").append(fromObjectToJson).append(")").toString();	
		System.out.println("the response " +finalString);
		return finalString;
	}

	public static String getMockedResponse(String fileName) {
		StringBuilder responseBody = new StringBuilder();

		try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName), Charset.defaultCharset())){
		      String line = null;
		      while ((line = reader.readLine()) != null) {
		    	  responseBody.append(line);
		      }      
		  } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("the response " +responseBody.toString());
		return responseBody.toString();

	}
	
	public static HttpResponse getResponseWithContentType(String responseBody,
			String resType) {

		HttpResponse response = HttpResponse.response().withBody(responseBody);
		String contentType = "";

		if (resType.equals(MockConstants.JSON)) {
			contentType = "application/json";
		} else if (resType.equals(MockConstants.XML)) {
			contentType = "application/xml";
		}
		Header header = Header.header("Content-Type", contentType);
		return response.withHeader(header);
	}

}
