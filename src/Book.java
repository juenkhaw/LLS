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
