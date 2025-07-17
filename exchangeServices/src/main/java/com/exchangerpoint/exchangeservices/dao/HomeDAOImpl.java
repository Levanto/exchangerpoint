/**
 *
 */
package com.exchangerpoint.exchangeservices.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.exchangerpoint.databaseservices.entity.BuyOrder;
import com.exchangerpoint.databaseservices.entity.ExchangeOrder;
import com.exchangerpoint.databaseservices.entity.SellOrder;
import com.exchangerpoint.exchangeservices.bean.Order;
import com.exchangerpoint.exchangeservices.common.ApplicationDataRepository;
import com.exchangerpoint.exchangeservices.utility.Constants;
import com.exchangerpoint.genericservices.service.GenericService;

/**
 * @author admin
 *
 */
@Transactional
public class HomeDAOImpl implements HomeDAO {
	@Autowired
	@Qualifier(Constants.SESSIONFACTORY)
	public SessionFactory sessionFactory;
	
	@Autowired
	private ApplicationDataRepository applicationDataRepository;
	
	@Autowired
	private GenericService genericService;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void setApplicationDataRepository(ApplicationDataRepository applicationDataRepository) {
		this.applicationDataRepository = applicationDataRepository;
	}

	public void setGenericService(GenericService genericService) {
		this.genericService = genericService;
	}

	@SuppressWarnings("static-access")
	public Order getOrder(String orderNumber) {
		try {
			Order order = new Order();

			ExchangeOrder exchangeOrder = (ExchangeOrder) genericService.getObjectById(ExchangeOrder.class, orderNumber);
			
			if (exchangeOrder != null)
			{
                if (exchangeOrder.getStatus().equals("PENDING"))
                	exchangeOrder.setAdminAccount(applicationDataRepository.adminEcurrencyAccountIdMap.get(exchangeOrder.getSentEcurrency()));

				order.setExchangeOrder(exchangeOrder);
			}
			else
			{
				SellOrder sellOrder = (SellOrder) genericService.getObjectById(SellOrder.class, orderNumber);

				if(sellOrder != null)
				{
					if(sellOrder.getStatus().equals("PENDING"))
						sellOrder.setAdminAccount(applicationDataRepository.adminEcurrencyAccountIdMap.get(sellOrder.getEcurrencyType()));
					
					order.setSellOrder(sellOrder);
				}
				else
				{
					BuyOrder buyOrder = (BuyOrder) genericService.getObjectById(BuyOrder.class, orderNumber);

					if(buyOrder != null)
					{
						order.setBuyOrder(buyOrder);
					}
					else
						return null;
				}
			}

			return order;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
