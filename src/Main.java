import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Main {
	public static final long MS_PER_DAY = 24 * 60 * 60 * 1000;
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	static File patronFile = new File("src/Patron.txt");
	static File bookFile = new File("src/Book.txt");
	static File magazineFile = new File("src/Magazine.txt");
	static File CDDVDFile = new File("src/CDDVD.txt");
	static File bookLoanFile = new File("src/BookLoan.txt");
	static File nonBookLoanFile = new File("src/NonBookLoan.txt");
	
	static ArrayList<Patron> patron = new ArrayList<Patron>();
	static ArrayList<Book> book = new ArrayList<Book>();
	static ArrayList<Magazine> magazine = new ArrayList<Magazine>();
	static ArrayList<CDDVD> CDDVD = new ArrayList<CDDVD>();
	static ArrayList<BookLoan> bookLoan = new ArrayList<BookLoan>();
	static ArrayList<NonBookLoan> nonBookLoan = new ArrayList<NonBookLoan>();
	
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
		if(date.equals(""))
			return null;
		Date d = new Date();
		try {
			d = sdf.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}
	
	public static String[] inData(File source) {
		try {
			FileReader fr = new FileReader(source);
			char raw[] = new char[(int)source.length()];
			fr.read(raw);
			String data = String.valueOf(raw);
			return data.split("\n");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("Hello World!");
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		System.out.println("Hello World!");
		
		//Testing File IO
		try {
			String[] patrRaw = inData(patronFile);
			String[] bookRaw = inData(bookFile);
			String[] magazineRaw = inData(magazineFile);
			String[] CDDVDRaw = inData(CDDVDFile);
			String[] bookLoanRaw = inData(bookLoanFile);
			String[] nonBookLoanRaw = inData(nonBookLoanFile);
			
			for(int i=0;i<patrRaw.length;i++)
				patron.add(new Patron(patrRaw[i].split("#")));
			for(int i=0;i<bookRaw.length;i++)
				book.add(new Book(bookRaw[i].split("#")));
			for(int i=0;i<magazineRaw.length;i++)
				magazine.add(new Magazine(magazineRaw[i].split("#")));
			for(int i=0;i<CDDVDRaw.length;i++)
				CDDVD.add(new CDDVD(CDDVDRaw[i].split("#")));
			for(int i=0;i<bookLoanRaw.length;i++)
				bookLoan.add(new BookLoan(bookLoanRaw[i].split("#")));
			
			for(int i=0;i<nonBookLoanRaw.length;i++)
				nonBookLoan.add(new NonBookLoan(nonBookLoanRaw[i].split("#")));
			
			//Testing File In
			for(int i=0;i<nonBookLoan.size();i++) {
				System.out.println(nonBookLoan.get(i));
			}
			
			//Testing Objects
			//Patron patr = new Patron("Stella", "Jln ABC", "11700", "012-3456789", 0);
			//Resource resc1 = new Book("Finding Dory", "Disney", "2016-6-10", "978-3-8473-1838-5", "Disney");
			//Resource resc2 = new Magazine("Tech Insider", "Tech", "2016-5-12", "385-2-5835-3258-3", "16-8");
			//Resource resc3 = new CDDVD("DVD", "DVD company", "2015-2-28");
			//Loan ln1 = new BookLoan(patron.get(0), book.get(0));
			//Loan ln2 = new NonBookLoan(patron.get(0), magazine.get(0));
			
			//Testing Outputs
			//System.out.println(patr);
			//System.out.println(resc1);
			//System.out.println(resc2);
			//System.out.println(resc3);
			//System.out.println(ln1);
			//System.out.println(ln1.getDueDayAfter());
			//System.out.println(ln1.getFineAmt());
			//System.out.println(ln2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
