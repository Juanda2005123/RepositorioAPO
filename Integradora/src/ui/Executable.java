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

		Executable exe = new Executable();
		exe.menu();
	}

	
	/**
	 * Menu
	 */
	public void menu() {
		System.out.println("Welcome");
		boolean cond = true;
		while (cond) {
			System.out.println("1. Register Project \n2. Culminate Project Stage");
			System.out.println("3. Register Knowledge Unit \n4. Approve Knowledge Unit");
			System.out.println("5. Publish knowledge unit \n6. Inform number of Knowledge unit of every type");
			System.out.println("7. Inform knowledge unit learned lessons, searched by stage \n8. Inform most Knowledge units in a project");
			System.out.println("9. Inform if a collaborator has register knowledge units \n10. Search description and learned lessons by key word\n11. Exit");
			int opcionMenu = lector.nextInt();
			
			switch(opcionMenu){
				
				case 1:
				registerProject();
				break;
				case 2:
				culminateProjectStage();
				break;
				case 3:
				registerKnowledgeUnit();
				break;
				case 4:
				approveKnowledgeUnit();
				break;
				case 5: 
				publishKnowledgeUnit();
				break;
				case 6:
				informTypeKnowledgeUnit();
				break;
				case 7:
				informLearnedLessonsByStage();
				break;
				case 8:
				mostKnowledgeUnitInAProject();
				break;
				case 9:
				informCollaboratorKnowledgeUnit();
				break;
				case 10:
				searchDescriptionAndLearnedLessonsByKeyWord();
				break;
				case 11:
					cond = false;
				break;
			}
		}
	}

	
	/**
	 * registra proyectos
	 */
	public void registerProject() {
		System.out.println("Type the information needed to register a new project");
		lector.nextLine();

		System.out.println("Type project name");
		String projectName = lector.nextLine();
		
		System.out.println("Type the client name");
		String clientName = lector.nextLine();

		System.out.println("Type project start date \n--------------------");
		System.out.println("Type the start day date");
		int startDay = lector.nextInt();
		System.out.println("Type the start month date");
		int startMonth = lector.nextInt();
		System.out.println("Type the start year date");
		int startYear = lector.nextInt();
		System.out.println("--------------------");

		System.out.println("The project End date will be taken off with the planned completion date of the last stage");

		lector.nextLine();
		System.out.println("Type the budget");
		double budget = lector.nextDouble();

		System.out.println("Enter the number of managers to be registered");
		int managersNumber = lector.nextInt();
		String[][] managerInformation = new String[managersNumber][2];
		lector.nextLine();
	
			for (int i = 0; i < managersNumber; i++) {
				System.out.println("Type the "+ (i+1) +". manager name");
				String managerName = lector.nextLine();
				System.out.println("Type the "+(i+1)+". manager phone number");
				String managerPhone = lector.nextLine();
				for (int j = 0; j < 2; j++) {
					if (j==0) {
					managerInformation[i][j] = managerName;
					} else {
					managerInformation[i][j] = managerPhone;
					}
				}
			}	
		
		//TODA LA INFORMACION NECESARIA PARA CREAR UN PROYECTO MENOS LA FECHA DE FIN DEL PROYECTO
		//AHORA VA LA INFORMACION PARA LAS ETAPAS

		System.out.println("Enter the number of days the 1.Start-up phase is planned to last");
		int start_up_stage = lector.nextInt();
		System.out.println("Enter the number of days the 2.Analysis phase is planned to last");
		int analysis_stage = lector.nextInt();
		System.out.println("Enter the number of days the 3.Design phase is planned to last");
		int design_stage = lector.nextInt();
		System.out.println("Enter the number of days the 4.Execution phase is planned to last");
		int execution_stage = lector.nextInt();
		System.out.println("Enter the number of days the 5.Closing phase is planned to last");
		int closing_stage = lector.nextInt();
		System.out.println("Enter the number of days the 6.Follow_up phase is planned to last");
		int follow_up_stage = lector.nextInt();





		if(controller.registerProject(projectName, clientName, startDay, startMonth, startYear, budget, managerInformation, start_up_stage, analysis_stage, design_stage, execution_stage, closing_stage, follow_up_stage)) {
			System.out.println("Project registered correctly");
		} else {
			System.out.println("Could not register the project");
		}
	}


	/**
	 * culmina etapas
	 */
	public void culminateProjectStage(){

		lector.nextLine();
		System.out.println("Type the project name");
		String projectName = lector.nextLine();
		System.out.println("Type 1. if you want to enter the date manually. 2. If you want the actual date");
		int decision = lector.nextInt();
		int day = 1;
		int month = 1;
		int year = 1;
		if(decision==1) {
			System.out.println("Type the day on which the stage is being completed. \n--------------------");
			System.out.println("Type the day ");
			day = lector.nextInt();
			System.out.println("Type the month");
			month = lector.nextInt();
			System.out.println("Type the year");
			year = lector.nextInt();
			System.out.println("--------------------");
		}
		if(controller.culminateProjectStage(projectName, day, month, year, decision)) {
			System.out.println("The completion of the stage was performed correctly");
		} else {
			System.out.println("The completion of the stage could not be carried out");
		}
		
	}

	/**
	 * registra capsulas
	 */
	public void registerKnowledgeUnit(){

		lector.nextLine();
		System.out.println("Type the project name");
		String projectName = lector.nextLine();
		System.out.println("Enter the stage: \n<<START_UP>>, <<ANALYSIS>>, <<DESIGN>>, \n<<EXECUTION>>, <<CLOSING>>, <<FOLLOW_UP>>");
		String stagePhase = lector.nextLine();


		System.out.println("Type the unique identifier of the knowledge unit");
		String idKU = lector.nextLine();
		System.out.println("Type the description of the situation. Type a # at the start and at the final of the key words. Must type the key words at the start");
		System.out.println("Example: <<#Pruebas funcionales# Se encontro que en la etapa...>>");
		String descriptionKU = lector.nextLine();
		System.out.println("Type the knowledge unit type: \n<<TECHNICAL>>, <<EXPERIENCES>>, <<MANAGEMENT>>, <<DOMAIN>>");
		String type = lector.nextLine();
		System.out.println("Type the learned lessons. Type a # at the start and at the final of the key words. Must type the key words at the start");
		System.out.println("Example: <<#Pruebas funcionales# Se encontro que en la etapa...>>");
		String learnedLessonsKU = lector.nextLine();

		String[][] collaboratorInformation = new String[1][2];
	
		for (int i = 0; i < 1; i++) {
			System.out.println("Type the collaborator name");
			String collaboratorName = lector.nextLine();
			System.out.println("Type the collaborator position");
			String collaboratorPosition = lector.nextLine();
			for (int j = 0; j < 2; j++) {
				if (j==0) {
				collaboratorInformation[i][j] = collaboratorName;
				} else {
				collaboratorInformation[i][j] = collaboratorPosition;
				}
			}
		}
		
		if(controller.registerKnowledgeUnit(projectName, stagePhase, idKU, descriptionKU, type, collaboratorInformation, learnedLessonsKU)){	
			System.out.println("Knowledge unit registered correctly");
		} else {
			System.out.println("Could not register the knowledge unit");
		}
	}

	/**
	 * Aprueba capsulas
	 */
	public void approveKnowledgeUnit(){
		
		lector.nextLine();
		System.out.println("Type the project name");
		String projectName = lector.nextLine();
		System.out.println("Enter the knowledge unit unique identifier");
		String idKU = lector.nextLine();
		System.out.println("Type 1. if you want to enter the date manually. 2. If you want the actual date");
		int decision = lector.nextInt();
		int day = 1;
		int month = 1;
		int year = 1;
		if(decision==1) {
			System.out.println("Type the day on which the stage is being completed. \n--------------------");
			System.out.println("Type the day ");
			day = lector.nextInt();
			System.out.println("Type the month");
			month = lector.nextInt();
			System.out.println("Type the year");
			year = lector.nextInt();
			System.out.println("--------------------");
		}

		System.out.println("Type 1. approve Knowledge unit 2. Not approve knowledge unit");
		int approve = lector.nextInt();
		if(controller.approveKnowledgeUnit(projectName, day, month, year, decision, approve, idKU)) {
			System.out.println("Knowledge unit approved correctly");
		}else {
			System.out.println("Could not approve the Knowledge unit");
		}
	}
	
	/**
	 * Publica las capuslas de conocimiento
	 */
	public void publishKnowledgeUnit(){

		lector.nextLine();
		System.out.println("Type the project name");
		String projectName = lector.nextLine();
		System.out.println("Enter the knowledge unit unique identifier");
		String idKU = lector.nextLine();
		System.out.println("Type the URL");
		String urlKU = lector.nextLine();

		if(controller.publishKnowledgeUnit(projectName, idKU, urlKU)) {
			System.out.println("Knowledge unit published correctly");
		}else {
			System.out.println("Could not publish the Knowledge unit");
		}

	}

	/**
	 * Informa las capsulas por el tipo de ellas
	 */
	public void informTypeKnowledgeUnit() {
		
		System.out.println(controller.informTypeKnowledgeUnit());
	}

	/**
	 * Infora las learned lessons buscadas por etapas
	 */
	public void informLearnedLessonsByStage() {
		
		lector.nextLine();
		System.out.println("Enter the stage: \n<<START_UP>>, <<ANALYSIS>>, <<DESIGN>>, \n<<EXECUTION>>, <<CLOSING>>, <<FOLLOW_UP>>");
		String stagePhase = lector.nextLine();
		String inform = controller.informLearnedLessonsByStage(stagePhase);
		if(inform.equalsIgnoreCase("")) {
			System.out.println("There are not learned lessons in the knowledge units");
		} else {
			System.out.println("These are the learned lessons in the knowledge units:");
			System.out.println(inform);

		}
	}

	/**
	 * muestra cual es el proyecto con mas capsulas
	 */
	public void mostKnowledgeUnitInAProject() {

		String comprobation = controller.mostKnowledgeUnitInAProject();
		if (comprobation.equalsIgnoreCase("")){
			System.out.println("There are no knowledge units");
		} else {
			System.out.println(comprobation);
		}
		
	}

	/**
	 * informa si un colaborador ha registrado una capsula de conocimiento
	 */
	public void informCollaboratorKnowledgeUnit() {

		lector.nextLine();
		System.out.println("Type the collaborator name");
		String collaboratorName = lector.nextLine();

		String comprobation = controller.informCollaboratorKnowledgeUnit(collaboratorName);
		if (comprobation.equalsIgnoreCase("")){
			System.out.println("This collaborator has not register a knowledge unit");
		} else {
			System.out.println(comprobation);
		}
	}

	/**
	 * busca en las capsulas publicadas, en las descripciones y learned lessons por palabras clave
	 */
	public void searchDescriptionAndLearnedLessonsByKeyWord() {
		lector.nextLine();

		System.out.println("Type the key word to search in the description and learned lessons of the knowledge units");
		String keyWord = lector.nextLine(); 

		String comprobation = controller.searchDescriptionAndLearnedLessonsByKeyWord(keyWord);
		if (comprobation.equalsIgnoreCase("")){
			System.out.println("Could not found descriptions or learned lessons with that key word");
		} else {
			System.out.println(comprobation);
		}
	}
	
	
	
}