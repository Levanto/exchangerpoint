<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ page import="com.exchangerpoint.exchangeservices.common.ApplicationDataRepository"%>
<%@ page import="com.exchangerpoint.databaseservices.entity.Currency"%>
<%@ page import="com.exchangerpoint.databaseservices.entity.Ecurrency"%>
<%@ page import="com.exchangerpoint.databaseservices.entity.Testimonial"%>
<%@ page import="com.exchangerpoint.databaseservices.entity.SEOData"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.SimpleDateFormat"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="description" content="Buy and Sell Bitcoin Ether Ripple safely online.Call or WhatsApp +919910922033 Skype ID Exchangerpoint">
<meta name="keywords" content="Bitcoin,Ether,Perfectmoney,Ripple, neteller,skrill,buy-bitcoin,sell-bitcoin,Buy-ether,sell-ether,credit card,western union,ecurrency,e-currency,exchange,buy,sell,neft,imps,paytm,paym,bank-transfer,online-transfer,instant,ethereum">
<meta name="author" content="Govind Singh">
<meta name="language" content="English">
<title>Trace Order</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!-- Styles -->
<link href="css/bootstrap/bootstrap.min.css" rel="stylesheet" />
<link rel="icon" type="image/jpg" href="img/exchangerPoint.jpg" />
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
<script	src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit"	async defer></script>
<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>

<style type="text/css">
.recent_posts a:hover {
	color: #F0AD4E !important;
}
</style>
</head>
<body class="pull_top">


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
					<li><a href="/buy-bitcoin-with-imps-india.htm">BUY BITCOIN</a></li>
					<li><a href="/buy-ethereum-with-bitcoin.htm">BUY ETHER</a></li>
					<li><a href="/buy-ripple-with-bitcoin.htm">BUY RIPPLE</a></li>
					<li class="active"><a href="/traceorder.htm">TRACE ORDER</a></li>	
					<li><a href="#Contactus">CONTACT US</a></li>
					<li><a href="/faq.htm">FAQ</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container">

		<div class="row feature_wrapper">
			<!-- Features Row -->
			<div class="features_op1_row" style="margin: 10%">
				<table style="margin: 0 10% 4% 10%;">
					<tbody>
						<tr>
							<td style="text-align: justify">You can trace your order by
								entering your order number and click on find button.You do not
								remember the order number please check your inbox/spam folder
								for order email.</td>
						</tr>
					</tbody>
				</table>
			
				<form action="/traceorder.htm" method="GET"
					onsubmit="chkOrder(this)">
					<script type="text/javascript">
						function chkOrder(e) {
							if ($("#order").val().trim() == "") {
								$("#showerror").html("Enter order no.")
							}

							e.submit();
						}
					</script>
					<style type="text/css">
r {
	color: red
}

g {
	color: green
}
</style>
					<table class="form" align="center" border="0" cellpadding="0"
						cellspacing="0" width="100%" style="margin: 0 10% 4% 10%;">
						<tbody>

							<tr>
								<td width="15%">Order Number<r>*</r>:
								</td>
								<td width="20%"><input name="order" id="order"
									maxlength="10"
									style="padding: 10px; border: 1px solid #CCC; border-radius: 3px; margin-bottom: 10px; width: 100%; box-sizing: border-box; font-family: montserrat; color: #2C3E50; font-size: 13px;"
									type="text"></td>

								<td align="left"><input value="Find"
									class="btnGreySubmit btn btn-warning" height="30"
									style="margin-left: 10%; margin-bottom: 2%;" type="submit"></td>
							</tr>
							<tr colspan="3" style="color: red" id="showerror">
								<c:if test="${orderDetail== null && msg !=null}">
					${msg}
					</c:if>
							</tr>
						</tbody>
					</table>

				</form>
				<c:if test="${orderDetail!=null}">
					${orderDetail}
					</c:if>
<script>
  (adsbygoogle = window.adsbygoogle || []).push({
    google_ad_client: "ca-pub-2383498571498370",
    enable_page_level_ads: true
  });</script>
  <ins class="adsbygoogle"
     style="display:block"
     data-ad-client="ca-pub-2383498571498370"
     data-ad-slot="3529740043"
     data-ad-format="auto"></ins>
<script>
(adsbygoogle = window.adsbygoogle || []).push({});
</script>
  
			</div>
		</div>
	</div>
	<!-- starts footer -->
	<footer id="footer" style="margin-top:10%"> <%@ include 	file="footer.jsp"%> </footer>

	<!-- Scripts -->
	<script src="js/jquery-latest.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/theme.js"></script>
	<script src="js/stepform.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/index-slider.js"></script>
	<script type="text/javascript" src="js/main.js"></script>

	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>
	<!-- jQuery easing plugin -->
	<script src="js/jquery.easing.min.js" type="text/javascript"></script>
	<!-- jQuery step form -->


</body>
</html>