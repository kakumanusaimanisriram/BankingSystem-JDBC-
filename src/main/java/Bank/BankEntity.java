package Bank;

import com.google.protobuf.Timestamp;

public class BankEntity {
	
	private String bname;
	private String bcode;
	private String blocation;
	private Timestamp createdAt;
	
	@Override
	public String toString() {
		return "BankEntity [bname=" + bname + ", bcode=" + bcode + ", blocation=" + blocation + ", createdAt="
				+ createdAt + "]";
	}
	
	
	
	
	

}
