package com.exchangerpoint.databaseservices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the SUPPORT_REQUEST database table.
 * 
 */
@Entity
@Table(name="SUPPORT_REQUEST")
@NamedQuery(name="SupportRequest.findAll", query="SELECT s FROM SupportRequest s")
public class SupportRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="REQUEST_NUMBER")
	private String requestNumber;

	@Column(name="USER_NAME")
	private String userName;
	
	@Column(name="CATEGORY")
	private String category;

	@Column(name="CREATED_DATE")
	private Timestamp createdDate;

	@Column(name="MESSAGE")
	private String message;

	@Column(name="MODIFIED_DATE")
	private Timestamp modifiedDate;

	@Column(name="REQUEST_STATUS")
	private String requestStatus;

	@Column(name="SUBJECT")
	private String subject;
	
	

	//bi-directional many-to-one association to UserAccount
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="USER_NAME" ,insertable=false, updatable=false)
	private UserAccount userAccount;

	public SupportRequest() {
	}

	public String getRequestNumber() {
		return this.requestNumber;
	}

	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
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

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getRequestStatus() {
		return this.requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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