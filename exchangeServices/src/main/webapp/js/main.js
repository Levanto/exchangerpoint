//common global variables
var verificationCodeCount = 0;
var exchangeOrderNumber = 0;
var sellOrderNumber = 0;
var buyOrderNumber = 0;
var ORDER_NO = 0;
var ORDER_TYPE = "";
var ORDER_ID = 0;
var sellVerificationCode = 0;
var buyVerificationCode = 0;
var PARENT;
var OTPSendingSuccess = true;

function generateOrderNumber() {
	var length = 10;
	var chars = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
	var orderNumber = '';
	for (var i = length; i > 0; --i)
		orderNumber += chars[Math.round(Math.random() * (chars.length - 1))];
	return orderNumber.toUpperCase();
}

function generateVerificationCode() {

	var randomCode = Math.random() + " ";
	var dot = randomCode.lastIndexOf('.');
	var code = randomCode.substring(dot + 1, 7);
	return code;

}

function checkMailId(mailids) {
	var arr = new Array('.com', '.net', '.org', '.biz', '.coop', '.info',
			'.museum', '.name', '.pro', '.edu', '.gov', '.int', '.mil', '.ac',
			'.ad', '.ae', '.af', '.ag', '.ai', '.al', '.am', '.an', '.ao',
			'.aq', '.ar', '.as', '.at', '.au', '.aw', '.az', '.ba', '.bb',
			'.bd', '.be', '.bf', '.bg', '.bh', '.bi', '.bj', '.bm', '.bn',
			'.bo', '.br', '.bs', '.bt', '.bv', '.bw', '.by', '.bz', '.ca',
			'.cc', '.cd', '.cf', '.cg', '.ch', '.ci', '.ck', '.cl', '.cm',
			'.cn', '.co', '.cr', '.cu', '.cv', '.cx', '.cy', '.cz', '.de',
			'.dj', '.dk', '.dm', '.do', '.dz', '.ec', '.ee', '.eg', '.eh',
			'.er', '.es', '.et', '.fi', '.fj', '.fk', '.fm', '.fo', '.fr',
			'.ga', '.gd', '.ge', '.gf', '.gg', '.gh', '.gi', '.gl', '.gm',
			'.gn', '.gp', '.gq', '.gr', '.gs', '.gt', '.gu', '.gv', '.gy',
			'.hk', '.hm', '.hn', '.hr', '.ht', '.hu', '.id', '.ie', '.il',
			'.im', '.in', '.io', '.iq', '.ir', '.is', '.it', '.je', '.jm',
			'.jo', '.jp', '.ke', '.kg', '.kh', '.ki', '.km', '.kn', '.kp',
			'.kr', '.kw', '.ky', '.kz', '.la', '.lb', '.lc', '.li', '.lk',
			'.lr', '.ls', '.lt', '.lu', '.lv', '.ly', '.ma', '.mc', '.md',
			'.mg', '.mh', '.mk', '.ml', '.mm', '.mn', '.mo', '.mp', '.mq',
			'.mr', '.ms', '.mt', '.mu', '.mv', '.mw', '.mx', '.my', '.mz',
			'.na', '.nc', '.ne', '.nf', '.ng', '.ni', '.nl', '.no', '.np',
			'.nr', '.nu', '.nz', '.om', '.pa', '.pe', '.pf', '.pg', '.ph',
			'.pk', '.pl', '.pm', '.pn', '.pr', '.ps', '.pt', '.pw', '.py',
			'.qa', '.re', '.ro', '.rw', '.ru', '.sa', '.sb', '.sc', '.sd',
			'.se', '.sg', '.sh', '.si', '.sj', '.sk', '.sl', '.sm', '.sn',
			'.so', '.sr', '.st', '.sv', '.sy', '.sz', '.tc', '.td', '.tf',
			'.tg', '.th', '.tj', '.tk', '.tm', '.tn', '.to', '.tp', '.tr',
			'.tt', '.tv', '.tw', '.tz', '.ua', '.ug', '.uk', '.um', '.us',
			'.uy', '.uz', '.va', '.vc', '.ve', '.vg', '.vi', '.vn', '.vu',
			'.ws', '.wf', '.ye', '.yt', '.yu', '.za', '.zm', '.zw');
	var mai = mailids.toLowerCase();
	var val = true;

	var dot = mai.lastIndexOf(".");
	var ext = mai.substring(dot, mai.length);

	var at = mai.indexOf("@");

	if (dot > 5 && at > 1) {
		for (var i = 0; i < arr.length; i++) {
			if (ext == arr[i]) {
				val = true;
				break;
			} else {
				val = false;
			}
		}
		if (val == false) {

			return false;
		}
	} else {
		return false;
	}
	return true;
}

