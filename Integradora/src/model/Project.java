package model;

import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;


public class Project{
	
	private String projectName;
	private String clientName;
	private Calendar calendarStart;
	private Calendar calendarEnd;
	private double budget;
	private Stage[] stages;
	private DateFormat formatter;
	private String[][] managerInformation;

	/**
	 * @param projectName String
	 * @param clientName String
	 * @param budget double 
	 * @param managerInformation String[][]
	 * @param calendarStart Calendar
	 * @param start_up_stage_calendar Calendar
	 * @param analysis_stage_calendar Calendar
	 * @param design_stage_calendar Calendar
	 * @param execution_stage_calendar Calendar
	 * @param closing_stage_calendar Calendar
	 * @param follow_up_stage_calendar Calendar
	 */
	public Project(String projectName, String clientName, double budget, String[][] managerInformation, Calendar calendarStart, Calendar start_up_stage_calendar, Calendar analysis_stage_calendar, Calendar design_stage_calendar, Calendar execution_stage_calendar, Calendar closing_stage_calendar, Calendar follow_up_stage_calendar){
		
		this.formatter = new SimpleDateFormat("dd/M/yy");

		this.projectName = projectName;	
		this.clientName = clientName;
		this.budget = budget; 
		this.managerInformation = managerInformation;
		this.calendarStart = calendarStart;
		this.calendarEnd = follow_up_stage_calendar;
		stages = new Stage[6];

		Stage Newstage0 = new Stage(Phase.START_UP, State.ACTIVE, calendarStart ,start_up_stage_calendar);
		Stage Newstage1 = new Stage(Phase.ANALYSIS, State.INACTIVE, start_up_stage_calendar ,analysis_stage_calendar);
		Stage Newstage2 = new Stage(Phase.DESIGN, State.INACTIVE, analysis_stage_calendar ,design_stage_calendar);
		Stage Newstage3 = new Stage(Phase.EXECUTION, State.INACTIVE, design_stage_calendar ,execution_stage_calendar);
		Stage Newstage4 = new Stage(Phase.CLOSING, State.INACTIVE, execution_stage_calendar ,closing_stage_calendar);
		Stage Newstage5 = new Stage(Phase.FOLLOW_UP, State.INACTIVE, closing_stage_calendar ,follow_up_stage_calendar);

		stages[0]=Newstage0;
		stages[1]=Newstage1;
		stages[2]=Newstage2;
		stages[3]=Newstage3;
		stages[4]=Newstage4;
		stages[5]=Newstage5;
		
	}

	/**
	 * @param calendarStage Calendar
	 * @return boolean
	 */
	public boolean culminateProjectStage(Calendar calendarStage) {

		State state = null;
		for(int i = 0; i < stages.length; i++) {
			state = stages[i].getState();
			if(state==State.ACTIVE) {
				stages[i].setState(State.INACTIVE);
				stages[i+1].setState(State.ACTIVE);
				stages[i].setRealCalendarFinal(calendarStage);
				stages[i+1].setRealCalendarInitial(calendarStage);
				return true;
			}
		}

		return false;
	}

	/**
	 * @param stagePhase String
	 * @param idKU String
	 * @param descriptionKU String
	 * @param typeKU TypeKU
	 * @param collaboratorInformation String[][]
	 * @param learnedLessonsKU String
	 * @return boolean
	 */
	public boolean registerKnowledgeUnit(String stagePhase, String idKU, String descriptionKU, TypeKU typeKU, String[][] collaboratorInformation, String learnedLessonsKU) {

		for(int i = 0; i < stages.length; i++) {
			if(stages[i]!=null){
				String realStagePhase = stages[i].getStagePhaseString();
				if(realStagePhase.equalsIgnoreCase(stagePhase)) {
					if(stages[i].registerKnowledgeUnit(idKU, descriptionKU, typeKU, collaboratorInformation, learnedLessonsKU)){
						return true;
					}
				}
			}
		}


		return false;
	}

