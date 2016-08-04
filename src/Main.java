import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
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
			File patronFile = new File("src/Patron.txt");
			File bookFile = new File("src/Book.txt");
			String[] patrRaw = inData(patronFile);
			String[] bookRaw = inData(bookFile);
			ArrayList patron = new ArrayList();
			ArrayList book = new ArrayList();
			for(int i=0;i<patrRaw.length;i++)
				patron.add(new Patron(patrRaw[i].split("#")));
			for(int i=0;i<bookRaw.length;i++)
				book.add(new Book(bookRaw[i].split("#")));
		
		for(int i=0;i<patron.size();i++) {
			System.out.println(book.get(i));
		}
		
		//Testing Objects
		//Patron patr = new Patron("Stella", "Jln ABC", "11700", "012-3456789", 0);
		Resource resc1 = new Book("Finding Dory", "Disney", "2016-6-10", "978-3-8473-1838-5", "Disney");
		Resource resc2 = new Magazine("Tech Insider", "Tech", "2016-5-12", "12-125-563", "15-1");
		Resource resc3 = new CDDVD("DVD", "DVD company", "2015-2-28");
		Loan ln1 = new BookLoan((Patron)patron.get(0), resc1);
		Loan ln2 = new NonBookLoan((Patron)patron.get(0), resc2);
		
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
