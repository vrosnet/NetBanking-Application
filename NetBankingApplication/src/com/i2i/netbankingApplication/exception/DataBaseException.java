package com.i2i.netbankingApplication.exception;


/**
 * <p>
 * custom exception to check the customer attribute.
 * If there is an error in the given data send message to class.
 * </p>
 * 
 * @author TEAM-2
 * 
 * @created 2016-09-06 
 */
public class DataBaseException extends Exception {
    private String message;
    public DataBaseException() {
    }

    public DataBaseException(String message) {
        super(message);
    }

    public DataBaseException(String message, Throwable cause) {
        super (message, cause);
    }

    public String toString() { 
        return this.message;
    }
}
