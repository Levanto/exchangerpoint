package com.exchangerpoint.walletservices.razorpay;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.exchangerpoint.commonservices.common.ApplicationDataRepository;
import com.exchangerpoint.commonservices.constant.ApplicationConstants;
import com.exchangerpoint.databaseservices.entity.RazorpayAccount;
import com.exchangerpoint.databaseservices.entity.UserAccount;


public class VirtualAccountClient extends ApiClient {
	
	@Autowired
	private ApplicationDataRepository applicationDataRepository;

  VirtualAccountClient(String auth) {
    super(auth);
  }

  public VirtualAccount create(JSONObject request) throws RazorpayException {
    return post(Constants.VIRTUAL_ACCOUNT_CREATE, request);
  }

  public VirtualAccount fetch(String id) throws RazorpayException {
    return get(String.format(Constants.VIRTUAL_ACCOUNT_GET, id), null);
  }

  public List<VirtualAccount> fetchAll() throws RazorpayException {
    return fetchAll(null);
  }

  public List<VirtualAccount> fetchAll(JSONObject request) throws RazorpayException {
    return getCollection(Constants.VIRTUAL_ACCOUNT_LIST, request);
  }

  public VirtualAccount edit(String id, JSONObject request) throws RazorpayException {
    return patch(String.format(Constants.VIRTUAL_ACCOUNT_EDIT, id), request);
  }

  public VirtualAccount close(String id) throws RazorpayException {
    JSONObject request = new JSONObject();
    request.put("status", "closed");
    return patch(String.format(Constants.VIRTUAL_ACCOUNT_EDIT, id), request);
  }

  public List<Payment> fetchPayments(String id) throws RazorpayException {
    return fetchPayments(id, null);
  }

  public List<Payment> fetchPayments(String id, JSONObject request) throws RazorpayException {
    return getCollection(String.format(Constants.VIRTUAL_ACCOUNT_PAYMENTS, id), request);
  }
  
  public  RazorpayAccount getVirtualAccount (RazorpayClient razorpayClient,UserAccount userAccount) throws RazorpayException {
		
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

public void setApplicationDataRepository(ApplicationDataRepository applicationDataRepository) {
	this.applicationDataRepository = applicationDataRepository;
}
  
  
}
