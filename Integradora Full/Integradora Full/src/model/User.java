package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.text.DateFormat;


public abstract class User implements UserMethods{

	private String id;
	private String name;
	private String nickname;
	private Calendar signUpDate;
	private DateFormat formatter;

	private ArrayList <Invoice> invoices;

       

	/**
	 * @param id String
	 * @param name String
	 * @param nickname String
	 * @param signUpDate Calendar
	 */
	public User(String id, String name, String nickname, Calendar signUpDate) {
		this.id = id;
		this.name = name;
		this.nickname = nickname;
		this.signUpDate = signUpDate;
		this.formatter = new SimpleDateFormat("dd/M/yy");

		invoices = new ArrayList<Invoice>();

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
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id String
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return String
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname String
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return Calendar
	 */
	public Calendar getSignUpDate() {
		return signUpDate;
	}

	/**
	 * @param signUpDate Calendar
	 */
	public void setSignUpDate(Calendar signUpDate) {
		this.signUpDate = signUpDate;
	}

	/**
	 * @return String
	 * @throws ParseException
	 */
	public String getInitialDateFormated() throws ParseException{
		return formatter.format(this.signUpDate.getTime());
	}

}
