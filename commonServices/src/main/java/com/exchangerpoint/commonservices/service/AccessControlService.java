package com.exchangerpoint.commonservices.service;

import com.exchangerpoint.commonservices.exception.AccessControlException;

public interface AccessControlService {

	
	
	void validateAccessControl(String email, String mobile, String ipAddress) throws AccessControlException;
}
