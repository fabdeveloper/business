package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.MysqlConnector;

public class TestMysqlConnector {
	

	public static void main(String[] args) {
		
		MysqlConnector connector = new MysqlConnector();
		connector.init();
		Connection con = connector.getConnection();
		String select = "SELECT * FROM registros";
		String update = "UPDATE registros set content = 'Matilde' where id = 1";
		String insert = "INSERT INTO registros VALUES(1, 'Maria')";
		String delete = "DELETE FROM registros where id = 1";
		
		//testSelect(select, con);
		//testInsert(insert, con);
		testUpdate(update, con);
		//testDelete(delete, con);
		
		
		
		connector.close();
		

	}
	
	public static void sendQuery(String query, Connection con){
		
		Statement statement;
		ResultSet resultSet = null;
		try {
			statement = con.createStatement();
	        resultSet = statement.executeQuery(query);
	        writeResultSet(resultSet);
        
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public static void sendUpdate(String update, Connection con){
		Statement statement;
		boolean bresult = true;
		try {
			statement = con.createStatement();
	        statement.executeUpdate(update);
        
		} catch (SQLException e) {
			bresult = false;
			e.printStackTrace();
		}finally{
			writeResult(bresult);
		}
		
	}
	
	
	public static void testSelect(String select, Connection con){		
		sendQuery(select, con);		
	}
	
	public static void testInsert(String insert, Connection con){
		sendUpdate(insert, con);
	}
	
	public static void testUpdate(String update, Connection con){
		sendUpdate(update, con);
	}
	
	public static void testDelete(String delete, Connection con){
		sendUpdate(delete, con);
	}
	
	
	private static void writeResultSet(ResultSet resultSet){
		try {
			while (resultSet.next()) {
				
	            Integer id = resultSet.getInt(1);
	            String content = resultSet.getString(2);
	            
	            System.out.println("id: " + id);
	            System.out.println("content: " + content);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void writeResult(boolean bresult){
        System.out.println("Actualizada BD: " + bresult);
	}

}
