package LLS;
import java.util.Date;

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
		//returns number of days left for this loan to expire
		long dif =  dueDate.getTime() - new Date().getTime();
		int days = Main.convertToDay(dif) - 1;
		return (days<0)?0:days;
	}
	
	public int getDueDayAfter() {
		//returns number of days passed after the due date for this loan
		long dif = new Date().getTime() - dueDate.getTime();
		int days = Main.convertToDay(dif) - 1;
		return (days<0)?0:days;
	}
	
	abstract public double getFineAmt();
	
	public boolean returnResource() {
		//returns true if the resource is successfully returned
		if(dateReturned==null) {
			resc.setIsBorrowed(false);
			this.dateReturned = new Date();
			patr.receiveFine(getFineAmt());
			return true;
		}
		return false;
	}
	
	protected String toRawData() {
		//return data in raw format for file-writing purpose
		return String.format("%s#%s#%s#%s#\r\n", patr.getUserCode(), resc.getCallNo(), Main.sdf.format(dateBorrowed), (dateReturned!=null)?Main.sdf.format(dateReturned):"");
	}

	public String toString() {
		return String.format("\n  Patron ID\t : %s\n  Patron Name\t : %s\n  Call No\t : %s\n  Resource Name\t : %s\n  Resource type\t : %s\n  Date Borrowed\t : %s\n  Loan Duration\t : %d days\n  Days left\t : %d days\n  Date Returned\t : %s\n", patr.getUserCode(), patr.getUserName(), resc.getCallNo(), resc.getTitle(), resc.getClassName(),
				Main.sdf.format(dateBorrowed), loanDuration, Main.sdf.format(dueDate), (dateReturned!=null)?Main.sdf.format(dateReturned):"NULL");
	}
}
