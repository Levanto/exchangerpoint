/**
 *
 */
package com.exchangerpoint.exchangeservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.exchangerpoint.exchangeservices.bean.Order;
import com.exchangerpoint.exchangeservices.dao.HomeDAO;
import com.exchangerpoint.exchangeservices.utility.Constants;

/**
 * @author admin
 */
public class HomeServiceImpl implements HomeService {
	@Autowired
	@Qualifier(Constants.HOMEDAO)
	public HomeDAO homeDAO;

	public HomeDAO getHomeDAO() {
		return homeDAO;
	}

	public void setHomeDAO(HomeDAO homeDAO) {
		this.homeDAO = homeDAO;
	}

	public Order getOrder(String orderNumber) {
		return homeDAO.getOrder(orderNumber);
	}
}