/* Script start for contactus.jsp */

function contactusValidate() {
	document.getElementById('error_message').innerHTML = "&nbsp;"
	document.getElementById('error_message').style.color = "red";

	var name = document.getElementById("name").value;
	var emailId = document.getElementById("emailId").value;
	var subject = document.getElementById("subject").value;
	var message = document.getElementById("message").value;
	emailId = emailId.toLowerCase();

	if (subject == null || subject.split(' ').join('').length == 0
			|| name == null || name.split(' ').join('').length == 0
			|| emailId == null || emailId.split(' ').join('').length == 0
			|| message.split(' ').join('').length == 0) {
		document.getElementById('error_message').innerHTML = "All field are required.";
		return false;

	}
	if (checkMailId(emailId) == false) {
		document.getElementById('error_message').innerHTML = "Invalid email ID";
		return false;
	}
	if (message.length > 500) {
		document.getElementById('error_message').innerHTML = "Message is too long!";
		return false;
	}
	ajaxCallContactUS();
}

function ContactUs() {
	var name;
	var emailId;
	var message;
	var resultMessage;
	var subject;
}
function ajaxCallContactUS() {
	var form = $("#contactusform");
	var obj = new ContactUs();
	obj.emailId = $("#emailId").val();
	obj.name = $("#name").val();
	obj.message = $("#message").val();
	obj.subject = $("#subject").val();
	var JSONObj = JSON.stringify(obj);
	$(".coverAll").show();
	$
			.ajax({
				type : 'POST',
				url : '/contactus.htm',
				data : JSONObj,
				contentType : "application/json",
				success : function(msg) {
					if (msg == "success") {
						document.getElementById('error_message').style.color = "green";
						document.getElementById('error_message').innerHTML = "Message Sent Successfully!";
						$("#emailId").val("");
						$("#name").val("");
						$("#message").val("");
						$("#subject").val("");
					} else if (msg == "failure")
						document.getElementById('error_message').innerHTML = "Message sending failled.Please try again or try after some time";
					else if (msg == "wrongfields")
						document.getElementById('error_message').innerHTML = "Message fileds are not correct.Please go back and correct the fields";
					else if (msg == "")
						document.getElementById('error_message').innerHTML = "Message Sending Failed!";
					$(".coverAll").hide();
				},
				error : function() {
					document.getElementById('error_message').innerHTML = "Message Sending Failed!";
					$("#coverAll").hide();
				}

			});

}



/* script end for exchange order */

function validateExchangeOrder() {
	if ($("#exchangeamount").val().trim() == "") {
		$("#lblExchangeValidationMsg").html("Enter Exchange Amount");
	} else if(parseInt($("#exchangeamount").val().trim()) <= 999.99 && $("#exchangefrom").val() == "INR") {
		$("#lblExchangeValidationMsg").html("Minimum is 1000 INR");
    }else if ($("#exchangereceivedAmount").val().trim() == "") {
		$("#lblExchangeValidationMsg")
				.html(
						"Exchange rate not calculated please try again.");
	} else if ($("#exchangewalet").val().trim() == "") {
		$("#lblExchangeValidationMsg").html("Enter Your "+ $("#exchangeto").val() + " Address");
	} else {
		$("#lblExchangeValidationMsg").html("");
		$("#lblExchangeValidationMsg").parent().find(".next").click();
	}
}

