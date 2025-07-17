package com.exchangerpoint.databaseservices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the FIAT_CURRENCY database table.
 * 
 */
@Entity
@Table(name="FIAT_CURRENCY")
@NamedQuery(name="FiatCurrency.findAll", query="SELECT f FROM FiatCurrency f")
public class FiatCurrency implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CURRENCY")
	private String currency;

	@Column(name="BANK_DEPOSIT")
	private String bankDeposit;

	@Column(name="CARD_DEPOSIT")
	private String cardDeposit;

	@Column(name="CREATED_DATE")
	private Timestamp createdDate;

	@Column(name="CURRENCY_NAME")
	private String currencyName;

	@Column(name="CURRENCY_STATUS")
	private String currencyStatus;

	

	@Column(name="MODIFIED_DATE")
	private Timestamp modifiedDate;

	@Column(name="RIMITANCE_DEPOSIT")
	private String rimitanceDeposit;
	
	@Column(name="PAYMENT_GATEWAY_DEPOSIT")
	private String paymentGatewayDeposit;
	
	
	@Column(name="BANK_WITHDRAW")
	private String bankWithdraw;
	
	@Column(name="COUNTRY")
	private String country;
	
	@Column(name="BANK_DEPOSIT_FEE")
	private int bankDepositFee;
	
	@Column(name="WITHDRAW_LIMIT")
	private int withdrawLimit;
	
	@Column(name="CREDIT_CARD_FEE")
	private int creditCardFee;
	
	@Column(name="PREPAID_CARD_FEE")
	private int prepaidCardFee;
	
	@Column(name="BANK_WITHDRAW_FEE")
	private int bankWithdrawFee;
	
	@Column(name="PAYMENT_GATEWAY_FEE")
	private int paymentGatewayFee;
	
	@Column(name="DEPOSIT_LIMIT")
	private int depositLimit;

	public FiatCurrency() {
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getCurrencyName() {
		return this.currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getCurrencyStatus() {
		return this.currencyStatus;
	}

	public void setCurrencyStatus(String currencyStatus) {
		this.currencyStatus = currencyStatus;
	}

	

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	

	public String getBankDeposit() {
		return bankDeposit;
	}

	public void setBankDeposit(String bankDeposit) {
		this.bankDeposit = bankDeposit;
	}

	public String getCardDeposit() {
		return cardDeposit;
	}

	public void setCardDeposit(String cardDeposit) {
		this.cardDeposit = cardDeposit;
	}

	public String getRimitanceDeposit() {
		return rimitanceDeposit;
	}

	public void setRimitanceDeposit(String rimitanceDeposit) {
		this.rimitanceDeposit = rimitanceDeposit;
	}

	
	public String getPaymentGatewayDeposit() {
		return paymentGatewayDeposit;
	}

	public void setPaymentGatewayDeposit(String paymentGatewayDeposit) {
		this.paymentGatewayDeposit = paymentGatewayDeposit;
	}

	public String getBankWithdraw() {
		return bankWithdraw;
	}

	public void setBankWithdraw(String bankWithdraw) {
		this.bankWithdraw = bankWithdraw;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getDepositLimit() {
		return depositLimit;
	}

	public void setDepositLimit(int depositLimit) {
		this.depositLimit = depositLimit;
	}

	public int getWithdrawLimit() {
		return withdrawLimit;
	}

	public void setWithdrawLimit(int withdrawLimit) {
		this.withdrawLimit = withdrawLimit;
	}

	public int getBankDepositFee() {
		return bankDepositFee;
	}

	public void setBankDepositFee(int bankDepositFee) {
		this.bankDepositFee = bankDepositFee;
	}

	public int getCreditCardFee() {
		return creditCardFee;
	}

	public void setCreditCardFee(int creditCardFee) {
		this.creditCardFee = creditCardFee;
	}

	public int getPrepaidCardFee() {
		return prepaidCardFee;
	}

	public void setPrepaidCardFee(int prepaidCardFee) {
		this.prepaidCardFee = prepaidCardFee;
	}

	public int getBankWithdrawFee() {
		return bankWithdrawFee;
	}

	public void setBankWithdrawFee(int bankWithdrawFee) {
		this.bankWithdrawFee = bankWithdrawFee;
	}

	public int getPaymentGatewayFee() {
		return paymentGatewayFee;
	}

	public void setPaymentGatewayFee(int paymentGatewayFee) {
		this.paymentGatewayFee = paymentGatewayFee;
	}

	

}