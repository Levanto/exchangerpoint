package com.exchangerpoint.exchangeservices.controller;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import com.exchangerpoint.databaseservices.entity.BuyOrder;
import com.exchangerpoint.databaseservices.entity.ExchangeOrder;
import com.exchangerpoint.databaseservices.entity.SellOrder;
import com.exchangerpoint.exchangeservices.common.ApplicationDataRepository;
import com.exchangerpoint.exchangeservices.controller.HomeController;
import com.exchangerpoint.exchangeservices.service.MailService;
import com.exchangerpoint.exchangeservices.service.MessagingService;
import com.exchangerpoint.exchangeservices.service.ValidationService;
import com.exchangerpoint.genericservices.service.GenericService;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@ContextConfiguration(locations = {"classpath:application-test-context.xml"})
public class HomeControllerTest {

	private HomeController homeController;
	private ExchangeOrder exchangeOrder;
	private SellOrder sellOrder;
	private BuyOrder buyOrder;
	private Model model;
	private HttpServletRequest request;
	private HttpSession session;
	
	private String testExchangeId;
	private String testSellId;
	private String testBuyId;

	@Autowired
	@Qualifier(com.exchangerpoint.genericservices.util.Constants.GENERIC_SERVICE)
	private GenericService genericService;

	@Autowired
	private ApplicationDataRepository applicationDataRepository;

	@Autowired
	private MessagingService messagingService;

	@Autowired
	private MailService mailService;
	
	@Autowired
	private ValidationService validationService;
	
	
	@Before
	public void setup() {
		applicationDataRepository.init();
		
		homeController = new HomeController();
		homeController.setApplicationDataRepository(applicationDataRepository);
		homeController.setGenericService(genericService);
		homeController.setMailService(mailService);
		homeController.setMessagingService(messagingService);
		homeController.setValidationService(validationService);
		
		model = new Model() {
			
			ModelMap mm = new ModelMap();
			
			
			public Model mergeAttributes(Map<String, ?> arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
		
			public boolean containsAttribute(String arg0) {
				// TODO Auto-generated method stub
				return false;
			}
			
			
			public Map<String, Object> asMap() {
				// TODO Auto-generated method stub
				return null;
			}
			
			
			public Model addAttribute(String arg0, Object arg1) {
				mm.addAttribute(arg0, arg1);
				return (Model) mm;
			}
			
		
			public Model addAttribute(Object arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			
			public Model addAllAttributes(Map<String, ?> arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			
			public Model addAllAttributes(Collection<?> arg0) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		request = new MockHttpServletRequest();
		session = request.getSession();
	}
	
	@Ignore
	@Test
	@Rollback(false)
	public void testExchangeSuccess() {
		loadExchangeOrder();
		request.setAttribute("orderNumber", testExchangeId);
		//request.getParameterMap().put("orderNumber", "12345678");
		session.setAttribute(testExchangeId, exchangeOrder);		
		String returnedString = homeController.exchangeSuccess(exchangeOrder, model, session, request);
	}
	
	@Ignore
	@Test
	@Rollback(false)
	public void testSellSuccess() {
		loadSellOrder();
		request.setAttribute("orderNumber", testSellId);
		session.setAttribute(testSellId, sellOrder);
		String returnedString = homeController.sellSuccess(sellOrder, model, session, request);
		System.out.println("Returned String: " + returnedString);
	}
	
	@Ignore
	@Test
	//@Transactional
	@Rollback(false)
	public void testBuySuccess() {
		loadExchangeOrder();
		request.setAttribute("orderNumber", testExchangeId);
		//request.getParameterMap().put("orderNumber", "12345678");
		session.setAttribute(testExchangeId, exchangeOrder);
		String returnedString = homeController.exchangeSuccess(exchangeOrder, model, session, request);
	}
	
	private void loadExchangeOrder() {
		testExchangeId = "test1234";
		exchangeOrder = new ExchangeOrder();
		exchangeOrder.setEmailId("sharma.vikrant28@gmail.com");
		exchangeOrder.setPhone("+918826655584");
		exchangeOrder.setExchangeId(testExchangeId);
		exchangeOrder.setSentEcurrency("Bitcoin");
		exchangeOrder.setReceivedEcurrency("Payza");
		exchangeOrder.setReceivedAmount("100");
		exchangeOrder.setEwalletId("noEwallet");
		exchangeOrder.setExchangeDate(new Date());
		exchangeOrder.setStatus("PENDING");
		exchangeOrder.setExchangeAmount("20");
	}

	private void loadSellOrder() {
		testSellId = "testSell12";
		sellOrder = new SellOrder();
		sellOrder.setEmailId("sharma.vikrant28@gmail.com");
		sellOrder.setPhone("+918826655584");
		sellOrder.setSellId(testSellId);
		sellOrder.setEcurrencyType("Bitcoin");
		sellOrder.setLocalCurrency("Payza");
		sellOrder.setReceivedAmount("100");
		//sellOrder.setEwalletId("noEwallet");
		sellOrder.setSellDate(new Date());
		sellOrder.setStatus("PENDING");
		sellOrder.setSellAmount("35");
	}
	
	private void loadBuyOrder() {
		testExchangeId = "test1234";
		exchangeOrder = new ExchangeOrder();
		exchangeOrder.setEmailId("sharma.vikrant28@gmail.com");
		exchangeOrder.setPhone("+918826655584");
		exchangeOrder.setExchangeId(testExchangeId);
		exchangeOrder.setSentEcurrency("Bitcoin");
		exchangeOrder.setReceivedEcurrency("Payza");
		exchangeOrder.setReceivedAmount("100");
		exchangeOrder.setEwalletId("noEwallet");
		exchangeOrder.setExchangeDate(new Date());
		exchangeOrder.setStatus("PENDING");
		exchangeOrder.setExchangeAmount("20");
	}
	
	public void setGenericService(GenericService genericService) {
		this.genericService = genericService;
	}

	public void setApplicationDataRepository(ApplicationDataRepository applicationDataRepository) {
		this.applicationDataRepository = applicationDataRepository;
	}

	public void setMessagingService(MessagingService messagingService) {
		this.messagingService = messagingService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public void setValidationService(ValidationService validationService) {
		this.validationService = validationService;
	}
}
