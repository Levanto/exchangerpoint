<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.exchangerpoint.exchangeservices.common.ApplicationDataRepository"%>
<%@ page import="com.exchangerpoint.databaseservices.entity.Currency"%>
<%@ page import="com.exchangerpoint.databaseservices.entity.Ecurrency"%>
<%@ page import="com.exchangerpoint.databaseservices.entity.Testimonial"%>
<%@ page import="com.exchangerpoint.exchangeservices.common.Constants"%>
<%@ page import="com.exchangerpoint.databaseservices.entity.SEOData"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.SimpleDateFormat"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<meta name="description" content="Buy and Sell Bitcoin Ether Ripple safely online.Call or WhatsApp +919910922033 Skype ID Exchangerpoint">
<meta name="keywords" content="Bitcoin,Ether,Perfectmoney,Ripple, neteller,skrill,buy-bitcoin,sell-bitcoin,Buy-ether,sell-ether,credit card,western union,ecurrency,e-currency,exchange,buy,sell,neft,imps,paytm,paym,bank-transfer,online-transfer,instant,ethereum">
<meta name="author" content="Govind Singh">
<meta name="language" content="English">
<title>ExchangerPoint</title>
 
<link rel="icon" type="image/jpg" href="img/exchangerPoint.jpg" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="propeller" content="5950cf8201c46b7322615b79964844a7" />
<!-- Styles -->
<link href="css/bootstrap/bootstrap.min.css" rel="stylesheet" />
<link href="css/stepform/jquerysctipttop.css" rel="stylesheet" />
<link href="css/stepform/stepstyle.css" rel="stylesheet" />
<link rel="stylesheet" href="css/compiled/bootstrap-overrides.css"
	type="text/css" />
<link rel="stylesheet" type="text/css" href="css/compiled/theme.css" />
<link href='css/lib/googlefont.css' rel='stylesheet' type='text/css' />
<link rel="stylesheet" href="css/compiled/index.css" type="text/css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="css/lib/animate.css"
	media="screen, projection" />
<link rel="stylesheet" type="text/css"
	href="css/stepform/index-main.css" />
</head>
<body class="pull_top">
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle pull-right"
					data-toggle="collapse" data-target=".navbar-ex1-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a href="/" class="navbar-brand"><strong><img
						src="img/exchangerPoint.jpg"
						style="height: 50px; margin: 0 7px; border-radius: 50%" />Exchanger
						Point</strong></a>
			</div>

			<div class="collapse navbar-collapse navbar-ex1-collapse" role="navigation">
				<ul class="nav navbar-nav navbar-right">
					<li class="active"><a href="/">HOME</a></li>
					<li><a href="/buy-bitcoin-with-imps-india.htm">BUY BITCOIN</a></li>
					<li><a href="/buy-ethereum-with-bitcoin.htm">BUY ETHER</a></li>
					<li><a href="/buy-ripple-with-bitcoin.htm">BUY RIPPLE</a></li>
					<li><a href="/traceorder.htm">TRACE ORDER</a></li>	
					<li><a href="#Contactus">CONTACT US</a></li>
					<li><a href="/faq.htm">FAQ</a></li>
				</ul>
			</div>
		</div>
	</div>

	<section id="feature_slider" class="lol"> 
	<article class="slide" id="showcasing"
		style="background: url('img/backgrounds/landscape.png') repeat-x top center;">
	<img class="asset left-30 sp600 t120 z1"
		src="img/slides/scene1/macbook.png" />
	<div class="info">
		<h2>We exchange ecurrency globally</h2>
	</div>
	</article> 
	
	<article class="slide" id="ideas"
		style="background: url('img/backgrounds/aqua.jpg') repeat-x top center;">
	<div class="info">
		<h2>We deliver what we say.</h2>
	</div>
	<img class="asset left-480 sp600 t260 z1"
		src="img/slides/scene2/left.png" /> <img
		class="asset left-210 sp600 t213 z2"
		src="img/slides/scene2/middle.png" /> <img
		class="asset left60 sp600 t260 z1" src="img/slides/scene2/right.png" />
	</article>
	
	 <article class="slide" id="tour"
		style="background: url('img/backgrounds/color-splash.jpg') repeat-x top center;">
	<img class="asset left-350 sp450 t135 z2"
		src="img/slides/scene3/desktop.png" />
	<div class="info">
		<h2>Instant Messenger 24*7</h2>
		<a href="#livechat">Help</a>
	</div>
	</article> 
	
	<article class="slide" id="responsive"
		style="background: url('img/backgrounds/indigo.jpg') repeat-x top center;">
	<img class="asset left-472 sp600 t120 z3"
		src="img/slides/scene4/html5.png" /> <img
		class="asset left-190 sp500 t120 z2" src="img/slides/scene4/css3.png" />
	<div class="info">
		<h2>One place for <strong>Everything you need</strong></h2>
	</div>
	</article>
 </section>
	
