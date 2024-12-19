package Utilities;

import java.security.SecureRandom;

public class AccountNumberGenerator {
	
	public static String getac() {
		int length=12;
		 String char_lower = "abcdefghijklmnopqrstuvwxyz";
		 String char_upper = char_lower.toUpperCase();
		  String number = "0123456789";

		    String random_string = char_lower + char_upper + number;
		    SecureRandom random = new SecureRandom();


		    StringBuilder sb = new StringBuilder(length);
		    
		    for (int i = 0; i < length; i++) {
		        // 0-62 (exclusive), random returns 0-61
		        int rndCharAt = random.nextInt(random_string.length());
		        char rndChar = random_string.charAt(rndCharAt);

		        sb.append(rndChar);
		    }

		    return sb.toString();
		}
}
	
	
	   
	   


