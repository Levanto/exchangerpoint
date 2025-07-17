package com.exchangerpoint.walletservices.razorpay;



import java.util.List;

import org.json.JSONObject;

public class PayoutClient extends ApiClient {

  PayoutClient(String auth) {
    super(auth);
  }

  public Contact createContact(JSONObject request) throws RazorpayException {
    return post(Constants.CONTACT_CREATE, request);
  }
  
  public FundAccount createFundAccount(JSONObject request) throws RazorpayException {
	    return post(Constants.FUNDACCOUNT_CREATE, request);
	  }
  
  public Payout createPayout(JSONObject request) throws RazorpayException {
	    return post(Constants.PAYOUT_CREATE, request);
	  }
  
  public Payout fetchPayout(String payoutId) throws RazorpayException {
	  return get(String.format(Constants.FETCH_PAYOUT, payoutId), null);
	  }
  
  public List<Transaction> fetchAllPayout() throws RazorpayException {
	    JSONObject transactionRequest = new JSONObject();
		transactionRequest.put("account_number", "7878780062885414");
	    return getCollection(Constants.FETCH_ALL_PAYOUT, transactionRequest);
	  }

  public int getAvailableBalance() throws RazorpayException {
	    JSONObject transactionRequest = new JSONObject();
	    int balance =0;
		transactionRequest.put("account_number", "7878780062885414");
		List<Transaction> transactionList =  getCollection(Constants.FETCH_ALL_PAYOUT, transactionRequest);
			for(Transaction transaction : transactionList) {
				balance = (Integer)transaction.get("balance")/100;
				break;
			}
			return balance;
			
	  }



}
