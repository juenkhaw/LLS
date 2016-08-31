package LLS;
public class BookLoan extends Loan {
	private static double fineRate = 1.00;
	
	public BookLoan() {
	}
	
	public BookLoan(Patron patr, Resource resc) {
		super(patr, resc);
		resc.setIsBorrowed(true);
		Main.loan.add(this);
	}
	
	public BookLoan(String[] data) {
		super(Patron.search(data[0]), Book.search(data[1]));
		super.setDateBorrowed(data[2]);
		super.setDueDate();
		super.setDateReturned(data[3]);
	}
	
	public static double getFineRate() {
		return fineRate;
	}
	
	public static void setFineRate(double newFineRate) {
		fineRate = newFineRate;
	}
	
	public double getFineAmt() {
		//returns the fine amount for this book loan
		return super.getDueDayAfter() * fineRate;
	}
	
	public String toRawData() {
		//return data in raw format for file-writing purpose
		return super.toRawData();
	}
	
	public String toString() {
		return String.format("%s  Fine Rate\t : MYR %.2f/day\n", super.toString(), fineRate);
	}
}