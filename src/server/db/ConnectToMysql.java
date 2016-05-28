package server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ConnectToMysql {

	Connection conn;
	Statement stmt;
	Statement statement;
	public ConnectToMysql() {
		
     this.conn=null;
     this.stmt = null;
		
	}

	public static Connection getConnection() throws Exception {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost/anotherme?useUnicode=yes&characterEncoding=UTF-8";
		String username = "root";
		String password = "";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url,username, password);
		return conn;
	}

	public void setSQL(String st) {
		conn = null;
		//Connection conn = null;
		stmt = null;
		//Statement stmt = null;
		try {
			System.out.println("i am here!!!!");
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(st);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("error: failed to create a tabel.");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("other error:");
			e.printStackTrace();
		} finally {
			closeConnection();
// 			try {
// 				//stmt.close();
//				
//				//conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
		}
	}

	public ResultSet getSql(String st) {
		conn = null;
		//Connection conn = null;
		 stmt = null;
		List<String> list;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.executeQuery(st);

			 statement = conn.createStatement();

			rs = statement.executeQuery(st);
			return rs;
		} catch (ClassNotFoundException e) {
			System.out.println("error: failed to load MySQL driver.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("error: failed to get.");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("other error:");
			e.printStackTrace();
		}
		return rs;
		
	}
	public void closeConnection(){

			try {
				System.out.println("close connection");
				this.statement.close();
				this.stmt.close();
				this.conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

	
	}
	
}