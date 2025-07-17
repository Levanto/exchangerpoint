package com.exchangerpoint.restservices.listener;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.exchangerpoint.commonservices.validator.CommonValidator;
import com.exchangerpoint.genericservices.service.GenericService;

@Transactional
@Component
public class AuthenticationListener implements ApplicationListener <AbstractAuthenticationEvent>
{
	
	@Autowired
	private GenericService genericService;
	
	
        @Override
        public void onApplicationEvent(AbstractAuthenticationEvent appEvent)
        {
        	try {
        		System.out.println("AuthenticationListener is being called ");
        		//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        		//String userName = authentication.getName();
        	
        		
	            if (appEvent instanceof AuthenticationSuccessEvent)
	            	
	            {   AuthenticationSuccessEvent event = (AuthenticationSuccessEvent)appEvent;
	            	Authentication authentication =event.getAuthentication();
            	    String userName = authentication.getName();
            	    String ipAddress = null;
            	    
            	    RequestAttributes attribs = RequestContextHolder.getRequestAttributes();
            	    if (RequestContextHolder.getRequestAttributes() != null) {
            	        HttpServletRequest request = ((ServletRequestAttributes) attribs).getRequest();
            	         ipAddress =CommonValidator.getCustomerRequestIpAddress(request);
            	    }
	        		
	        		
	        		String query ="UPDATE USER_ACCOUNT SET LAST_LOGIN_IP='"+ipAddress+"', FAILED_LOGIN_ATTEMPTS=0, LAST_LOGIN_TIME = CURRENT_TIMESTAMP where USER_NAME ='"+userName+"' OR EMAIL='"+userName+"' OR PHONE='"+userName+"'";
	        		if(!userName.equalsIgnoreCase("web_truested_cleint"))
	        		genericService.executeSQLUpdate(query);
	                
	            }
	
	            if (appEvent instanceof AuthenticationFailureBadCredentialsEvent)
	            {
	            	AuthenticationFailureBadCredentialsEvent event = (AuthenticationFailureBadCredentialsEvent) appEvent;
	            	Authentication authentication =event.getAuthentication();
	            	String userName = authentication.getName();
	            	
	        		String query ="UPDATE USER_ACCOUNT a INNER JOIN USER_ACCOUNT b ON a.USER_NAME=b.USER_NAME OR a.EMAIL=b.EMAIL OR a.PHONE=b.PHONE SET a.FAILED_LOGIN_ATTEMPTS= b.FAILED_LOGIN_ATTEMPTS+1  where a.USER_NAME='"+userName+"' OR a.EMAIL='"+userName+"' OR a.PHONE='"+userName+"'";
	        		if(!userName.equalsIgnoreCase("web_truested_cleint"))
	        		genericService.executeSQLUpdate(query);
	                        
	            } 
            
        	}catch(Exception e)  {
        		e.printStackTrace();
        	}
        }

       
    	
		public void setGenericService(GenericService genericService) {
			this.genericService = genericService;
		}

		
		

        
}