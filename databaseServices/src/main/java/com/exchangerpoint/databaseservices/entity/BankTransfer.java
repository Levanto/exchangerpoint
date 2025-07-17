package com.exchangerpoint.databaseservices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the BANK_TRANSFER database table.
 * 
 */
@Entity
@Table(name="BANK_TRANSFER")
@NamedQuery(name="BankTransfer.findAll", query="SELECT b FROM BankTransfer b")
public class BankTransfer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TRANSFER_NUMBER")
	private String transferNumber;
	
	@Column(name="USER_NAME")
	private String userName;
	

	@Column(name="ACCOUNT_HOLDER_NAME")
	private String accountHolderName;

	@Column(name="ACCOUNT_NUMBER")
	private String accountNumber;

	@Column(name="AMOUNT")
	private double amount;

	@Column(name="CREATED_DATE")
	private Timestamp createdDate;

	@Column(name="CURRENCY")
	private String currency;

	@Column(name="FEES")
	private double fees;

	@Column(name="IFSC_CODE")
	private String ifscCode;

	@Column(name="MODIFIED_DATE")
	private Timestamp modifiedDate;

	@Column(name="REFERENCE")
	private String reference;

	@Column(name="TRANSFER_STATUS")
	private String transferStatus;

	@Column(name="TRANSFER_TYPE")
	private String transferType;

	@Column(name="USER_EMAIL")
	private String userEmail;

	@Column(name="USER_PHONE")
	private String userPhone;

	@Column(name="VERIFY_CODE")
	private String verifyCode;

	//bi-directional many-to-one association to UserAccount
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="USER_NAME" ,insertable=false, updatable=false)
	private UserAccount userAccount;

	public BankTransfer() {
	}

	public String getTransferNumber() {
		return this.transferNumber;
	}

	public void setTransferNumber(String transferNumber) {
		this.transferNumber = transferNumber;
	}

	public String getAccountHolderName() {
		return this.accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getFees() {
		return this.fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}

	public String getIfscCode() {
		return this.ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getTransferStatus() {
		return this.transferStatus;
	}

	public void setTransferStatus(String transferStatus) {
		this.transferStatus = transferStatus;
	}

	public String getTransferType() {
		return this.transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getVerifyCode() {
		return this.verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}