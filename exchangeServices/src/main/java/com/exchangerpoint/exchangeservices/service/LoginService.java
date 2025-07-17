/**
 * 
 */
package com.exchangerpoint.exchangeservices.service;

import com.exchangerpoint.databaseservices.entity.User;
import com.exchangerpoint.exchangeservices.bean.LoginBean;

/**
 * @author admin
 *
 */
public interface LoginService {
	public String resetVerify(String userName, String verify);
	User getUser(String user);
	String doregister(User userPersonal);
	String checkUser(String user);
	String checkEmaiId(String emailId);
	String verify(String userName, String verify);
	
	User doLogin(LoginBean loginBean);
	User verifyEmaiId(String emailId);

}
