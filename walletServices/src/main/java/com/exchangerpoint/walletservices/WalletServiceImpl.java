package com.exchangerpoint.walletservices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.exchangerpoint.commonservices.common.ApplicationDataRepository;
import com.exchangerpoint.commonservices.constant.ApplicationConstants;
import com.exchangerpoint.commonservices.errorcode.ServiceErrorCode;
import com.exchangerpoint.commonservices.service.MailService;
import com.exchangerpoint.commonservices.service.MessagingService;
import com.exchangerpoint.commonservices.utility.CryptoUtil;
import com.exchangerpoint.databaseservices.entity.FiatCurrency;
import com.exchangerpoint.databaseservices.entity.RazorpayAccount;
import com.exchangerpoint.databaseservices.entity.UserAccount;
import com.exchangerpoint.databaseservices.entity.UserLimit;
import com.exchangerpoint.databaseservices.entity.UserWallet;
import com.exchangerpoint.genericservices.exception.GenericException;
import com.exchangerpoint.genericservices.service.GenericService;
import com.exchangerpoint.genericservices.util.PropertyConditionMatcher;
import com.exchangerpoint.walletservices.exception.WalletServiceException;
import com.exchangerpoint.walletservices.razorpay.RazorpayClient;
import com.exchangerpoint.walletservices.razorpay.RazorpayException;
import com.exchangerpoint.walletservices.razorpay.VirtualAccount;
@Transactional
public class WalletServiceImpl implements WalletService {

	@Autowired
	private GenericService genericService;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private MessagingService messagingService;
	
	
	
	
	@Autowired
	private ApplicationDataRepository applicationDataRepository;
	
    protected final Log logger = LogFactory.getLog(WalletServiceImpl.class);

	
	
    @SuppressWarnings("static-access")
	@Override
	public RazorpayAccount createVirtualAccount(UserAccount userAccount)
			throws RazorpayException {
		RazorpayClient razorpayClient = new RazorpayClient(CryptoUtil.decrypt(ApplicationConstants.PLAY_BALL1, ApplicationConstants.PLAY_BALL2, applicationDataRepository.adminParamMap.get("razorpayMerchantKey")) , CryptoUtil.decrypt(ApplicationConstants.PLAY_BALL1, ApplicationConstants.PLAY_BALL2, applicationDataRepository.adminParamMap.get("razorpayMerchantSecret")));

		JSONObject request = new JSONObject();

		JSONObject receiver = new JSONObject();
		
		JSONArray receiverTypeArray = new JSONArray();
		receiverTypeArray.put("vpa");
		receiverTypeArray.put("bank_account");
		receiver.put("types", receiverTypeArray);
		
		JSONObject vpa = new JSONObject();
		String number = Math.random()+"";
		number= number.substring(number.indexOf(".")+1, number.indexOf(".") + 7);
		vpa.put("descriptor", "oint"+number);
		receiver.put("vpa", vpa);
		
		request.put("receivers", receiver);

		request.put("description", userAccount.getName().toUpperCase() +" EXPT ACCOUNT");

		VirtualAccount virtualAccount = razorpayClient.VirtualAccounts.create(request);
		
		request = new JSONObject();
		JSONObject notes = new JSONObject();
		JSONArray receiversArray = virtualAccount.get("receivers");

		for(Object object : receiversArray) {
			 receiver =new JSONObject(object.toString());
			
			if(receiver.getString("entity").equalsIgnoreCase("bank_account")) {
				notes.put("ACCOUNT NUMBER", receiver.getString("account_number"));
			}

		}
		request.put("notes", notes);
		razorpayClient.VirtualAccounts.edit(virtualAccount.get("id").toString(),request);
		
	

	
	RazorpayAccount razorpayAccount = new RazorpayAccount();
	razorpayAccount.setAccountStatus(ApplicationConstants.ACCOUNT_STATUS_ACTIVE);
	razorpayAccount.setBankFee(Double.parseDouble(ApplicationDataRepository.adminParamMap.get("BANK_FEE")));
	razorpayAccount.setAccountId(virtualAccount.get("id").toString());
	 receiversArray = virtualAccount.get("receivers");

	for(Object object : receiversArray) {
		 receiver =new JSONObject(object.toString());
		if(receiver.getString("entity").equalsIgnoreCase("vpa")) {
			razorpayAccount.setUpiId(receiver.getString("address"));
		}

		if(receiver.getString("entity").equalsIgnoreCase("bank_account")) {
			razorpayAccount.setAccountNumber(receiver.getString("account_number"));
			razorpayAccount.setIfscCode(receiver.getString("ifsc"));
			razorpayAccount.setBankName(receiver.getString("bank_name").toUpperCase());
		}

	}
	if(razorpayAccount.getUpiId() ==null)
		razorpayAccount.setUpiId("NOT AVAILABLE");

	razorpayAccount.setUserAccount(userAccount);
	
	return razorpayAccount;
	}
	
