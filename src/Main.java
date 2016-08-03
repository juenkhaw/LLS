import java.util.Scanner;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Main {
	public static final long MS_PER_DAY = 24 * 60 * 60 * 1000;
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public static int getRandomNum(int limit) {
		return (int)(Math.random()*limit);
	}
	
	public static char getRandomChar(char limit) {
		return (char)('A'+Math.random()*(limit-'A'+1));
	}
	
	public static int convertToDay(long dif) {
		return (int)(dif/MS_PER_DAY)+1;
	}
	
	public static long convertToMS(int dif) {
		return (long)(dif*MS_PER_DAY);
	}
	
	public static Date convertToDate(String date) {
		Date d = new Date();
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
		}
		return d;
	}
	
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
		//System.out.println(patr);
		//System.out.println(resc1);
		//System.out.println(resc2);
		//System.out.println(resc3);
		System.out.println(ln1);
		System.out.println(ln1.getDueDayLeft());
		//System.out.println(ln2);
	}
}