<div id="showcase">
		<div class="container">
			<div class="row feature_wrapper">
				<div class="features_op1_row">
					<!-- Exchange Order -->
					<div class="col-sm-4 feature first ordeformHeader"
						style="background: url('img/backgrounds/exchangeorder.png') repeat-x top center;">

						<div id="exchangeForm" name="exchangeform"
							action="/exchangeticket.htm" class="img-responsive orderform">
							<h4 style="color: white">Buy Bitcoin Ether Ripple (INDIA)</h4>
							<!-- progressbar -->
							<ul class="progressbar">
								<li class="active">Buy Coin</li>
								<li>Verify</li>
								<li>Success</li>
							</ul>
							<!-- fieldsets -->
							<fieldset class="img-responsive">
								<h2 class="fs-title">Buy Coin Order</h2>
								<div id="exchangeerror" style="">&nbsp;</div>
								<select name="exchangeto" id="exchangeto"
									onchange="getExchangeFees();">
									<%
										for (int i = 0; i < ApplicationDataRepository.ecurrencyList.size(); i++) {
									%>
									<option
										value="<%=ApplicationDataRepository.ecurrencyList.get(i)
						.getEcurrency()%>"><%=ApplicationDataRepository.ecurrencyList.get(i)
						.getEcurrency()%></option>
									<%
										}
									%>
								</select>
								
								<select name="from" id="exchangefrom"
									onchange="getExchangeFees();">
									<option value="INR">INR (NEFT | IMPS | UPI | Paytm)</option>
								</select>
								 <input type="text" name="amount" id="exchangeamount"
									placeholder="Amount in Rupees" maxlength="10" onblur="getExchangeFees();"  />
									
									<input type="text" id="exchangewalet"
									name="exchangewalet" placeholder="Enter Your Bitcoin/Ether/Ripple Address"
									maxlength="100" />
									
									<input
									id="exchangereceivedAmount" type="text" name="receivedAmount"
									style="border: 0 !important; color: green ; font-weight: bold;" placeholder="Received Amount ?"
									readonly="readonly" /> 
									
									
									
									<label class="chkRadio" style="color: red"
									id="lblExchangeValidationMsg"></label><br> 
									<input
									type="button" style="margin-top: 11%" class="action-button"
									value="Next" onClick="validateExchangeOrder();" /> 
									
									<input
									style="display: none" type="button" class="next action-button"
									value="Next" />
							</fieldset>
							<fieldset class="img-responsive">
								<h2 class="fs-title">Verify Order</h2>
								<div class="validationDiv">
									<!-- exchangeemaildiv -->
									   <img class="img-responsive" src="img/email-verification.png"
										style="margin: 0 32%;" width="100" height="100" />
										
										<img class="failedrobotImgClass"
										src="img/robotverificationfailed.png"
										style="margin-bottom: 5px; margin: -2% 0%; display: none"
										height="120" width="120"/>
										
										<img class="invalidemailImgclass"
										src="img/invalidemail.png"
										style="margin-bottom: 5px; margin: -2% 0%; display: none"
										height="120" width="120"/>
										
									<div class="verifyerror"
										style="color: red; margin-top: 5px; font-weight: bold;">&nbsp;</div>
										
									<!-- exchangeverifyerror -->
									   <input type="text" class="userEmail" id="exchangeemail"
										name="exchangeemail" style="margin-top: 10px;"
										placeholder="Email*" maxlength="100" /> 
										
										<input type="text"
										class="userMobile" id="exchangemobile" name="exchangemobile"
										style="margin-top: 0px;" placeholder="Example +9112344567890"
										maxlength="15" />

									<div id="exchangecaptcha"></div>
									    <input type="button" value="Get Verification Code"
										onclick="setResponse('1');sendVerificationCode(this);"
										class="btn previous-button" style="width: 100%">
								</div>

								<div class="OTPVerificationDIV" style="display: none">
									<!-- exchangeverifydiv -->

									    <img class="sndngImgClass" src="img/OTPSending.gif"
										style="margin-bottom: 5px; margin: -2% 0%;" height="120" />
										
										<img class="sentImgClass" src="img/OTPreceived.jpg"
										style="margin-bottom: 5px; margin: -2% 0%; display: none"
										height="120" /> 
										
										<img class="failedImgClass"
										src="img/OTPfailed.png"
										style="margin-bottom: 5px; margin: -2% 0%; display: none"
										height="120" />
										
										<img class="failedorderImgclass"
										src="img/orderfailed.png"
										style="margin-bottom: 5px; margin: -2% 0%; display: none"
										height="120" width="120"/>
										<img class="processingImgclass"
										src="img/processing.gif"
										style="margin-bottom: 5px; margin: -2% 0%; display: none"/>
										
									<!-- -------------------  -->
									<h3 class="fs-subtitle msgOfSentOTP"
										style="font-weight: normal; font-size: 15px; color: #666; margin-bottom: 1px; display: none">
									</h3>
									<div class="OTPVerifyCodeError"	style="color: red; margin-top: 5px; font-weight: bold;">&nbsp;</div>
									<!-- exchangeverifycodeerror -->
									   <input type="text" class="OTPVerifyCode"
										id="exchangeverifycode" name="exchangeverifycode"
										placeholder="Enter verifiation code" style="margin-top: 5px;"
										maxlength="6" />
										
									    <input type="button"
										id="exchangeverifybutton" name="exchangeverifybutton"
										onClick="OTPCodeVerify(this);"
										class="verify-button btn btn-primary" value="Verify"
										style="margin-top: 15px;" /><br> 
										
										<a id="resendMailLink"
										href="#orderform" onClick="toggalEmailFields(this);"
										style="margin-bottom: 15px; color: #ff8000; font-weight: bold; text-decoration: underline;">Change
										Email/Phone</a>&nbsp;&nbsp;&nbsp;

									<!--comment: Enable link after 45 sec  -->
									<a class="resendMailLink"
										style="margin-bottom: 15px; color: #ccc; font-weight: bold; text-decoration: underline;">Resend
										Code</a>
									<!-- ----------------------------- -->
								</div>
								<input type="button" id="exchangechangebutton" name="previous"
									class="previous"
									style="margin-top: 15px; background: #ccc; width: 75%"
									value="<< Change Exchange Details"
									onClick="toggalEmailFields(this);" /> <input type="button"
									class="next action-button"
									style="margin-top: 15px; display: none" />
							</fieldset>

							<fieldset class="img-responsive">
								<div id="exchangesuccess" class="exchangesuccess" name="exchangesuccess"style="color: green; margin-top: 5px; font-weight: bold;">&nbsp;</div> 
								<img class="exchangesuccessImgclass" src="img/paytm.jpg" style="margin-bottom: 5px; margin: -2% 0%;"/>										
							</fieldset>
						</div>
					</div>
					
					
