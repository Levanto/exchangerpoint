function changeFieldPlaceHolder(e) {
	var VAL = $(e).val();
	var TEXT = $(e).find("option:selected").text();
	if (VAL == "Neteller" || VAL == "Bitcoin")
		$("#exchangewalet").attr("placeholder", "Enter Your " + TEXT + " ID");
	else
		$("#exchangewalet").attr("placeholder", "Enter Your Account ID");
	getExchangeFees();
}

function toggalSellPaymentFields(e) {

	var par = $(e).parents("fieldset");

	if ($(par).parent().attr("id").trim() == "sellForm") {
		if ($("#sellamount").val().trim() == "") {
			$("#lblSellValidationMsg").html("Enter Sell Amount");
			return;
		} else if ($("#sellreceivedAmount").val().trim() == "") {
			$("#lblSellValidationMsg")
					.html("Exchange rate not calculated please try again.");
			return;
		} else {
			$("#lblSellValidationMsg").html("");
		}
	}

	var radios = $(par).find("input[type='radio']");
	PARENT = par;

	$(par).find(".chkRadio").html("");
	var flag = false;
	for (var i = 0; i < radios.size(); i++) {
		if ($(radios[i]).is(":checked")) {
			flag = true;
			var nextFieldSet = $(par).next();
			if ($(radios[i]).attr("class").trim() == "radBT") {
				$(nextFieldSet).find("div.fBankTransfer").show();
				$(nextFieldSet).find("div.fMU").hide();
				$(nextFieldSet).find("div.fCardPayment").hide();
			} else if ($(radios[i]).attr("class").trim() == "radMG"
					|| $(radios[i]).attr("class").trim() == "radWU") {
				$(nextFieldSet).find("div.fMU").show();
				$(nextFieldSet).find("div.fBankTransfer").hide();
				$(nextFieldSet).find("div.fCardPayment").hide();
			} else if ($(radios[i]).attr("class").trim() == "radC") {
				$(nextFieldSet).find("div.fCardPayment").show();
				$(nextFieldSet).find("div.fMU").hide();
				$(nextFieldSet).find("div.fBankTransfer").hide();
			}
			PARENT = $(PARENT).next();
			$(par).find(".next").trigger("click");
		}
	}
	if (flag == false)
		$(par).find(".chkRadio").html("Select Payment Method");
}

function toggalBuyPaymentFields(e) {
	var par = $(e).parents("fieldset");
	PARENT = par;
	if ($("#buyamount").val().trim() == "") {
		$("#lblBuyValidationMsg").html("Enter Buy Amount");
		return;
	}else if ($("#buyreceivedamount").val().trim() == "") {
		$("#lblBuyValidationMsg")
				.html(
						"Exchange rate not calculated please try again.");
		return;
	}  else if ($("#buyewalletid").val().trim() == "") {
		$("#lblBuyValidationMsg").html("Enter Your " + $("#buyto").val() + " Address");
		return;
	} else {
		$("#lblBuyValidationMsg").html("");
	}
	var radios = $(par).find("input[type='radio']");
	var fieldSet = '<fieldset class="img-reponsive" id="divCardPayment" >						<h2 class="fs-title"></h2>								<div width="100%" class="fCardPayment">									<input type="text" placeholder="Card No" id="txtBuyCardNo"										name="buyCardNo" onchange="setCardType()" /> <input										type="text" placeholder="Card Holder Name"										id="txtBuyCardHolderName" name="buyCardHolderName" /> <input										type="text" class="cardTypeName" style="border: none"										placeholder="Card Type" id="txtBuyCardType" name="buyCardType" /><input										type="text" placeholder="Expiry Date" id="txtBuyExpiryDate"										name="buyExpiryNo" style="width: 65%" /><input type="text"										style="width: 30%" id="txtCardCVV" placeholder="CVV"										id="txtBuyCVV" name="cardCVV" />								</div>								<div id="RecaptchaFieldBuy"></div><label id="lblBuyPaymentMethodCheck"		 style="color:red"></label>		<br>				<input type="button" name="previous"									class="previous action-button" value="Previous" /> <input									type="button"  class="action-button"		onclick="blankDetailsCheckBuy()"							value="Next" /> <input					style="display:none"				type="button"  class="next action-button"									value="Next" /></fieldset>';
	$(par).find(".chkRadio").html("");
	var flag = false;
	for (var i = 0; i < radios.size(); i++) {
		if ($(radios[i]).is(":checked")) {
			flag = true;
			if ($(radios[i]).attr("class").trim() == "radC") {
				if ($(par).next().attr("id") == undefined)
					$(par).after(fieldSet);
				registerNextPreviousFunction();
			} else {
				if ($(par).next().attr("id") != undefined)
					$(par).next().remove();
			}
			PARENT = $(PARENT).next();
			$(par).find(".next").trigger("click");
		}
	}
	if (flag == false)
		$(par).find(".chkRadio").html("Select Payment Method..");
}

function registerNextPreviousFunction() {

	var current_fs, next_fs, previous_fs; // fieldsets
	var left, opacity, scale; // fieldset properties which we will animate
	var animating; // flag to prevent quick multi-click glitches

	$(".next").click(
			function() {
				if (animating)
					return false;
				animating = true;

				current_fs = $(this).parent();
				next_fs = $(this).parent().next();

				// activate next step on progressbar using the index of next_fs
				$("#progressbar li").eq($("fieldset").index(next_fs)).addClass(
						"active");

				// show the next fieldset
				current_fs.css({
					'position' : 'absolute'
				});
				next_fs.show();
				// hide the current fieldset with style
				current_fs.animate({
					opacity : 0
				}, {
					step : function(now, mx) {
						// as the opacity of current_fs reduces to 0 - stored in
						// "now"
						// 1. scale current_fs down to 80%
						scale = 1 - (1 - now) * 0.2;
						// 2. bring next_fs from the right(50%)
						left = (now * 50) + "%";
						// 3. increase opacity of next_fs to 1 as it moves in
						opacity = 1 - now;
						current_fs.css({
							'transform' : 'scale(' + scale + ')'
						});
						next_fs.css({
							'left' : left,
							'opacity' : opacity
						});
					},
					duration : 800,
					complete : function() {
						current_fs.hide();
						animating = false;
					},
					// this comes from the custom easing plugin
					easing : 'easeInOutBack'
				});
			});

	$(".previous").click(
			function() {
				if (animating)
					return false;
				animating = true;

				current_fs = $(this).parent();
				previous_fs = $(this).parent().prev();

				// de-activate current step on progressbar
				$("#progressbar li").eq($("fieldset").index(current_fs))
						.removeClass("active");

				// show the previous fieldset
				previous_fs.show();
				// hide the current fieldset with style
				current_fs.animate({
					opacity : 0
				}, {
					step : function(now, mx) {
						// as the opacity of current_fs reduces to 0 - stored in
						// "now"
						// 1. scale previous_fs from 80% to 100%
						scale = 0.8 + (1 - now) * 0.2;
						// 2. take current_fs to the right(50%) - from 0%
						left = ((1 - now) * 50) + "%";
						// 3. increase opacity of previous_fs to 1 as it moves
						// in
						opacity = 1 - now;
						current_fs.css({
							'left' : left
						});
						previous_fs.css({
							'transform' : 'scale(' + scale + ')',
							'opacity' : opacity
						});
					},
					duration : 800,
					complete : function() {
						current_fs.hide();
						animating = false;
					},
					// this comes from the custom easing plugin
					easing : 'easeInOutBack'
				});
			});

}
