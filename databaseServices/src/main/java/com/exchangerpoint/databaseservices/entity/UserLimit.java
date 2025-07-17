package com.exchangerpoint.databaseservices.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USER_LIMIT database table.
 * 
 */
@Entity
@Table(name="USER_LIMIT")
@NamedQuery(name="UserLimit.findAll", query="SELECT u FROM UserLimit u")
public class UserLimit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="USER_NAME")
	private String userName;

	@Column(name="CURRENCY")
	private String currency;

	@Column(name="DAILY_DEPOSIT_LIMIT")
	private int dailyDepositLimit;

	@Column(name="DAILY_WITHDRAW_LIMIT")
	private int dailyWithdrawLimit;

	@Column(name="DEPOSIT_ALLOWED_LIMIT")
	private int depositAllowedLimit;

	//bi-directional one-to-one association to UserAccount
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="USER_NAME" ,insertable=false, updatable=false)
	private UserAccount userAccount;

	@Column(name="WITHDRAW_ALLOWED_LIMIT")
	private int withdrawAllowedLimit;

	public UserLimit() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getDailyDepositLimit() {
		return this.dailyDepositLimit;
	}

	public void setDailyDepositLimit(int dailyDepositLimit) {
		this.dailyDepositLimit = dailyDepositLimit;
	}

	public int getDailyWithdrawLimit() {
		return this.dailyWithdrawLimit;
	}

	public void setDailyWithdrawLimit(int dailyWithdrawLimit) {
		this.dailyWithdrawLimit = dailyWithdrawLimit;
	}

	public int getDepositAllowedLimit() {
		return this.depositAllowedLimit;
	}

	public void setDepositAllowedLimit(int depositAllowedLimit) {
		this.depositAllowedLimit = depositAllowedLimit;
	}

	
	public int getWithdrawAllowedLimit() {
		return this.withdrawAllowedLimit;
	}

	public void setWithdrawAllowedLimit(int withdrawAllowedLimit) {
		this.withdrawAllowedLimit = withdrawAllowedLimit;
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