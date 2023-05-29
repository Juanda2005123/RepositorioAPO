package model;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.ArrayList;

public class Controller {

	private ArrayList <User> users;
	private ArrayList <Bibliographic> biblio;
	private String autoUsers[][];
	private String autoProducts[];
	private ArrayList <Invoice> invoices;

	public Controller() {

		users = new ArrayList<User>();
		biblio = new ArrayList<Bibliographic>();
		//Name Nickname
		autoUsers = new String[10][2];
		//Name
		autoProducts = new String[20];
		invoices = new ArrayList<Invoice>();

		createArrays();

	}

	public void createArrays() {
		//Users
		autoUsers[0][0] = "Juan David";
		autoUsers[0][1] = "JuanDa";
		autoUsers[1][0] = "Juan Camilo";
		autoUsers[1][1] = "JuanK";
		autoUsers[2][0] = "Esteban";
		autoUsers[2][1] = "Est23";
		autoUsers[3][0] = "Isabella";
		autoUsers[3][1] = "Isa";
		autoUsers[4][0] = "Dana";
		autoUsers[4][1] = "Danita";
		autoUsers[5][0] = "Cristina";
		autoUsers[5][1] = "Cristi";
		autoUsers[6][0] = "Cristiano Ronaldo";
		autoUsers[6][1] = "CR7";
		autoUsers[7][0] = "Messi Cuccittini";
		autoUsers[7][1] = "Goat";
		autoUsers[8][0] = "Lebron James";
		autoUsers[8][1] = "The King";
		autoUsers[9][0] = "Stephen Curry";
		autoUsers[9][1] = "Chef Curry";
		//Products
		autoProducts[0] = "Biblia"; 
		autoProducts[1] = "El Señor de los Anillos";
		autoProducts[2] = "Harry Potter";
		autoProducts[3] = "Odisea";
		autoProducts[4] = "Don Quijote de la Mancha";
		autoProducts[5] = "Cien Años de Soledad";
		autoProducts[6] = "El Codigo da Vinci";
		autoProducts[7] = "El diario de Ana Frank";
		autoProducts[8] = "War and Peace";
		autoProducts[9] = "Vogue";
		autoProducts[10] = "L Officiel";
		autoProducts[11] = "Playboy";
		autoProducts[12] = "National Geographic";
		autoProducts[13] = "Chernobyl";
		autoProducts[14] = "The Last of Us";
		autoProducts[15] = "Fast and Furious";
		autoProducts[16] = "Transformers";
		autoProducts[17] = "Halo";
		autoProducts[18] = "The Good Doctor";
		autoProducts[19] = "Maze Runner";

	}
	//Hace falta realizar la compras o suscripciones
	//Para devolver el boolean se verifican que todas las flags sean true
	public boolean generateAuto() {


		Calendar signUpDate = Calendar.getInstance();
		boolean flag0 = false;
		boolean flag1 = false;
		boolean flag2 = false;
		boolean flag3 = false;
		boolean flag4 = false;
		boolean flag5 = false;

		for (int j = 0; j <2; j++){
			int numUser = new Random().nextInt(10);
			String userName = autoUsers[numUser][0];
			String userNickname = autoUsers[numUser][1];
			String userId = generateUniqueUserId();
			if (j == 0){
				User regular = new Regular(userId, userName, userNickname, signUpDate);
				flag0 = users.add(regular);

			}else {
				User premium = new Premium(userId, userName, userNickname, signUpDate);
				flag1 = users.add(premium);


			}
		}
		
		for(int i = 0; i<4; i++){

			int num = new Random().nextInt(20);
			String name = autoProducts[num];
			int randomPages = 0;
			String url = generateUrl(name);
			Calendar publicationDate = generateDate();
			int genreCategory = new Random().nextInt(3);

			if(i==0||i==1){

				randomPages = new Random().nextInt(100, 670);
				String review = generateReview(name);
				Genre genre = genreDecision(genreCategory);
				int priceBook = new Random().nextInt(10,200);
				double price = generatePrice(priceBook);
				if(i==0){

					Bibliographic firstBook = new Book(generateUniqueIdentificationHexa(), name, randomPages, url, 0, publicationDate, review, genre, price, 0);
					flag2 = biblio.add(firstBook);

				} else {

					Bibliographic secondBook = new Book(generateUniqueIdentificationHexa(), name, randomPages, url, 0, publicationDate, review, genre, price, 0);
					flag3 = biblio.add(secondBook);

				}
			} else{

				randomPages = new Random().nextInt(12, 40);
				CategoryMagazine category = categoryDecision(genreCategory);
				int priceMagazine = new Random().nextInt(5,40);
				double price = generatePrice(priceMagazine);
				int periodicity = new Random().nextInt(12);
				periodicity += 1;
				if(i==2){

					Bibliographic firstMagazine = new Magazine(generateUniqueIdentificationAlfa(), name, randomPages, url, 0, publicationDate, category, price, periodicity, 0);
					flag4 = biblio.add(firstMagazine);

				} else {

					Bibliographic secondMagazine = new Magazine(generateUniqueIdentificationAlfa(), name, randomPages, url, 0, publicationDate, category, price, periodicity, 0);
					flag5 = biblio.add(secondMagazine);

				}
			}
		}

		if(((flag0&&flag1)&&(flag2&&flag3))&&(flag4&&flag5)){
			return true;
		}
		return false;
	
		
		
	}
	/**
	 * @param name String
	 * @return String
	 */
	public String generateUrl (String name){
		String msg = "";

		String [] nameUrl = name.split(" ");
		for(int i = 0; i<nameUrl.length;i++){
			if(nameUrl[i]!=null){
				msg += nameUrl[i];
			}
		}
		msg += ".com";

		return msg;
	}

