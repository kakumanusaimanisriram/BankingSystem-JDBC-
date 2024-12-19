package Amin;

import java.util.Scanner;

import Utilities.DbOperations;
import Utilities.Menu;
import Utilities.Scan;

public class AdminManagement {
	
	Scanner scanner= Scan.getScanner();
	
	public static void adminFunc() {
		char choice ;
		String bName;
		boolean result;
		do {
			Scanner scanner = Scan.getScanner();
			System.out.println("1)Create Bank");
			System.out.println("2)Delete Specific Bank");
			System.out.println("3)Get Details of a Bank");
			System.out.println("4)Get All Bank Details");
			System.out.println("5)Update particular Bank using bankName");
			System.out.println("6)Create an account of a Customer in particular Bank");
			System.out.println("7)Delete an account of a Customer in particular Bank");
			System.out.println("8)Get particular Customer Details");
			System.out.println("9)Update an account of a Customer in particular Bank");
			System.out.println("10)Get All Customers");
			System.out.println("11)Exit");
			System.out.println("Enter your choice : ");
			int option = scanner.nextInt();
			scanner.nextLine();
			AdminManagementImpl ad = new AdminManagementImpl();
			DbOperations dbops = new DbOperations();
			Menu menu = new Menu();
			switch(option) {
			
			case 1 : menu.createBank();
					break;
					
			case 2 : menu.deleteSpecificBank();
					break;
					
					
			case 3 : menu.getBankDetails();
					break;
					
					
			case 4 : ad.getAllBanksDetails();
						break;
			
			
			case 5 : menu.updateBank();
					break;
				
			case 6 :System.out.println("Enter bank name : ");
					bName = scanner.nextLine();
					result = dbops.getBankDetailsByName(bName);
					menu.createCustomer(bName, result);
					break;
				
					
			case 7 :
				menu.deleteCustomerAccount();
				break;
				
			case 8 :
				menu.getCustomer();
				break;
				
				
			case 9 : 
				menu.updateCustomer();
				break;
				
				
				
				
			case 10 : ad.getAllCustomers();
						break;
						
				
			case 11: dbops.deleteAdmin();
				return;	
				
				
			default : System.out.println("Enter a Valid Choice");
						break;
			
			
			}
			
			System.out.println("Do you want to go back to admin Portal Y|N??");
			choice = scanner.nextLine().charAt(0);
		}while(choice == 'Y' || choice == 'y');
		
		
		
		
		
	}

}
