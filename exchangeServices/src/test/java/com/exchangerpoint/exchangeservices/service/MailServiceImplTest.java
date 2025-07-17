package com.exchangerpoint.exchangeservices.service;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.exchangerpoint.exchangeservices.service.MailService;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@ContextConfiguration(locations = {"classpath:application-test-context.xml"})
public class MailServiceImplTest {

	@Autowired
	private MailService mailService;
	
	private String subject;
	private String recipient;
	private String message;
	private String siteName;
	
	@Before
	public void setup() {
		subject = "Test";
		recipient = "sharma.vikrant28@gmail.com";
		message = "Mail Services are working absolutely perfect.";
		siteName = "ecurrencybasket.com";
	}
	
	@Ignore("Tested")
	@Test
	@Rollback(false)
	public void testSendMail() {
		mailService.sendMail(subject, recipient, message, siteName);
	}
}
