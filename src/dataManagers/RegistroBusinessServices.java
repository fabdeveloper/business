package dataManagers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import db.MysqlConnector;
import interfaces.InterfaceBusinessServices;

public class RegistroBusinessServices implements InterfaceBusinessServices {
	
	private MysqlConnector db;

	public RegistroBusinessServices() {
		super();

		this.db = new MysqlConnector();
	}

	@Override
	public JSONObject getService(JSONObject incomingData) {
		Integer id;
		String content;
		String select;
		boolean selectresult = false;
		ResultSet resultSet = null;
		JSONObject resultJson = null;
		
		if(incomingData != null){
			try {
				id = incomingData.getInt("id");
				//content = incomingData.getString("content");	
				select = "SELECT * FROM registros WHERE id = " + id + ";";
				// insertar en BD
				this.db.init();
				resultSet = this.db.sendQuery(select);
				resultJson = resultSetToJson(resultSet);

				this.db.close();
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("incomingData es null en -> getService");
		}
		return resultJson;		
	}

	
	
	private JSONObject resultSetToJson(ResultSet resultSet) {
		JSONObject resultJson = null;
		if(resultSet != null){
			try {
				while (resultSet.next()) {
					
				    Integer id = resultSet.getInt(1);
				    String content = resultSet.getString(2);
				    
				    resultJson = new JSONObject();
				    resultJson.put("id", id);
				    resultJson.put("content", content);
				    
//				    System.out.println("id: " + id);
//				    System.out.println("content: " + content);
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}		
		return resultJson;
	}

	@Override
	public JSONObject postService(JSONObject incomingData) {
		// recibe un objeto registro y lo inserta en BD
		Integer id;
		String content;
		String insert;
		boolean insertresult = false;
		
		if(incomingData != null){
			try {
				id = incomingData.getInt("id");
				content = incomingData.getString("content");	
				insert = "INSERT INTO registros VALUES(" + id + ",'" + content + "');";
				// insertar en BD
				this.db.init();
				insertresult = this.db.sendUpdate(insert);
				this.db.close();
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		JSONObject result = new JSONObject();
		try {
			result.put("id", 0);
			result.put("content", insertresult);

		} catch (JSONException e) {
			e.printStackTrace();
		}		
		return result;
	}

	@Override
	public JSONObject putService(JSONObject incomingData) {
		Integer id;
		String content;
		String update;
		boolean insertresult = false;
		
		if(incomingData != null){
			try {
				id = incomingData.getInt("id");
				content = incomingData.getString("content");	
				update = "UPDATE registros SET content = '" + content + "' WHERE id = " + id + ";";
				// insertar en BD
				this.db.init();
				insertresult = this.db.sendUpdate(update);
				this.db.close();
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		JSONObject result = new JSONObject();
		try {
			result.put("id", 0);
			result.put("content", insertresult);

		} catch (JSONException e) {
			e.printStackTrace();
		}		
		return result;
	}

	@Override
	public JSONObject deleteService(JSONObject incomingData) {
		Integer id;
		String delete;
		boolean insertresult = false;
		
		if(incomingData != null){
			try {
				id = incomingData.getInt("id");
				delete = "DELETE FROM registros WHERE id = " + id + ";";
				// insertar en BD
				this.db.init();
				insertresult = this.db.sendUpdate(delete);
				this.db.close();
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		JSONObject result = new JSONObject();
		try {
			result.put("id", 0);
			result.put("content", insertresult);

		} catch (JSONException e) {
			e.printStackTrace();
		}		
		return result;
	}

}
