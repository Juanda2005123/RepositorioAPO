package model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Controller {

	private Project[] projects;

	public Controller() {

		projects = new Project[10];
		testCases();
	}
	
	public void testCases() {

		String[][] managerInformation = new String[3][2];
		managerInformation[0][0] = "Juan David";
		managerInformation[0][1] = "3225197962";
		managerInformation[1][0] = "Mama";
		managerInformation[1][1] = "3002288073";
		managerInformation[2][0] = "Papa";
		managerInformation[2][1] = "3155210617";

		Calendar calendarStart = new GregorianCalendar(2023,(3-1),22);
		Calendar start_up_stage_calendar = addDaysCalendar(calendarStart, 15);
		Calendar analysis_stage_calendar = addDaysCalendar(start_up_stage_calendar, 30);
		Calendar design_stage_calendar = addDaysCalendar(analysis_stage_calendar, 40);
		Calendar execution_stage_calendar = addDaysCalendar(design_stage_calendar, 100);
		Calendar closing_stage_calendar = addDaysCalendar(execution_stage_calendar, 20);
		Calendar follow_up_stage_calendar = addDaysCalendar(closing_stage_calendar, 100);
		projects[0] = new Project("PROYECTOX", "CLIENTE DANA", 289141, managerInformation, calendarStart, start_up_stage_calendar, analysis_stage_calendar, design_stage_calendar, execution_stage_calendar, closing_stage_calendar, follow_up_stage_calendar);

	}

	/**
	 * @param projectName
	 * @param clientName
	 * @param startDay
	 * @param startMonth
	 * @param startYear
	 * @param budget
	 * @param managerInformation
	 * @param start_up_stage
	 * @param analysis_stage
	 * @param design_stage
	 * @param execution_stage
	 * @param closing_stage
	 * @param follow_up_stage
	 * @return boolean
	 */
	public boolean registerProject(String projectName, String clientName, int startDay, int startMonth, int startYear, double budget, String[][] managerInformation, int start_up_stage, int analysis_stage, int design_stage, int execution_stage, int closing_stage, int follow_up_stage) {

		//Calendario inicio fecha PROYECTO y start_up_stage
		Calendar calendarStart = new GregorianCalendar(startYear,startMonth-1,startDay);
		//Calendario fin start_up_stage e inicio analysis_stage
		Calendar start_up_stage_calendar = addDaysCalendar(calendarStart, start_up_stage);
		//Calendario fin analysis_stage e inicio design_stage
		Calendar analysis_stage_calendar = addDaysCalendar(start_up_stage_calendar, analysis_stage);
		//Calendario fin design_stage e inicio execution_stage
		Calendar design_stage_calendar = addDaysCalendar(analysis_stage_calendar, design_stage);
		//Calendario fin execution_stage e inicio closing_stage
		Calendar execution_stage_calendar = addDaysCalendar(design_stage_calendar, execution_stage);
		//Calendario fin closing_stage e inicio follow_up_stage
		Calendar closing_stage_calendar = addDaysCalendar(execution_stage_calendar, closing_stage);
		//Calendario fin follow_up_stage y fin de PROYECTO
		Calendar follow_up_stage_calendar = addDaysCalendar(closing_stage_calendar, follow_up_stage);


		Project newProject = new Project(projectName, clientName, budget, managerInformation, calendarStart, start_up_stage_calendar, analysis_stage_calendar, design_stage_calendar, execution_stage_calendar, closing_stage_calendar, follow_up_stage_calendar);
		
		for(int i = 0; i<projects.length;i++){
			if(projects[i]==null){
				projects[i] = newProject;
				return true;
			}
		}
		return false;
	}

	/**
	 * @param projectName String
	 * @param day int
	 * @param month int
	 * @param year int
	 * @param decision int
	 * @return boolean
	 */
	public boolean culminateProjectStage(String projectName, int day, int month, int year, int decision) {

		Calendar calendarStage = Calendar.getInstance();
		if(decision==1) {
			calendarStage = new GregorianCalendar(year,month-1,day);
		}

		String realProjectName = "";
		for(int i = 0; i < projects.length; i++) {
			realProjectName = projects[i].getProjectName();
			if(realProjectName.equalsIgnoreCase(projectName)) {
				if(projects[i].culminateProjectStage(calendarStage)) {
					return true;
				}
			}
		}

		return false;
	}



	/**
	 * @param projectName String
	 * @param stagePhase String
	 * @param idKU String
	 * @param descriptionKU String
	 * @param type String
	 * @param collaboratorInformation String[][]
	 * @param learnedLessonsKU String
	 * @return boolean
	 */
	public boolean registerKnowledgeUnit(String projectName, String stagePhase, String idKU, String descriptionKU, String type, String[][] collaboratorInformation, String learnedLessonsKU) {

		TypeKU typeKU = TypeKU.TECHNICAL;
		if(type.equalsIgnoreCase("EXPERIENCES")){
			typeKU = TypeKU.EXPERIENCES;
		} else if(type.equalsIgnoreCase("MANAGEMENT")){
			typeKU = TypeKU.MANAGEMENT;
		} else if(type.equalsIgnoreCase("DOMAIN")){
			typeKU = TypeKU.DOMAIN;
		}


		for(int i = 0; i < projects.length; i++){
			if(projects[i]!=null){
				String realProjectName = projects[i].getProjectName();
				if(realProjectName.equalsIgnoreCase(projectName)) {
					if(projects[i].registerKnowledgeUnit(stagePhase, idKU, descriptionKU, typeKU, collaboratorInformation, learnedLessonsKU)) {
						return true;
					}
				}
			}
			
		}
		return false;
	}

	/**
	 * @param projectName String
	 * @param day int
	 * @param month int
	 * @param year int
	 * @param decision int
	 * @param approveInt int
	 * @param idKU String
	 * @return boolean
	 */
	public boolean approveKnowledgeUnit(String projectName, int day, int month, int year, int decision, int approveInt, String idKU) {

		Calendar calendarStage = Calendar.getInstance();
		if(decision==1) {
			calendarStage = new GregorianCalendar(year,month-1,day);
		}
		Status approve = Status.NOT_APPROVED;
		if(approveInt==1) {
			approve = Status.APPROVED;
		} 
		String realProjectName = "";
		for(int i = 0; i < projects.length; i++) {
			if(projects[i]!=null){
				realProjectName = projects[i].getProjectName();
				if(realProjectName.equalsIgnoreCase(projectName)) {
					if(projects[i].approveKnowledgeUnit(approve, idKU, calendarStage)) {
						return true;
					}
				}
			}
		}


		return false;
	}

	/**
	 * @param projectName String
	 * @param idKU String
	 * @param urlKU String
	 * @return boolean
	 */
	public boolean publishKnowledgeUnit(String projectName, String idKU, String urlKU) {
	
		String realProjectName = "";
		for(int i = 0; i < projects.length; i++) {
			if(projects[i]!=null){
				realProjectName = projects[i].getProjectName();
				if(realProjectName.equalsIgnoreCase(projectName)) {
					if(projects[i].publishKnowledgeUnit(idKU, urlKU)) {
						return true;
					}
				}
			}
		}


		return false;
	}

	/**
	 * @return boolean
	 */
	public String informTypeKnowledgeUnit() {

		String msg = "The number of knowledge units by type:";

		//0. Technical
		int technical = 0;
        //1. Experiences
		int experiences = 0;
        //2. Management
		int management = 0;
        //3. Domain
		int domain = 0;

		int informArray[];
        informArray = new int[4];

		for(int i = 0; i < projects.length; i++) {
			if(projects[i]!=null){
				informArray = projects[i].informTypeKnowledgeUnit();
				
				technical += informArray[0];
				experiences += informArray[1];
				management += informArray[2];
				domain += informArray[3];
			}
		}

		msg += ("\nTechnical: "+(technical)+"\nExperiences: "+(experiences));
		msg += ("\nManagement: "+(management)+"\nDomain: "+(domain));
		
		return msg;
	}

	/**
	 * @param stagePhaseCompare String
	 * @return boolean
	 */
	public String informLearnedLessonsByStage(String stagePhaseCompare) {

		Phase stagePhase = Phase.START_UP;
		if(stagePhaseCompare.equalsIgnoreCase("ANALYSIS")) {
			stagePhase = Phase.ANALYSIS;
		} else if(stagePhaseCompare.equalsIgnoreCase("DESIGN")) {
			stagePhase = Phase.DESIGN;
		} else if(stagePhaseCompare.equalsIgnoreCase("EXECUTION")) {
			stagePhase = Phase.EXECUTION;
		} else if(stagePhaseCompare.equalsIgnoreCase("CLOSING")) {
			stagePhase = Phase.CLOSING;
		} else if(stagePhaseCompare.equalsIgnoreCase("FOLLOW_UP")) {
			stagePhase = Phase.FOLLOW_UP;
		}

		String msg = "";
		for(int i = 0; i < projects.length; i++) {
			if(projects[i]!=null) {
				msg += projects[i].informLearnedLessonsByStage(stagePhase);
			}
		}



		return msg;
	}

	/**
	 * @return boolean
	 */
	public String mostKnowledgeUnitInAProject() {
		
		String msg = "";
		int compareArray[];
        compareArray = new int[11];
		for(int p = 0; p < compareArray.length; p++) {
			compareArray[p] = 0;
		}
		for(int i = 0; i < projects.length; i++) {
			if(projects[i]!=null) {
				compareArray[i] = projects[i].mostKnowledgeUnitInAProject();
			}
		}
		int j = 0;
		int contator = 1;
		for(int i = 0; i < 10; i++) {
			if((compareArray[i]>compareArray[i+1])&&(compareArray[i]>=contator)){
				j = i;
				contator = compareArray[i];
				msg = ("The project name is: "+projects[j].getProjectName());
			}
		}
		int verification = 0;
		for (int i = 0; i < 10; i++) {
			verification += compareArray[i];
		}
		if(verification==0){
			msg = "";
		}
		
		return msg;
	}

	/**
	 * @param collaboratorName String
	 * @return boolean
	 */
	public String informCollaboratorKnowledgeUnit(String collaboratorName) {

		String msg = "";
		boolean flag = false;

		for (int i = 0; i < projects.length; i++) {
			if(projects[i]!=null) {
				flag = projects[i].informCollaboratorKnowledgeUnit(collaboratorName);
				if(flag==true){
					msg += ("\nA knowledge unit has been registred in: "+projects[i].getProjectName());
				}
			}

		}

		return msg;
	}
	/**
	 * @param keyWord String
	 * @return boolean
	 */
	public String searchDescriptionAndLearnedLessonsByKeyWord(String keyWord) {

		String msg = "";

		for (int i = 0; i < projects.length; i++) {
			if(projects[i]!=null) {
				msg += projects[i].searchDescriptionAndLearnedLessonsByKeyWord(keyWord);
			}
		}

		return msg;
	}
		
	

	/**
	 * @param calendar Calendar
	 * @param num int
	 * @return Calendar
	 */
	public Calendar addDaysCalendar(Calendar calendar, int num) {
		calendar.add(Calendar.DAY_OF_YEAR, num);
		return calendar;
	}
}
