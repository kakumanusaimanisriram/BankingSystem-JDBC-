package Utilities;

import java.util.Scanner;

import Amin.AdminManagementImpl;
import Customer.CustomerAuthentication;

public class Menu {
	

	Scanner scanner= Scan.getScanner();
	String bName ;
	String bCode;
	String bLocation;
	String cAccountno;
	String cfname;
	String clname;
	String cphone;
	String cpassword;
	String cemail;
	boolean result;
	double amount;
	AdminManagementImpl ad = new AdminManagementImpl();
	DbOperations dbops = new DbOperations();
	
	public void createBank() {
		System.out.println("Enter Unique Bank Name :");
		bName = scanner.nextLine().toUpperCase();
		System.out.println("Enter Alphanumeric Bank code : ");
		bCode = scanner.nextLine();
		System.out.println("Enter Bank Location : ");
		bLocation = scanner.nextLine();
		ad.createBank(bName, bCode, bLocation);
		
	}
	
	public void deleteSpecificBank() {
		System.out.println("Enter Bank Name :");
		bName = scanner.nextLine().toUpperCase();
		ad.deleteBank(bName);
	}
	
	public void getBankDetails() {
		result = dbops.getBankDetailsByName(bName);
		System.out.println("Enter Bank Name : ");
		bName = scanner.nextLine().toUpperCase();
		dbops.getBankDetailsByName(bName);
	}
	
	public void updateBank() {
		System.out.println("Enter Bank Name :");
		bName = scanner.nextLine().toUpperCase();
		result = dbops.getBankDetailsByName(bName);
		if(result) {
			
			System.out.println("Enter Bank code to be updated: ");
			bCode = scanner.nextLine();
			System.out.println("Enter Bank Location to be updated: ");
			bLocation = scanner.nextLine();
			ad.updateBankDetails(bName, bCode, bLocation);
		}
	}
	
	public  void createCustomer(String bName , boolean result) {
		if(result) {
			System.out.println("Enter Customer First Name :");
			cfname = scanner.nextLine();
			System.out.println("Enter Customer Last Name :");
			clname= scanner.nextLine();
			System.out.println("Enter Customer phoneno :");
			cphone = scanner.nextLine();
			System.out.println("Enter Customer email :");
			cemail = scanner.nextLine();
			System.out.println("Enter Customer password :");
			cpassword = scanner.nextLine();
			dbops.createCustomerAccountforSpecifiedBank(bName, cfname, clname, cphone, cemail, cpassword);
		}
		else {
			System.out.println("Try Again !!!");
		}
		
	}
	
	
	public void deleteCustomerAccount() {
		System.out.println("Enter Customer accountno :");
		cAccountno = scanner.nextLine();
		System.out.println("Enter Customer password :");
		cpassword = scanner.nextLine();
		dbops.deleteCustomerAccount(cAccountno, cpassword);
	}
	
	
	public void getCustomer() {
		System.out.println("Enter Customer accountno :");
		cAccountno = scanner.nextLine();
		System.out.println("Enter Customer password :");
		cpassword = scanner.nextLine();
		dbops.getCustomerDetails(cAccountno, cpassword);
	}
	
	public void updateCustomer() {
		System.out.println("Enter Customer accountno :");
		cAccountno = scanner.nextLine();
		System.out.println("Enter Customer password :");
		cpassword = scanner.nextLine();
		boolean res = dbops.isValidCustomer(cAccountno, cpassword);
		if(res) {
			System.out.println("Authentication Successfull !!!");
			System.out.println("Enter Customer First Name :");
			cfname = scanner.nextLine();
			System.out.println("Enter Customer Last Name :");
			clname= scanner.nextLine();
			System.out.println("Enter Customer phoneno :");
			cphone = scanner.nextLine();
			System.out.println("Enter Customer email :");
			cemail = scanner.nextLine();
			
			dbops.updateCustomerAccount(cAccountno,cfname, clname, cphone, cemail, cpassword);
		}
		else {
			System.out.println("Invalid Credentials or no such Customer present");
		}
	}
	
	
	public void deposit() {
		System.out.println("Enter accountno :");
		String acno = scanner.nextLine();
		System.out.println("Enter password : ");
		String password = scanner.nextLine();
		boolean result = CustomerAuthentication.isValidCustomer(acno, password);
		if(result) {
			System.out.println("Enter amount to be deposited : ");
			amount = scanner.nextDouble();
			dbops.depositAmount(acno, password, amount);
		}
		
	}
	
	public void withDraw() {
		System.out.println("Enter accountno :");
		String acno = scanner.nextLine();
		System.out.println("Enter password: ");
		String password = scanner.nextLine();
		boolean result = CustomerAuthentication.isValidCustomer(acno, password);
		if(result) {
			System.out.println("Enter amount to be withdrawn : ");
			amount = scanner.nextDouble();
			dbops.withDraw(acno, password, amount);
		}
		
	}
	
	
	public void transferAmount() {
		System.out.println("Enter sender accountno :");
		String senderacno = scanner.nextLine();
		System.out.println("Enter password : ");
		String senderpassword = scanner.nextLine();
		boolean result = CustomerAuthentication.isValidCustomer(senderacno, senderpassword);
		if(result) {
			System.out.println("Enter receiver accountno :");
			String receiveracno = scanner.nextLine();
			boolean res = dbops.validateCustomerByAcNo(receiveracno);
			if(res) {
				System.out.println("Enter amount to be Sent : ");
				amount = scanner.nextDouble();
				dbops.transferAmount(senderacno ,senderpassword , receiveracno ,amount);
			}
		}
		
	}

}
