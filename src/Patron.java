
public class Patron {
	private static int userCount = 0;
	private String userCode;
	private String userName;
	private String address;
	private String postCode;
	private String hpNo;
	private double fine;
	
	private Patron() {		
	}
	
	public Patron(String userName, String address, String postCode, String hpNo, double fine) {
		userCount+=1;
		this.userName = userName;
		this.address = address;
		this.postCode = postCode;
		this.hpNo = hpNo;
		this.fine = fine;
	}
	
	public String getUserCode() {return userCode;}
	public String getUserName() {return userName;}
	public String getAddress() {return address;}
	public String getPostCode() {return postCode;}
	public String getHpNo() {return hpNo;}
	public double getFine() {return fine;}
	
	static public int getUserCount() {return userCount;}
	
	public void setUserName(String userName) {this.userName = userName;}
	public void setAddress(String address) {this.address = address;}
	public void setPostCode(String postCode) {this.postCode = postCode;}
	public void setHpNo(String hpNo) {this.hpNo = hpNo;}
	
	public String toString() {
		return String.format("\nUser Code\t: %s\nUser Name\t: %s\nAddress\t\t: %s\nPost Code\t: %s\nHp. No.\t\t: %s\nTotal Fine\t: MYR%8.2f",
				userCode, userName, address, postCode, hpNo, fine);
	}
}
