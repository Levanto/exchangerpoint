package com.exchangerpoint.restservices.converter;

import com.exchangerpoint.commonservices.exception.RestException;
import com.exchangerpoint.databaseservices.entity.CryptoCurrency;
import com.exchangerpoint.databaseservices.entity.FiatCurrency;
import com.exchangerpoint.databaseservices.entity.PaymentMethod;
import com.exchangerpoint.restspec.dto.application.CurrencyResponse;
import com.exchangerpoint.restspec.dto.application.PaymentMethodResponse;
import com.exchangerpoint.restspec.dto.application.PaymentMethodsResponse;
import com.exchangerpoint.restspec.dto.application.SupportedCurrencyResponse;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

public class ApplicationResponseConverter {

	
	public static SupportedCurrencyResponse SupportedCurrencyResponse(List<CryptoCurrency> cryptoCurrencyList,List<FiatCurrency> fiatCurrencyList ) throws RestException {
		SupportedCurrencyResponse supportedCurrencyResponse = new SupportedCurrencyResponse();
		for( CryptoCurrency cryptoCurrency : cryptoCurrencyList) {
			CurrencyResponse CurrencyResponse =  new CurrencyResponse();
			CurrencyResponse.setCurrency(cryptoCurrency.getSymbol());
			supportedCurrencyResponse.addCurrenciesItem(CurrencyResponse);
		}
		
		for( FiatCurrency fiatCurrency : fiatCurrencyList) {
			CurrencyResponse CurrencyResponse =  new CurrencyResponse();
			CurrencyResponse.setCurrency(fiatCurrency.getCurrency());
			supportedCurrencyResponse.addCurrenciesItem(CurrencyResponse);
		
		}
		return supportedCurrencyResponse;
	}
	
	
	public static PaymentMethodsResponse PaymentMethodsResponse(List<PaymentMethod> paymentMethodList) throws RestException {
		PaymentMethodsResponse paymentMethodsResponse = new PaymentMethodsResponse();
		for( PaymentMethod paymentMethod : paymentMethodList) {
			PaymentMethodResponse paymentMethodResponse =  new PaymentMethodResponse();
			try {
				BeanUtils.copyProperties(paymentMethodResponse, paymentMethod);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			paymentMethodsResponse.addPaymentMethodsItem(paymentMethodResponse);
		}
		
		
		return paymentMethodsResponse;
	}
}
