package Bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Utilities.Database;

public class BankAuthentication {
	
private static boolean result ;
	
	public static boolean isValidBank(String bName , String bCode) {
		Connection con = Database.getConnection();
		String query = "Select * from bank where bname = ? and bcode = ?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,bName);
			pstmt.setString(2,bCode);
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
