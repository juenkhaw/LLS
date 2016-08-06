public class Book extends Resource{
	private String ISBN;
	private String author;
	
	public Book() {
	}
	
	public Book(String title, String publisher, String publicationDate, String ISBN, String author) {
		super(title, publisher, publicationDate);
		this.ISBN = ISBN;
		this.author = author;
		Main.book.add(this);
	}
	
	public Book(String[] data) {
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
		for(int i=0;i<Main.book.size();i++) {
			if(Main.book.get(i).getCallNo().equals(callNo))
				return Main.book.get(i);
		}
		return null;
	}
	
	public String toRawData() {
		return String.format("%s%s#%s\r\n", super.toRawData(), ISBN, author);
	}
	
	public String toString() {
		return String.format("\n%sISBN : %s\nAuthor : %s\n", super.toString(), ISBN, author);
	}
}
