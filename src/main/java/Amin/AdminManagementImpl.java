package Amin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Utilities.Database;
import Utilities.Scan;

public class AdminManagementImpl {
	
	
	Connection con = Database.getConnection();
	Scanner scanner = Scan.getScanner();
	PreparedStatement pstmt;
	
	public void createBank(String bName ,String bCode , String bLocation) {
		String query = "insert into bank(bname,bcode,blocation) values(?,?,?)";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,bName);
			pstmt.setString(2,bCode);
			pstmt.setString(3,bLocation);
			int row = pstmt.executeUpdate();
			if(row > 0) {
				System.out.println("Bank Added Successful");
			}	
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	public void deleteBank(String bankName) {
		
		String query1 = "Select * from bank where bname= ?";
		try {
			pstmt = con.prepareStatement(query1);
			pstmt.setString(1,bankName);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				String query2 = "Delete from bank where bname=?";
				pstmt = con.prepareStatement(query2);
				pstmt.setString(1,bankName);
				int row = pstmt.executeUpdate();
				if(row > 0) {
					System.out.println("Deletion of  " +bankName+"  bank Successful");
				}
				
			}
			else {
				System.out.println("No such bank present");
			}
			pstmt.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	public void getAllBanksDetails() {
		String query = "Select * from bank";
		try {
			pstmt = con.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			System.out.println(
	                "----------------------------------------------------------------------------------------------------------------------------------------");
			while(rs.next()) {
				System.out.println("BankEntity [Bankname=" +  rs.getString("bname") + ", Bankcode=" +  rs.getString("bcode") + ", Banklocation=" + rs.getString("blocation") + ", createdAt="
				+  rs.getTimestamp("created_at") + "]");
                       
				      
			}  
			
			System.out.println(
	                "--------------------------------------------------------------------------------------------------------------------------------------");    
	 
	            
	       pstmt.close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateBankDetails(String bName,String bCode,String bLocation) {
		String query = "Select * from bank where bname = ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,bName);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				String query2 = "update bank set bname = ? , bcode = ? , blocation = ? where bname=?";
				pstmt = con.prepareStatement(query2);
				pstmt.setString(1,bName);
				pstmt.setString(2,bCode);
				pstmt.setString(3, bLocation);
				pstmt.setString(4,bName);
				int row = pstmt.executeUpdate();
				if(row > 0) {
					System.out.println("Updating details of "+ bName + " successful");
				}
				pstmt.close();
				
			}
			else {
				System.out.println("No such bank present");
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	


	
	
	
	
	
	
	public void getAllCustomers() {
		String query = "Select * from customer";
		try {
			pstmt = con.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			System.out.println(
	                "------------------------------------------------------------------------------------------------------------");
	            
	          
	 
	            while (rs.next()) {
	            	System.out.println("CustomerEntity [CustomerAccountNumber=" + rs.getString("c_acno") + ", FirstName=" + rs.getString("c_fname")
					+ ", Lastname=" +  rs.getString("c_lname") + ", phoneno=" +  rs.getString("c_phone") + ", email=" + rs.getString("c_email") + ", password=" + rs.getString("c_password")
					+ ", balance=" + rs.getString("c_balance") + ", created_at=" +rs.getString("created_at")+",BankName=" +rs.getString("c_bname")+ "]");
	                
	            }
	            System.out.println(
	                "-------------------------------------------------------------------------------------------------------------------\n");
	       pstmt.close();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
