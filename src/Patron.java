import java.util.ArrayList;

public class Patron {
	private static int userCount = 100000;
	private String userCode;
	private String userName;
	private String address;
	private String postCode;
	private String hpNo;
	private double fine;
	
	public Patron() {
	}
	
	public Patron(String userName, String address, String postCode, String hpNo) {
		userCount+=1;
		userCode = "P" + String.valueOf(userCount);
		this.userName = userName;
		this.address = address;
		this.postCode = postCode;
		this.hpNo = hpNo;
		fine = 0;
		Main.patron.add(this);
	}
	
	public Patron(String[] data) {
		userCount+=1;
		userCode = "P" + String.valueOf(userCount);
		userName = data[0];
		address = data[1];
		postCode = data[2];
		hpNo = data[3];
		fine = Double.parseDouble(data[4]);
	}
	
	public String getUserCode() {
		return userCode;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getPostCode() {
		return postCode;
	}
	
	public String getHpNo() {
		return hpNo;
	}
	
	public double getFine() {
		return fine;
	}
	
	static public int getUserCount() {
		return userCount;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	public void setHpNo(String hpNo) {
		this.hpNo = hpNo;
	}
	
	public void receiveFine(double fine) {
		this.fine += fine;
	}
	
	public void payFine(double fine) {
		this.fine -= fine;
	}
	
	public static Patron search(String userCode) {
		for(int i=0;i<Main.patron.size();i++) {
			if(Main.patron.get(i).userCode.equals(userCode))
				return Main.patron.get(i);
		}
		return null;
	}
	
	public String toRawData() {
		return String.format("%s#%s#%s#%s#%.2f#\r\n", userName, address, postCode, hpNo, fine);
	}
	
	public String toString() {
		return String.format("\nUser Code\t: %s\nUser Name\t: %s\nAddress\t\t: %s\nPost Code\t: %s\nHp. No.\t\t: %s\nTotal Fine\t: MYR%8.2f\n",
				userCode, userName, address, postCode, hpNo, fine);
	}
}
