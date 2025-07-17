package com.exchangerpoint.userservices.converter;

import com.exchangerpoint.commonservices.errorcode.RestErrorCode;
import com.exchangerpoint.commonservices.exception.RestException;
import com.exchangerpoint.databaseservices.entity.UserAccount;
import com.exchangerpoint.restspec.dto.user.UserResponse;
import com.exchangerpoint.restspec.dto.user.UsersResponse;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

public class UserResponseConverter {

	public static UserResponse UserResponse(UserAccount userAccount) throws RestException {
		UserResponse UserResponse =  new UserResponse();
		try {
			BeanUtils.copyProperties(UserResponse, userAccount);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RestException(RestErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage(),RestErrorCode.INTERNAL_SERVER_ERROR.getErrorCode());
		}
		return UserResponse;
	}

	public static UsersResponse UsersResponse(List<UserAccount> userAccountList) throws RestException {
		UsersResponse UsersResponse = new UsersResponse();
		for( UserAccount userAccount : userAccountList) {
		UserResponse UserResponse =  new UserResponse();
		try {
			BeanUtils.copyProperties(UserResponse, userAccount);
			UsersResponse.addUsersItem(UserResponse);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RestException(RestErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage(),RestErrorCode.INTERNAL_SERVER_ERROR.getErrorCode());
		}
		
		}
		return UsersResponse;
	}
}
