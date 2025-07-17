package com.exchangerpoint.walletservices.razorpay;

public class VirtualAccountData {
	
	private String virtualAccountId;
	private String virtualAccountNumber;
	private String ifscCode;
	private String AccountHolderName;
	private String bankName;
	private String upiId;
	
	public String getVirtualAccountId() {
		return virtualAccountId;
	}
	public void setVirtualAccountId(String virtualAccountId) {
		this.virtualAccountId = virtualAccountId;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getAccountHolderName() {
		return AccountHolderName;
	}
	public void setAccountHolderName(String accountHolderName) {
		AccountHolderName = accountHolderName;
	}
	
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getVirtualAccountNumber() {
		return virtualAccountNumber;
	}
	public void setVirtualAccountNumber(String virtualAccountNumber) {
		this.virtualAccountNumber = virtualAccountNumber;
	}
	public String getUpiId() {
		return upiId;
	}
	public void setUpiId(String upiId) {
		this.upiId = upiId;
	}
	
	

}
