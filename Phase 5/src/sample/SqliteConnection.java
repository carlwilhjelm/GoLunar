package sample;

import java.sql.Connection;
import java.sql.DriverManager;


/*
 * SqliteConnection class establishes connection to DB using static method
 */
public class SqliteConnection {
	public static Connection Connector() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + Start.holdPath);
			return conn;
		}
		catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
