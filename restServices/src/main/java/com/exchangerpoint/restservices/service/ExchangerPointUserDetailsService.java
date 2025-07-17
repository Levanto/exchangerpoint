package com.exchangerpoint.restservices.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.routines.InetAddressValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.exchangerpoint.commonservices.constant.ApplicationConstants;
import com.exchangerpoint.commonservices.errorcode.RestErrorCode;
import com.exchangerpoint.commonservices.exception.AccessControlException;
import com.exchangerpoint.commonservices.service.AccessControlService;
import com.exchangerpoint.commonservices.service.MailService;
import com.exchangerpoint.commonservices.utility.Utility;
import com.exchangerpoint.commonservices.validator.CommonValidator;
import com.exchangerpoint.databaseservices.entity.TempRecord;
import com.exchangerpoint.databaseservices.entity.UserAccount;
import com.exchangerpoint.genericservices.service.GenericService;
import com.exchangerpoint.restservices.bean.RestError;
import com.exchangerpoint.userservices.UserService;

public class ExchangerPointUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GenericService genericService;
	
	
	@Autowired
	private AccessControlService accessControlService;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
    private HttpServletRequest request;
	
	@Resource(name = "tokenServices")
    ConsumerTokenServices tokenServices;
	
	@Resource(name="tokenStore")
	TokenStore tokenStore;
	
	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		
		
		UserAccount userAccount = userService.getUser(userName);
		if(userAccount == null)
			throw new UsernameNotFoundException("User "+ userName+ " not found");
		
		if(userAccount.getAccountStatus().equalsIgnoreCase(ApplicationConstants.ACCOUNT_STATUS_INACTIVE)) {
            throw new RuntimeException(ApplicationConstants.ACCOUNT_STATUS_INACTIVE);
        }
		
		if(userAccount.getAccountStatus().equalsIgnoreCase(ApplicationConstants.ACCOUNT_STATUS_CLOSED)) {
            throw new RuntimeException(ApplicationConstants.ACCOUNT_STATUS_CLOSED);
        }
		
		if(userAccount.getAccountStatus().equalsIgnoreCase(ApplicationConstants.ACCOUNT_STATUS_BLOCKED)) {
            throw new RuntimeException(ApplicationConstants.ACCOUNT_STATUS_BLOCKED);
        }
		
		if(userAccount.getAccountStatus().equalsIgnoreCase(ApplicationConstants.ACCOUNT_STATUS_SUSPENDED)) {
            throw new RuntimeException(ApplicationConstants.ACCOUNT_STATUS_SUSPENDED);
        }
		
		if(userAccount.getFailedLoginAttempts()>=5) {
            throw new RuntimeException(ApplicationConstants.ACCOUNT_STATUS_FAILED_ATTEMPT);
        }
		
		if(!doesIpMatch(CommonValidator.getCustomerRequestIpAddress(request),userAccount.getLastLoginIp())) {
			TempRecord tempRecord  = new TempRecord();
			tempRecord.setUserName(userAccount.getUserName());
			tempRecord.setField("VERIFYIP");
			tempRecord.setValue(CommonValidator.getCustomerRequestIpAddress(request));
			tempRecord.setCode(Utility.generateVerifyToken());
			boolean doesIpUpdated = genericService.save(tempRecord);
			if(doesIpUpdated) {
			
			String content ="Hi "+userAccount.getName()+ ",<br>This is to notify you that we have detected a login from an IP address that has not been seen on your account before. This will happen if you have a dynamic IP address, which is common. You will need to login again from the same device using the link below.<br><a href='https://exchangerpoint.com/user/"+userAccount.getUserName()+"/verifyip/"+tempRecord.getCode()+"'>CLICK HERE TO VERIFY IP</a><br> It is common for your IP address to change even if you are using the same device from the same location. However, if you are unsure please compare the IP address from this login event ("+CommonValidator.getCustomerRequestIpAddress(request)+") to the address found here: \r\n" + 
					"https://whatismyipaddress.com/ <br>Always use unique, strong passwords for your ExchangerPoint account and never use the same password twice. Do not reuse your passwords on other accounts, such as your personal email account. The ExchangerPoint staff will never ask you for your password.<br><br>Best Regards,<br>ExchangerPoint Team";
			mailService.sendMail("ExchangerPoint New IP Address Verification", userAccount.getEmail(), content);
			
			String selectQuery ="select token_id,refresh_token from oauth_access_token where user_name ='"+userAccount.getUserName()+"'";
			HashSet<String> oAuth2RefreshTokenSet = new HashSet<String>();
			@SuppressWarnings("unchecked")
			List<Object[]>  tokenList = genericService.performSelectQuery(selectQuery);
			
			if(tokenList != null && tokenList.size() >0) {
				for(Object[] token : tokenList) {
					tokenServices.revokeToken(token[0].toString());	
					
					if(oAuth2RefreshTokenSet.add(token[1].toString()))
						{
						genericService.executeSQLUpdate("delete from oauth_refresh_token where token_id ='"+token[1].toString()+"'");
						}
					
					
					
				}
				
			}
			
			genericService.executeSQLUpdate("delete from oauth_access_token where user_name ='"+userAccount.getUserName()+"'");
			throw new RuntimeException(ApplicationConstants.LAST_LOGIN_IP_NOT_MATCH);
			}
        }
		
		try {
			accessControlService.validateAccessControl(userAccount.getEmail(),userAccount.getPhone(),CommonValidator.getCustomerRequestIpAddress(request));
		}catch (AccessControlException e) {
			
			throw new RuntimeException(ApplicationConstants.ACCESS_DENIED);
		}
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
	        String[] authStrings = userAccount.getRole().trim().split(",");
	        for(String authString : authStrings) {
	            authorities.add(new SimpleGrantedAuthority(authString));
	        }
		UserDetails userDetails = (UserDetails)new User(userAccount.getUserName(),
				userAccount.getPassword(), authorities);
		return userDetails;
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<RestError> handleRuntimeException(RuntimeException e) 
	{	RestError restError = null;
	
		if(e.getMessage().equals(ApplicationConstants.ACCESS_DENIED))
		 restError = new RestError(RestErrorCode.ACCESS_DENIED.getErrorCode() , RestErrorCode.ACCESS_DENIED.getErrorMessage());
		
		
		if(e.getMessage().equals(ApplicationConstants.ACCOUNT_STATUS_INACTIVE))
		 restError = new RestError(RestErrorCode.USER_ACCOUNT_NOT_ACTIVE.getErrorCode() , RestErrorCode.USER_ACCOUNT_NOT_ACTIVE.getErrorMessage());
		
		if(e.getMessage().equals(ApplicationConstants.ACCOUNT_STATUS_BLOCKED))
			 restError = new RestError(RestErrorCode.USER_ACCOUNT_BLOCKED.getErrorCode() , RestErrorCode.USER_ACCOUNT_BLOCKED.getErrorMessage());
		
		if(e.getMessage().equals(ApplicationConstants.ACCOUNT_STATUS_SUSPENDED))
			 restError = new RestError(RestErrorCode.USER_ACCOUNT_SUSPENDED.getErrorCode() , RestErrorCode.USER_ACCOUNT_SUSPENDED.getErrorMessage());
		
		if(e.getMessage().equals(ApplicationConstants.ACCOUNT_STATUS_CLOSED))
			 restError = new RestError(RestErrorCode.USER_ACCOUNT_CLOSED.getErrorCode() , RestErrorCode.USER_ACCOUNT_CLOSED.getErrorMessage());
		
		
		if(e.getMessage().equals(ApplicationConstants.ACCOUNT_STATUS_FAILED_ATTEMPT))
			 restError = new RestError(RestErrorCode.USER_MAX_FAILED_ATTEMPT.getErrorCode() , RestErrorCode.USER_MAX_FAILED_ATTEMPT.getErrorMessage());
		
		if(e.getMessage().equals(ApplicationConstants.LAST_LOGIN_IP_NOT_MATCH))
			 restError = new RestError(RestErrorCode.LAST_LOGIN_IP_NOT_MATCH.getErrorCode() , RestErrorCode.LAST_LOGIN_IP_NOT_MATCH.getErrorMessage());
		
		return new ResponseEntity<RestError>(restError, HttpStatus.CONFLICT);
	}
	
	private boolean doesIpMatch(String newIpAddress,String oldIpAddress) {
		InetAddressValidator validator = InetAddressValidator.getInstance();
		if(oldIpAddress == null) {
			return true;
		}
		
		if(newIpAddress == null || !validator.isValid(newIpAddress)) {
			return false;
		}
		if(newIpAddress.equalsIgnoreCase(oldIpAddress))
		{
			return true;
		}
		if(validator.isValidInet4Address(oldIpAddress) && validator.isValidInet4Address(newIpAddress)) {
		String oldIpArray[] = oldIpAddress.split("\\.");
		String newIpArray[] = newIpAddress.split("\\.");
		
		if(oldIpArray[0].equals(newIpArray[0]) && oldIpArray[1].equals(newIpArray[1]))
			return true;
		}
		
		if(validator.isValidInet6Address(oldIpAddress)&& validator.isValidInet6Address(newIpAddress)) {
			String oldIpArray[] = oldIpAddress.split(":");
			String newIpArray[] = newIpAddress.split(":");
			
			if(oldIpArray[0].equalsIgnoreCase(newIpArray[0]) && oldIpArray[1].equalsIgnoreCase(newIpArray[1]))
				return true;
			}
		
		return false;
	}
	
	public void setAccessControlService(AccessControlService accessControlService) {
		this.accessControlService = accessControlService;
	}

	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public void setGenericService(GenericService genericService) {
		this.genericService = genericService;
	}

	
}

