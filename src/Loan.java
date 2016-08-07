import java.util.Date;
import java.util.ArrayList;

public abstract class Loan {
	private Patron patr;
	private Resource resc;
	private static int loanDuration = 14;
	private Date dateBorrowed;
	private Date dueDate;
	private Date dateReturned;

	protected Loan() {
	}
	
	protected Loan(Patron patr, Resource resc) {
		this.patr = patr;
		this.resc = resc;
		//System.out.println(resc);
		dateBorrowed = new Date();
		//dateBorrowed = Main.convertToDate("2016-7-34");
		dueDate = new Date();
		dueDate.setTime(dateBorrowed.getTime()+Main.convertToMS(loanDuration));
		dateReturned = null;
		resc.setIsBorrowed(true);
	}
	
	public Patron getPatr() {
		return patr;
	}
	
	public Resource getResc() {
		return resc;
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
	
	protected void setDateBorrowed(String date) {
		this.dateBorrowed = Main.convertToDate(date);
	}
	
	protected void setDueDate() {
		this.dueDate = (Date)dateBorrowed.clone();
		dueDate.setTime(dateBorrowed.getTime()+Main.convertToMS(loanDuration));
	}
	
	protected void setDateReturned(String date) {
		this.dateReturned = Main.convertToDate(date);
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
	
	public void returnResource() {
		resc.setIsBorrowed(false);
		this.dateBorrowed = new Date();
		patr.receiveFine(getFineAmt());
	}
	
	protected String toRawData() {
		return String.format("%s#%s#%s#%s#\r\n", patr.getUserCode(), resc.getCallNo(), Main.sdf.format(dateBorrowed), (dateReturned!=null)?Main.sdf.format(dateReturned):"");
	}

	public String toString() {
		return String.format("\nPatron ID : %s\nPatron Name : %s\nResource Call No : %s\nResource Name : %s\nResource type : %s\nDate Borrowed : %s\nLoan Duration : %d days\nDue Date : %s\nDays left : %d days\nDate Returned : %s\n", patr.getUserCode(), patr.getUserName(), resc.getCallNo(), resc.getTitle(), resc.getClass(),
				Main.sdf.format(dateBorrowed), loanDuration, Main.sdf.format(dueDate), getDueDayLeft(), (dateReturned!=null)?Main.sdf.format(dateReturned):"NULL");
	}
}
