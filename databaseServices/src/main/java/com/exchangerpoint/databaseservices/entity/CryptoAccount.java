package com.exchangerpoint.databaseservices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the CRYPTO_ACCOUNT database table.
 * 
 */
@Entity
@Table(name="CRYPTO_ACCOUNT")
@NamedQuery(name="CryptoAccount.findAll", query="SELECT c FROM CryptoAccount c")
public class CryptoAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ACCOUNT_ID")
	private String accountId;

	@Column(name="USER_NAME")
	private String userName;
	
	@Column(name="ACCOUNT_STATUS")
	private String accountStatus;

	@Column(name="CREATED_DATE")
	private Timestamp createdDate;

	@Column(name="CURRENCY")
	private String currency;

	@Column(name="MODIFIED_DATE")
	private Timestamp modifiedDate;

	//bi-directional many-to-one association to UserAccount
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="USER_NAME" ,insertable=false, updatable=false)
	private UserAccount userAccount;

	public CryptoAccount() {
	}

	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
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

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
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