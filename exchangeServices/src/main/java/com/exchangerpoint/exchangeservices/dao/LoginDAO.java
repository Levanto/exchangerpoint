/**
 * 
 */
package com.exchangerpoint.exchangeservices.dao;

import com.exchangerpoint.databaseservices.entity.User;
import com.exchangerpoint.exchangeservices.bean.LoginBean;

/**
 * @author govind
 *
 */
public interface LoginDAO {
	public String resetVerify(String userName, String verify);

	String doregister(User userPersonal);
    String checkUser(String user);
	String checkEmaiId(String emailId);
	String verify(String userName, String verify);
	User doLogin(LoginBean loginBean);
	User verifyEmaiId(String emailId);
	User getUser(String user);
}
