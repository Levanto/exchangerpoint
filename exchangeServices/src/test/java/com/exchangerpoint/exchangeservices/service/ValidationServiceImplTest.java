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

import com.exchangerpoint.exchangeservices.common.ErrorCodes;
import com.exchangerpoint.exchangeservices.exception.ExchangeException;
import com.exchangerpoint.exchangeservices.service.ValidationService;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@ContextConfiguration(locations = {"classpath:application-test-context.xml"})
public class ValidationServiceImplTest {

	@Autowired
	ValidationService validationService;
	
	@Before
	public void setup() {
		
	}
	
	@Ignore("Tested")
	@Test
	public void testValidEmail() {

		boolean emailValid = validationService.validateEmail("sharma.vikrant28@gmail.com");
		if (emailValid)
			System.out.println("\'sharma.vikrant28@gmail.com\' is a valid EMAIL.");
		Assert.assertTrue("Email is not valid !", emailValid);
	}
	
	@Ignore("Tested")
	@Test
	public void testInvalidEmail() {
	
		boolean emailValid = validationService.validateEmail("sharma.vikrant28@gmail.comcoom");
		if (!emailValid)
			System.out.println("\'sharma.vikrant28@gmail.comcoom\' is not a valid EMAIL.");
		Assert.assertFalse("Email id is valid", emailValid);
	}
	
	@Ignore("Tested")
	@Test
	public void testValidPhone() {
		
		boolean phoneValid = false;
		
		try {
			phoneValid = validationService.validatePhone("+918826655584");
		} catch (ExchangeException e) {
			e.printStackTrace();
		}
		
		if(phoneValid)
			System.out.println("\'+918826655584\' is a valid phone number.");
		Assert.assertTrue("\'+918826655584\' is an invalid number", phoneValid);
	}
	
	@Ignore("Tested")
	@Test
	public void testInvalidPhoneNumber() {
		boolean phoneValid = false;
		
		try {
			phoneValid = validationService.validatePhone("+9188df655584");
		} catch (ExchangeException e) {
			if(!phoneValid)
				System.out.println("\'+9188df655584\' is an invalid phone number.");
			Assert.assertFalse("\'+918826655584\' is a valid number", phoneValid);
		}
	}
	
	@Ignore("Tested")
	@Test
	public void testInvalidMobileLength() {
		boolean phoneValid = false;
		
		try {
			phoneValid = validationService.validatePhone("+9188");
		} catch (ExchangeException e) {
			if(!phoneValid)
				System.out.println("\'+9188\' is an invalid phone number.");
			Assert.assertFalse("\'+9188\' is a valid number", phoneValid);
		}
	}
	
	//@Ignore("Tested")
	@Test
	@Rollback(false)
	public void testBlockedEmail() {
		
		try {
			validationService.validateAccessControl("block.1@gmail.com", "+919922992299", "123.2.3.4");
		} catch (ExchangeException e) {
			Assert.assertEquals("Access is valid", e.getErrorCode(), ErrorCodes.USER_BLOCKED);
		}
	}
	
	@Ignore("Tested")
	@Test
	@Rollback(false)
	public void testBlockedIPAddress() {
		
		try {
			validationService.validateAccessControl("bl12344@gmail.com", "+919922992299", "9.10.11.12");
		} catch (ExchangeException e) {
			Assert.assertEquals("Access is valid", e.getErrorCode(), ErrorCodes.USER_BLOCKED);
		}
	}
	
	@Ignore("Tested")
	@Test
	@Rollback(false)
	public void testBlockedPhoneNumber() {
		
		try {
			validationService.validateAccessControl("bl12344@gmail.com", "+91888845678	", "9.134.11.12");
		} catch (ExchangeException e) {
			Assert.assertEquals("Access is valid", e.getErrorCode(), ErrorCodes.USER_BLOCKED);
		}
	}
	
	@Ignore("Tested")
	@Test
	@Rollback(false)
	public void testValidateAccessControl() {
	}

	public void setValidationService(ValidationService validationService) {
		this.validationService = validationService;
	}
}