	/**
	 * @return Calendar
	 */
	public Calendar generateDate() {

		int year = new Random().nextInt(1900, 2024);
		int month = new Random().nextInt(1, 13);
		int day = new Random().nextInt(1, 28);


		Calendar calendar = new GregorianCalendar(year, month-1, day);

		return calendar;
	}

	/**
	 * @param name String
	 * @return String
	 */
	public String generateReview(String name) {
		String msg = name;

		int num = new Random().nextInt(5);
		switch (num) {
			case 0:
			msg += " is really good";
			break;
			case 1:
			msg += " is a masterpiece";
			break;
			case 2:
			msg += " is an ordinary book";
			break;
			case 3:
			msg += " is not as good as people thought";
			break;
			case 4:
			msg += " it is a freaking book made by God";
			break;
		}


		return msg;
	}

	/**
	 * @param price int
	 * @return double
	 */
	public double generatePrice (int price) {

		double realPrice = price+(0.99);
		return realPrice;
	}

	/**
	 * @return String
	 */
	public String getUserList() {

		String msg = "";

		for (int i = 0; i < users.size(); i++) {


			msg += "\n" + (i + 1) + ". " + users.get(i).getId() + " - " + users.get(i).getName();
			

		}

		return msg;

	}

	/**
	 * @return String
	 */
	public String getProductList() {

		String msg = "";

		for (int i = 0; i < biblio.size(); i++) {

			msg += "\n" + (i + 1) + ". " + biblio.get(i).getId() + " - " + biblio.get(i).getName();
			

		}

		return msg;

	}

	/**
	 * @return String
	 */
	public String getBookList() {

		String msg = "";

		for (int i = 0; i < biblio.size(); i++) {
			
			if(biblio.get(i) instanceof Book){
				msg += "\n" + (i + 1) + ". " + biblio.get(i).getId() + " - " + biblio.get(i).getName();
			}
			
		}

		return msg;

	}

	/**
	 * @return String
	 */
	public String getMagazineList() {

		String msg = "";

		for (int i = 0; i < biblio.size(); i++) {
			
			if(biblio.get(i) instanceof Magazine){
				msg += "\n" + (i + 1) + ". " + biblio.get(i).getId() + " - " + biblio.get(i).getName();
			}
			
		}

		return msg;

	}

	/**
	 * @param id String
	 * @param name String
	 * @param nickname String
	 * @param userDecision int
	 * @return boolean
	 */
	public boolean registerUser(String id, String name, String nickname, int userDecision) {

		Calendar signUpDate = Calendar.getInstance();

		if(userDecision==1){
			//regular
			Regular userR = new Regular(id, name, nickname, signUpDate);
			return users.add(userR);
			
		
	
		}

		Premium userP = new Premium(id, name, nickname, signUpDate);
		return users.add(userP);
		
		
	} 



		
	

