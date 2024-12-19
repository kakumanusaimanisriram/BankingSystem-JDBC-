package Amin;

import java.sql.Timestamp;

public class AdminEntity {
	private int admin_id;
	private String admin_name;
	private String admin_phoneno ; 
	private String admin_email; 
	private String admin_password ;
	private Timestamp created_at;
	
	
	@Override
	public String toString() {
		return "AdminEntity [admin_id=" + admin_id + ", admin_name=" + admin_name + ", admin_phoneno=" + admin_phoneno
				+ ", admin_email=" + admin_email + ", admin_password=" + admin_password + ", created_at=" + created_at
				+ "]";
	}
	
	
	

}
