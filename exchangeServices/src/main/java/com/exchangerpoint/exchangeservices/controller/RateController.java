/**
 * 
 */
package com.exchangerpoint.exchangeservices.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.exchangerpoint.exchangeservices.common.ApplicationDataRepository;
import com.exchangerpoint.exchangeservices.service.HomeService;
import com.exchangerpoint.exchangeservices.service.LocationService;
import com.exchangerpoint.exchangeservices.utility.Constants;
import com.exchangerpoint.exchangeservices.utility.Validator;
import com.exchangerpoint.genericservices.service.GenericService;

/**
 * @author GovindSingh
 */

@RestController
public class RateController {

	
	private String exchangeRate = null;
	private String btcurl = "http://api.coindesk.com/v1/bpi/currentprice/";
	private String ethurl = "https://api.kraken.com/0/public/Ticker?pair=ETH";
	private String rippleurl = "https://api.kraken.com/0/public/Ticker?pair=XRP";
	private String exchangeurl = "http://www.x-rates.com/calculator/?from=";
	private String inputInvalid = "Input:invalid data!";
	private static Validator validator = new Validator();
	private Double receivedAmount = null;
	private DecimalFormat numberFormat = new DecimalFormat("0.00000000");

	@Autowired
	@Qualifier(Constants.HOMESERVICE)
	private HomeService homeService;

	@Autowired
	private LocationService locationService;

	@Autowired
	private GenericService genericService;

	@RequestMapping(value = "/buy/{ecurrency}/{currency}/{paymentMethod}/{amount}", method = RequestMethod.GET)
	public @ResponseBody String buyOrder(@PathVariable final String ecurrency, @PathVariable final String currency,
			@PathVariable final String paymentMethod, @PathVariable Double amount) {
		
		String key = ecurrency + "buy" + paymentMethod;

		if (ecurrency.equalsIgnoreCase("Bitcoin")) {
			exchangeRate = callBackURL(btcurl + currency);

			Float fees = ApplicationDataRepository.exchangeRateMap.get(key);

			if (validator.isBlank(exchangeRate) || fees == null)
				return inputInvalid;

			receivedAmount = (amount * (100 - fees.doubleValue()) * 0.01) / Double.parseDouble(exchangeRate);
			receivedAmount = receivedAmount- 0.0005626;
			
			return numberFormat.format(receivedAmount).toString();
		}

		if (ecurrency.equalsIgnoreCase("Ether")) {

			Float fees = ApplicationDataRepository.exchangeRateMap.get(key);

			if (currency.equals("INR")) {
				String conversionRate = callBackURL(exchangeurl + "USD&to=INR");
				amount = amount / Double.parseDouble(conversionRate);
				exchangeRate = callBackURL(ethurl + "USD");
			} else {
				exchangeRate = callBackURL(ethurl + currency);
			}

			if (exchangeRate == null || fees == null)
				return inputInvalid;

			receivedAmount = (amount * (100 - (double)fees) * 0.01) / Double.parseDouble(exchangeRate);
			return  numberFormat.format(receivedAmount).toString();
		}

		if (ecurrency.equalsIgnoreCase("Ripple")) {

			Float fees = ApplicationDataRepository.exchangeRateMap.get(key);

			if (currency.equals("INR")) {
				String conversionRate = callBackURL(exchangeurl + "USD&to=INR");
				amount = amount / Double.parseDouble(conversionRate);
				exchangeRate = callBackURL(rippleurl + "USD");
			} else {
				exchangeRate = callBackURL(rippleurl + currency);
			}
			
			if (exchangeRate == null || fees == null)
				return inputInvalid;

			receivedAmount = (amount * (100 - (double)fees) * 0.01) / Double.parseDouble(exchangeRate);
			return  numberFormat.format(receivedAmount).toString();

		}
		
		if (ecurrency.equalsIgnoreCase("Electroneum")) {

			Float fees = ApplicationDataRepository.exchangeRateMap.get(key);

			if (currency.equals("INR")) {
				String conversionRate = callBackURL(exchangeurl + "USD&to=INR");
				amount = amount / Double.parseDouble(conversionRate);
				exchangeRate = "1";//callBackURL(rippleurl + "USD");
			} else {
				exchangeRate = "1";//callBackURL(rippleurl + currency);
			}
			
			if (exchangeRate == null || fees == null)
				return inputInvalid;

			receivedAmount = (amount * (100 - (double)fees) * 0.01) / Double.parseDouble(exchangeRate);
			return  numberFormat.format(receivedAmount).toString();

		}
		
		
		return inputInvalid;
	}

	public String callBackURL(String url) {
		URL obj = null;
		HttpURLConnection conn = null;
		String userAgent = "Mozilla/5.0";
		try {
			if (url.contains("coindesk")) {
				url = url + ".json";
			}
			// creating URL object
			obj = new URL(url);
			// opening connection to site

			conn = (HttpURLConnection)obj.openConnection();

			// optional default is GET
			conn.setRequestMethod("GET");

			// add request header
			conn.setRequestProperty("User-Agent", userAgent);
			// Reading response
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println(response);
			// parsing response

			if (url.contains("coindesk")) {
				String a[] = response.toString().split(":");
				String b = a[17];
				if (a.length > 18) {
					b = a[22];
				}

				exchangeRate = b.substring(0, b.indexOf(".")).trim();

			} else if (url.contains("kraken")) {
				String a[] = response.toString().split(":");
				String b[] = a[4].split("\"");
				exchangeRate = b[1].trim();

			} else {
				String respon = response.toString();

				String str1 = "<span class=\"ccOutputRslt\">";
				String str2 = "<span class=\"ccOutputTrail\">";

				int index1 = respon.indexOf(str1);
				int index2 = respon.indexOf(str2);

				exchangeRate = respon.substring(index1 + 27, index2);

				String array[] = exchangeRate.split(",");
				exchangeRate = "";
				for (int i = 0; i < array.length; i++)
					exchangeRate = exchangeRate + array[i];
			}
             
			return exchangeRate;
		} catch (Exception e) {
			e.printStackTrace();
			return exchangeRate;
		}
	}
}
