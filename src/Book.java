public class Book extends Resource{
	private String ISBN;
	private String author;
	
	public Book() {
	}
	
	public Book(String title, String publisher, String publicationDate, String ISBN, String author) {
		super(title, publisher, publicationDate);
		this.ISBN = ISBN;
		this.author = author;
	}
	
	public Book(String[] bookData) {
		super(bookData[0], bookData[1], bookData[2]);
		super.setCallNo(bookData[3]);
		super.setAccessionNo(bookData[4]);
		super.setIsBorrowed(Boolean.parseBoolean(bookData[5]));
		ISBN = bookData[6];
		author = bookData[7];
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
	
	public String toString() {
		return String.format("\n%sISBN : %s\nAuthor : %s\n", super.toString(), ISBN, author);
	}
}
