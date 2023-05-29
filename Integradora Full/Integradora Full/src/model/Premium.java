package model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

public class Premium extends User {

    private ArrayList <Bibliographic> biblioPremium;

    /**
     * @param id String
     * @param name String
     * @param nickname String
     * @param signUpDate Calendar
     */
    public Premium(String id, String name, String nickname, Calendar signUpDate) {
        super(id, name, nickname, signUpDate);

        biblioPremium = new ArrayList<Bibliographic>();

    }
    /**
     * @return String
     * @throws ParseException
     */
    public String toStringLoca() throws ParseException{
        String msg = "Id:"+ this.getId();
        msg += "\nNombre:"+this.getName();
        msg += "\nNickname: "+this.getNickname();
        msg += "\nFecha de vinculacion: "+this.getInitialDateFormated();
        return msg;
    }
    /**
	 * @param p Bibliographic
	 * @return boolean
	 */
    public boolean addBiblioProduct (Bibliographic p){

        return biblioPremium.add(p);

    }


    /**
	 * @param informationArray String[]
	 * @return String[]
	 */
    public String[] toStringBiblioList(String[] informationArray) {
        boolean pri = false;
        if(biblioPremium.size()>0){
            pri = true;
        }
        if(!pri){
            informationArray[3]="";
            return informationArray;
        }
        if(informationArray[1].equalsIgnoreCase("E")){
            informationArray[3]="OUT";
            return informationArray;
            
        }

        ArrayList <Bibliographic> dateBibliographic = returnBiblioDate();
        double numberOfPages = dateBibliographic.size()/25;
        int val = Integer.parseInt(informationArray[4]);

        if(informationArray[1].equalsIgnoreCase("S")){
            if((val+1)>numberOfPages){
                informationArray[3] = "NOS";
                return informationArray;
            } else {
                val++;
                informationArray[4]=""+val;
            }
        }
        if(informationArray[1].equalsIgnoreCase("A")){
            if((val-1)<0){
                informationArray[3]="NOA";
                return informationArray;
            } else {
                val--;
                informationArray[4]=""+val;
            }
        }

        int k = val*25;

        Bibliographic[][] matrix = new Bibliographic[5][5];

        String msg = "Biblioteca de "+getName()+"\n";
        msg += "\n";
        msg += "   |  0  |  1  |  2  |  3  |  4  |";

        
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[i].length; j++){
                if(k==dateBibliographic.size()){
                    break;
                }
                matrix[i][j] = dateBibliographic.get(k);
                k++;
            }
        }
            
        
        
        
        for (int i = 0; i < matrix.length; i++){
            msg += "\n "+i+" |";
            for (int j = 0; j < matrix[i].length; j++){
                if(matrix[i][j]!=null){
                    msg += " "+matrix[i][j].getId()+" |";
                } else {
                    msg += " --- |";
                }
            }
        }

      

        msg += "\nType the x,y coordinate or the corresponding code of the bibliographic product";
        msg += "\nType S for the next page \nType A for the previous page\nType E to exit";
        informationArray[3] = msg;
        return informationArray;


    }

    /**
     * @return ArrayList <Bibliographic>
     */
    public ArrayList <Bibliographic> returnBiblioDate(){

        //1 Mas reciente
        ArrayList <Bibliographic> dateBibliographic = new ArrayList<Bibliographic>();

        dateBibliographic.addAll(biblioPremium);

        Collections.sort(dateBibliographic, Comparator.comparing(Bibliographic::getPublicationDate));

        return dateBibliographic;

    }
    /**
	 * @param answer int[]
     * @param b String[]
	 * @return String
	 */
    public String getProductId(int[] answer, String[] b) {

        ArrayList <Bibliographic> dateBibliographic = returnBiblioDate();
        int val = Integer.parseInt(b[4]);
        
        Bibliographic[][] matrix = new Bibliographic[5][5];
        
        int k = val*25;

        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[i].length; j++){
                if(k==dateBibliographic.size()){
                    break;
                }
                matrix[i][j] = dateBibliographic.get(k);
                k++;
            }
        }

        String id = matrix[answer[0]][answer[1]].getId();
        return id;
    }
    /**
	 * @param productId String
     * @param informationArray String[]
	 * @return String[]
	 */
    public String[] readingSession(String productId, String[] informationArray) {
        String msg = "";
        int num = 0;

        for (int i = 0; i < biblioPremium.size(); i++){
                String id = biblioPremium.get(i).getId();
                if(id.equalsIgnoreCase(productId)){
                    num = i;
                    msg = ".";
                }
        }
        if(msg.equals("")){
            informationArray[3] = msg;
            return informationArray;
        }
        msg = "";

        if(informationArray[2].equalsIgnoreCase("S")){
            if(biblioPremium.get(num).getPagesRead()!=biblioPremium.get(num).getPagesNumber()){
                biblioPremium.get(num).addNewReadPage(1);
            } else {
                msg += "\nYou have finished reading it \n";
            }
        } 
        if(informationArray[2].equalsIgnoreCase("A")){
            if(biblioPremium.get(num).getPagesRead()==1){
                msg += "\nYou are in the first page\n";
            } else {
                biblioPremium.get(num).addNewReadPage(-1);
            }
        }
        if(informationArray[2].equalsIgnoreCase("B")){
            informationArray[3] = "BACK";
            return informationArray;
        }
        msg += "\nReading session in progress\n";
        msg += "\nReading: "+biblioPremium.get(num).getName()+"\n";
        msg += "\nReading page "+biblioPremium.get(num).getPagesRead()+" of "+biblioPremium.get(num).getPagesNumber()+"\n";
        
        msg += "Type A for the previus page\n";
        msg += "Type S for the next page\n";
        msg += "Type B to return to the library\n";

        informationArray[3] = msg;

        return informationArray;
    }
    /**
	 * @return ArrayList<Bibliographic>
	 */
    public ArrayList<Bibliographic> userMagazineListToCancel() {
        ArrayList <Bibliographic> list = new ArrayList<Bibliographic>();
        if(biblioPremium.size()==0){
            list.add(null);
        }
        for (int i = 0; i < biblioPremium.size(); i++){
            if(biblioPremium.get(i) instanceof Magazine){
                list.add(biblioPremium.get(i));
            }
        }
        return list;
    }
    /**
	 * @param option int
	 * @return String
	 */
    public String cancelSuscription(int option) {

        if(option>=biblioPremium.size()){
			return "false";
		}
        String i = biblioPremium.get(option).getId();
		biblioPremium.remove(option);
		return i;
    }
    /**
	 * @param magazineId String
	 */
    public void deleteMagazine(String magazineId) {
        
        for (int i = 0; i < biblioPremium.size(); i++){
            String realId = biblioPremium.get(i).getId();
            if(realId.equalsIgnoreCase(magazineId)){
                biblioPremium.remove(i);
            }
        }
    }

    
}
