package application;

import java.sql.*;
import java.util.logging.Logger;


/*
 * SqliteConnection class establishes connection to DB using static method
 */
public class SqliteConnection {
	public static Connection Connector() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:Uptabled.db");
			return conn;
		}
		catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
