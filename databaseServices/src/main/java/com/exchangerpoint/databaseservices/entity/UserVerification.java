package com.exchangerpoint.databaseservices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the USER_VERIFICATION database table.
 * 
 */
@Entity
@Table(name="USER_VERIFICATION")
@NamedQuery(name="UserVerification.findAll", query="SELECT u FROM UserVerification u")
public class UserVerification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_NAME")
	private String userName;

	@Column(name="ADDRESS")
	private boolean address;

	

	@Column(name="CARD")
	private boolean card;

	@Column(name="`COMMENT`")
	private String comment;

	@Column(name="EMAIL")
	private boolean email;

	@Column(name="ID")
	private boolean id;

	@Column(name="MODIFIED_DATE")
	private Timestamp modifiedDate;

	@Column(name="PHONE")
	private boolean phone;

	//bi-directional one-to-one association to UserAccount
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="USER_NAME")
	private UserAccount userAccount;

	public UserVerification() {
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}



	public boolean isAddress() {
		return address;
	}

	public void setAddress(boolean address) {
		this.address = address;
	}

	public boolean isCard() {
		return card;
	}

	public void setCard(boolean card) {
		this.card = card;
	}

	public boolean isEmail() {
		return email;
	}

	public void setEmail(boolean email) {
		this.email = email;
	}

	public boolean isId() {
		return id;
	}

	public void setId(boolean id) {
		this.id = id;
	}

	public boolean isPhone() {
		return phone;
	}

	public void setPhone(boolean phone) {
		this.phone = phone;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

}