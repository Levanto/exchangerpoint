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
<title>Recent Searches</title>
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
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<style type="text/css">
.recent_posts a:hover {
	color: #F0AD4E !important;
}
</style>
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
	<div class="row feature_wrapper">
		<!-- Features Row -->
		<div class="features_op1_row" style="margin: 10%">
			<h3 class="footer_header">Recent Searches</h3>
			<%
				List<SEOData> lstSData= null;
													
																			if(session.getAttribute("lstSEOData") != null){
																				lstSData = (ArrayList<SEOData>)session.getAttribute("lstSEOData");
																			}
																			else{
																				lstSData = ApplicationDataRepository.lstSEO;
																			}
																			
																													for (int i = 0; i < lstSData.size(); i++) {
			%>
			<div class="post">
				<%
					List<Ecurrency> lstEcurrency = ApplicationDataRepository.ecurrencyList;
																		List<Currency> lstCurrency = ApplicationDataRepository.currencyList;
																		boolean isE1 = false, isE2 = false, isC1=false, isC2 = false;
																		
																		for (int l = 0; l < lstEcurrency.size(); l++){
																			if(lstEcurrency.get(l).getEcurrency().trim().equals(lstSData.get(i).getFrom().trim())){
																				isE1 = true;
																			}
																			else if (lstEcurrency.get(l).getEcurrency().trim().equals(lstSData.get(i).getTo().trim())){
																				isE2 = true;
																			}
																		}
																		
																		for (int l = 0; l < lstCurrency.size(); l++){
																			String Curr = lstCurrency.get(l).getCurrency().trim();
																			String CurrShort = lstCurrency.get(l).getValue().trim();
																			if(Curr.equals(lstSData.get(i).getFrom().trim()) || CurrShort.equals(lstSData.get(i).getFrom().trim())){
																				isC1 = true;
																			}
																			else if (Curr.equals(lstSData.get(i).getTo().trim()) || CurrShort.equals(lstSData.get(i).getTo().trim())){
																				isC2 = true;
																			}
																		}
																		
																		
																		if(isC1 && isE2){
				%>

				<%-- <%=formatter.format(lstSData.get(i).getCreatedDate())%> --%>
				<a
					href="exchangerpoint.com/buy.htm?from=<%=lstSData.get(i).getFrom()%>&to=<%=lstSData.get(i).getTo()%>&amount=<%=lstSData.get(i).getAmount()%>&country=<%=lstSData.get(i).getCountry()%>"
					class="title">Buy <%=lstSData.get(i).getFrom()%> with <%=lstSData.get(i).getTo()%>
					from <%=lstSData.get(i).getCountry()%>
				</a>
			</div>
			<%
				}
													else if(isC2 && isE1){
			%>

			<%-- <%=formatter.format(lstSData.get(i).getCreatedDate())%> --%>
			<a
				href="exchangerpoint.com/sell.htm?from=<%=lstSData.get(i).getFrom()%>&to=<%=lstSData.get(i).getTo()%>&amount=<%=lstSData.get(i).getAmount()%>&country=<%=lstSData.get(i).getCountry()%>"
				class="title">Sell <%=lstSData.get(i).getFrom()%> for <%=lstSData.get(i).getTo()%>
				from <%=lstSData.get(i).getCountry()%>
			</a>
		</div>
		<%
			}else{
		%>

		<%-- <%=formatter.format(lstSData.get(i).getCreatedDate())%> --%>
		<a
			href="exchangerpoint.com/exchange.htm?from=<%=lstSData.get(i).getFrom()%>&to=<%=lstSData.get(i).getTo()%>&amount=<%=lstSData.get(i).getAmount()%>&country=<%=lstSData.get(i).getCountry()%>"
			class="title">Exchange <%=lstSData.get(i).getFrom()%> to <%=lstSData.get(i).getTo()%>
			from <%=((lstSData.get(i).getCountry()==null)?"Unidentified":lstSData.get(i).getCountry())%>
		</a>
	</div>
	<%
		}
	%>
	<hr />
	<%
		}
	%>

	<!-- starts footer -->
	<footer id="footer" style="margin-top:50%">
	<div class="container">
		<div class="row sections">
			<div class="col-sm-4 recent_posts">
				<h3 class="footer_header">Recent Searches</h3>
				<%
					List<SEOData> lstSEOData= null;
										
																if(session.getAttribute("lstSEOData") != null){
																	lstSEOData = (ArrayList<SEOData>)session.getAttribute("lstSEOData");			
																	ApplicationDataRepository.lstSEO = lstSEOData;
																}
																else{
																	lstSEOData = ApplicationDataRepository.lstSEO;
																}
																int count =0;	
															if(lstSEOData!=null)	{
																count = (lstSEOData.size()>5)?5:lstSEOData.size();
															}
																										for (int i = 0; i < count; i++) {
				%>
				<div class="post">
					<%
						List<Ecurrency> lstEcurrency = ApplicationDataRepository.ecurrencyList;
													List<Currency> lstCurrency = ApplicationDataRepository.currencyList;
													boolean isE1 = false, isE2 = false, isC1=false, isC2 = false;
													
													for (int l = 0; l < lstEcurrency.size(); l++){
														if(lstEcurrency.get(l).getEcurrency().trim().equals(lstSEOData.get(i).getFrom().trim())){
															isE1 = true;
														}
														else if (lstEcurrency.get(l).getEcurrency().trim().equals(lstSEOData.get(i).getTo().trim())){
															isE2 = true;
														}
													}
													
													for (int l = 0; l < lstCurrency.size(); l++){
														String Curr = lstCurrency.get(l).getCurrency().trim();
														String CurrShort = lstCurrency.get(l).getValue().trim();
														if(Curr.equals(lstSEOData.get(i).getFrom().trim()) || CurrShort.equals(lstSEOData.get(i).getFrom().trim())){
															isC1 = true;
														}
														else if (Curr.equals(lstSEOData.get(i).getTo().trim()) || CurrShort.equals(lstSEOData.get(i).getTo().trim())){
															isC2 = true;
														}
													}
													
													
													if(isC1 && isE2){
					%>

					<%-- <%=formatter.format(lstSEOData.get(i).getCreatedDate())%> --%>
					<a
						href="exchangerpoint.com/buy.htm?from=<%=lstSEOData.get(i).getFrom()%>&to=<%=lstSEOData.get(i).getTo()%>&amount=<%=lstSEOData.get(i).getAmount()%>&country=<%=lstSEOData.get(i).getCountry()%>"
						class="title">Buy <%=lstSEOData.get(i).getFrom()%> with <%=lstSEOData.get(i).getTo()%>
						from <%=lstSEOData.get(i).getCountry()%>
					</a>
				</div>
				<%
					}
										else if(isC2 && isE1){
				%>

				<%-- <%=formatter.format(lstSEOData.get(i).getCreatedDate())%> --%>
				<a
					href="exchangerpoint.com/sell.htm?from=<%=lstSEOData.get(i).getFrom()%>&to=<%=lstSEOData.get(i).getTo()%>&amount=<%=lstSEOData.get(i).getAmount()%>&country=<%=lstSEOData.get(i).getCountry()%>"
					class="title">Sell <%=lstSEOData.get(i).getFrom()%> for <%=lstSEOData.get(i).getTo()%>
					from <%=lstSEOData.get(i).getCountry()%>
				</a>
			</div>
			<%
				}else{
			%>

			<%-- <%=formatter.format(lstSEOData.get(i).getCreatedDate())%> --%>
			<a
				href="exchangerpoint.com/exchange.htm?from=<%=lstSEOData.get(i).getFrom()%>&to=<%=lstSEOData.get(i).getTo()%>&amount=<%=lstSEOData.get(i).getAmount()%>&country=<%=lstSEOData.get(i).getCountry()%>"
				class="title">Exchange <%=lstSEOData.get(i).getFrom()%> to <%=lstSEOData.get(i).getTo()%>
				from <%=lstSEOData.get(i).getCountry()%>
			</a>
		</div>

		<%
			}
		%><hr />
		<%
			}
										if(count<lstSEOData.size()){
		%><a class="btn btn-warning" type="button"
			href="recentsearches.htm"
			style="margin-right: 43%; bottom: -20%; position: absolute;">All
			Searches..</a>
		<%
			}
																				
											if(session.getAttribute("lstSEOData") != null){
												session.removeAttribute("lstSEOData");
												}
		%>

	</div>
	<script type="text/javascript">
		onload = startTime;
		function startTime() {

			appendLi();
			var t = setTimeout(function() {
				startTime()
			}, 1500);
		}

		function startTime2() {
			var liFirst = $("#divTest").find("div.cc:first").detach();
			$("#divTest").append(liFirst);
			$(liFirst).slideDown(800);
		}

		function appendLi() {

			$("#divTest").find("div.cc:first").slideUp(800);
			var t1 = setTimeout(function() {
				startTime2()
			}, 1500);

		}
	</script>
	<div class="col-sm-4 testimonials">
		<h3 class="footer_header">Recent Testimonials</h3>
		<div class="wrapper">
			<div id="divTest"
				style="height: 387px !important; overflow-y: hidden">

				<%
					for (int i = 0; i < ApplicationDataRepository.testimonialList
							.size(); i++) {
				%>
				<div class="cc">
					<p style="font-style: italic; font-weight: normal !important">
						<span style="font-weight: bold; color: red"><img
							src="images/doublequote.jpg"
							style="height: 20px; width: 20px; margin-bottom: 22px;">&nbsp;&nbsp;</span><%=ApplicationDataRepository.testimonialList.get(i)
						.getMessage()%></p>
					<div style="float: right">
						<p style="color: #ccc">
							-<small><%=ApplicationDataRepository.testimonialList.get(i)
						.getName()%><br> <%
 	SimpleDateFormat formatter = new SimpleDateFormat("d MMM yyyy");
 %>&nbsp;&nbsp;<%=formatter
						.format(ApplicationDataRepository.testimonialList
								.get(i).getCreatedDate())%></small>
					</div>
					<br> <br>
					<hr />
				</div>

				<%
					}
				%>



			</div>
			<a class="btn btn-warning" type="button" href="addtestimonial.htm"
				style="margin-right: 43%; margin-top: 4%;">Add Testimonial</a>
		</div>
	</div>
	<div class="col-sm-4 contact">
		<style>
