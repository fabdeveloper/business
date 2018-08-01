package services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import dataManagers.RegistroBusinessServices;
import interfaces.InterfaceBusinessServices;


@Path("/")

public class BusinessServices {
	private final Integer GET_SERVICE = 1;
	private final Integer POST_SERVICE = 2;
	private final Integer PUT_SERVICE = 3;
	private final Integer DELETE_SERVICE = 4;
	
	private InterfaceBusinessServices dataManager;

	
	
	
	//@GET
	@POST
	@Path("/getService")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)	
	public Response getService(InputStream incomingData) {
		System.out.println("ejecutando getService...");
		// leer datos de entrada
		JSONObject jsonRecibido = this.readInputStream(incomingData);
		// determinar el gestor segun el tipo de datos recibido
		this.dataManager = setDataManager(jsonRecibido);		
		// procesar los datos
		JSONObject respuestaJson = this.dataManager.getService(jsonRecibido);	
			
		return Response.status(200).entity(respuestaJson.toString()).build();			
	}
	
	@POST
	@Path("/postService")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response postService(InputStream incomingData) {		
		System.out.println("ejecutando postService...");
		// leer datos de entrada
		JSONObject jsonRecibido = this.readInputStream(incomingData);
		// determinar el gestor segun el tipo de datos recibido
		this.dataManager = setDataManager(jsonRecibido);		
		// procesar los datos
		JSONObject respuestaJson = this.dataManager.postService(jsonRecibido);		
		
		return Response.status(200).entity(respuestaJson.toString()).build();			
	}
	
	@POST
	@Path("/putService")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response putService(InputStream incomingData) {		
		System.out.println("ejecutando putService...");
		// leer datos de entrada
		JSONObject jsonRecibido = this.readInputStream(incomingData);
		// determinar el gestor segun el tipo de datos recibido
		this.dataManager = setDataManager(jsonRecibido);		
		// procesar los datos
		JSONObject respuestaJson = this.dataManager.putService(jsonRecibido);	
		
		return Response.status(200).entity(respuestaJson.toString()).build();			
	}
	
	@POST
	@Path("/deleteService")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response deleteService(InputStream incomingData) {		
		System.out.println("ejecutando deleteService...");
		// leer datos de entrada
		JSONObject jsonRecibido = this.readInputStream(incomingData);
		// determinar el gestor segun el tipo de datos recibido
		this.dataManager = setDataManager(jsonRecibido);		
		// procesar los datos
		JSONObject respuestaJson = this.dataManager.deleteService(jsonRecibido);	
		
		return Response.status(200).entity(respuestaJson.toString()).build();			
	}
	
	
	
	private InterfaceBusinessServices setDataManager(JSONObject jsonRecibido) {
		
		// analizar el tipo de datos recibido
		// ...
		
		// asignar manager
		InterfaceBusinessServices respuesta = new RegistroBusinessServices();
		return respuesta;
	}
	
	private JSONObject readInputStream(InputStream incomingData){
		StringBuilder myStringBuilder = new StringBuilder();
		JSONObject jsonRecibido = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			while ((line = in.readLine()) != null) {
				myStringBuilder.append(line);
			}
			jsonRecibido = new JSONObject(myStringBuilder.toString());

		} catch (Exception e) {
			System.out.println("Error Parsing: - ");
			e.printStackTrace();
		}
		System.out.println("Data Received in -> readInputStream : " + myStringBuilder.toString());		
		return jsonRecibido;
	}
	
	
	
	
	
	/**************************************************************************/
	/**************************************************************************/
	
//	private JSONObject respuestaTest(Integer numService){
//		JSONObject myJsonObj = new JSONObject();
//		String nombreServicio = "desconocido";
//		String contenido = "Respuesta de ";
//
//		
//		switch(numService){
//		case 1:
//			nombreServicio = "getService";
//			break;
//		case 2:
//			nombreServicio = "postService";
//
//			break;
//		case 3:
//			nombreServicio = "putService";
//
//			break;
//		case 4:
//			nombreServicio = "deleteService";
//
//			break;
//			default:
//				break;
//		
//		}
//		try {
//			myJsonObj.put("id", numService);
//			myJsonObj.put("content", contenido+nombreServicio);
//
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			System.out.println("se ha liado el JSONObject...");
//			e.printStackTrace();
//		}	
//		return myJsonObj;
//	}
//	
//
//	
//	private JSONObject process(InputStream incomingData, Integer numService){		
//		JSONObject datosRecibidos = this.readInputStream(incomingData);
//		JSONObject respuestaJson = null;
//		Integer id = -1;
//		try {
//			id = datosRecibidos.getInt("id");
//		} catch (JSONException e) {
//			System.out.println("JSONException ejecutando -> process..." + numService);
//			System.out.println("no ID encontrado, se asigna -1");
//			//e.printStackTrace();
//			id = -1;
//		}catch (Exception ex) {
//			System.out.println("Exception ejecutando -> process..." + numService);
//			System.out.println("no ID encontrado, se asigna -1");
//			//ex.printStackTrace();
//			id = -1;
//		}
//		if(id == -1){
//			respuestaJson = this.respuestaTest(numService);			
//		}else{
//			// PROCESS
//			System.out.println("Procesando ..." + numService);
//			respuestaJson = datosRecibidos;			
//		}
//		return respuestaJson;
//		
//		
//	}

	

}
