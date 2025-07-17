package com.exchangerpoint.exchangeservices.utility;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.exchangerpoint.databaseservices.entity.AdminParam;
import com.exchangerpoint.exchangeservices.common.ApplicationDataRepository;
import com.exchangerpoint.exchangeservices.common.Constants;
import com.exchangerpoint.exchangeservices.utility.CryptoUtil;
import com.exchangerpoint.genericservices.service.GenericService;


@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@ContextConfiguration(locations = {"classpath:application-test-context.xml"})
public class CryptoUtilTest {

	private String key1;
	private String key2;
	
	
	@Autowired
	private GenericService genericServie;
	
	@Autowired
	private ApplicationDataRepository applicationDataRepository;
	
	@Before
	public void setup() {
		key1 = Constants.PLAY_BALL1;
		key2 = Constants.PLAY_BALL2;
	
		
		applicationDataRepository.init();
	}
	
	@Ignore("Tested")
	@Test
	@Rollback(false)
	public void testEncryption() {
				
		AdminParam ap1 = new AdminParam();
		ap1.setKey("mailservicesmtpserver");
		ap1.setValue(CryptoUtil.encrypt(key1, key2, "email-smtp.us-west-2.amazonaws.com"));
		ap1.setStatus("ACTIVE");
		ap1.setEncryption("true");
		
		AdminParam ap2 = new AdminParam();
		ap2.setKey("mailservicesmtpport");
		ap2.setValue(CryptoUtil.encrypt(key1, key2, "587"));
		ap2.setStatus("ACTIVE");
		ap2.setEncryption("true");
		
		AdminParam ap3 = new AdminParam();
		ap3.setKey("mailserviceauthaddress");
		ap3.setValue(CryptoUtil.encrypt(key1, key2, "AKIAIXFIYO4CNV74DYWQ"));
		ap3.setStatus("ACTIVE");
		ap3.setEncryption("true");
		
		AdminParam ap4 = new AdminParam();
		ap4.setKey("mailserviceauthpassword");
		ap4.setValue(CryptoUtil.encrypt(key1, key2, "AoV12Pt2pZjfPQr47MBmWGoZQ68yGAGnGuiUubC2Vq+O"));
		ap4.setStatus("ACTIVE");
		ap4.setEncryption("true");
		
		AdminParam ap5 = new AdminParam();
		ap5.setKey("twilioaccountsid");
		ap5.setValue(CryptoUtil.encrypt(key1, key2, "ACa696020ae9ced8b799531a7fb0050cc9"));
		ap5.setStatus("ACTIVE");
		ap5.setEncryption("true");
		
		AdminParam ap6 = new AdminParam();
		ap6.setKey("twilioauthtoken");
		ap6.setValue(CryptoUtil.encrypt(key1, key2, "0a48d8f414e36e5eef2c0df31a921c90"));
		ap6.setStatus("ACTIVE");
		ap6.setEncryption("true");
		
		AdminParam ap7 = new AdminParam();
		ap7.setKey("twilosendernumber");
		ap7.setValue("+18703402002");
		ap7.setStatus("ACTIVE");
		ap7.setEncryption("false");
		

		try {
			
			genericServie.update(ap1);
			genericServie.update(ap2);
			genericServie.update(ap3);
			genericServie.update(ap4);
			genericServie.update(ap5);
			genericServie.update(ap6);
			genericServie.update(ap7);
		}
		catch(DataAccessException dae) {
			dae.printStackTrace();
		}
		//Assert.assertTrue("test encryption failed.", result);
	}
	
	@SuppressWarnings({ "static-access" })
	@Ignore("Tested")
	@Test
	@Rollback(false)
	public void testDecryption() {
		String decryptedMailServiceSMTPServer = CryptoUtil.decrypt(key1, key2, applicationDataRepository.adminParamMap.get("mailservicesmtpserver"));
		System.out.println("decryptedMailServiceSMTPServer decryption: Both equal ? " + decryptedMailServiceSMTPServer.equals("email-smtp.us-west-2.amazonaws.com"));
		Assert.assertTrue("decryptedMailServiceSMTPServer decryption failed.", decryptedMailServiceSMTPServer.equals("email-smtp.us-west-2.amazonaws.com"));
		
		String decryptedMailServiceSMTPPort = CryptoUtil.decrypt(key1, key2, applicationDataRepository.adminParamMap.get("mailservicesmtpport"));
		System.out.println("decryptedMailServiceSMTPPort decryption: Both equal ? " + decryptedMailServiceSMTPPort.equals("587"));
		Assert.assertTrue("decryptedMailServiceSMTPPort decryption failed.", decryptedMailServiceSMTPPort.equals("587"));
		
		String decryptedMailServiceAuthAddress = CryptoUtil.decrypt(key1, key2, applicationDataRepository.adminParamMap.get("mailserviceauthaddress"));
		System.out.println("decryptedMailServiceAuthAddress decryption: Both equal ? " + decryptedMailServiceAuthAddress.equals("AKIAIXFIYO4CNV74DYWQ"));
		Assert.assertTrue("decryptedMailServiceAuthAddress decryption failed.", decryptedMailServiceAuthAddress.equals("AKIAIXFIYO4CNV74DYWQ"));
		
		String decryptedMailServiceAuthPassword = CryptoUtil.decrypt(key1, key2, applicationDataRepository.adminParamMap.get("mailserviceauthpassword"));
		System.out.println("decryptedMailServiceAuthPassword decryption: Both equal ? " + decryptedMailServiceAuthPassword.equals("AoV12Pt2pZjfPQr47MBmWGoZQ68yGAGnGuiUubC2Vq+O"));
		Assert.assertTrue("decryptedMailServiceAuthPassword decryption failed.", decryptedMailServiceAuthPassword.equals("AoV12Pt2pZjfPQr47MBmWGoZQ68yGAGnGuiUubC2Vq+O"));
		
		String decryptedtwilioaccountsid = CryptoUtil.decrypt(key1, key2, applicationDataRepository.adminParamMap.get("twilioaccountsid"));
		System.out.println("decryptedtwilioaccountsid decryption: Both equal ? " + decryptedtwilioaccountsid.equals("ACa696020ae9ced8b799531a7fb0050cc9"));
		Assert.assertTrue("decryptedtwilioaccountsid decryption failed.", decryptedtwilioaccountsid.equals("ACa696020ae9ced8b799531a7fb0050cc9"));
		
		String decryptedtwilioauthtoken = CryptoUtil.decrypt(key1, key2, applicationDataRepository.adminParamMap.get("twilioauthtoken"));
		System.out.println("decryptedtwilioauthtoken decryption: Both equal ? " + decryptedtwilioauthtoken.equals("0a48d8f414e36e5eef2c0df31a921c90"));
		assertThat("decryptedtwilioauthtoken decryption: Both equal ? ", decryptedtwilioauthtoken, is("0a48d8f414e36e5eef2c0df31a921c90"));
	
	}

	public void setGenericServieImpl(GenericService genericServieImpl) {
		this.genericServie = genericServieImpl;
	}
}
