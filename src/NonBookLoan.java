public class NonBookLoan extends Loan {
	private static double fineRate = 2.00;
	
	public NonBookLoan() {
	}
	
	public NonBookLoan(Patron patr, Resource resc) {
		super(patr, resc);
		Main.nonBookLoan.add(this);
	}
	
	public NonBookLoan(String[] data) {
		super(Patron.search(data[0]), searchNonBook(data[1]));
		super.setDateBorrowed(data[2]);
		super.setDueDate();
		super.setDateReturned(data[3]);
	}
	
	private static Resource searchNonBook(String callNo) {
		if(Magazine.search(callNo)==null)
			return CDDVD.search(callNo);
		return Magazine.search(callNo);
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
