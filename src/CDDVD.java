public class CDDVD extends Resource{

    public CDDVD() {
    }

    public CDDVD(String title, String publisher, String publicationDate)
    {
    	super(title, publisher, publicationDate);
    }
    
    public CDDVD(String[] data) {
    	super(data[0], data[1], data[2]);
    	super.setAccessionNo(data[3]);
    	super.setCallNo(data[4]);
    	super.setIsBorrowed(Boolean.parseBoolean(data[5]));
    }
    
    public String toString() {
    	return super.toString();
    }
}