	/**
	 * @param userPosition int
	 * @param newName String
	 * @return boolean
	 */
	public boolean editUser(int userPosition, String newName) {

		if(userPosition>=users.size()){
			return false;
		}

		users.get(userPosition).setName(newName);

		if(users.get(userPosition).getName().equalsIgnoreCase(newName)){
			return true;
		}
		
		return false;
		
	}

	/**
	 * @param userPosition int
	 * @return boolean
	 */
	public boolean deleteUser(int userPosition) {

		if(userPosition>=users.size()){
			return false;
		}
		users.remove(userPosition);
		return true;

	}

	//"" Invalido
	//"P" Premium
	//"R" Regular
	/**
	 * @param position int
	 * @return String
	 */
	public String verifyTypeOfUser(int position) {

		if(position>=users.size()){
			return "";
		}
		if(verifyTypeOfUser1(position)){
			return "P";
		} else {
			return "R";
		}
	}

	/**
	 * @param position int
	 * @return boolean
	 */
	public boolean verifyTypeOfUser1(int position){

		if(users.get(position) instanceof Premium){
			return true;
		}

		return false;
	}

	/**
	 * @param id String
	 * @return int
	 */
	public int findPositionOfUserWithId(String id){
		
		for(int i = 0; i < users.size(); i++) {
			String realId = users.get(i).getId();
			if(realId.equalsIgnoreCase(id)){
				return i;
			}
		}

		return -1;
	}


	/**
	 * @param option int
	 * @return Genre
	 */
	public Genre genreDecision (int option) {

		Genre genre = Genre.HISTORICAL_NOVEL;
		if(option==1){
			genre = Genre.SCIENCE_FICTION;
		} else if(option==2){
			genre = Genre.FANTASY;
		}
		return genre;
	}
	/**
	 * @param option int
	 * @return CategoryMagazine
	 */
	public CategoryMagazine categoryDecision (int option) {

		CategoryMagazine categoryMagazine = CategoryMagazine.SCIENCE;
		if(option==1){
			categoryMagazine = CategoryMagazine.VARIETY;
		} else if(option==2){
			categoryMagazine = CategoryMagazine.DESIGN;
		}

		return categoryMagazine;
	}

	/**
	 * @param position int
	 * @return String
	 */
	public String userMagazineListToCancel(int position) {
		String msg = "";

		ArrayList <Bibliographic> list = (users.get(position)).userMagazineListToCancel();

		if(list.isEmpty()){
			return msg;
		}

		for (int i = 0; i < list.size(); i++){
			msg += "\n" +(i+1) +". "+ list.get(i).getId() + " - " + list.get(i).getName();;

		}
		return msg;
	}

	/**
	 * @param option int
	 * @param position int
	 * @return boolean
	 */
	public boolean cancelSuscription(int option, int position) {

		String idProduct = users.get(position).cancelSuscription(option);

		for(int i = 0; i < biblio.size(); i++){
			String realId = biblio.get(i).getId();
			if(realId.equalsIgnoreCase(idProduct)){
				((Magazine) biblio.get(i)).addActiveSuscriptions(-1);
				return true;
			}
		}
		return false;
	}




	/**
	 * @param name String
	 * @param url String
	 * @param pagesNumber int
	 * @param dayPublished int
	 * @param monthPublished int
	 * @param yearPublished int
	 * @param review String
	 * @param genreDecision int
	 * @param price double
	 * @return boolean
	 */
	public boolean registerBook(String name, String url, int pagesNumber, int dayPublished, int monthPublished, int yearPublished, String review, int genreDecision, double price){

		Calendar publicationDate = new GregorianCalendar(yearPublished, monthPublished, dayPublished);

		Genre genre = genreDecision(genreDecision);

		String id = generateUniqueIdentificationHexa();
		Book newBook = new Book(id, name, pagesNumber, url, 0, publicationDate, review, genre, price, 0);

		return biblio.add(newBook);
	}