<!----------------------------------------- Sell Order form start from here----------------------------------------->

					<div class="col-sm-4 feature ordeformHeader"
						style="background: url('img/backgrounds/sellorderback.png') repeat-x top center;">

						<div id="sellForm" class="img-responsive orderform">
							<h4 style="color: white">Sell Ecurrency (Globally)</h4>
							<!-- progressbar -->
							<ul class="progressbar">
								<li class="active">Sell Order</li>
								<li>Verify Order</li>
								<li>Make Payment</li>
							</ul>
							<!-- fieldsets-->


							<fieldset class="img-responsive">
								<h2 class="fs-title">Sell Order</h2>
								<br> <select name="from" id="sellfrom"
									onchange="getSellFees()">
									<%
										for (int i = 0; i < ApplicationDataRepository.ecurrencyList.size(); i++) {
									%>
									<option
										value="<%=ApplicationDataRepository.ecurrencyList.get(i)
						.getEcurrency()%>"><%=ApplicationDataRepository.ecurrencyList.get(i)
						.getEcurrency()%></option>
									<%
										}
									%>
								</select><input type="text" name="sellcardcode" id="sellcardcode"
									placeholder="Card Code" style="display: none" /> <select
									name="to" id="sellto" onchange="getSellFees()">
									<%
										for (int i = 0; i < ApplicationDataRepository.currencyList.size(); i++) {
									%>
									<option
										value="<%=ApplicationDataRepository.currencyList.get(i)
						.getCurrency()%>"><%=ApplicationDataRepository.currencyList.get(i)
						.getValue()%></option>
									<%
										}
									%>
								</select><input id="sellamount" type="text" name="amount"
									placeholder="Amount in coins" maxlength="10" onblur="getSellFees()"   /><input
									id="sellreceivedAmount" type="text"
									style="border: none; margin-bottom: 4%; color: green"
									name="receivedAmount" placeholder="Received Amount ?" readonly="readonly" />
								<label class="radio-inline lablespm lblBT"> <input
									name="spm" type="radio" id="radioSellBT" class="radBT"><img
									class="pm" src="img/bt.gif" /></label> <label
									class="radio-inline lablespm lblWU"><input
									id="radioSellWU" name="spm" type="radio" class="radWU"><img
									class="pm" src="img/wu.png" /></label> <label
									class="radio-inline lablespm lblMG"><input
									id="radioSellMG" name="spm" type="radio" class="radMG"><img
									class="pm" src="img/mg.gif" /></label>
								<!-- <label
									class="radio-inline lablespm lblCP"><input
									id="radioSellWCard" name="spm" type="radio" class="radC"><img
									class="pm" src="img/card.gif" /></label> -->
								<br> <br> <label class="chkRadio"
									id="lblSellValidationMsg" style="color: red;"></label><br>
								<input type="button" class="next action-button"
									style="display: none" value="Next" /> <input type="button"
									class="btn action-button" value="Next"
									onClick="toggalSellPaymentFields(this);" />



							</fieldset>
							<fieldset class="img-reponsive">
								<h2 class="fs-title"></h2>
								<div width="100%" class="fBankTransfer">
									<input type="text" placeholder="Receiver's Name"
										id="txtReceiverName" name="receiverName" /> <input
										type="text" placeholder="Bank Name" id="txtSellBankName"
										name="sellBankName" /> <input type="text"
										placeholder="Account No" id="txtSellAccountNo"
										name="sellccountNo" /> <input type="text"
										placeholder="IFSC Code" id="txtSellCode" name="btSellCode" /><input
										type="text" placeholder="SWIFT/IBN Code" id="txtSwiftCode"
										name="btSellCode" /> <input type="text" placeholder="Country"
										id="txtSellBTCountry" name="btSellCountry" />
								</div>
								<div width="100%" class="fMU" style="display: none">
									<input type="text" placeholder="Receiver's Name"
										id="txSelltReceiverName" name="muSellReceiverName" /> <input
										type="text" placeholder="City" id="txtSellCity"
										name="muSellCity" /> <input type="text" placeholder="Country"
										id="txtSellCountry" name="muSellCountry" />
								</div>
								<div width="100%" class="fCardPayment" style="display: none">
									<input type="text" placeholder="Card No" id="txtSellCardNo"
										name="cardNo" onchange="setCardType()" /> <input type="text"
										placeholder="Card Holder Name" id="txtSellCardHolderName"
										name="sellCardHolderName" /> <input type="text"
										class="cardTypeName" style="border: none"
										placeholder="Card Type" id="txtSellCardType"
										name="sellCardType" />
								</div>
								<label id="lblSellPaymentMethodCheck" style="color: red"></label><br>
								<input type="button" name="previous"
									class="previous action-button" value="Previous" /> <input
									type="button" class="action-button" value="Next"
									onclick="blankDetailsCheckSell()" /><input type="button"
									class="next action-button" style="display: none" value="Next" />
							</fieldset>

							<fieldset class="img-responsive">
								<h2 class="fs-title">Verify Order</h2>
								<div class="validationDiv">
									<!-- exchangeemaildiv -->
									<img class="img-responsive" src="img/email-verification.png"
										style="margin: 0 32%;" width="100" height="100" />
										<img class="failedrobotImgClass"
										src="img/robotverificationfailed.png"
										style="margin-bottom: 5px; margin: -2% 0%; display: none"
										height="120" width="120"/>
										<img class="invalidemailImgclass"
										src="img/invalidemail.png"
										style="margin-bottom: 5px; margin: -2% 0%; display: none"
										height="120" width="120"/>
										
									<div class="verifyerror"
										style="color: red; margin-top: 5px; font-weight: bold;">&nbsp;</div>
									<!-- exchangeverifyerror -->
									<input type="text" class="userEmail" id="sellemail"
										name="sellemail" style="margin-top: 10px;"
										placeholder="Email*" maxlength="100" /> <input type="text"
										class="userMobile" id="sellmobile" name="sellmobile"
										style="margin-top: 0px;" placeholder="Example +9112344567890 (Optional)"
										maxlength="15" />
									<div id="sellcaptcha"></div>
									<input type="button" value="Get Verification Code"
										onclick="setResponse('2');sendVerificationCode(this);"
										class="btn previous-button" style="width: 100%">
								</div>

								<div class="OTPVerificationDIV" style="display: none">
									<img class="sndngImgClass" src="img/OTPSending.gif"
										style="margin-bottom: 5px; margin: -2% 0%;" height="120" /> <img
										class="sentImgClass" src="img/OTPreceived.jpg"
										style="margin-bottom: 5px; margin: -2% 0%; display: none"
										height="120" /> <img class="failedImgClass"
										src="img/OTPfailed.png"
										style="margin-bottom: 5px; margin: -2% 0%; display: none"
										height="120" />
										<img class="failedorderImgclass"
										src="img/orderfailed.png"
										style="margin-bottom: 5px; margin: -2% 0%; display: none"
										height="120" width="120"/>
										<img class="processingImgclass"
										src="img/processing.gif"
										style="margin-bottom: 5px; margin: -2% 0%; display: none"/>
									<!-- -------------------  -->
									<h3 class="fs-subtitle msgOfSentOTP"
										style="font-weight: normal; font-size: 15px; color: #666; margin-bottom: 1px; display: none">
									</h3>
									<div class="OTPVerifyCodeError"
										style="color: red; margin-top: 5px; font-weight: bold;">&nbsp;</div>
									<!-- exchangeverifycodeerror -->
									<input type="text" class="OTPVerifyCode" id="sellverifycode"
										name="sellverifycode" placeholder="Enter verifiation code"
										style="margin-top: 5px;" maxlength="6" /> <input
										type="button" id="sellverifybutton" name="sellverifybutton"
										onClick="OTPCodeVerify(this);"
										class="verify-button btn btn-primary" value="Verify"
										style="margin-top: 15px;" /><br> <a
										onClick="toggalEmailFields(this);"
										style="margin-bottom: 15px; color: #ff8000; font-weight: bold; text-decoration: underline;">Change
										Email/Phone</a>&nbsp;&nbsp;&nbsp;

									<!--comment: Enable link after 45 sec  -->
									<a class="resendMailLink"
										style="margin-bottom: 15px; color: #ccc; font-weight: bold; text-decoration: underline;">Resend
										Code</a>
									<!-- ----------------------------- -->
								</div>
								<input type="button" id="sellchangebutton" name="previous"
									class="previous"
									style="margin-top: 15px; background: #ccc; width: 75%"
									value="<< Change Sell Order Details"
									onClick="toggalEmailFields(this);" /> <input type="button"
									class="next action-button"
									style="margin-top: 15px; display: none" />
							</fieldset>

							<fieldset class="img-responsive">
								<div id="sellsuccess" style="color: green; margin-top: 5px; font-weight: bold;">&nbsp;</div> 
							</fieldset>
						</div>
					</div>
	
	
	
