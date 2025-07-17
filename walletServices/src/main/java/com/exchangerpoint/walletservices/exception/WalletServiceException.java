package com.exchangerpoint.walletservices.exception;

import com.exchangerpoint.commonservices.exception.ServiceException;

public class WalletServiceException extends ServiceException{
	private static final long serialVersionUID = -5686144013297177431L;

    
    private String errorMessage;
    private int errorCode;

    /**
     * Constructor
     * @param errorMessage the error message
     */
    public WalletServiceException(String errorMessage) {
    	super(errorMessage);
        this.errorMessage = errorMessage;
    }
    
    /**
     * Constructor
     * @param errorMessage the error message
     * @param errorCode the error code
     */
    public WalletServiceException(String errorMessage, int errorCode) {
    	super(errorMessage,errorCode);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @return the errorCode
     */
	public int getErrorCode() {
		return errorCode;
	}
}
