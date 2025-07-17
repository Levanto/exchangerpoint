package com.exchangerpoint.restservices.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.routines.EmailValidator;

import com.exchangerpoint.commonservices.errorcode.RestErrorCode;
import com.exchangerpoint.commonservices.exception.RestException;
import com.exchangerpoint.commonservices.validator.CommonValidator;
import com.exchangerpoint.restspec.dto.user.AddUserRequest;
import com.exchangerpoint.restspec.dto.user.ResetPasswordRequest;
import com.exchangerpoint.restspec.dto.user.UpdateEmailRequest;
import com.exchangerpoint.restspec.dto.user.UpdatePasswordRequest;
import com.exchangerpoint.restspec.dto.user.UpdatePhoneRequest;
import com.exchangerpoint.restspec.dto.user.UpdatePinRequest;
import com.exchangerpoint.restspec.dto.user.UpdateUserRequest;

public class UserValidator {
	protected final static Log logger = LogFactory.getLog(UserValidator.class);

public static void addUserRequestValidator(AddUserRequest addUserRequest) throws RestException {
		
		final String userName = addUserRequest.getUserName();
		final String password = addUserRequest.getPassword();
		final String email = addUserRequest.getEmail();
		
		if(CommonValidator.isBlank(userName)) {
			logger.error("Error Code : " + RestErrorCode.NO_USERNAME_SUPPLIED.getErrorCode() + " Error Message : " + RestErrorCode.NO_USERNAME_SUPPLIED.getErrorMessage() +" UserName : " + userName);
			throw new RestException(RestErrorCode.NO_USERNAME_SUPPLIED.getErrorMessage(),RestErrorCode.NO_USERNAME_SUPPLIED.getErrorCode());
		}
		
		if(userName.length() > 25) {
			logger.error("Error Code : " + RestErrorCode.INVALID_USEERNAME_MAX_LENGTH_EXCEEDED.getErrorCode() + " Error Message : " + RestErrorCode.INVALID_USEERNAME_MAX_LENGTH_EXCEEDED.getErrorMessage() +" UserName : " + userName);
			throw new RestException(RestErrorCode.INVALID_USEERNAME_MAX_LENGTH_EXCEEDED.getErrorMessage(),RestErrorCode.INVALID_USEERNAME_MAX_LENGTH_EXCEEDED.getErrorCode());
		    
		}
		
		if(userName.length() < 3) {
			logger.error("Error Code : " + RestErrorCode.INVALID_USEERNAME_MIN_LENGTH_REQUIRED.getErrorCode() + " Error Message : " + RestErrorCode.INVALID_USEERNAME_MIN_LENGTH_REQUIRED.getErrorMessage() +" UserName : " + userName);
			throw new RestException(RestErrorCode.INVALID_USEERNAME_MIN_LENGTH_REQUIRED.getErrorMessage(),RestErrorCode.INVALID_USEERNAME_MIN_LENGTH_REQUIRED.getErrorCode());
		    
		}
		
		if(!CommonValidator.letterAndNumberValidator(userName)) {
			logger.error("Error Code : " + RestErrorCode.INVALID_USEERNAME_UNSUPPORTED_CHAR.getErrorCode() + " Error Message : " + RestErrorCode.INVALID_USEERNAME_UNSUPPORTED_CHAR.getErrorMessage() +" UserName : " + userName);
			throw new RestException(RestErrorCode.INVALID_USEERNAME_UNSUPPORTED_CHAR.getErrorMessage(),RestErrorCode.INVALID_USEERNAME_UNSUPPORTED_CHAR.getErrorCode());
		    
		}
		
		if(CommonValidator.isBlank(password)) {
			logger.error("Error Code : " + RestErrorCode.NO_PASSWORD_SUPPLIED.getErrorCode() + " Error Message : " + RestErrorCode.NO_PASSWORD_SUPPLIED.getErrorMessage() +" UserName : " + userName);
			throw new RestException(RestErrorCode.NO_PASSWORD_SUPPLIED.getErrorMessage(),RestErrorCode.NO_PASSWORD_SUPPLIED.getErrorCode());
		}
		
		
		
		if(password.length() > 25) {
			logger.error("Error Code : " + RestErrorCode.INVALID_PASSWORD_MAX_LENGTH_EXCEEDED.getErrorCode() + " Error Message : " + RestErrorCode.INVALID_PASSWORD_MAX_LENGTH_EXCEEDED.getErrorMessage() +" UserName : " + userName);
			throw new RestException(RestErrorCode.INVALID_PASSWORD_MAX_LENGTH_EXCEEDED.getErrorMessage(),RestErrorCode.INVALID_PASSWORD_MAX_LENGTH_EXCEEDED.getErrorCode());
		    
		}
		
		if(password.length() < 8) {
			logger.error("Error Code : " + RestErrorCode.INVALID_PASSWORD_MIN_LENGTH_REQUIRED.getErrorCode() + " Error Message : " + RestErrorCode.INVALID_PASSWORD_MIN_LENGTH_REQUIRED.getErrorMessage() +" UserName : " + userName);
			throw new RestException(RestErrorCode.INVALID_PASSWORD_MIN_LENGTH_REQUIRED.getErrorMessage(),RestErrorCode.INVALID_PASSWORD_MIN_LENGTH_REQUIRED.getErrorCode());
		    
		}
		
		if(!CommonValidator.specialCharValidator(password)) {
			logger.error("Error Code : " + RestErrorCode.INVALID_PASSWORD_UNSUPPORTED_CHAR.getErrorCode() + " Error Message : " + RestErrorCode.INVALID_PASSWORD_UNSUPPORTED_CHAR.getErrorMessage() +" UserName : " + userName);
			throw new RestException(RestErrorCode.INVALID_PASSWORD_UNSUPPORTED_CHAR.getErrorMessage(),RestErrorCode.INVALID_PASSWORD_UNSUPPORTED_CHAR.getErrorCode());
		    
		}
		
		if(CommonValidator.isBlank(email)) {
			logger.error("Error Code : " + RestErrorCode.NO_EMAIL_ID_SUPPLIED.getErrorCode() + " Error Message : " + RestErrorCode.NO_EMAIL_ID_SUPPLIED.getErrorMessage() +" Email : " + email+ " UserName : " + userName);
			throw new RestException(RestErrorCode.NO_EMAIL_ID_SUPPLIED.getErrorMessage(),RestErrorCode.NO_EMAIL_ID_SUPPLIED.getErrorCode());
		}
		
		if(!EmailValidator.getInstance().isValid(email)) {
			logger.error("Error Code : " + RestErrorCode.INVALID_EMAIL_ID.getErrorCode() + " Error Message : " + RestErrorCode.INVALID_EMAIL_ID.getErrorMessage() +" Email : " + email +" UserName : " + userName);
			throw new RestException(RestErrorCode.INVALID_EMAIL_ID.getErrorMessage(),RestErrorCode.INVALID_EMAIL_ID.getErrorCode());
		}
	}



