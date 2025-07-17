package com.exchangerpoint.restservices.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import com.exchangerpoint.commonservices.errorcode.RestErrorCode;
import com.exchangerpoint.commonservices.exception.RestException;

@ControllerAdvice
@RequestMapping("/api/v1")
public class BaseRestController {
	protected final Log logger = LogFactory.getLog(BaseRestController.class);

	public void validateUserAccess(String userName) throws RestException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = authentication.getName();
	    if(!(userName.equalsIgnoreCase(currentUserName) || currentUserName.equalsIgnoreCase("ExchangerPoint"))) {
	    	logger.error("Error Code : " + RestErrorCode.ACCESS_DENIED.getErrorCode() + " Error Message : " + RestErrorCode.ACCESS_DENIED.getErrorMessage() +" UserName : " + userName);
			throw new RestException(RestErrorCode.ACCESS_DENIED.getErrorMessage(),RestErrorCode.ACCESS_DENIED.getErrorCode());
			
	    }
	}
	
	
	
}
