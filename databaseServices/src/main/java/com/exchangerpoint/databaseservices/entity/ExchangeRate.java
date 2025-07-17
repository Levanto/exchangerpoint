package com.exchangerpoint.databaseservices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the EXCHANGE_RATE database table.
 * 
 */
@Entity
@Table(name="EXCHANGE_RATE")
@NamedQuery(name="ExchangeRate.findAll", query="SELECT e FROM ExchangeRate e")
public class ExchangeRate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;

	@Column(name="CREATED_DATE")
	private Timestamp createdDate;

	@Column(name="EXCHANGE_FEES")
	private double exchangeFees;

	@Column(name="EXCHANGE_STATUS")
	private String exchangeStatus;

	@Column(name="FROM_CURRENCY")
	private String fromCurrency;

	@Column(name="MODIFIED_DATE")
	private Timestamp modifiedDate;

	@Column(name="TO_CURRENCY")
	private String toCurrency;

	public ExchangeRate() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public double getExchangeFees() {
		return this.exchangeFees;
	}

	public void setExchangeFees(double exchangeFees) {
		this.exchangeFees = exchangeFees;
	}

	public String getExchangeStatus() {
		return this.exchangeStatus;
	}

	public void setExchangeStatus(String exchangeStatus) {
		this.exchangeStatus = exchangeStatus;
	}

	public String getFromCurrency() {
		return this.fromCurrency;
	}

	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getToCurrency() {
		return this.toCurrency;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

}