function getExchangeFees() {
	$("#lblExchangeValidationMsg").html("");
	$("#exchangereceivedAmount").val("");
	var exchangeamount = $("#exchangeamount").val().trim();
	if ($("#exchangeamount").val().trim() == "") {
		return;
	} else if(parseInt($("#exchangeamount").val().trim()) <= 999.99 && $("#exchangefrom").val() == "INR") {
		$("#lblExchangeValidationMsg").html("Minimum is 1000 INR");
		return;
    }
	
	$("#exchangewalet").attr("placeholder", "Enter Your "+ $("#exchangeto").val() + " Address");
	
	$.ajax({
				method  : "GET",
				url     : "/exchangerate.htm",
				data    : "from=" + $("#exchangefrom").val() + "&to=" + $("#exchangeto").val() + "&amount=" + $("#exchangeamount").val().trim() + "&random="+Math.random(),
				success : function(response) {
							var resArr = response.split(":");
							if (response == "Input:invalid data!" || resArr.length <= 1) {
									$("#lblExchangeValidationMsg")
									.html(
											"Exchange rate not calculated please try again.");				
								} else {
		
								$("#exchangereceivedAmount").val(resArr[0]);
				                  }						
						   },
				error : function(error) {
					$("#exchangereceivedAmount").val("");
				}
		});
}

function getBuyFees() {

	$("#lblBuyValidationMsg").html("");
	$("#buyreceivedamount").val("");
	    
$("#buyamount").attr("placeholder", "Enter amount in "+ $("#buyfrom").val());

	if ($("#buyamount").val().trim() == "") {
		return;
	}else if(parseInt($("#buyamount").val().trim()) <= 9.99) {
		$("#lblBuyValidationMsg").html("Minimum is 10 "+ $("#buyfrom").val());
		return;
    }

	
$("#buyewalletid").attr("placeholder", "Enter Your "+ $("#buyto").val() + " Address");

	$.ajax({
		method  : "GET",
		url     : "/exchangerate.htm",
		data    : "from=" + $("#buyfrom").val() + "&to=" + $("#buyto").val() + "&amount=" + $("#buyamount").val().trim() + "&random="+Math.random(),
		success : function(response) {
					var resArr = response.split(":");
					
					if (response == "Input:invalid data!" || resArr.length <= 1) {
							$("#lblBuyValidationMsg")
							.html(
									"Exchange rate not calculated please try again.");				
						} 
						
                        $("#buyreceivedamount").val(resArr[0]);
												
				   },
		error : function(error) {
			$("#buyreceivedAmount").val("");
		}
});
	
}

function getSellFees() {
	$("#lblSellValidationMsg").html("");
	$("#sellreceivedAmount").val("");
	if ($("#sellamount").val().trim() == "") {
		return;
	}
	
	$.ajax({
		method  : "GET",
		url     : "/exchangerate.htm",
		data    : "from=" + $("#sellfrom").val() + "&to=" + $("#sellto").val() + "&amount=" + $("#sellamount").val().trim() + "&random="+Math.random(),
		success : function(response) {
					var resArr = response.split(":");
					if (response == "Input:invalid data!" || resArr.length <= 1) {
							$("#lblSellValidationMsg")
							.html(
									"Fees for current selection cannot be<br>estimsted. Please choose other<br>currency to proceed..");				
						} 
						
						var fees = 0;
						var amt = 0;
						try {
							amt = parseFloat(resArr[0].substring(0, resArr[0].indexOf(".") + 3));
							fees = parseFloat(resArr[1].substring(0, resArr[1].indexOf(".") + 3));
						} catch (error) {
						}

						$("#sellreceivedAmount").val(amt + " "+$("#sellto").val());
												
				   },
		error : function(error) {
			$("#sellreceivedAmount").val("");
		}
});
}

function blankDetailsCheckSell() {
	
	if ($("#sellamount").val().trim() == "") {
		$("#lblSellValidationMsg").html("Enter Sell Amount");
		return;
	} 
	
	if ($("#radioSellBT").is(":checked")) {
		if ($("#txtReceiverName").val().trim() == "") {
			$("#lblSellPaymentMethodCheck").html("Enter receiver's name.");
			return;
		} else if ($("#txtSellBankName").val().trim() == "") {
			$("#lblSellPaymentMethodCheck").html("Enter bank name.");
			return;
		} else if ($("#txtSellAccountNo").val().trim() == "") {
			$("#lblSellPaymentMethodCheck").html("Enter account number.");
			return;
		} else if ($("#txtSellCode").val().trim() == "") {
			$("#lblSellPaymentMethodCheck").html("Enter ifsc code.");
			return;
		} else if ($("#txtSwiftCode").val().trim() == "") {
			$("#lblSellPaymentMethodCheck").html("Enter swift/ibn code.");
			return;
		} else if ($("#txtSellBTCountry").val().trim() == "") {
			$("#lblSellPaymentMethodCheck").html("Enter country.");
			return;
		} else
			$("#lblSellPaymentMethodCheck").html("");
	} else if ($("#radioSellWU").is(":checked")
			|| $("#radioSellMG").is(":checked")) {
		if ($("#txSelltReceiverName").val().trim() == "") {
			$("#lblSellPaymentMethodCheck").html("Enter receiver's name.");
			return;
		} else if ($("#txtSellCity").val().trim() == "") {
			$("#lblSellPaymentMethodCheck").html("Enter city.");
			return;
		} else if ($("#txtSellCountry").val().trim() == "") {
			$("#lblSellPaymentMethodCheck").html("Enter country.");
			return;
		} else {
			$("#lblSellPaymentMethodCheck").html("");
		}
	} else {
			$("#lblSellPaymentMethodCheck").html("");
			$("#lblSellValidationMsg").parent().find(".next").click();
			return;
		
	}
	PARENT = $(PARENT).next();
	$(PARENT).prev().find(".next").trigger("click");
}



