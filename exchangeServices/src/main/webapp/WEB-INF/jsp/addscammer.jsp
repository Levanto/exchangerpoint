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
<title>Add Scammer</title>
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
    var widgetId25;
	var onloadCallback = function() {
		var widgetId4 = grecaptcha.render('contactuscaptcha', {
			'sitekey' : '6LehVAMTAAAAAF5aoIZn2myh6lD-C7Nfez_-aV6x'
		});
		var widgetId5 = grecaptcha.render('divaddtestimonial', {
			'sitekey' : '6LehVAMTAAAAAF5aoIZn2myh6lD-C7Nfez_-aV6x'
		});
        var widgetId25 = grecaptcha.render('divaddscammer', {
			'sitekey' : '6LehVAMTAAAAAF5aoIZn2myh6lD-C7Nfez_-aV6x'
		});
	};
</script>
<script>
	function setResponse(id) {
		if (id == 4)
			document.getElementById("rCaptcharesponce").value = grecaptcha
					.getResponse(widgetId4);
		if (id == 5)
			document.getElementById("rCaptcharesponce").value = grecaptcha
					.getResponse(widgetId5);
        if (id == 25)
			document.getElementById("rCaptcharesponce").value = grecaptcha
					.getResponse(widgetId25);

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
				<br /> <br /> <label style="margin-left: 11%; margin-bottom: 3%;"
					id="error"><c:if test="${msg2 !=null}">${msg2}
					<script type="text/javascript">
					onload = reloadAll;
					function reloadAll(){
					$("#txtname").val("");
					$("#txtemail").val("");
					$("#txtphone").val("");
					$("#txtmsg").val("");}
					</script>
					</c:if><c:if test="${error2 !=null}">${error2}</c:if></label>
					<script type="text/javascript">
					function valdateFields(){
						$("#error").html("");
						if($("#txtname").val().trim() == ""){
							$("#error").html("<span style='color:red'>Name cannot be blank..</span>");
							return false;
						}else if($("#txtemail").val().trim() == ""){
							$("#error").html("<span style='color:red'>E-mail cannot be blank..</span>");
							return false;
						}else if($("#txtphone").val().trim() == ""){
							$("#error").html("<span style='color:red'>Phone cannot be blank..</span>");
							return false;
						}
						else if($("#txtmsg").val().trim() == ""){
							$("#error").html("<span style='color:red'>Message cannot be blank..</span>");
							return false;
						}
					}
					</script>
					<a href="/checkscammer.htm" style="float:right;margin-right: 30%">Goto Search Scammer Page</a>
					<!--   enctype="multipart/form-data"  -->
				<form:form commandName="Scammer" action="/addscammer.htm"   method="POST" onsubmit = "return valdateFields()">
					<table class="table table-condensed table-stripped table-bordered" style="width:70%">
					<tr><td colspan="2"><spam style="font-size:1.5em;margin-left:30%;color:blue">Add Scammer</spam></td></tr>
						<tr><td>Name : </td>
							<td><form:input type="text" class="form-control"
									placeholder="Name" path="Name" id="txtname"></form:input></td>
						</tr>
						<tr><td>Email : </td>
							<td><form:input type="text" class="form-control"
									placeholder="Email" path="Email" id="txtemail"></form:input></td>
						</tr>
						<tr><td>Phone : </td>
							<td><form:input type="text" class="form-control"
									placeholder="Phone" path="Phone" id="txtphone"></form:input></td>
						</tr>
						<!-- <tr><td>Upload Photos : </td>
							<td><div id="divImage" style="height: 100px; width: 100px"></div>
								<input type="file" id="txtfile" path="Proofs" name="file"></input></td> 
						</tr>-->
						<tr><td>Message : </td>
							<td><form:textarea class="form-control"
									path="Message" id="txtmsg"></form:textarea></td>
						</tr>
<tr><td></td>
							<td><div id="divaddscammer"></div></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="Save" class="btn btn-warning" />&nbsp;&nbsp;<input type="button" value="Reset" onclick="reloadAll()" class="btn btn-primary" /></td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
	</div>
	<!-- starts footer -->
	<footer id="footer" style="margin-top:10%"> <%@ include
		file="footer.jsp"%> </footer>

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