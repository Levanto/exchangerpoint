package com.exchangerpoint.databaseservices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the CRYPTO_CURRENCY database table.
 * 
 */
@Entity
@Table(name="CRYPTO_CURRENCY")
@NamedQuery(name="CryptoCurrency.findAll", query="SELECT c FROM CryptoCurrency c")
public class CryptoCurrency implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SYMBOL")
	private String symbol;

	@Column(name="BUY_FEES")
	private double buyFees;

	@Column(name="CATEGORY")
	private String category;

	@Column(name="CREATED_DATE")
	private Timestamp createdDate;

	@Column(name="CURRENCY_STATUS")
	private String currencyStatus;

	@Column(name="DEPOSIT")
	private String deposit;

	@Column(name="DEPOSIT_FEES")
	private double depositFees;

	@Column(name="MODIFIED_DATE")
	private Timestamp modifiedDate;

	@Column(name="NAME")
	private String name;

	@Column(name="SELL_FEES")
	private double sellFees;

	@Column(name="WITHDRAW")
	private String withdraw;

	@Column(name="WITHDRAW_FEES")
	private double withdrawFees;

	public CryptoCurrency() {
	}

	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public double getBuyFees() {
		return this.buyFees;
	}

	public void setBuyFees(double buyFees) {
		this.buyFees = buyFees;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getCurrencyStatus() {
		return this.currencyStatus;
	}

	public void setCurrencyStatus(String currencyStatus) {
		this.currencyStatus = currencyStatus;
	}

	public String getDeposit() {
		return this.deposit;
	}

	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}

	public double getDepositFees() {
		return this.depositFees;
	}

	public void setDepositFees(double depositFees) {
		this.depositFees = depositFees;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSellFees() {
		return this.sellFees;
	}

	public void setSellFees(double sellFees) {
		this.sellFees = sellFees;
	}

	public String getWithdraw() {
		return this.withdraw;
	}

	public void setWithdraw(String withdraw) {
		this.withdraw = withdraw;
	}

	public double getWithdrawFees() {
		return this.withdrawFees;
	}

	public void setWithdrawFees(double withdrawFees) {
		this.withdrawFees = withdrawFees;
	}

}