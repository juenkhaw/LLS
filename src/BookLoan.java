public class BookLoan extends Loan {
	private static double fineRate = 1.00;
	
	public BookLoan() {
	}
	
	public BookLoan(Patron patr, Resource resc) {
		super(patr, resc);
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
		return super.getDueDayAfter() * fineRate;
	}
	
	public String toRawData() {
		return super.toRawData();
	}
	
	public String toString() {
		return String.format("%sFine Rate : MYR%8.2f/day\n", super.toString(), fineRate);
	}
}