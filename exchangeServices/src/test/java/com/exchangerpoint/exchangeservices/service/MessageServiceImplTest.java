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

import com.exchangerpoint.exchangeservices.service.MessagingService;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@ContextConfiguration(locations = {"classpath:application-test-context.xml"})
public class MessageServiceImplTest {

	@Autowired
	private MessagingService messageService;
	
	private String recipient;
	private String message;
	
	@Before
	public void setup() {
		recipient = "+919599228227";
		message = "Message Services are working fantabulously.";
	}
	
	//@Ignore("Tested")
	@Test
	@Rollback(false)
	public void testSendSMS() {
		messageService.sendSMS(recipient, message);
	}
}
