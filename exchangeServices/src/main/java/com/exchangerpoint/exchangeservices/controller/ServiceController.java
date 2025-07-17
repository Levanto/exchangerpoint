package com.exchangerpoint.exchangeservices.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.exchangerpoint.databaseservices.entity.BuyOrder;
import com.exchangerpoint.databaseservices.entity.ExchangeOrder;
import com.exchangerpoint.databaseservices.entity.SellOrder;
import com.exchangerpoint.exchangeservices.service.MailService;
import com.exchangerpoint.exchangeservices.service.MessagingService;
import com.exchangerpoint.exchangeservices.utility.OrderType;
import com.exchangerpoint.genericservices.service.GenericService;


@RestController
public class ServiceController {
	
	private String facebookReviewMessage="Please write a nice review on our facebook page https://www.facebook.com/pg/exchangerpoint/reviews<br> Thank you <br> ExchangerPoint Team";
	private String clientEmailSubject=null;
	private String clientEmail=null;
	private String clientMessage=null;
	private String clientPhone=null;
	private String clientSMS=null;
	private boolean isMailSent = false;
	private boolean isSMSSent = false;
	
	@Autowired
	@Qualifier(com.exchangerpoint.genericservices.util.Constants.GENERIC_SERVICE)
	private GenericService genericService;

	@Autowired
	private MessagingService messagingService;

	@Autowired
	private MailService mailService;
	
	public void setGenericService(GenericService genericService) {
		this.genericService = genericService;
	}

	public void setMessagingService(MessagingService messagingService) {
		this.messagingService = messagingService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	@RequestMapping(value = "/updateorder/{type}/{orderId}/{status}", method = RequestMethod.GET)
	public @ResponseBody String updateOrderStatus(@PathVariable String type, @PathVariable String orderId, @PathVariable String status, HttpSession session, HttpServletRequest request) {

		boolean isUpdated = false;		
		
		OrderType orderType = OrderType.valueOf(type);
		
		switch(orderType) {
		
		 case exchange :			
			ExchangeOrder exchangeOrder = (ExchangeOrder) genericService.getObjectById(ExchangeOrder.class, orderId);
			if(exchangeOrder!= null && exchangeOrder.getStatus().equalsIgnoreCase("PENDING"))
			{exchangeOrder.setStatus(status);
			isUpdated = genericService.update(exchangeOrder) ;
			clientEmailSubject= "Exchange order #[" + orderId +"] COMPLETED on exchangerpoint.com";
			clientEmail =exchangeOrder.getEmailId();
			
			clientMessage="Hi, <br> Thank you for your order on www.exchangerpoint.com . Your order "+ orderId+" has been "+status.toLowerCase()+ ". We have sent <b>" +exchangeOrder.getReceivedAmount()+" "+exchangeOrder.getReceivedEcurrency()
			+ "</b> to you address <b>"+exchangeOrder.getEwalletId() +"</b> .<br> "+ facebookReviewMessage;
			
			clientPhone = exchangeOrder.getPhone();
			clientSMS="Exchange order #["+orderId+"]"+" has been "+status.toLowerCase()+ " by ExchangerPoint";
			}
		  break;
		  
		 case sell :        	
        	SellOrder sellOrder = (SellOrder) genericService.getObjectById(SellOrder.class, orderId);
        	if(sellOrder != null && sellOrder.getStatus().equalsIgnoreCase("PENDING"))
			{sellOrder.setStatus(status);
			isUpdated = genericService.update(sellOrder) ;
			clientEmailSubject= "Sell order #[" + orderId +"] "+status+ "  on exchangerpoint.com";
			clientEmail =sellOrder.getEmailId();
			
			clientMessage="Hi, <br> Thank you for your order on www.exchangerpoint.com . Your order "+ orderId+" has been "+status.toLowerCase()+ ". We have sent <b>" +sellOrder.getReceivedAmount()+ " "+ sellOrder.getLocalCurrency()
			+ "</b> by <b>" + sellOrder.getPaymentMethod()+ "</b> .<br> "+ facebookReviewMessage;
			
			clientPhone = sellOrder.getPhone();
			clientSMS="Sell order #["+orderId+"]"+" has been "+status.toLowerCase()+ " by ExchangerPoint";
			}
        	break;
        	
		 case buy :        	
        	BuyOrder buyOrder = (BuyOrder) genericService.getObjectById(BuyOrder.class, orderId);
        	if(buyOrder != null && buyOrder.getStatus().equalsIgnoreCase("PENDING"))
			{buyOrder.setStatus(status);
			 isUpdated = genericService.update(buyOrder);
			 
			 clientEmailSubject= "Buy order #[" + orderId +"] "+status+ "  on exchangerpoint.com";
			 clientEmail =buyOrder.getEmailId();
				
			 clientMessage="Hi, <br> Thank you for your order on www.exchangerpoint.com . Your order "+ orderId+" has been "+status.toLowerCase()+ ". We have sent <b>" +buyOrder.getPaidAmount()+" "+buyOrder.getEcurrencyType()
				+ "</b> to you address <b>"+buyOrder.getEwalletId() +"</b> .<br>"+facebookReviewMessage;
				
			 clientPhone = buyOrder.getPhone();
			 clientSMS="Buy order #["+orderId+"]"+" has been "+status.toLowerCase()+ " by ExchangerPoint";
				
			}
        	break;
		}
		
        if(isUpdated && status.equalsIgnoreCase("COMPLETED")) {
        	// Send Email to Client.
        	isMailSent = mailService.sendMail(clientEmailSubject, clientEmail, clientMessage, "ExchangerPoint.com");
        	
        	if(clientPhone != null && clientPhone.trim().length() > 5)
        	{   clientPhone = clientPhone.contains("+") ? clientPhone : "+" + clientPhone;
        		isSMSSent = messagingService.sendSMS(clientPhone, clientSMS); 
        	}      	
        	       	
        }  
       return "{Updated:"+isUpdated+", Email Sent:"+isMailSent+", SMS Sent:"+isSMSSent+", Status:"+status+"}";
        
	}

}