	public static void updateUserRequestValidator(UpdateUserRequest updateUserRequest) throws RestException {
		
		final String name = updateUserRequest.getName();
		final String pin = updateUserRequest.getPin();
		
		if(CommonValidator.isBlank(updateUserRequest.getUserName())) {
			logger.error("Error Code : " + RestErrorCode.NO_USERNAME_SUPPLIED.getErrorCode() + " Error Message : " + RestErrorCode.NO_USERNAME_SUPPLIED.getErrorMessage() +" UserName : " + updateUserRequest.getUserName());
			throw new RestException(RestErrorCode.NO_USERNAME_SUPPLIED.getErrorMessage(),RestErrorCode.NO_USERNAME_SUPPLIED.getErrorCode());
		}
		
		if( ! CommonValidator.isBlank(name) && name.length() > 100) {
			logger.error("Error Code : " + RestErrorCode.INVALID_NAME_MAX_LENGTH_EXCEEDED.getErrorCode() + " Error Message : " + RestErrorCode.INVALID_NAME_MAX_LENGTH_EXCEEDED.getErrorMessage() +" Name : " + name +" UserName : " + updateUserRequest.getUserName());
			throw new RestException(RestErrorCode.INVALID_NAME_MAX_LENGTH_EXCEEDED.getErrorMessage(),RestErrorCode.INVALID_NAME_MAX_LENGTH_EXCEEDED.getErrorCode());
		    
		}
		
		if( ! CommonValidator.isBlank(name) && ! CommonValidator.letterValidator(name.replaceAll("\\s+",""))) {
			logger.error("Error Code : " + RestErrorCode.INVALID_NAME_UNSUPPORTED_CHAR.getErrorCode() + " Error Message : " + RestErrorCode.INVALID_NAME_UNSUPPORTED_CHAR.getErrorMessage() +" Name : " + name +" UserName : " + updateUserRequest.getUserName());
			throw new RestException(RestErrorCode.INVALID_NAME_UNSUPPORTED_CHAR.getErrorMessage(),RestErrorCode.INVALID_NAME_UNSUPPORTED_CHAR.getErrorCode());
		    
		}
		
		if(! CommonValidator.isBlank(pin) && ! CommonValidator.digitValidator(pin)) {
			logger.error("Error Code : " + RestErrorCode.INVALID_PIN_UNSUPPORTED_CHAR.getErrorCode() + " Error Message : " + RestErrorCode.INVALID_PIN_UNSUPPORTED_CHAR.getErrorMessage() +" Pin : " + pin +" UserName : " + updateUserRequest.getUserName());
			throw new RestException(RestErrorCode.INVALID_PIN_UNSUPPORTED_CHAR.getErrorMessage(),RestErrorCode.INVALID_PIN_UNSUPPORTED_CHAR.getErrorCode());
		    
		}
		
		if(! CommonValidator.isBlank(pin) && pin.length() > 4) {
			logger.error("Error Code : " + RestErrorCode.INVALID_PIN_MAX_LENGTH_EXCEEDED.getErrorCode() + " Error Message : " + RestErrorCode.INVALID_PIN_MAX_LENGTH_EXCEEDED.getErrorMessage() +" Pin : " + pin +" UserName : " + updateUserRequest.getUserName());
			throw new RestException(RestErrorCode.INVALID_PIN_MAX_LENGTH_EXCEEDED.getErrorMessage(),RestErrorCode.INVALID_PIN_MAX_LENGTH_EXCEEDED.getErrorCode());
		    
		}
		
		if(! CommonValidator.isBlank(pin) && pin.length() < 4) {
			logger.error("Error Code : " + RestErrorCode.INVALID_PIN_MIN_LENGTH_REQUIRED.getErrorCode() + " Error Message : " + RestErrorCode.INVALID_PIN_MIN_LENGTH_REQUIRED.getErrorMessage() +" Pin : " + pin +" UserName : " + updateUserRequest.getUserName());
			throw new RestException(RestErrorCode.INVALID_PIN_MIN_LENGTH_REQUIRED.getErrorMessage(),RestErrorCode.INVALID_PIN_MIN_LENGTH_REQUIRED.getErrorCode());
		    
		}
	}

public static void updatePinRequestValidator(UpdatePinRequest updatePinRequest) throws RestException {
		
		
		final String pin = updatePinRequest.getPin();
		
		if(CommonValidator.isBlank(updatePinRequest.getUserName())) {
			logger.error("Error Code : " + RestErrorCode.NO_USERNAME_SUPPLIED.getErrorCode() + " Error Message : " + RestErrorCode.NO_USERNAME_SUPPLIED.getErrorMessage() +" UserName : " + updatePinRequest.getUserName());
			throw new RestException(RestErrorCode.NO_USERNAME_SUPPLIED.getErrorMessage(),RestErrorCode.NO_USERNAME_SUPPLIED.getErrorCode());
		}
		
		
		
		if(! CommonValidator.isBlank(pin) && ! CommonValidator.digitValidator(pin)) {
			logger.error("Error Code : " + RestErrorCode.INVALID_PIN_UNSUPPORTED_CHAR.getErrorCode() + " Error Message : " + RestErrorCode.INVALID_PIN_UNSUPPORTED_CHAR.getErrorMessage() +" Pin : " + pin +" UserName : " + updatePinRequest.getUserName());
			throw new RestException(RestErrorCode.INVALID_PIN_UNSUPPORTED_CHAR.getErrorMessage(),RestErrorCode.INVALID_PIN_UNSUPPORTED_CHAR.getErrorCode());
		    
		}
		
		if(! CommonValidator.isBlank(pin) && pin.length() > 4) {
			logger.error("Error Code : " + RestErrorCode.INVALID_PIN_MAX_LENGTH_EXCEEDED.getErrorCode() + " Error Message : " + RestErrorCode.INVALID_PIN_MAX_LENGTH_EXCEEDED.getErrorMessage() +" Pin : " + pin +" UserName : " + updatePinRequest.getUserName());
			throw new RestException(RestErrorCode.INVALID_PIN_MAX_LENGTH_EXCEEDED.getErrorMessage(),RestErrorCode.INVALID_PIN_MAX_LENGTH_EXCEEDED.getErrorCode());
		    
		}
		
		if(! CommonValidator.isBlank(pin) && pin.length() < 4) {
			logger.error("Error Code : " + RestErrorCode.INVALID_PIN_MIN_LENGTH_REQUIRED.getErrorCode() + " Error Message : " + RestErrorCode.INVALID_PIN_MIN_LENGTH_REQUIRED.getErrorMessage() +" Pin : " + pin +" UserName : " + updatePinRequest.getUserName());
			throw new RestException(RestErrorCode.INVALID_PIN_MIN_LENGTH_REQUIRED.getErrorMessage(),RestErrorCode.INVALID_PIN_MIN_LENGTH_REQUIRED.getErrorCode());
		    
		}
	}


