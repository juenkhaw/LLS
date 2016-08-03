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
			this.publicationDate = Main.sdf.parse(publicationDate);
		} catch (ParseException e) {
		}
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
		return String.format("\n%s\n%s\n%s\n%s\n%s\n", title, publisher, new SimpleDateFormat("yyyy-MM-dd").format(new Date()), callNo, accessionNo);
	}
}