function sendVerificationCode(e) {
	var par = $($(e).parents("fieldset")[0]).parent();
	var email = $(par).find(".userEmail").val();
	var mobile = $(par).find(".userMobile").val();

	// validate email
	if (email == null || email.split(' ').join('').length == 0) {
		$(par).find(".verifyerror").html("Enter Your Email ID");
		return false;
	}

	if (checkMailId(email) == false) {
		$(par).find(".verifyerror").html("Invalid Email ID");
		return false;
	}
	if (mobile == null || mobile.split(' ').join('').length == 0) {
		$(par).find(".verifyerror").html("Enter Your Mobile Number");
		return false;
	}
	if (mobile.split(' ').join('').length > 0) {

		if (mobile.length < 10 || isNaN(mobile)) {
			$(par).find(".verifyerror").html("Invalid number!");
			return false;
		}

		if (mobile.length == 10) {
			$(par).find(".verifyerror").html("Country Code Missing!");
			return false;
		}
	}

	$(par).find(".verifyerror").html("&nbsp;");

	// toggal fields
	$(par).find(".validationDiv").hide();
	$(par).find(".OTPVerificationDIV").show();

	// generate order number
	exchangeOrderNumber = generateOrderNumber();

	PARENT = $(e).parent().parent();
	var formName = $(par).attr("id");
	if (formName == "exchangeForm")
		ORDER_TYPE = "E";
	else if (formName == "sellForm")
		ORDER_TYPE = "S";
	else if (formName == "sellFormB")
		ORDER_TYPE = "SB";
	else if (formName == "buyForm")
		ORDER_TYPE = "B";
	else if (formName == "buyFormWU")
		ORDER_TYPE = "BW";
		
	else
		ORDER_TYPE = "";

	sendDetailsToSendOTP(email, mobile, par);

}

function toggalEmailFields(e) {
	var par = $(e).parents("fieldset");
	// toggle fields
	$(par).find(".validationDiv").show();
	$(par).find(".OTPVerificationDIV").hide();	
	$(par).find(".userEmail").val("");
	$(par).find(".OTPVerifyCode").val("");
	$(par).find(".userMobile").val("");
	$(par).find(".verifyerror").val("&nbsp;");
	$(par).find("img.img-responsive").show();
	$(PARENT).find("img.sndngImgClass").show();
	$(PARENT).find("img.sentImgClass").hide();
	$(PARENT).find(".msgOfSentOTP").html("");
	$(PARENT).find(".msgOfSentOTP").hide();
	$(PARENT).find("img.failedImgClass").hide();
	$(PARENT).find("img.orderfailed").hide();
	$(PARENT).find("img.failedrobotImgClass").hide();
	$(PARENT).find("img.invalidemailImgclass").hide();
	$(PARENT).find(".resendMailLink").css("color", "#ccc");
	$(PARENT).find(".resendMailLink").removeAttr("onclick");
	PARENT = "";
}




// /////////////////////////////classes
function ExchangeOrder() {
	var exchangeId;
	var emailCount;
	var smsCount;
	var emailId;
	var phone;
	var sentEcurrency;
	var adminAccount;
	var siteName;
	var ewalletId;
	var receivedEcurrency;
	var exchangeAmount;
	var receivedAmount;
	var exchangeDate;
	var status;
	var verifyCode;
	var ecurrencyType;
	var exchangeDateTrans;
	var cardcode;
	var grecaptcharesponse;
}

