import java.text.SimpleDateFormat;
import java.util.Date;

public class Loan {
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
		dueDate = new Date();
		dueDate.setDate(dueDate.getDate()+loanDuration);
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
	
	public String toString() {
		return String.format("\n%s%sDate Borrowed : %s\nDue Date : %s\nDate Returned : %s\n", patr.toString(), resc.toString(), Main.sdf.format(dateBorrowed), Main.sdf.format(dueDate), (dateReturned!=null)?Main.sdf.format(dateReturned):"NULL");
	}
}