	public static void updatePasswordRequestValidator(UpdatePasswordRequest updatePasswordRequest) throws RestException {
	    String password = updatePasswordRequest.getNewPassword();
		
	    if(CommonValidator.isBlank(updatePasswordRequest.getUserName())) {
			logger.error("Error Code : " + RestErrorCode.NO_USERNAME_SUPPLIED.getErrorCode() + " Error Message : " + RestErrorCode.NO_USERNAME_SUPPLIED.getErrorMessage() +" UserName : " + updatePasswordRequest.getUserName());
			throw new RestException(RestErrorCode.NO_USERNAME_SUPPLIED.getErrorMessage(),RestErrorCode.NO_USERNAME_SUPPLIED.getErrorCode());
		}
	    
	    if(CommonValidator.isBlank(updatePasswordRequest.getOldPassword())) {
			logger.error("Error Code : " + RestErrorCode.NO_OLD_PASSWORD_SUPPLIED.getErrorCode() + " Error Message : " + RestErrorCode.NO_OLD_PASSWORD_SUPPLIED.getErrorMessage() +" UserName : " + updatePasswordRequest.getUserName());
			throw new RestException(RestErrorCode.NO_OLD_PASSWORD_SUPPLIED.getErrorMessage(),RestErrorCode.NO_OLD_PASSWORD_SUPPLIED.getErrorCode());
		}
		
		
		if(CommonValidator.isBlank(password)) {
			logger.error("Error Code : " + RestErrorCode.NO_NEW_PASSWORD_SUPPLIED.getErrorCode() + " Error Message : " + RestErrorCode.NO_NEW_PASSWORD_SUPPLIED.getErrorMessage() +" UserName : " + updatePasswordRequest.getUserName());
			throw new RestException(RestErrorCode.NO_NEW_PASSWORD_SUPPLIED.getErrorMessage(),RestErrorCode.NO_NEW_PASSWORD_SUPPLIED.getErrorCode());
		}
		
		
		
		if(password.length() > 25) {
			logger.error("Error Code : " + RestErrorCode.INVALID_NEW_PASSWORD_MAX_LENGTH_EXCEEDED.getErrorCode() + " Error Message : " + RestErrorCode.INVALID_NEW_PASSWORD_MAX_LENGTH_EXCEEDED.getErrorMessage() +" UserName : " + updatePasswordRequest.getUserName());
			throw new RestException(RestErrorCode.INVALID_NEW_PASSWORD_MAX_LENGTH_EXCEEDED.getErrorMessage(),RestErrorCode.INVALID_NEW_PASSWORD_MAX_LENGTH_EXCEEDED.getErrorCode());
		    
		}
		
		if(password.length() < 8) {
			logger.error("Error Code : " + RestErrorCode.INVALID_NEW_PASSWORD_MIN_LENGTH_REQUIRED.getErrorCode() + " Error Message : " + RestErrorCode.INVALID_NEW_PASSWORD_MIN_LENGTH_REQUIRED.getErrorMessage() +" UserName : " + updatePasswordRequest.getUserName());
			throw new RestException(RestErrorCode.INVALID_NEW_PASSWORD_MIN_LENGTH_REQUIRED.getErrorMessage(),RestErrorCode.INVALID_NEW_PASSWORD_MIN_LENGTH_REQUIRED.getErrorCode());
		    
		}
		
		if(!CommonValidator.specialCharValidator(password)) {
			logger.error("Error Code : " + RestErrorCode.INVALID_NEW_PASSWORD_UNSUPPORTED_CHAR.getErrorCode() + " Error Message : " + RestErrorCode.INVALID_NEW_PASSWORD_UNSUPPORTED_CHAR.getErrorMessage() +" UserName : " + updatePasswordRequest.getUserName());
			throw new RestException(RestErrorCode.INVALID_NEW_PASSWORD_UNSUPPORTED_CHAR.getErrorMessage(),RestErrorCode.INVALID_NEW_PASSWORD_UNSUPPORTED_CHAR.getErrorCode());
		    
		}
		
		if(password.equals(updatePasswordRequest.getOldPassword()))
	       {
	    		logger.error("Error code : " +RestErrorCode.NEW_PASSWORD_MATCH_OLD_PASSWORD.getErrorCode() + " Error message : "+RestErrorCode.NEW_PASSWORD_MATCH_OLD_PASSWORD.getErrorMessage() +" UserName : " + updatePasswordRequest.getUserName());
				throw new RestException(RestErrorCode.NEW_PASSWORD_MATCH_OLD_PASSWORD.getErrorMessage(),RestErrorCode.NEW_PASSWORD_MATCH_OLD_PASSWORD.getErrorCode());	
		   }
	       
	}
	
