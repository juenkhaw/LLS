import java.util.*;
import java.io.*;
import java.text.*;

public class Main {
	static String[] rawDataIn = null;
	static String rawDataOut = "";
	static final long MS_PER_DAY = 24 * 60 * 60 * 1000;
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	static File patronFile = new File("src/Patron.txt");
	static File bookFile = new File("src/Book.txt");
	static File magazineFile = new File("src/Magazine.txt");
	static File CDDVDFile = new File("src/CDDVD.txt");
	static File bookLoanFile = new File("src/BookLoan.txt");
	static File nonBookLoanFile = new File("src/NonBookLoan.txt");
	
	static ArrayList<Patron> patron = new ArrayList<Patron>();
	static ArrayList<Resource> resource = new ArrayList<Resource>();
	//static ArrayList<Magazine> magazine = new ArrayList<Magazine>();
	//static ArrayList<CDDVD> CDDVD = new ArrayList<CDDVD>();
	static ArrayList<Loan> loan = new ArrayList<Loan>();
	//static ArrayList<NonBookLoan> nonBookLoan = new ArrayList<NonBookLoan>();
	
	public static void clscr() throws IOException, InterruptedException {
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	}
	
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
			fr.close();
			return data.split("\n");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void outData(File target) {
		try {
			FileWriter fw = new FileWriter(target);
			fw.write(rawDataOut);
			fw.flush();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {

		try {
			rawDataIn = inData(patronFile);
			for(int i=0;i<rawDataIn.length;i++)
				patron.add(new Patron(rawDataIn[i].split("#")));
			rawDataIn = inData(bookFile);
			for(int i=0;i<rawDataIn.length;i++)
				resource.add(new Book(rawDataIn[i].split("#")));
			rawDataIn = inData(magazineFile);
			for(int i=0;i<rawDataIn.length;i++)
				resource.add(new Magazine(rawDataIn[i].split("#")));
			rawDataIn = inData(CDDVDFile);
			for(int i=0;i<rawDataIn.length;i++)
				resource.add(new CDDVD(rawDataIn[i].split("#")));
			rawDataIn = inData(bookLoanFile);
			for(int i=0;i<rawDataIn.length;i++)
				loan.add(new BookLoan(rawDataIn[i].split("#")));
			rawDataIn = inData(nonBookLoanFile);
			for(int i=0;i<rawDataIn.length;i++)
				loan.add(new NonBookLoan(rawDataIn[i].split("#")));
			rawDataIn = null;
			
			System.out.println(patron.get(1).searchLoan(false));
			
			//Testing File In
			/*for(int i=0;i<nonBookLoan.size();i++) {
				System.out.println(nonBookLoan.get(i));
			}
			*/
			//Testing Objects (DO NOT UNCOMMENT)
			//Patron patr = new Patron("Paik Wai", "Jln 145", "11200", "012-3495789");
			//Resource resc1 = new Book("Ghibli Studio Artwork", "Ghibli Studio", "2012-1-30", "901-2-5278-0931-5", "Hayao Miyazaki");
			//Resource resc2 = new Magazine("Xue Hai", "QingNian Publisher", "2016-7-5", "325-2-9180-1952-3", "16-7");
			//Resource resc3 = new CDDVD("Zootopia", "Disney", "2016-7-10");
			//Loan ln1 = new BookLoan(patron.get(2), book.get(0));
			//Loan ln2 = new NonBookLoan(patron.get(1), magazine.get(0));
			
			//Testing Outputs (DO NOT UNCOMMENT)
			//System.out.println(patr);
			//System.out.println(resc1);
			//System.out.println(resc2);
			//System.out.println(resc3);
			//System.out.println(ln1);
			//System.out.println(ln1.getDueDayAfter());
			//System.out.println(ln1.getFineAmt());
			//System.out.println(ln2);
			
			//File Out Testing
			for(int i=0;i<patron.size();i++)
				rawDataOut += patron.get(i).toRawData();
			outData(patronFile);
			rawDataOut = "";
			for(int i=0;i<resource.size();i++) {
				if(resource.get(i) instanceof Book)
					rawDataOut += resource.get(i).toRawData();
			}
			outData(bookFile);
			rawDataOut = "";
			for(int i=0;i<resource.size();i++)
				if(resource.get(i) instanceof Magazine)
					rawDataOut += resource.get(i).toRawData();
			outData(magazineFile);
			rawDataOut = "";
			for(int i=0;i<resource.size();i++) {
				if(resource.get(i) instanceof CDDVD)
					rawDataOut += resource.get(i).toRawData();
			}
			outData(CDDVDFile);
			rawDataOut = "";
			for(int i=0;i<loan.size();i++) {
				if(loan.get(i) instanceof BookLoan)
					rawDataOut += loan.get(i).toRawData();
			}
			outData(bookLoanFile);
			rawDataOut = "";
			for(int i=0;i<loan.size();i++) {
				if(loan.get(i) instanceof NonBookLoan)
					rawDataOut += loan.get(i).toRawData();
			}
			outData(nonBookLoanFile);
			rawDataOut = "";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Hello World!");
	}
}
