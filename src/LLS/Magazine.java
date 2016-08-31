package LLS;
public class Magazine extends Resource {

	private String ISSN;
	private String volNo;

    public Magazine() {
    }

    public Magazine(String title, String publisher, String publicationDate, String ISSN, String volNo)
    {
		super(title, publisher, publicationDate);
		this.ISSN = ISSN;
		this.volNo = volNo;
		Main.resource.add(this);
    }
    
    public Magazine(String[] data) {
    	//constructors of Magazine class for the data from the Magazine file
    	super(data[0], data[1], data[2]);
		super.setCallNo(data[3]);
		super.setAccessionNo(data[4]);
		super.setIsBorrowed(Boolean.parseBoolean(data[5]));
		ISSN = data[6];
		volNo = data[7];
	}

    public String getISSN(){
    	return ISSN;
    }
    
    public String getVolNo(){
    	return volNo;
    }
    
    public static Magazine search(String callNo) {
    	//returns the Magazine object if the callNo is found existed in a magazine
    	for(int i=0;i<Main.resource.size();i++) {
			if(Main.resource.get(i) instanceof Magazine && Main.resource.get(i).getCallNo().equals(callNo))
				return (Magazine)Main.resource.get(i);
		}
		return null;
    }
    
    public String toRawData() {
    	//return data in raw format for file-writing purpose
    	return String.format("%s%s#%s#\r\n", super.toRawData(), ISSN, volNo);
    }

    public String toString() {
    	return String.format(super.toString() + "  ISSN\t\t : %s\n  Volume No\t : %s\n", ISSN, volNo);
    }
}