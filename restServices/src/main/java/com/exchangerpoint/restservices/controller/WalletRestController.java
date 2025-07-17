package com.exchangerpoint.restservices.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.exchangerpoint.commonservices.common.ApplicationDataRepository;
import com.exchangerpoint.commonservices.errorcode.RestErrorCode;
import com.exchangerpoint.commonservices.errorcode.ServiceErrorCode;
import com.exchangerpoint.commonservices.exception.RestException;
import com.exchangerpoint.commonservices.validator.CommonValidator;
import com.exchangerpoint.databaseservices.entity.UserWallet;
import com.exchangerpoint.restspec.dto.wallet.UpdateWalletRequest;
import com.exchangerpoint.restspec.dto.wallet.WalletDetails;
import com.exchangerpoint.restspec.dto.wallet.WalletResponse;
import com.exchangerpoint.restspec.dto.wallet.WalletsResponse;
import com.exchangerpoint.walletservices.WalletService;
import com.exchangerpoint.walletservices.converter.WalletResponseConverter;
import com.exchangerpoint.walletservices.exception.WalletServiceException;


@RestController
public class WalletRestController extends BaseRestController{
	
	
	
	@Autowired
	private WalletService walletService;
	
	@SuppressWarnings("unused")
	@Autowired
	private ApplicationDataRepository applicationDataRepository;
	
	protected final Log logger = LogFactory.getLog(WalletRestController.class);

	@CrossOrigin
	@GetMapping("/wallets")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
	public ResponseEntity<WalletsResponse> getWallets() throws RestException {
		
		List<UserWallet> userWalletList = walletService.getWallets();
		if(userWalletList == null) {
			logger.error("Error Code : " + RestErrorCode.WALLET_DOES_NOT_EXIST.getErrorCode() + " Error Message : " + RestErrorCode.WALLET_DOES_NOT_EXIST.getErrorMessage());
			throw new RestException(RestErrorCode.WALLET_DOES_NOT_EXIST.getErrorMessage(),RestErrorCode.WALLET_DOES_NOT_EXIST.getErrorCode());
		}
		WalletsResponse walletsResponse = WalletResponseConverter.WalletsResponse(userWalletList);
	    return new ResponseEntity<WalletsResponse>(walletsResponse, HttpStatus.OK);
	}
	
	
	
	
	
