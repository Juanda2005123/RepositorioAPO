package model;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.text.DateFormat;

public abstract class Bibliographic {

    private String id;
    private String name;
    private int pagesNumber;
    private String url;
    private int pagesRead;
    private Calendar publicationDate;
    private DateFormat formatter;
    
    /**
     * @param id String
     * @param name String
     * @param pagesNumber int
     * @param url String
     * @param pagesRead int
     * @param publicationDate Calendar
     */
    public Bibliographic(String id, String name, int pagesNumber, String url, int pagesRead, Calendar publicationDate) {
        this.id = id;
        this.name = name;
        this.pagesNumber = pagesNumber;
        this.url = url;
        this.pagesRead = pagesRead;
        this.publicationDate = publicationDate;
        this.formatter = new SimpleDateFormat("dd/M/yy");
    
    }

    /**
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * @param id String
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return int 
     */
    public int getPagesNumber() {
        return pagesNumber;
    }

    /**
     * @param pagesNumber int
     */
    public void setPagesNumber(int pagesNumber) {
        this.pagesNumber = pagesNumber;
    }

    /**
     * @return String
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url String
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return int
     */
    public int getPagesRead() {
        return pagesRead;
    }

    /**
     * @param pagesRead int
     */
    public void setPagesRead(int pagesRead) {
        this.pagesRead = pagesRead;
    }

    /**
     * @param newPage int
     */
    public void addNewReadPage(int newPage) {
        this.pagesRead += newPage; 
    }

    /**
     * @return Calendar
     */
    public Calendar getPublicationDate() {
        return publicationDate;
    }

    /**
     * @param publicationDate Calendar
     */
    public void setPublicationDate(Calendar publicationDate) {
		this.publicationDate = publicationDate;
	}

	/**
	 * @return String
	 * @throws ParseException
	 */
	public String getInitialDateFormated() throws ParseException{
		return formatter.format(this.publicationDate.getTime());
	}

    

    
}