<!----------------------------------------------- Buy order form ------------------------------------------------------>
					<div class="col-sm-4 feature last ordeformHeader"
						style="background: url('img/backgrounds/buyorderback.png') repeat-x top center;">

						<div id="buyForm" class="img-responsive orderform">
							<h4 style="color: white">Buy Ecurrency (Globally)</h4>
							<!-- progressbar -->
							<ul class="progressbar">
								<li class="active">Buy Order</li>
								<li>Verify Order</li>
								<li>Make Payment</li>
							</ul>
							<!-- fieldsets -->
							<fieldset class="img-responsive">
								<h2 class="fs-title">Buy Order</h2>
								<br><select
									name="to" id="buyto" onchange="getBuyFees();">
									<%
										for (int i = 0; i < ApplicationDataRepository.ecurrencyList.size(); i++) {
									%>
									<option
										value="<%=ApplicationDataRepository.ecurrencyList.get(i)
						.getEcurrency()%>"><%=ApplicationDataRepository.ecurrencyList.get(i)
						.getEcurrency()%></option>
									<%
										}
									%>
								</select>
								<br> <select name="from" id="buyfrom"
									onchange="getBuyFees();">
									<%
										for (int i = 0; i < ApplicationDataRepository.currencyList.size(); i++) {
									 if(!ApplicationDataRepository.currencyList.get(i)
						.getCurrency().equals("INR")) {%>
									<option
										value="<%=ApplicationDataRepository.currencyList.get(i)
						.getCurrency()%>"><%=ApplicationDataRepository.currencyList.get(i)
						.getValue()%></option>
									<%
										}}
									%>
								</select>  <input type="text"  name="amount"
									id="buyamount" placeholder="Amount" maxlength="10"  
									onblur="getBuyFees();toggalWalletAddressFields();" />
									<input
									type="text" id="buyewalletid" name="buyewalletid"
									placeholder="Enter Your Bitcoin/Ether/Ripple Address" maxlength="100" style="display: none"/>
									
									<input type="text"
									style="border: none;  color: green"
									id="buyreceivedamount" name="buyReceivedAmount"
									placeholder="Received Amount ?" readonly="readonly"  />
									
									 <label
									class="chkRadio" style="color: red;"></label> <label
									class="radio-inline lablespm lblBT"> <input name="bpm"
									type="radio" id="radioBuyBT" class="radBT"><img
									class="pm" src="img/bt.gif" /></label> <label
									class="radio-inline lablespm lblWU"><input
									id="radioBuyWU" name="bpm" type="radio" class="radWU"><img
									class="pm" src="img/wu.png" /></label> <label
									class="radio-inline lablespm lblMG"><input
									id="radioBuyMG" name="bpm" type="radio" class="radMG"><img
									class="pm" src="img/mg.gif" /></label>
								<br><br><label class="chkRadio"
									id="lblBuyValidationMsg" style="color: red;"></label><br>
								
								 <input
									type="button" class="next action-button" style="display: none"
									value="Next" /> 
										
									<input type="button" class="btn action-button"
									value="Next" onClick="toggalBuyPaymentFields(this);" />

								<!-- <div id="RecaptchaField2"></div> -->

							</fieldset>
							<fieldset class="img-responsive">
								<h2 class="fs-title">Verify Order</h2>
								<div class="validationDiv">
									<img class="img-responsive" src="img/email-verification.png"
										style="margin: 0 32%;" width="100" height="100" />
										<img class="failedrobotImgClass"
										src="img/robotverificationfailed.png"
										style="margin-bottom: 5px; margin: -2% 0%; display: none"
										height="120" width="120"/>
										<img class="invalidemailImgclass"
										src="img/invalidemail.png"
										style="margin-bottom: 5px; margin: -2% 0%; display: none"
										height="120" width="120"/>
										
									<div class="verifyerror"
										style="color: red; margin-top: 5px; font-weight: bold;">&nbsp;</div>
									<!-- exchangeverifyerror -->
									<input type="text" class="userEmail" id="buyemail"
										name="buyemail" style="margin-top: 10px;" placeholder="Email*"
										maxlength="100" /> <input type="text" class="userMobile"
										id="buymobile" name="buymobile" style="margin-top: 0px;"
										type="button" placeholder="Example +9112344567890 (Optional)" />
									<div id="buycaptcha"></div>
									<input type="button" value="Get Verification Code"
										onclick="setResponse('3');sendVerificationCode(this);"
										class="btn previous-button" style="width: 100%">
								</div>

								<div class="OTPVerificationDIV" style="display: none">
									<img class="sndngImgClass" src="img/OTPSending.gif"
										style="margin-bottom: 5px; margin: -2% 0%;" height="120" /> <img
										class="sentImgClass" src="img/OTPreceived.jpg"
										style="margin-bottom: 5px; margin: -2% 0%; display: none"
										height="120" /> <img class="failedImgClass"
										src="img/OTPfailed.png"
										style="margin-bottom: 5px; margin: -2% 0%; display: none"
										height="120" />
										<img class="failedorderImgclass"
										src="img/orderfailed.png"
										style="margin-bottom: 5px; margin: -2% 0%; display: none"
										height="120" width="120"/>
										<img class="processingImgclass"
										src="img/processing.gif"
										style="margin-bottom: 5px; margin: -2% 0%; display: none"/>
									<!-- -------------------  -->
									<h3 class="fs-subtitle msgOfSentOTP"
										style="font-weight: normal; font-size: 15px; color: #666; margin-bottom: 1px; display: none">
									</h3>
									<div class="OTPVerifyCodeError"
										style="color: red; margin-top: 5px; font-weight: bold;">&nbsp;</div>
									<!-- buyverifycodeerror -->
									<input type="text" class="OTPVerifyCode" id="buyverifycode"
										name="buyverifycode" placeholder="Enter verifiation code"
										style="margin-top: 5px;" maxlength="6" />
										 <input
										type="button" id="buyverifybutton" name="buyverifybutton"
										onClick="OTPCodeVerify(this);"
										class="verify-button btn btn-primary" value="Verify"
										style="margin-top: 15px;" />
										<br> <a href="#orderform"
										onClick="toggalEmailFields(this);"
										style="margin-bottom: 15px; color: #ff8000; font-weight: bold; text-decoration: underline;">Change
										Email/Phone</a>&nbsp;&nbsp;&nbsp;

									<!--comment: Enable link after 45 sec  -->
									<a class="resendMailLink"
										style="margin-bottom: 15px; color: #ccc; font-weight: bold; text-decoration: underline;">Resend
										Code</a>
									<!-- ----------------------------- -->
								</div>
								<input type="button" id="buychangebutton" name="previous"
									class="previous"
									style="margin-top: 15px; background: #ccc; width: 75%"
									value="<< Change Buy Order Details"
									onClick="toggalEmailFields(this);" /> <input type="button"
									class="next action-button"
									style="margin-top: 15px; display: none" />
							</fieldset>

							<fieldset class="img-responsive">
								<div id="buysuccess" style="color: green; margin-top: 5px; font-weight: bold;">&nbsp;</div> 
							</fieldset>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<!-------------------------------- feature section ------------------------------------------------------>

