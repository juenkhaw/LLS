import java.util.Date;

public class Resource {
	private String title;
	private String publisher;
	private Date publicationDate;
	private String callNo;
	private String accessionNo;
	
	public Resource() {
	}
	
	public Resource(String title, String publisher, Date publicationDate) {
		this.title = title;
		this.publisher = publisher;
		this.publicationDate = publicationDate;
	}
}
