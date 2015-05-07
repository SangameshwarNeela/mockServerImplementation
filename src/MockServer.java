

import static org.mockserver.integration.ClientAndServer.startClientAndServer;

import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpCallback;
import org.mockserver.model.HttpRequest;

public class MockServer {

	
	public static void main(String[] args) {
		  
		ClientAndServer mockServer = startClientAndServer(MockConstants.MOCK_SERVER_PORT);
		//MockServerClient mockServer = new MockServerClient("localhost", 8082, "/mockserver");
		HttpRequest request = new HttpRequest();
		
		// Mapping a request and generate a dynamic response based on the request query parameters
		
		HttpCallback httpCallback = new HttpCallback();		
		httpCallback.withCallbackClass(MockResponseHandler.class.getName());	
		
		request.withMethod("GET");
		request.withPath(MockConstants.SAMPLE_METHOD);
		mockServer.when(request).callback(httpCallback);	
		
		request.withMethod("GET");
		request.withPath(MockConstants.SAMPLE_METHOD_XML);
		mockServer.when(request).callback(httpCallback);
		
	}

}
