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
<meta name="description" content="About ExchangerPoint Private Limited. Call or WhatsApp +919910922033 Skype ID Exchangerpoint">
<meta name="keywords" content="buy, Bitcoin, paytm,uk,eth,buy-Bitcoin-paytm,buy-Bitcoin,buy-Bitcoin-with-paytm,buy-eth,buy-eth-with-paytm,btc-with-paytm,ecurrency,e-currency,exchange">
<meta name="author" content="Govind Singh">
<meta name="language" content="English">
<title>About US</title>
 
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

	<%@ include file="indiapaymentinfo.jsp"%>   
	<%@ include file="googlepagelevelads.jsp"%> 
<!-- Rcaptcha -->
<script type="text/javascript">
	var widgetId4;
	var onloadCallback = function() {

		
		var widgetId4 = grecaptcha.render('contactuscaptcha', {
			'sitekey' : '6LehVAMTAAAAAF5aoIZn2myh6lD-C7Nfez_-aV6x'
		});
	};
</script>
<script>
	function setResponse(id) {
		if (id == 4)
			document.getElementById("rCaptcharesponce").value = grecaptcha
					.getResponse(widgetId4);
	}
</script>
<script
	src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit"
	async defer>
	
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
					<li ><a href="/buy-bitcoin-with-imps-india.htm">SERVICES</a></li>
					
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
			<div class="col-sm-4 feature ordeformHeader">
			
				<img alt="Owner(CEO)" src="img/aboutus.jpg"  style="width: 370px; height: 500px;">
					
					<div class="col-sm-6" style="margin-top: 100px; width: 370px; height: 370px; overflow: auto;">
					       
				             </div>	
					</div>	
					
				<div class="col-sm-6 info">
				<p style="font-size: 17px;  text-align: justify;text-justify: inter-word;">	ExchangerPoint Private Limited is financial consulting 	company owned by Govind Singh (CEO). ExchangerPoint provides investment consulting and currency exchange services. ExchangerPoint has started its operation in Fab, 2015 in Bengalure.Now ExchangerPoint has expended its operation all across india and UK and USA.We have big team of professional working on development and on operations.ExchangerPoint provides you best help you need to for financial planning. 	 
<br><br> ExchangerPoint also train you for forex trading so you can build your own income over the time. You can do forex trading at your home comfort and even trade on your smart phone. We at ExchangerPoint help you at each step from learning to earning.
<br><br> We are also dealing in crypto currencies like Bitcoin, Ether and Ripple which is accepted all across the word.	We will help you how to mine cryptocurrencies with laptop, mobile and miners etc.				
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