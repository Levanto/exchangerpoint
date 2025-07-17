/**
 *
 */
package com.exchangerpoint.exchangeservices.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.exchangerpoint.databaseservices.entity.User;
import com.exchangerpoint.exchangeservices.bean.LoginBean;
import com.exchangerpoint.exchangeservices.utility.Constants;
import com.exchangerpoint.exchangeservices.utility.HashCodeVerify;

/**
 * @author govind
 *
 */
@Transactional
public class LoginDAOImpl implements LoginDAO {

	@Autowired
	@Qualifier(Constants.SESSIONFACTORY)
	public SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public String doregister(User userPersonal) {
		try{
			sessionFactory.getCurrentSession().save(userPersonal);
            return "success";
		}catch(Exception e)
		{   e.printStackTrace();
			return "error";
		}

	}

	public String checkUser(String userName) {
		try{
			User userPersonal=null;
			userPersonal=(User) sessionFactory.getCurrentSession().get(User.class,userName);



			if(userPersonal!=null)
			{return "error";}
			else
				{return "success";}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "error";
		}
	}
	public String checkEmaiId(String emailId) {
		try{
			User userPersonal=new User();
		Criteria cr=sessionFactory.getCurrentSession().createCriteria(User.class);
		cr.add(Restrictions.eq("emailId", emailId));
	    userPersonal=(User) cr.uniqueResult();
		if(userPersonal!=null)
			{return "error";}
			else
				{return "success";}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "error";
		}
	}

	public String verify(String userName, String verify) {

		try {
			if(userName!=null&&verify!=null)
			{ try{
				User userPersonal=new User();
				userPersonal=(User) sessionFactory.getCurrentSession().get(User.class,userName);
			    if(userPersonal!=null)
			    {
			    if(userPersonal.getVerifyCode().equals(verify))
				{
			    if(!userPersonal.getStatus().equals("Blocked")){
			    userPersonal.setStatus("Active");
				userPersonal.setVerifyCode(null);
			    sessionFactory.getCurrentSession().saveOrUpdate(userPersonal);
			    	return "You have successfully activated you account .Please  login to your account";
				}
			    else
			    	return "Your account is blocked by admin .Please contact administrator by clicking on 'Contact Us' link ";
				}
			    else
			    	return "Verifiction code is expired or request is invalid";
			    }
			    else
			    	return "Verifiction code is expired or request is invalid";
			    }catch(Exception e)
			{   e.printStackTrace();
				return "Verifiction code is expired or request is invalid";
			}

			}else

				return "Verifiction code is expired or request is invalid";
		} catch (Exception e) {

			e.printStackTrace();
			return "Verifiction code is expired or request is invalid";
		}
	}

	public User doLogin(LoginBean loginBean) {
		try{
		if(loginBean.getPassword()!=null&&loginBean.getUserName()!=null){

			User userPersonal=new User();
			userPersonal=(User) sessionFactory.getCurrentSession().get(User.class,loginBean.getUserName());
			if(userPersonal!=null){
			HashCodeVerify hashCodeVerify=new HashCodeVerify();
			String password=hashCodeVerify.getEncryption(loginBean.getPassword());
			if(userPersonal.getPassword().toLowerCase().equals(password))
				return userPersonal;
			else

				return null;

		}
		else
		{
			return null;
		}

		}
		else
			return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public User verifyEmaiId(String emailId) {
		try{
			User userPersonal=new User();
		Criteria cr=sessionFactory.getCurrentSession().createCriteria(User.class);
		cr.add(Restrictions.eq("emailId", emailId));
	    userPersonal=(User) cr.uniqueResult();
		if(userPersonal!=null)
			return userPersonal;
		 else
			return null;

		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public String resetVerify(String userName, String verify) {

		try {
			if(userName!=null&&verify!=null)
			{ try{
				User userPersonal=new User();
				userPersonal=(User) sessionFactory.getCurrentSession().get(User.class,userName);
			    if(userPersonal!=null)
			    {if(userPersonal.getVerifyCode()!=null){
			    if(userPersonal.getVerifyCode().equals(verify))
				{
			    if(!userPersonal.getStatus().equals("Blocked")){

				userPersonal.setVerifyCode(null);
			    sessionFactory.getCurrentSession().saveOrUpdate(userPersonal);
			    	return "success";
				}
			    else
			    	return "Your account is blocked by admin .Please contact administrator by clicking on 'Contact Us' link ";
				}
			    else
			    	return "Verifiction code is expired or request is invalid";
			    }
			    else
			    	return "Verifiction code is expired or request is invalid";
			    }
			    else
			    	return "Verifiction code is expired or request is invalid";

			    }catch(Exception e)
			{   e.printStackTrace();
				return "Verifiction code is expired or request is invalid";
			}

			}else

				return "Verifiction code is expired or request is invalid";
		} catch (Exception e) {

			e.printStackTrace();
			return "Verifiction code is expired or request is invalid";
		}
	}
	public User getUser(String user) {
		try{
			User userPersonal=null;
			userPersonal=(User) sessionFactory.getCurrentSession().get(User.class,user);



		return userPersonal;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
