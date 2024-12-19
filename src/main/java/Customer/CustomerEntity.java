package Customer;

import com.google.protobuf.Timestamp;

public class CustomerEntity {
	private String CustomerAccountNumber;
	private String FirstName;
	private String Lastname ;
	private String phoneno ;
	private String email ;
	private String password;
	private String balance ;
	private String bname;
	private Timestamp created_at;
	@Override
	public String toString() {
		return "CustomerEntity [CustomerAccountNumber=" + CustomerAccountNumber + ", FirstName=" + FirstName
				+ ", Lastname=" + Lastname + ", phoneno=" + phoneno + ", email=" + email + ", password=" + password
				+ ", balance=" + balance + ", bname=" + bname + ", created_at=" + created_at + "]";
	}

}
