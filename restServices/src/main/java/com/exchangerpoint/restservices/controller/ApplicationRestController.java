package com.exchangerpoint.restservices.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exchangerpoint.commonservices.common.ApplicationDataRepository;
import com.exchangerpoint.commonservices.errorcode.RestErrorCode;
import com.exchangerpoint.commonservices.exception.RestException;
import com.exchangerpoint.restservices.converter.ApplicationResponseConverter;
import com.exchangerpoint.restspec.dto.application.PaymentMethodResponse;
import com.exchangerpoint.restspec.dto.application.PaymentMethodsResponse;
import com.exchangerpoint.restspec.dto.application.SupportedCurrencyResponse;
import com.exchangerpoint.restspec.dto.wallet.WalletsResponse;
import com.exchangerpoint.walletservices.converter.WalletResponseConverter;


@RestController
public class ApplicationRestController extends BaseRestController{
	
	
	
	
	
	@Autowired
	private ApplicationDataRepository applicationDataRepository;
	
	protected final Log logger = LogFactory.getLog(ApplicationRestController.class);

	
	@CrossOrigin
	@GetMapping("/app/supportedcurrencies")
	public ResponseEntity<SupportedCurrencyResponse> getWalletCurrencies() throws RestException {
		
		try {
			SupportedCurrencyResponse supportedCurrencyResponse = ApplicationResponseConverter.SupportedCurrencyResponse(ApplicationDataRepository.cryptoCurrencyList, ApplicationDataRepository.fiatCurrencyList);
	    return new ResponseEntity<SupportedCurrencyResponse>(supportedCurrencyResponse, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@CrossOrigin
	@GetMapping("/app/paymentmethods")
	public ResponseEntity<PaymentMethodsResponse> getPaymentMethods() throws RestException {
		
		try {
			PaymentMethodsResponse paymentMethodsResponse = ApplicationResponseConverter.PaymentMethodsResponse(ApplicationDataRepository.paymentMethodList);
	    return new ResponseEntity<PaymentMethodsResponse>(paymentMethodsResponse, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	public void setApplicationDataRepository(ApplicationDataRepository applicationDataRepository) {
		this.applicationDataRepository = applicationDataRepository;
	}

	

}
