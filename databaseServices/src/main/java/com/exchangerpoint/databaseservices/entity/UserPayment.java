package com.exchangerpoint.databaseservices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the USER_PAYMENT database table.
 * 
 */
@Entity
@Table(name="USER_PAYMENT")
@NamedQuery(name="UserPayment.findAll", query="SELECT u FROM UserPayment u")
public class UserPayment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PAYMENT_ID")
	private String paymentId;
	
	@Column(name="USER_NAME")
	private String userName;

	@Column(name="AMOUNT")
	private double amount;

	@Column(name="`COMMENT`")
	private String comment;

	@Column(name="CURRENCY")
	private String currency;

	@Column(name="PAYMENT_DATE")
	private Timestamp paymentDate;

	@Column(name="PAYMENT_INFO")
	private String paymentInfo;

	@Column(name="PAYMENT_METHOD")
	private String paymentMethod;

	@Column(name="PAYMENT_MODE")
	private String paymentMode;

	@Column(name="PAYMENT_STATUS")
	private String paymentStatus;

	

	//bi-directional one-to-one association to UserAccount
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="USER_NAME" ,insertable=false, updatable=false)
		private UserAccount userAccount;
		
	public UserPayment() {
	}

	public String getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Timestamp getPaymentDate() {
		return this.paymentDate;
	}

	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentInfo() {
		return this.paymentInfo;
	}

	public void setPaymentInfo(String paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	public String getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentMode() {
		return this.paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPaymentStatus() {
		return this.paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
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