package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlConnector {
	
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	public void init(){		
        // This will load the MySQL driver, each DB has its own driver
        try {
			Class.forName("com.mysql.jdbc.Driver");
        // Setup the connection with the DB
			this.connection = DriverManager
                .getConnection("jdbc:mysql://localhost/Test_Environment?"
                        + "user=root&password=nomelase");
			
			this.statement = this.connection.createStatement();
        
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return this.connection;
	}	
	
	public Statement getStatement() {
		return this.statement;
	}

	public void close(){
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
            	connection.close();
            }
        } catch (Exception e) {
			e.printStackTrace();
        }
	}
	
	public ResultSet sendQuery(String query){		
		this.resultSet = null;
		try {
	        this.resultSet = this.statement.executeQuery(query);        
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return this.resultSet;
	}
	
	public boolean sendUpdate(String update){
		boolean bresult = true;
		try {
	        this.statement.executeUpdate(update);        
		} catch (SQLException e) {
			bresult = false;
			e.printStackTrace();
		}	
		return bresult;
	}

}
