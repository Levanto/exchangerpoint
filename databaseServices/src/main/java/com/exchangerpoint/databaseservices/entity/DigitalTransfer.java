package com.exchangerpoint.databaseservices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the DIGITAL_TRANSFER database table.
 * 
 */
@Entity
@Table(name="DIGITAL_TRANSFER")
@NamedQuery(name="DigitalTransfer.findAll", query="SELECT d FROM DigitalTransfer d")
public class DigitalTransfer implements Serializable {
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

	@Column(name="FROM_ACCOUNT")
	private String fromAccount;

	@Column(name="MODIFIED_DATE")
	private Timestamp modifiedDate;

	@Column(name="REFERANCE")
	private String referance;

	@Column(name="TO_ACCOUNT")
	private String toAccount;

	@Column(name="TRANSFER_STATUS")
	private String transferStatus;

	@Column(name="TRANSFER_TYPE")
	private String transferType;

	@Column(name="USER_EMAIL")
	private String userEmail;

	

	//bi-directional many-to-one association to UserAccount
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="USER_NAME" ,insertable=false, updatable=false)
	private UserAccount userAccount;


	@Column(name="VERIFY_CODE")
	private String verifyCode;

	public DigitalTransfer() {
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

	public String getFromAccount() {
		return this.fromAccount;
	}

	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getReferance() {
		return this.referance;
	}

	public void setReferance(String referance) {
		this.referance = referance;
	}

	public String getToAccount() {
		return this.toAccount;
	}

	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
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

	

	

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public String getVerifyCode() {
		return this.verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}