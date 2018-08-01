package interfaces;

import org.json.JSONObject;

public interface InterfaceBusinessServices {
	
	public JSONObject getService(JSONObject incomingData);
	public JSONObject postService(JSONObject incomingData);
	public JSONObject putService(JSONObject incomingData);
	public JSONObject deleteService(JSONObject incomingData);


}
