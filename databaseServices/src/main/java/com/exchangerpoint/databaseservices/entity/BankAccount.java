package com.exchangerpoint.databaseservices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the BANK_ACCOUNT database table.
 * 
 */
@Entity
@Table(name="BANK_ACCOUNT")
@NamedQuery(name="BankAccount.findAll", query="SELECT b FROM BankAccount b")
public class BankAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ACCOUNT_NUMBER")
	private String accountNumber;
	
	@Column(name="USER_NAME")
	private String userName;

	@Column(name="ACCOUNT_HOLDER_NAME")
	private String accountHolderName;

	@Column(name="ACCOUNT_STATUS")
	private String accountStatus;

	@Column(name="CREATED_DATE")
	private Timestamp createdDate;

	@Column(name="IFSC_CODE")
	private String ifscCode;

	@Column(name="MODIFIED_DATE")
	private Timestamp modifiedDate;

	//bi-directional many-to-one association to UserAccount
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="USER_NAME" ,insertable=false, updatable=false)
		private UserAccount userAccount;
	public BankAccount() {
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountHolderName() {
		return this.accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getAccountStatus() {
		return this.accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
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

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public UserAccount getUserAccount() {
		return userAccount;
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