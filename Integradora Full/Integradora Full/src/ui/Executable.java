package ui;

import java.util.Scanner;

import model.Controller;

public class Executable {

	private Scanner lector;
	private Controller controller;

	public Executable() {

		lector = new Scanner(System.in);
		controller = new Controller();
	}

	public static void main(String[] args) {

		Executable executable = new Executable();
		executable.menu();

	}

	private void menu() {

		System.out.println("Welcome to ReaderX!");

		boolean flag = false;

		while (!flag) {

			System.out.println("\n------MAIN MENU------");
			System.out.println("\n1. Manage users and cancel subscription");
			System.out.println("2. Manage bibliographic products");
			System.out.println("3. Automatic generation of objects");
			System.out.println("4. Buy a book");
			System.out.println("5. Subscribe to a magazine");
			System.out.println("6. Library and reading session");
			System.out.println("7. Reports");
			System.out.println("0. Salir");
			int option = lector.nextInt();

			switch (option) {

			case 1:
				manageUsers();
				break;
			case 2:
				manageBibliographicProducts();
				break;
			case 3:
				generateAuto();
				break;
			case 4:
				buyBook();
				break;
			case 5:
				subscribeMagazine();
				break;
			case 6:
				libraryReading();
				break;
			case 7:
				manageReports();
				break;
			case 0:
				flag = true;
				System.out.println("Come back soon");
				break;
			default:
				System.out.println("Invalid option");
				break;

			}

		}

	}

	

	private void manageUsers() {
		System.out.println("Type the action to be performed");
		System.out.println("1. Register user \n2. Modify user \n3. Delete user \n4. Cancel Suscription");
		int decision = lector.nextInt();
		switch(decision){
			case 1:
			registerUser();
			break;
			case 2:
			modifyUser();
			break;
			case 3:
			deleteUser();
			break;
			case 4:
			cancelSuscription();
			break;
			
		}
	}

	private void manageBibliographicProducts() {
		System.out.println("Type the action to be performed");
		System.out.println("1. Register bibliographic products \n2. Modify bibliographic products \n3. Delete bibliographic products");
		int decision = lector.nextInt();
		switch(decision){
			case 1:
			registerBibliographicProducts();
			break;
			case 2:
			modifyBibliographicProducts();
			break;
			case 3:
			deleteBibliographicProducts();
			break;
		}
	}

	private void registerUser() {

		System.out.println("Type the needed information to the register");

		// Limpieza de buffer
		lector.nextLine();

		System.out.println("Two types of users can be registered:");
		System.out.println("1. Regular \n2. Premium");
		int userDecision = lector.nextInt();

		lector.nextLine();

		System.out.println("Type the identification");
		String id = lector.nextLine();

		System.out.println("Type the name");
		String name = lector.nextLine();

		System.out.println("Type the nickname");
		String nickname = lector.nextLine();


		if (controller.registerUser(id, name, nickname, userDecision)) {

			System.out.println("User succesfully registered");

		} else {

			System.out.println("Could not register the new user");
		}
	}

	private void modifyUser() {

		String query = controller.getUserList();

		if (query.equals("")) {

			System.out.println("There are not registered users");
		} else {

			System.out.println("\nThis is the list of registered users in the system");

			System.out.println(query);

			System.out.println("\nChoose the user to modify");

			int option = lector.nextInt();
			option -= 1;

			lector.nextLine();

			System.out.println("Type the new user name");
			String newName = lector.nextLine();
			

			if (controller.editUser(option, newName)) {

				System.out.println("\nUser succesfully modified");

			} else {

				System.out.println("\nCould not modified the user");
			}

		}

	}

	private void deleteUser() {

		String query = controller.getUserList();

		if (query.equals("")) {

			System.out.println("There are not registered users");
		} else {

			System.out.println("\nThis is the list of registered users in the system");

			System.out.println(query);

			System.out.println("\nChoose the user to delete");

			int option = lector.nextInt();
			option -= 1;

			if (controller.deleteUser(option)) {

				System.out.println("\nUser succesfully deleted");

			} else {

				System.out.println("\nCould not deleted the user");
			}

		}

	}

	public void cancelSuscription() {

		lector.nextLine();
		System.out.println("In order to cancel a suscription we need your id:");
		String id = lector.nextLine();

		int position = controller.findPositionOfUserWithId(id);

		if(position==-1){
			System.out.println("Invalid identification");
		} else {
			String query = controller.userMagazineListToCancel(position);

			if(query.equals("")){
				System.out.println("You do not have magazines to cancel the suscription");
			} else {
				System.out.println("This is the list of suscriptions that you have \nChoose one to cancel");
				System.out.println(query);

				int option = lector.nextInt();
				option --;

				if(controller.cancelSuscription(option, position)){
					System.out.println("The suscription was cancelled succesfully");
				} else {
					System.out.println("Could not cancel the suscription");
				}

			}
		
		}
	}


