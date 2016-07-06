package server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectToMysql {

	Connection conn;
	Statement stmt;

	public ConnectToMysql() {

		this.conn = null;
		this.stmt = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Connection getConnection() throws Exception {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost/anotherme?useUnicode=yes&characterEncoding=UTF-8";
		String username = "root";
		String password = "";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

	public void setSQL(String st) {
		try {
			if ((conn != null) && (stmt != null)) {
				if (!conn.isClosed()) {
					conn.close();
				}
				if (!stmt.isClosed()) {
					stmt.close();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			// System.out.println("i am here!!!!");
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
			try {
				if (!conn.isClosed()) {
					conn.close();
				}
				if (!stmt.isClosed()) {
					stmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public ConnectionParm getSql(String st) {

		ConnectionParm con = new ConnectionParm();

		try {
			con.setConn(getConnection());
			con.setStmt(con.getConn().createStatement());

			con.setRs(con.getStmt().executeQuery(st));
			return con;

		} catch (SQLException e) {
			System.out.println("error: failed to get.");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("other error:");
			e.printStackTrace();
		}
		return con;

	}

}