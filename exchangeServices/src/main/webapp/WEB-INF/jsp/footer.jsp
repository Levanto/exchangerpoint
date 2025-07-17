<%@ page import="com.exchangerpoint.exchangeservices.common.ApplicationDataRepository"%>
<%@ page import="com.exchangerpoint.databaseservices.entity.Currency"%>
<%@ page import="com.exchangerpoint.databaseservices.entity.Ecurrency"%>
<%@ page import="com.exchangerpoint.databaseservices.entity.Testimonial"%>
<%@ page import="com.exchangerpoint.databaseservices.entity.SEOData"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.exchangerpoint.exchangeservices.common.ApplicationDataRepository"%>
<%@ page import="java.text.SimpleDateFormat"%>

<div class="container">
	<div class="row sections">

		<div class="col-sm-4 recent_posts">
			<table>
				<tr>
					<td>
						<h3 class="footer_header">Our Facebook Page Updates</h3>
					</td>
				</tr>
				<tr>
					<td>					<div class="fb-page"
						data-href="https://www.facebook.com/exchangerpoint"
						data-tabs="timeline" data-height="410" data-small-header="true"
						data-adapt-container-width="true" data-hide-cover="false"
						data-show-facepile="true">
						<div class="fb-xfbml-parse-ignore">
							<blockquote cite="https://www.facebook.com/exchangerpoint">
							<a href="https://www.facebook.com/exchangerpoint">Exchanger	Point</a>
							</blockquote>
						</div>
					</div>
					</td>
				</tr>
				<tr>
					<td></td>
				</tr>
			</table>
			
		</div>

		<div class="col-sm-4 testimonials">
			<h3 class="footer_header">Recent Testimonials</h3>
			<div class="wrapper">
				<div id="divTest"
					style="height: 387px !important; overflow-y: hidden">
					<script type="text/javascript">
						onload = startTime;
						function startTime() {

							appendLi();
							var t = setTimeout(function() {
								startTime()
							}, 1500);
						}

						function startTime2() {
							var liFirst = $("#divTest").find("div.cc:first")
									.detach();
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
					<%
						for (int i = 0; i < ApplicationDataRepository.testimonialList.size(); i++) {
					%>
					<div class="cc">
						<p style="font-style: italic; font-weight: normal !important">
							<span style="font-weight: bold; color: red"><img
								src="images/doublequote.jpg"
								style="height: 20px; width: 20px; margin-bottom: 22px;">&nbsp;&nbsp;</span><%=ApplicationDataRepository.testimonialList.get(i).getMessage()%></p>
						<div style="float: right">
							<p style="color: #ccc">
								-<small><%=ApplicationDataRepository.testimonialList.get(i).getName()%><br> <%
 	SimpleDateFormat formatter = new SimpleDateFormat("d MMM yyyy");
 %>&nbsp;&nbsp;<%=formatter.format(ApplicationDataRepository.testimonialList.get(i).getCreatedDate())%></small>
						</div>
						<br> <br>
						<hr />
					</div>

					<%
						}
					%>



				</div>
				<a class="btn btn-warning" type="button" href="addtestimonial.htm"
					style="margin-right: 43%; margin-top: 5%;">Add Testimonial</a>
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
						name="name" id="name" maxlength="20" /> <input
						style="width: 100%" type="text" placeholder="Your email"
						name="emailId" id="emailId" maxlength="50" /> <input
						style="width: 100%" type="text" placeholder="Subject"
						name="subject" id="subject" />
					<textarea rows="3" style="width: 100%" placeholder="Message"
						name="message" id="message"></textarea>
					<div id="contactuscaptcha"></div>
					<input class="btn btn-warning" type="button" value="Send Message"
						onClick="setResponse('4');contactusValidate();"
						style="margin-right: 65%; margin-top: 10%;" />
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
	</div></div>
<script>
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id))
				return;
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/en_GB/sdk.js#xfbml=1&version=v2.5&appId=784370631641655";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
</script>

<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-61681760-1', 'auto');
  ga('send', 'pageview');

</script>

<!--Start of Zopim Live Chat Script-->
<script type="text/javascript">
window.$zopim||(function(d,s){var z=$zopim=function(c){z._.push(c)},$=z.s=
d.createElement(s),e=d.getElementsByTagName(s)[0];z.set=function(o){z.set.
_.push(o)};z._=[];z.set._=[];$.async=!0;$.setAttribute("charset","utf-8");
$.src="//v2.zopim.com/?3FaBH2AbCBScTYrE1SNg0qI87TN08KzS";z.t=+new Date;$.
type="text/javascript";e.parentNode.insertBefore($,e)})(document,"script");
</script>