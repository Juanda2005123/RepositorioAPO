package model;

import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Stage {

    private Phase stagePhase;
    private State state;
	private Calendar calendarInitial;
    private Calendar calendarFinal;
    private DateFormat formatter;
    private KnowledgeUnit[] units;
    private Calendar realCalendarInitial;
    private Calendar realCalendarFinal;

    /**
     * @param stagePhase Phase
     * @param state State
     * @param calendarInitial Calendar
     * @param calendarFinal Calendar
     */
    public Stage(Phase stagePhase, State state, Calendar calendarInitial, Calendar calendarFinal){

        this.formatter = new SimpleDateFormat("dd/M/yy");
        this.stagePhase = stagePhase;
        this.state = state;
        this.calendarInitial = calendarInitial; 
        this.calendarFinal = calendarFinal;
        this.realCalendarInitial = null; 
        this.realCalendarFinal = null;
        units = new KnowledgeUnit[50];

        if(stagePhase==Phase.START_UP) {
            this.realCalendarInitial = calendarInitial;
        }

    }

    /**
     * @param idKU String
     * @param descriptionKU String
     * @param typeKU TypeKU
     * @param collaboratorInformation String[][]
     * @param learnedLessonsKU String
     * @return boolean
     */
    public boolean registerKnowledgeUnit(String idKU, String descriptionKU, TypeKU typeKU, String[][] collaboratorInformation, String learnedLessonsKU) {

        KnowledgeUnit newKnowledgeUnit = new KnowledgeUnit(idKU, descriptionKU, typeKU, collaboratorInformation, learnedLessonsKU);
		
        for(int i = 0; i < units.length; i++){
            if(units[i]==null){
                units[i] = newKnowledgeUnit;
                return true;
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

        for(int i = 0; i < units.length; i++) {
            if(units[i]!=null){
               String realIdKU = units[i].getIdKU();
			    if(realIdKU.equalsIgnoreCase(idKU)) {
			    	units[i].setStatusKU(approve);
                   units[i].setApproveDate(calendarStage);
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

      

        for(int i = 0; i < units.length; i++) {
            if(units[i]!=null){
                String realIdKU = units[i].getIdKU();
                Status approveVerification = units[i].getStatusKU();
			    if((realIdKU.equalsIgnoreCase(idKU))&&(approveVerification==Status.APPROVED)) {
			    	units[i].setUrlKU(urlKU);
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

        TypeKU typeKU = null;
        int informArray[];
        //0. Technical
        //1. Experiences
        //2. Management
        //3. Domain
        informArray = new int[4]; 

		for(int i = 0; i < units.length; i++) {
            if(units[i]!=null){
                typeKU = units[i].getTypeKU();
                if(typeKU==TypeKU.TECHNICAL) {
                    informArray[0] += 1;
                } else if(typeKU==TypeKU.EXPERIENCES) {
                    informArray[1] += 1;
                } else if(typeKU==TypeKU.MANAGEMENT) {
                    informArray[2] += 1;
                } else if(typeKU==TypeKU.DOMAIN) {
                    informArray[3] += 1;
                }
            }
		}
        
        
		return informArray;
	}

    /**
     * @return String
     */
    public String informLearnedLessonsByStage() {
      
        String msg = "";
        String learnedLessons = "";

        for (int i = 0; i < units.length; i++) {
            if(units[i]!=null) {
                learnedLessons = units[i].getLearnedLessonsKU();
                msg += ("\n"+learnedLessons);
                
            }
        }

        return msg;
    }

    /**
     * @return int
     */
    public int mostKnowledgeUnitInAProject() {
        int number = 0;

        for (int i = 0; i < units.length; i++) {
            if(units[i]!=null){
                number++;
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
        String realCollaboratorName = "";
        for(int i = 0; i < units.length; i++) {
            if(units[i]!=null) {
				realCollaboratorName = units[i].getCollaboratorName();
                if(collaboratorName.equalsIgnoreCase(realCollaboratorName)){
                    flag = true;            
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

        for (int i = 0; i < units.length; i++) {
            if(units[i]!=null){
                String url = units[i].getUrlKU();
                if(!url.equals("")) {
                    boolean description = units[i].generateHashtagDescription(keyWord);
                    boolean learnedLessons = units[i].generateHashtagLearnedLessons(keyWord);
                    if(description) {
                        msg += "\nDescription: "+units[i].getDescriptionKU();
                    }
                    if(learnedLessons) {
                        msg += "\nLearned lessons: "+units[i].getLearnedLessonsKU();
                    }
                }   
            }
        }

        return msg;
    }
    
    public Phase getStagePhase() {
        return stagePhase;
    }

    public String getStagePhaseString() {
        String msg = "";
        msg += stagePhase;
        return msg;
    }

    public void setStagePhase(Phase stagePhase) {
        this.stagePhase = stagePhase;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Calendar getCalendarInitial(){
		return calendarInitial;
	}

	public String getInitialDateFormated() throws ParseException{
		return formatter.format(this.calendarInitial.getTime());
	}	

    public Calendar getCalendarFinal(){
		return calendarFinal;
	}

	public String getFinalDateFormated() throws ParseException{
		return formatter.format(this.calendarFinal.getTime());
	}	

    public Calendar getRealCalendarInitial(){
		return realCalendarInitial;
	}

	public String getRealInitialDateFormated() throws ParseException{
		return formatter.format(this.realCalendarInitial.getTime());
	}	

    public Calendar getRealCalendarFinal(){
		return realCalendarFinal;
	}

	public String getRealFinalDateFormated() throws ParseException{
		return formatter.format(this.realCalendarFinal.getTime());
	}

    public void setRealCalendarInitial(Calendar realCalendarInitial) {
        this.realCalendarInitial = realCalendarInitial;
    }

    public void setRealCalendarFinal(Calendar realCalendarFinal) {
        this.realCalendarFinal = realCalendarFinal;
    }	


    

}
