import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Resource {
	private String title;
	private String publisher;
	private Date publicationDate;
	private String callNo;
	private String accessionNo;
	private boolean isBorrowed;

	public Resource() {
	}

	public Resource(String title, String publisher, String publicationDate) {
		this.title = title;
		this.publisher = publisher;
		try {
			this.publicationDate = new Date();
			this.publicationDate = Main.sdf.parse(publicationDate);
		} catch (ParseException e) {
		}
		accessionNo = String.format("%d:%03d",this.publicationDate.getYear()%100, Main.getRandomNum(1000));
		callNo = String.format("%c%c-%03d-%c%02d-%04d", Main.getRandomChar('Z'), Main.getRandomChar('Z'), Main.getRandomNum(1000), title.charAt(0), Main.getRandomNum(100), this.publicationDate.getYear()+1900);
		isBorrowed = false;
	}

	public void setTitle(String title){
		this.title = title;
	}
	
	public void setPublisher(String publisher){
		this.publisher = publisher;
	}
	
	public void setCallNo(String callNo){
		this.callNo = callNo;
	}
	
	public void setAccessionNo(String accessionNo){
		this.accessionNo = accessionNo;
	}
	
	public void setIsBorrowed () {
		isBorrowed = !isBorrowed;
	}

	public String getTitle(){
		return title;
	}
	
	public String getPublisher(){
		return publisher;
	}
	
	public Date getpublicationDate(){
		return publicationDate;
	}
	
	public String getCallNo(){
		return callNo;
	}
	
	public String getAccessionNo(){
		return accessionNo;
	}
	
	public boolean getIsBorrowed() {
		return isBorrowed;
	}
	
	public String toString() {
		return String.format("\nTitle : %s\nPublisher : %s\nPublication Date : %s\nCall No : %s\nAccession No : %s\nIs Borrowed? : %s\n", title, publisher, Main.sdf.format(new Date()), callNo, accessionNo, isBorrowed);
	}
}
