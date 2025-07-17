/**
 *
 */
package com.exchangerpoint.exchangeservices.service;

import com.exchangerpoint.exchangeservices.bean.Order;

/**
 * @author admin
 */
public interface HomeService {

	public Order getOrder(String orderNumber);
}
