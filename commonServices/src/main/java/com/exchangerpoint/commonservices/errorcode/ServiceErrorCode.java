package com.exchangerpoint.commonservices.errorcode;

import com.exchangerpoint.commonservices.common.ApplicationDataRepository;

public enum ServiceErrorCode {

	ACCESS_DENIED(400300, ApplicationDataRepository.serviceErrorCodeProperties.getProperty("400300")),
	INTERNAL_SERVER_ERROR(400500, ApplicationDataRepository.serviceErrorCodeProperties.getProperty("400500")),
	OLD_PASSWORD_NOT_MATCH(201700, ApplicationDataRepository.serviceErrorCodeProperties.getProperty("201700")),
	USER_DOES_NOT_EXIST(200200, ApplicationDataRepository.serviceErrorCodeProperties.getProperty("200200")),
	INVALID_ACTIVATION_CODE(203300, ApplicationDataRepository.serviceErrorCodeProperties.getProperty("203300")),
	USERNAME_ALREADY_TAKEN(200300, ApplicationDataRepository.serviceErrorCodeProperties.getProperty("200300")),
	INVALID_VERIFICATION_CODE(203600, ApplicationDataRepository.serviceErrorCodeProperties.getProperty("203600")),
	NO_PHONE_UPDATED(202100, ApplicationDataRepository.serviceErrorCodeProperties.getProperty("202100")),
	NEW_PIN_MATCHES_OLD_PIN(203700, ApplicationDataRepository.serviceErrorCodeProperties.getProperty("203700")),
	PIN_DOES_NOT_MATCH(203900, ApplicationDataRepository.serviceErrorCodeProperties.getProperty("203900")),

	PHONE_ALREADY_REGISTERED(203100, ApplicationDataRepository.serviceErrorCodeProperties.getProperty("203100")),
	
	EMAIL_ID_ALREADY_EXIST(200800, ApplicationDataRepository.serviceErrorCodeProperties.getProperty("200800")),
	
	WALLET_DOES_NOT_EXIST(210100, ApplicationDataRepository.serviceErrorCodeProperties.getProperty("210100")),
	BANK_ACCOUNT_DOES_NOT_EXIST(210600, ApplicationDataRepository.serviceErrorCodeProperties.getProperty("210600")),
	BANK_ACCOUNT_ALREADY_EXIST(210700, ApplicationDataRepository.serviceErrorCodeProperties.getProperty("210700"));
	
	
	private int errorCode;
	private String errorMessage;

	ServiceErrorCode(int errorCode, String errorMessage) {
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