	/**
	 * @param name String
	 * @param url String
	 * @param pagesNumber int
	 * @param dayPublished int
	 * @param monthPublished int
	 * @param yearPublished int
	 * @param categoryDecision int
	 * @param price double
	 * @param periodicityIssue int
	 * @return
	 */
	public boolean registerMagazine(String name, String url, int pagesNumber, int dayPublished, int monthPublished, int yearPublished, int categoryDecision, double price, int periodicityIssue){

		Calendar publicationDate = new GregorianCalendar(yearPublished, monthPublished, dayPublished);

		CategoryMagazine categoryMagazine = categoryDecision(categoryDecision);

		String id = generateUniqueIdentificationAlfa();
		Magazine newMagazine = new Magazine(id, name, pagesNumber, url, 0, publicationDate, categoryMagazine, price, periodicityIssue, 0);

		return biblio.add(newMagazine);
	}

	/**
	 * @param newName String
	 * @param newNumberPages int
	 * @param newUrl String
	 * @param newPrice double
	 * @param decision int
	 * @param option int
	 * @return boolean
	 */
	public boolean modifyBibliographicProducts(String newName, int newNumberPages, String newUrl, double newPrice, int decision, int option){

		if(option>=biblio.size()){
			return false;
		}

		biblio.get(option).setName(newName);
		biblio.get(option).setPagesNumber(newNumberPages);
		biblio.get(option).setUrl(newUrl);

		if(verifyTypeOfProduct(option)){
			Genre genre = genreDecision(decision);
			((Book) biblio.get(option)).setGenre(genre);
			((Book) biblio.get(option)).setPrice(newPrice);
			return true;
		} else {
			CategoryMagazine categoryMagazine = categoryDecision(decision);
			((Magazine) biblio.get(option)).setCategoryMagazine(categoryMagazine);
			((Magazine) biblio.get(option)).setPriceSuscription(newPrice);
			return true;
		}

	}

	/**
	 * @param option int
	 * @return boolean
	 */
	public boolean deleteBibliographicProducts(int option) {


		if(option>=biblio.size()){
			return false;
		}
		if(biblio.get(option) instanceof Magazine){
			String magazineId = biblio.get(option).getId();

			for(int i = 0; i < users.size(); i++){
				if(users.get(i) instanceof Premium) {
					((Premium)users.get(i)).deleteMagazine(magazineId);
				} else {
					((Regular)users.get(i)).deleteMagazine(magazineId);
				}
			}
		}
		biblio.remove(option);
		return true;
	}

	/**
	 * @param id String
	 * @param decisionBook int
	 * @return boolean
	 */
	public boolean buyBook(String id, int decisionBook) {

		int position = findPositionOfUserWithId(id);
		if(position == -1) {
			return false;
		} else {

			String user = verifyTypeOfUser(position);
			Calendar date = Calendar.getInstance();

			((Book) biblio.get(decisionBook)).addCopiesSold(1);
			if(user.equalsIgnoreCase("P")){
				Bibliographic p = biblio.get(decisionBook);
				p.setPagesRead(1);

				double price = ((Book) biblio.get(decisionBook)).getPrice();
				users.get(position).generateInvoice(price, date);
				generateInvoice(price,date);

				return ((Premium) users.get(position)).addBiblioProduct(p);

			} else if (user.equalsIgnoreCase("R")) {
				Bibliographic p = biblio.get(decisionBook);
				p.setPagesRead(1);

				double price = ((Book) biblio.get(decisionBook)).getPrice();
				users.get(position).generateInvoice(price, date);
				generateInvoice(price,date);

				return ((Regular) users.get(position)).addBiblioProduct(p);

			} else {
				return false;
			}
		}

	}

	/**
	 * @param id String
	 * @param decisionMagazine int
	 * @return boolean
	 */
	public boolean subscribeMagazine(String id, int decisionMagazine) {

		int position = findPositionOfUserWithId(id);
		if(position == -1) {
			return false;
		} else {

			String user = verifyTypeOfUser(position);
			Calendar date = Calendar.getInstance();

			((Magazine) biblio.get(decisionMagazine)).addActiveSuscriptions(1);
			if(user.equalsIgnoreCase("P")){
				Bibliographic p = biblio.get(decisionMagazine);
				p.setPagesRead(1);

				double price = ((Magazine) biblio.get(decisionMagazine)).getPriceSuscription();
				users.get(position).generateInvoice(price, date);
				generateInvoice(price,date);

				return ((Premium) users.get(position)).addBiblioProduct(p);

			} else if (user.equalsIgnoreCase("R")) {
				Bibliographic p = biblio.get(decisionMagazine);
				p.setPagesRead(1);

				double price = ((Magazine) biblio.get(decisionMagazine)).getPriceSuscription();
				users.get(position).generateInvoice(price, date);
				generateInvoice(price,date);

				return ((Regular) users.get(position)).addBiblioProduct(p);

			} else {
				return false;
			}
		}

	}

