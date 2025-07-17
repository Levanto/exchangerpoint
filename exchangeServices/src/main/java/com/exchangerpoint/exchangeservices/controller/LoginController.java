/**
 *
 */
package com.exchangerpoint.exchangeservices.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.exchangerpoint.databaseservices.entity.User;
import com.exchangerpoint.exchangeservices.bean.LoginBean;
import com.exchangerpoint.exchangeservices.service.LoginService;
import com.exchangerpoint.exchangeservices.utility.Constants;

/**
 * @author admin
 *
 */
@Controller
public class LoginController {

	/**
	 * LoginService Object
	 */

	@Autowired
	@Qualifier(Constants.LOGINSERVICE)
	private LoginService loginService;

	/**
	 * Log implementation for LoginController
	 */
	protected final Log logger = LogFactory.getLog(LoginController.class);

	@RequestMapping(value = "/login.htm", method = RequestMethod.GET)
    public String getLogin(Model model, HttpSession session, HttpServletRequest request) {

		LoginBean loginBean=new LoginBean();
		model.addAttribute("LoginBean", loginBean);

		return "login";

    }

	@RequestMapping(value = "/login.htm", method = RequestMethod.POST)
    public  String doLogin(@ModelAttribute("LoginBean") LoginBean loginBean,Model model, HttpSession session, HttpServletRequest request) {

User userPersonal=loginService.doLogin(loginBean);

		if(userPersonal!=null)
		{
			if(!userPersonal.getStatus().equals("Blocked")){
		       if(!userPersonal.getStatus().equals("Deactive")){
			session=request.getSession();
			session.setAttribute("user", userPersonal);
			session.setMaxInactiveInterval(30*60*60);

			return "login";

			}
			else
			{
				session.setAttribute("resultMessage","You have not yet verified your email .<a href='resend.htm'>Click here</a>Resend the varification link");
				LoginBean loginBean1=new LoginBean();
				model.addAttribute("LoginBean", loginBean1);

				return "login";


			}


			}
		 else
		{
			session.setAttribute("resultMessage","Your account is blocked please contact admin");
			LoginBean loginBean1=new LoginBean();
			model.addAttribute("LoginBean", loginBean1);

			return "login";
			}
		}
		else
		{
			session.setAttribute("resultMessage","Invalid username or password");
			LoginBean loginBean1=new LoginBean();
			model.addAttribute("LoginBean", loginBean1);

			return "login";
		}
    }

	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
    public String logout(Model model, HttpSession session, HttpServletRequest request) {
    	try{

	  session.removeAttribute("user");
	  session.removeAttribute("UserBank");
	  session.removeAttribute("UserAccount");
	  session.invalidate();
    	}
	catch(Exception e)
	{
		e.printStackTrace();

	}
		return "logout";

    }

}
