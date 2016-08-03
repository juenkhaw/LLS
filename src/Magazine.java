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

    public String getISSN(){return ISSN;}
    public String getVolNo(){return volNo;}

    public String toString()
    {
    	return String.format(super.toString() + "ISSN : %s\nVolume No : %s\n", ISSN, volNo);
    }
}