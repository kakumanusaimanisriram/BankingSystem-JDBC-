package Utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DbOperations {

	Connection con = Database.getConnection();
	Scanner scanner = Scan.getScanner();
	PreparedStatement pstmt;

	public boolean getBankDetailsByName(String bankName) {
		boolean isPresent = false;
		String query = "Select * from bank where bname = ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bankName);
			ResultSet rs = pstmt.executeQuery();

			System.out.println(
					"----------------------------------------------------------------------------------------------------------------------------------------");
			if (rs.next()) {
				isPresent = true;

				System.out.println("BankEntity [bname=" + rs.getString("bname") + ", bcode=" + rs.getString("bcode")
						+ ", blocation=" + rs.getString("blocation") + ", createdAt=" + rs.getTimestamp("created_at")
						+ "]");

				System.out.println(
						"--------------------------------------------------------------------------------------------------------------------------------------");

			} else {
				System.out.println("No such bank present");
				System.out.println(
						"----------------------------------------------------------------------------------------------------------------------------------------");
			}
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isPresent;

	}

	public void createCustomerAccountforSpecifiedBank(String bankName, String cFirstName, String cLastName,
			String phone, String email, String password) {

		String query = "Select * from bank where bname = ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bankName);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String query2 = "insert into customer(c_acno,c_fname,c_lname,c_phone,c_email,c_password,c_balance,c_bname) values(?,?,?,?,?,?,?,?)";
				pstmt = con.prepareStatement(query2);
				pstmt.setString(1, AccountNumberGenerator.getac());
				pstmt.setString(2, cFirstName);
				pstmt.setString(3, cLastName);
				pstmt.setString(4, phone);
				pstmt.setString(5, email);
				pstmt.setString(6, password);
				pstmt.setDouble(7, 500);
				pstmt.setString(8, bankName);
				int row = pstmt.executeUpdate();
				if (row > 0) {
					System.out.println("Creating Account with provided details successful");
				}
				pstmt.close();

			} else {
				System.out.println("No such bank with id " + bankName + " present");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deleteCustomerAccount(String accountno, String password) {

		String query = "Select * from customer where c_acno = ? and c_password = ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, accountno);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String query1 = "delete from customer where c_acno = ? and c_password = ?";
				pstmt = con.prepareStatement(query1);
				pstmt.setString(1, accountno);
				pstmt.setString(2, password);
				int row = pstmt.executeUpdate();
				if (row > 0) {
					System.out.println("Deletion of Account with provided details successful");
				} else {
					System.out.println("Something went wrong try again!!!");
				}

				pstmt.close();

			} else {
				System.out.println("Invalid Credentials or No such customer present");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public boolean isValidCustomer(String accountno, String password) {
		boolean result = false;
		String query = "Select * from customer where c_acno = ? and c_password = ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, accountno);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void getCustomerDetails(String accountno, String password) {
		String query = "Select * from customer where c_acno = ? and c_password = ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, accountno);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();

			System.out.println(
					"---------------------------------------------------------------------------------------------------------------------------");

			if (rs.next()) {
				System.out.println("CustomerEntity [CustomerAccountNumber=" + rs.getString("c_acno") + ", FirstName="
						+ rs.getString("c_fname") + ", Lastname=" + rs.getString("c_lname") + ", phoneno="
						+ rs.getString("c_phone") + ", email=" + rs.getString("c_email") + ", password="
						+ rs.getString("c_password") + ", balance=" + rs.getString("c_balance") + ", created_at="
						+ rs.getString("created_at") + ",BankName=" + rs.getString("c_bname") + "]");

				System.out.println(
						"--------------------------------------------------------------------------------------------------------------------------------\n");
				pstmt.close();
			} else {
				System.out.println("No such Customer present or Invalid Credentials");
				System.out.println("-----------------------------------------------------------\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void updateCustomerAccount(String accountno, String cFirstName, String cLastName, String phone, String email,
			String password) {
		String query1 = "update customer set c_fname=?,c_lname=?,c_phone=?,c_email=?,c_password=? where c_acno=?";
		try {
			pstmt = con.prepareStatement(query1);
			pstmt.setString(1, cFirstName);
			pstmt.setString(2, cLastName);
			pstmt.setString(3, phone);
			pstmt.setString(4, email);
			pstmt.setString(5, password);
			pstmt.setString(6, accountno);
			int row = pstmt.executeUpdate();
			if (row > 0) {
				System.out.println("Updating Cusomer Details successful");
				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void getAllCustomersOfBank(String bName) {
		String query = "Select * from customer where c_bname=?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bName);
			ResultSet rs = pstmt.executeQuery();

			System.out.println(
					"---------------------------------------------------------------------------------------------------------------------------");

			while (rs.next()) {
				System.out.println("CustomerEntity [CustomerAccountNumber=" + rs.getString("c_acno") + ", FirstName="
						+ rs.getString("c_fname") + ", Lastname=" + rs.getString("c_lname") + ", phoneno="
						+ rs.getString("c_phone") + ", email=" + rs.getString("c_email") + ", password="
						+ rs.getString("c_password") + ", balance=" + rs.getString("c_balance") + ", created_at="
						+ rs.getString("created_at") + ",BankName=" + rs.getString("c_bname") + "]");

			}

			System.out.println(
					"--------------------------------------------------------------------------------------------------------------------------------\n");
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void transferAmount() {

	}

	public void depositAmount(String acno, String password, double amount) {
		boolean result = isValidCustomer(acno, password);
		if (result) {
			String query = "select * from customer where c_acno = ? and c_password = ?";
			try {
				con.setAutoCommit(false);
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, acno);
				pstmt.setString(2, password);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					con.setSavepoint();
					double balance = rs.getDouble("c_balance");
					balance = balance + amount;
					String query1 = "update customer set c_balance = ? where c_acno = ? and c_password = ?";
					pstmt = con.prepareStatement(query1);
					pstmt.setDouble(1, balance);
					pstmt.setString(2, acno);
					pstmt.setString(3, password);
					int row = pstmt.executeUpdate();
					if(row > 0) {
						con.commit();
						System.out.println("--------------------------------------");
						System.out.println("Credited Successfull");
						System.out.println("CurrentBalance  : " + balance);
						System.out.println("----------------------------------------");

						
					}
					
					
				} else {
					System.out.println("Could Complete Action Try Again");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public void withDraw(String acno, String password, double amount) {
		boolean result = isValidCustomer(acno, password);
		if (result) {
			String query = "select c_balance from customer where c_acno = ? and c_password = ?";
			try {
				con.setAutoCommit(false);
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, acno);
				pstmt.setString(2, password);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					con.setSavepoint();
					double balance = rs.getDouble("c_balance");
					if (balance >= amount) {
						balance = balance - amount;
						String query1 = "update customer set c_balance = ? where c_acno = ? and c_password = ?";
						pstmt = con.prepareStatement(query1);
						pstmt.setDouble(1, balance);
						pstmt.setString(2, acno);
						pstmt.setString(3, password);
						int row = pstmt.executeUpdate();
						if(row > 0) {
							con.commit();
							System.out.println("--------------------------------------");
							System.out.println("Debit Successfull");
							System.out.println("Amount Debited : " + amount);
							System.out.println("CurrentBalance  : " + balance);
							System.out.println("----------------------------------------");

						}
						
						

					} else {
						System.out.println("InSufficient Funds");
					}

				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	public void transferAmount(String senderac, String senderpassword, String receiverac, double amount) {
		String query = "Select c_password from customer where c_acno = ?";
		try {
			//con.setAutoCommit(false);
			//con.setSavepoint();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, receiverac);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				withDraw(senderac, senderpassword, amount);
				depositAmount(receiverac, rs.getString("c_password"), amount);

			}

		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public boolean validateCustomerByAcNo(String acno) {
		boolean result = false;
		String query = "select * from customer where c_acno = ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, acno);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public  void deleteAdmin() {
		String query = "delete from admin where admin_email = 'sribhavya@gmail.com'";
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void getAllBankNames() {
		String query = "Select b_name from bank";
		try {
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("---------------------------------------------------------");
			while(rs.next()) {
				System.out.println(rs.getString("b_name"));
			}
			System.out.println("--------------------------------------------------------");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
