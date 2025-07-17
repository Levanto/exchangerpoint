package com.exchangerpoint.commonservices.errorcode;

import com.exchangerpoint.commonservices.common.ApplicationDataRepository;

public enum RestErrorCode {

	UNAUTHORIZED_USER(1000, ApplicationDataRepository.restErrorCodeProperties.getProperty("1000")),
	USER_ACCOUNT_NOT_ACTIVE(1001, ApplicationDataRepository.restErrorCodeProperties.getProperty("1001")),
	USER_ACCOUNT_BLOCKED(1002, ApplicationDataRepository.restErrorCodeProperties.getProperty("1002")),
	USER_ACCOUNT_SUSPENDED(1003, ApplicationDataRepository.restErrorCodeProperties.getProperty("1003")),
	USER_MAX_FAILED_ATTEMPT(1004, ApplicationDataRepository.restErrorCodeProperties.getProperty("1004")),
	LAST_LOGIN_IP_NOT_MATCH(1005, ApplicationDataRepository.restErrorCodeProperties.getProperty("1005")),
	USER_ACCOUNT_CLOSED(1006, ApplicationDataRepository.restErrorCodeProperties.getProperty("1006")),
	INVALID_TOKEN(1009, ApplicationDataRepository.restErrorCodeProperties.getProperty("1009")),
	BAD_CREDENTIAL(1010, ApplicationDataRepository.restErrorCodeProperties.getProperty("1010")),
	
	USER_DOES_NOT_EXIST(2002, ApplicationDataRepository.restErrorCodeProperties.getProperty("2002")),
	NO_USERS_EXIST(2029, ApplicationDataRepository.restErrorCodeProperties.getProperty("2029")),
	NO_USERNAME_SUPPLIED(2001, ApplicationDataRepository.restErrorCodeProperties.getProperty("2001")),
	USERNAME_ALREADY_TAKEN(2003, ApplicationDataRepository.restErrorCodeProperties.getProperty("2003")),
	INVALID_USEERNAME_UNSUPPORTED_CHAR(2004, ApplicationDataRepository.restErrorCodeProperties.getProperty("2004")),
	INVALID_USEERNAME_MAX_LENGTH_EXCEEDED(2005, ApplicationDataRepository.restErrorCodeProperties.getProperty("2005")),
	INVALID_USEERNAME_MIN_LENGTH_REQUIRED(2006, ApplicationDataRepository.restErrorCodeProperties.getProperty("2006")),
	NO_EMAIL_ID_SUPPLIED(2007, ApplicationDataRepository.restErrorCodeProperties.getProperty("2007")),
	EMAIL_ID_ALREADY_EXIST(2008, ApplicationDataRepository.restErrorCodeProperties.getProperty("2008")),
	INVALID_EMAIL_ID(2009, ApplicationDataRepository.restErrorCodeProperties.getProperty("2009")),
	NO_PASSWORD_SUPPLIED(2010, ApplicationDataRepository.restErrorCodeProperties.getProperty("2010")),
	INVALID_PASSWORD_UNSUPPORTED_CHAR(2011, ApplicationDataRepository.restErrorCodeProperties.getProperty("2011")),
	INVALID_PASSWORD_MAX_LENGTH_EXCEEDED(2012, ApplicationDataRepository.restErrorCodeProperties.getProperty("2012")),
	INVALID_PASSWORD_MIN_LENGTH_REQUIRED(2013, ApplicationDataRepository.restErrorCodeProperties.getProperty("2013")),
	INVALID_ACTIVATION_CODE(2033, ApplicationDataRepository.restErrorCodeProperties.getProperty("2033")),
	NO_NEW_PASSWORD_SUPPLIED(2034, ApplicationDataRepository.restErrorCodeProperties.getProperty("2034")),
	NO_OLD_PASSWORD_SUPPLIED(2016, ApplicationDataRepository.restErrorCodeProperties.getProperty("2016")),
	OLD_PASSWORD_NOT_MATCH(2017, ApplicationDataRepository.restErrorCodeProperties.getProperty("2017")),
	INVALID_NEW_PASSWORD_UNSUPPORTED_CHAR(2018, ApplicationDataRepository.restErrorCodeProperties.getProperty("2018")),
	INVALID_NEW_PASSWORD_MAX_LENGTH_EXCEEDED(2019, ApplicationDataRepository.restErrorCodeProperties.getProperty("2019")),
	INVALID_NEW_PASSWORD_MIN_LENGTH_REQUIRED(2020, ApplicationDataRepository.restErrorCodeProperties.getProperty("2020")),
	NEW_PASSWORD_MATCH_OLD_PASSWORD(2035, ApplicationDataRepository.restErrorCodeProperties.getProperty("2035")),
	PHONE_ALREADY_REGISTERED(2031, ApplicationDataRepository.restErrorCodeProperties.getProperty("2031")),
	NO_PHONE_NUMBER_SUPPLIED(2030, ApplicationDataRepository.restErrorCodeProperties.getProperty("2030")),
	INVALID_PHONE_NUMBER_SUPPLIED(2032, ApplicationDataRepository.restErrorCodeProperties.getProperty("2032")),
	INVALID_VERIFICATION_CODE(2036, ApplicationDataRepository.restErrorCodeProperties.getProperty("2036")),
	NO_PHONE_UPDATED(2021, ApplicationDataRepository.restErrorCodeProperties.getProperty("2021")),

	
	INVALID_NAME_UNSUPPORTED_CHAR(2014, ApplicationDataRepository.restErrorCodeProperties.getProperty("2014")),
	INVALID_NAME_MAX_LENGTH_EXCEEDED(2015, ApplicationDataRepository.restErrorCodeProperties.getProperty("2015")),
	INVALID_PIN_UNSUPPORTED_CHAR(2026, ApplicationDataRepository.restErrorCodeProperties.getProperty("2026")),
	INVALID_PIN_MAX_LENGTH_EXCEEDED(2027, ApplicationDataRepository.restErrorCodeProperties.getProperty("2027")),
	INVALID_PIN_MIN_LENGTH_REQUIRED(2028, ApplicationDataRepository.restErrorCodeProperties.getProperty("2028")),
	NEW_PIN_MATCHES_OLD_PIN(2037, ApplicationDataRepository.restErrorCodeProperties.getProperty("2037")),
	PIN_DOES_NOT_MATCH(2039, ApplicationDataRepository.restErrorCodeProperties.getProperty("2039")),
	