.coverAll {
	position: absolute;
	height: 100%;
	width: 100%;
	display: none;
	background: white;
	opacity: .7;
}
</style>
		<div id="Contactus">
			<div class="coverAll">
				<img src="img/OTPSending.gif" style="margin: 45% 27%;" height="170" />
			</div>
			<h3 style="padding-left: 9%" class="footer_header">Contact US</h3>
			<p style="padding-left: 9%" align="center">ExchangerPoint,
				Shushant Lock Phase 1, Gurgaon, Haryana,India-122001</p>

			<form action="#" method="post" name="contactusform" id="us"
				style="padding-left: 16%">
				<div id="error_message" align="center"
					style="color: red; font-weigh: bold">&nbsp;</div>
				<input type="text" placeholder="Your name" style="width: 100%"
					name="name" id="name" maxlength="20" /> <input style="width: 100%"
					type="text" placeholder="Your email" name="emailId" id="emailId"
					maxlength="50" /> <input style="width: 100%" type="text"
					placeholder="Subject" name="subject" id="subject" />
				<textarea rows="3" style="width: 100%" placeholder="Message"
					name="message" id="message"></textarea>
				<div id="contactuscaptcha"></div>
				<input class="btn btn-warning" type="button" value="Send"
					onClick="contactusValidate();"
					style="margin-right: 43%; margin-top: 4%;" />
			</form>
		</div>
	</div>
	</div>
	<div class="row credits">
		<div class="col-md-12">
			<div class="row social">
				<div class="col-md-12">
					<a href="https://www.facebook.com/exchangerpoint" class="facebook"
						target="_blank"> <span class="socialicons ico1"></span> <span
						class="socialicons_h ico1h"></span>
					</a> <a href="https://twitter.com/exchangerpoint" class="twitter"
						target="_blank"> <span class="socialicons ico2"></span> <span
						class="socialicons_h ico2h"></span>
					</a> <a href="https://plus.google.com/+Exchangerpointdeals"
						class="gplus" target="_blank"> <span class="socialicons ico3"></span>
						<span class="socialicons_h ico3h"></span>
					</a>

				</div>
			</div>
			<div class="row copyright">
				<div class="col-md-12">©2015 ExchangerPoint. All rights
					reserved.</div>
			</div>
		</div>
	</div>

	</footer>

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