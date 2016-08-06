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
    }
    
    public Magazine(String[] data) {
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
    	for(int i=0;i<Main.magazine.size();i++) {
			if(Main.magazine.get(i).getCallNo().equals(callNo))
				return Main.magazine.get(i);
		}
		return null;
    }

    public String toString()
    {
    	return String.format(super.toString() + "ISSN : %s\nVolume No : %s\n", ISSN, volNo);
    }
}