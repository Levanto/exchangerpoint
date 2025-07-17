package com.exchangerpoint.databaseservices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the CRYPTO_TRANSFER database table.
 * 
 */
@Entity
@Table(name="CRYPTO_TRANSFER")
@NamedQuery(name="CryptoTransfer.findAll", query="SELECT c FROM CryptoTransfer c")
public class CryptoTransfer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TRANSFER_NUMBER")
	private String transferNumber;

	@Column(name="USER_NAME")
	private String userName;
	
	@Column(name="AMOUNT")
	private double amount;

	@Column(name="CREATED_DATE")
	private Timestamp createdDate;

	@Column(name="FEES")
	private double fees;

	@Column(name="FROM_ADDRESS")
	private String fromAddress;

	@Column(name="MODIFIED_DATE")
	private Timestamp modifiedDate;

	@Column(name="SYMBOL")
	private String symbol;

	@Column(name="TAG")
	private String tag;

	@Column(name="TO_ADDRESS")
	private String toAddress;

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

	public CryptoTransfer() {
	}

	public String getTransferNumber() {
		return this.transferNumber;
	}

	public void setTransferNumber(String transferNumber) {
		this.transferNumber = transferNumber;
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

	public double getFees() {
		return this.fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}

	public String getFromAddress() {
		return this.fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getToAddress() {
		return this.toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
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