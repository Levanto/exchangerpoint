package com.exchangerpoint.userservices;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.exchangerpoint.commonservices.common.ApplicationDataRepository;
import com.exchangerpoint.commonservices.constant.ApplicationConstants;
import com.exchangerpoint.commonservices.errorcode.ServiceErrorCode;
import com.exchangerpoint.commonservices.service.MailService;
import com.exchangerpoint.commonservices.service.MessagingService;
import com.exchangerpoint.commonservices.utility.CryptoUtil;
import com.exchangerpoint.commonservices.utility.Utility;
import com.exchangerpoint.commonservices.validator.CommonValidator;
import com.exchangerpoint.databaseservices.entity.FiatCurrency;
import com.exchangerpoint.databaseservices.entity.RazorpayAccount;
import com.exchangerpoint.databaseservices.entity.TempRecord;
import com.exchangerpoint.databaseservices.entity.UserAccount;
import com.exchangerpoint.databaseservices.entity.UserLimit;
import com.exchangerpoint.databaseservices.entity.UserOperation;
import com.exchangerpoint.databaseservices.entity.UserVerification;
import com.exchangerpoint.databaseservices.entity.UserWallet;
import com.exchangerpoint.genericservices.exception.GenericException;
import com.exchangerpoint.genericservices.service.GenericService;
import com.exchangerpoint.genericservices.util.PropertyConditionMatcher;
import com.exchangerpoint.walletservices.razorpay.RazorpayException;
import com.exchangerpoint.restspec.dto.user.ActivateAccountRequest;
import com.exchangerpoint.restspec.dto.user.AddUserRequest;
import com.exchangerpoint.restspec.dto.user.IdentityVerificationRequest;
import com.exchangerpoint.restspec.dto.user.IpVerificationRequest;
import com.exchangerpoint.restspec.dto.user.ResetPasswordRequest;
import com.exchangerpoint.restspec.dto.user.UpdateEmailRequest;
import com.exchangerpoint.restspec.dto.user.UpdatePasswordRequest;
import com.exchangerpoint.restspec.dto.user.UpdatePhoneRequest;
import com.exchangerpoint.restspec.dto.user.UpdatePinRequest;
import com.exchangerpoint.restspec.dto.user.UpdateUserRequest;
import com.exchangerpoint.walletservices.WalletService;
import com.exchangerpoint.userservices.exception.UserServiceException;
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private GenericService genericService;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private MessagingService messagingService;
	
	
	@Autowired
	private DelegatingPasswordEncoder passwordEncoder;
	
	@Autowired
	private WalletService walletService;
	
	@Autowired
	private ApplicationDataRepository applicationDataRepository;
	
    protected final Log logger = LogFactory.getLog(UserServiceImpl.class);

	
	@SuppressWarnings("unchecked")
	@Override
	public UserAccount getUser(String userName) {
       try {
    	 
		Criteria criteria = genericService.createBlankCriteria(UserAccount.class);
		
		criteria = genericService.addORConditionToCriteria(criteria, prepareORConditionsList(userName));
		List<UserAccount> userList = criteria.list();
		
		if(userList != null && userList.size()>=1)
			return userList.get(0);
		else 
			return null;
       } catch (GenericException e) {
    	   e.printStackTrace();
    	   return null;
       }
		
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<UserAccount> getUserList() {
		return (List<UserAccount>) genericService.getList(UserAccount.class);
		
	}

	@Override
	public boolean doesUserNameExist(String userName) {
		UserAccount userAccount = (UserAccount) genericService.getObjectById(UserAccount.class, userName);
		if(userAccount != null)
			return true;
		
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean doesEmailExist(String email) {
		HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("email", email);
		List<UserAccount> userAccountList = (List<UserAccount>) genericService.getObjectsByEqualityConditions(UserAccount.class, properties);
		
		if(userAccountList != null && userAccountList.size() > 0)
			return true;
		
		return false;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean doesPhoneExist(String phone) {
		HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("phone", phone);
		List<UserAccount> userAccountList = (List<UserAccount>) genericService.getObjectsByEqualityConditions(UserAccount.class, properties);
		
		if(userAccountList != null && userAccountList.size() >0)
			return true;
		
		return false;
	}

	@Override
	public boolean addUser(AddUserRequest addUserRequest) throws UserServiceException{
		
		boolean doesUserNameExist = doesUserNameExist(addUserRequest.getUserName());
		if(doesUserNameExist) {
			logger.error("Error Code : " + ServiceErrorCode.USERNAME_ALREADY_TAKEN.getErrorCode() + " Error Message : " + ServiceErrorCode.USERNAME_ALREADY_TAKEN.getErrorMessage() +" UserName : " + addUserRequest.getUserName());
			throw new UserServiceException(ServiceErrorCode.USERNAME_ALREADY_TAKEN.getErrorMessage(),ServiceErrorCode.USERNAME_ALREADY_TAKEN.getErrorCode());	
		}
		
		boolean doesEmailExist = doesEmailExist(addUserRequest.getEmail());
		if(doesEmailExist) {
			logger.error("Error Code : " + ServiceErrorCode.EMAIL_ID_ALREADY_EXIST.getErrorCode() + " Error Message : " + ServiceErrorCode.EMAIL_ID_ALREADY_EXIST.getErrorMessage() +" Email ID : " + addUserRequest.getEmail());
			throw new UserServiceException(ServiceErrorCode.EMAIL_ID_ALREADY_EXIST.getErrorMessage(),ServiceErrorCode.EMAIL_ID_ALREADY_EXIST.getErrorCode());	
		}
		UserAccount userAccount = new UserAccount();
		userAccount.setUserName(addUserRequest.getUserName());
		userAccount.setEmail(addUserRequest.getEmail());
		userAccount.setRole("ROLE_USER");
		userAccount.setPassword(passwordEncoder.encode(addUserRequest.getPassword()));
		userAccount.setAccountStatus(ApplicationConstants.ACCOUNT_STATUS_INACTIVE);
		
		
		boolean doesUserCreated = genericService.save(userAccount);
		if(doesUserCreated) {
			TempRecord tempRecord  = new TempRecord();
			tempRecord.setUserName(addUserRequest.getUserName());
			tempRecord.setField("ACTIVATION");
			tempRecord.setCode(Utility.generateVerifyCode());
			genericService.save(tempRecord);
			String body ="Hi "+userAccount.getUserName()+",<br>Welcome to ExchangerPoint, Please use below code to activate you account <br> Your activation code is "+tempRecord.getCode()+"<br> You can also activate your account by using below link <a href='https://exchangerpoint.com/user/"+userAccount.getUserName()+"/activate/"+tempRecord.getCode()+"'>CLICK HERE TO ACTIVATE</a><br> Best Regards,<br>ExchangerPoint Team";
		    mailService.sendMail("ExchangerPoint Account Activation Code", addUserRequest.getEmail(), body);
		}
		
	  return doesUserCreated;
	}

	


	@Override
	public boolean updateUser(UpdateUserRequest updateUserRequest) throws UserServiceException {
		UserAccount userAccount = (UserAccount) genericService.getObjectById(UserAccount.class, updateUserRequest.getUserName());
		
	    if(userAccount == null) {
	    	logger.error("Error code : " +ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode() + " Error message : "+ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorMessage()+" UpdateUserRequest " + updateUserRequest.toString());
			throw new UserServiceException(ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorMessage(),ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode());	
	    }
	    
	    

	    UserVerification userVerification = userAccount.getUserVerification();
	    if(userVerification==null ||!userVerification.isAddress())
	    userAccount.setCountry(updateUserRequest.getCountry());
	    if(userVerification== null || !userVerification.isId())
		userAccount.setName(updateUserRequest.getName());
		if(userAccount.getPin()==null)
		userAccount.setPin(CryptoUtil.encrypt(ApplicationConstants.PLAY_BALL1, ApplicationConstants.PLAY_BALL2, updateUserRequest.getPin()));
		
		 UserOperation userOperation = userAccount.getUserOperation();
		 
		if(userOperation== null) {
			userOperation = new UserOperation();
			userOperation.setDeposit(true);
			userOperation.setWithdraw(true);
			userOperation.setUpdateProfile(true);
			userOperation.setTrade(true);
			userOperation.setUserName(updateUserRequest.getUserName());
			userAccount.setUserOperation(userOperation);
		}
		
		if(!userOperation.isUpdateProfile() || !userAccount.getAccountStatus().equalsIgnoreCase(ApplicationConstants.ACCOUNT_STATUS_ACTIVE)) {
			logger.error("Error code : " +ServiceErrorCode.ACCESS_DENIED.getErrorCode() + " Error message : "+ServiceErrorCode.ACCESS_DENIED.getErrorMessage()+" UpdateUserRequest " + updateUserRequest.toString());
			throw new UserServiceException(ServiceErrorCode.ACCESS_DENIED.getErrorMessage(),ServiceErrorCode.ACCESS_DENIED.getErrorCode());	
	   	
		}
		
		if(userAccount.getUserWallets().size()==0 && userAccount.getUserLimits().size()==0)
		userAccount = setWalletAndLimit(userAccount.getCountry(), userAccount.getUserName(),userAccount);
		
		return genericService.update(userAccount);
	}



	@Override
	public boolean deleteUser(String userName) throws UserServiceException {
       UserAccount userAccount = (UserAccount) genericService.getObjectById(UserAccount.class, userName);
       if(userAccount == null) {
	    	logger.error("Error code : " +ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode() + " Error message : "+ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorMessage()+" userName" + userName);
			throw new UserServiceException(ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorMessage(),ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode());	
	    }
	    
       if(userAccount.getUserOperation() == null || !userAccount.getUserOperation().isUpdateProfile() ||  !userAccount.getAccountStatus().equalsIgnoreCase(ApplicationConstants.ACCOUNT_STATUS_ACTIVE)) {
			logger.error("Error code : " +ServiceErrorCode.ACCESS_DENIED.getErrorCode() + " Error message : "+ServiceErrorCode.ACCESS_DENIED.getErrorMessage()+" DeleteUserRequest " + userName);
			throw new UserServiceException(ServiceErrorCode.ACCESS_DENIED.getErrorMessage(),ServiceErrorCode.ACCESS_DENIED.getErrorCode());	
	   	
		}
      
      
       userAccount.setAccountStatus(ApplicationConstants.ACCOUNT_STATUS_CLOSED);
	
	   return genericService.update(userAccount);
	}



	@Override
	public boolean updatePassword(UpdatePasswordRequest updatePasswordRequest) throws UserServiceException {
		 UserAccount userAccount = (UserAccount) genericService.getObjectById(UserAccount.class, updatePasswordRequest.getUserName());
	       if(userAccount == null) {
		    	logger.error("Error code : " +ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode() + " Error message : "+ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorMessage()+" userName " + updatePasswordRequest.getUserName());
				throw new UserServiceException(ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorMessage(),ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode());	
		    }
	       String newPassword =passwordEncoder.encode(updatePasswordRequest.getNewPassword());
	       
	       if(!passwordEncoder.matches(updatePasswordRequest.getOldPassword(), userAccount.getPassword()))
	       {
	    		logger.error("Error code : " +ServiceErrorCode.OLD_PASSWORD_NOT_MATCH.getErrorCode() + " Error message : "+ServiceErrorCode.OLD_PASSWORD_NOT_MATCH.getErrorMessage()+" userName " + updatePasswordRequest.getUserName());
				throw new UserServiceException(ServiceErrorCode.OLD_PASSWORD_NOT_MATCH.getErrorMessage(),ServiceErrorCode.OLD_PASSWORD_NOT_MATCH.getErrorCode());	
		   }
	       
	       if(userAccount.getUserOperation() == null || !userAccount.getUserOperation().isUpdateProfile() ||  !userAccount.getAccountStatus().equalsIgnoreCase(ApplicationConstants.ACCOUNT_STATUS_ACTIVE)) {
				logger.error("Error code : " +ServiceErrorCode.ACCESS_DENIED.getErrorCode() + " Error message : "+ServiceErrorCode.ACCESS_DENIED.getErrorMessage()+" updatePasswordRequest " + updatePasswordRequest.getUserName());
				throw new UserServiceException(ServiceErrorCode.ACCESS_DENIED.getErrorMessage(),ServiceErrorCode.ACCESS_DENIED.getErrorCode());	
		   	
			}
	       userAccount.setPassword(newPassword);
	       return genericService.update(userAccount);
	}



	@Override
	public boolean updateEmail(UpdateEmailRequest updateEmailRequest) throws UserServiceException {
		 UserAccount userAccount = (UserAccount) genericService.getObjectById(UserAccount.class, updateEmailRequest.getUserName());
	       if(userAccount == null) {
		    	logger.error("Error code : " +ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode() + " Error message : "+ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorMessage()+" UserName " + updateEmailRequest.getUserName());
				throw new UserServiceException(ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorMessage(),ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode());	
		    }
	       
	       if(!CryptoUtil.decrypt(ApplicationConstants.PLAY_BALL1, ApplicationConstants.PLAY_BALL2, userAccount.getPin()).equalsIgnoreCase(updateEmailRequest.getPin())) {
		    	logger.error("Error code : " +ServiceErrorCode.PIN_DOES_NOT_MATCH.getErrorCode() + " Error message : "+ServiceErrorCode.PIN_DOES_NOT_MATCH.getErrorMessage()+" UserName " + updateEmailRequest.getUserName());
				throw new UserServiceException(ServiceErrorCode.PIN_DOES_NOT_MATCH.getErrorMessage(),ServiceErrorCode.PIN_DOES_NOT_MATCH.getErrorCode());	
		    }
	       
	       boolean doesEmailExist = doesEmailExist(updateEmailRequest.getEmail());
			if(doesEmailExist) {
				logger.error("Error Code : " + ServiceErrorCode.EMAIL_ID_ALREADY_EXIST.getErrorCode() + " Error Message : " + ServiceErrorCode.EMAIL_ID_ALREADY_EXIST.getErrorMessage() +" UserName  : " + updateEmailRequest.getUserName());
				throw new UserServiceException(ServiceErrorCode.EMAIL_ID_ALREADY_EXIST.getErrorMessage(),ServiceErrorCode.EMAIL_ID_ALREADY_EXIST.getErrorCode());	
			}
			
			 if(userAccount.getUserOperation() == null || !userAccount.getUserOperation().isUpdateProfile() ||  !userAccount.getAccountStatus().equalsIgnoreCase(ApplicationConstants.ACCOUNT_STATUS_ACTIVE)) {
					logger.error("Error code : " +ServiceErrorCode.ACCESS_DENIED.getErrorCode() + " Error message : "+ServiceErrorCode.ACCESS_DENIED.getErrorMessage()+" updateEmailRequest " + updateEmailRequest.getUserName());
					throw new UserServiceException(ServiceErrorCode.ACCESS_DENIED.getErrorMessage(),ServiceErrorCode.ACCESS_DENIED.getErrorCode());	
			   	
				}
			 
			 
			
			
				TempRecord tempRecord  = new TempRecord();
				tempRecord.setUserName(userAccount.getUserName());
				tempRecord.setField("EMAIL");
				tempRecord.setValue(updateEmailRequest.getEmail());
				tempRecord.setCode(Utility.generateVerifyCode());
				
				boolean doesEmailUpdated= genericService.save(tempRecord);
				if(doesEmailUpdated) {
				String body ="Hi "+userAccount.getName()+",<br>Your email verification code is "+tempRecord.getCode()+"<br>You can also verify email by using below link <a href='https://exchangerpoint.com/user/"+userAccount.getUserName()+"/verifyemail/"+tempRecord.getCode()+"'>CLICK HERE TO VERIFY</a><br> Best Regards,<br>ExchangerPoint Team";

			    mailService.sendMail("Email Verification Code", updateEmailRequest.getEmail(), body);
			}
			
		  return doesEmailUpdated;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean verifyEmail(UpdateEmailRequest updateEmailRequest) throws UserServiceException {
        UserAccount userAccount = (UserAccount) genericService.getObjectById(UserAccount.class, updateEmailRequest.getUserName());
        
		if(userAccount == null) {
	    	logger.error("Error code : " +ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode() + " Error message : "+ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorMessage() + " UserName " +updateEmailRequest.getUserName());
			throw new UserServiceException(ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorMessage(),ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode());	
	    }
		HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("field", "EMAIL");
		properties.put("userName", updateEmailRequest.getUserName());
		List<TempRecord> tempRecordList = (List<TempRecord>) genericService.getOrderedList(TempRecord.class, properties, "createdDate", "desc");
		
	    if(tempRecordList == null || tempRecordList.size() ==0 || !tempRecordList.get(0).getCode().equals(updateEmailRequest.getVerificationCode())|| !tempRecordList.get(0).getUserName().equalsIgnoreCase(userAccount.getUserName()))
	    {	logger.error("Error code : " + ServiceErrorCode.INVALID_VERIFICATION_CODE.getErrorCode() + " Error message : "+ServiceErrorCode.INVALID_VERIFICATION_CODE.getErrorMessage() + " UserName " +updateEmailRequest.getUserName());
		    throw new UserServiceException(ServiceErrorCode.INVALID_VERIFICATION_CODE.getErrorMessage(),ServiceErrorCode.INVALID_VERIFICATION_CODE.getErrorCode());	
	     }
	    
	    boolean doesEmailExist = doesEmailExist(tempRecordList.get(0).getValue());
		if(doesEmailExist) {
			logger.error("Error Code : " + ServiceErrorCode.EMAIL_ID_ALREADY_EXIST.getErrorCode() + " Error Message : " + ServiceErrorCode.EMAIL_ID_ALREADY_EXIST.getErrorMessage() +" UserName  : " + updateEmailRequest.getUserName());
			throw new UserServiceException(ServiceErrorCode.EMAIL_ID_ALREADY_EXIST.getErrorMessage(),ServiceErrorCode.EMAIL_ID_ALREADY_EXIST.getErrorCode());	
		}
		
	    if(userAccount.getUserOperation() == null || !userAccount.getUserOperation().isUpdateProfile() || !userAccount.getAccountStatus().equalsIgnoreCase(ApplicationConstants.ACCOUNT_STATUS_ACTIVE)) {
			logger.error("Error code : " +ServiceErrorCode.ACCESS_DENIED.getErrorCode() + " Error message : "+ServiceErrorCode.ACCESS_DENIED.getErrorMessage()+" updateEmailRequest " + updateEmailRequest.getUserName());
			throw new UserServiceException(ServiceErrorCode.ACCESS_DENIED.getErrorMessage(),ServiceErrorCode.ACCESS_DENIED.getErrorCode());	
	   	
		}
	    
	    userAccount.setEmail(tempRecordList.get(0).getValue());
	    UserVerification userVerification = userAccount.getUserVerification();
		userVerification.setEmail(true);
		userAccount.setUserVerification(userVerification);
		genericService.delete(tempRecordList.get(0));
	    return genericService.update(userAccount);
	}

	
	@Override
	public boolean updatePhone(UpdatePhoneRequest updatePhoneRequest) throws UserServiceException {
		UserAccount userAccount = (UserAccount) genericService.getObjectById(UserAccount.class, updatePhoneRequest.getUserName());
	       if(userAccount == null) {
		    	logger.error("Error code : " +ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode() + " Error message : "+ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorMessage()+" UserName " + updatePhoneRequest.getUserName());
				throw new UserServiceException(ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorMessage(),ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode());	
		    }
	       
	       if(!CryptoUtil.decrypt(ApplicationConstants.PLAY_BALL1, ApplicationConstants.PLAY_BALL2, userAccount.getPin()).equalsIgnoreCase(updatePhoneRequest.getPin())) {
		    	logger.error("Error code : " +ServiceErrorCode.PIN_DOES_NOT_MATCH.getErrorCode() + " Error message : "+ServiceErrorCode.PIN_DOES_NOT_MATCH.getErrorMessage()+" UserName " + updatePhoneRequest.getUserName());
				throw new UserServiceException(ServiceErrorCode.PIN_DOES_NOT_MATCH.getErrorMessage(),ServiceErrorCode.PIN_DOES_NOT_MATCH.getErrorCode());	
		    }
	       
	       boolean doesPhoneExist = doesPhoneExist(updatePhoneRequest.getPhone());
			if(doesPhoneExist) {
				logger.error("Error Code : " + ServiceErrorCode.PHONE_ALREADY_REGISTERED.getErrorCode() + " Error Message : " + ServiceErrorCode.PHONE_ALREADY_REGISTERED.getErrorMessage() +" UserName  : " + updatePhoneRequest.getUserName());
				throw new UserServiceException(ServiceErrorCode.PHONE_ALREADY_REGISTERED.getErrorMessage(),ServiceErrorCode.PHONE_ALREADY_REGISTERED.getErrorCode());	
			}
			
			 if(userAccount.getUserOperation() == null || !userAccount.getUserOperation().isUpdateProfile() ||  !userAccount.getAccountStatus().equalsIgnoreCase(ApplicationConstants.ACCOUNT_STATUS_ACTIVE)) {
					logger.error("Error code : " +ServiceErrorCode.ACCESS_DENIED.getErrorCode() + " Error message : "+ServiceErrorCode.ACCESS_DENIED.getErrorMessage()+" updatePhoneRequest " + updatePhoneRequest.getUserName());
					throw new UserServiceException(ServiceErrorCode.ACCESS_DENIED.getErrorMessage(),ServiceErrorCode.ACCESS_DENIED.getErrorCode());	
			   	
				}
			 
			
		
				TempRecord tempRecord  = new TempRecord();
				tempRecord.setUserName(userAccount.getUserName());
				tempRecord.setField("PHONE");
				tempRecord.setValue(updatePhoneRequest.getPhone());
				tempRecord.setCode(Utility.generateVerifyCode());
				boolean doesPhoneUpdated = genericService.save(tempRecord);
				if(doesPhoneUpdated) {
			    messagingService.sendSMS(updatePhoneRequest.getPhone(), "Your phone verification code is "+ tempRecord.getCode()+" on ExchangerPoint.com. Pease do not share it with anyone.");
			}
			
		  return doesPhoneUpdated;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean verifyPhone(UpdatePhoneRequest updatePhoneRequest) throws UserServiceException {
        UserAccount userAccount = (UserAccount) genericService.getObjectById(UserAccount.class, updatePhoneRequest.getUserName());
       
	    if(userAccount == null) {
	    	logger.error("Error code : " +ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode() + " Error message : "+ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorMessage() + " UserName " +updatePhoneRequest.getUserName());
			throw new UserServiceException(ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorMessage(),ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode());	
	    }
	    HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("field", "PHONE");
		properties.put("userName", updatePhoneRequest.getUserName());
		List<TempRecord> tempRecordList = (List<TempRecord>) genericService.getOrderedList(TempRecord.class, properties, "createdDate", "desc");
		
	    if(tempRecordList == null || tempRecordList.size() ==0 || !tempRecordList.get(0).getCode().equals(updatePhoneRequest.getVerificationCode())|| !tempRecordList.get(0).getUserName().equalsIgnoreCase(userAccount.getUserName()))
	    {	logger.error("Error code : " + ServiceErrorCode.INVALID_VERIFICATION_CODE.getErrorCode() + " Error message : "+ServiceErrorCode.INVALID_VERIFICATION_CODE.getErrorMessage() + " UserName " +updatePhoneRequest.getUserName());
		    throw new UserServiceException(ServiceErrorCode.INVALID_VERIFICATION_CODE.getErrorMessage(),ServiceErrorCode.INVALID_VERIFICATION_CODE.getErrorCode());	
	     }
	    boolean doesPhoneExist = doesPhoneExist(tempRecordList.get(0).getValue());
		if(doesPhoneExist) {
			logger.error("Error Code : " + ServiceErrorCode.PHONE_ALREADY_REGISTERED.getErrorCode() + " Error Message : " + ServiceErrorCode.PHONE_ALREADY_REGISTERED.getErrorMessage() +" UserName  : " + updatePhoneRequest.getUserName());
			throw new UserServiceException(ServiceErrorCode.PHONE_ALREADY_REGISTERED.getErrorMessage(),ServiceErrorCode.PHONE_ALREADY_REGISTERED.getErrorCode());	
		}
		
	    if(userAccount.getUserOperation() == null || !userAccount.getUserOperation().isUpdateProfile() || !userAccount.getAccountStatus().equalsIgnoreCase(ApplicationConstants.ACCOUNT_STATUS_ACTIVE)) {
			logger.error("Error code : " +ServiceErrorCode.ACCESS_DENIED.getErrorCode() + " Error message : "+ServiceErrorCode.ACCESS_DENIED.getErrorMessage()+" updatePhoneRequest " + updatePhoneRequest.getUserName());
			throw new UserServiceException(ServiceErrorCode.ACCESS_DENIED.getErrorMessage(),ServiceErrorCode.ACCESS_DENIED.getErrorCode());	
	   	
		}
	    
	    userAccount.setPhone(tempRecordList.get(0).getValue());
	    UserVerification userVerification = userAccount.getUserVerification();
		userVerification.setPhone(true);
		userAccount.setUserVerification(userVerification);
		genericService.delete(tempRecordList.get(0));
	    return genericService.update(userAccount);
	}
	
	
	@Override
	public boolean updatePin(UpdatePinRequest updatePinRequest) throws UserServiceException {
		UserAccount userAccount = (UserAccount) genericService.getObjectById(UserAccount.class, updatePinRequest.getUserName());
	       if(userAccount == null) {
		    	logger.error("Error code : " +ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode() + " Error message : "+ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorMessage()+" UserName " + updatePinRequest.getUserName());
				throw new UserServiceException(ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorMessage(),ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode());	
		    }
	       
	       if(CryptoUtil.decrypt(ApplicationConstants.PLAY_BALL1, ApplicationConstants.PLAY_BALL2, userAccount.getPin()).equalsIgnoreCase(updatePinRequest.getPin())) {
				logger.error("Error Code : " + ServiceErrorCode.NEW_PIN_MATCHES_OLD_PIN.getErrorCode() + " Error Message : " + ServiceErrorCode.NEW_PIN_MATCHES_OLD_PIN.getErrorMessage() +" UserName  : " + updatePinRequest.getUserName());
				throw new UserServiceException(ServiceErrorCode.NEW_PIN_MATCHES_OLD_PIN.getErrorMessage(),ServiceErrorCode.NEW_PIN_MATCHES_OLD_PIN.getErrorCode());	
			}
	       
	       if(CommonValidator.isBlank(userAccount.getPhone())) {
				logger.error("Error Code : " + ServiceErrorCode.NO_PHONE_UPDATED.getErrorCode() + " Error Message : " + ServiceErrorCode.NO_PHONE_UPDATED.getErrorMessage() +" UserName  : " + updatePinRequest.getUserName());
				throw new UserServiceException(ServiceErrorCode.NO_PHONE_UPDATED.getErrorMessage(),ServiceErrorCode.NO_PHONE_UPDATED.getErrorCode());	
			}
			
	       if(userAccount.getUserOperation() == null || !userAccount.getUserOperation().isUpdateProfile() ||  !userAccount.getAccountStatus().equalsIgnoreCase(ApplicationConstants.ACCOUNT_STATUS_ACTIVE)) {
				logger.error("Error code : " +ServiceErrorCode.ACCESS_DENIED.getErrorCode() + " Error message : "+ServiceErrorCode.ACCESS_DENIED.getErrorMessage()+" updatePinRequest " + updatePinRequest.getUserName());
				throw new UserServiceException(ServiceErrorCode.ACCESS_DENIED.getErrorMessage(),ServiceErrorCode.ACCESS_DENIED.getErrorCode());	
		   	
			}
			TempRecord tempRecord  = new TempRecord();
			tempRecord.setUserName(userAccount.getUserName());
			tempRecord.setField("PIN");
			tempRecord.setValue(CryptoUtil.encrypt(ApplicationConstants.PLAY_BALL1, ApplicationConstants.PLAY_BALL2, updatePinRequest.getPin()));
			tempRecord.setCode(Utility.generateVerifyCode());
				
			boolean doesPinUpdated = genericService.save(tempRecord);
			if(doesPinUpdated) {
			    messagingService.sendSMS(userAccount.getPhone(), "Your Pin verification code is "+ tempRecord.getCode()+" on ExchangerPoint.com. Please do not share this with anyone.");
			}
			
		  return doesPinUpdated;
	}


	@SuppressWarnings("unchecked")
	@Override
	public boolean verifyPin(UpdatePinRequest updatePinRequest) throws UserServiceException {
        UserAccount userAccount = (UserAccount) genericService.getObjectById(UserAccount.class, updatePinRequest.getUserName());
        
		if(userAccount == null ) {
	    	logger.error("Error code : " +ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode() + " Error message : "+ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorMessage() + " UserName " +updatePinRequest.getUserName());
			throw new UserServiceException(ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorMessage(),ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode());	
	    }
	    
		HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("field", "PIN");
		properties.put("userName", updatePinRequest.getUserName());
		List<TempRecord> tempRecordList = (List<TempRecord>) genericService.getOrderedList(TempRecord.class, properties, "createdDate", "desc");
		
		
	    if(tempRecordList == null || tempRecordList.size() ==0 || !tempRecordList.get(0).getCode().equals(updatePinRequest.getVerificationCode()) || !tempRecordList.get(0).getUserName().equalsIgnoreCase(userAccount.getUserName()))
	    {	logger.error("Error code : " + ServiceErrorCode.INVALID_VERIFICATION_CODE.getErrorCode() + " Error message : "+ServiceErrorCode.INVALID_VERIFICATION_CODE.getErrorMessage() + " UserName " +updatePinRequest.getUserName());
		    throw new UserServiceException(ServiceErrorCode.INVALID_VERIFICATION_CODE.getErrorMessage(),ServiceErrorCode.INVALID_VERIFICATION_CODE.getErrorCode());	
	     }
	   
	    if(userAccount.getUserOperation() == null || !userAccount.getUserOperation().isUpdateProfile() || !userAccount.getAccountStatus().equalsIgnoreCase(ApplicationConstants.ACCOUNT_STATUS_ACTIVE)) {
			logger.error("Error code : " +ServiceErrorCode.ACCESS_DENIED.getErrorCode() + " Error message : "+ServiceErrorCode.ACCESS_DENIED.getErrorMessage()+" updatePinRequest " + updatePinRequest.getUserName());
			throw new UserServiceException(ServiceErrorCode.ACCESS_DENIED.getErrorMessage(),ServiceErrorCode.ACCESS_DENIED.getErrorCode());	
	   	
		}
	    userAccount.setPin(tempRecordList.get(0).getValue());
	    
	    genericService.delete(tempRecordList.get(0));
	    return genericService.update(userAccount);
	}
	
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean resetPassword(ResetPasswordRequest resetPasswordRequest) throws UserServiceException {
		HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("email", resetPasswordRequest.getEmail());
		List<UserAccount> userAccountList = (List<UserAccount>) genericService.getObjectsByEqualityConditions(UserAccount.class, properties);
		
		
		
		if(userAccountList == null || userAccountList.size() ==0 ) {
		    	logger.error("Error code : " +ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode() + " Error message : "+ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorMessage()+" Email " + resetPasswordRequest.getEmail());
				throw new UserServiceException(ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorMessage(),ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode());	
		    }
	       
		if((userAccountList.get(0).getUserOperation()!= null && !userAccountList.get(0).getUserOperation().isUpdateProfile() )|| userAccountList.get(0).getAccountStatus().equalsIgnoreCase(ApplicationConstants.ACCOUNT_STATUS_BLOCKED) || userAccountList.get(0).getAccountStatus().equalsIgnoreCase(ApplicationConstants.ACCOUNT_STATUS_CLOSED)) {
			
			logger.error("Error code : " +ServiceErrorCode.ACCESS_DENIED.getErrorCode() + " Error message : "+ServiceErrorCode.ACCESS_DENIED.getErrorMessage()+" resetPasswordRequest " + resetPasswordRequest.getEmail());
			throw new UserServiceException(ServiceErrorCode.ACCESS_DENIED.getErrorMessage(),ServiceErrorCode.ACCESS_DENIED.getErrorCode());	
	   	
		}
				TempRecord tempRecord  = new TempRecord();
				tempRecord.setUserName(userAccountList.get(0).getUserName());
				tempRecord.setField("PASSWORD");
				tempRecord.setCode(Utility.generateVerifyToken());
				boolean doesPasswordUpdated = genericService.save(tempRecord);
				if(doesPasswordUpdated) {
				 String body ="Hi "+userAccountList.get(0).getName()+",<br>Your password reset code is "+tempRecord.getCode()+"<br>You can also reset your password by using below link <a href='https://exchangerpoint.com/user/resetpassword/"+tempRecord.getCode()+"/?email="+userAccountList.get(0).getEmail()+"'>CLICK HERE TO RESET</a><br> Best Regards,<br>ExchangerPoint Team";

			    mailService.sendMail("ExchangerPoint Password Reset Code", resetPasswordRequest.getEmail(), body);
               }
			
		  return doesPasswordUpdated;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean verifyResetPassword(ResetPasswordRequest resetPasswordRequest) throws UserServiceException {
		HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("email", resetPasswordRequest.getEmail());
		List<UserAccount> userAccountList = (List<UserAccount>) genericService.getObjectsByEqualityConditions(UserAccount.class, properties);
		
		
		if(userAccountList == null || userAccountList.size() ==0) {
		    	logger.error("Error code : " +ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode() + " Error message : "+ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorMessage()+" Email " + resetPasswordRequest.getEmail());
				throw new UserServiceException(ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorMessage(),ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode());	
		    }
	      
		properties = new HashMap<String, Object>();
        properties.put("field", "PASSWORD");
		properties.put("userName", userAccountList.get(0).getUserName());
		List<TempRecord> tempRecordList = (List<TempRecord>) genericService.getOrderedList(TempRecord.class, properties, "createdDate", "desc");
		
		if(tempRecordList == null || tempRecordList.size() ==0 || !tempRecordList.get(0).getCode().equalsIgnoreCase(resetPasswordRequest.getVerificationCode())|| !tempRecordList.get(0).getUserName().equalsIgnoreCase(userAccountList.get(0).getUserName()))
	    {	logger.error("Error code : " + ServiceErrorCode.INVALID_VERIFICATION_CODE.getErrorCode() + " Error message : "+ServiceErrorCode.INVALID_VERIFICATION_CODE.getErrorMessage() + " UserName " +userAccountList.get(0).getUserName());
		    throw new UserServiceException(ServiceErrorCode.INVALID_VERIFICATION_CODE.getErrorMessage(),ServiceErrorCode.INVALID_VERIFICATION_CODE.getErrorCode());	
	     } 
		
		if((userAccountList.get(0).getUserOperation()!= null && !userAccountList.get(0).getUserOperation().isUpdateProfile() )|| userAccountList.get(0).getAccountStatus().equalsIgnoreCase(ApplicationConstants.ACCOUNT_STATUS_BLOCKED) || userAccountList.get(0).getAccountStatus().equalsIgnoreCase(ApplicationConstants.ACCOUNT_STATUS_BLOCKED)) {
			logger.error("Error code : " +ServiceErrorCode.ACCESS_DENIED.getErrorCode() + " Error message : "+ServiceErrorCode.ACCESS_DENIED.getErrorMessage()+" resetPasswordRequest " + resetPasswordRequest.getEmail());
			throw new UserServiceException(ServiceErrorCode.ACCESS_DENIED.getErrorMessage(),ServiceErrorCode.ACCESS_DENIED.getErrorCode());	
	   	
		}
		UserAccount userAccount = userAccountList.get(0);
		String newPassword =passwordEncoder.encode(resetPasswordRequest.getNewPassword());
		userAccount.setPassword(newPassword);
		
		UserVerification userVerification = userAccount.getUserVerification();
		if(userVerification==null) {
		    userVerification = new UserVerification();
			userVerification.setUserName(userAccount.getUserName());
			userVerification.setEmail(true);
			userAccount.setUserVerification(userVerification);
			
			UserOperation userOperation = userAccount.getUserOperation();
			if(userOperation ==null && userAccount.getAccountStatus().equals(ApplicationConstants.ACCOUNT_STATUS_INACTIVE)) {
				userAccount.setAccountStatus(ApplicationConstants.ACCOUNT_STATUS_ACTIVE);
			}
		}
		genericService.delete(tempRecordList.get(0));
	    return genericService.update(userAccount);
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean activateUser(ActivateAccountRequest activateAccountRequest) throws UserServiceException {
        UserAccount userAccount = (UserAccount) genericService.getObjectById(UserAccount.class, activateAccountRequest.getUserName());
        
	    if(userAccount == null) {
	    	logger.error("Error code : " +ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode() + " Error message : "+ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorMessage() + " UserName " +activateAccountRequest.getUserName());
			throw new UserServiceException(ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorMessage(),ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode());	
	    }
	    
	    HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("field", "ACTIVATION");
		properties.put("userName", activateAccountRequest.getUserName());
		List<TempRecord> tempRecordList = (List<TempRecord>) genericService.getOrderedList(TempRecord.class, properties, "createdDate", "desc");
		
	    if(tempRecordList == null || tempRecordList.size() ==0 || !tempRecordList.get(0).getCode().equals(activateAccountRequest.getActivationCode())|| !tempRecordList.get(0).getUserName().equalsIgnoreCase(userAccount.getUserName()))
	    {	logger.error("Error code : " + ServiceErrorCode.INVALID_ACTIVATION_CODE.getErrorCode() + " Error message : "+ServiceErrorCode.INVALID_ACTIVATION_CODE.getErrorMessage() + " UserName " +activateAccountRequest.getUserName());
		    throw new UserServiceException(ServiceErrorCode.INVALID_ACTIVATION_CODE.getErrorMessage(),ServiceErrorCode.INVALID_ACTIVATION_CODE.getErrorCode());	
	     }
	   
	    
	    userAccount.setAccountStatus(ApplicationConstants.ACCOUNT_STATUS_ACTIVE);
	    UserVerification userVerification = new UserVerification();
		userVerification.setUserName(activateAccountRequest.getUserName());
		userVerification.setEmail(true);
		userAccount.setUserVerification(userVerification);
		genericService.delete(tempRecordList.get(0));
	    return genericService.update(userAccount);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean verifyIp(IpVerificationRequest ipVerificationRequest) throws UserServiceException {
        UserAccount userAccount = (UserAccount) genericService.getObjectById(UserAccount.class, ipVerificationRequest.getUserName());
        
	    if(userAccount == null) {
	    	logger.error("Error code : " +ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode() + " Error message : "+ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorMessage() + " UserName " +ipVerificationRequest.getUserName());
			throw new UserServiceException(ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorMessage(),ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode());	
	    }
	    
	    HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("field", "VERIFYIP");
		properties.put("userName", ipVerificationRequest.getUserName());
		List<TempRecord> tempRecordList = (List<TempRecord>) genericService.getOrderedList(TempRecord.class, properties, "createdDate", "desc");
		
	    if(tempRecordList == null || tempRecordList.size() ==0 || !tempRecordList.get(0).getCode().equalsIgnoreCase(ipVerificationRequest.getVerificationCode()) || !tempRecordList.get(0).getUserName().equalsIgnoreCase(userAccount.getUserName()))
	    {	logger.error("Error code : " + ServiceErrorCode.INVALID_ACTIVATION_CODE.getErrorCode() + " Error message : "+ServiceErrorCode.INVALID_ACTIVATION_CODE.getErrorMessage() + " UserName " +ipVerificationRequest.getUserName());
		    throw new UserServiceException(ServiceErrorCode.INVALID_ACTIVATION_CODE.getErrorMessage(),ServiceErrorCode.INVALID_ACTIVATION_CODE.getErrorCode());	
	     }
	   
	    
	    userAccount.setLastLoginIp(tempRecordList.get(0).getValue());
		genericService.delete(tempRecordList.get(0));
	    return genericService.update(userAccount);
	}
	
	@Override
	public boolean verifyIdentity(IdentityVerificationRequest identityVerificationRequest)
			throws UserServiceException {
		UserAccount userAccount = (UserAccount) genericService.getObjectById(UserAccount.class, identityVerificationRequest.getUserName());
        
	    if(userAccount == null) {
	    	logger.error("Error code : " +ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode() + " Error message : "+ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorMessage() + " IdentityVerificationRequest " +identityVerificationRequest.getUserName());
			throw new UserServiceException(ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorMessage(),ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode());	
	    }
	    
	    UserVerification userVerification = userAccount.getUserVerification();
	    
	    if(identityVerificationRequest.getId() !=2) {
	    	if(identityVerificationRequest.getId() == 1)
	    	{
	    		userVerification.setId(true);
	    		if(userAccount.getCountry().equalsIgnoreCase("INDIA") && (userAccount.getRazorpayAccounts() == null ||userAccount.getRazorpayAccounts().size()==0)) {
	    			try {
						RazorpayAccount razorpayAccount = walletService.createVirtualAccount(userAccount);
						List<RazorpayAccount> razorpayAccountList = new ArrayList<RazorpayAccount>();
						razorpayAccountList.add(razorpayAccount);
						userAccount.setRazorpayAccounts(razorpayAccountList);
	    			} catch (RazorpayException e) {
						e.printStackTrace();
					}
	    			
	    		}
	    	}
	    	else if(identityVerificationRequest.getId()==0)
	    	{
	    		 userVerification.setId(false);
	    		 if(userAccount.getCountry().equalsIgnoreCase("INDIA") && userAccount.getRazorpayAccounts() != null &&userAccount.getRazorpayAccounts().size()>0) {
	    			 List<RazorpayAccount> razorpayAccountList =userAccount.getRazorpayAccounts();
	    			 for(RazorpayAccount razorpayAccount : razorpayAccountList) {
	    				 razorpayAccount.setAccountStatus(ApplicationConstants.ACCOUNT_STATUS_INACTIVE);
	    			 }
		    			
		    		}
	    	}
	    }
		return false;
	}
	private List<PropertyConditionMatcher> prepareORConditionsList(String userName) {
		PropertyConditionMatcher userNameCondition = new PropertyConditionMatcher("userName", "==", userName);
		PropertyConditionMatcher emailCondition = new PropertyConditionMatcher("email", "==", userName);
		PropertyConditionMatcher phoneCondition = new PropertyConditionMatcher("phone", "==", userName);

		List<PropertyConditionMatcher> conditionsList = new ArrayList<PropertyConditionMatcher>();
		conditionsList.add(userNameCondition);
		conditionsList.add(emailCondition);
		conditionsList.add(phoneCondition);

		return conditionsList;
	}

	
	private UserAccount setWalletAndLimit(String country, String userName, UserAccount userAccount) {
		
		UserWallet userWallet = new UserWallet();
    	UserLimit userLimit = new UserLimit();
    	boolean doesCountryMatched = false;
    	FiatCurrency usdCurrency = null;
		
		for(FiatCurrency currency : ApplicationDataRepository.fiatCurrencyList) {
			if(currency.getCountry().equalsIgnoreCase(country)) {
				userWallet.setCurrencySymbol(currency.getCurrency());
			    
			    userLimit.setCurrency(currency.getCurrency());
			    userLimit.setDailyDepositLimit(currency.getDepositLimit());
			    userLimit.setDepositAllowedLimit(currency.getDepositLimit());
			    userLimit.setWithdrawAllowedLimit(currency.getWithdrawLimit());
			    userLimit.setDailyWithdrawLimit(currency.getWithdrawLimit());
			    doesCountryMatched= true;
			}
			if(currency.getCurrency().equals("USD")) {
				usdCurrency=currency;
			}
		}
		
    	if(!doesCountryMatched) {
    		userWallet.setCurrencySymbol(usdCurrency.getCurrency());
    		userLimit.setUserName(userName);
		    userLimit.setCurrency(usdCurrency.getCurrency());
		    userLimit.setDailyDepositLimit(usdCurrency.getDepositLimit());
		    userLimit.setDepositAllowedLimit(usdCurrency.getDepositLimit());
		    userLimit.setWithdrawAllowedLimit(usdCurrency.getWithdrawLimit());
		    userLimit.setDailyWithdrawLimit(usdCurrency.getWithdrawLimit());
    	}
    	
	    
		userWallet.setWalletStatus(ApplicationConstants.ACCOUNT_STATUS_ACTIVE);
		userWallet.setAvailableBalance(0);
		userWallet.setPendingBalance(0);
		userWallet.setReserveBalance(0);
		userWallet.setTotalBalance(0);
		userWallet.setUserName(userName);
		userWallet.setAutoConvert(false);
		userAccount.addUserWallet(userWallet);
	    
	    UserWallet userWallet2 = new UserWallet();
	    userWallet2.setCurrencySymbol("BTC");
	    userWallet2.setWalletStatus(ApplicationConstants.ACCOUNT_STATUS_ACTIVE);
	    userWallet2.setAvailableBalance(0);
	    userWallet2.setPendingBalance(0);
	    userWallet2.setReserveBalance(0);
	    userWallet2.setTotalBalance(0);
	    userWallet2.setAutoConvert(false);
	    userWallet2.setUserName(userName);
	    userWallet2.setUserAccount(userAccount);
	    userAccount.addUserWallet(userWallet2);
	    
	    
	    userAccount.addUserLimit(userLimit);
	    
	    return userAccount;
    }
	

	public void setGenericService(GenericService genericService) {
		this.genericService = genericService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}
	
	
	
	public void setMessagingService(MessagingService messagingService) {
		this.messagingService = messagingService;
	}



	public void setPasswordEncoder(DelegatingPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}



	public void setApplicationDataRepository(ApplicationDataRepository applicationDataRepository) {
		this.applicationDataRepository = applicationDataRepository;
	}



	public void setWalletService(WalletService walletService) {
		this.walletService = walletService;
	}


    
	
}
