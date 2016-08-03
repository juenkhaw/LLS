import java.util.Scanner;
import java.io.IOException;
import java.util.Date;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("Hello World!");
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		System.out.println("Hello World!");
		
		//Testing Objects
		Patron patr = new Patron("Stella", "Jln ABC", "11700", "012-3456789", 0);
		Resource resc1 = new Book("Finding Dory", "Disney", "2016-6-10", "12-214-124", "Disney");
		Resource resc2 = new Magazine("Tech Insider", "Tech", "2016-5-12", "12-125-563", "15-1");
		Resource resc3 = new CDDVD("DVD", "DVD company", "2015-2-28");
		Loan ln1 = new BookLoan(patr, resc1);
		Loan ln2 = new NonBookLoan(patr, resc2);
		
		//Testing Outputs
		System.out.println(patr.toString()+resc1.toString()+resc2.toString()+resc3.toString()+ln1.toString()+ln2.toString());
	}
}
