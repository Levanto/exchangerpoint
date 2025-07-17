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

import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.exchangerpoint.exchangeservices.common.ApplicationDataRepository;
import com.exchangerpoint.exchangeservices.common.Constants;
import com.exchangerpoint.exchangeservices.service.MailService;
import com.exchangerpoint.exchangeservices.utility.CryptoUtil;

/**
 * @author Govind
 *
 */

public class MailServiceImpl implements MailService{

	/**
	 * Log implementation for MailerService
	 */
	protected final Log logger = LogFactory.getLog(MailServiceImpl.class);
	
	@Autowired
	private ApplicationDataRepository applicationDataRepository;
	private Map<String, String> adminParamMap = applicationDataRepository.adminParamMap;
	
	public boolean sendMail(String subject, String recipient, String message,
			String siteName) {
		try {
			Session sessionMail = createMailSession();
			sessionMail.setDebug(true);

			// create a message
			Message msg = new MimeMessage(sessionMail);

			// set the from and to address

			InternetAddress addressFrom = new InternetAddress("support@"
					+ siteName, "Support");
			msg.setFrom(addressFrom);

			InternetAddress[] addressTo = new InternetAddress[1];
			addressTo[0] = new InternetAddress(recipient);
			msg.setRecipients(Message.RecipientType.TO, addressTo);

			// Optional : You can also set your custom headers in the Email if
			// you Want
			msg.addHeader("site", "www." + siteName);

			// Setting the Subject and Content Type
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setReplyTo(addressTo);

			msg.setContent(message, "text/html");

			Transport.send(msg);
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			logger.error("MailerService->Error to send mail to client "
					+ e.getMessage());
        return false;
		}

	}

	private Session createMailSession() {

		Properties props = new Properties();
		props.put("mail.smtp.host", CryptoUtil.decrypt(Constants.PLAY_BALL1, Constants.PLAY_BALL2, adminParamMap.get("mailservicesmtpserver")));
		props.put("mail.smtp.port", CryptoUtil.decrypt(Constants.PLAY_BALL1, Constants.PLAY_BALL2, adminParamMap.get("mailservicesmtpport")));
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", adminParamMap.get("mailservicesmtpstarttlsenable"));

		// set authentication and get the default Session
		return Session.getInstance(props, new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(CryptoUtil.decrypt(Constants.PLAY_BALL1, Constants.PLAY_BALL2, adminParamMap.get("mailserviceauthaddress")),
						CryptoUtil.decrypt(Constants.PLAY_BALL1, Constants.PLAY_BALL2, adminParamMap.get("mailserviceauthpassword")));
			}
		});
	}

	public void setApplicationDataRepository(ApplicationDataRepository applicationDataRepository) {
		this.applicationDataRepository = applicationDataRepository;
	}

}
