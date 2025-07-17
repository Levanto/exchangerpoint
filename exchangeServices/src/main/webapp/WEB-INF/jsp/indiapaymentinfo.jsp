<script type="text/javascript">	
function printmessageinpage() {
if(ORDER_TYPE == "E")
{
$("#exchangesuccess").html("<p style='color:green' >Your order number <b>" + ORDER_ID + "</b> has been succesfully submitted<br>Please send us " + $("#exchangeamount").val() + " INR </p><br><p style='color:blue'>1.NEFT: Govind Singh ACCOUNT 141701511093 IFSC ICIC00014171</p><br><p style='color:brown'>2.IMPS: MMID 9229864 Mobile 9910922033 </p><br><p style='color:orange'>3.UPI LINK: exchangerpoint@UPI </p><br><p style='color:blue'> 4.PayTM: Send Paytm to 9910922033 or Scan Below QR code </p>");
		
}

$(PARENT).find(".OTPVerifyCodeError").html("");
$(PARENT).find(".next").trigger("click");
ORDER_ID = 0;
PARENT = "";		
}		
</script>