package com.exchangerpoint.exchangeservices.utility;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class HashCodeVerify {

	/**
	 * @param args
	 */
	protected final  Log logger = LogFactory.getLog(HashCodeVerify.class);

	public boolean verifyTransaction(HttpServletRequest request) {
		String lr_paidto=(String)request.getParameter("lr_paidto");
		String lr_paidby=(String)request.getParameter("lr_paidby");
		String lr_store=(String)request.getParameter("lr_store");
		String lr_amnt=(String)request.getParameter("lr_amnt");
		String lr_transfer=(String)request.getParameter("lr_transfer");
		String lr_currency=(String)request.getParameter("lr_currency");
		String lr_encrypted=(String)request.getParameter("lr_encrypted");
		String store_security="BUCKSpool3356";
		
		if(lr_paidto==null||lr_paidby==null||lr_store==null||lr_amnt==null||lr_transfer==null||lr_currency==null||lr_encrypted==null)
		{ logger.error("Error:Some value is coming null");
			return false;
		}
		
		
		
		
		if(!lr_paidto.equals("U9186280"))
		{
			
			logger.error("Error:Buckspool accoount is not matching");
			return false;
		}
		
		if(!lr_store.equals("buckspool"))
		{
		
			logger.error("Error:Buckspool store name  is not matching");
			return false;
			
		}
		if(!lr_amnt.equals("25.00")&&!lr_amnt.equals("10.00")&&!lr_amnt.equals("250.00")&&!lr_amnt.equals("500.00"))
		{
			
			logger.error("Error:Paid amount is not matching");
			return false;
			
		}
		if(!lr_currency.equals("LRUSD"))
		{
			
			logger.error("Error:Buckspool accoount is not matching");
			return false;
		}

	
		String hashCode=lr_paidto+":"+lr_paidby+":"+lr_store+":"+lr_amnt+":"+lr_transfer+":"+lr_currency+":"+store_security;
		MessageDigest digest=null;
		byte[] hash=null;
		
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e1) {
		
			logger.error("Error:No such algorithm");
			e1.printStackTrace();
			
			return false;
		}
       catch(Exception e){
		
			
			logger.error("Error: MessageDigest Null pointer exception");
			
			e.printStackTrace();	
			return false;
		}
       
		try {
			hash = digest.digest(hashCode.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			
			logger.error("Error:Unsupported encoding exception");
			
			e.printStackTrace();
			return false;
		}
		catch(Exception e){
		
			
			logger.error("Error:Digest Null pointer exception");
			
			e.printStackTrace();	
			return false;
		}
		StringBuffer hexString = new StringBuffer();
		try {
		 
			 
		        for (int i = 0; i < hash.length; i++) {
		        	hexString .append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
		        }}
	     catch(Exception e)
	      {
		 
			
			logger.error("Error:HexStringNull pointer exception");
			
			e.printStackTrace();
			return false;
	       }
		
	
	 if(hexString.toString().toLowerCase().equals(lr_encrypted.toLowerCase()))
	 {
	
		logger.error("Success:Received hash code and generated hashcode matched successfully");
		return true;
	 }
	 else
	 { 
		
		 logger.error("Error:Received hash code and generated hashcode did not match");
			return false;
	 }
	
	}
	public boolean verifySellTransaction(HttpServletRequest request) {
		String lr_paidto=(String)request.getParameter("lr_paidto");
		String lr_paidby=(String)request.getParameter("lr_paidby");
		String lr_store=(String)request.getParameter("lr_store");
		String lr_amnt=(String)request.getParameter("lr_amnt");
		String lr_transfer=(String)request.getParameter("lr_transfer");
		String lr_currency=(String)request.getParameter("lr_currency");
		String lr_encrypted=(String)request.getParameter("lr_encrypted");
		String store_security="BUCKSpool3356";
		
		if(lr_paidto==null||lr_paidby==null||lr_store==null||lr_amnt==null||lr_transfer==null||lr_currency==null||lr_encrypted==null)
		{ logger.error("Error:Some value is coming null");
			return false;
		}
		
		
		
		
		if(!lr_paidto.equals("U9186280"))
		{
			
			logger.error("Error:Buckspool accoount is not matching");
			return false;
		}
		
		if(!lr_store.equals("buckspoolsell"))
		{
		
			logger.error("Error:Buckspool store name  is not matching");
			return false;
			
		}
		
		if(!lr_currency.equals("LRUSD"))
		{
			
			logger.error("Error:Buckspool accoount is not matching");
			return false;
		}

	
		String hashCode=lr_paidto+":"+lr_paidby+":"+lr_store+":"+lr_amnt+":"+lr_transfer+":"+lr_currency+":"+store_security;
		MessageDigest digest=null;
		byte[] hash=null;
		
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e1) {
		
			logger.error("Error:No such algorithm");
			e1.printStackTrace();
			
			return false;
		}
       catch(Exception e){
		
			
			logger.error("Error: MessageDigest Null pointer exception");
			
			e.printStackTrace();	
			return false;
		}
       
		try {
			hash = digest.digest(hashCode.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			
			logger.error("Error:Unsupported encoding exception");
			
			e.printStackTrace();
			return false;
		}
		catch(Exception e){
		
			
			logger.error("Error:Digest Null pointer exception");
			
			e.printStackTrace();	
			return false;
		}
		StringBuffer hexString = new StringBuffer();
		try {
		 
			 
		        for (int i = 0; i < hash.length; i++) {
		        	hexString .append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
		        }}
	     catch(Exception e)
	      {
		 
			
			logger.error("Error:HexStringNull pointer exception");
			
			e.printStackTrace();
			return false;
	       }
		
	
	 if(hexString.toString().toLowerCase().equals(lr_encrypted.toLowerCase()))
	 {
	
		logger.error("Success:Received hash code and generated hashcode matched successfully");
		return true;
	 }
	 else
	 { 
		
		 logger.error("Error:Received hash code and generated hashcode did not match");
			return false;
	 }
	
	}
	public String getEncryption(String hashCode)
	{MessageDigest digest=null;
	 byte[] hash=null;
	 
	 try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e1) {
		
			logger.error("Error:No such algorithm");
			e1.printStackTrace();
			
			
		}
    catch(Exception e){
		
			
			logger.error("Error: MessageDigest Null pointer exception");
			
			e.printStackTrace();	
			
		}
		try {
		hash = digest.digest(hashCode.getBytes("UTF-8"));
	} catch (UnsupportedEncodingException e) {
		
		logger.error("Error:Unsupported encoding exception");
		
		e.printStackTrace();
		
	}
	catch(Exception e){
	
		
		logger.error("Error:Digest Null pointer exception");
		
		e.printStackTrace();	
		
	}
	StringBuffer hexString = new StringBuffer();
	try {
	 
		 
	        for (int i = 0; i < hash.length; i++) {
	        	hexString .append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
	        }}
     catch(Exception e)
      {
	 
		
		logger.error("Error:HexStringNull pointer exception");
		
		e.printStackTrace();
		
       }
	
     if(hexString.toString().length()>20)
 		return hexString.toString().substring(0, 19).toLowerCase();
     
  return hexString.toString().toLowerCase();
	}
	
	
}
