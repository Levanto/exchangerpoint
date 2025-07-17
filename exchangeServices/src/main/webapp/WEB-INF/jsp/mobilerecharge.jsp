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
<meta name="description" content="Mobile recharge or topup with bitcoin. Call or WhatsApp +919910922033 Skype ID Exchangerpoint">
<meta name="keywords" content="recharge, Bitcoin, india,pay-bill,mobile-recharge,btc,airtel,vodafone,idea,bsnl,tata,mts,mtln,jio,dongle,data,pack,bundle,postpaid,prepaid,landline">
<meta name="author" content="Govind Singh">
<meta name="language" content="English">
<title>Mobile Recharge(Top-UP) with Bitcoin in India</title>
 
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
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-61681760-1', 'auto');
  ga('send', 'pageview');

</script>
<script>
  (adsbygoogle = window.adsbygoogle || []).push({
    google_ad_client: "ca-pub-2383498571498370",
    enable_page_level_ads: true
  });
</script>   
<!-- Rcaptcha -->
<script type="text/javascript">
	var widgetId1;
	var widgetId4;
	var onloadCallback = function() {

		var widgetId1 = grecaptcha.render('sellcaptcha', {
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

if(ORDER_TYPE == "S")
{
$("#sellsuccess").html("<p style='color:green' >Your order number <b>" + ORDER_ID + "</b> has been succesfully submitted<br>Please send us " + $("#sellamount").val() + " " + $("#sellfrom").val()+" </p><br><p style='color:blue'>Our Bitcoin address 14S4WmNELz7m2ksq5ayskhkqVnCrK3BPp4 </p><br> You can also scan QR code and make payment<br><br>");
	
}
$(PARENT).find(".OTPVerifyCodeError").html("");
$(PARENT).find(".next").trigger("click");
ORDER_ID = 0;
PARENT = "";		
}

</script>

<%@ include file="googlepagelevelads.jsp"%> 

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
						style="background: url('img/backgrounds/sellorderback.png') repeat-x top center;">

						<div id="sellFormB" class="img-responsive orderform">
							<h4 style="color: white">Mobile Top-Up With Bitcoin</h4>
							<!-- progressbar -->
							<ul class="progressbar">
								<li class="active">Recharge Order</li>
								<li>Verify Order</li>
								<li>Make Payment</li>
							</ul>
							<!-- fieldsets-->


							<fieldset class="img-responsive">
								<h2 class="fs-title">Recharge Order</h2>
								<br>
								<input type="hidden" name="to" id="sellto" value="INR" />			
								<input type="hidden" name="from" id="sellfrom" value="Bitcoin" />
								<select name="txtSellCardType" id="txtSellCardType">
								<option value="Aircel">Aircel</option>
								<option value="Airtel">Airtel</option>
								<option value="BSNL">BSNL</option>
								<option value="Idea">Idea</option>
								<option value="Jio">Jio</option>
								<option value="Tata Docomo">Tata Docomo</option>
								<option value="Vodafone">Vodafone</option>
								</select>
								<input id="sellamount" type="text" name="amount"
									placeholder="Amount in Bitcoins like 0.085" maxlength="10" onblur="getSellFees()"   />
								<input	id="sellreceivedAmount" type="text"
									style="border: none; margin-bottom: 4%; color: green"
									name="receivedAmount" placeholder="Received Amount ?" readonly="readonly" />
							<!--<label class="radio-inline lablespm lblBT"> <input
									name="spm" type="radio" id="radioSellBT" class="radBT"><img
									class="pm" src="img/bt.gif" /></label> <label
									class="radio-inline lablespm lblWU"><input
									id="radioSellWU" name="spm" type="radio" class="radWU"><img
									class="pm" src="img/wu.png" /></label> <label
									class="radio-inline lablespm lblMG"><input
									id="radioSellMG" name="spm" type="radio" class="radMG"><img
									class="pm" src="img/mg.gif" />					
							       <label class="radio-inline lablespm lblCP"><input
									id="radioSellWCard" name="spm" type="radio" class="radC"  ></label></label> -->
								<br> 
								 <input type="button" class="next action-button"
									style="display: none" value="Next" />
								 <input type="button"
									class="btn action-button" value="Next"
									onClick="blankDetailsCheckSell();" /><br>
									<label class="chkRadio"
									id="lblSellValidationMsg" style="color: red;"></label>

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
										style="margin-top: 0px;" placeholder="Example +9112344567890"
										maxlength="15" />
									<div id="sellcaptcha"></div>
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
							<img class="sellsuccessImgclass" src="img/bitcoin-address.jpg" style="margin-bottom: 5px; margin: -2% 0%;"/>
							</fieldset>
						</div>
	
						  <div class="col-sm-6" style="margin-top: 100px; width: 370px; height: 370px; overflow: auto;">
					        <%@ include file="googleads.jsp"%>
				             </div>	
				
					</div>	
				<div class="col-sm-6 info">
				<p style="font-size: 17px;  text-align: justify;text-justify: inter-word;">Exchangerpoint is best place to top-up your mobile with bitcoins. we provide you support at every step. You can directly reach us on phone or whatsapp.
				 We provide mobile and DTH recharges with bitcoins in all India regions.The wide range of operators includes Airtel , Jio, Vodafone, Idea, BSNL, MTNL, MTS, Tata Docomo, and all DTH providers.
				 It is very easy process to recharge your mobile with bitcoins just enter the amount in INR left side  and verify your order with OTP.
				 Once you complete order you will get our bitcoin address to make the payment .Once your bitcoin transaction receive 1 confirmation we will top-up your mobile as per order amount.
				  
				</p>
				<%@ include file="googlearticalads.jsp"%> 
				<%@ include file="bitcoinselllinks.jsp"%> 			
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