	WALLET_DOES_NOT_EXIST(2101, ApplicationDataRepository.restErrorCodeProperties.getProperty("2101")),
	NO_CURRENCY_SUPPLIED(2102, ApplicationDataRepository.restErrorCodeProperties.getProperty("2102")),
	NO_BANK_ACCOUNT_NUUMBER_SUPPLIED(2103, ApplicationDataRepository.restErrorCodeProperties.getProperty("2103")),
	NO_IFSC_CODE_SUPPLIED(2104, ApplicationDataRepository.restErrorCodeProperties.getProperty("2104")),
	IFSC_CODE_IS_WRONG(2105, ApplicationDataRepository.restErrorCodeProperties.getProperty("2105")),
	BANK_ACCOUNT_ALREADY_EXIST(2106, ApplicationDataRepository.restErrorCodeProperties.getProperty("2106")),
	BANK_DOES_NOT_EXIST(2107, ApplicationDataRepository.restErrorCodeProperties.getProperty("2107")),
	
	
	
	
	CONTENT_TYPE_NOT_SUPPORTED(1008, ApplicationDataRepository.restErrorCodeProperties.getProperty("1008")),
	METHOD_NOT_SUPPORTED(1007, ApplicationDataRepository.restErrorCodeProperties.getProperty("1007")),
	ACCESS_DENIED(4003, ApplicationDataRepository.restErrorCodeProperties.getProperty("4003")),
	BAD_REQUEST(4000, ApplicationDataRepository.restErrorCodeProperties.getProperty("4000")),
	INTERNAL_SERVER_ERROR(5000, ApplicationDataRepository.restErrorCodeProperties.getProperty("5000"));
	
	
	
	private int errorCode;
	private String errorMessage;

	RestErrorCode(int errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
