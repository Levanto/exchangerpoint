package com.exchangerpoint.databaseservices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the USER_WALLET database table.
 * 
 */
@Entity
@Table(name="USER_WALLET")
@NamedQuery(name="UserWallet.findAll", query="SELECT u FROM UserWallet u")
public class UserWallet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="WALLET_ID")
	private String walletId;
	
	@Column(name="USER_NAME")
	private String userName;

	@Column(name="AUTO_CONVERT")
	private boolean autoConvert;

	@Column(name="AVAILABLE_BALANCE")
	private double availableBalance;

	@Column(name="CREATED_DATE")
	private Timestamp createdDate;

	

	@Column(name="CURRENCY_SYMBOL")
	private String currencySymbol;

	@Column(name="MODIFIED_DATE")
	private Timestamp modifiedDate;

	@Column(name="PENDING_BALANCE")
	private double pendingBalance;

	@Column(name="RESERVE_BALANCE")
	private double reserveBalance;

	@Column(name="TOTAL_BALANCE")
	private double totalBalance;

	@Column(name="WALLET_STATUS")
	private String walletStatus;

	
	//bi-directional one-to-one association to UserAccount
	 @ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="USER_NAME" ,insertable=false, updatable=false)
		private UserAccount userAccount;

	public UserWallet() {
	}

	public String getWalletId() {
		return this.walletId;
	}

	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}

	public boolean getAutoConvert() {
		return this.autoConvert;
	}

	public void setAutoConvert(boolean autoConvert) {
		this.autoConvert = autoConvert;
	}

	public double getAvailableBalance() {
		return this.availableBalance;
	}

	public void setAvailableBalance(double availableBalance) {
		this.availableBalance = availableBalance;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	

	public String getCurrencySymbol() {
		return this.currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public double getPendingBalance() {
		return this.pendingBalance;
	}

	public void setPendingBalance(double pendingBalance) {
		this.pendingBalance = pendingBalance;
	}

	public double getReserveBalance() {
		return this.reserveBalance;
	}

	public void setReserveBalance(double reserveBalance) {
		this.reserveBalance = reserveBalance;
	}

	public double getTotalBalance() {
		return this.totalBalance;
	}

	public void setTotalBalance(double totalBalance) {
		this.totalBalance = totalBalance;
	}

	public String getWalletStatus() {
		return this.walletStatus;
	}

	public void setWalletStatus(String walletStatus) {
		this.walletStatus = walletStatus;
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