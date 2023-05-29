package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Invoice {
    private Calendar transactionDate;
    private double amountPaid;
    private DateFormat formatter;

    /**
     * @param transactionDate Calendar
     * @param amountPaid double
     */
    public Invoice (Calendar transactionDate, double amountPaid){
        this.transactionDate = transactionDate;
        this.amountPaid = amountPaid;
        this.formatter = new SimpleDateFormat("dd/M/yy");
    }

    /**
     * @return String
     */
    public String toStringInvoice () {
        String msg = "";

        msg += "\n-----------------------------";
        msg += "\nAmount: "+getAmountPaid();
        try {
            msg += "\nDate of transaction: "+getInitialDateFormated();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        msg += "\n-----------------------------";
        return msg;
    }

    
    /**
     * @return double
     */
    public double getAmountPaid() {
        return amountPaid;
    }

    /**
     * @param amountPaid double
     */
    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    /**
     * @return Calendar
     */
    public Calendar getTransactionDate() {
        return transactionDate;
    }

    /**
     * @param publicationDate Calendar
     */
    public void setTransactionDate(Calendar publicationDate) {
		this.transactionDate = publicationDate;
	}

	/**
	 * @return String
	 * @throws ParseException
	 */
	public String getInitialDateFormated() throws ParseException{
		return formatter.format(this.transactionDate.getTime());
	}
}
