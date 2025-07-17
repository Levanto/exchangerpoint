package com.exchangerpoint.exchangeservices.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exchangerpoint.databaseservices.entity.SEOData;
import com.exchangerpoint.exchangeservices.common.ApplicationDataRepository;
import com.exchangerpoint.exchangeservices.service.HomeService;
import com.exchangerpoint.exchangeservices.service.LocationService;
import com.exchangerpoint.exchangeservices.utility.Constants;
import com.exchangerpoint.genericservices.service.GenericService;

@Controller
public class ExchangeController {
	private URL obj = null;
	private HttpURLConnection conn = null;
	private String userAgent = "Mozilla/5.0";
	private String exchangeRate = null;
	private String btcurl = "http://api.coindesk.com/v1/bpi/currentprice/";
	private String ethurl = "https://api.kraken.com/0/public/Ticker?pair=ETH";
	private String rippleurl = "https://api.kraken.com/0/public/Ticker?pair=XRP";
	private String exchangeurl = "http://www.x-rates.com/calculator/?from=";
	private String inputInvalid = "Input:invalid data!";

	@Autowired
	@Qualifier(Constants.HOMESERVICE)
	private HomeService homeService;

	@Autowired
	private LocationService locationService;

	@Autowired
	private GenericService genericService;

	@Autowired
	private ApplicationDataRepository applicationDataRepository;

	public void setApplicationDataRepository(ApplicationDataRepository applicationDataRepository) {
		this.applicationDataRepository = applicationDataRepository;
	}