	@CrossOrigin
	@GetMapping("/wallets/")
	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<WalletResponse> getWallets(@RequestBody WalletDetails walletDetails) throws RestException {
		if(CommonValidator.isBlank(walletDetails.getUserName())) {
			logger.error("Error Code : " + RestErrorCode.NO_USERNAME_SUPPLIED.getErrorCode() + " Error Message : " + RestErrorCode.NO_USERNAME_SUPPLIED.getErrorMessage() +" GetWallet : " + walletDetails.toString());
			throw new RestException(RestErrorCode.NO_USERNAME_SUPPLIED.getErrorMessage(),RestErrorCode.NO_USERNAME_SUPPLIED.getErrorCode());
		}
		
		if(CommonValidator.isBlank(walletDetails.getCurrency())) {
			logger.error("Error Code : " + RestErrorCode.NO_CURRENCY_SUPPLIED.getErrorCode() + " Error Message : " + RestErrorCode.NO_CURRENCY_SUPPLIED.getErrorMessage() +" GetWallet : " + walletDetails.toString());
			throw new RestException(RestErrorCode.NO_CURRENCY_SUPPLIED.getErrorMessage(),RestErrorCode.NO_CURRENCY_SUPPLIED.getErrorCode());
		}
		
		validateUserAccess(walletDetails.getUserName());
		
		UserWallet userWallet  = new UserWallet();
		try{
			 userWallet = walletService.getWallet(walletDetails.getUserName(),walletDetails.getCurrency());
		} catch(WalletServiceException exception) {
			if(exception.getErrorCode() == ServiceErrorCode.WALLET_DOES_NOT_EXIST.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.WALLET_DOES_NOT_EXIST.getErrorCode() + " Error Message : " + RestErrorCode.WALLET_DOES_NOT_EXIST.getErrorMessage() +" GetWallet : " + walletDetails.toString());
				throw new RestException(RestErrorCode.WALLET_DOES_NOT_EXIST.getErrorMessage(),RestErrorCode.WALLET_DOES_NOT_EXIST.getErrorCode());   	
			}
			if(exception.getErrorCode() == ServiceErrorCode.INTERNAL_SERVER_ERROR.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.INTERNAL_SERVER_ERROR.getErrorCode() + " Error Message : " + RestErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage() +" GetWallet : " + walletDetails.toString());
				throw new RestException(RestErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage(),RestErrorCode.INTERNAL_SERVER_ERROR.getErrorCode());   	
			}
		}
		
		WalletResponse walletResponse = WalletResponseConverter.WalletResponse(userWallet);
	    return new ResponseEntity<WalletResponse>(walletResponse, HttpStatus.OK);
		
	}
	
	@CrossOrigin
	@GetMapping("/wallets/{userName}")
	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<WalletsResponse> getWallet(@PathVariable String userName) throws RestException {
		if(CommonValidator.isBlank(userName)) {
			logger.error("Error Code : " + RestErrorCode.NO_USERNAME_SUPPLIED.getErrorCode() + " Error Message : " + RestErrorCode.NO_USERNAME_SUPPLIED.getErrorMessage() +" GetWallets : " + userName);
			throw new RestException(RestErrorCode.NO_USERNAME_SUPPLIED.getErrorMessage(),RestErrorCode.NO_USERNAME_SUPPLIED.getErrorCode());
		}
		
		validateUserAccess(userName);
		List<UserWallet> userWalletList = new ArrayList<UserWallet>();
		try{
		 userWalletList = walletService.getWallets(userName);
		
		} catch(WalletServiceException exception) {
			
			if(exception.getErrorCode() == ServiceErrorCode.INTERNAL_SERVER_ERROR.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.INTERNAL_SERVER_ERROR.getErrorCode() + " Error Message : " + RestErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage() +" UserName : " + userName);
				throw new RestException(RestErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage(),RestErrorCode.INTERNAL_SERVER_ERROR.getErrorCode());   	
			}
		}
		
		WalletsResponse walletsResponse = WalletResponseConverter.WalletsResponse(userWalletList);
	    return new ResponseEntity<WalletsResponse>(walletsResponse, HttpStatus.OK);
		
	}
	
	@CrossOrigin
	@PostMapping("/wallets/add/")
	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@ResponseStatus(HttpStatus.CREATED)
	public void walletCreate(@RequestBody WalletDetails walletDetails) throws RestException {
		
		if(CommonValidator.isBlank(walletDetails.getUserName())) {
			logger.error("Error Code : " + RestErrorCode.NO_USERNAME_SUPPLIED.getErrorCode() + " Error Message : " + RestErrorCode.NO_USERNAME_SUPPLIED.getErrorMessage() +" createWallet : " + walletDetails.getUserName());
			throw new RestException(RestErrorCode.NO_USERNAME_SUPPLIED.getErrorMessage(),RestErrorCode.NO_USERNAME_SUPPLIED.getErrorCode());
		}
		
		if(CommonValidator.isBlank(walletDetails.getCurrency())) {
			logger.error("Error Code : " + RestErrorCode.NO_CURRENCY_SUPPLIED.getErrorCode() + " Error Message : " + RestErrorCode.NO_CURRENCY_SUPPLIED.getErrorMessage() +" createWallet : " + walletDetails.getUserName());
			throw new RestException(RestErrorCode.NO_CURRENCY_SUPPLIED.getErrorMessage(),RestErrorCode.NO_CURRENCY_SUPPLIED.getErrorCode());
		}
		
		validateUserAccess(walletDetails.getUserName());
		
		try{
	    walletService.createWallet(walletDetails.getUserName(), walletDetails.getCurrency());
		 
        } catch(WalletServiceException exception) {
			
			if(exception.getErrorCode() == ServiceErrorCode.INTERNAL_SERVER_ERROR.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.INTERNAL_SERVER_ERROR.getErrorCode() + " Error Message : " + RestErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage() +" createWallet : " + walletDetails.getUserName());
				throw new RestException(RestErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage(),RestErrorCode.INTERNAL_SERVER_ERROR.getErrorCode());   	
			}
		}
			
	}
	
	@CrossOrigin
	@PostMapping("/wallets/update/")
	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateWallet(@RequestBody UpdateWalletRequest updateWalletRequest) throws RestException {
		
		
	}
	
	
	@CrossOrigin
	@DeleteMapping("/wallets/delete")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteWallet(@RequestBody WalletDetails walletDetails) throws RestException {
		if(CommonValidator.isBlank(walletDetails.getUserName())) {
			logger.error("Error Code : " + RestErrorCode.NO_USERNAME_SUPPLIED.getErrorCode() + " Error Message : " + RestErrorCode.NO_USERNAME_SUPPLIED.getErrorMessage() +" createWallet : " + walletDetails.getUserName());
			throw new RestException(RestErrorCode.NO_USERNAME_SUPPLIED.getErrorMessage(),RestErrorCode.NO_USERNAME_SUPPLIED.getErrorCode());
		}
		
		if(CommonValidator.isBlank(walletDetails.getCurrency())) {
			logger.error("Error Code : " + RestErrorCode.NO_CURRENCY_SUPPLIED.getErrorCode() + " Error Message : " + RestErrorCode.NO_CURRENCY_SUPPLIED.getErrorMessage() +" createWallet : " + walletDetails.getUserName());
			throw new RestException(RestErrorCode.NO_CURRENCY_SUPPLIED.getErrorMessage(),RestErrorCode.NO_CURRENCY_SUPPLIED.getErrorCode());
		}
		
		validateUserAccess(walletDetails.getUserName());
		try{
		    walletService.deleteWallet(walletDetails.getUserName(),walletDetails.getCurrency());
           } catch(WalletServiceException exception) {
        	   if(exception.getErrorCode() == ServiceErrorCode.WALLET_DOES_NOT_EXIST.getErrorCode()) {
   				logger.error("Error Code : " + RestErrorCode.WALLET_DOES_NOT_EXIST.getErrorCode() + " Error Message : " + RestErrorCode.WALLET_DOES_NOT_EXIST.getErrorMessage() +" GetWallet : " + walletDetails.toString());
   				throw new RestException(RestErrorCode.WALLET_DOES_NOT_EXIST.getErrorMessage(),RestErrorCode.WALLET_DOES_NOT_EXIST.getErrorCode());   	
   			}
			if(exception.getErrorCode() == ServiceErrorCode.INTERNAL_SERVER_ERROR.getErrorCode()) {
				logger.error("Error Code : " + RestErrorCode.INTERNAL_SERVER_ERROR.getErrorCode() + " Error Message : " + RestErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage() +" createWallet : " + walletDetails.getUserName());
				throw new RestException(RestErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage(),RestErrorCode.INTERNAL_SERVER_ERROR.getErrorCode());   	
			}
		}
	}
	
	
	

	
	public void setWalletService(WalletService walletService) {
		this.walletService = walletService;
	}



	public void setApplicationDataRepository(ApplicationDataRepository applicationDataRepository) {
		this.applicationDataRepository = applicationDataRepository;
	}

	

}
