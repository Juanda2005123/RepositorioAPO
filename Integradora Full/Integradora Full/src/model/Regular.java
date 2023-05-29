package model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class Regular extends User{

    private Bibliographic[] biblioRegular;

    /**
     * @param id String
     * @param name String
     * @param nickname String
     * @param signUpDate Calendar
     */
    public Regular(String id, String name, String nickname, Calendar signUpDate) {
        super(id, name, nickname, signUpDate);

        biblioRegular = new Bibliographic[7];

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

        if(p instanceof Book){
            int bookCount = 0;
            for (int i = 0; i < biblioRegular.length; i++) {
                if(biblioRegular[i]!=null){
                    if(biblioRegular[i] instanceof Book){
                        bookCount++;
                    }
                }
            }
            if(bookCount<5){
                for (int i = 0; i < biblioRegular.length; i++){
                    if(biblioRegular[i]==null){
                        biblioRegular[i] = p;
                        return true;
                    }
                }
            }
            return false;
        }else {
            int magazineCount = 0;
            for (int i = 0; i < biblioRegular.length; i++) {
                if(biblioRegular[i]!=null){
                    if(biblioRegular[i] instanceof Magazine){
                        magazineCount++;
                    }
                }
            }
            if(magazineCount<2){
                for (int i = 0; i < biblioRegular.length; i++){
                    if(biblioRegular[i]==null){
                        biblioRegular[i] = p;
                        return true;
                    }
                }
            }
            return false;
        }



    }
    /**
	 * @param informationArray String[]
	 * @return String[]
	 */
    public String[] toStringBiblioList(String[] informationArray){
        boolean pri = false;
        for(int i = 0; i < biblioRegular.length; i++){
            if(biblioRegular[i]!=null){
                pri = true;
            }
        }
        if(!pri){
            informationArray[3]="";
            return informationArray;
        }
        if(informationArray[1].equalsIgnoreCase("E")){
            informationArray[3] = "OUT";
            return informationArray;
        }

        Bibliographic[] dateBibliographic = returnBiblioDate();
        Bibliographic[][] matrix = new Bibliographic[5][5];
        
        int k = 0;

        String msg = "Biblioteca de "+getName()+"\n";
        msg += "\n";
        msg += "   |  0  |  1  |  2  |  3  |  4  |";

        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[i].length; j++){
                if(dateBibliographic[k]!=null){
                    matrix[i][j] = dateBibliographic[k];
                    k++;
                }
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
        msg += "\nType E to exit";
        informationArray[3] = msg;
        return informationArray;


    }

    /**
     * @return Bibliographic[]
     */
    public Bibliographic[] returnBiblioDate(){

        //1 Mas reciente
        Bibliographic[] dateBibliographic = new Bibliographic[biblioRegular.length];

        Bibliographic biblioVariable =null;
        
        for (int i = 0; i < biblioRegular.length; i++){
            if(biblioRegular[i]!=null){
                dateBibliographic[i] = biblioRegular[i];
            }
        }

        for (int i = 0; i < dateBibliographic.length; i++){
            if(dateBibliographic[i]!=null){
                for (int j = (i+1); j < dateBibliographic.length; j++){
                    if(dateBibliographic[j]!=null){
                        int val = (dateBibliographic[i].getPublicationDate()).compareTo(dateBibliographic[j].getPublicationDate());
                        if(val==1||val==0){
                            biblioVariable = dateBibliographic[i];
                            dateBibliographic[i] = dateBibliographic[j];
                            dateBibliographic[j] = biblioVariable;
                        }
                    }
                    
                }
                
            }
        }
        return dateBibliographic;



    }
    /**
	 * @param answer int[]
     * @param b String[]
	 * @return String
	 */
    public String getProductId(int[] answer, String[] b) {

        Bibliographic[] dateBibliographic = returnBiblioDate();
        Bibliographic[][] matrix = new Bibliographic[5][5];
        
        int k = 0;

        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[i].length; j++){
                if(dateBibliographic[k]!=null){
                    matrix[i][j] = dateBibliographic[k];
                    k++;
                }
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

        for (int i = 0; i < biblioRegular.length; i++){
            if(biblioRegular[i]!=null){
                String id = biblioRegular[i].getId();
                if(id.equalsIgnoreCase(productId)){
                    num = i;
                    msg = ".";
                }
            }
        }
        if(msg.equals("")){
            informationArray[3] = msg;
            return informationArray;
        }
        msg = "";

        int sumBook = Integer.parseInt(informationArray[5]);
        int sumMagazine = Integer.parseInt(informationArray[6]);

        if(informationArray[2].equalsIgnoreCase("S")){
            if(biblioRegular[num].getPagesRead()!=biblioRegular[num].getPagesNumber()){
                biblioRegular[num].addNewReadPage(1);

                if(biblioRegular[num] instanceof Book){
                    sumBook++;
                    informationArray[5] = ""+sumBook;
                } else {
                    sumMagazine++;
                    informationArray[6] = ""+sumMagazine;
                }
            } else {
                msg += "\nYou have finished reading it \n";
            }
            
        } 

        if(informationArray[2].equalsIgnoreCase("A")){
            if(biblioRegular[num].getPagesRead()==1){
                msg += "\nYou are in the first page\n";
            } else {
                biblioRegular[num].addNewReadPage(-1);

                if(biblioRegular[num] instanceof Book){
                    sumBook++;
                    informationArray[5] = ""+sumBook;
                } else {
                    sumMagazine++;
                    informationArray[6] = ""+sumMagazine;
                }
            }
        }

        if(informationArray[2].equalsIgnoreCase("B")){
            informationArray[3] = "BACK";
            return informationArray;  
        }

        msg += "\nReading session in progress\n";
        msg += "\nReading: "+biblioRegular[num].getName()+"\n";
        msg += "\nReading page "+biblioRegular[num].getPagesRead()+" of "+biblioRegular[num].getPagesNumber()+"\n";
        
        msg += "Type A for the previus page\n";
        msg += "Type S for the next page\n";
        msg += "Type B to return to the library\n";

        if(biblioRegular[num] instanceof Book) {
            if((sumBook%20)==0) {
               msg += returnAdvertisements();
            }
        } else {
            if((sumMagazine%5)==0){
                msg += returnAdvertisements();
            }
        }
        informationArray[3] = msg;

        return informationArray;
    }
    /**
	 * @return String
	 */
    public String returnAdvertisements() {

        int numUser = new Random().nextInt(3);
        String msg = "";
        switch(numUser){
            
            case 0:
            msg = "\n--------------------------------------------------------------------------------------------\n";
            msg += "¡Suscríbete al Combo Plus y llévate Disney+ y Star+ a un precio increíble!";
            msg += "\n--------------------------------------------------------------------------------------------";
            return msg; 

            case 1:
            msg = "\n--------------------------------------------------------------------------------------------\n";
            msg += "Ahora tus mascotas tienen una app favorita: Laika. Los mejores productos para tu peludito.";
            msg += "\n--------------------------------------------------------------------------------------------";
            return msg;

            case 2:
            msg = "\n--------------------------------------------------------------------------------------------\n";
            msg += "¡Estamos de aniversario! Visita tu Éxito más cercano y sorpréndete con las mejores ofertas.";
            msg += "\n-------------------------------------------------------------------------------------------------";
            return msg;

        }
        return "\nAnuncio Imposible \nCompra Bytes";
    }
    /**
	 * @return ArrayList<Bibliographic>
	 */
    public ArrayList<Bibliographic> userMagazineListToCancel() {

        ArrayList <Bibliographic> list = new ArrayList<Bibliographic>();
        if(biblioRegular.length==0){
            list.add(null);
        }
        
        for (int i = 0; i < biblioRegular.length; i++){
            if(biblioRegular[i] instanceof Magazine){
                list.add(biblioRegular[i]);
            }
        }
        return list;
    }
    /**
     * @param option int
	 * @return String
	 */
    public String cancelSuscription(int option) {

        if(option>=biblioRegular.length){
			return "false";
		}
        String i = biblioRegular[option].getId();
        biblioRegular[option]=null;
		return i;
    }
    /**
     * @param magazineId String
	 */
    public void deleteMagazine(String magazineId) {

        for (int i = 0; i < biblioRegular.length; i++){
            if(biblioRegular[i]!=null){
                String realId = biblioRegular[i].getId();
                if(realId.equalsIgnoreCase(magazineId)){
                    biblioRegular[i]=null;
                }
            }
        }
    }

    
}
