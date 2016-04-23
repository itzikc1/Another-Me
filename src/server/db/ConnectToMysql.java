package server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ConnectToMysql {

	public ConnectToMysql() {

	}

	public static Connection getConnection() throws Exception {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost/anotherme";
		String username = "root";
		String password = "";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

	public void setSQL(String st) {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
//			System.out.println(st);
			stmt.executeUpdate(st);
//			System.out
//					.println("CreateEmployeeTableMySQL: main(): table created.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("error: failed to create a tabel.");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("other error:");
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ResultSet getSql(String st) {
		Connection conn = null;
		Statement stmt = null;
		List<String> list;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.executeQuery(st);

			Statement statement = conn.createStatement();

			rs = statement.executeQuery(st);
//			System.out.println("Result: , "
//					+ rs.toString());

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
}
