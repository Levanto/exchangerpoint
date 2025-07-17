/**
 *
 */
package com.exchangerpoint.exchangeservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.exchangerpoint.databaseservices.entity.User;
import com.exchangerpoint.exchangeservices.bean.LoginBean;
import com.exchangerpoint.exchangeservices.dao.LoginDAO;
import com.exchangerpoint.exchangeservices.utility.Constants;

/**
 * @author Govind
 *
 */
public class LoginServiceImpl implements LoginService {
	@Autowired
	@Qualifier(Constants.LOGINDAO)
	public LoginDAO loginDAO;

	public LoginDAO getLoginDAO() {
		return loginDAO;
	}

	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

	public String doregister(User userPersonal) {

		return loginDAO.doregister(userPersonal);
	}

	public String checkUser(String user) {

		return loginDAO.checkUser(user);
	}

	public String checkEmaiId(String emailId) {
		return loginDAO.checkEmaiId(emailId);
	}

	public String verify(String userName, String verify) {
		return loginDAO.verify(userName,verify);
	}

	public User doLogin(LoginBean loginBean) {
		return loginDAO.doLogin(loginBean);
	}

	public User verifyEmaiId(String emailId) {
		return loginDAO.verifyEmaiId(emailId);
	}
  public User getUser(String user) {

		return loginDAO.getUser(user);
	}

	public String resetVerify(String userName, String verify) {
		return loginDAO.resetVerify(userName,verify);


	}

}
