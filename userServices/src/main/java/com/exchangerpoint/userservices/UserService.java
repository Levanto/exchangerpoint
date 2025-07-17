package com.exchangerpoint.userservices;

import java.util.List;

import com.exchangerpoint.databaseservices.entity.UserAccount;
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
import com.exchangerpoint.userservices.exception.UserServiceException;

public interface UserService {

	public UserAccount getUser(String userName);

	public List<UserAccount> getUserList();

	public boolean doesUserNameExist(String userName);

	public boolean doesEmailExist(String email);
	
	public boolean doesPhoneExist(String phone);

	public boolean addUser(AddUserRequest addUserRequest) throws UserServiceException;
	
	public boolean updateUser(UpdateUserRequest updateUserRequest) throws UserServiceException;
	
	public boolean deleteUser(String userName) throws UserServiceException;
	
	public boolean updatePassword(UpdatePasswordRequest updatePasswordRequest) throws UserServiceException;
	
	public boolean updateEmail(UpdateEmailRequest updateEmailRequest) throws UserServiceException;
	
	public boolean updatePhone(UpdatePhoneRequest updateEmailRequest) throws UserServiceException;

	public boolean activateUser(ActivateAccountRequest activateAccountRequest) throws UserServiceException;

	public boolean verifyIp(IpVerificationRequest ipVerificationRequest) throws UserServiceException;
	
	public boolean updatePin(UpdatePinRequest updatePinRequest) throws UserServiceException;

	public boolean verifyPhone(UpdatePhoneRequest updatePhoneRequest) throws UserServiceException;

	public boolean verifyEmail(UpdateEmailRequest updateEmailRequest) throws UserServiceException;

	public boolean verifyPin(UpdatePinRequest updatePinRequest) throws UserServiceException;

	public boolean resetPassword(ResetPasswordRequest resetPasswordRequest) throws UserServiceException;

	public boolean verifyResetPassword(ResetPasswordRequest resetPasswordRequest) throws UserServiceException;

	public boolean verifyIdentity(IdentityVerificationRequest identityVerificationRequest) throws UserServiceException;

	
	
	
	
	

}
