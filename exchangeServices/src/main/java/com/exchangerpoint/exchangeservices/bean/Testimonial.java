package com.exchangerpoint.exchangeservices.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Testimonial {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="MESSAGE")
	private String message;
	
	@Column(name="SENT_ECURRENCY")
	private String sentEurrency;
	
	@Column(name="RECEIVED_ECURRENCY")
	private String receivedEcurrency;

	@Column(name="CREATED_DATE")
	private Timestamp createdDate;
	
	@Column(name="STATUS")
	private String status;
	@Column(name = "GRECAPTCHARESPONSE")
	private String grecaptcharesponse;

	public String getGrecaptcharesponse() {
		return grecaptcharesponse;
	}

	public void setGrecaptcharesponse(String grecaptcharesponse) {
		this.grecaptcharesponse = grecaptcharesponse;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSentEurrency() {
		return sentEurrency;
	}

	public void setSentEurrency(String sentEurrency) {
		this.sentEurrency = sentEurrency;
	}

	public String getReceivedEcurrency() {
		return receivedEcurrency;
	}

	public void setReceivedEcurrency(String receivedEcurrency) {
		this.receivedEcurrency = receivedEcurrency;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Testimonial() {
	}

}
