package com.exchangerpoint.restservices.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.exchangerpoint.commonservices.errorcode.RestErrorCode;
import com.exchangerpoint.commonservices.errorcode.ServiceErrorCode;
import com.exchangerpoint.commonservices.exception.AccessControlException;
import com.exchangerpoint.commonservices.exception.RestException;
import com.exchangerpoint.commonservices.service.AccessControlService;
import com.exchangerpoint.commonservices.validator.CommonValidator;
import com.exchangerpoint.databaseservices.entity.UserAccount;
import com.exchangerpoint.userservices.UserService;
import com.exchangerpoint.userservices.converter.UserResponseConverter;
import com.exchangerpoint.userservices.exception.UserServiceException;
import com.exchangerpoint.restservices.service.RestService;
import com.exchangerpoint.restservices.validator.UserValidator;
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
import com.exchangerpoint.restspec.dto.user.UserResponse;
import com.exchangerpoint.restspec.dto.user.UsersResponse;


@RestController
public class UserRestController extends BaseRestController{
	
	@SuppressWarnings("unused")
	@Autowired
	private RestService restService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccessControlService accessControlService;
	
	
	protected final Log logger = LogFactory.getLog(UserRestController.class);

	@CrossOrigin
	@GetMapping("/users")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
	public ResponseEntity<UsersResponse> getUsers() throws RestException {
		
		List<UserAccount> userAccountList = userService.getUserList();
		if(userAccountList == null) {
			logger.error("Error Code : " + RestErrorCode.NO_USERS_EXIST.getErrorCode() + " Error Message : " + RestErrorCode.NO_USERS_EXIST.getErrorMessage());
			throw new RestException(RestErrorCode.NO_USERS_EXIST.getErrorMessage(),RestErrorCode.NO_USERS_EXIST.getErrorCode());
		}
		UsersResponse UsersResponse = UserResponseConverter.UsersResponse(userAccountList);
	    return new ResponseEntity<UsersResponse>(UsersResponse, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping("/users/{userName}")
	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<UserResponse> getUser(@PathVariable String userName) throws RestException {
		
		if(CommonValidator.isBlank(userName)) {
			logger.error("Error Code : " + RestErrorCode.NO_USERNAME_SUPPLIED.getErrorCode() + " Error Message : " + RestErrorCode.NO_USERNAME_SUPPLIED.getErrorMessage() +" GetUser : " + userName);
			throw new RestException(RestErrorCode.NO_USERNAME_SUPPLIED.getErrorMessage(),RestErrorCode.NO_USERNAME_SUPPLIED.getErrorCode());
		}
		
		validateUserAccess(userName);
		
		
		UserAccount userAccount = userService.getUser(userName);
		if(userAccount == null) {
			logger.error("Error Code : " + RestErrorCode.USER_DOES_NOT_EXIST.getErrorCode() + " Error Message : " + RestErrorCode.USER_DOES_NOT_EXIST.getErrorMessage() +" UserName : " + userName);
			throw new RestException(RestErrorCode.USER_DOES_NOT_EXIST.getErrorMessage(),RestErrorCode.USER_DOES_NOT_EXIST.getErrorCode());
		}
		UserResponse UserResponse =  UserResponseConverter.UserResponse(userAccount);
	    return new ResponseEntity<UserResponse>(UserResponse, HttpStatus.OK);
	}
	
	@CrossOrigin
	@DeleteMapping("/users/{userName}")
	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable String userName) throws RestException {
		
		if(CommonValidator.isBlank(userName)) {
			logger.error("Error Code : " + RestErrorCode.NO_USERNAME_SUPPLIED.getErrorCode() + " Error Message : " + RestErrorCode.NO_USERNAME_SUPPLIED.getErrorMessage() +" GetUser : " + userName);
			throw new RestException(RestErrorCode.NO_USERNAME_SUPPLIED.getErrorMessage(),RestErrorCode.NO_USERNAME_SUPPLIED.getErrorCode());
		}
		
		validateUserAccess(userName);
		try {
		boolean isUserDeleted = userService.deleteUser(userName);
		if(isUserDeleted == false) {
			logger.error("Error Code : " + RestErrorCode.USER_DOES_NOT_EXIST.getErrorCode() + " Error Message : " + RestErrorCode.USER_DOES_NOT_EXIST.getErrorMessage() +" UserName : " + userName);
			throw new RestException(RestErrorCode.USER_DOES_NOT_EXIST.getErrorMessage(),RestErrorCode.USER_DOES_NOT_EXIST.getErrorCode());
		}
		
		} catch (UserServiceException exception) {
			if(exception.getErrorCode() == ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.USER_DOES_NOT_EXIST.getErrorCode() + " Error Message : " + RestErrorCode.USER_DOES_NOT_EXIST.getErrorMessage() +" UserName : " + userName);
				throw new RestException(RestErrorCode.USER_DOES_NOT_EXIST.getErrorMessage(),RestErrorCode.USER_DOES_NOT_EXIST.getErrorCode());   	
			}
			if(exception.getErrorCode() == ServiceErrorCode.ACCESS_DENIED.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.ACCESS_DENIED.getErrorCode() + " Error Message : " + RestErrorCode.ACCESS_DENIED.getErrorMessage() +" UserName : " + userName);
				throw new RestException(RestErrorCode.ACCESS_DENIED.getErrorMessage(),RestErrorCode.ACCESS_DENIED.getErrorCode());   	
			}
			
		}
		
	}
	
	@CrossOrigin
	@PostMapping("/users/create/")
	@ResponseStatus(HttpStatus.CREATED)
	public void addUser(@RequestBody AddUserRequest addUserRequest, HttpServletRequest request) throws RestException {
		
		try {
			accessControlService.validateAccessControl(addUserRequest.getEmail(),"",CommonValidator.getCustomerRequestIpAddress(request));
		}catch (AccessControlException e) {
			logger.error("Error Code : " + RestErrorCode.ACCESS_DENIED.getErrorCode() + " Error Message : " + RestErrorCode.ACCESS_DENIED.getErrorMessage() +" AddUserRequest : " + addUserRequest);
			throw new RestException(RestErrorCode.ACCESS_DENIED.getErrorMessage(),RestErrorCode.ACCESS_DENIED.getErrorCode());		
		
		}
		UserValidator.addUserRequestValidator(addUserRequest);
		
		try {
		boolean doesUserCreated = userService.addUser(addUserRequest);
		if(!doesUserCreated) {
			logger.error("Error Code : " + RestErrorCode.INTERNAL_SERVER_ERROR.getErrorCode() + " Error Message : " + RestErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage() +" AddUserRequest : " + addUserRequest);
			throw new RestException(RestErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage(),RestErrorCode.INTERNAL_SERVER_ERROR.getErrorCode());		
		}
		} catch (UserServiceException exception) {
			if(exception.getErrorCode() == ServiceErrorCode.USERNAME_ALREADY_TAKEN.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.USERNAME_ALREADY_TAKEN.getErrorCode() + " Error Message : " + RestErrorCode.USERNAME_ALREADY_TAKEN.getErrorMessage() +" UserName : " + addUserRequest.getUserName());
				throw new RestException(RestErrorCode.USERNAME_ALREADY_TAKEN.getErrorMessage(),RestErrorCode.USERNAME_ALREADY_TAKEN.getErrorCode());   	
			}
			if(exception.getErrorCode() == ServiceErrorCode.EMAIL_ID_ALREADY_EXIST.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.EMAIL_ID_ALREADY_EXIST.getErrorCode() + " Error Message : " + RestErrorCode.EMAIL_ID_ALREADY_EXIST.getErrorMessage() +" UserName : " + addUserRequest.getUserName());
				throw new RestException(RestErrorCode.EMAIL_ID_ALREADY_EXIST.getErrorMessage(),RestErrorCode.EMAIL_ID_ALREADY_EXIST.getErrorCode());   	
			}
		}
		
	}

	//post
	@CrossOrigin
	@PostMapping("/users/update/")
	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateUser(@RequestBody UpdateUserRequest updateUserRequest) throws RestException {
		
		UserValidator.updateUserRequestValidator(updateUserRequest);
		validateUserAccess(updateUserRequest.getUserName());
		
		try {
			boolean doesUserUpdated = userService.updateUser(updateUserRequest);
			
			if(!doesUserUpdated) {
				logger.error(RestErrorCode.INTERNAL_SERVER_ERROR);
				throw new RestException(RestErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage(),RestErrorCode.INTERNAL_SERVER_ERROR.getErrorCode());		
			}
		} catch (UserServiceException exception) {
			if(exception.getErrorCode() == ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.USER_DOES_NOT_EXIST.getErrorCode() + " Error Message : " + RestErrorCode.USER_DOES_NOT_EXIST.getErrorMessage() +" UserName : " + updateUserRequest.getUserName());
				throw new RestException(RestErrorCode.USER_DOES_NOT_EXIST.getErrorMessage(),RestErrorCode.USER_DOES_NOT_EXIST.getErrorCode());   	
			}
			if(exception.getErrorCode() == ServiceErrorCode.ACCESS_DENIED.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.ACCESS_DENIED.getErrorCode() + " Error Message : " + RestErrorCode.ACCESS_DENIED.getErrorMessage() +" UserName : " + updateUserRequest.getUserName());
				throw new RestException(RestErrorCode.ACCESS_DENIED.getErrorMessage(),RestErrorCode.ACCESS_DENIED.getErrorCode());   	
			}
		}
	
	}
	
	//post
	@CrossOrigin
	@PostMapping("/users/update/password/")
	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest) throws RestException {
		
		UserValidator.updatePasswordRequestValidator(updatePasswordRequest);
		validateUserAccess(updatePasswordRequest.getUserName());
		
		try {
			boolean doesUserUpdated = userService.updatePassword(updatePasswordRequest);
			
			if(!doesUserUpdated) {
				logger.error(RestErrorCode.INTERNAL_SERVER_ERROR);
				throw new RestException(RestErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage(),RestErrorCode.INTERNAL_SERVER_ERROR.getErrorCode());		
			}
		} catch (UserServiceException exception) {
			if(exception.getErrorCode() == ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.USER_DOES_NOT_EXIST.getErrorCode() + " Error Message : " + RestErrorCode.USER_DOES_NOT_EXIST.getErrorMessage() +" UserName : " + updatePasswordRequest.getUserName());
				throw new RestException(RestErrorCode.USER_DOES_NOT_EXIST.getErrorMessage(),RestErrorCode.USER_DOES_NOT_EXIST.getErrorCode());   	
			}
			if(exception.getErrorCode() == ServiceErrorCode.OLD_PASSWORD_NOT_MATCH.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.OLD_PASSWORD_NOT_MATCH.getErrorCode() + " Error Message : " + RestErrorCode.OLD_PASSWORD_NOT_MATCH.getErrorMessage() +" UserName : " + updatePasswordRequest.getUserName());
				throw new RestException(RestErrorCode.OLD_PASSWORD_NOT_MATCH.getErrorMessage(),RestErrorCode.OLD_PASSWORD_NOT_MATCH.getErrorCode());   	
			}
			if(exception.getErrorCode() == ServiceErrorCode.ACCESS_DENIED.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.ACCESS_DENIED.getErrorCode() + " Error Message : " + RestErrorCode.ACCESS_DENIED.getErrorMessage() +" UserName : " + updatePasswordRequest.getUserName());
				throw new RestException(RestErrorCode.ACCESS_DENIED.getErrorMessage(),RestErrorCode.ACCESS_DENIED.getErrorCode());   	
			}
		}
	
	}
	
	//post
	@CrossOrigin
	@PostMapping("/users/reset/password/")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePassword(@RequestBody ResetPasswordRequest resetPasswordRequest) throws RestException {
		
		UserValidator.resetPasswordRequestValidator(resetPasswordRequest);
		try {
			accessControlService.validateAccessControl(resetPasswordRequest.getEmail(),null,null);
		}catch (AccessControlException e) {
			logger.error("Error Code : " + RestErrorCode.ACCESS_DENIED.getErrorCode() + " Error Message : " + RestErrorCode.ACCESS_DENIED.getErrorMessage() +" resetPasswordRequest : " + resetPasswordRequest);
			throw new RestException(RestErrorCode.ACCESS_DENIED.getErrorMessage(),RestErrorCode.ACCESS_DENIED.getErrorCode());		
		
		}
		
		try {
			boolean doesUserUpdated = userService.resetPassword(resetPasswordRequest);
			
			if(!doesUserUpdated) {
				logger.error(RestErrorCode.INTERNAL_SERVER_ERROR);
				throw new RestException(RestErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage(),RestErrorCode.INTERNAL_SERVER_ERROR.getErrorCode());		
			}
		} catch (UserServiceException exception) {
			if(exception.getErrorCode() == ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.NO_USERS_EXIST.getErrorCode() + " Error Message : " + RestErrorCode.NO_USERS_EXIST.getErrorMessage() +" resetPasswordRequest : " + resetPasswordRequest);
				throw new RestException(RestErrorCode.NO_USERS_EXIST.getErrorMessage(),RestErrorCode.NO_USERS_EXIST.getErrorCode());   	
			}
			if(exception.getErrorCode() == ServiceErrorCode.ACCESS_DENIED.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.ACCESS_DENIED.getErrorCode() + " Error Message : " + RestErrorCode.ACCESS_DENIED.getErrorMessage() +" Email : " + resetPasswordRequest.getEmail());
				throw new RestException(RestErrorCode.ACCESS_DENIED.getErrorMessage(),RestErrorCode.ACCESS_DENIED.getErrorCode());   	
			}
			
		}
	
	}
	
	//post
	@CrossOrigin
	@PostMapping("/users/verify/password/")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void verifyPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) throws RestException {
		
		UserValidator.verifyPasswordRequestValidator(resetPasswordRequest);
		try {
			accessControlService.validateAccessControl(resetPasswordRequest.getEmail(),null,null);
		}catch (AccessControlException e) {
			logger.error("Error Code : " + RestErrorCode.ACCESS_DENIED.getErrorCode() + " Error Message : " + RestErrorCode.ACCESS_DENIED.getErrorMessage() +" resetPasswordRequest : " + resetPasswordRequest);
			throw new RestException(RestErrorCode.ACCESS_DENIED.getErrorMessage(),RestErrorCode.ACCESS_DENIED.getErrorCode());		
		
		}
		
		try {
			boolean doesUserUpdated = userService.verifyResetPassword(resetPasswordRequest);
			
			if(!doesUserUpdated) {
				logger.error(RestErrorCode.INTERNAL_SERVER_ERROR);
				throw new RestException(RestErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage(),RestErrorCode.INTERNAL_SERVER_ERROR.getErrorCode());		
			}
		} catch (UserServiceException exception) {
			if(exception.getErrorCode() == ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.NO_USERS_EXIST.getErrorCode() + " Error Message : " + RestErrorCode.NO_USERS_EXIST.getErrorMessage() +" resetPasswordRequest : " + resetPasswordRequest);
				throw new RestException(RestErrorCode.NO_USERS_EXIST.getErrorMessage(),RestErrorCode.NO_USERS_EXIST.getErrorCode());   	
			}
			
			if(exception.getErrorCode() == ServiceErrorCode.INVALID_VERIFICATION_CODE.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.INVALID_VERIFICATION_CODE.getErrorCode() + " Error Message : " + RestErrorCode.INVALID_VERIFICATION_CODE.getErrorMessage() +" resetPasswordRequest : " + resetPasswordRequest);
				throw new RestException(RestErrorCode.INVALID_VERIFICATION_CODE.getErrorMessage(),RestErrorCode.INVALID_VERIFICATION_CODE.getErrorCode());   	
			}
			
			if(exception.getErrorCode() == ServiceErrorCode.ACCESS_DENIED.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.ACCESS_DENIED.getErrorCode() + " Error Message : " + RestErrorCode.ACCESS_DENIED.getErrorMessage() +" Email : " + resetPasswordRequest.getEmail());
				throw new RestException(RestErrorCode.ACCESS_DENIED.getErrorMessage(),RestErrorCode.ACCESS_DENIED.getErrorCode());   	
			}
			
		}
	
	}
	
	//post
	@CrossOrigin
	@PostMapping("/users/update/email/")
	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateEmail(@RequestBody UpdateEmailRequest updateEmailRequest) throws RestException {
		
		UserValidator.updateEmailRequestValidator(updateEmailRequest);
		try {
			accessControlService.validateAccessControl(updateEmailRequest.getEmail(),null,null);
		}catch (AccessControlException e) {
			logger.error("Error Code : " + RestErrorCode.ACCESS_DENIED.getErrorCode() + " Error Message : " + RestErrorCode.ACCESS_DENIED.getErrorMessage() +" updateEmailRequest : " + updateEmailRequest);
			throw new RestException(RestErrorCode.ACCESS_DENIED.getErrorMessage(),RestErrorCode.ACCESS_DENIED.getErrorCode());		
		
		}
		validateUserAccess(updateEmailRequest.getUserName());
		
		try {
			boolean doesUserUpdated = userService.updateEmail(updateEmailRequest);
			
			if(!doesUserUpdated) {
				logger.error(RestErrorCode.INTERNAL_SERVER_ERROR);
				throw new RestException(RestErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage(),RestErrorCode.INTERNAL_SERVER_ERROR.getErrorCode());		
			}
		} catch (UserServiceException exception) {
			if(exception.getErrorCode() == ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.USER_DOES_NOT_EXIST.getErrorCode() + " Error Message : " + RestErrorCode.USER_DOES_NOT_EXIST.getErrorMessage() +" UserName : " + updateEmailRequest.getUserName());
				throw new RestException(RestErrorCode.USER_DOES_NOT_EXIST.getErrorMessage(),RestErrorCode.USER_DOES_NOT_EXIST.getErrorCode());   	
			}
			if(exception.getErrorCode() == ServiceErrorCode.PIN_DOES_NOT_MATCH.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.PIN_DOES_NOT_MATCH.getErrorCode() + " Error Message : " + RestErrorCode.PIN_DOES_NOT_MATCH.getErrorMessage() +" UserName : " + updateEmailRequest.getUserName());
				throw new RestException(RestErrorCode.PIN_DOES_NOT_MATCH.getErrorMessage(),RestErrorCode.PIN_DOES_NOT_MATCH.getErrorCode());   	
			}
			
			if(exception.getErrorCode() == ServiceErrorCode.EMAIL_ID_ALREADY_EXIST.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.EMAIL_ID_ALREADY_EXIST.getErrorCode() + " Error Message : " + RestErrorCode.EMAIL_ID_ALREADY_EXIST.getErrorMessage() +" UserName : " + updateEmailRequest.getUserName());
				throw new RestException(RestErrorCode.EMAIL_ID_ALREADY_EXIST.getErrorMessage(),RestErrorCode.EMAIL_ID_ALREADY_EXIST.getErrorCode());   	
			}
			if(exception.getErrorCode() == ServiceErrorCode.ACCESS_DENIED.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.ACCESS_DENIED.getErrorCode() + " Error Message : " + RestErrorCode.ACCESS_DENIED.getErrorMessage() +" UserName : " + updateEmailRequest.getUserName());
				throw new RestException(RestErrorCode.ACCESS_DENIED.getErrorMessage(),RestErrorCode.ACCESS_DENIED.getErrorCode());   	
			}
			
		}
	
	}
	
	//post
	@CrossOrigin
	@PostMapping("/users/verify/email/")
	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void verifyEmail(@RequestBody UpdateEmailRequest updateEmailRequest) throws RestException {
		
		
		validateUserAccess(updateEmailRequest.getUserName());
		
		try {
			boolean doesUserUpdated = userService.verifyEmail(updateEmailRequest);
			
			if(!doesUserUpdated) {
				logger.error(RestErrorCode.INTERNAL_SERVER_ERROR);
				throw new RestException(RestErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage(),RestErrorCode.INTERNAL_SERVER_ERROR.getErrorCode());		
			}
		} catch (UserServiceException exception) {
			if(exception.getErrorCode() == ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.USER_DOES_NOT_EXIST.getErrorCode() + " Error Message : " + RestErrorCode.USER_DOES_NOT_EXIST.getErrorMessage() +" UserName : " + updateEmailRequest.getUserName());
				throw new RestException(RestErrorCode.USER_DOES_NOT_EXIST.getErrorMessage(),RestErrorCode.USER_DOES_NOT_EXIST.getErrorCode());   	
			}
			if(exception.getErrorCode() == ServiceErrorCode.EMAIL_ID_ALREADY_EXIST.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.EMAIL_ID_ALREADY_EXIST.getErrorCode() + " Error Message : " + RestErrorCode.EMAIL_ID_ALREADY_EXIST.getErrorMessage() +" UserName : " + updateEmailRequest.getUserName());
				throw new RestException(RestErrorCode.EMAIL_ID_ALREADY_EXIST.getErrorMessage(),RestErrorCode.EMAIL_ID_ALREADY_EXIST.getErrorCode());   	
			}
			
			
			if(exception.getErrorCode() == ServiceErrorCode.INVALID_VERIFICATION_CODE.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.INVALID_VERIFICATION_CODE.getErrorCode() + " Error Message : " + RestErrorCode.INVALID_VERIFICATION_CODE.getErrorMessage() +" username : " + updateEmailRequest.getUserName());
				throw new RestException(RestErrorCode.INVALID_VERIFICATION_CODE.getErrorMessage(),RestErrorCode.INVALID_VERIFICATION_CODE.getErrorCode());   	
			}
			if(exception.getErrorCode() == ServiceErrorCode.ACCESS_DENIED.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.ACCESS_DENIED.getErrorCode() + " Error Message : " + RestErrorCode.ACCESS_DENIED.getErrorMessage() +" UserName : " + updateEmailRequest.getUserName());
				throw new RestException(RestErrorCode.ACCESS_DENIED.getErrorMessage(),RestErrorCode.ACCESS_DENIED.getErrorCode());   	
			}
		}
	
	}
	
	//post
	@CrossOrigin
	@PostMapping("/users/update/phone/")
	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePhone(@RequestBody UpdatePhoneRequest updatePhoneRequest) throws RestException {
		
		UserValidator.updatePhoneRequestValidator(updatePhoneRequest);
		try {
			accessControlService.validateAccessControl(null,updatePhoneRequest.getPhone(),null);
		}catch (AccessControlException e) {
			logger.error("Error Code : " + RestErrorCode.ACCESS_DENIED.getErrorCode() + " Error Message : " + RestErrorCode.ACCESS_DENIED.getErrorMessage() +" updateEmailRequest : " + updatePhoneRequest);
			throw new RestException(RestErrorCode.ACCESS_DENIED.getErrorMessage(),RestErrorCode.ACCESS_DENIED.getErrorCode());		
		
		}
		validateUserAccess(updatePhoneRequest.getUserName());
		
		try {
			boolean doesUserUpdated = userService.updatePhone(updatePhoneRequest);
			
			if(!doesUserUpdated) {
				logger.error(RestErrorCode.INTERNAL_SERVER_ERROR);
				throw new RestException(RestErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage(),RestErrorCode.INTERNAL_SERVER_ERROR.getErrorCode());		
			}
		} catch (UserServiceException exception) {
			if(exception.getErrorCode() == ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.USER_DOES_NOT_EXIST.getErrorCode() + " Error Message : " + RestErrorCode.USER_DOES_NOT_EXIST.getErrorMessage() +" UserName : " + updatePhoneRequest.getUserName());
				throw new RestException(RestErrorCode.USER_DOES_NOT_EXIST.getErrorMessage(),RestErrorCode.USER_DOES_NOT_EXIST.getErrorCode());   	
			}
			
			if(exception.getErrorCode() == ServiceErrorCode.PIN_DOES_NOT_MATCH.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.PIN_DOES_NOT_MATCH.getErrorCode() + " Error Message : " + RestErrorCode.PIN_DOES_NOT_MATCH.getErrorMessage() +" UserName : " + updatePhoneRequest.getUserName());
				throw new RestException(RestErrorCode.PIN_DOES_NOT_MATCH.getErrorMessage(),RestErrorCode.PIN_DOES_NOT_MATCH.getErrorCode());   	
			}
			
			if(exception.getErrorCode() == ServiceErrorCode.PHONE_ALREADY_REGISTERED.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.PHONE_ALREADY_REGISTERED.getErrorCode() + " Error Message : " + RestErrorCode.PHONE_ALREADY_REGISTERED.getErrorMessage() +" UserName : " + updatePhoneRequest.getUserName());
				throw new RestException(RestErrorCode.PHONE_ALREADY_REGISTERED.getErrorMessage(),RestErrorCode.PHONE_ALREADY_REGISTERED.getErrorCode());   	
			}
			if(exception.getErrorCode() == ServiceErrorCode.ACCESS_DENIED.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.ACCESS_DENIED.getErrorCode() + " Error Message : " + RestErrorCode.ACCESS_DENIED.getErrorMessage() +" UserName : " + updatePhoneRequest.getUserName());
				throw new RestException(RestErrorCode.ACCESS_DENIED.getErrorMessage(),RestErrorCode.ACCESS_DENIED.getErrorCode());   	
			}
		}
	
	}
	
	//post
	@CrossOrigin
	@PostMapping("/users/verify/phone/")
	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void verifyPhone(@RequestBody UpdatePhoneRequest updatePhoneRequest) throws RestException {
		
		
		validateUserAccess(updatePhoneRequest.getUserName());
		
		try {
			boolean doesUserUpdated = userService.verifyPhone(updatePhoneRequest);
			
			if(!doesUserUpdated) {
				logger.error(RestErrorCode.INTERNAL_SERVER_ERROR);
				throw new RestException(RestErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage(),RestErrorCode.INTERNAL_SERVER_ERROR.getErrorCode());		
			}
		} catch (UserServiceException exception) {
			if(exception.getErrorCode() == ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.USER_DOES_NOT_EXIST.getErrorCode() + " Error Message : " + RestErrorCode.USER_DOES_NOT_EXIST.getErrorMessage() +" UserName : " + updatePhoneRequest.getUserName());
				throw new RestException(RestErrorCode.USER_DOES_NOT_EXIST.getErrorMessage(),RestErrorCode.USER_DOES_NOT_EXIST.getErrorCode());   	
			}
			
			if(exception.getErrorCode() == ServiceErrorCode.PHONE_ALREADY_REGISTERED.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.PHONE_ALREADY_REGISTERED.getErrorCode() + " Error Message : " + RestErrorCode.PHONE_ALREADY_REGISTERED.getErrorMessage() +" UserName : " + updatePhoneRequest.getUserName());
				throw new RestException(RestErrorCode.PHONE_ALREADY_REGISTERED.getErrorMessage(),RestErrorCode.PHONE_ALREADY_REGISTERED.getErrorCode());   	
			}
			
			if(exception.getErrorCode() == ServiceErrorCode.INVALID_VERIFICATION_CODE.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.INVALID_VERIFICATION_CODE.getErrorCode() + " Error Message : " + RestErrorCode.INVALID_VERIFICATION_CODE.getErrorMessage() +" username : " + updatePhoneRequest.getUserName());
				throw new RestException(RestErrorCode.INVALID_VERIFICATION_CODE.getErrorMessage(),RestErrorCode.INVALID_VERIFICATION_CODE.getErrorCode());   	
			}
			
			if(exception.getErrorCode() == ServiceErrorCode.ACCESS_DENIED.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.ACCESS_DENIED.getErrorCode() + " Error Message : " + RestErrorCode.ACCESS_DENIED.getErrorMessage() +" UserName : " + updatePhoneRequest.getUserName());
				throw new RestException(RestErrorCode.ACCESS_DENIED.getErrorMessage(),RestErrorCode.ACCESS_DENIED.getErrorCode());   	
			}
			
		}
	
	}
	
	//post
	@CrossOrigin
	@PostMapping("/users/update/pin/")
	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePin(@RequestBody UpdatePinRequest updatePinRequest) throws RestException {
		
		UserValidator.updatePinRequestValidator(updatePinRequest);
		
		validateUserAccess(updatePinRequest.getUserName());
		
		try {
			boolean doesUserUpdated = userService.updatePin(updatePinRequest);
			
			if(!doesUserUpdated) {
				logger.error(RestErrorCode.INTERNAL_SERVER_ERROR);
				throw new RestException(RestErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage(),RestErrorCode.INTERNAL_SERVER_ERROR.getErrorCode());		
			}
		} catch (UserServiceException exception) {
			if(exception.getErrorCode() == ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.USER_DOES_NOT_EXIST.getErrorCode() + " Error Message : " + RestErrorCode.USER_DOES_NOT_EXIST.getErrorMessage() +" UserName : " + updatePinRequest.getUserName());
				throw new RestException(RestErrorCode.USER_DOES_NOT_EXIST.getErrorMessage(),RestErrorCode.USER_DOES_NOT_EXIST.getErrorCode());   	
			}
			if(exception.getErrorCode() == ServiceErrorCode.NEW_PIN_MATCHES_OLD_PIN.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.NEW_PIN_MATCHES_OLD_PIN.getErrorCode() + " Error Message : " + RestErrorCode.NEW_PIN_MATCHES_OLD_PIN.getErrorMessage() +" UserName : " + updatePinRequest.getUserName());
				throw new RestException(RestErrorCode.NEW_PIN_MATCHES_OLD_PIN.getErrorMessage(),RestErrorCode.NEW_PIN_MATCHES_OLD_PIN.getErrorCode());   	
			}
			if(exception.getErrorCode() == ServiceErrorCode.NO_PHONE_UPDATED.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.NO_PHONE_UPDATED.getErrorCode() + " Error Message : " + RestErrorCode.NO_PHONE_UPDATED.getErrorMessage() +" UserName : " + updatePinRequest.getUserName());
				throw new RestException(RestErrorCode.NO_PHONE_UPDATED.getErrorMessage(),RestErrorCode.NO_PHONE_UPDATED.getErrorCode());   	
			}
			if(exception.getErrorCode() == ServiceErrorCode.ACCESS_DENIED.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.ACCESS_DENIED.getErrorCode() + " Error Message : " + RestErrorCode.ACCESS_DENIED.getErrorMessage() +" UserName : " + updatePinRequest.getUserName());
				throw new RestException(RestErrorCode.ACCESS_DENIED.getErrorMessage(),RestErrorCode.ACCESS_DENIED.getErrorCode());   	
			}
			
		}
	
	}
	
	//post
	@CrossOrigin
	@PostMapping("/users/verify/pin/")
	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void verifyPin(@RequestBody UpdatePinRequest updatePinRequest) throws RestException {
		
		validateUserAccess(updatePinRequest.getUserName());
		
		try {
			boolean doesUserUpdated = userService.verifyPin(updatePinRequest);
			
			if(!doesUserUpdated) {
				logger.error(RestErrorCode.INTERNAL_SERVER_ERROR);
				throw new RestException(RestErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage(),RestErrorCode.INTERNAL_SERVER_ERROR.getErrorCode());		
			}
		} catch (UserServiceException exception) {
			if(exception.getErrorCode() == ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.USER_DOES_NOT_EXIST.getErrorCode() + " Error Message : " + RestErrorCode.USER_DOES_NOT_EXIST.getErrorMessage() +" UserName : " + updatePinRequest.getUserName());
				throw new RestException(RestErrorCode.USER_DOES_NOT_EXIST.getErrorMessage(),RestErrorCode.USER_DOES_NOT_EXIST.getErrorCode());   	
			}
			if(exception.getErrorCode() == ServiceErrorCode.INVALID_VERIFICATION_CODE.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.INVALID_VERIFICATION_CODE.getErrorCode() + " Error Message : " + RestErrorCode.INVALID_VERIFICATION_CODE.getErrorMessage() +" username : " + updatePinRequest.getUserName());
				throw new RestException(RestErrorCode.INVALID_VERIFICATION_CODE.getErrorMessage(),RestErrorCode.INVALID_VERIFICATION_CODE.getErrorCode());   	
			}
			
			if(exception.getErrorCode() == ServiceErrorCode.ACCESS_DENIED.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.ACCESS_DENIED.getErrorCode() + " Error Message : " + RestErrorCode.ACCESS_DENIED.getErrorMessage() +" UserName : " + updatePinRequest.getUserName());
				throw new RestException(RestErrorCode.ACCESS_DENIED.getErrorMessage(),RestErrorCode.ACCESS_DENIED.getErrorCode());   	
			}
		}
	
	}
	
	
	
	//put
	@CrossOrigin
	@PostMapping("/users/verify/ip/")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void verifyIp(@RequestBody IpVerificationRequest ipVerificationRequest) throws RestException {
		
		if(CommonValidator.isBlank(ipVerificationRequest.getUserName())) {
			logger.error("Error Code : " + RestErrorCode.NO_USERNAME_SUPPLIED.getErrorCode() + " Error Message : " + RestErrorCode.NO_USERNAME_SUPPLIED.getErrorMessage() +" UserName : " + ipVerificationRequest.getUserName());
			throw new RestException(RestErrorCode.NO_USERNAME_SUPPLIED.getErrorMessage(),RestErrorCode.NO_USERNAME_SUPPLIED.getErrorCode());
		}
		
		
		try {
			boolean doesIpVerified = userService.verifyIp(ipVerificationRequest);
			
			
			if(!doesIpVerified) {
				logger.error(RestErrorCode.INTERNAL_SERVER_ERROR);
				throw new RestException(RestErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage(),RestErrorCode.INTERNAL_SERVER_ERROR.getErrorCode());		
			}
		} catch (UserServiceException exception) {
			if(exception.getErrorCode() == ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.USER_DOES_NOT_EXIST.getErrorCode() + " Error Message : " + RestErrorCode.USER_DOES_NOT_EXIST.getErrorMessage() +" UserName : " + ipVerificationRequest.getUserName());
				throw new RestException(RestErrorCode.USER_DOES_NOT_EXIST.getErrorMessage(),RestErrorCode.USER_DOES_NOT_EXIST.getErrorCode());   	
			}
			
			if(exception.getErrorCode() == ServiceErrorCode.INVALID_ACTIVATION_CODE.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.INVALID_ACTIVATION_CODE.getErrorCode() + " Error Message : " + RestErrorCode.INVALID_ACTIVATION_CODE.getErrorMessage() +" UserName : " + ipVerificationRequest.getUserName());
				throw new RestException(RestErrorCode.INVALID_ACTIVATION_CODE.getErrorMessage(),RestErrorCode.INVALID_ACTIVATION_CODE.getErrorCode());   	
			}
		}
	
	}
	
	
	//post
	@CrossOrigin
	@PostMapping("/users/activate/")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void activateAccount(@RequestBody ActivateAccountRequest activateAccountRequest) throws RestException {
		
		if(CommonValidator.isBlank(activateAccountRequest.getUserName())) {
			logger.error("Error Code : " + RestErrorCode.NO_USERNAME_SUPPLIED.getErrorCode() + " Error Message : " + RestErrorCode.NO_USERNAME_SUPPLIED.getErrorMessage() +" UserName : " + activateAccountRequest.getUserName());
			throw new RestException(RestErrorCode.NO_USERNAME_SUPPLIED.getErrorMessage(),RestErrorCode.NO_USERNAME_SUPPLIED.getErrorCode());
		}
		
		
		try {
			boolean doesActivated = userService.activateUser(activateAccountRequest);
			
			
			if(!doesActivated) {
				logger.error(RestErrorCode.INTERNAL_SERVER_ERROR);
				throw new RestException(RestErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage(),RestErrorCode.INTERNAL_SERVER_ERROR.getErrorCode());		
			}
		} catch (UserServiceException exception) {
			if(exception.getErrorCode() == ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.USER_DOES_NOT_EXIST.getErrorCode() + " Error Message : " + RestErrorCode.USER_DOES_NOT_EXIST.getErrorMessage() +" UserName : " + activateAccountRequest.getUserName());
				throw new RestException(RestErrorCode.USER_DOES_NOT_EXIST.getErrorMessage(),RestErrorCode.USER_DOES_NOT_EXIST.getErrorCode());   	
			}
			
			if(exception.getErrorCode() == ServiceErrorCode.INVALID_ACTIVATION_CODE.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.INVALID_ACTIVATION_CODE.getErrorCode() + " Error Message : " + RestErrorCode.INVALID_ACTIVATION_CODE.getErrorMessage() +" UserName : " + activateAccountRequest.getUserName());
				throw new RestException(RestErrorCode.INVALID_ACTIVATION_CODE.getErrorMessage(),RestErrorCode.INVALID_ACTIVATION_CODE.getErrorCode());   	
			}
		}
	
	}
	
	//post
	@CrossOrigin
	@PostMapping("/users/verify/identity/")
	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void verifyId(@RequestBody IdentityVerificationRequest identityVerificationRequest) throws RestException {
		
		if(CommonValidator.isBlank(identityVerificationRequest.getUserName())) {
			logger.error("Error Code : " + RestErrorCode.NO_USERNAME_SUPPLIED.getErrorCode() + " Error Message : " + RestErrorCode.NO_USERNAME_SUPPLIED.getErrorMessage() +" IdentityVerificationRequest : " + identityVerificationRequest.getUserName());
			throw new RestException(RestErrorCode.NO_USERNAME_SUPPLIED.getErrorMessage(),RestErrorCode.NO_USERNAME_SUPPLIED.getErrorCode());
		}
		
		
		try {
			boolean doesIdentityVerified = userService.verifyIdentity(identityVerificationRequest);
			
			
			if(!doesIdentityVerified) {
				logger.error(RestErrorCode.INTERNAL_SERVER_ERROR);
				throw new RestException(RestErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage(),RestErrorCode.INTERNAL_SERVER_ERROR.getErrorCode());		
			}
		} catch (UserServiceException exception) {
			if(exception.getErrorCode() == ServiceErrorCode.USER_DOES_NOT_EXIST.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.USER_DOES_NOT_EXIST.getErrorCode() + " Error Message : " + RestErrorCode.USER_DOES_NOT_EXIST.getErrorMessage() +" IdentityVerificationRequest : " + identityVerificationRequest.getUserName());
				throw new RestException(RestErrorCode.USER_DOES_NOT_EXIST.getErrorMessage(),RestErrorCode.USER_DOES_NOT_EXIST.getErrorCode());   	
			}
			
			
		}
	
	}
	
	
	public void setRestService(RestService restService) {
		this.restService = restService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setAccessControlService(AccessControlService accessControlService) {
		this.accessControlService = accessControlService;
	}

	

}
