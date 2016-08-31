package LLS;

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
		//standard constructor of Patron class
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
		//constructor of Patron class for the data from the Patron file
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
	
	public static boolean checkHpNo(String number)
    {
    	char c;
		boolean valid = true;

		if(number.length() == 11 || number.length() == 12){
			c = number.charAt(3);
			if(c == '-'){
				for(int i = 0; i < 3; i++)
				{
					if(Character.isLetter(number.charAt(i)))
					valid = false;
				}
				for(int i = 4; i <number.length(); i++)
				{
					if(Character.isLetter(number.charAt(i)))
					valid = false;
				}
			}else valid = false;
		}else valid = false;
		return valid;
    }
	
	public boolean receiveFine(double fine) {
		//Receives fine issued and increments this.fine 
		if(fine < 0)
			return false;
		this.fine += fine;
		return true;
	}
	
	public boolean payFine(double fine) {
		//Receives fines paid and decrements this.fine
		if(this.fine < fine)
			return false;
		if(fine < 0)
			return false;
		this.fine -= fine;
		return true;
	}
	
	public static Patron search(String userCode) {
		//returns a Patron object if the the userCode has found to be existed
		for(int i=0;i<Main.patron.size();i++) {
			if(Main.patron.get(i).userCode.equals(userCode))
				return Main.patron.get(i);
		}
		return null;
	}
	
	public int searchLoan(boolean all, boolean print) {
		//returns number of loans that this patron currently having
		//if all = true, it returns number of loans that this patron having/had
		//if print = true, it prints out details of all of the loans
		int count = 0;
		for(int i=0;i<Main.loan.size();i++) {
			if(this.userCode.equals(Main.loan.get(i).getPatr().getUserCode()) && (Main.loan.get(i).getDateReturned()==null || all)) {
				if(print)
					System.out.println(Main.loan.get(i));
				count++;
			}
		}
		return count;
	}
	
	public Loan searchLoan(String callNo) {
		//returns a Loan object if there is a match by comparing the callNos
		Loan l = null;
		for(int i=0;i<Main.loan.size();i++) {
			if(Main.loan.get(i).getPatr().getUserCode().equals(this.userCode)&&Main.loan.get(i).getResc().getCallNo().equals(callNo))
				l = Main.loan.get(i);
		}
		return l;
	}
	
	public Loan borrowResource(Resource resc) {
		//returns a new Loan object if the resource is successfully borrowed
		if(searchLoan(false, false)<3 && fine<=10 && !resc.getIsBorrowed()) {
			if(resc instanceof Book)
				return new BookLoan(this, resc);
			else
				return new NonBookLoan(this, resc);
		}
		return null;
	}
	
	public String toRawData() {
		//return data in raw format for file-writing purpose
		return String.format("%s#%s#%s#%s#%.2f#\r\n", userName, address, postCode, hpNo, fine);
	}
	
	public String toString() {
		return String.format("\n  Patron Code\t: %s\n  Patron Name\t: %s\n  Address\t: %s\n  Post Code\t: %s\n  Hp. No.\t: %s\n  Total Fine\t: MYR%8.2f\n",
				userCode, userName, address, postCode, hpNo, fine);
	}
}
