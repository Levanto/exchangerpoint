package com.exchangerpoint.restservices.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.exchangerpoint.commonservices.common.ApplicationDataRepository;
import com.exchangerpoint.genericservices.service.GenericService;
import com.exchangerpoint.userservices.UserService;
import com.exchangerpoint.walletservices.WalletService;

public class RestServiceImpl implements RestService {
	@Autowired
	private UserService userService;

	@Autowired
	private GenericService genericService;
	
	@Autowired
	private WalletService walletService;
	@Autowired
	private ApplicationDataRepository applicationDataRepository;
	
    protected final Log logger = LogFactory.getLog(RestServiceImpl.class);
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setGenericService(GenericService genericService) {
		this.genericService = genericService;
	}
	public void setApplicationDataRepository(ApplicationDataRepository applicationDataRepository) {
		this.applicationDataRepository = applicationDataRepository;
	}
	public void setWalletService(WalletService walletService) {
		this.walletService = walletService;
	}
	

}
