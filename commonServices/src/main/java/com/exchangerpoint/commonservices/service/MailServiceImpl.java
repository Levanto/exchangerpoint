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
 * 2012-2015 Levanto Technologies. All Rights Reserved.
 */
package com.exchangerpoint.commonservices.service;

import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.exchangerpoint.commonservices.common.ApplicationDataRepository;
import com.exchangerpoint.commonservices.constant.ApplicationConstants;
import com.exchangerpoint.commonservices.utility.CryptoUtil;


/**
 * @author Govind
 *
 */

public class MailServiceImpl implements MailService{

	/**
	 * Log implementation for MailerService
	 */
	protected final Log logger = LogFactory.getLog(MailServiceImpl.class);
	
	@SuppressWarnings("unused")
	@Autowired
	private ApplicationDataRepository applicationDataRepository;
	private Map<String, String> adminParamMap = ApplicationDataRepository.adminParamMap;

	public boolean sendMail(String subject, String recipient, String message) {
		
		
		Transport transport = null;
		try {
			
			Session session = createMailSession();
			session.setDebug(true);

			// create a message
			Message msg = new MimeMessage(session);

			// set the from and to address

			InternetAddress addressFrom = new InternetAddress("support@exchangerpoint.com", "Support");
			msg.setFrom(addressFrom);

			InternetAddress[] addressTo = new InternetAddress[1];
			addressTo[0] = new InternetAddress(recipient);
			msg.setRecipients(Message.RecipientType.TO, addressTo);

			// Optional : You can also set your custom headers in the Email if
			// you Want
			msg.addHeader("site", "www.exchangerpoint.com");

			// Setting the Subject and Content Type
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setReplyTo(addressTo);

			msg.setContent(message, "text/html");

			transport = session.getTransport("smtps");
			transport.connect(CryptoUtil.decrypt(ApplicationConstants.PLAY_BALL1, ApplicationConstants.PLAY_BALL2, adminParamMap.get("mailservicesmtpserver")), CryptoUtil.decrypt(ApplicationConstants.PLAY_BALL1, ApplicationConstants.PLAY_BALL2, adminParamMap.get("mailserviceauthaddress")), CryptoUtil.decrypt(ApplicationConstants.PLAY_BALL1, ApplicationConstants.PLAY_BALL2, adminParamMap.get("mailserviceauthpassword")));
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
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
		props.put("mail.smtp.host", CryptoUtil.decrypt(ApplicationConstants.PLAY_BALL1, ApplicationConstants.PLAY_BALL2, adminParamMap.get("mailservicesmtpserver")));
		props.put("mail.smtp.port", CryptoUtil.decrypt(ApplicationConstants.PLAY_BALL1, ApplicationConstants.PLAY_BALL2, adminParamMap.get("mailservicesmtpport")));
		props.put("mail.tranport.protocol", "smtps");
		props.put("mail.smtps.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.debug", "true");
		props.put("mail.smtps.ssl.trust", "*");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

		// set authentication and get the default Session
		Session session = Session.getInstance(props, null);
		session.setDebug(true);
		return session;
	}

	public void setApplicationDataRepository(ApplicationDataRepository applicationDataRepository) {
		this.applicationDataRepository = applicationDataRepository;
	}

}

