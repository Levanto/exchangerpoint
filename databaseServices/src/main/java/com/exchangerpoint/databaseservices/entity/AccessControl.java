package com.exchangerpoint.databaseservices.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ACCESS_CONTROL database table.
 * 
 */
@Entity
@Table(name="ACCESS_CONTROL")
@NamedQuery(name="AccessControl.findAll", query="SELECT a FROM AccessControl a")
public class AccessControl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private String id;

	@Column(name="EMAIL")
	private String email;

	@Column(name="IP_ADDRESS")
	private String ipAddress;
	@Column(name="PHONE")
	private String phone;

	public AccessControl() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}