	@SuppressWarnings("static-access")
	@RequestMapping(value = "/exchangerate.htm", method = RequestMethod.GET)
	public @ResponseBody String getExchangeRate(HttpServletRequest request) {
		try {
			String to = request.getParameter("to");
			String from = request.getParameter("from");
			String amount = request.getParameter("amount");

			if (to == null || to.trim().length() < 0 || from == null || from.trim().length() < 0)
				return inputInvalid;

			if (amount == null || amount.trim().length() < 0)
				amount = "1";

			amount = amount.trim();

			// Record SEO DATA
			//saveSEOData(from, to, amount, request);

			double exchangeAmount = Double.parseDouble(amount);
			double receivedAmount = 0.0;
			double exchangeFees = 0.0;
			if (from.equalsIgnoreCase("bitcoin") && to.equalsIgnoreCase("electroneum")) {

				Float fees = applicationDataRepository.exchangeRateMap.get(from + to);
				exchangeRate = callBackURL(btcurl + "USD");

				if (exchangeRate == null || fees == null)
					return inputInvalid;

				receivedAmount = exchangeAmount * Double.parseDouble(exchangeRate);
				exchangeFees = exchangeAmount * (double)fees * 0.01;

				// calculating fees to buy ripple
			} else if (from.length() == 3 && to.equalsIgnoreCase("electroneum")) {
				Float fees = applicationDataRepository.exchangeRateMap.get(to + "buy");

				if (from.equals("INR")) {
					exchangeFees = exchangeAmount * (double)fees * 0.01;
					String conversionRate = callBackURL(exchangeurl + "USD&to=INR");
					exchangeAmount = exchangeAmount / Double.parseDouble(conversionRate);
					exchangeRate = "0.50";
				} else {
					exchangeRate = "0.50";
				}
				if (exchangeRate == null || fees == null)
					return inputInvalid;

				receivedAmount = (exchangeAmount * (100 - (double)fees) * 0.01) / Double.parseDouble(exchangeRate);
				if (!from.equals("INR"))
					exchangeFees = exchangeAmount * (double)fees * 0.01;
				// calcualte sell ripple
			} 
			// calculating fees to buy ripple
			else if (from.equalsIgnoreCase("bitcoin") && to.equalsIgnoreCase("ripple")) {

				Float fees = applicationDataRepository.exchangeRateMap.get(from + to);
				exchangeRate = callBackURL(rippleurl + "XBT");

				if (exchangeRate == null || fees == null)
					return inputInvalid;

				receivedAmount = (exchangeAmount * (100 - (double)fees) * 0.01) / Double.parseDouble(exchangeRate);
				exchangeFees = exchangeAmount * (double)fees * 0.01;

				// calculating fees to buy ripple
			} else if (from.length() == 3 && to.equalsIgnoreCase("ripple")) {
				Float fees = applicationDataRepository.exchangeRateMap.get(to + "buy");

				if (from.equals("INR")) {
					exchangeFees = exchangeAmount * (double)fees * 0.01;
					String conversionRate = callBackURL(exchangeurl + "USD&to=INR");
					exchangeAmount = exchangeAmount / Double.parseDouble(conversionRate);
					exchangeRate = callBackURL(rippleurl + "USD");
				} else {
					exchangeRate = callBackURL(rippleurl + from);
				}
				if (exchangeRate == null || fees == null)
					return inputInvalid;

				receivedAmount = (exchangeAmount * (100 - (double)fees) * 0.01) / Double.parseDouble(exchangeRate);
				if (!from.equals("INR"))
					exchangeFees = exchangeAmount * (double)fees * 0.01;
				// calcualte sell ripple
			} else if (from.equalsIgnoreCase("ripple") && to.length() == 3) {
				Float fees = applicationDataRepository.exchangeRateMap.get(from + "sell");

				if (to.equals("INR")) {
					exchangeRate = callBackURL(rippleurl + "USD");
				} else {
					exchangeRate = callBackURL(rippleurl + to);
				}

				if (exchangeRate == null || fees == null)
					return inputInvalid;

				exchangeFees = exchangeAmount * (double)fees * 0.01;

				receivedAmount = exchangeAmount * Double.parseDouble(exchangeRate) * (100 - (double)fees) * 0.01;

				if (to.equals("INR")) {
					String conversionRate = callBackURL(exchangeurl + "USD&to=INR");
					receivedAmount = receivedAmount * Double.parseDouble(conversionRate);
				}
				// calculating fees to buy ether

			} else if (from.equalsIgnoreCase("bitcoin") && to.equalsIgnoreCase("ether")) {

				Float fees = applicationDataRepository.exchangeRateMap.get(from + to);
				exchangeRate = callBackURL(ethurl + "XBT");

				if (exchangeRate == null || fees == null)
					return inputInvalid;

				receivedAmount = (exchangeAmount * (100 - (double)fees) * 0.01) / Double.parseDouble(exchangeRate);
				exchangeFees = exchangeAmount * (double)fees * 0.01;

				// calculating fees to buy ether
			} else if (from.length() == 3 && to.equalsIgnoreCase("ether")) {
				Float fees = applicationDataRepository.exchangeRateMap.get(to + "buy");

				if (from.equals("INR")) {
					exchangeFees = exchangeAmount * (double)fees * 0.01;
					String conversionRate = callBackURL(exchangeurl + "USD&to=INR");
					exchangeAmount = exchangeAmount / Double.parseDouble(conversionRate);
					exchangeRate = callBackURL(ethurl + "USD");
				} else {
					exchangeRate = callBackURL(ethurl + from);
				}

				if (exchangeRate == null || fees == null)
					return inputInvalid;

				receivedAmount = (exchangeAmount * (100 - (double)fees) * 0.01) / Double.parseDouble(exchangeRate);
				if (!from.equals("INR"))
					exchangeFees = exchangeAmount * (double)fees * 0.01;
				// calculate ether sell
			} else if (from.equalsIgnoreCase("ether") && to.length() == 3) {
				Float fees = applicationDataRepository.exchangeRateMap.get(from + "sell");

				if (to.equals("INR")) {
					exchangeRate = callBackURL(ethurl + "USD");
				} else {
					exchangeRate = callBackURL(ethurl + to);
				}

				if (exchangeRate == null || fees == null)
					return inputInvalid;

				exchangeFees = exchangeAmount * (double)fees * 0.01;

				receivedAmount = exchangeAmount * Double.parseDouble(exchangeRate) * (100 - (double)fees) * 0.01;

				if (to.equals("INR")) {
					String conversionRate = callBackURL(exchangeurl + "USD&to=INR");
					receivedAmount = receivedAmount * Double.parseDouble(conversionRate);
				}
				// calculating fees to sell bitcoin
			} else if (from.equalsIgnoreCase("bitcoin") && to.length() == 3) {
				exchangeRate = callBackURL(btcurl + to);
				Float fees = applicationDataRepository.exchangeRateMap.get(from + "sell");

				if (fees == null || exchangeRate == null)
					return inputInvalid;

				receivedAmount = exchangeAmount * Double.parseDouble(exchangeRate) * (100 - (double)fees) * 0.01;
				exchangeFees = exchangeAmount * Double.parseDouble(exchangeRate) * (double)fees * 0.01;

				// calculating fees to buy bitcoin
			} else if (from.length() == 3 && to.equalsIgnoreCase("bitcoin")) {
				exchangeRate = callBackURL(btcurl + from);

				Float fees = applicationDataRepository.exchangeRateMap.get(to + "buy");
				if (exchangeRate == null || fees == null)
					return inputInvalid;

				receivedAmount = (exchangeAmount * (100 - (double)fees) * 0.01) / Double.parseDouble(exchangeRate);
				receivedAmount = receivedAmount-0.0005626;
				exchangeFees = exchangeAmount * (double)fees * 0.01;
			}
			// if no bitcoin buy and sell
			else {

				@SuppressWarnings("rawtypes")
				List ecurrencyList = applicationDataRepository.ecurrencyNameList;

				Float fees = applicationDataRepository.exchangeRateMap.get(from + to);

				if (fees == null)
					return inputInvalid;

				// exchange ecurrency example skrill to bitcoin
				if (ecurrencyList.contains(from) && to.equalsIgnoreCase("bitcoin")) {
					exchangeRate = callBackURL(btcurl + "USD");

					if (exchangeRate == null)
						return inputInvalid;

					receivedAmount = (exchangeAmount * (100 - (double)fees) * 0.01) / Double.parseDouble(exchangeRate);
					exchangeFees = exchangeAmount * (double)fees * 0.01;
				}

				// exchange bitcoin example bitcoin to skrill
				else if (from.equalsIgnoreCase("bitcoin") && ecurrencyList.contains(to)) {
					exchangeRate = callBackURL(btcurl + "USD");

					if (exchangeRate == null)
						return inputInvalid;

					receivedAmount = exchangeAmount * (100 - (double)fees) * 0.01;
					exchangeFees = exchangeAmount * (double)fees * 0.01;
				}

				// exchange ecurrency example skrill to neteller
				else if (ecurrencyList.contains(from) && ecurrencyList.contains(to)) {

					receivedAmount = exchangeAmount * (100 - (double)fees) * 0.01;
					exchangeFees = exchangeAmount * (double)fees * 0.01;
				}

				// buy ecurrency rate calculation example INR to Skrill
				else if (from.length() == 3 && ecurrencyList.contains(to)) {
					fees = applicationDataRepository.exchangeRateMap.get(to + "buy");

					exchangeFees = exchangeAmount * (double)fees * 0.01;

					// sell ecurrency fees calculations example Skrill to INR
				} else if (ecurrencyList.contains(from) && to.length() == 3) {
					fees = applicationDataRepository.exchangeRateMap.get(from + "sell");

					receivedAmount = exchangeAmount * (100 - (double)fees) * 0.01;
					exchangeFees = exchangeAmount * (double)fees * 0.01;

				}
				// native currency exchange example USD to INR
				else if (from.length() == 3 && to.length() == 3) {
					// converting USD to local currency
					String conversionRate = callBackURL(exchangeurl + "" + from + "&to=" + to);
					if (conversionRate == null)
						return inputInvalid;

					return exchangeAmount * Double.parseDouble(conversionRate) + ":" + 0.00;
					// does not match any condition
				} else {
					System.out.println("reached if end");
					return inputInvalid;
				}
			}

			// print and return the exchange rate
			System.out.println("{From :" + from + ", To :" + to + ", ExchangeAmount :" + exchangeAmount
					+ ", ReceivedAmount :" + receivedAmount + ", ExchangeFees :" + exchangeFees + "}");
			DecimalFormat numberFormat = new DecimalFormat("0.00000000");
			return numberFormat.format(receivedAmount) + ":" + exchangeFees;

		} catch (Exception e) {
			e.printStackTrace();
			return inputInvalid;
		}

	}

