package com.exchangerpoint.exchangeservices.exception;

public class UserBlockedException extends ExchangeException {

	private static final long serialVersionUID = -1006054286114860887L;

	/**
     * Constructor
     * @param errorMessage the error message
     */
    public UserBlockedException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Constructor
     * @param errorMessage the error message
     * @param errorCode the error code
     */
    public UserBlockedException(String errorMessage, int errorCode) {
        super(errorMessage, errorCode);
    }

    /**
     * Constructor
     * @param fieldName the field name
     * @param errorMessage the error message
     * @param errorCode the error code
     */
    public UserBlockedException(String fieldName, String errorMessage, int errorCode) {
        super(fieldName, errorMessage, errorCode);
    }

    /**
     * @return the fieldName
     */
    public String getFieldName() {
        return super.getFieldName();
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return super.getErrorMessage();
    }
    
    /**
     * @return the errorCode
     */
	public int getErrorCode() {
		return super.getErrorCode();
	}
}
