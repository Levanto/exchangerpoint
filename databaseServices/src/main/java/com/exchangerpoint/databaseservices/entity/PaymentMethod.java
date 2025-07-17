package com.exchangerpoint.databaseservices.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PAYMENT_METHOD database table.
 * 
 */
@Entity
@Table(name="PAYMENT_METHOD")
@NamedQuery(name="PaymentMethod.findAll", query="SELECT p FROM PaymentMethod p")
public class PaymentMethod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="METHOD_ID")
	private int methodId;

	@Column(name="CURRENCY")
	private String currency;

	@Column(name="DEPOSIT_FEES")
	private double depositFees;

	@Column(name="METHOD_STATUS")
	private String methodStatus;

	@Column(name="MINIMUM_AMOUNT")
	private double minimumAmount;

	@Column(name="NAME")
	private String name;
	
	@Column(name="GATEWAY")
	private String gateway;

	@Column(name="ID_VERIFICATION")
	private boolean idverification;

	@Column(name="ADDRESS_VERIFICATION")
	private boolean addressVerification;
	
	@Column(name="CARD_VERIFICATION")
	private boolean cardVerification;
	
	@Column(name="WITHDRAW_FEES")
	private double withdrawFees;

	public PaymentMethod() {
	}

	public int getMethodId() {
		return this.methodId;
	}

	public void setMethodId(int methodId) {
		this.methodId = methodId;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getDepositFees() {
		return this.depositFees;
	}

	public void setDepositFees(double depositFees) {
		this.depositFees = depositFees;
	}

	public String getMethodStatus() {
		return this.methodStatus;
	}

	public void setMethodStatus(String methodStatus) {
		this.methodStatus = methodStatus;
	}

	public double getMinimumAmount() {
		return this.minimumAmount;
	}

	public void setMinimumAmount(double minimumAmount) {
		this.minimumAmount = minimumAmount;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public boolean isIdverification() {
		return idverification;
	}

	public void setIdverification(boolean idverification) {
		this.idverification = idverification;
	}

	public boolean isAddressVerification() {
		return addressVerification;
	}

	public void setAddressVerification(boolean addressVerification) {
		this.addressVerification = addressVerification;
	}

	public boolean isCardVerification() {
		return cardVerification;
	}

	public void setCardVerification(boolean cardVerification) {
		this.cardVerification = cardVerification;
	}

	public double getWithdrawFees() {
		return this.withdrawFees;
	}

	public void setWithdrawFees(double withdrawFees) {
		this.withdrawFees = withdrawFees;
	}

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}
	
	

}