<div id="clients">
		<div class="container">
			<div class="row">
				<!-- Ad-2 -->
				<%@ include file="WEB-INF/jsp/googleads.jsp"%>
			</div>
		</div>
</div>

<div id="features">	
		<div class="container">		        
			<div class="section_header"><h3>Exchange Process</h3></div>
			  <div class="row feature">
				<div class="col-sm-6"><img src="img/ecurrency-exchange-process.PNG"	class="img-responsive" /></div>
				<div class="col-sm-6 info"><h3><img src="img/e-currency-exchange.png" /> How it works ?	</h3>
					<p>
						Exchange your ecurrency to other ecurrency is very easy and just
						few step process with us. <br> <b>1. </b>Go to exchange form
						and calculate the rates. <br> <b>2. </b>If you are ok with
						exchange rate then submit the exchange order. <br> <b>3.
						</b>We will send a verification code to your mobile and email id,enter this code on
						verification page. <br> <b>4. </b>Once you successfully
						verify order you will see our account details.Just send payment to
						given ID. <br> <b>5. </b>We will verify your payment and
						complete your order . <br> <b>6. </b> You will get email or
						message on mobile.

					</p>
					<!-- Ad-2 -->
				<%@ include file="WEB-INF/jsp/googlearticalads.jsp"%> 
				</div>
			</div>
			<div class="section_header"><h3>Features</h3></div>
			  <div class="row feature">
				<div class="col-sm-6"><img src="img/showcase1.png" class="img-responsive" /></div>
				  <div class="col-sm-6 info"><h3><img src="img/features-ico1.png" /> No SignUp required.</h3>
					<p>
					   <b>Tired to do Signup ? </b> <br>We understand the pain of filling forum
						so we made it signup free.
						<br><b>How it works?</b><br> Once you submit order on
						our site, you will get activation code on your email id ,use this
						code to activate your order.Once you activate order You will get
						your order number.Now you can trace your order with order number
						and your email id.
					</p>
					<!-- Ad-3 -->
				<%@ include file="WEB-INF/jsp/googlearticalads.jsp"%> 
				</div>
			</div>
			
			<div id="livechat">
				<div class="row feature">
					<div class="col-sm-6 pic-right"><img src="img/showcase2.png" class="pull-right img-responsive" /></div>
					  <div class="col-sm-6 info info-left">	<h3><img src="img/features-ico2.png" /> Live Chat</h3>
						<p>
							We provide 24*7 online chat ExchangerPoint.service on our site.If
							we are offline then you can drop message through chat and we will
							get back you as soon as possible.We also very active on
							Skype,Yahoo Messanger,Hangout and Whatsapp. 
						</p>
						<br>
						<h4>Phone:</h4>
						<b style="color: green">+91-9910922033 &nbsp;&nbsp;(Whatsapp)</b>
						<br>
						<h4>Skype:</h4>
						<b style="color: blue">Exchangerpoint</b> <br>
						<h4>Yahoo:</h4>
						<b style="color: violet">ExchangerPoint@yahoo.com</b> <br>
						<h4>Gmail:</h4>
						<b style="color: green">ExchangerPoint@gmail.com</b> <br>
					</div>
				</div>
			</div>
			
			<div class="row feature">
				<div class="col-sm-6"><img src="img/showcase3.png" class="img-responsive" /></div>
				<div class="col-sm-6 info"><h3><img src="img/features-ico3.png" /> Why Choose Us?</h3>
					<p>
						Excellent and unmatched customer service forms the
						ideal of the company ExchangerPoint. Our customers are royal to us. We have given
						the best of the best service and that is the reason
						that we have a solid database of valued and treasured customers in
						a very short span of an year. 
						<br> <b>1. </b>We are trusted and tested with time working since 2015 and have many served customer
						You can check our reviews on facebook and google.						
						<br> <b>2.</b>We provide support with all sort of communications so you will get us where ever you want at any time.						
					    <br> <b>3. </b>You can be confident	that you are dealing with the experts. We have been trading ecurrencies from long time.

					</p>
					<!-- Ad-4 -->
				<%@ include file="WEB-INF/jsp/googlearticalads.jsp"%> 
				</div>
			</div>
		</div>
	</div>



	<div id="clients">
		<div class="container">
			<div class="section_header"><h3>Helpful Links</h3></div>
			  <div class="row">
			  
				<div class="col-md-4">
				<b>Buy Bitcoins With Western Union</b><br>
				<a href="https://exchangerpoint.com/buy-bitcoin-with-western-union-united-states.htm"> Buy Bitcoin With Western Union USA</a> &nbsp
				<a href="https://exchangerpoint.com/buy-bitcoin-with-western-union-canada.htm"> Buy Bitcoin With Western Union Canada</a> &nbsp
				<a href="https://exchangerpoint.com/buy-bitcoin-with-western-union-australia.htm"> Buy Bitcoin With Western Union Australia</a> &nbsp				
				<a href="https://exchangerpoint.com/buy-bitcoin-with-western-union-united-kingdom.htm"> Buy Bitcoin With Western Union United Kingdom</a> &nbsp
				<br><b>Buy Bitcoins With MoneyGram</b><br>
				<a href="https://exchangerpoint.com/buy-bitcoin-with-moneygram-united-states.htm"> Buy Bitcoin With MoneyGram USA</a> &nbsp
				<a href="https://exchangerpoint.com/buy-bitcoin-with-moneygram-canada.htm"> Buy Bitcoin With MoneyGram Canada</a> &nbsp
				<a href="https://exchangerpoint.com/buy-bitcoin-with-moneygram-australia.htm"> Buy Bitcoin With MoneyGram Australia</a> &nbsp
				<a href="https://exchangerpoint.com/buy-bitcoin-with-moneygram-united-kingdom.htm"> Buy Bitcoin With Moneygram United Kingdom</a> &nbsp
			     <br><b>Direct Links Buy Bitcoin Globally</b><br>
				<a href="https://exchangerpoint.com/buy-bitcoin-with-bank-transfer-united-states.htm"> Buy Bitcoin with USA Bank Transfer</a> &nbsp
				<a href="https://exchangerpoint.com/buy-bitcoin-with-bank-transfer-europe.htm"> Buy Bitcoin with Europe Bank Transfer</a> &nbsp
				<a href="https://exchangerpoint.com/buy-bitcoin-with-transferwise.htm"> Buy Bitcoin With Transferwise</a> &nbsp
				<br><b>Pay With Bitcoins</b><br>
				<a href="https://exchangerpoint.com/mobile-recharge-with-bitcoin-in-india.htm"> Recharge INDIA Mobile and DTH with Bitcoin</a>
				<a href="https://exchangerpoint.com/buy-amazon-giftcard-with-bitcoin.htm"> Buy Amazon Gift Cards With Bitcoin</a>&nbsp
                <a href="https://exchangerpoint.com/buy-flipkart-giftcard-with-bitcoin.htm">Buy Flipkart Gift Cards With Bitcoin</a>&nbsp
                <a href="https://exchangerpoint.com/buy-myntra-giftcard-with-bitcoin.htm"> Buy Myntra Gift Cards With Bitcoin </a>&nbsp
				
				</div>
				<div class=col-md-4>
				<b>Direct Links Buy Bitcoin in INDIA</b><br>
					<a href="https://exchangerpoint.com/buy-bitcoin-with-paytm-india.htm"> Buy Bitcoin With PayTM INDIA</a> &nbsp
				<a href="https://exchangerpoint.com/buy-bitcoin-with-imps-india.htm"> Buy Bitcoin With IMPS</a> &nbsp
				<a href="https://exchangerpoint.com/buy-bitcoin-with-online-bank-transfer-india.htm"> Buy Bitcoin With India Online Bank Transfer</a> &nbsp
				<a href="https://exchangerpoint.com/buy-bitcoin-with-neft-india.htm"> Buy Bitcoin With NEFT Transfer</a>&nbsp
				<a href="https://exchangerpoint.com/buy-bitcoin-with-upi-india.htm"> Buy Bitcoin With UPI Transfer (BHIP APP or Bank Apps)</a>&nbsp
				<a href="https://exchangerpoint.com/buy-bitcoin-with-airtel-money-india.htm"> Buy Bitcoin With Airtel Money </a>
				<br><b>Direct Links Buy Ether in INDIA</b><br>
				<a href="https://exchangerpoint.com/buy-ether-with-paytm-india.htm"> Buy Ether With PayTM INDIA</a> &nbsp
				<a href="https://exchangerpoint.com/buy-ether-with-imps-india.htm"> Buy Ether With IMPS</a> &nbsp
				<a href="https://exchangerpoint.com/buy-ether-with-online-bank-transfer-india.htm"> Buy Ether With India Online Bank Transfer</a> &nbsp
				<a href="https://exchangerpoint.com/buy-ether-with-neft-india.htm"> Buy Ether With NEFT Transfer</a>&nbsp
				<a href="https://exchangerpoint.com/buy-ether-with-upi-india.htm"> Buy Ether With UPI Transfer (BHIP APP or Bank Apps)</a>&nbsp
				<a href="https://exchangerpoint.com/buy-ether-with-airtel-money-india.htm"> Buy Ether With Airtel Money </a>&nbsp
				<a href="https://exchangerpoint.com/buy-ethereum-with-bitcoin.htm"> Buy Ether With Bitcoin</a>
				<br><b>Direct Links Buy Ripple in INDIA</b><br>
				<a href="https://exchangerpoint.com/buy-ripple-with-paytm-india.htm"> Buy Ripple With PayTM INDIA</a> &nbsp
				<a href="https://exchangerpoint.com/buy-ripple-with-imps-india.htm"> Buy Ripple With IMPS</a> &nbsp
				<a href="https://exchangerpoint.com/buy-ripple-with-online-bank-transfer-india.htm"> Buy Ripple With India Online Bank Transfer</a> &nbsp
				<a href="https://exchangerpoint.com/buy-ripple-with-neft-india.htm"> Buy Ripple With NEFT Transfer</a>&nbsp
				<a href="https://exchangerpoint.com/buy-ripple-with-bitcoin.htm">Buy Ripple With Bitcoin</a>
				
				</div>
				<div class=col-md-4>
				<b>Direct Links Buy Bitcoin in United Kingdom</b><br>
				<a href="https://exchangerpoint.com/buy-bitcoin-with-paym-united-kingdom.htm"> Buy Bitcoin With Paym United Kingdom</a> &nbsp
				<a href="https://exchangerpoint.com/buy-bitcoin-with-national-bank-transfer-united-kingdom.htm"> Buy Bitcoin With National Bank Transfer United Kingdom</a> &nbsp
				<a href="https://exchangerpoint.com/buy-bitcoin-with-cash-deposit-united-kingdom.htm"> Buy Bitcoin With Cash Deposit in United Kingdom</a> &nbsp
				<a href="https://exchangerpoint.com/buy-bitcoin-with-pingit-united-kingdom.htm"> Buy Bitcoin With Barclays Pingit United Kingdom</a>&nbsp
				
				<br><b>Direct Links Buy Ether in United Kingdom</b><br>
				<a href="https://exchangerpoint.com/buy-ether-with-paym-united-kingdom.htm"> Buy Ether With Paym United Kingdom</a> &nbsp
				<a href="https://exchangerpoint.com/buy-ether-with-national-bank-transfer-united-kingdom.htm"> Buy Ether With National Bank Transfer United Kingdom</a> &nbsp
				<a href="https://exchangerpoint.com/buy-ether-with-cash-deposit-united-kingdom.htm"> Buy Ether With Cash Deposit in United Kingdom</a> &nbsp
				<a href="https://exchangerpoint.com/buy-ether-with-pingit-united-kingdom.htm"> Buy Ether With Barclays Pingit United Kingdom</a>&nbsp
				
				<br><b>Direct Links Buy Ripple in United Kingdom</b><br>
				<a href="https://exchangerpoint.com/buy-ripple-with-paym-united-kingdom.htm"> Buy Ripple With Paym United Kingdom</a> &nbsp
				<a href="https://exchangerpoint.com/buy-ripple-with-national-bank-transfer-united-kingdom.htm"> Buy Ripple With National Bank Transfer United Kingdom</a> &nbsp
				<a href="https://exchangerpoint.com/buy-ripple-with-cash-deposit-united-kingdom.htm"> Buy Ripple With Cash Deposit in United Kingdom</a> &nbsp
				<a href="https://exchangerpoint.com/buy-ripple-with-pingit-united-kingdom.htm"> Buy Ripple With Barclays Pingit United Kingdom</a>&nbsp
				
				</div>

			</div>
		</div>
	</div>
