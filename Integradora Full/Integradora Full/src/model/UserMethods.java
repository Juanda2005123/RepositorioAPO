package model;

import java.util.ArrayList;

public interface UserMethods {
    
    public String[] toStringBiblioList(String[] biblioDecision);
    public boolean addBiblioProduct(Bibliographic p);
    public String getProductId(int[] answer, String[] p);
    public String[] readingSession(String productId, String[] informationArray);
    public ArrayList<Bibliographic> userMagazineListToCancel();
    public String cancelSuscription(int option);
    public void deleteMagazine(String magazineId);
}
