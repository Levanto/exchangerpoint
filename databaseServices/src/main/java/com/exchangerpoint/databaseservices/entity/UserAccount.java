package com.exchangerpoint.databaseservices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the USER_ACCOUNT database table.
 * 
 */
@Entity
@Table(name="USER_ACCOUNT")
@NamedQuery(name="UserAccount.findAll", query="SELECT u FROM UserAccount u")
public class UserAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_NAME")
	private String userName;

	@Column(name="ACCOUNT_STATUS")
	private String accountStatus;

	
	@Column(name="`COMMENT`")
	private String comment;

	@Column(name="COUNTRY")
	private String country;

	@Column(name="CREATED_DATE")
	private Timestamp createdDate;

	

	@Column(name="EMAIL")
	private String email;

	@Column(name="FAILED_LOGIN_ATTEMPTS")
	private int failedLoginAttempts;

	@Column(name="LAST_LOGIN_IP")
	private String lastLoginIp;

	@Column(name="LAST_LOGIN_TIME")
	private Timestamp lastLoginTime;

	@Column(name="MODIFIED_DATE")
	private Timestamp modifiedDate;

	@Column(name="NAME")
	private String name;

	@Column(name="PASSWORD")
	private String password;

	@Column(name="PHONE")
	private String phone;

	@Column(name="PIN")
	private String pin;

	@Column(name="ROLE")
	private String role;

	
	@Column(name="USER_FEE")
	private double userFee;

	//bi-directional many-to-one association to BankTransfer
	@OneToMany(mappedBy="userAccount",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<BankTransfer> bankTransfers;

	//bi-directional many-to-one association to BuysellOrder
	@OneToMany(mappedBy="userAccount",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<BuysellOrder> buysellOrders;

	//bi-directional many-to-one association to CryptoAccount
	@OneToMany(mappedBy="userAccount",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<CryptoAccount> cryptoAccounts;
	
	//bi-directional many-to-one association to CryptoAccount
	@OneToMany(mappedBy="userAccount",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<BankAccount> bankAccounts;

	//bi-directional many-to-one association to CryptoTrade
	@OneToMany(mappedBy="userAccount",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<CryptoTrade> cryptoTrades;

	//bi-directional many-to-one association to CryptoTransfer
	@OneToMany(mappedBy="userAccount", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<CryptoTransfer> cryptoTransfers;

	//bi-directional many-to-one association to DigitalAccount
	@OneToMany(mappedBy="userAccount",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<DigitalAccount> digitalAccounts;

	//bi-directional many-to-one association to ExchangeOrder
	@OneToMany(mappedBy="userAccount", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<ExchangeOrder> exchangeOrders;

	//bi-directional many-to-one association to SupportRequest
	@OneToMany(mappedBy="userAccount",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SupportRequest> supportRequests;

	//bi-directional one-to-one association to UserAddress
	@OneToOne(mappedBy="userAccount",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private UserAddress userAddress;

	//bi-directional one-to-one association to UserVerification
	@OneToOne(mappedBy="userAccount", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private UserVerification userVerification;
	
	//bi-directional one-to-one association to UserVerification
	@OneToOne(mappedBy="userAccount",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private UserOperation userOperation;


	//bi-directional many-to-one association to UserWallet
	@OneToMany(mappedBy="userAccount", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<UserWallet> userWallets;
	
	//bi-directional many-to-one association to UserLimit
	@OneToMany(mappedBy="userAccount", cascade = CascadeType.ALL)
	private List<UserLimit> userLimits;
	
	//bi-directional many-to-one association to UserLimit
	@OneToMany(mappedBy="userAccount", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<UserPayment> userPayments;
	
	
   //bi-directional many-to-one association to UserLimit
	@OneToMany(mappedBy="userAccount", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		private List<RazorpayAccount> razorpayAccounts;
		
		
		//bi-directional many-to-one association to UserLimit
	@OneToMany(mappedBy="userAccount",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
				private List<CardAccount> cardAccounts;
				
				//bi-directional many-to-one association to UserLimit
	@OneToMany(mappedBy="userAccount", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
				private List<DigitalTransfer> digitalTransfers;			

	public UserAccount() {
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAccountStatus() {
		return this.accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getFailedLoginAttempts() {
		return this.failedLoginAttempts;
	}

	public void setFailedLoginAttempts(int failedLoginAttempts) {
		this.failedLoginAttempts = failedLoginAttempts;
	}

	public String getLastLoginIp() {
		return this.lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Timestamp getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPin() {
		return this.pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	

	public List<BankTransfer> getBankTransfers() {
		return this.bankTransfers;
	}

	public void setBankTransfers(List<BankTransfer> bankTransfers) {
		this.bankTransfers = bankTransfers;
	}

	public BankTransfer addBankTransfer(BankTransfer bankTransfer) {
		getBankTransfers().add(bankTransfer);
		bankTransfer.setUserAccount(this);

		return bankTransfer;
	}

	public BankTransfer removeBankTransfer(BankTransfer bankTransfer) {
		getBankTransfers().remove(bankTransfer);
		bankTransfer.setUserAccount(null);

		return bankTransfer;
	}

	public List<BuysellOrder> getBuysellOrders() {
		return this.buysellOrders;
	}

	public void setBuysellOrders(List<BuysellOrder> buysellOrders) {
		this.buysellOrders = buysellOrders;
	}

	public BuysellOrder addBuysellOrder(BuysellOrder buysellOrder) {
		getBuysellOrders().add(buysellOrder);
		buysellOrder.setUserAccount(this);

		return buysellOrder;
	}

	public BuysellOrder removeBuysellOrder(BuysellOrder buysellOrder) {
		getBuysellOrders().remove(buysellOrder);
		buysellOrder.setUserAccount(null);

		return buysellOrder;
	}

	public List<CryptoAccount> getCryptoAccounts() {
		return this.cryptoAccounts;
	}

	public void setCryptoAccounts(List<CryptoAccount> cryptoAccounts) {
		this.cryptoAccounts = cryptoAccounts;
	}

	public CryptoAccount addCryptoAccount(CryptoAccount cryptoAccount) {
		getCryptoAccounts().add(cryptoAccount);
		cryptoAccount.setUserAccount(this);

		return cryptoAccount;
	}

	public CryptoAccount removeCryptoAccount(CryptoAccount cryptoAccount) {
		getCryptoAccounts().remove(cryptoAccount);
		cryptoAccount.setUserAccount(null);

		return cryptoAccount;
	}

	public List<BankAccount> getBankAccounts() {
		return this.bankAccounts;
	}

	public void setBankAccounts(List<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}

	public BankAccount addBankAccount(BankAccount bankAccount) {
		getBankAccounts().add(bankAccount);
		bankAccount.setUserAccount(this);

		return bankAccount;
	}

	public BankAccount removeBankAccount(BankAccount bankAccount) {
		getBankAccounts().remove(bankAccount);
		bankAccount.setUserAccount(null);

		return bankAccount;
	}

	
	public List<CryptoTrade> getCryptoTrades() {
		return this.cryptoTrades;
	}

	public void setCryptoTrades(List<CryptoTrade> cryptoTrades) {
		this.cryptoTrades = cryptoTrades;
	}

	public CryptoTrade addCryptoTrade(CryptoTrade cryptoTrade) {
		getCryptoTrades().add(cryptoTrade);
		cryptoTrade.setUserAccount(this);

		return cryptoTrade;
	}

	public CryptoTrade removeCryptoTrade(CryptoTrade cryptoTrade) {
		getCryptoTrades().remove(cryptoTrade);
		cryptoTrade.setUserAccount(null);

		return cryptoTrade;
	}

	public List<CryptoTransfer> getCryptoTransfers() {
		return this.cryptoTransfers;
	}

	public void setCryptoTransfers(List<CryptoTransfer> cryptoTransfers) {
		this.cryptoTransfers = cryptoTransfers;
	}

	public CryptoTransfer addCryptoTransfer(CryptoTransfer cryptoTransfer) {
		getCryptoTransfers().add(cryptoTransfer);
		cryptoTransfer.setUserAccount(this);

		return cryptoTransfer;
	}

	public CryptoTransfer removeCryptoTransfer(CryptoTransfer cryptoTransfer) {
		getCryptoTransfers().remove(cryptoTransfer);
		cryptoTransfer.setUserAccount(null);

		return cryptoTransfer;
	}

	public List<DigitalAccount> getDigitalAccounts() {
		return this.digitalAccounts;
	}

	public void setDigitalAccounts(List<DigitalAccount> digitalAccounts) {
		this.digitalAccounts = digitalAccounts;
	}

	public DigitalAccount addDigitalAccount(DigitalAccount digitalAccount) {
		getDigitalAccounts().add(digitalAccount);
		digitalAccount.setUserAccount(this);

		return digitalAccount;
	}

	public DigitalAccount removeDigitalAccount(DigitalAccount digitalAccount) {
		getDigitalAccounts().remove(digitalAccount);
		digitalAccount.setUserAccount(null);

		return digitalAccount;
	}

	public List<ExchangeOrder> getExchangeOrders() {
		return this.exchangeOrders;
	}

	public void setExchangeOrders(List<ExchangeOrder> exchangeOrders) {
		this.exchangeOrders = exchangeOrders;
	}

	public ExchangeOrder addExchangeOrder(ExchangeOrder exchangeOrder) {
		getExchangeOrders().add(exchangeOrder);
		exchangeOrder.setUserAccount(this);

		return exchangeOrder;
	}

	public ExchangeOrder removeExchangeOrder(ExchangeOrder exchangeOrder) {
		getExchangeOrders().remove(exchangeOrder);
		exchangeOrder.setUserAccount(null);

		return exchangeOrder;
	}

	public List<SupportRequest> getSupportRequests() {
		return this.supportRequests;
	}

	public void setSupportRequests(List<SupportRequest> supportRequests) {
		this.supportRequests = supportRequests;
	}

	public SupportRequest addSupportRequest(SupportRequest supportRequest) {
		getSupportRequests().add(supportRequest);
		supportRequest.setUserAccount(this);

		return supportRequest;
	}

	public SupportRequest removeSupportRequest(SupportRequest supportRequest) {
		getSupportRequests().remove(supportRequest);
		supportRequest.setUserAccount(null);

		return supportRequest;
	}

	public UserAddress getUserAddress() {
		return this.userAddress;
	}

	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}

	public UserVerification getUserVerification() {
		return this.userVerification;
	}

	public void setUserVerification(UserVerification userVerification) {
		this.userVerification = userVerification;
	}

	public List<UserWallet> getUserWallets() {
		return this.userWallets;
	}

	public void setUserWallets(List<UserWallet> userWallets) {
		this.userWallets = userWallets;
	}

	public UserWallet addUserWallet(UserWallet userWallet) {
		getUserWallets().add(userWallet);
		userWallet.setUserAccount(this);

		return userWallet;
	}

	public UserWallet removeUserWallet(UserWallet userWallet) {
		getUserWallets().remove(userWallet);
		userWallet.setUserAccount(null);

		return userWallet;
	}
	
	
	public List<UserLimit> getUserLimits() {
		return this.userLimits;
	}

	public void setUserLimits(List<UserLimit> userLimits) {
		this.userLimits = userLimits;
	}

	public UserLimit addUserLimit(UserLimit userLimit) {
		getUserLimits().add(userLimit);
		userLimit.setUserAccount(this);
		

		return userLimit;
	}

	public UserLimit removeUserLimit(UserLimit userLimit) {
		getUserLimits().remove(userLimit);
		userLimit.setUserAccount(null);

		return userLimit;
	}
	
	
	public List<UserPayment> getUserPayments() {
		return this.userPayments;
	}

	public void setUserPayments(List<UserPayment> userPayments) {
		this.userPayments = userPayments;
	}

	public UserPayment addUserPayment(UserPayment userPayment) {
		getUserPayments().add(userPayment);
		userPayment.setUserAccount(this);

		return userPayment;
	}

	public UserPayment removeUserPayment(UserPayment userPayment) {
		getUserPayments().remove(userPayment);
		userPayment.setUserAccount(null);

		return userPayment;
	}

	
	public List<RazorpayAccount> getRazorpayAccounts() {
		return this.razorpayAccounts;
	}

	public void setRazorpayAccounts(List<RazorpayAccount> razorpayAccounts) {
		this.razorpayAccounts = razorpayAccounts;
	}

	public RazorpayAccount addRazorpayAccount(RazorpayAccount razorpayAccount) {
		getRazorpayAccounts().add(razorpayAccount);
		razorpayAccount.setUserAccount(this);

		return razorpayAccount;
	}

	public RazorpayAccount removeRazorpayAccount(RazorpayAccount razorpayAccount) {
		getRazorpayAccounts().remove(razorpayAccount);
		razorpayAccount.setUserAccount(null);

		return razorpayAccount;
	}
	
	
	public List<CardAccount> getCardAccounts() {
		return this.cardAccounts;
	}

	public void setCardAccounts(List<CardAccount> cardAccounts) {
		this.cardAccounts = cardAccounts;
	}

	public CardAccount addCardAccount(CardAccount cardAccount) {
		getCardAccounts().add(cardAccount);
		cardAccount.setUserAccount(this);

		return cardAccount;
	}

	public CardAccount removeCardAccount(CardAccount cardAccount) {
		getCardAccounts().remove(cardAccount);
		cardAccount.setUserAccount(null);

		return cardAccount;
	}
	
	
	public List<DigitalTransfer> getDigitalTransfers() {
		return this.digitalTransfers;
	}

	public void setDigitalTransfers(List<DigitalTransfer> digitalTransfers) {
		this.digitalTransfers = digitalTransfers;
	}

	public DigitalTransfer addDigitalTransfer(DigitalTransfer digitalTransfer) {
		getDigitalTransfers().add(digitalTransfer);
		digitalTransfer.setUserAccount(this);

		return digitalTransfer;
	}

	public DigitalTransfer removeDigitalTransfer(DigitalTransfer digitalTransfer) {
		getDigitalTransfers().remove(digitalTransfer);
		digitalTransfer.setUserAccount(null);

		return digitalTransfer;
	}

	public UserOperation getUserOperation() {
		return userOperation;
	}

	public void setUserOperation(UserOperation userOperation) {
		this.userOperation = userOperation;
	}
	
	
}