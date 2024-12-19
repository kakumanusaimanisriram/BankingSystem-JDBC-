package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;


public class Database {
	
	private static Connection conn ;
	
	private static FileInputStream fis;
	
	
	private Database() {
		
	}
	static {

		//admin credentials 
		String query = "insert into admin(admin_name, admin_phoneno, admin_email, admin_password) values(?,?,?,?)";
		System.out.println("Establishing connection");
		PreparedStatement pstmt;
		try {
			System.out.println(Database.getConnection());
			pstmt = Database.getConnection().prepareStatement(query);
			pstmt.setString(1,"bhavya");
			pstmt.setString(2,"483798431");
			pstmt.setString(3,"sribhavya@gmail.com");
			pstmt.setString(4,"sri@123");
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static Connection getConnection() {
		//System.out.println("Establishing connection");
		
		if(conn == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} 
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			try {
				fis = new FileInputStream("config.properties");
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			Properties properties = new Properties();
			try {
				properties.load(fis);
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
			try {
				conn = DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("username"),properties.getProperty("password"));
				//System.out.println("Connection Established");
				//System.out.println(conn);
				
				//change admin details accordingly
				
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			return conn;
		}
		return conn;
		
		
	}

}