	/**
	 * @return String
	 */
	public String showInvoice() {
		
		int num = invoices.size();
		return invoices.get(num-1).toStringInvoice();
	}

	/**
	 * @param price double
	 * @param date Calendar
	 */
	public void generateInvoice(double price, Calendar date) {
		
		Invoice newInvoice = new Invoice(date, price);
		invoices.add(newInvoice);
	}

	/**
	 * @param position int
	 * @param informationArray String[]
	 * @return String[]
	 */
	public String[] getUserBiblioList(int position, String[] informationArray){

		String user = verifyTypeOfUser(position);

		if(user.equalsIgnoreCase("P")){

			if(informationArray[1].length()==3)  {
				informationArray[0] = "Reading";
				String productId = getProductId(informationArray, position);
				informationArray = ((Premium)users.get(position)).readingSession(productId, informationArray);
				if(informationArray[3].equalsIgnoreCase("S")){
					biblio.get(idBiblioPositionReadingSession(productId)).addNewReadPage(1);
				}
				return informationArray;
				
			}

			informationArray[0] = "Biblio";
			informationArray = ((Premium)users.get(position)).toStringBiblioList(informationArray);
			return informationArray;



		} else if(user.equalsIgnoreCase("R")){

			if(informationArray[1].length()==3){
				informationArray[0] = "Reading";
				String productId = getProductId(informationArray, position);
				informationArray = ((Regular)users.get(position)).readingSession(productId, informationArray);
				if(informationArray[3].equalsIgnoreCase("S")){
					biblio.get(idBiblioPositionReadingSession(productId)).addNewReadPage(1);
				}
				return informationArray;

			}

			informationArray[0] = "Biblio";
			informationArray = ((Regular)users.get(position)).toStringBiblioList(informationArray);
			return informationArray;
		}

		return informationArray;
	}

	/**
	 * @param productId String
	 * @return int
	 */
	public int idBiblioPositionReadingSession(String productId){
		for(int i = 0; i < biblio.size(); i++){
			String id = biblio.get(i).getId();
			if(productId.equalsIgnoreCase(id)){
				return i;
			}
		}
		return -1;
	}

	/**
	 * @param biblioDecision String[]
	 * @param position int
	 * @return
	 */
	public String getProductId(String[] biblioDecision, int position){

		if(biblioDecision[1].contains(",")){
			String[] notAnswer = biblioDecision[1].split(",");
			int[] answer = new int[2];
			answer[0] = Integer.parseInt(notAnswer[0]);
			answer[1] = Integer.parseInt(notAnswer[1]);
			return users.get(position).getProductId(answer, biblioDecision);
		} 
		return biblioDecision[1];

		

	}


	
	/**
	 * @param position int
	 * @return boolean
	 */
	public boolean verifyTypeOfProduct(int position){

		if(biblio.get(position) instanceof Book){
			return true;
		}
		return false;
	}



