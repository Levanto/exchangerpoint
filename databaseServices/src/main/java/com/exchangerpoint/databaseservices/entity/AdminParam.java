package com.exchangerpoint.databaseservices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the ADMIN_PARAM database table.
 * 
 */
@Entity
@Table(name="ADMIN_PARAM")
@NamedQuery(name="AdminParam.findAll", query="SELECT a FROM AdminParam a")
public class AdminParam implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NAME")
	private String name;

	@Column(name="CREATED_DATE")
	private Timestamp createdDate;

	@Column(name="ENCRIPTION")
	private String encription;

	@Column(name="MODIFIED_DATE")
	private Timestamp modifiedDate;

	@Column(name="PARAM_STATUS")
	private String paramStatus;
	
	@Column(name="VALUE")
	private String value;

	public AdminParam() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getEncription() {
		return this.encription;
	}

	public void setEncription(String encription) {
		this.encription = encription;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getParamStatus() {
		return this.paramStatus;
	}

	public void setParamStatus(String paramStatus) {
		this.paramStatus = paramStatus;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}