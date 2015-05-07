

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.mockserver.mock.action.ExpectationCallback;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.Parameter;

import com.google.gson.Gson;

public class MockResponseHandler implements ExpectationCallback {
	
	

	public HttpResponse handle(HttpRequest req) {
	
		HttpResponse response = null;
		List<Parameter> parameters = req.getQueryStringParameters();
		//String countType = null;
		//String releaseFlag = null;
		//String request = null;
		List<String> values = new ArrayList<String>();
		//Gson gson = new Gson();
		
		for (Parameter parameter : parameters) {
			
			
			if (MockConstants.CALLBACK_PARAM.equalsIgnoreCase(parameter.getName())) {
				values = parameter.getValues();
			}
		}
		 
		String responseBody = "";
		try {
			if (MockConstants.SAMPLE_METHOD.equals(req.getPath())) {
				
				responseBody = ResponseUtil.getMockedResponse(MockConstants.SAMPLE_METHOD_RES,"jquery_1312312", "replace_me");
				response = ResponseUtil.getResponseWithContentType(responseBody, MockConstants.JSON);
			}		
			
			else if(MockConstants.SAMPLE_METHOD_XML.equals(req.getPath())) {
				
				responseBody = ResponseUtil.getMockedResponse(MockConstants.SAMPLE_METHOD_XML_RES,"", "");
				response = ResponseUtil.getResponseWithContentType(responseBody, MockConstants.XML);
				
			}
			

		} catch (Exception e) {

			System.out.println("Exception occured while processing the request" + req.getPath() 
					+ e);
		}
		
		
		
		return response;
	}

}
