package lms_db;

import java.sql.Connection;

public class DbTest {
	 static Database database;
	

	public static void main(String[] args) {
		Connection connection = getcon();

	}
	public static Connection getcon() {
		return database.getDatabaseConnection();
	}

}