	public static void resetPasswordRequestValidator(ResetPasswordRequest resetPasswordRequest) throws RestException {
	   
		
	    if(CommonValidator.isBlank(resetPasswordRequest.getEmail())) {
			logger.error("Error Code : " + RestErrorCode.NO_EMAIL_ID_SUPPLIED.getErrorCode() + " Error Message : " + RestErrorCode.NO_EMAIL_ID_SUPPLIED.getErrorMessage() +" EmailId : " + resetPasswordRequest.getEmail());
			throw new RestException(RestErrorCode.NO_EMAIL_ID_SUPPLIED.getErrorMessage(),RestErrorCode.NO_EMAIL_ID_SUPPLIED.getErrorCode());
		}
	    
		       
	}
	
	public static void verifyPasswordRequestValidator(ResetPasswordRequest resetPasswordRequest) throws RestException {
	    String password = resetPasswordRequest.getNewPassword();
		
	    if(CommonValidator.isBlank(resetPasswordRequest.getEmail())) {
			logger.error("Error Code : " + RestErrorCode.NO_EMAIL_ID_SUPPLIED.getErrorCode() + " Error Message : " + RestErrorCode.NO_EMAIL_ID_SUPPLIED.getErrorMessage() +" EmailId : " + resetPasswordRequest.getEmail());
			throw new RestException(RestErrorCode.NO_EMAIL_ID_SUPPLIED.getErrorMessage(),RestErrorCode.NO_EMAIL_ID_SUPPLIED.getErrorCode());
		}
	    
	    
		
		
		if(CommonValidator.isBlank(password)) {
			logger.error("Error Code : " + RestErrorCode.NO_NEW_PASSWORD_SUPPLIED.getErrorCode() + " Error Message : " + RestErrorCode.NO_NEW_PASSWORD_SUPPLIED.getErrorMessage() +" EmailId : " + resetPasswordRequest.getEmail());
			throw new RestException(RestErrorCode.NO_NEW_PASSWORD_SUPPLIED.getErrorMessage(),RestErrorCode.NO_NEW_PASSWORD_SUPPLIED.getErrorCode());
		}
		
		
		
		if(password.length() > 25) {
			logger.error("Error Code : " + RestErrorCode.INVALID_NEW_PASSWORD_MAX_LENGTH_EXCEEDED.getErrorCode() + " Error Message : " + RestErrorCode.INVALID_NEW_PASSWORD_MAX_LENGTH_EXCEEDED.getErrorMessage() +" EmailId : " + resetPasswordRequest.getEmail());
			throw new RestException(RestErrorCode.INVALID_NEW_PASSWORD_MAX_LENGTH_EXCEEDED.getErrorMessage(),RestErrorCode.INVALID_NEW_PASSWORD_MAX_LENGTH_EXCEEDED.getErrorCode());
		    
		}
		
		if(password.length() < 8) {
			logger.error("Error Code : " + RestErrorCode.INVALID_NEW_PASSWORD_MIN_LENGTH_REQUIRED.getErrorCode() + " Error Message : " + RestErrorCode.INVALID_NEW_PASSWORD_MIN_LENGTH_REQUIRED.getErrorMessage() +" EmailId : " + resetPasswordRequest.getEmail());
			throw new RestException(RestErrorCode.INVALID_NEW_PASSWORD_MIN_LENGTH_REQUIRED.getErrorMessage(),RestErrorCode.INVALID_NEW_PASSWORD_MIN_LENGTH_REQUIRED.getErrorCode());
		    
		}
		
		if(!CommonValidator.specialCharValidator(password)) {
			logger.error("Error Code : " + RestErrorCode.INVALID_NEW_PASSWORD_UNSUPPORTED_CHAR.getErrorCode() + " Error Message : " + RestErrorCode.INVALID_NEW_PASSWORD_UNSUPPORTED_CHAR.getErrorMessage() +" EmailId : " + resetPasswordRequest.getEmail());
			throw new RestException(RestErrorCode.INVALID_NEW_PASSWORD_UNSUPPORTED_CHAR.getErrorMessage(),RestErrorCode.INVALID_NEW_PASSWORD_UNSUPPORTED_CHAR.getErrorCode());
		    
		}
		       
	}
	
