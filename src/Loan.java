import java.util.Date;

public class Loan {
	private Patron patr;
	private Resource resc;
	//private static int duration = 14;
	private Date dateBorrowed;
	private Date dueDate;
	private Date dateReturned;

	public Loan() {}
	public Loan(Patron patr, Resource resc) {
		this.patr = patr;
		this.resc = resc;
	}

	public Date getDateBorrowed(){return dateBorrowed;}
	public Date getDueDate(){return dueDate;}
	public Date getDateReturned(){return dateReturned;}
	
	public String toString() {
		return String.format("%s\n%s\n%s\n%s\n%s\n", patr.toString(), resc.toString(), dateBorrowed, dueDate, dateReturned);
	}
}