<br>
	<div id="clients">
		<div class="container">
			<div class="row">
				<!-- Ad-5 -->
				<%@ include file="WEB-INF/jsp/googleads.jsp"%>
			</div>
		</div>
	</div>
    <input type="hidden" id="rCaptcharesponce"></input>
	<!-- starts footer -->
	<footer id="footer"> <%@ include file="WEB-INF/jsp/footer.jsp"%>
	</footer>


<%@ include file="WEB-INF/jsp/googlepagelevelads.jsp"%> 

	
<!-- Rcaptcha -->
<script type="text/javascript">
	var widgetId1;
	var widgetId2;
	var widgetId3;
	var widgetId4;
	var onloadCallback = function() {

		var widgetId1 = grecaptcha.render('exchangecaptcha', {
			'sitekey' : '6LehVAMTAAAAAF5aoIZn2myh6lD-C7Nfez_-aV6x'
		});
		var widgetId2 = grecaptcha.render('sellcaptcha', {
			'sitekey' : '6LehVAMTAAAAAF5aoIZn2myh6lD-C7Nfez_-aV6x'
		});
		var widgetId3 = grecaptcha.render('buycaptcha', {
			'sitekey' : '6LehVAMTAAAAAF5aoIZn2myh6lD-C7Nfez_-aV6x'
		});
		var widgetId4 = grecaptcha.render('contactuscaptcha', {
			'sitekey' : '6LehVAMTAAAAAF5aoIZn2myh6lD-C7Nfez_-aV6x'
		});
	};