	@Override
	public void closeVirtualAccount(String id) throws RazorpayException {
		// TODO Auto-generated method stub
		
	}

	



	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserWallet> getWallets(){
		
		return (List<UserWallet>) genericService.getList(UserWallet.class);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserWallet> getWallets(String userName) throws WalletServiceException{
		try {
	    	 
			Criteria criteria = genericService.createBlankCriteria(UserWallet.class);
			
			criteria = genericService.addANDConditionToCriteria(criteria, prepareANDConditionsList(userName, null));
			return criteria.list();
			
			
	       } catch (GenericException e) {
	    	   e.printStackTrace();
	    	   logger.error("Error code : " +ServiceErrorCode.INTERNAL_SERVER_ERROR.getErrorCode() + " Error message : "+ServiceErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage()+" getWallet " + userName);
			   throw new WalletServiceException(ServiceErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage(),ServiceErrorCode.INTERNAL_SERVER_ERROR.getErrorCode());	
	       
	       }
	}

	

	@SuppressWarnings("unchecked")
	@Override
	public UserWallet getWallet(String userName, String currency) throws WalletServiceException{
		try {
	    	 
			Criteria criteria = genericService.createBlankCriteria(UserWallet.class);
			
			criteria = genericService.addANDConditionToCriteria(criteria, prepareANDConditionsList(userName, currency));
			List<UserWallet> userWalletList = criteria.list();
			
			if(userWalletList != null && userWalletList.size()>=1)
				return userWalletList.get(0);
			else 
			{   logger.error("Error code : " +ServiceErrorCode.WALLET_DOES_NOT_EXIST.getErrorCode() + " Error message : "+ServiceErrorCode.WALLET_DOES_NOT_EXIST.getErrorMessage()+" getWallet " + userName);
				throw new WalletServiceException(ServiceErrorCode.WALLET_DOES_NOT_EXIST.getErrorMessage(),ServiceErrorCode.WALLET_DOES_NOT_EXIST.getErrorCode());	
			}	      
			
			
	       } catch (GenericException e) {
	    	   e.printStackTrace();
	    	   logger.error("Error code : " +ServiceErrorCode.INTERNAL_SERVER_ERROR.getErrorCode() + " Error message : "+ServiceErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage()+" getWallet " + userName);
				throw new WalletServiceException(ServiceErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage(),ServiceErrorCode.INTERNAL_SERVER_ERROR.getErrorCode());	
	       }
	}

	@SuppressWarnings("unchecked")
	@Override
	public void createWallet(String userName, String currency)  throws WalletServiceException{
		boolean userCreated =false;
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("userName", userName);
		valueMap.put("currencySymbol", currency);
		List<UserWallet> userWalletList =genericService.getObjectsByEqualityConditions(UserWallet.class, valueMap);
		if(userWalletList != null && userWalletList.size()>=1)
		    {
			UserWallet userWallet = userWalletList.get(0); 
			userWallet.setWalletStatus(ApplicationConstants.ACCOUNT_STATUS_ACTIVE);
			userCreated =genericService.update(userWallet);
		     }
		else {
			
			UserWallet userWallet = new UserWallet();
			userWallet.setCurrencySymbol(currency);
			userWallet.setWalletStatus(ApplicationConstants.ACCOUNT_STATUS_ACTIVE);
			userWallet.setAvailableBalance(0);
			userWallet.setPendingBalance(0);
			userWallet.setReserveBalance(0);
			userWallet.setTotalBalance(0);
			userWallet.setAutoConvert(false);
			userWallet.setUserName(userName);
			userCreated= genericService.update(userWallet);
			
		}
		for(FiatCurrency fiatCurrency : ApplicationDataRepository.fiatCurrencyList) {
			if(fiatCurrency.getCurrency().equalsIgnoreCase(currency)) {
				UserLimit userLimit = new UserLimit();
			    userLimit.setCurrency(fiatCurrency.getCurrency());
			    userLimit.setDailyDepositLimit(fiatCurrency.getDepositLimit());
			    userLimit.setDepositAllowedLimit(fiatCurrency.getDepositLimit());
			    userLimit.setWithdrawAllowedLimit(fiatCurrency.getWithdrawLimit());
			    userLimit.setDailyWithdrawLimit(fiatCurrency.getWithdrawLimit());
			    userLimit.setUserName(userName);
			    genericService.update(userLimit);
			    
			}
			
		}
		if(!userCreated) {
			 logger.error("Error code : " +ServiceErrorCode.INTERNAL_SERVER_ERROR.getErrorCode() + " Error message : "+ServiceErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage()+" createWallet " + userName);
				throw new WalletServiceException(ServiceErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage(),ServiceErrorCode.INTERNAL_SERVER_ERROR.getErrorCode());	
	       
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteWallet(String userName, String currency) throws WalletServiceException {
		try {
	    	 
			Criteria criteria = genericService.createBlankCriteria(UserWallet.class);
			
			criteria = genericService.addANDConditionToCriteria(criteria, prepareANDConditionsList(userName, currency));
			List<UserWallet> userWalletList = criteria.list();
			
			if(userWalletList != null && userWalletList.size()>=1)
				{
				UserWallet userWallet = userWalletList.get(0); 
				userWallet.setWalletStatus(ApplicationConstants.ACCOUNT_STATUS_INACTIVE);
				 genericService.update(userWallet);
				
				}
			else 
			{   logger.error("Error code : " +ServiceErrorCode.WALLET_DOES_NOT_EXIST.getErrorCode() + " Error message : "+ServiceErrorCode.WALLET_DOES_NOT_EXIST.getErrorMessage()+" getWallet " + userName);
			    throw new WalletServiceException(ServiceErrorCode.WALLET_DOES_NOT_EXIST.getErrorMessage(),ServiceErrorCode.WALLET_DOES_NOT_EXIST.getErrorCode());	
		}
			
			
	       }  catch (GenericException e) {
	    	   e.printStackTrace();
	    	   logger.error("Error code : " +ServiceErrorCode.INTERNAL_SERVER_ERROR.getErrorCode() + " Error message : "+ServiceErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage()+" getWallet " + userName);
				throw new WalletServiceException(ServiceErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage(),ServiceErrorCode.INTERNAL_SERVER_ERROR.getErrorCode());	
	       }
	}

	


	private List<PropertyConditionMatcher> prepareANDConditionsList(String userName, String currency) {
		PropertyConditionMatcher statusCondition = new PropertyConditionMatcher("walletStatus", "==", "ACTIVE");
		PropertyConditionMatcher userNameCondition = new PropertyConditionMatcher("userName", "==", userName);
		
		List<PropertyConditionMatcher> conditionsList = new ArrayList<PropertyConditionMatcher>();
		conditionsList.add(statusCondition);
		conditionsList.add(userNameCondition);
		
		if(currency != null) {
		PropertyConditionMatcher currencyCondition = new PropertyConditionMatcher("currencySymbol", "==", currency);
		conditionsList.add(currencyCondition);
		}
		return conditionsList;
	}



	public void setGenericService(GenericService genericService) {
		this.genericService = genericService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}
	
	
	
	public void setMessagingService(MessagingService messagingService) {
		this.messagingService = messagingService;
	}



	public void setApplicationDataRepository(ApplicationDataRepository applicationDataRepository) {
		this.applicationDataRepository = applicationDataRepository;
	}

	
}
