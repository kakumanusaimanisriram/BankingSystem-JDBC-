package Amin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import Utilities.Database;

public class AdminAuthentication {
	
	
	private static boolean result ;
	
	public static boolean isAdmin(String email , String password) {
		Connection con = Database.getConnection();
		String query = "Select admin_email , admin_password from admin where admin_email = ? and admin_password = ?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,email);
			pstmt.setString(2,password);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				result = true;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	
	
}