	/**
	 * @return String
	 */
	public String generateUniqueIdentificationHexa(){

		String alphabet = "ABCDEF0123456789";
        Random r = new Random();
		String msg = "";

		boolean comprobation = true;
		while(comprobation){
			comprobation = false;
			msg = "";
			for(int j = 0; j < 3; j++){
				char c = alphabet.charAt(r.nextInt(alphabet.length()));
				msg += c; 
			}


			for(int i = 0; i<biblio.size(); i++){
			
				String biblioId = biblio.get(i).getId();
	
				if(biblioId.equalsIgnoreCase(msg)){
					comprobation = true;
				}

			}

		}	
		return msg;

	}
	/**
	 * @return String
	 */
	public String generateUniqueIdentificationAlfa(){

		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random r = new Random();
		String msg = "";

		boolean comprobation = true;
		while(comprobation){
			comprobation = false;
			msg = "";
			for(int j = 0; j < 3; j++){
				char c = alphabet.charAt(r.nextInt(alphabet.length()));
				msg += c; 
			}

			for(int i = 0; i<biblio.size(); i++){

				String biblioId = biblio.get(i).getId();
	
				if(biblioId.equalsIgnoreCase(msg)){
					comprobation = true;
				}

				
			}

		}	
		return msg;

	}
	/**
	 * @return String
	 */
	public String generateUniqueUserId(){

		String alphabet = "0123456789";
        Random r = new Random();
		String msg = "";

		boolean comprobation = true;
		while(comprobation){
			comprobation = false;
			msg = "1";
			for(int j = 0; j < 2; j++){
				char c = alphabet.charAt(r.nextInt(alphabet.length()));
				msg += c; 
			}

			for(int i = 0; i<users.size(); i++){

				String userId = users.get(i).getId();
	
				if(userId.equalsIgnoreCase(msg)){
					comprobation = true;
				}

				
			}

		}	
		return msg;

	}

	
	/**
	 * @return String
	 */
	public String totalPagesPerType() {
		int[] pagesRead = new int[2];
		pagesRead[0] = 0;
		pagesRead[1] = 0;

		for (int i = 0; i < biblio.size(); i++){
			if(biblio.get(i) instanceof Book){
				pagesRead[0] += biblio.get(i).getPagesRead();
			} else {
				pagesRead[1] += biblio.get(i).getPagesRead();
			} 
		}
		
		String msg = "The total amount of pages read in books: "+pagesRead[0];
		msg += "\nThe total amount of pages read in Magazines: "+pagesRead[1];


		return msg;

	}

	/**
	 * @return String
	 */
	public String informMostReadGenreCategory() {
		//0. Fantasy
		//1. Science Fiction
		//2. Historical Novel
		int[] pagesBook = new int[3];
		//0. Design
		//1. Science
		//2. Variety
		int[] pagesMagazine = new int[3];


		for (int i = 0; i < biblio.size(); i++) {
			if(biblio.get(i) instanceof Book){
				Genre genre = ((Book) biblio.get(i)).getGenre();
				if(genre==Genre.FANTASY){
					pagesBook[0] += biblio.get(i).getPagesRead();
				} else if(genre==Genre.SCIENCE_FICTION) {
					pagesBook[1] += biblio.get(i).getPagesRead();
				} else {
					pagesBook[2] += biblio.get(i).getPagesRead();
				}
			} else {
				CategoryMagazine categoryMagazine = ((Magazine) biblio.get(i)).getCategoryMagazine();
				if(categoryMagazine==CategoryMagazine.DESIGN) {
					pagesMagazine[0] += biblio.get(i).getPagesRead();
				} else if (categoryMagazine==CategoryMagazine.SCIENCE) {
					pagesMagazine[1] += biblio.get(i).getPagesRead();
				} else {
					pagesMagazine[2] += biblio.get(i).getPagesRead();
				}
			}
		}
		String msg = primitiveBookComparator(pagesBook);
		msg += primitiveMagazineComparator(pagesMagazine);
		return msg;

	}

