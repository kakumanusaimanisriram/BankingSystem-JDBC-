package Customer;

import java.util.Scanner;

import Utilities.DbOperations;
import Utilities.Menu;
import Utilities.Scan;

public class CustomerManagement {
	
	public static void customerFunc() {
		char choice ;
		boolean result;
		String bName;

		
		do {
			Scanner scanner = Scan.getScanner();
			System.out.println("1)Create Account");
			System.out.println("2)Delete Account");
			System.out.println("3)Get Account Details / View Balance");
			System.out.println("4)Update Account Details");
			System.out.println("5)Transfer Money");
			System.out.println("6)Deposit Amount ");
			System.out.println("7)WithDraw Amount");
			System.out.println("8)Get All available Banks");
			System.out.println("9)Exit");
			System.out.println("Enter your choice : ");
			int option = scanner.nextInt();
			scanner.nextLine();
			
			DbOperations dbops = new DbOperations();
			Menu menu = new Menu();
			
			
			switch(option) {
			
						
			case 1 : 	System.out.println("Enter your bank name : ");
						bName = scanner.nextLine();
						result = dbops.getBankDetailsByName(bName);
						menu.createCustomer(bName, result);
						break;
			
			case 2 :
				menu.deleteCustomerAccount();
				break;
				
			case 3 : menu.getCustomer();
					break;
				
			case 4 : menu.updateCustomer();
					break;
			
			case 5 : menu.transferAmount();
					break;
						
						
			case 6 : menu.deposit();
						break;
			
				
			case 7 : menu.withDraw();
						break;
			case 8 : dbops.getAllBankNames();
						break;
			case 9 :dbops.deleteAdmin();
				return;
			
			
			default : System.out.println("Enter a Valid Choice");
						break;
			}
			System.out.println("Do you want to go back to Customer Portal Y|N??");
			choice = scanner.nextLine().charAt(0);
		}while(choice == 'Y' || choice == 'y');

	}
}



