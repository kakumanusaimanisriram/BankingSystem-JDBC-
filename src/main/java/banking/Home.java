package banking;

import java.sql.SQLException;
import java.util.Scanner;

import Amin.AdminAuthentication;
import Amin.AdminManagement;
import Bank.BankAuthentication;
import Bank.BankManagement;
import Customer.CustomerAuthentication;
import Customer.CustomerManagement;
import Utilities.Database;
import Utilities.DbOperations;
import Utilities.Scan;

public class Home {
	public static void main(String[] args) {
		char choice;
		do {
			Scanner scanner = Scan.getScanner();
			System.out.println(" ---->||    Welcome to Banking Application    ||<----- ");
			System.out.println("1)Administrator portal");
			System.out.println("2)Bank portal");
			System.out.println("3)Customer portal");
			System.out.println("4)Exit");
			System.out.println("Enter your choice : ");
			int option = scanner.nextInt();
			scanner.nextLine();
			DbOperations dbops = new DbOperations();
			switch(option) {
			case 1 : System.out.println("Enter registered email : ");
					String email = scanner.nextLine();
					System.out.println("Enter password : ");
					String password = scanner.nextLine();
					boolean isValid = AdminAuthentication.isAdmin(email, password);
					if(isValid) {
						System.out.println("Admin Authentication Successful");
						System.out.println(" \"\\n ->||    Welcome to Adminstrator Portal    ||<- \\n");
						AdminManagement.adminFunc();
						
						
					}
					else {
						System.out.println("Authentication Failed !!! Invalid Credentials or Admin Not registered");
						
					}
					
					break;
			
			
			case 2 :  System.out.println("Enter registered Bank Name : ");
				String bName = scanner.nextLine().toUpperCase();
				System.out.println("Enter registered Bank Code : ");
				String bCode = scanner.nextLine();
				isValid = BankAuthentication.isValidBank(bName,bCode);
				if(isValid) {
					System.out.println("Bank Authentication Successful");
					System.out.println("--->||    Welcome to "+bName+" BANK Portal    ||<---");
					BankManagement.bankFunc(bName, bCode);
					
					
				}
				else {
					System.out.println("Authentication Failed !!! Invalid Credentials");
					
				}
				break;
					
			case 3 :  System.out.println("Enter accountNo : ");
			String acno = scanner.nextLine().toUpperCase();
			System.out.println("Enter registered password : ");
			password = scanner.nextLine();
			isValid = CustomerAuthentication.isValidCustomer(acno,password);
			if(isValid) {
				System.out.println("Customer Authentication Successful");
				System.out.println("--->|| Welcome ||<---");
				CustomerManagement.customerFunc();
				
				
			}
			else {
				System.out.println("Authentication Failed !!! Invalid Credentials");
				
			}
			break;
			
			case 4 :System.out.println("Logout Successfull");
			try {
				dbops.deleteAdmin();
				Database.getConnection().close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			return;
			
			default : System.out.println("Enter a valid choice : ");
						break;
			}
			
			System.out.println("Do you want to return to main page ?? Y | N");
			choice = scanner.next().charAt(0);
			
		}while(choice == 'Y' || choice == 'y');
		
		
	}
}