	/**
	 * @param pagesBook int[]
	 * @return String
	 */
	public String primitiveBookComparator (int[] pagesBook) {

		String msg = "";
		if(pagesBook[0]>pagesBook[1]){
			if(pagesBook[0]>pagesBook[2]){
				msg += "The most read genre is "+Genre.FANTASY;
				msg += "\nThis genre has been read: "+pagesBook[0];
			} else {
				if (pagesBook[0]==pagesBook[2]) {
					msg += "The most read genres are "+Genre.FANTASY+ " and "+Genre.HISTORICAL_NOVEL;
					msg += "\nThese genres have been read: "+pagesBook[0];
				} else {
					msg += "The most read genre is "+Genre.HISTORICAL_NOVEL;
					msg += "\nThis genre has been read: "+pagesBook[2];
				}
			}
				
		} else {
			if(pagesBook[0]==pagesBook[1]){
				if(pagesBook[0]>pagesBook[2]){
					msg += "The most read genres are "+Genre.FANTASY+ " and "+Genre.SCIENCE_FICTION;
					msg += "\nThese genres have been read: "+pagesBook[0];
				} else {
					if(pagesBook[0]==pagesBook[2]){
						msg += "All books genres have the same amount of pages read";
						msg += "\nThe amount of pages read is: " +pagesBook[0];
					} else {
						msg += "The most read genre is "+Genre.HISTORICAL_NOVEL;
						msg += "\nThis genre has been read: "+pagesBook[2];
					}
				}
			} else {
				if(pagesBook[1]>pagesBook[2]) {
					msg += "The most read genre is "+Genre.SCIENCE_FICTION;
					msg += "\nThis genre has been read: "+pagesBook[1];
				} else {
					if(pagesBook[1]==pagesBook[2]){
						msg += "The most read genres are "+Genre.SCIENCE_FICTION+ " and "+Genre.HISTORICAL_NOVEL;
						msg += "\nThese genres have been read: "+pagesBook[1];
					} else {
						msg += "The most read genre is "+Genre.HISTORICAL_NOVEL;
						msg += "\nThis genre has been read: "+pagesBook[2];
					}
				}
			}
		}
		msg += "\n------------------------\n";
		return msg;
	}

	/**
	 * @param pagesBook int[]
	 * @return String
	 */
	public String primitiveMagazineComparator(int[] pagesBook) {

		String msg = "";
		if(pagesBook[0]>pagesBook[1]){
			if(pagesBook[0]>pagesBook[2]){
				msg += "The most read category is "+CategoryMagazine.DESIGN;
				msg += "\nThis category has been read: "+pagesBook[0];
			} else {
				if (pagesBook[0]==pagesBook[2]) {
					msg += "The most read categorys are "+CategoryMagazine.DESIGN+ " and "+CategoryMagazine.VARIETY;
					msg += "\nThese categorys have been read: "+pagesBook[0];
				} else {
					msg += "The most read category is "+CategoryMagazine.VARIETY;
					msg += "\nThis category has been read: "+pagesBook[2];
				}
			}
				
		} else {
			if(pagesBook[0]==pagesBook[1]){
				if(pagesBook[0]>pagesBook[2]){
					msg += "The most read categorys are "+CategoryMagazine.DESIGN +" and "+CategoryMagazine.SCIENCE;
					msg += "\nThese category have been read: "+pagesBook[0];
				} else {
					if(pagesBook[0]==pagesBook[2]){
						msg += "All magazines categorys have the same amount of pages read";
						msg += "\nThe amount of pages read is: " +pagesBook[0];
					} else {
						msg += "The most read category is "+CategoryMagazine.VARIETY;
						msg += "\nThis category has been read: "+pagesBook[2];
					}
				}
			} else {
				if(pagesBook[1]>pagesBook[2]) {
					msg += "The most read category is "+CategoryMagazine.SCIENCE;
					msg += "\nThis category has been read: "+pagesBook[1];
				} else {
					if(pagesBook[1]==pagesBook[2]){
						msg += "The most read categorys are "+CategoryMagazine.SCIENCE+ " and "+CategoryMagazine.VARIETY;
						msg += "\nThese categorys have been read: "+pagesBook[1];
					} else {
						msg += "The most read category is "+CategoryMagazine.VARIETY;
						msg += "\nThis category has been read: "+pagesBook[2];
					}
				}
			}
		}
		return msg;
	}

