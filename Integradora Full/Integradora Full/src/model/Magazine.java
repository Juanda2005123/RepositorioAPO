package model;

import java.util.Calendar;
//import java.text.SimpleDateFormat;
//import java.text.ParseException;
//import java.text.DateFormat;

public class Magazine extends Bibliographic{
    private CategoryMagazine categoryMagazine;
    private double priceSuscription;
    private int periodicityIssue; //Months
    private int activeSuscriptions;

    /**
     * @param id String
     * @param name String
     * @param pagesNumber int
     * @param url String
     * @param pagesRead int
     * @param publicationDate Calendar
     * @param categoryMagazine CategoryMagazine
     * @param priceSuscription double
     * @param periodicityIssue int
     * @param activeSuscriptions int
     */
    public Magazine(String id, String name, int pagesNumber, String url, int pagesRead, Calendar publicationDate, CategoryMagazine categoryMagazine, double priceSuscription, int periodicityIssue, int activeSuscriptions){
        super(id, name, pagesNumber, url, pagesRead, publicationDate);
        this.categoryMagazine = categoryMagazine;
        this.priceSuscription = priceSuscription;
        this.periodicityIssue = periodicityIssue;
        this.activeSuscriptions = activeSuscriptions;
    }

    /**
     * @param p int
     */
    public void addActiveSuscriptions(int p) {
        this.activeSuscriptions += p;
    }

    /**
     * @return CategoryMagazine
     */
    public CategoryMagazine getCategoryMagazine() {
        return categoryMagazine;
    }

    /**
     * @param categoryMagazine CategoryMagazine
     */
    public void setCategoryMagazine(CategoryMagazine categoryMagazine) {
        this.categoryMagazine = categoryMagazine;
    }

    /**
     * @return double
     */
    public double getPriceSuscription() {
        return priceSuscription;
    }

    /**
     * @param priceSuscription double
     */
    public void setPriceSuscription(double priceSuscription) {
        this.priceSuscription = priceSuscription;
    }

    /**
     * @return int
     */
    public int getPeriodicityIssue() {
        return periodicityIssue;
    }

    /**
     * @param periodicityIssue int
     */
    public void setPeriodicityIssue(int periodicityIssue) {
        this.periodicityIssue = periodicityIssue;
    }

    /**
     * @return int
     */
    public int getActiveSuscriptions() {
        return activeSuscriptions;
    }

    /**
     * @param activeSuscriptions int
     */
    public void setActiveSuscriptions(int activeSuscriptions) {
        this.activeSuscriptions = activeSuscriptions;
    }
    
}
