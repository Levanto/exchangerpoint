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
<meta name="description" content="Buy Bitcoin with Western Union Transfer United States. Call or WhatsApp +919910922033 Skype ID Exchangerpoint">
<meta name="keywords" content="buy, Bitcoin, MoneyGram,western-union,usa,canada,united-kingdom,canada,australia,transfer,buy-Bitcoin-bank-transfer,buy-Bitcoin,buy-Bitcoin-with-money-transfer,buy-eth,btc-with-transfer,ecurrency,e-currency,exchange">
<meta name="author" content="Govind Singh">
<meta name="language" content="English">
<title>Buy Bitcoin with Western Union, MoneyGram or Bank Transfer</title>
 
<link rel="icon" type="image/jpg" href="img/exchangerPoint.jpg" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
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
<script src='js/index-main.js'></script>
<script type="text/javascript" src="js/main.js"></script>
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<%@ include file="googlepagelevelads.jsp"%>  
<!-- Rcaptcha -->
<script type="text/javascript">
	var widgetId1;
	var widgetId4;
	var onloadCallback = function() {

		var widgetId1 = grecaptcha.render('buycaptcha', {
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
		if (id == 4)
			document.getElementById("rCaptcharesponce").value = grecaptcha
					.getResponse(widgetId4);
	}
</script>
<script
	src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit"
	async defer>
	
</script>

<script type="text/javascript">	
function printmessageinpage() {
if(ORDER_TYPE == "B")
{
$("#buysuccess").html("<p style='color:green' >Your order number <b>" + ORDER_ID + "</b> has been succesfully submitted<br>Please send  " + $("#buyamount").val() + " "+$("#buyfrom").val()+" by Western Union or MoneyGram to  below Details</p></br><p style='color:blue'><br>First Name  KALA Last Name SINGH Country INDIA <br> </p>");
		
}

$(PARENT).find(".OTPVerifyCodeError").html("");
$(PARENT).find(".next").trigger("click");
ORDER_ID = 0;
PARENT = "";		
}		
</script>

</head>
<body class="pull_top">
	<style type="text/css">
b.bl {
	color: #2966a7;
}
</style>

    	<div
		style="background: rgba(0, 0, 0, 0.8); border-bottom: 1px solid #252525;"
		class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle pull-right"
					data-toggle="collapse" data-target=".navbar-ex1-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a href="index.html" class="navbar-brand"><strong><img
						src="img/exchangerPoint.jpg"
						style="height: 30px; margin: 0 7px; border-radius: 50%" />Exchanger
						Point</strong></a>
			</div>
			<div class="collapse navbar-collapse navbar-ex1-collapse"
				role="navigation">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/">HOME</a></li>
					<li class="active"><a href="/buy-bitcoin-with-imps-india.htm">BUY BITCOIN</a></li>
					<li><a href="/buy-ethereum-with-bitcoin.htm">BUY ETHER</a></li>
					<li><a href="/buy-ripple-with-bitcoin.htm">BUY RIPPLE</a></li>
					<li><a href="/traceorder.htm">TRACE ORDER</a></li>	
					<li><a href="#Contactus">CONTACT US</a></li>
					<li><a href="/faq.htm">FAQ</a></li>
				</ul>
			</div>
		</div>
	</div>
		<div class="container">
			<div class="section_header">
				<h3>&nbsp;</h3>
			</div>
			
			<div class="row feature">
			
				<br>
				<div class="col-sm-4 feature ordeformHeader"
						style="background: url('img/backgrounds/buyorderback.png') repeat-x top center;">

						<div id="buyFormWU" name="buyform"
							action="/buyticket.htm" class="img-responsive orderform">
							<h4 style="color: white">Buy Bitcoin Across The World</h4>
							<!-- progressbar -->
							<ul class="progressbar">
								<li class="active">Place Order</li>
								<li>OTP Verify</li>
								<li>Order Success</li>
							</ul>
							<!-- fieldsets -->
							<fieldset class="img-responsive">
								<h2 class="fs-title">Buy Bitcoin Order</h2>
								<div id="exchangeerror" style="">&nbsp;</div>
								       <input type="hidden" name="buyto" id="buyto" value="Bitcoin" />			
								<br> <select name="from" id="buyfrom"
									onblur="getBuyFees();">
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
									id="buyamount" placeholder="Amount in USD|GBP|EUR|AUD|CAD|" maxlength="10"  
									onblur="getBuyFees();" />
									<input
									type="text" id="buyewalletid" name="buyewalletid"
									placeholder="Enter Your Bitcoin Address" maxlength="100" />
									
									<input type="text"
									style="border: none;  color: green"
									id="buyreceivedamount" name="buyReceivedAmount"
									placeholder="Received Amount ?" readonly="readonly"  />
									
									 <label
									class="chkRadio" style="color: red;"></label><label
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
										onclick="setResponse('1');sendVerificationCode(this);"
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
					       <div class="col-sm-6" style="margin-top: 100px; width: 370px; height: 370px; overflow: auto;">
					       <%@ include file="googleads.jsp"%> </div>	
				
					</div>
				
				<div class="col-sm-6 info">
				<p style="font-size: 17px;  text-align: justify;text-justify: inter-word;"> Buying bitcoin with cash was never been easy thats by ExchangerPoint introduced cross boarder transfer expert organization like western union and moneygram. Western union makes the cash transfer smooth and fast.We will pickup your transaction and send you the bitcoins on given address in trade .We never ask any documents for western union and moneygram transfers. All people from allowed countries for western union and moneygram can open trade and buy bitcoin. You can also send western union and moneygram with online transfer. We just need MTCN or reference number to redeem the money. 
<br><br> Procedure-- Its really simple locate western union near by you <a href="https://www.westernunion.com/gb/en/agent-locator.html" >Find WU Agent</a> and give them cash with receiver details which you will get once you open the order here on this page. Once your money is transfered you will get MTCN(refrence) number just send that number to us by email to support@exchangerpoint.com or by whatsapp +919910922033. The same way you can do moneygram transfer <a heref="https://secure.moneygram.com/locations" > Find Monegram Agent </a>and buy bitcoins. You can also send money onlne on western union or moneygram website and get your coins faster.
				      <!-- Ad-1 -->
				<%@ include file="googlearticalads.jsp"%> 
				<%@ include file="bitcoinbuylinks.jsp"%> 
				</p>
                						
				</div>
				
				
			</div>
			
			
		</div>
		

	
    <!-- start footer -->
	<footer id="footer"> <%@ include file="footer.jsp"%> </footer>
	<!-- end footer -->
	
	<!-- Scripts -->
	<script src="js/jquery-latest.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/theme.js"></script>
	<script src="js/stepform.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/index-slider.js"></script>


	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>
	<!-- jQuery easing plugin -->
	<script src="js/jquery.easing.min.js" type="text/javascript"></script>
	<!-- jQuery step form -->

	<input type="hidden" id="rCaptcharesponce"></input>

</body>
</html>