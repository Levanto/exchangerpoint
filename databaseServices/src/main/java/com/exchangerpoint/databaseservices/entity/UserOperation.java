package com.exchangerpoint.databaseservices.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USER_OPERATION database table.
 * 
 */
@Entity
@Table(name="USER_OPERATION")
@NamedQuery(name="UserOperation.findAll", query="SELECT u FROM UserOperation u")
public class UserOperation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_NAME")
	private String userName;

	@Column(name="DEPOSIT")
	private boolean deposit;

	@Column(name="WITHDRAW")
	private boolean withdraw;

	@Column(name="UPDATE_PROFILE")
	private boolean updateProfile;

	@Column(name="TRADE")
	private boolean trade;

	
	//bi-directional one-to-one association to UserAccount
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="USER_NAME")
	private UserAccount userAccount;

	public UserOperation() {
	}

	

	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public boolean isDeposit() {
		return deposit;
	}



	public void setDeposit(boolean deposit) {
		this.deposit = deposit;
	}



	public boolean isWithdraw() {
		return withdraw;
	}



	public void setWithdraw(boolean withdraw) {
		this.withdraw = withdraw;
	}



	public boolean isUpdateProfile() {
		return updateProfile;
	}



	public void setUpdateProfile(boolean updateProfile) {
		this.updateProfile = updateProfile;
	}



	public boolean isTrade() {
		return trade;
	}



	public void setTrade(boolean trade) {
		this.trade = trade;
	}



	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

}