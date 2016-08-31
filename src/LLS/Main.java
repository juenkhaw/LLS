package LLS;
import java.util.*;
import java.io.*;
import java.text.*;

public class Main {
	static String[] rawDataIn = null;
	static String rawDataOut = "";
	static final long MS_PER_DAY = 24 * 60 * 60 * 1000;
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	//File objects declarations
	static File patronFile = new File("src/Patron.txt");
	static File bookFile = new File("src/Book.txt");
	static File magazineFile = new File("src/Magazine.txt");
	static File CDDVDFile = new File("src/CDDVD.txt");
	static File bookLoanFile = new File("src/BookLoan.txt");
	static File nonBookLoanFile = new File("src/NonBookLoan.txt");

	//ArrayList objects declarations
	static ArrayList<Patron> patron = new ArrayList<Patron>();
	static ArrayList<Resource> resource = new ArrayList<Resource>();
	static ArrayList<Loan> loan = new ArrayList<Loan>();

	private static void clscr() throws IOException, InterruptedException {
		//clears the console screen
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	}

	private static void readKey() {
		//reads any key so that the screen won't jump to the next one directly
		Scanner s = new Scanner(System.in);
		System.out.println("\n  PRESS ANY KEY TO CONTINUE >>>");
		try {
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
		s.nextLine();
	 }

	private static void printHeader(String userName, String title) throws IOException, InterruptedException {
		//prints the header on the top of the console
		clscr();
		System.out.println("\n  WELCOME TO OK LIBRARY\n  Library Loan Management System\t\t\t"+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		System.out.println((userName=="")?"":("  Librarian : " + userName));
		System.out.println("\n  "+title);
		for(int i=0;i<80;i++) System.out.print("=");
		System.out.println("\n");
	}

	public static int getRandomNum(int limit) {
		//generates a random integer that is < limit and returns it
		return (int)(Math.random()*limit);
	}

	public static char getRandomChar(char limit) {
		//generates a random character in the range of ['A', limit] and returns it
		return (char)('A'+Math.random()*(limit-'A'+1));
	}

	public static int convertToDay(long dif) {
		//converts milliseconds to number of days and returns it
		return (int)(dif/MS_PER_DAY)+1;
	}

	public static long convertToMS(int dif) {
		//converts number of days to milliseconds and returns it
		return (long)(dif*MS_PER_DAY);
	}

	public static Date convertToDate(String date) {
		//converts a formatted string which contains date info into a real Date object and returns the Date obejct
		if(date.equals(""))
			return null;
		Date d = new Date();
		try {
			d = sdf.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return d;
	}

	public static boolean checkInput(String opt, String[] option)
	{
		for(int i=0;i<option.length;i++) {
			if(opt.equals(option[i]))
				return true;
		}
		return false;
	}


	public static String[] inData(File source) {
		//reads all of the data from a source file and returns it as a String array
		try {
			FileReader fr = new FileReader(source);
			if(source.length()==0)
				return new String[] {};
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
		//writes all the data to a target file
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
	    Scanner input = new Scanner(System.in);
		boolean valid = false;
		boolean check, userValid;
	    String username, password, userCode, option, select, insert, callNo, hpNo, address;

		System.out.println("\n  INITIALIZING...\n  READING FILES DATA...\n");

		try {
			//Get data from files
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
		} catch (Exception e) {
			printHeader("", "READ FILE ERROR");
			System.out.println("\n  FILES ARE MISSING/CORREUPTED/NON-ACCESSIBLE"
					+ "\n  Please check for the data files and restart the program.");
			readKey();
			System.exit(0);
		}

		System.out.println("  DONE.");
		readKey();

		try {

			printHeader("", "Staff Login");
			do{
			    System.out.print("  Please log into your staff acoount.\n\n  Staff Username: ");
			    username = input.nextLine();
			    System.out.print("  Password: ");
			    password = input.nextLine();
				//username & password
			    if((username.equals("tattjuen") || username.equals("yongwah") || username.equals("jaleen")) && password.equals("abc123")){
					System.out.println("\n  LOGIN SUCCESSFUL\n  Welcome, "+username+".");
			      	valid = true;
			    }
			    else
			    	System.out.println("\n  LOGIN FAILED\n  Wrong username or password. Please try again.\n");
			}while(valid != true);
			readKey();
			do{
				//main menu
				printHeader(username, "Main Menu");
				System.out.println("  1. Borrow / Return book / non-book\n  2. Pay overdue charges\n  3. Update files\n  4. Search book / non-book\n  5. Log out and exit");
				System.out.print("\n  Enter your option: ");
				do{
					option = input.nextLine();
					check = checkInput(option,new String[] {"1", "2", "3","4","5"});
					if(check == false)
						System.out.print("  INVALID INPUT\n  Please try again : ");
				}while(check == false);

				//user select 1/2/3
				do {
					if(checkInput(option, new String[] {"1","2","3"})){
						Patron currPatr = null;
						userValid = true;
						//ask usercode
						do {
							System.out.print("\n  Enter 0 to back to MAIN MENU\n  Enter a Patron Code: ");
							userCode = input.nextLine();
							//check user code
							currPatr = Patron.search(userCode);
							if(userCode.equals("0"))
								break;
							else if(currPatr==null)
								System.out.println("\n  PATRON NOT FOUND\n  Please try another one.");
							else {
								System.out.print("\n  PATRON FOUND\n");
								System.out.print(currPatr);
								if(currPatr.searchLoan(false, false)==3 || currPatr.getFine() >= 10) {
									System.out.println("\n  WARNING\n  This user is not eligible to borrow resources as he/she : "+
											"\n  1, has maxinum number of loans, or\n  2, accumulated fine more than MYR10.");
									userValid = false;
								}
								readKey();
							}
						} while (currPatr==null && !userCode.equals("0"));
						if(userCode.equals("0")) break;
						//1 --> borrow/return menu
						if(option.equals("1")){
							printHeader(username, "Borrow/Return Resources");
							//print patron details
							System.out.print("\n  0. Back to MAIN MENU\n  1. Borrow Resources\n  2. Return Resources\n\n  Enter your option : ");
							do{
								select = input.nextLine();
								check = checkInput(select, new String[] {"0","1","2"});
								if(check == false)
									System.out.print("\n  INVALID INPUT\n  Please try again : ");
							}while(check == false);
							if(select.equals("0")) break;
							//print whether user choose borrow or return
							if(option.equals("1")) System.out.print("\n  Borrow Resources\n  ----------------");
							else System.out.print("\n  Return Resources\n  ----------------");

							//ask user book/non-book
							System.out.print("\n  0. Back to MAIN MENU\n  1. Book\n  2. Non-book\n\n  Enter your option : ");
							do{
								insert = input.nextLine();
								check = checkInput(insert, new String[] {"0", "1","2"});
								if(check == false)
									System.out.print("\n  INVALID INPUT\n  Please try again : ");
							}while(check == false);
							if(insert.equals("0")) break;

							//ask user borrow book/non-book accession no.
							if(select.equals("1")){
								if(!userValid) {
									System.out.println("\n  WARNING\n  This user is not eligible to borrow resources as he/she : "+
											"\n  1, has maxinum number of loans, or\n  2, accumulated fine more than MYR10.");
									readKey();
									break;
								}
								if(insert.equals("1")){
									printHeader(username, "Borrow Book");
									Book b = null;
									do {
										System.out.print("\n  Enter the call no. of the resource that this patron wishes to borrow/return.\n  Book Call no. : ");
										callNo = input.nextLine();
										b = Book.search(callNo);
										if(callNo.equals("0")) break;
										else if(b==null)
											System.out.println("\n  BOOK NOT FOUND\n  Please try another one.");
										else if (b.getIsBorrowed()){
											System.out.println("\n  WARNING\n  The Book (call no. = "+b.getCallNo()+") has been borrowed.\n  Please try another one.");
											readKey();
										}
										else {
											System.out.println("\n  RESOURCE FOUND\n");
											System.out.print(b);
											currPatr.borrowResource(b);
											System.out.println("\n  THIS PATRON AND THE BOOK HAS BEEN REGISTERED IN A LOAN");
											System.out.println(loan.get(loan.size()-1).toString());
											readKey();
											break;
										}
									} while (b==null&&!callNo.equals("0"));
									break;
									//update book borrowed
								}
								else{
									printHeader(username, "Borrow Non-Book");
									Resource r = null;
									do {
										System.out.print("\n  Enter the call no. of the resource that this patron wishes to borrow/return.\n  Non-Book Call no. : ");
										callNo = input.nextLine();
										r = Resource.searchNonBook(callNo);
										if(callNo.equals("0")) break;
										else if(r==null)
											System.out.println("\n  RESOURCE NOT FOUND\n  Please try another one.");
										else if(r.getIsBorrowed()){
											System.out.println("\n  WARNING\n  The "+r.getClassName()+" (call no. = "+r.getCallNo()+") has been borrowed.\n  Please try another one.");
											readKey();
										}
										else {
											System.out.println("\n  RESOURCE FOUND\n");
											System.out.print(r);
											currPatr.borrowResource(r);
											System.out.println("\n  THIS PATRON AND THE RESOURCE HAS BEEN REGISTERED IN A LOAN");
											System.out.println(loan.get(loan.size()-1).toString());
											readKey();
											break;
										}
									} while (r==null&&!callNo.equals("0"));
									break;
									//update non-book borrowed
								}
							}

							//ask user return book/non-book accession no.
							else{
								if(insert.equals("1")){
									printHeader(username, "Return Book");
									BookLoan bl = null;
									do {
										System.out.print("\n  Enter the call no. of the resource that this patron wishes to borrow/return.\n  Book Call no. : ");
										callNo = input.nextLine();
										bl = (BookLoan)currPatr.searchLoan(callNo);
										if(callNo.equals("0"))
											break;
										else if(bl==null)
											System.out.println("\n  BOOK LOAN NOT FOUND\n  The book is not found or this patron do not having this loan.\n  Please try another one.");
										else if(bl.getDateReturned()!=null)
											System.out.println("\n  BOOK RETURNED\n  The book has been returned by this patron.\n  Please try another one.");
										else {
											bl.returnResource();
											System.out.println("\n  THE BOOK HAS BEEN SUCCESSFULLY RETURNED.");
											System.out.printf("\n  Date borrowed\t : %s\n  Date returned\t : %s\n  Days overdue\t : %d day(s)\n  Fine issued\t : MYR %.2f",
													sdf.format(bl.getDateBorrowed()), sdf.format(bl.getDateReturned()), bl.getDueDayAfter(), bl.getFineAmt());
											readKey();
											break;
										}
									} while (bl==null || bl.getDateReturned()!=null);
									break;
									//update book return
								}
								else{
									printHeader(username, "Return Non-Book");
									NonBookLoan nbl = null;
									do {
										System.out.print("\n  Please enter the call no. of the resource that this patron wishes to borrow/return.\n  Non-Book Call no. : ");
										callNo = input.nextLine();
										nbl = (NonBookLoan)currPatr.searchLoan(callNo);
										if(callNo.equals("0"))
											break;
										else if(nbl==null)
											System.out.println("\n  NON-BOOK LOAN NOT FOUND\n  The resource is not found or this patron do not having this loan.\n  Please try another one.");
										else if(nbl.getDateReturned()!=null)
											System.out.println("\n  RESOURCE RETURNED\n  The resource has been returned by this patron.\n  Please try another one.");
										else {
											nbl.returnResource();
											System.out.println("\n  THE RESOURCE HAS BEEN SUCCESSFULLY RETURNED.");
											System.out.printf("\n  Date borrowed\t : %s\n  Date returned\t : %s\n  Days overdue\t : %d day(s)\n  Fine issued\t : MYR %.2f",
													sdf.format(nbl.getDateBorrowed()), sdf.format(nbl.getDateReturned()), nbl.getDueDayAfter(), nbl.getFineAmt());
											readKey();
											break;
										}
									} while (nbl==null || nbl.getDateReturned()!=null);
									break;
									//update non-book return
								}
							}
						}


						//2 --> Pay overdue charges process
						else if(option.equals("2")){
							String buffer;
							double amt = 0;;
							boolean doubleValid;
							printHeader(username, "Pay Overdue Charges");
							//print patron details
							//get the current overdue balance
							System.out.printf("\n  Current Patron Fine Amount: MYR %.2f\n", currPatr.getFine());
							do {
								doubleValid = true;
								System.out.print("\n  Enter 0 to cancel the payment\n  Pay Amount: MYR ");
								buffer = input.nextLine();
								try {
									amt = Double.parseDouble(buffer);
								} catch (Exception e) {
									System.out.println("\n  INVALID INPUT\n  Please enter a valid figure.");
									continue;
								}
								doubleValid = currPatr.payFine(amt);
								if(!doubleValid)
									System.out.println("\n  PAYMENT FAILED\n  Please check the balance of this patron's fine.");
								else
									System.out.printf("\n  PAYMENT RECEIVED\n  Updated Patron Fine Amount: MYR %.2f", currPatr.getFine());
							} while (!doubleValid);
							if(buffer.equals("0")) break;
							//success statement
							
						}

						//3 --> Updates files process
						else{
							printHeader(username, "Update Files");
							System.out.println(currPatr);
							//print patron details
							System.out.print("\n  Patron may only able to update Phone No. and Address\n\n  0. Back to MAIN MENU\n  1. Phone No.\n  2. Address\n\n  Enter your option : ");
							do{
								select = input.nextLine();
								check = checkInput(select, new String[] {"0","1","2"});
								if(check == false)
									System.out.print("\n  INVALID INPUT\n  Please try again : ");
							}while(check == false);
							if(select.equals("0")) break;
							if(select.equals("1")){
								System.out.print("\n  New Hp No.: ");
								do{
									hpNo = input.nextLine();
									valid = Patron.checkHpNo(hpNo);
									if(valid != true) System.out.print("\n  INVALID INPUT\n  Please try again: ");
								}while(valid != true);
									currPatr.setHpNo(hpNo);

							}
							else{
								System.out.print("\n  New Address: ");
								do {
									address = input.nextLine();
									if(address.equals(""))
										System.out.print("\n  INVALID INPUT\n  Please try again: ");
								} while (address.equals(""));
								currPatr.setAddress(address);
							}
							//print updated patron details
							System.out.println("\n  PATRON DETAILS HAS BEEN UPDATED\n");
							System.out.println(currPatr);

						}

					}

						//4 --> Search book/non-book process
						else if(option.equals("4")){
							printHeader(username, "Search Book/Non-Book");
							System.out.print("\n  0. Back to MAIN MENU\n  1. Book\n  2. Non-Book\n\n  Enter your option : ");
								do{
									select = input.nextLine();
									check = checkInput(select, new String[] {"0","1","2"});
									if(check == false)
										System.out.print("\n  INVALID INPUT\n  Please try again : ");
								}while(check == false);
								if(select.equals("0")) break;
							if(select.equals("1")){
								System.out.print("\n  Search Book\n  -----------\n  Call no. : ");
								Book b = null;
								do {
									callNo = input.nextLine();
									b = Book.search(callNo);
									if(callNo.equals("0"))
										break;
									else if(b==null)
										System.out.print("\n  BOOK NOT FOUND\n  Please try again : ");
									else {
										System.out.println("\n  BOOK FOUND\n");
										System.out.println(b);
										readKey();
									}
								}while (b==null);
								if(callNo.equals("0"))
									break;
								//print book details
							}
							else{
								System.out.print("\n  Search Non-Book\n  ---------------\n  Call no. : ");
								Resource r = null;
								do {
									callNo = input.nextLine();
									r = Resource.searchNonBook(callNo);
									if(callNo.equals("0"))
										break;
									else if(r==null)
										System.out.print("\n  NON-BOOK NOT FOUND\n  Please try again : ");
									else {
										System.out.println("\n  RESOURCE FOUND\n");
										System.out.println(r);
										readKey();
									}
								}while (r==null);;
								if(callNo.equals("0"))
									break;
							}
						}
					if(option.equals("5")) break;
					} while (true);
				}while(!option.equals("5"));//stop looping when user enter option 5
				//Ending
				clscr();

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			//Write data to files
			System.out.println("\n  WRITTING FILES DATA...");
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
			for(int i=0;i<resource.size();i++) {
				if(resource.get(i) instanceof Magazine)
					rawDataOut += resource.get(i).toRawData();
			}
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
			printHeader("", "WRITE FILE ERROR");
			System.out.println("\n  FILES ARE MISSING/CORREUPTED/NON-ACCESSIBLE"
					+ "\n  Please check for the data files and restart the program.");
			readKey();
			System.exit(0);
		}

		System.out.println("  EXITING...");
		input.close();
		System.out.println("\n  DONE.");
	}
}
