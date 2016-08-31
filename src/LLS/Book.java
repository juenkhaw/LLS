package LLS;
public class Book extends Resource{
	private String ISBN;
	private String author;
	
	public Book() {
	}
	
	public Book(String title, String publisher, String publicationDate, String ISBN, String author) {
		super(title, publisher, publicationDate);
		this.ISBN = ISBN;
		this.author = author;
		Main.resource.add(this);
	}
	
	public Book(String[] data) {
		//constructor of Book class for the data from the Book file
		super(data[0], data[1], data[2]);
		super.setCallNo(data[3]);
		super.setAccessionNo(data[4]);
		super.setIsBorrowed(Boolean.parseBoolean(data[5]));
		ISBN = data[6];
		author = data[7];
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getISBN() {
		return ISBN;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public static Book search(String callNo) {
		//returns the Book object if the callNo is found existed in a book
		for(int i=0;i<Main.resource.size();i++) {
			if(Main.resource.get(i) instanceof Book && Main.resource.get(i).getCallNo().equals(callNo))
				return (Book)Main.resource.get(i);
		}
		return null;
	}
	
	public String toRawData() {
		//return data in raw format for file-writing purpose
		return String.format("%s%s#%s#\r\n", super.toRawData(), ISBN, author);
	}
	
	public String toString() {
		return String.format("\n%s  ISBN\t\t : %s\n  Author\t : %s\n", super.toString(), ISBN, author);
	}
}