	private void registerBibliographicProducts() {

		System.out.println("Type the needed information to the register");

		// Limpieza de buffer
		lector.nextLine();

		System.out.println("Two types of bibliographic products can be registered:");
		System.out.println("1. Book \n2. Magazine");
		int userDecision = lector.nextInt();

		lector.nextLine();


		System.out.println("Type the name");
		String name = lector.nextLine();

		System.out.println("Type the Url of the cover");
		String url = lector.nextLine();

		System.out.println("Type the pages number");
		int pagesNumber = lector.nextInt();

		System.out.println("-----------------------------");
		System.out.println("Type the day it was published");
		int dayPublished = lector.nextInt();
		System.out.println("Type the month it was published");
		int monthPublished = lector.nextInt();
		monthPublished -= 1;
		System.out.println("Type the year it was published");
		int yearPublished = lector.nextInt();
		System.out.println("-----------------------------");

		lector.nextLine();

		double price = 0;
		if(userDecision==1){
			System.out.println("Type a book review");
			String review = lector.nextLine();
			System.out.println("Choose the genre: \n1. <<SCIENCE FICTION>> \n2. <<FANTASY>> \n3. <<HISTORICAL NOVEL>>");
			int genreDecision = lector.nextInt();
			System.out.println("Type the price of the book(in dollars)");
			price = lector.nextDouble();

			if (controller.registerBook(name, url, pagesNumber, dayPublished, monthPublished, yearPublished, review, genreDecision, price)) {

				System.out.println("Book succesfully registered");
	
			} else {
	
				System.out.println("Could not register the book");
			}

		} else if(userDecision==2){
			System.out.println("Choose the category: \n1. <<VARIETY>> \n2. <<DESIGN>> \n3. <<SCIENCE>>");
			int categoryDecision = lector.nextInt();
			System.out.println("Type the price of the suscription");
			price = lector.nextInt();
			System.out.println("Type the periodicity issue(months)");
			int periodicityIssue = lector.nextInt();

			if (controller.registerMagazine(name, url, pagesNumber, dayPublished, monthPublished, yearPublished, categoryDecision, price, periodicityIssue)) {

				System.out.println("Magazine succesfully registered");
	
			} else {
	
				System.out.println("Could not register the magazine");
			}

		}


	}
			
	private void modifyBibliographicProducts() {

		String query = controller.getProductList();

		if (query.equals("")) {

			System.out.println("There are not registered products");
		} else {

			System.out.println("\nThis is the list of registered products in the system");

			System.out.println(query);

			System.out.println("\nChoose the product to modify");

			int option = lector.nextInt();
			option -= 1;

			lector.nextLine();

			System.out.println("Type the information needed to modify the product");
			System.out.println("The system allows to modify the name, number of pages, url, price and genre(books) or category(magazines)");
		
			System.out.println("Type the new name");
			String newName = lector.nextLine();
			System.out.println("Type the new number of pages");
			int newNumberPages = lector.nextInt();
			lector.nextLine();
			System.out.println("Type the new url");
			String newUrl = lector.nextLine();
			System.out.println("Type the new price");
			double newPrice = lector.nextDouble();

			int decision = 0;
			if(controller.verifyTypeOfProduct(option)){
				System.out.println("Choose the genre: \n1. <<SCIENCE FICTION>> \n2. <<FANTASY>> \n3. <<HISTORICAL NOVEL>>");
				decision = lector.nextInt();
			}else{
				System.out.println("Choose the category: \n1. <<VARIETY>> \n2. <<DESIGN>> \n3. <<SCIENCE>>");
				decision = lector.nextInt();
			}
			
			if(controller.modifyBibliographicProducts(newName, newNumberPages, newUrl, newPrice, decision, option)) {
				System.out.println("The product was modified succesfully");
			} else {
				System.out.println("Could not modified the product");
			}

		}
	}
	
	private void deleteBibliographicProducts() {
		

		String query = controller.getProductList();

		if (query.equals("")) {

			System.out.println("There are not registered products");

		} else {

			System.out.println("\nThis is the list of registered products in the system");

			System.out.println(query);

			System.out.println("\nChoose the product to delete");

			int option = lector.nextInt();
			option -= 1;

			if(controller.deleteBibliographicProducts(option)) {
				System.out.println("The product was deleted succesfully");
			} else {
				System.out.println("Could not delete the product");
			}
		}
	}

	private void generateAuto() {
		System.out.println("The system will generate 2 users (Premium and Regular) and 4 bibliograpich products (2 Books and 2 Magazines) randomly from a database");

		if(controller.generateAuto()) {
			System.out.println("Good");
		} else {
			System.out.println("Bad");
		}
	}

	private void buyBook(){

		String query = controller.getBookList();

		if(query.equals("")){
			System.out.println("There are not books to buy");
		} else {
			System.out.println("\nThis is the list of books registered in the system");
			System.out.println(query);
			System.out.println("\nChoose the book to buy");

			int option = lector.nextInt();
			option --;

			lector.nextLine();

			System.out.println("In order to buy a book we need your id:");
			String id = lector.nextLine();

			if(controller.buyBook(id, option)){
				System.out.println("The book was bought");
				System.out.println(controller.showInvoice());
			}else {
				System.out.println("The book could not be purchased");
			}


		}

	}

