package com.exchangerpoint.exchangeservices.common;

public interface ErrorCodes {

	/**
	 * HTML Error Codes
	 */
	int USER_BLOCKED = 402;
	
	/**
	 * Error Codes
	 * 10001-10100 Database Error Codes
	 * 10101-10200 Validation Error Codes
	 */
	
	/*
	 * Database Error Codes
	 */
	int DATA_FETCH_ERROR = 10001;
	
	/*
	 * Validation Error Codes
	 */
	int INVALID_MOBILE_LENGTH = 10101;
	int INVALID_NUMBER = 10102;
}