	public static void updateEmailRequestValidator(UpdateEmailRequest updateEmailRequest) throws RestException {
	    String email = updateEmailRequest.getEmail();
		
	    if(CommonValidator.isBlank(updateEmailRequest.getUserName())) {
	    	logger.error("Error Code : " + RestErrorCode.NO_USERNAME_SUPPLIED.getErrorCode() + " Error Message : " + RestErrorCode.NO_USERNAME_SUPPLIED.getErrorMessage() +" UserName : " + updateEmailRequest.getUserName());
			throw new RestException(RestErrorCode.NO_USERNAME_SUPPLIED.getErrorMessage(),RestErrorCode.NO_USERNAME_SUPPLIED.getErrorCode());
		}
	    
	    
		
		
		if(CommonValidator.isBlank(email)) {
			logger.error("Error Code : " + RestErrorCode.NO_EMAIL_ID_SUPPLIED.getErrorCode() + " Error Message : " + RestErrorCode.NO_EMAIL_ID_SUPPLIED.getErrorMessage() +" UserName : " + updateEmailRequest.getUserName());
			throw new RestException(RestErrorCode.NO_EMAIL_ID_SUPPLIED.getErrorMessage(),RestErrorCode.NO_EMAIL_ID_SUPPLIED.getErrorCode());
		}
		
		
		
		if(email.length() > 100 || email.length() < 5) {
			logger.error("Error Code : " + RestErrorCode.INVALID_EMAIL_ID.getErrorCode() + " Error Message : " + RestErrorCode.INVALID_EMAIL_ID.getErrorMessage() +" UserName : " + updateEmailRequest.getUserName());
			throw new RestException(RestErrorCode.INVALID_EMAIL_ID.getErrorMessage(),RestErrorCode.INVALID_EMAIL_ID.getErrorCode());
		    
		}
		
		if(!CommonValidator.validateEmail(email)) {
			logger.error("Error Code : " + RestErrorCode.INVALID_EMAIL_ID.getErrorCode() + " Error Message : " + RestErrorCode.INVALID_EMAIL_ID.getErrorMessage() +" UserName : " + updateEmailRequest.getUserName());
			throw new RestException(RestErrorCode.INVALID_EMAIL_ID.getErrorMessage(),RestErrorCode.INVALID_EMAIL_ID.getErrorCode());
		    
		}
		
	       
	}
	
