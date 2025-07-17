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
<%@ page import="com.exchangerpoint.databaseservices.entity.Scammer"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="description" content="Buy and Sell Bitcoin Ether Ripple safely online.Call or WhatsApp +919910922033 Skype ID Exchangerpoint">
<meta name="keywords" content="Bitcoin,Ether,Perfectmoney,Ripple, neteller,skrill,buy-bitcoin,sell-bitcoin,Buy-ether,sell-ether,credit card,western union,ecurrency,e-currency,exchange,buy,sell,neft,imps,paytm,paym,bank-transfer,online-transfer,instant,ethereum">
<meta name="author" content="Govind Singh">
<meta name="language" content="English">
<title>CHECK SCAMMER</title>
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
<style type="text/css">
.recent_posts a:hover {
	color: #F0AD4E !important;
}
</style>
<script type="text/javascript">
	var widgetId4;
	var widgetId5;
	var onloadCallback = function() {
		var widgetId4 = grecaptcha.render('contactuscaptcha', {
			'sitekey' : '6LehVAMTAAAAAF5aoIZn2myh6lD-C7Nfez_-aV6x'
		});	};
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

<script async
	src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
</head>
<body style="height: 1400px">

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

			<div class="collapse navbar-collapse navbar-ex1-collapse"
				role="navigation">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/">HOME</a></li>
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
	<div class="container">

		<div class="row feature_wrapper">
			<!-- Features Row -->
			<div class="features_op1_row" style="margin: 10%">
				<table style="margin: 0 10% -1% 10%;">
					<tbody>
						<tr>
							<td style="text-align: justify"></td>
						</tr>
					</tbody>
				</table>

				<style type="text/css">
r {
	color: red
}

g {
	color: green;
}
</style>

				<p>Before making any deal please check this scammer record
					directory for the other person. If you find
					any record matching here please do not create any deal with that person since there
					are plenty of chances of getting scammed.<br>
					You can search here with name or email or mobile number or any combination of these three details for getting more precise search result.<br><br><r>**</r>If you have been scammed by
					any one you can also add his record here so it will keep safe other
					from that scammer.</p>

				<br /> <br /> <label style="margin-left: 11%; margin-bottom: 3%;"
					id="error"><c:if test="${msg3 !=null}">${msg3}</c:if></label> <a
					href="/addscammer.htm" class="btn btn-primary"
					style="float: right; margin-right: 12%"><b>Add New Scammer
						Record </b></a>

				<form:form commandName="Scammer" action="checkscammer.htm"
					method="POST">
					<table class="table table-condensed table-stripped table-bordered"
						style="width: 88%">
						<tr>
							<td colspan="5"><span
								style="font-size: 1.5em; margin-left: 30%; color: blue">Search
									Scammer</span></td>
						</tr>
						<tr>
							<td><form:input type="text" class="form-control"
									placeholder="Name" path="Name" id="txtname"></form:input></td>
							<td><form:input placeholder="E-Mail" type="text"
									class="form-control" path="Email" id="txtemail"></form:input></td>
							<td><form:input type="text" class="form-control"
									placeholder="Phone" path="Phone" id="txtphone"></form:input></td>
							<td><input type="submit" value="Search"
								class="btn btn-warning" /></td>
							<td><a class="btn btn-primary" href="/checkscammer.htm" />Reset</a></td>
						</tr>
					</table>
				</form:form>
				<script>
					(adsbygoogle = window.adsbygoogle || []).push({
						google_ad_client : "ca-pub-2383498571498370",
						enable_page_level_ads : true
					});
				</script>

				<!-- Ad-1 -->
				<ins class="adsbygoogle" style="display: block"
					data-ad-client="ca-pub-2383498571498370" data-ad-slot="3529740043"
					data-ad-format="auto"></ins>
				<script>
					(adsbygoogle = window.adsbygoogle || []).push({});
				</script>
				<c:if test="${result !=null}">
					<div>
						<table class="table table-condensed table-stripped table-bordered"
							style="width: 90%">
							<thead>
								<tr>
									<th></th>
									<th colspan="4"><span
										style="font-size: 1.5em; margin-left: 30%; color: blue">Search
											Results</span></th>
								</tr>
								<tr>
									<th>Sr. No.</th>
									<th>Name</th>
									<th>E-Mail</th>
									<th>Phone</th>
									<th>Message</th>
								</tr>
							</thead>
							<tbody>${result}</tbody>
						</table>
				</c:if>
				<c:if test="${result1!=null}">
					<spam style="color:grey;">No result.... </spam>
				</c:if>
			</div>
		</div>
	</div>
	</div>
	<br>
	<div class="clear"></div>
	<!-- starts footer 
	<footer id="footer" style="margin-top:30%"> <%@ include
		file="footer.jsp"%> </footer> -->

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
	<input type="hidden" id="rCaptcharesponce"></input>

</body>
</html>
