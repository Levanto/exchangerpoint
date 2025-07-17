/*
 * @(#) MailServiceImpl.java
 *
 * Copyright 2012-2015 Levanto Technologies. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Levanto Technologies.
 * Any reproduction of this material must contain this notice.
 */

/*
 * � 2012-2015 Levanto Technologies. All Rights Reserved.
 */
package com.exchangerpoint.exchangeservices.service;
/**
 * @author Govind
 *
 */
public interface MailService {
	public boolean sendMail(String subject, String recipient, String message,
			String siteName);
}
