package com.exchangerpoint.exchangeservices.service;

import com.exchangerpoint.exchangeservices.exception.ExchangeException;

public interface ValidationService {

	boolean validateEmail(String email);
	
	boolean validatePhone(String mobile) throws ExchangeException;
	
	boolean validateAccessControl(String email, String mobile, String ipAddress) throws ExchangeException;
}
