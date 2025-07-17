package com.exchangerpoint.exchangeservices.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.exchangerpoint.exchangeservices.common.ApplicationDataRepository;
import com.google.gson.Gson;

public class GoogleRobotVerification {
	
	public static boolean isHuman(
            String response,
            String remoteip
            ) {
		
		if(response == null
				|| response.trim().length() == 0 )
			return false;

    	 URL url;
		try {
			url = new URL("https://www.google.com/recaptcha/api/siteverify?secret=" +
					ApplicationDataRepository.adminParamMap.get("rcaptchaprivateexchangerpoint.com") +
					"&response=" + response + 
					"&remoteip=" + remoteip);

         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
         conn.setRequestMethod("GET");
         String line, outputString = "";
         BufferedReader reader = new BufferedReader(
                 new InputStreamReader(conn.getInputStream()));
         while ((line = reader.readLine()) != null) {
             outputString += line;
         }
         System.out.println(outputString);
         CaptchaResponse capRes = new Gson().fromJson(outputString, CaptchaResponse.class);

         if(capRes.isSuccess()) {
        	 return true;
         } else {
        	 return false;
         }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 return false;
		}
    }
}
