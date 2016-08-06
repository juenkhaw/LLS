public class CDDVD extends Resource{

    public CDDVD() {
    }

    public CDDVD(String title, String publisher, String publicationDate)
    {
    	super(title, publisher, publicationDate);
    	Main.CDDVD.add(this);
    }
    
    public CDDVD(String[] data) {
    	super(data[0], data[1], data[2]);
    	super.setCallNo(data[3]);
    	super.setAccessionNo(data[4]);
    	super.setIsBorrowed(Boolean.parseBoolean(data[5]));
    }
    
    public static CDDVD search(String callNo) {
    	for(int i=0;i<Main.CDDVD.size();i++) {
			if(Main.CDDVD.get(i).getCallNo().equals(callNo))
				return Main.CDDVD.get(i);
		}
		return null;
    }
    
    public String toRawData() {
    	return super.toRawData() + "\r\n";
    }
    
    public String toString() {
    	return super.toString();
    }
}