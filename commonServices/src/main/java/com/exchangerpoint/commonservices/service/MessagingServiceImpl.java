package com.exchangerpoint.commonservices.service;

import com.exchangerpoint.commonservices.common.ApplicationDataRepository;
import com.exchangerpoint.commonservices.constant.ApplicationConstants;
import com.exchangerpoint.commonservices.utility.CryptoUtil;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * @author Govind
 *
 */
public class MessagingServiceImpl implements MessagingService {

	@Autowired
	private ApplicationDataRepository applicationDataRepository;
	@SuppressWarnings("static-access")
	private Map<String, String> adminParamMap = applicationDataRepository.adminParamMap;

	// Find your Account Sid and Token at twilio.com/user/account
	private final String ACCOUNT_SID = CryptoUtil.decrypt(ApplicationConstants.PLAY_BALL1, ApplicationConstants.PLAY_BALL2, adminParamMap.get("twilioaccountsid"));
	private final String AUTH_TOKEN = CryptoUtil.decrypt(ApplicationConstants.PLAY_BALL1, ApplicationConstants.PLAY_BALL2, adminParamMap.get("twilioauthtoken"));
	private final String SENDER = adminParamMap.get("twiliosendernumber");
	
	public boolean sendSMS(String recipient, String messageBody) {

		try{

			TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
			// Build the parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("From",SENDER));
			params.add(new BasicNameValuePair("To", recipient));
			params.add(new BasicNameValuePair("Body", messageBody));
			MessageFactory messageFactory = client.getAccount().getMessageFactory();
			Message message = messageFactory.create(params);
			//send sms
			String sid=message.getSid();
			if(sid!=null&&sid.length()==34)
				return true;
			else
				return false;
		}catch(TwilioRestException e){
			e.printStackTrace();
			return false;
		}
	}

	public void setApplicationDataRepository(ApplicationDataRepository applicationDataRepository) {
		this.applicationDataRepository = applicationDataRepository;
	}
}
