package com.exchangerpoint.commonservices.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.exchangerpoint.databaseservices.entity.AdminParam;
import com.exchangerpoint.databaseservices.entity.FiatCurrency;
import com.exchangerpoint.databaseservices.entity.PaymentMethod;
import com.exchangerpoint.databaseservices.entity.CryptoCurrency;
import com.exchangerpoint.databaseservices.entity.ExchangeRate;
import com.exchangerpoint.genericservices.service.GenericService;
import com.exchangerpoint.genericservices.util.Constants;

public class ApplicationDataRepository {
	public static List<CryptoCurrency> cryptoCurrencyList;
	public static List<PaymentMethod> paymentMethodList;
	public static List<String> ecurrencyNameList;
	public static List<FiatCurrency> fiatCurrencyList;
	public static Map<String, String> adminParamMap;
	public static Map<String, Double> exchangeRateMap;
	public static Properties restErrorCodeProperties = new Properties();
	public static Properties errorCodeProperties = new Properties();
	public static Properties serviceErrorCodeProperties = new Properties();
	private InputStream input = null;
	
	protected final Log logger = LogFactory.getLog(ApplicationDataRepository.class);
    

	@Autowired
	@Qualifier(Constants.GENERIC_SERVICE)
	private GenericService genericService;

	public void init() {
		loadPaymentMethodList();
		loadCryptoCurrencyList();
		loadFiatCurrencyList();
		loadAdminParamMap();
		loadExchangeRateMap();
		loadProperties();
	}

	
	@SuppressWarnings("unchecked")
	private void loadPaymentMethodList() {
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("methodStatus", "ACTIVE");
		paymentMethodList = genericService.getOrderedList(PaymentMethod.class, valueMap, "name", "asc");
	}

	
	@SuppressWarnings("unchecked")
	private void loadCryptoCurrencyList() {
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("currencyStatus", "ACTIVE");

		cryptoCurrencyList = new ArrayList<CryptoCurrency>();
		ecurrencyNameList = new ArrayList<String>();

		if (genericService.getOrderedList(CryptoCurrency.class, valueMap, "name", "asc") != null) {
			cryptoCurrencyList = genericService.getObjectsByEqualityConditions(CryptoCurrency.class, valueMap);
			if (cryptoCurrencyList.size() > 0) {
				for (CryptoCurrency cc : cryptoCurrencyList) {
					ecurrencyNameList.add(cc.getName());
				}
			}
		}

	}

	@SuppressWarnings("unchecked")
	private void loadFiatCurrencyList() {
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("currencyStatus", "ACTIVE");
		fiatCurrencyList = genericService.getOrderedList(FiatCurrency.class, valueMap, "currency", "asc");
	}



	@SuppressWarnings("unchecked")
	private void loadAdminParamMap() {
		List<AdminParam> adminParamList = genericService.getList(AdminParam.class);
        adminParamMap = new HashMap<String, String>();

		for (int i = 0; i < adminParamList.size(); i++) {
			adminParamMap.put(adminParamList.get(i).getName(), adminParamList.get(i).getValue());
		}
	}

	@SuppressWarnings("unchecked")
	private void loadExchangeRateMap() {
		List<ExchangeRate> exchangeRateList = genericService.getList(ExchangeRate.class);

		exchangeRateMap = new HashMap<String, Double>();
		for (int i = 0; i < exchangeRateList.size(); i++) {
			exchangeRateMap.put((exchangeRateList.get(i).getFromCurrency() + exchangeRateList.get(i).getToCurrency()),
				Double.valueOf(exchangeRateList.get(i).getExchangeFees()));
		}
	}

    private void loadProperties() {
		try {

			input = ApplicationDataRepository.class.getClassLoader().getResourceAsStream("RestErrorCode.properties");
			restErrorCodeProperties.load(input);
			input = ApplicationDataRepository.class.getClassLoader().getResourceAsStream("ErrorCode.properties");
			errorCodeProperties.load(input);
			input = ApplicationDataRepository.class.getClassLoader().getResourceAsStream("ServiceErrorCode.properties");
			serviceErrorCodeProperties.load(input);
			
		} catch (IOException ex) {
			logger.error(ex);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}

		
    }
    
	public void setGenericService(GenericService genericService) {
		this.genericService = genericService;
	}
}
