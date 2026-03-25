package util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
	private static final Logger logger = Logger.getLogger(DBConnection.class.getName());

	private static final String USERNAME;
	private static final String URL;
	private static final String PASSWORD;

	static {
		Properties props = new Properties();
		try (InputStream in = DBConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
			if (in == null)
				throw new RuntimeException("db.properties not found");
			props.load(in);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Failed to load db.properties", e);
			throw new RuntimeException("DB config load failed",e);
		}
		URL = props.getProperty("db.url");
		USERNAME = props.getProperty("db.username");
		PASSWORD = props.getProperty("db.password");
	}

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);

	}
}
