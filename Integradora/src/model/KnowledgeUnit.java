package model;

import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * The class that have the information of the knowledge units and interacts with Controller.
 */
public class KnowledgeUnit {
	
	private DateFormat formatter;
	private String idKU;
	private String descriptionKU;
	private TypeKU typeKU;
	private String[][] collaboratorInformation;
	private String learnedLessonsKU;
	private Status statusKU;
	private Calendar approveDate;
	private String urlKU;

	
	/**
	 * @param idKU String
	 * @param descriptionKU String
	 * @param typeKU TypeKU
	 * @param collaboratorInformation String[][]
	 * @param learnedLessonsKU String
	 */
	public KnowledgeUnit(String idKU, String descriptionKU, TypeKU typeKU, String[][] collaboratorInformation, String learnedLessonsKU) {

		this.formatter = new SimpleDateFormat("dd/M/yy");
		this.idKU = idKU;
		this.descriptionKU = descriptionKU;
		this.typeKU = typeKU;
		this.collaboratorInformation = collaboratorInformation;
		this.learnedLessonsKU = learnedLessonsKU;
		this.statusKU = Status.TO_BE_DEFINED;
		this.approveDate = null;
		this.urlKU = "";
	}

	/**
	 * @param keyWord String
	 * @return boolean
	 */
	public boolean generateHashtagLearnedLessons(String keyWord){

        String[] learnedLessonsH = learnedLessonsKU.split("#");
		String learnedLessons = learnedLessonsH[1];
		learnedLessons = learnedLessons.toUpperCase();
		keyWord = keyWord.toUpperCase();

		if(learnedLessons.contains(keyWord)) {
			return true;
		}


		return false;
	}

	/**
	 * @param keyWord String
	 * @return boolean
	 */
	public boolean generateHashtagDescription(String keyWord){

		String[] descriptionH = descriptionKU.split("#");
		String description = descriptionH[1];
		description = description.toUpperCase();
		keyWord = keyWord.toUpperCase();

		if(description.contains(keyWord)) {
			return true;
		}



		return false;
	}

	public String[][] getCollaboratorInformation() {
		return collaboratorInformation;
	}

	public String getCollaboratorName() {

		String msg = collaboratorInformation[0][0];

		return msg;
	}


	public String getCollaboratorInformationString (){
		String msg = "";
		
		for (int i = 0; i < collaboratorInformation.length; i++) {
			for (int j = 0; j < 2; j++) {
				if (j==0) {
				msg += ("\nName: "+collaboratorInformation[i][j]);
				} else {
				msg += ("\nPosition: "+collaboratorInformation[i][j]);
				}
			}

		msg+="\n-------------------------------";

		}

	return msg;
	}

	public void setUrlKU(String urlKU) {
		this.urlKU = urlKU;
	}

	public String getUrlKU() {
		return urlKU;
	}

	public void setApproveDate(Calendar approveDate) {
		this.approveDate = approveDate;
	}

	/**
	 * The method that gets the information of id.
	 * @return id String
	 */
	public String getIdKU() {
		return idKU;
	}
	/**
	 * The method that sets the information of id.
	 * @param id String
	 */
	public void setIdKU(String idKU) {
		this.idKU = idKU;
	}
	/**
	 * The method that gets the information of description.
	 * @return description String
	 */
	public String getDescriptionKU() {
		return descriptionKU;
	}
	/**
	 * The method that sets the information of description.
	 * @param description String
	 */
	public void setDescriptionKU(String descriptionKU) {
		this.descriptionKU = descriptionKU;
	}
	/**
	 * The method that gets the information of type.
	 * @return type String
	 */
	public TypeKU getTypeKU() {
		return typeKU;
	}
	/**
	 * The method that sets the information of type.
	 * @param type String
	 */
	public void setTypeKU(TypeKU typeKU) {
		this.typeKU = typeKU;
	}
	/**
	 * The method that gets the information of learned lessons.
	 * @return learnedLessons String
	 */
	public String getLearnedLessonsKU() {
		return learnedLessonsKU;
	}
	/**
	 * The method that sets the information of learned lessons.
	 * @param learnedLessons String
	 */
	public void setLearnedLessonsKU(String learnedLessonsKU) {
		this.learnedLessonsKU = learnedLessonsKU;
	}
	/**
	 * The method that gets the information of status.
	 * @return status Strings
	 */
	public Status getStatusKU() {
		return statusKU;
	}
	/**
	 * The method that sets the information of status.
	 * @param status String
	 */
	public void setStatusKU(Status statusKU) {
		this.statusKU = statusKU;
	}
	
	public Calendar getApproveDate(){
		return approveDate;
	}

	public String getApproveDateFormated() throws ParseException{
		return formatter.format(this.approveDate.getTime());
	}
	
	
}