	private void subscribeMagazine() {

		String query = controller.getMagazineList();

		if(query.equals("")){
			System.out.println("There are not magazines to subscribe");
		} else {
			System.out.println("\nThis is the list of magazines registered in the system");
			System.out.println(query);
			System.out.println("\nChoose the magazine to subscribe");

			int option = lector.nextInt();
			option --;

			lector.nextLine();

			System.out.println("In order to subscribe to a magazine we need your id:");
			String id = lector.nextLine();

			if(controller.subscribeMagazine(id, option)){
				System.out.println("The suscription to the magazine was correct");
				System.out.println(controller.showInvoice());
			}else {
				System.out.println("Subscription to the magazine could not be completed.");
			}
		}
		
	}

	private void libraryReading() {
		String[] informationArray = new String[7];
		informationArray[1] = "";
		informationArray[2] = "";
		informationArray[4] = "0";
		informationArray[5] = "0"; //Contador Libro --> 20
		informationArray[6] = "0"; //Contador Magazine --> 5

		boolean flag = true;
		lector.nextLine();

		System.out.println("Type your identification");
		String id = lector.nextLine();

		int position = controller.findPositionOfUserWithId(id);

		if(position==-1){
			System.out.println("Invalid identification");

		} else {
			while(flag){

				informationArray = controller.getUserBiblioList(position, informationArray);

				//BIBLIOTECA
				if(informationArray[0].equalsIgnoreCase("Biblio")){
					if(informationArray[3].equals("")){
						System.out.println("\n-------------------------------------------");
						System.out.println("You do not have any bibliographic product");
						flag = false;
	
					} else if(informationArray[3].equalsIgnoreCase("OUT")){
	
						System.out.println("Thank you for using our library");
						flag = false;

					} else if(informationArray[3].equalsIgnoreCase("NOS")){
					
						System.out.println("\n-------------------------------------------");
						System.out.println("You cannot advance another page, because you have no more products");
						informationArray[1] = "";
						informationArray[2] = "";


					} else if(informationArray[3].equalsIgnoreCase("NOA")){
						
						System.out.println("\n-------------------------------------------");
						System.out.println("You cant go back another page, because there are no more pages");
						informationArray[1] = "";
						informationArray[2] = "";

					} else{
	
						System.out.println(informationArray[3]);
						informationArray[1] = lector.nextLine();
	
					}
				//READING SESSION
				} else {
					if(informationArray[3].equals("")){
						System.out.println("Error");
						informationArray[1]="";
						informationArray[0]="Biblio";
						informationArray[2]="";
					} else if(informationArray[3].equalsIgnoreCase("Back")) {
						informationArray[1]="";
						informationArray[0]="Biblio";
						informationArray[2]="";
					} else {
						System.out.println(informationArray[3]);
						informationArray[2] = lector.nextLine();
					}
				}
				


			}
			
		}
	
	}

	private void manageReports(){

		boolean flag = false;
		while(!flag) {
			System.out.println("\nType the action to be performed");
			System.out.println("1. Inform the total of pages per product type");
			System.out.println("2. Inform the genre of the book and the category of the magazine, that are most read");
			System.out.println("3. Inform top 5 of books and top 5 of magazines that are most read");
			System.out.println("4. Of every genre, inform the number of sell books and the $amount of the total sells");
			System.out.println("5. Of every category, inform the number of active suscriptions and the $amount of the total active suscription");
			System.out.println("0. Exit");
			int option = lector.nextInt();
	
			switch (option){
				case 1:
					informTotalPagesPerType();
				break;
				case 2:
					informMostReadGenreCategory();
				break;
				case 3:
					informTop5BooksMagazines();
				break;
				case 4:
					informSellBooksAndAmountByGenre();
				break;
				case 5:
					informMagazinesSuscriptionsAndAmountByCategory();
				break;
				case 0:
				flag = true;
				break;
				default:
				System.out.println("Invalid option");
				break;
			}
		
		}
	}
	
	private void informTotalPagesPerType() {

		System.out.println("\nThis inform shows the total pages read of the books and magazine\n");
		System.out.println(controller.totalPagesPerType());
		
	}
	private void informMostReadGenreCategory() {

		System.out.println("\nThis inform shows the category (Magazine) and genre (Book) that are the most read");
		System.out.println(controller.informMostReadGenreCategory());
	}
	private void informTop5BooksMagazines() {
		System.out.println("\nThis inform shows the top 5 of books and magazines");
		System.out.println(controller.informTop5BooksMagazines());
	}

	private void informSellBooksAndAmountByGenre() {
		System.out.println("\nThis inform shows the the number of books and amount that has been selled by genre");
		System.out.println(controller.informSellBooksAndAmountByGenre());
	}

	private void informMagazinesSuscriptionsAndAmountByCategory() {
		System.out.println("\nThis inform shows the the number of magazines and amount that has been selled by category");
		System.out.println(controller.informMagazinesSuscriptionsAndAmountByCategory());
	}
}



