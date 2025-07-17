package com.exchangerpoint.databaseservices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the CRYPTO_TRADE database table.
 * 
 */
@Entity
@Table(name="CRYPTO_TRADE")
@NamedQuery(name="CryptoTrade.findAll", query="SELECT c FROM CryptoTrade c")
public class CryptoTrade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TRADE_ID")
	private String tradeId;
	
	@Column(name="USER_NAME")
	private String userName;

	@Column(name="AMOUNT")
	private double amount;

	@Column(name="BASE_CURRENCY")
	private String baseCurrency;

	@Column(name="CREATED_DATE")
	private Timestamp createdDate;

	@Column(name="MODIFIED_DATE")
	private Timestamp modifiedDate;

	@Column(name="ORDER_TYPE")
	private String orderType;

	@Column(name="PRICE")
	private double price;

	@Column(name="QOUTE_CURRENCY")
	private String qouteCurrency;

	@Column(name="SUM")
	private double sum;

	@Column(name="TRADE_STATUS")
	private String tradeStatus;

	//bi-directional many-to-one association to UserAccount
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="USER_NAME" ,insertable=false, updatable=false)
	private UserAccount userAccount;

	public CryptoTrade() {
	}

	public String getTradeId() {
		return this.tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getBaseCurrency() {
		return this.baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getOrderType() {
		return this.orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getQouteCurrency() {
		return this.qouteCurrency;
	}

	public void setQouteCurrency(String qouteCurrency) {
		this.qouteCurrency = qouteCurrency;
	}

	public double getSum() {
		return this.sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public String getTradeStatus() {
		return this.tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
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