/**
 *
 */
package com.exchangerpoint.exchangeservices.dao;

import com.exchangerpoint.exchangeservices.bean.Order;

/**
 * @author admin
 */
public interface HomeDAO {

	Order getOrder(String orderNumber);
}
