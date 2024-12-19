package Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Utilities.Database;

public class CustomerAuthentication {
	
	public static boolean result;
	
	public static boolean isValidCustomer(String acno , String password) {
		Connection con = Database.getConnection();
		String query = "Select * from customer where c_acno = ? and c_password = ?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,acno);
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
