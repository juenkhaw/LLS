package LLS;
import java.util.Date;

public class Resource {
	private String title;
	private String publisher;
	private Date publicationDate;
	private String callNo;
	private String accessionNo;
	private boolean isBorrowed;

	protected Resource() {
	}

	protected Resource(String title, String publisher, String publicationDate) {
		this.title = title;
		this.publisher = publisher;
		this.publicationDate = Main.convertToDate(publicationDate);
		do {
			accessionNo = String.format("%d:%03d",this.publicationDate.getYear()%100, Main.getRandomNum(1000));
		} while (!validateAccessionNo());
		do {
			callNo = String.format("%c%c-%03d-%c%02d-%04d", Main.getRandomChar('Z'), Main.getRandomChar('Z'), Main.getRandomNum(1000), title.charAt(0), Main.getRandomNum(100), this.publicationDate.getYear()+1900);
		} while (!validateCallNo());
		isBorrowed = false;
	}

	public void setTitle(String title){
		this.title = title;
	}
	
	public void setPublisher(String publisher){
		this.publisher = publisher;
	}
	
	protected void setCallNo(String callNo){
		this.callNo = callNo;
	}
	
	protected void setAccessionNo(String accessionNo){
		this.accessionNo = accessionNo;
	}
	
	public void setIsBorrowed (boolean isBorrowed) {
		this.isBorrowed = isBorrowed;
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
	
	public String getClassName() {
		if(this instanceof Book)
			return "Book";
		else if(this instanceof Magazine)
			return "Magazine";
		else
			return "CDDVD";
	}
	
	private boolean validateCallNo() {
		//returns true if the generated callNo has not been used
		for(int i=0;i<Main.resource.size();i++) {
			if(Main.resource.get(i).getCallNo().equals(callNo))
				return false;
		}
		return true;
	}
	
	private boolean validateAccessionNo() {
		//returns true if the generated accessionNo has not been used
		for(int i=0;i<Main.resource.size();i++) {
			if(Main.resource.get(i).getAccessionNo().equals(accessionNo))
				return false;
		}
		return true;
	}
	
	public static Resource search(String callNo) {
		//returns the Resource object if the callNo is found existed in a resource
		for(int i=0;i<Main.resource.size();i++) {
			if(Main.resource.get(i).getCallNo().equals(callNo))
				return Main.resource.get(i);
		}
		return null;
	}
	
	public static Resource searchNonBook(String callNo) {
		//returns the Magazine/CDDVD object if the callNo is found existed in a magazine/CDDVD
		if(Magazine.search(callNo)==null)
			return CDDVD.search(callNo);
		return Magazine.search(callNo);
	}
	
	protected String toRawData() {
		//return data in raw format for file-writing purpose
		return String.format("%s#%s#%s#%s#%s#%s#", title, publisher, Main.sdf.format(publicationDate), callNo, accessionNo, String.valueOf(isBorrowed));
	}
	
	public String toString() {
		return String.format("\n  Title\t\t : %s\n  Publisher\t : %s\n  Public. Date\t : %s\n  Resource Type\t : %s\n  Call No\t : %s\n  Accession No\t : %s\n  Is Borrowed?\t : %s\n", title, publisher, Main.sdf.format(new Date()), getClassName(), callNo, accessionNo, isBorrowed);
	}
}
