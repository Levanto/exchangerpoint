package com.exchangerpoint.walletservices;

import java.util.List;

import com.exchangerpoint.databaseservices.entity.RazorpayAccount;
import com.exchangerpoint.databaseservices.entity.UserAccount;
import com.exchangerpoint.databaseservices.entity.UserWallet;
import com.exchangerpoint.walletservices.exception.WalletServiceException;
import com.exchangerpoint.walletservices.razorpay.RazorpayException;

public interface WalletService {

	public List<UserWallet> getWallets(String userName) throws WalletServiceException;

	
	public  RazorpayAccount createVirtualAccount (UserAccount userAccount) throws RazorpayException ;
	public  void closeVirtualAccount (String id) throws RazorpayException ;


	public List<UserWallet> getWallets();


	public UserWallet getWallet(String userName, String currency) throws WalletServiceException;


	public void createWallet(String userName, String currency) throws WalletServiceException;


	public void deleteWallet(String userName, String currency) throws WalletServiceException;


	
	
	
	

}
