public class NonBookLoan extends Loan {
	private static double fineRate = 2.00;
	
	public NonBookLoan() {
	}
	
	public NonBookLoan(Patron patr, Resource resc) {
		super(patr, resc);
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
	
	public String toString() {
		return String.format("%sFine Rate : MYR%8.2f/day\n", super.toString(), fineRate);
	}
}
