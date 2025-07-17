/**
 *
 */
package com.exchangerpoint.exchangeservices.utility;


import java.util.Date;
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


/**
 * @author admin
 *
 */

public class Mailer {

	/**
	 * Log implementation for LoginController
	 */
	protected final Log logger = LogFactory.getLog(Mailer.class);

	    private String result=null;

		public String writeMail(String name,String subject,String email,String message,String siteName,String smtpServer,String smtpPort,final String authAddress,final String authPassword ){




			siteName=siteName.replaceAll("www.","");

		      try{
			                Properties props = new Properties();
			                props.put("mail.smtp.host", smtpServer);
			                props.put("mail.smtp.port", smtpPort);
			                props.put("mail.smtp.auth", "true");
			                props.put("mail.smtp.starttls.enable","true");


			            // create some properties and get the default Session
			            Session sessionMail = Session.getInstance(props, new Authenticator() {
			                 public PasswordAuthentication getPasswordAuthentication() {
			                         return new PasswordAuthentication(authAddress, authPassword);
			                 }
			                });

			            sessionMail.setDebug(true);

			            // create a message
			            Message msg = new MimeMessage(sessionMail);

			            // set the from and to address
			            InternetAddress addressFrom = new InternetAddress(Constants.supportAddress,name);
			            msg.setFrom(addressFrom);
			            InternetAddress[] replyTo = new InternetAddress[1];
			            replyTo[0]=new InternetAddress(email,name);
			            msg.setReplyTo(replyTo);

			            InternetAddress[] addressTo = new InternetAddress[1];
			            addressTo[0] = new InternetAddress(Constants.gmailId+"@gmail.com");
			            msg.setRecipients(Message.RecipientType.TO, addressTo);

			            // Optional : You can also set your custom headers in the Email if you Want
			            msg.addHeader("site", siteName);

			            // Setting the Subject and Content Type
			            msg.setSubject(subject);
			            msg.setSentDate(new Date());


			            msg.setContent(message, "text/html");

			            Transport.send(msg);
			            result="success";
			        }catch(Exception e){
			                e.printStackTrace();
			                result="error";
			                logger.error("Mailer->Error to send mail to admin "+e.getMessage());

			        }
		return result;
		}



		public String sendMail(String name,String subject,String email,String message,String siteName,String smtpServer,String smtpPort,final String authAddress,final String authPassword ,final String fromAddress){






			 siteName=siteName.replaceAll("www.","");

		      try{
			                Properties props = new Properties();
			                props.put("mail.smtp.host", smtpServer);
			                props.put("mail.smtp.port", smtpPort);
			                props.put("mail.smtp.auth", "true");
			                props.put("mail.smtp.starttls.enable","true");

			            // create some properties and get the default Session
			            Session sessionMail = Session.getInstance(props, new Authenticator() {
			                 public PasswordAuthentication getPasswordAuthentication() {
			                         return new PasswordAuthentication(authAddress, authPassword);
			                 }
			                });

			            sessionMail.setDebug(true);

			            // create a message
			            Message msg = new MimeMessage(sessionMail);

			            // set the from and to address

			            InternetAddress addressFrom = new InternetAddress(fromAddress,name);
			            msg.setFrom(addressFrom);

			            InternetAddress[] addressTo = new InternetAddress[1];
			            addressTo[0] = new InternetAddress(email);
			            msg.setRecipients(Message.RecipientType.TO, addressTo);


			            // Optional : You can also set your custom headers in the Email if you Want
			            msg.addHeader("site", siteName);

			            // Setting the Subject and Content Type
			            msg.setSubject(subject);
			            msg.setSentDate(new Date());


			            msg.setContent(message, "text/html");

			            Transport.send(msg);
			            result="success";
			        }catch(Exception e){
			                e.printStackTrace();
			                result="error";
			                logger.error("Mailer->Error to send mail to client "+e.getMessage());

			        }
		return result;
		}



}
