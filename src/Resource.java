import java.util.Date;
import java.text.SimpleDateFormat;

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
	
	public String toString() {
		return String.format("%s\n%s\n%s\n%s\n%s\n", title, publisher, new SimpleDateFormat("yyyy-MM-dd").format(new Date()), callNo, accessionNo);
	}
}
