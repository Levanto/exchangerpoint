package com.exchangerpoint.databaseservices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the RAZORPAY_ACCOUNT database table.
 * 
 */
@Entity
@Table(name="RAZORPAY_ACCOUNT")
@NamedQuery(name="RazorpayAccount.findAll", query="SELECT r FROM RazorpayAccount r")
public class RazorpayAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ACCOUNT_ID")
	private String accountId;
	
	@Column(name="USER_NAME")
	private String userName;

	@Column(name="ACCOUNT_NUMBER")
	private String accountNumber;

	@Column(name="ACCOUNT_STATUS")
	private String accountStatus;

	@Column(name="BANK_NAME")
	private String bankName;

	@Column(name="CREATED_DATE")
	private Timestamp createdDate;

	@Column(name="IFSC_CODE")
	private String ifscCode;

	@Column(name="UPI_ID")
	private String upiId;
	
	@Column(name="BANK_FEE")
	private double bankFee;

	

	
	//bi-directional one-to-one association to UserAccount
			@ManyToOne(fetch = FetchType.LAZY)
			@JoinColumn(name="USER_NAME" ,insertable=false, updatable=false)
			private UserAccount userAccount;
			
			
	public RazorpayAccount() {
	}

	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountStatus() {
		return this.accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getIfscCode() {
		return this.ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getUpiId() {
		return this.upiId;
	}

	public void setUpiId(String upiId) {
		this.upiId = upiId;
	}

	

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public double getBankFee() {
		return bankFee;
	}

	public void setBankFee(double bankFee) {
		this.bankFee = bankFee;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	

}