function BuyOrder() {
	var buyId;
	var emailId;
	var phone;
	var siteName;
	var smsCount;
	var emailCount;
	var buyAmount;
	var paidAmount;
	var localCurrency;
	var adminAccount;
	var buyDate;
	var ecurrencyType;
	var ewalletId;
	var paymentMethod;
	var status;
	var verifyCode;
	var buyDateTrans;
	var cardNumber;
	var cardExpiryDate;
	var cvv;
	var cardHolderName;
	var cardType;
	var grecaptcharesponse;
}

function SellOrder() {
	var sellId;
	var emailId;
	var phone;
	var ecurrencyType;
	var city;
	var adminAccount;
	var sellAmount;
	var bankName;
	var accountHolderName;
	var country;
	var ifscCode;
	var accountNumber;
	var swiftCode;
	var paymentMethod;
	var sellDate;
	var localCurrency;
	var siteName;
	var smsCount;
	var emailCount;
	var receivedAmount;
	var status;
	var sellDateTrans;
	var verifyCode;
	var cardNumber;
	var cvv;
	var cardExpiryDate;
	var cardType;
	var cardcode;
	var grecaptcharesponse;
}

// ////////////////////////////classes

function sendDetailsToSendOTP(emailId, phoneNo, par) {

	// generate verification code
	// EXCHANGE_VERIFICATION_CODE = generateVerificationCode();
	var obj;
	var link;
	if (ORDER_TYPE == "E") {
		link = "/createexchangeorder.htm";
		obj = new ExchangeOrder();
		obj.emailId = emailId;
		obj.phone = phoneNo;
		obj.sentEcurrency = $("#exchangefrom").val().trim();
		obj.ewalletId = $("#exchangewalet").val().trim();
		obj.receivedEcurrency = $("#exchangeto").val().trim();
		obj.exchangeAmount = $("#exchangeamount").val().trim();
		obj.receivedAmount = $("#exchangereceivedAmount").val().trim();
		obj.ecurrencyType = $("#exchangefrom").val();

		if ($("#exchangefrom").find(":selected").text().trim() == "Amazon Gift Card"
				|| $("#exchangefrom").find(":selected").text().trim() == "Ebay Gift Card"
				|| $("#exchangefrom").find(":selected").text().trim() == "Paysafecard") {
			obj.cardcode = $("#exchangecardcode").val().trim()
			obj.ecurrencyType = "GIFTCARD";
		} else
			obj.cardcode = "0";
		obj.grecaptcharesponse = document.getElementById("rCaptcharesponce").value;
	} else if (ORDER_TYPE == "S" || ORDER_TYPE == "SB") {
		link = "/createsellorder.htm";
		obj = new SellOrder();
		obj.emailId = emailId;
		obj.phone = phoneNo;
		obj.sellAmount = $("#sellamount").val();
		obj.localCurrency = $("#sellto").val();
		obj.ecurrencyType = $("#sellfrom").val();
		obj.receivedAmount = $("#sellreceivedAmount").val();

		if ($("#radioSellBT").is(":checked")) {
			obj.paymentMethod = "Bank Transfer";
			obj.accountHolderName = $("#txtReceiverName").val();
			obj.accountNumber = $("#txtSellAccountNo").val();
			obj.bankName = $("#txtSellBankName").val();
			obj.ifscCode = $("#txtSellCode").val();
			obj.swiftCode = $("#txtSwiftCode").val();
			obj.country = $("#txtSellBTCountry").val();
		} else if ($("#radioSellWU").is(":checked")
				|| $("#radioSellMG").is(":checked")) {
			if ($("#radioSellWU").is(":checked"))
				obj.paymentMethod = "Western Union";
			else
				obj.paymentMethod = "MoneyGram";
			obj.accountHolderName = $("#txSelltReceiverName").val();
			obj.city = $("#txtSellCity").val();
			obj.country = $("#txtSellCountry").val();
		} else {
			obj.paymentMethod = "Card Payment";
			obj.cardType = $("#txtSellCardType").find(":selected").text();
			//obj.accountHolderName = $("#txSelltReceiverName").val();
		}
		if (($("#sellfrom").find(":selected").text().trim() == "Amazon Gift Card"
				|| $("#sellfrom").find(":selected").text().trim() == "Ebay Gift Card" || $(
				"#sellfrom").find(":selected").text().trim() == "Paysafecard")
				&& ($("#sellto").val() == "0" || $("#sellamount").val().trim() == "")) {
			obj.cardcode = $("#sellcardcode").val();
			obj.ecurrencyType = "GIFTCARD";
		} else
			obj.cardcode = 0;
		if(ORDER_TYPE == "S")
		obj.grecaptcharesponse = document.getElementById("g-recaptcha-response-1").value;
		else
		obj.grecaptcharesponse = document.getElementById("rCaptcharesponce").value;
		ORDER_TYPE="S"
			
	} else if (ORDER_TYPE == "B" || ORDER_TYPE == "BW") {
		link = "/createbuyorder.htm";
		obj = new BuyOrder();
		obj.emailId = emailId;
		obj.phone = phoneNo;
		obj.buyAmount = $("#buyamount").val();
		obj.paidAmount = $("#buyreceivedamount").val();
		obj.localCurrency = $("#buyfrom").val();
		obj.ecurrencyType = $("#buyto").val();
		obj.ewalletId = $("#buyewalletid").val();
		if ($("#radioBuyBT").is(":checked"))
			obj.paymentMethod = "Bank Transfer";
		else if ($("#radioBuyWU").is(":checked"))
			obj.paymentMethod = "Western Union";
		else if ($("#radioBuyMG").is(":checked"))
			obj.paymentMethod = "MoneyGram";
		else if ($("#radioBuyCard").is(":checked")) {
			obj.paymentMethod = "Card Payment";
		}
		if (ORDER_TYPE == "B")
		obj.grecaptcharesponse = document.getElementById("g-recaptcha-response-2").value;
		else
		obj.grecaptcharesponse = document.getElementById("rCaptcharesponce").value;
		
		ORDER_TYPE="B"
	}else
		return false;

	var JSONObj = JSON.stringify(obj);

	$.ajax({
				type : "POST",
				url : link,
				data : JSONObj,
				contentType : "application/json",
				success : function(response) {
					$(PARENT).find("img.sndngImgClass").hide();
					if (response == "GoogleCaptchaVerificationFailed"){
						$(PARENT).find(".verifyerror").html("<b style='color:red'>Robot Verification Failed.</b>");
						
						$(PARENT).find(".msgOfSentOTP").fadeIn();
						$(PARENT).find("img.failedrobotImgClass").fadeIn();
						$(PARENT).find("img.img-responsive").hide();
						$(PARENT).find("img.invalidemailImgclass").hide();
						$(PARENT).find("img.failedImgClass").hide();
						$(PARENT).find("img.orderfailed").hide();						
						$(PARENT).find("img.sentImgClass").hide();
						$(par).find(".validationDiv").show();
						$(par).find(".OTPVerificationDIV").hide();
						$(PARENT).find(".resendMailLink").css("color", "#ccc");
						$(PARENT).find(".resendMailLink").removeAttr("onclick");
						ORDER_ID = 0;
					} else if (response == "InvalidEmail"){
						$(PARENT).find(".verifyerror").html("<b style='color:red'>Invalid Email Address.</b>");
						
						$(PARENT).find(".msgOfSentOTP").fadeIn();
						$(PARENT).find("img.invalidemailImgclass").fadeIn();
						$(PARENT).find("img.failedrobotImgClass").hide();
						$(PARENT).find("img.img-responsive").hide();						
						$(PARENT).find("img.failedImgClass").hide();
						$(PARENT).find("img.orderfailed").hide();						
						$(PARENT).find("img.sentImgClass").hide();
						$(par).find(".validationDiv").show();
						$(par).find(".OTPVerificationDIV").hide();
						$(PARENT).find(".resendMailLink").css("color", "#ccc");
						$(PARENT).find(".resendMailLink").removeAttr("onclick");
						ORDER_ID = 0;
					} else if(response == "OTPFailed") {
						$(PARENT)
								.find(".msgOfSentOTP")
								.html(
										"<b style='color:red'>Message Sending Failed.Please Try Again.</b>");
						$(PARENT).find(".msgOfSentOTP").fadeIn();
						$(PARENT).find("img.failedImgClass").fadeIn();
						$(PARENT).find("img.failedrobotImgClass").hide();
						$(PARENT).find("img.invalidemailImgclass").hide();
						$(PARENT).find("img.orderfailed").hide();
						$(PARENT).find("img.sentImgClass").hide();
						ORDER_ID = 0;
					} else if (response == "OrderFailed"){
						$(PARENT)
								.find(".msgOfSentOTP")
								.html(
										"<b style='color:red'>Submit Order Failed</b>");
						$(PARENT).find(".msgOfSentOTP").fadeIn();
						$(PARENT).find("img.orderfailed").hide();
						$(PARENT).find("img.failedImgClass").hide();
						$(PARENT).find("img.failedrobotImgClass").hide();
						$(PARENT).find("img.invalidemailImgclass").hide();
						$(PARENT).find("img.sentImgClass").hide();
						ORDER_ID = 0;
				  } else if (response.split(":")[0] == "OTPSuccess") {					 
					  $(PARENT).find("img.sentImgClass").fadeIn();
						$(PARENT)
								.find(".msgOfSentOTP")
								.html(
										"<b style='color:green'>Check Your Mailbox/Spam/Mobile SMS </b>");
						$(PARENT).find(".msgOfSentOTP").fadeIn();
						$(PARENT).find("img.failedImgClass").hide();
						$(PARENT).find("img.failedrobotImgClass").hide();
						$(PARENT).find(".invalidemailImgclass").hide();
						$(PARENT).find(".resendMailLink").css("color",
								"blue");
						$(PARENT).find(".resendMailLink").removeAttr(
								"onclick",
								"sendExchangeVerificationCode(this)");
						ORDER_ID = response.split(":")[1].trim();
					} else if (response.split(":")[0] == "OrderSuccess") {
						ORDER_ID = response.split(":")[1].trim();
						saveOrderDetails();
						} 

				},
				error : function(error) {
					$(PARENT).find("img.sndngImgClass").hide();
					$(PARENT)
							.find(".msgOfSentOTP")
							.html(
									"<b style='color:red'>Message Sending Failed.Please Try Again.</b>");
					$(PARENT).find(".msgOfSentOTP").fadeIn();
					$(PARENT).find("img.failedImgClass").fadeIn();
					$(PARENT).find("img.sentImgClass").hide();
					$(PARENT).find("img.orderfailed").hide();
					$(PARENT).find("img.failedrobotImgClass").hide();
					$(PARENT).find("img.invalidemailImgclass").hide();
					ORDER_ID = 0;
				}
			});
}

