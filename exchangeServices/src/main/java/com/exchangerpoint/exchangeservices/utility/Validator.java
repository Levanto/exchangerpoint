/**
 * 
 */
package com.exchangerpoint.exchangeservices.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Govind
 *
 */
public class Validator {
	/**
	 * Log implementation for LoginController
	 */
	protected final Log logger = LogFactory.getLog(Validator.class);

	
	public boolean mailFieldValidator(String name,String email,String message,String subject)
	{ boolean flag=true;
		if(name==null||name.trim().length()==0)
			{flag=false;
			logger.error("Validation->Name field is empty in contact form");}
		if(email==null||email.trim().length()==0)
			{flag=false;
			logger.error("Validation->Email field is empty in contact form");}
		if(subject==null||subject.trim().length()==0)
		{flag=false;
		logger.error("Validation->Subject field is empty in contact form");}
		if(message==null||message.trim().length()==0)
			{flag=false;
			logger.error("Validation->Message field is empty in contact form");}
		if(message==null||message.length()>500)
			{flag=false;
			logger.error("Validation->Message length is more than 500 characters in contact form");}
		
		return flag;
	}

	
	public boolean isBlank(String... inputArray) {
		boolean isBlank = false;
		if(inputArray == null ) {
			return true;
		}
		
		for(String input :inputArray)
		{
			if(input == null || input.trim().length() ==0)
				isBlank = true;
		}
		return isBlank;
	}

}
