package com.exchangerpoint.databaseservices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the CARD_ACCOUNT database table.
 * 
 */
@Entity
@Table(name="CARD_ACCOUNT")
@NamedQuery(name="CardAccount.findAll", query="SELECT s FROM CardAccount s")
public class CardAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="USER_NAME")
	private String userName;

	@Column(name="ACCOUNT_STATUS")
	private String accountStatus;

	@Column(name="CARD_LAST_DIGITS")
	private String cardLastDigits;

	@Column(name="CREATED_DATE")
	private Timestamp createdDate;

	
	
	@Column(name="CARD_FEE")
	private double cardFees;


	
	//bi-directional one-to-one association to UserAccount
			@ManyToOne(fetch = FetchType.LAZY)
			@JoinColumn(name="USER_NAME" ,insertable=false, updatable=false)
			private UserAccount userAccount;

	public CardAccount() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountStatus() {
		return this.accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getCardLastDigits() {
		return this.cardLastDigits;
	}

	public void setCardLastDigits(String cardLastDigits) {
		this.cardLastDigits = cardLastDigits;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
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

	public double getCardFees() {
		return cardFees;
	}

	public void setCardFees(double cardFees) {
		this.cardFees = cardFees;
	}
	

}