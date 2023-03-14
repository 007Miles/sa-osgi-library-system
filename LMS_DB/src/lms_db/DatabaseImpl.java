package lms_db;

import java.sql.Connection;
//import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.*;

public class DatabaseImpl implements Database{

	private Connection connection;
	private final String diverName;
	private String databaseConnectionLink;
	private String databaseUser;
	private String databasePassword;
	
	public DatabaseImpl() {
//		this.diverName = "com.mysql.jdbc.Driver";
//		this.databaseConnectionLink = "jdbc:mysql://localhost:3306/hms_db";
//		this.databaseUser = "root";
//		this.databasePassword = "";
		
		this.diverName = "com.mysql.jdbc.Driver";
		
		this.databaseConnectionLink = "jdbc:mysql://localhost:3306/hospdb";
		this.databaseUser = "hospadmin";
		this.databasePassword = "pass";
		
//		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospdb", "hospadmin", "pass");
	}

	@Override
	public Connection getDatabaseConnection() {
		// TODO Auto-generated method stub
		try {
			Class.forName(diverName);
			connection = (Connection) DriverManager.getConnection(databaseConnectionLink, databaseUser, databasePassword);
		} catch (ClassNotFoundException exc) {
			System.out.println("Class not found");
			System.out.println(exc.getMessage());
		} catch (SQLException exc) {
			System.out.println("SQL Error !!!");
			System.out.println(exc.getMessage());
		} finally {
			System.out.println("check");
//			return connection;
		}
		return connection;
	}

}
