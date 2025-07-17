package com.exchangerpoint.databaseservices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the EXCHANGE_ORDER database table.
 * 
 */
@Entity
@Table(name="EXCHANGE_ORDER")
@NamedQuery(name="ExchangeOrder.findAll", query="SELECT e FROM ExchangeOrder e")
public class ExchangeOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ORDER_NUMBER")
	private String orderNumber;
	
	@Column(name="USER_NAME")
	private String userName;
	
	@Column(name="AMOUNT")
	private double amount;

	@Column(name="BALANCE")
	private double balance;

	@Column(name="CREATED_DATE")
	private Timestamp createdDate;

	@Column(name="FEES")
	private double fees;

	@Column(name="FROM_CURRENCY")
	private String fromCurrency;

	@Column(name="MODIFIED_DATE")
	private Timestamp modifiedDate;

	@Column(name="ORDER_STATUS")
	private String orderStatus;

	@Column(name="TO_CURRENCY")
	private String toCurrency;

	//bi-directional many-to-one association to UserAccount
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="USER_NAME" ,insertable=false, updatable=false)
	private UserAccount userAccount;

	public ExchangeOrder() {
	}

	public String getOrderNumber() {
		return this.orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getBalance() {
		return this.balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
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

	public String getFromCurrency() {
		return this.fromCurrency;
	}

	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getToCurrency() {
		return this.toCurrency;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
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