function OTPCodeVerify(e) {
	var par = $(e).parents("fieldset");
	var verificationCode = $(par).find(".OTPVerifyCode").val();
	PARENT = par;
	$.ajax({
		type : "POST",
		url : "/verifyotp.htm",
		data : "verifycode=" + verificationCode + "&ordertype=" + ORDER_TYPE
				+ "&ordernumber=" + ORDER_ID,
		success : function(response) {
		  
			if (response.trim() == "success") {
				$("#exchangeverifybutton").hide();
				$(PARENT).find(".OTPVerifyCode").hide();
				$(PARENT).find("img.sentImgClass").hide();
				$(PARENT).find(".msgOfSentOTP").html("");
				$(PARENT).find("img.processingImgclass").fadeIn();
				saveOrderDetails();

			} else {
				$(PARENT).find(".OTPVerifyCodeError").html("Invalid Code!");
				$("#exchangeverifybutton").show();
			}
		},
		error : function(error) {
			$(PARENT).find(".OTPVerifyCodeError").html("Invalid Code!");
			$("#exchangeverifybutton").show();
		}
	});
}

function saveOrderDetails() {
	var link;
	if (ORDER_TYPE == "E") {
		link = "/exchangesuccess.htm";
	} else if (ORDER_TYPE == "S" || ORDER_TYPE == "SB")
		link = "/sellsuccess.htm";
	else
		link = "/buysuccess.htm";

	$.ajax({
		method : "POST",
		url : link,
		data : "orderNumber=" + ORDER_ID,
		success : function(response) {
			if(response=="success")
				{
				printmessageinpage();
				}
			else{
			$(PARENT).next().html(response);
			}
		},
		error : function(error) {
			$(PARENT).find(".OTPVerifyCodeError").html(
					"Problem while saving order.<br>Please try again.");
		}
	});
}

function toggalWalletAddressFields(){
	document.getElementById('buyewalletid').style.display = 'block';
    }