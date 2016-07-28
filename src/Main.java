import java.util.Scanner;
import java.io.IOException;
import java.util.Date;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("Hello World!");
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		System.out.println("Hello World!");
		Patron patr = new Patron("TEST", "TEST", "TEST", "TEST", 99.99);
		Resource resc = new Resource("TEST", "TEST", new Date());
		//System.out.println(patr.toString());
		//System.out.println(resc.toString());
		//System.out.println(new Loan(patr, resc).toString());
		System.out.println(new BookLoan(patr, resc).toString());
	}
}
