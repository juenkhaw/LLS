import java.util.Date;

public abstract class Loan {
	private Patron patr;
	private Resource resc;
	private static int loanDuration = 14;
	private Date dateBorrowed;
	private Date dueDate;
	private Date dateReturned;

	public Loan() {
	}
	
	public Loan(Patron patr, Resource resc) {
		this.patr = patr;
		this.resc = resc;
		dateBorrowed = new Date();
		//dateBorrowed = Main.convertToDate("2016-7-34");
		dueDate = new Date();
		dueDate.setTime(dateBorrowed.getTime()+Main.convertToMS(loanDuration));
		dateReturned = null;
	}

	public Date getDateBorrowed(){
		return dateBorrowed;
	}
	
	public Date getDueDate(){
		return dueDate;
	}
	
	public Date getDateReturned(){
		return dateReturned;
	}
	
	public static void setLoanDuration(int newLoanDuration) {
		loanDuration = newLoanDuration;
	}
	
	public static int getLoanDuration() {
		return loanDuration;
	}
	
	public int getDueDayLeft() {
		long dif =  dueDate.getTime() - new Date().getTime();
		int days = Main.convertToDay(dif) - 1;
		return (days<0)?0:days;
	}
	
	public int getDueDayAfter() {
		long dif = new Date().getTime() - dueDate.getTime();
		int days = Main.convertToDay(dif) - 1;
		return (days<0)?0:days;
	}
	
	abstract public double getFineAmt();

	public String toString() {
		return String.format("\n%s%sDate Borrowed : %s\nLoan Duration : %d days\nDue Date : %s\nDate Returned : %s\n", patr.toString(), resc.toString(), Main.sdf.format(dateBorrowed), loanDuration, Main.sdf.format(dueDate), (dateReturned!=null)?Main.sdf.format(dateReturned):"NULL");
	}
}