	@RequestMapping(value = "/bitcoinrate.htm", method = RequestMethod.GET)
	public @ResponseBody String getBitcoinRate(HttpServletRequest request) {

		String from = request.getParameter("from");
		String amount = request.getParameter("amount");

		if (from == null || from.trim().length() < 0)
			return "Input:invalid data!";

		if (amount == null || amount.trim().length() < 0)
			amount = "1";

		from = from.toLowerCase().trim();
		amount = amount.trim();

		try {
			exchangeRate = callBackURL(btcurl + from);

			// calculating if amount is specified
			exchangeRate = "" + Double.parseDouble(exchangeRate.trim()) * Double.parseDouble(amount);

			return exchangeRate;
		} catch (Exception e) {
			e.printStackTrace();
			return exchangeRate;
		}
	}

	public String callBackURL(String url) {
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

	private void saveSEOData(String from, String to, String amount, HttpServletRequest request) {
		try {
			String ipAddress = request.getHeader("X-FORWARDED-FOR");
			if (ipAddress == null) {
				ipAddress = request.getRemoteAddr();
			} else {
				ipAddress = new StringTokenizer(ipAddress, ",").nextToken().trim();
			}
			System.out.println(ipAddress);
			locationService.updateLocationDetails(ipAddress);

			SEOData seoData = new SEOData();

			seoData.setFrom(from);
			seoData.setTo(to);
			seoData.setAmount(amount);
			seoData.setStatus("ACTIVE");
			seoData.setIpAddress(ipAddress);
			seoData.setCountry(locationService.getCountry());
			genericService.submit(seoData);
		} catch (Exception e) {
			// TODO
		}
	}
}
