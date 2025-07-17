package com.exchangerpoint.databaseservices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the MAIN_ACCOUNT database table.
 * 
 */
@Entity
@Table(name="MAIN_ACCOUNT")
@NamedQuery(name="MainAccount.findAll", query="SELECT m FROM MainAccount m")
public class MainAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CURRENCY")
	private String currency;

	@Column(name="ADDDRESS")
	private String address;

	@Column(name="FEE_BALANCE")
	private double feeBalance;

	@Column(name="MAIN_BALANCE")
	private double mainBalance;

	@Column(name="MODIFIED_DATE")
	private Timestamp modifiedDate;

	@Column(name="RESERVE_BALANCE")
	private double reserveBalance;

	@Column(name="TOTAL_BALANCE")
	private double totalBalance;

	public MainAccount() {
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getFeeBalance() {
		return this.feeBalance;
	}

	public void setFeeBalance(double feeBalance) {
		this.feeBalance = feeBalance;
	}

	public double getMainBalance() {
		return this.mainBalance;
	}

	public void setMainBalance(double mainBalance) {
		this.mainBalance = mainBalance;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public double getReserveBalance() {
		return this.reserveBalance;
	}

	public void setReserveBalance(double reserveBalance) {
		this.reserveBalance = reserveBalance;
	}

	public double getTotalBalance() {
		return this.totalBalance;
	}

	public void setTotalBalance(double totalBalance) {
		this.totalBalance = totalBalance;
	}

}