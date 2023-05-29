package model;

import java.util.Calendar;
//import java.text.SimpleDateFormat;
//import java.text.ParseException;
//import java.text.DateFormat;

public class Book extends Bibliographic{
    
    private String review;
    private Genre genre;
    private double price;
    private int copiesSold;

    /**
     * @param id String
     * @param name String
     * @param pagesNumber int
     * @param url String
     * @param pagesRead int
     * @param publicationDate Calendar
     * @param review String
     * @param genre Genre
     * @param price double
     * @param copiesSold int
     */
    public Book(String id, String name, int pagesNumber, String url, int pagesRead, Calendar publicationDate, String review, Genre genre, double price, int copiesSold){
        super(id, name, pagesNumber, url, pagesRead, publicationDate);
        this.review = review;
        this.genre = genre;
        this.price = price;
        this.copiesSold = copiesSold;
    }

    /**
     * @param p int
     */
    public void addCopiesSold(int p) {
        this.copiesSold += p;
    }

    /**
     * @return String
     */
    public String getReview() {
        return review;
    }

    /**
     * @param review String
     */
    public void setReview(String review) {
        this.review = review;
    }

    /**
     * @return Genre
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * @param genre Genre
     */
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    /**
     * @return double
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price double
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return int
     */
    public int getCopiesSold() {
        return copiesSold;
    }

    /**
     * @param copiesSold int
     */
    public void setCopiesSold(int copiesSold) {
        this.copiesSold = copiesSold;
    }

    
}
