import java.util.Date;

public class BookLoan extends Loan {
	private static double fineRate = 1.00;
	
	public BookLoan() {
	}
	
	public BookLoan(Patron patr, Resource resc) {
		super(patr, resc);
	}
	
	public static double getFineRate() {
		return fineRate;
	}
	
	public static void setFineRate(double newFineRate) {
		fineRate = newFineRate;
	}
	
	public String toString() {
		return String.format("%s\n%s\n", super.toString(), fineRate);
	}
}