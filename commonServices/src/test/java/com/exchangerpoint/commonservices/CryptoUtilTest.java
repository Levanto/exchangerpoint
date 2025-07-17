package com.exchangerpoint.commonservices;

import static org.junit.Assert.assertThat;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

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

import com.exchangerpoint.commonservices.common.ApplicationDataRepository;
import com.exchangerpoint.commonservices.constant.ApplicationConstants;
import com.exchangerpoint.commonservices.service.MailServiceImpl;
import com.exchangerpoint.commonservices.utility.CryptoUtil;
import com.exchangerpoint.databaseservices.entity.AdminParam;
import com.exchangerpoint.genericservices.service.GenericService;


@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@ContextConfiguration(locations = {"classpath:application-test-context.xml"})
public class CryptoUtilTest {

	private String key1;
	private String key2;
	
	
	@Autowired
	private ApplicationDataRepository applicationDataRepository;
	
	@Autowired
	private GenericService genericServie;
	@Autowired
	private MailServiceImpl mailServiceImpl;
	
	
	
	@Before
	public void setup() {
		key1 = ApplicationConstants.PLAY_BALL1;
		key2 = ApplicationConstants.PLAY_BALL2;
	
		
		applicationDataRepository.init();
	}
	
	@Ignore("Tested")
	@Test
	@Rollback(false)
	public void testEncryption() {
				
		AdminParam ap1 = new AdminParam();
		ap1.setName("mailservicesmtpserver");
		ap1.setValue(CryptoUtil.encrypt(key1, key2, "mail.exchangerpoint.com"));

		ap1.setParamStatus("ACTIVE");
		ap1.setEncription("true");
		
		AdminParam ap2 = new AdminParam();
		ap2.setName("mailservicesmtpport");
		ap2.setValue(CryptoUtil.encrypt(key1, key2, "465"));
		ap2.setParamStatus("ACTIVE");
		ap2.setEncription("true");
		
		AdminParam ap3 = new AdminParam();
		ap3.setName("mailserviceauthaddress");
		ap3.setValue(CryptoUtil.encrypt(key1, key2, "support@exchangerpoint.com"));
		ap3.setParamStatus("ACTIVE");
		ap3.setEncription("true");
		
		AdminParam ap4 = new AdminParam();
		ap4.setName("mailserviceauthpassword");
		ap4.setValue(CryptoUtil.encrypt(key1, key2, "s1dL0G1N"));
		ap4.setParamStatus("ACTIVE");
		ap4.setEncription("true");
		
		AdminParam ap5 = new AdminParam();
		ap5.setName("twilioaccountsid");
		ap5.setValue(CryptoUtil.encrypt(key1, key2, "ACa696020ae9ced8b799531a7fb0050cc9"));
		ap5.setParamStatus("ACTIVE");
		ap5.setEncription("true");
		
		AdminParam ap6 = new AdminParam();
		ap6.setName("twilioauthtoken");
		ap6.setValue(CryptoUtil.encrypt(key1, key2, "0a48d8f414e36e5eef2c0df31a921c90"));
		ap6.setParamStatus("ACTIVE");
		ap6.setEncription("true");
		
		AdminParam ap7 = new AdminParam();
		ap7.setName("twilosendernumber");
		ap7.setValue("+18703402002");
		ap7.setParamStatus("ACTIVE");
		ap7.setEncription("false");
		

		try {
			
			genericServie.update(ap1);
			//genericServie.update(ap2);
			genericServie.update(ap3);
			//genericServie.update(ap4);
			//genericServie.update(ap5);
			//genericServie.update(ap6);
			//genericServie.update(ap7);
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
	
	@Ignore("Tested")
	@Test
	@Rollback(false)
	public void mail() {




		mailServiceImpl.sendMail("TEST", "sidhu.govind@gmail.com", "TEST");
	}

	
	

	public void setMailServiceImpl(MailServiceImpl mailServiceImpl) {
		this.mailServiceImpl = mailServiceImpl;
	}

	
	public void setGenericServie(GenericService genericServie) {
		this.genericServie = genericServie;
	}

	
	public void setApplicationDataRepository(ApplicationDataRepository applicationDataRepository) {
		this.applicationDataRepository = applicationDataRepository;
	}
	
	
}