</script>
<script>
	function setResponse(id) {

		if (id == 1)
			document.getElementById("rCaptcharesponce").value = grecaptcha
					.getResponse(widgetId1);
		if (id == 2)
			document.getElementById("rCaptcharesponce").value = grecaptcha
					.getResponse(widgetId2);
		if (id == 3)
			document.getElementById("rCaptcharesponce").value = grecaptcha
					.getResponse(widgetId3);
		if (id == 4)
			document.getElementById("rCaptcharesponce").value = grecaptcha
					.getResponse(widgetId4);
	}
</script>
<script	src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit"	async defer></script>


<script type="text/javascript">	
function printmessageinpage() {
if(ORDER_TYPE == "E")
{
$("#exchangesuccess").html("<p style='color:green' >Your order number <b>" + ORDER_ID + "</b> has been succesfully submitted<br>Please send us " + $("#exchangeamount").val() + " INR </p><br><p style='color:blue'>1.NEFT: Govind Singh ACCOUNT 141701511093 IFSC ICIC00014171</p><br><p style='color:brown'>2.IMPS: MMID 9229864 Mobile 9910922033 </p><br><p style='color:orange'>3.UPI LINK: exchangerpoint@UPI </p><br><p style='color:blue'> 4.PayTM: Send Paytm to 9910922033 or Scan Below QR code<br>If PayTm Limit Reached Then Transfer Your Paytm Balance to Our Bank Account </p>");
		
}

if(ORDER_TYPE == "S")
{
$("#sellsuccess").html("<p style='color:green' >Your order number <b>" + ORDER_ID + "</b> has been succesfully submitted<br>Please send us " + $("#sellamount").val() + " " + $("#sellfrom").val()+" </p><br><p style='color:blue'>1.Bitcoin address 14S4WmNELz7m2ksq5ayskhkqVnCrK3BPp4 </p><br><p style='color:brown'>2.Ether address 0x254404CeC5DDe4f563BD2493C037228FC6E55172 </p><br><p style='color:blue'>3.Ripple address r4ZdpwWNRs63hj1KLYH2Fof8Fq7wBCwprA </p><br>");
	
}
if(ORDER_TYPE == "B")
{
$("#buysuccess").html("<p style='color:green' >Your order number <b>" + ORDER_ID + "</b> has been succesfully submitted<br>Please check your email for further instructions.");
		
}

$(PARENT).find(".OTPVerifyCodeError").html("");
$(PARENT).find(".next").trigger("click");
ORDER_ID = 0;
PARENT = "";		
}

</script>



<!--End of Zopim Live Chat Script-->

<!-- Scripts -->
	<script src='js/index-main.js'></script>
    <script type="text/javascript" src="js/main.js"></script>
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
	<script src="js/jquery-latest.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/theme.js"></script>
	<script src="js/stepform.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/index-slider.js"></script>


	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>
	<!-- jQuery easing plugin -->
	<script src="js/jquery.easing.min.js" type="text/javascript"></script>
</body>
</html>