	/** 
	 * @return String
	 */
	public String informTop5BooksMagazines() {

		String[] msg = new String[2];
		msg[0] = "";
		msg[1] = "";
		int[] contador = new int[2];
		contador[0] = 1;
		contador[1] = 1;

		ArrayList <Bibliographic> top5 = new ArrayList<Bibliographic>();
        top5.addAll(biblio);
		Collections.sort(top5, Comparator.comparing(Bibliographic::getPagesRead).reversed());

		for (int i = 0; i < top5.size(); i++){
			if(top5.get(i) instanceof Book){
				if(contador[0]==6){
					break;
				}
				msg[0] += "\n-------------------";
				msg[0] += "\n"+contador[0];
				msg[0] += "\nName: "+top5.get(i).getName();
				msg[0] += "\nGenre: "+((Book) top5.get(i)).getGenre();
				msg[0] += "\nPages read: "+top5.get(i).getPagesRead();
				contador[0]++;
			} else {
				if(contador[1]==6){
					break;
				}
				msg[1] += "\n-------------------";
				msg[1] += "\n"+contador[1];
				msg[1] += "\nName: "+top5.get(i).getName();
				msg[1] += "\nGenre: "+((Magazine) top5.get(i)).getCategoryMagazine();
				msg[1] += "\nPages read: "+top5.get(i).getPagesRead();
				contador[1]++;
			}
		}

		if(msg[0].equals("")){
			msg[0] = "The are not books";
		}
		if(msg[1].equals("")){
			msg[1] = "The are not magazines";
		}

		String message = "\nThis is the top books and magazines:";
		message += "\n\nBooks:";
		message += "\n"+msg[0];
		message += "\n\nMagazines:";
		message += "\n"+msg[1];
		return message;
	}
	/**
	 * @return String
	 */
	public String informSellBooksAndAmountByGenre(){
		int[] sells = new int[3];
		double[] amount = new double[3];
		sells[0] = 0;
		sells[1] = 0;
		sells[2] = 0;

		for (int i = 0; i < biblio.size(); i++) {
			if(biblio.get(i) instanceof Book){
				Genre genre = ((Book) biblio.get(i)).getGenre();
				switch (genre) {
					case SCIENCE_FICTION:
					sells[0] += ((Book) biblio.get(i)).getCopiesSold();
					amount[0] += ((Book) biblio.get(i)).getPrice()*sells[0];
					break;
					case FANTASY:
					sells[1] += ((Book) biblio.get(i)).getCopiesSold();
					amount[1] += ((Book) biblio.get(i)).getPrice()*sells[1];
					break;
					case HISTORICAL_NOVEL:
					sells[2] += ((Book) biblio.get(i)).getCopiesSold();
					amount[2] += ((Book) biblio.get(i)).getPrice()*sells[2];
					break;
				}
			}
		}
		String msg = "\n--------------------------";
		msg += "\nSCIENCE FICTION:";
		msg += "\nSells: "+sells[0];
		msg += "\nAmount: "+amount[0];
		msg += "\nSCIENCE FICTION:";
		msg += "\nSells: "+sells[1];
		msg += "\nAmount: "+amount[1];
		msg += "\nSCIENCE FICTION:";
		msg += "\nSells: "+sells[2];
		msg += "\nAmount: "+amount[2];
		return msg;
	}

	/**
	 * @return String
	 */
	public String informMagazinesSuscriptionsAndAmountByCategory() {
		int[] sells = new int[3];
		double[] amount = new double[3];
		sells[0] = 0;
		sells[1] = 0;
		sells[2] = 0;

		for (int i = 0; i < biblio.size(); i++) {
			if(biblio.get(i) instanceof Magazine){
				CategoryMagazine categoryMagazine = ((Magazine) biblio.get(i)).getCategoryMagazine();
				switch (categoryMagazine) {
					case VARIETY:
					sells[0] += ((Magazine) biblio.get(i)).getActiveSuscriptions();
					amount[0] += ((Magazine) biblio.get(i)).getPriceSuscription()*sells[0];
					break;
					case DESIGN:
					sells[1] += ((Magazine) biblio.get(i)).getActiveSuscriptions();
					amount[1] += ((Magazine) biblio.get(i)).getPriceSuscription()*sells[1];
					break;
					case SCIENCE:
					sells[2] += ((Magazine) biblio.get(i)).getActiveSuscriptions();
					amount[2] += ((Magazine) biblio.get(i)).getPriceSuscription()*sells[2];
					break;
				}
			}
		}
		String msg = "\n--------------------------";
		msg += "\nVARIETY:";
		msg += "\nActive Suscriptions: "+sells[0];
		msg += "\nAmount: "+amount[0];
		msg += "\nDESIGN:";
		msg += "\nActive Suscriptions: "+sells[1];
		msg += "\nAmount: "+amount[1];
		msg += "\nSCIENCE:";
		msg += "\nActive Suscriptions: "+sells[2];
		msg += "\nAmount: "+amount[2];
		return msg;
	}





}