	/**
	 * @param approve Status
	 * @param idKU String
	 * @param calendarStage Calendar
	 * @return boolean
	 */
	public boolean approveKnowledgeUnit(Status approve, String idKU, Calendar calendarStage) {

		for(int i = 0; i < stages.length; i++) {
			if(stages[i]!=null){
				if(stages[i].approveKnowledgeUnit(approve, idKU, calendarStage)){
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * @param idKU String
	 * @param urlKU String
	 * @return boolean
	 */ 
	public boolean publishKnowledgeUnit(String idKU, String urlKU) {

		for(int i = 0; i < stages.length; i++) {
			if(stages[i]!=null){
				if(stages[i].publishKnowledgeUnit(idKU, urlKU)){
					return true;
				}
			}
		}


		return false;
	}

	/**
	 * @return int[]
	 */
	public int[] informTypeKnowledgeUnit() {
		

		int informArray[];
        informArray = new int[4];

		int realInformArray[];
        realInformArray = new int[4];


		for(int i = 0; i < stages.length; i++) {
			if(stages[i]!=null) {
				informArray = stages[i].informTypeKnowledgeUnit();
				realInformArray[0] += informArray[0]; 
				realInformArray[1] += informArray[1]; 
				realInformArray[2] += informArray[2]; 
				realInformArray[3] += informArray[3]; 
			}
		}


		return realInformArray;
	}

	/**
	 * @param stagePhase Phase
	 * @return String
	 */
	public String informLearnedLessonsByStage(Phase stagePhase) {

		String msg = "";

		for(int i = 0; i < stages.length; i++) {
			if(stages[i]!=null) {
				Phase realStagePhase = stages[i].getStagePhase();
				if(realStagePhase==stagePhase) {
					msg += stages[i].informLearnedLessonsByStage();
				}
			}
		}

		return msg;
	}

	/**
	 * @return int
	 */
	public int mostKnowledgeUnitInAProject() {
		int number = 0;

		for (int i = 0; i < stages.length; i++) {
			if(stages[i]!=null) {
				number += stages[i].mostKnowledgeUnitInAProject();
			}
		}

		return number;
	}

	/**
	 * @param collaboratorName String
	 * @return boolean
	 */
	public boolean informCollaboratorKnowledgeUnit(String collaboratorName) {

		boolean flag = false;
		for (int i = 0; i < stages.length; i++) {
			if(stages[i]!=null) {
				flag = stages[i].informCollaboratorKnowledgeUnit(collaboratorName);
				if(flag==true){
					return flag;
				}
			}
		}

		return flag;

	}

	/**
	 * @param keyWord String
	 * @return String
	 */
	public String searchDescriptionAndLearnedLessonsByKeyWord(String keyWord) {

		String msg = "";

		for (int i = 0; i < stages.length; i++) {
			if(stages[i]!=null) {
				msg += stages[i].searchDescriptionAndLearnedLessonsByKeyWord(keyWord);
			}
		}

		return msg;
	}
	
	public Stage[] getStages() {
		return stages;
	}

	public void setStages(Stage[] stages) {
		this.stages = stages;
	}



	public String getProjectName(){
		return projectName;
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}



	public String getClientName(){
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}



	public double getBudget(){
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}



	public String getManagerInformation(){
		String msg = "";
		
		for (int i = 0; i < managerInformation.length; i++) {
			for (int j = 0; j < 2; j++) {
				if (j==0) {
				msg += ("\nName: "+managerInformation[i][j]);
				} else {
				msg += ("\nPhone Number: "+managerInformation[i][j]);
				}
			}

		msg+="\n-------------------------------";

		}

	return msg;
	}






	public Calendar getCalendarStart(){
		return calendarStart;
	}
	
	public String getInitialDateFormated() throws ParseException{
		return formatter.format(this.calendarStart.getTime());
	}

	public Calendar getCalendarEnd(){
		return calendarEnd;
	}

	public String getFinalDateFormated() throws ParseException{
		return formatter.format(this.calendarEnd.getTime());
	}		

	public Calendar addDaysCalendar (Calendar calendaron, int days) {
		calendaron.add(Calendar.DAY_OF_YEAR, days);
		return calendaron;
	}

	public String getProjectInfo() throws ParseException{
		String msg = "";

		msg = "\nName: " + projectName + "\nClient: " + clientName + "\nInitial Date: " + getInitialDateFormated() + 
		"\nFinal Date: " + getFinalDateFormated() + "\nTotalBudget: " + budget + ".\n";
		
		return msg;
	}
}