	public static void updatePhoneRequestValidator(UpdatePhoneRequest updatePhoneRequest) throws RestException {
	    String phone = updatePhoneRequest.getPhone();
		
	    if(CommonValidator.isBlank(updatePhoneRequest.getUserName())) {
	    	logger.error("Error Code : " + RestErrorCode.NO_USERNAME_SUPPLIED.getErrorCode() + " Error Message : " + RestErrorCode.NO_USERNAME_SUPPLIED.getErrorMessage() +" UserName : " + updatePhoneRequest.getUserName());
			throw new RestException(RestErrorCode.NO_USERNAME_SUPPLIED.getErrorMessage(),RestErrorCode.NO_USERNAME_SUPPLIED.getErrorCode());
		}
	    
	    
		
		
		if(CommonValidator.isBlank(phone)) {
			logger.error("Error Code : " + RestErrorCode.NO_PHONE_NUMBER_SUPPLIED.getErrorCode() + " Error Message : " + RestErrorCode.NO_PHONE_NUMBER_SUPPLIED.getErrorMessage() +" UserName : " + updatePhoneRequest.getUserName());
			throw new RestException(RestErrorCode.NO_PHONE_NUMBER_SUPPLIED.getErrorMessage(),RestErrorCode.NO_PHONE_NUMBER_SUPPLIED.getErrorCode());
		}
		
		
		
		if(phone.length() > 15 || phone.length() < 8) {
			logger.error("Error Code : " + RestErrorCode.INVALID_PHONE_NUMBER_SUPPLIED.getErrorCode() + " Error Message : " + RestErrorCode.INVALID_PHONE_NUMBER_SUPPLIED.getErrorMessage() +" UserName : " + updatePhoneRequest.getUserName());
			throw new RestException(RestErrorCode.INVALID_PHONE_NUMBER_SUPPLIED.getErrorMessage(),RestErrorCode.INVALID_PHONE_NUMBER_SUPPLIED.getErrorCode());
		    
		}
		
		if(!CommonValidator.phoneValidator(phone)) {
			logger.error("Error Code : " + RestErrorCode.INVALID_PHONE_NUMBER_SUPPLIED.getErrorCode() + " Error Message : " + RestErrorCode.INVALID_PHONE_NUMBER_SUPPLIED.getErrorMessage() +" UserName : " + updatePhoneRequest.getUserName());
			throw new RestException(RestErrorCode.INVALID_PHONE_NUMBER_SUPPLIED.getErrorMessage(),RestErrorCode.INVALID_PHONE_NUMBER_SUPPLIED.getErrorCode());
		    
		}
		
	       
	}
	
	
}
