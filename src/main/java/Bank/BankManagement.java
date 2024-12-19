package Bank;

import java.util.Scanner;

import Utilities.DbOperations;
import Utilities.Menu;
import Utilities.Scan;

public class BankManagement {

	
	public static void bankFunc(String bName,String bCode) {
		char choice ;
		boolean result;

		
		do {
			Scanner scanner = Scan.getScanner();
			System.out.println("1)Display Bank Details");
			System.out.println("2)Create an account of a Customer");
			System.out.println("3)Delete an account of a Customer");
			System.out.println("4)Login to Customer Account");
			System.out.println("5)Update account details of a Customer");
			System.out.println("6)Get All Customers in a Bank");
			System.out.println("7)Exit");
			System.out.println("Enter your choice : ");
			int option = scanner.nextInt();
			scanner.nextLine();
			
			DbOperations dbops = new DbOperations();
			Menu menu = new Menu();
			
			
			switch(option) {
			case 1 : dbops.getBankDetailsByName(bName);
						break;
						
			case 2 : result = dbops.getBankDetailsByName(bName);
						menu.createCustomer(bName, result);
						break;
			
			case 3 :
				menu.deleteCustomerAccount();
				break;
				
			case 4 : menu.getCustomer();
					break;
				
			case 5 : menu.updateCustomer();
					break;
			
			case 6 : dbops.getAllCustomersOfBank(bName);
						break;
					
						
			case 7 : dbops.deleteAdmin();
				return;	
			
			
			default : System.out.println("Enter a Valid Choice");
						break;
			}
			System.out.println("Do you want to go back to Bank Portal Y|N??");
			choice = scanner.nextLine().charAt(0);
		}while(choice == 'Y' || choice == 'y');

	}
}
