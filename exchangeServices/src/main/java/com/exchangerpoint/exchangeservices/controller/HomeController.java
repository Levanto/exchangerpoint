/**
 *
 */
package com.exchangerpoint.exchangeservices.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exchangerpoint.databaseservices.entity.BuyOrder;
import com.exchangerpoint.databaseservices.entity.ExchangeOrder;
import com.exchangerpoint.databaseservices.entity.GiftCard;
import com.exchangerpoint.databaseservices.entity.SEOData;
import com.exchangerpoint.databaseservices.entity.Scammer;
import com.exchangerpoint.databaseservices.entity.SellOrder;
import com.exchangerpoint.databaseservices.entity.Testimonial;
import com.exchangerpoint.exchangeservices.bean.ContactUs;
import com.exchangerpoint.exchangeservices.bean.LoginBean;
import com.exchangerpoint.exchangeservices.bean.Order;
import com.exchangerpoint.exchangeservices.common.ApplicationDataRepository;
import com.exchangerpoint.exchangeservices.exception.ExchangeException;
import com.exchangerpoint.exchangeservices.service.HomeService;
import com.exchangerpoint.exchangeservices.service.MailService;
import com.exchangerpoint.exchangeservices.service.MessagingService;
import com.exchangerpoint.exchangeservices.service.ValidationService;
import com.exchangerpoint.exchangeservices.utility.Constants;
import com.exchangerpoint.exchangeservices.utility.GoogleRobotVerification;
import com.exchangerpoint.exchangeservices.utility.OrderType;
import com.exchangerpoint.exchangeservices.utility.Validator;
import com.exchangerpoint.genericservices.service.GenericService;
import com.google.gson.Gson;

/**
 * @author Admin
 */
@Controller
public class HomeController {

	/* initialize Constants object */
	Constants CONSTANTS = new Constants();

	/**
	 * HomeService Object
	 */
	@Autowired
	@Qualifier(Constants.HOMESERVICE)
	private HomeService homeService;

	@Autowired
	@Qualifier(com.exchangerpoint.genericservices.util.Constants.GENERIC_SERVICE)
	private GenericService genericService;

	@Autowired
	@Qualifier(Constants.APPLICATIONCONTEXT)
	private ApplicationDataRepository applicationDataRepository;

	@Autowired
	private MessagingService messagingService;

	@Autowired
	private MailService mailService;

	@Autowired
	private ValidationService validationService;

	// Local Constants
	private static final String ORDER_SUCCESS = "OrderSuccess";
	private static final String ORDER_FAILED = "OrderFailed";
	private static final String OTP_SUCCESS = "OTPSuccess";
	private static final String OTP_FAILED = "OTPFailed";
	private static final String SUCCESS = "success";
	private static final String FAILURE = "failure";
	private static final String BITCOIN = "Bitcoin";
	private static final String ETHER = "Ether";
	private static final String RIPPLE = "Ripple";
	private static final String SITE_NAME = com.exchangerpoint.exchangeservices.common.Constants.SITE_NAME;
	
		
	/**
	 * Log implementation for LoginController
	 */
	protected final Log logger = LogFactory.getLog(HomeController.class);

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpSession session, HttpServletRequest request) {
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("status", "ACTIVE");
		List<SEOData> lstSEOData = genericService.getOrderedList(SEOData.class, valueMap, "createdDate", "desc");
		session.setAttribute("lstSEOData", lstSEOData);
		return "/";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String postHome(Model model, HttpSession session, HttpServletRequest request) {

		return "home";
	}

	@RequestMapping(value = "/privacy.htm", method = RequestMethod.GET)
	public String privacy(Model model, HttpSession session, HttpServletRequest request) {

		return "privacy";

	}

	/*
	 * Bitcoin Buy order URL mapping starts here
	 */

	@RequestMapping(value = "/sell-bitcoin-for-paytm-india.htm", method = RequestMethod.GET)
	public String sellbitcoinwithpaytm(Model model, HttpSession session, HttpServletRequest request) {

		return "sellbitcoinindiawallet";

	}
	
	@RequestMapping(value = "/sell-bitcoin-for-airtel-money-india.htm", method = RequestMethod.GET)
	public String sellbitcoinwithairtel(Model model, HttpSession session, HttpServletRequest request) {

		return "sellbitcoinindiawallet";

	}
	@RequestMapping(value = "/sell-bitcoin-for-tez-by-google.htm", method = RequestMethod.GET)
	public String sellbitcoinwithtez(Model model, HttpSession session, HttpServletRequest request) {

		return "sellbitcoinindiawallet";

	}
	
	@RequestMapping(value = "/sell-bitcoin-for-phonepay-india.htm", method = RequestMethod.GET)
	public String sellbitcoinwithphonepay(Model model, HttpSession session, HttpServletRequest request) {

		return "sellbitcoinindiawallet";

	}
	
	@RequestMapping(value = "/sell-bitcoin-for-india-digital-wallet.htm", method = RequestMethod.GET)
	public String sellbitcoinwithindiawallet(Model model, HttpSession session, HttpServletRequest request) {

		return "sellbitcoinindiawallet";

	}
	
	@RequestMapping(value = "/buy-bitcoin-with-paytm-india.htm", method = RequestMethod.GET)
	public String buybitcoinwithpaytm(Model model, HttpSession session, HttpServletRequest request) {

		return "bitcoinpaytm";

	}

	@RequestMapping(value = "/buy-bitcoin-with-paym-united-kingdom.htm", method = RequestMethod.GET)
	public String buybitcoinwithpaym(Model model, HttpSession session, HttpServletRequest request) {

		return "bitcoinpaym";

	}

	@RequestMapping(value = "/buy-bitcoin-with-national-bank-transfer-united-kingdom.htm", method = RequestMethod.GET)
	public String buybitcoinwithukbank(Model model, HttpSession session, HttpServletRequest request) {

		return "bitcoinbankuk";

	}

	@RequestMapping(value = "/buy-bitcoin-with-cash-deposit-united-kingdom.htm", method = RequestMethod.GET)
	public String buybitcoinwithukbankcash(Model model, HttpSession session, HttpServletRequest request) {

		return "bitcoinbankukcash";

	}

	@RequestMapping(value = "/buy-bitcoin-with-imps-india.htm", method = RequestMethod.GET)
	public String buybitcoinwithimpsindia(Model model, HttpSession session, HttpServletRequest request) {

		return "bitcoinimps";

	}

	@RequestMapping(value = "/buy-bitcoin-with-online-bank-transfer-india.htm", method = RequestMethod.GET)
	public String buybitcoinwithindiabank(Model model, HttpSession session, HttpServletRequest request) {

		return "bitcoinimps";

	}

	@RequestMapping(value = "/buy-bitcoin-in-india.htm", method = RequestMethod.GET)
	public String buybitcoinindia(Model model, HttpSession session, HttpServletRequest request) {

		return "bitcoinindia";

	}
	
	@RequestMapping(value = "/buy-ethereum-in-india.htm", method = RequestMethod.GET)
	public String buyethereumindia(Model model, HttpSession session, HttpServletRequest request) {

		return "etherindia";

	}
	
	@RequestMapping(value = "/buy-ripple-in-india.htm", method = RequestMethod.GET)
	public String buyrippleindia(Model model, HttpSession session, HttpServletRequest request) {

		return "rippleindia";

	}
	
	@RequestMapping(value = "/buy-mobile-recharge-in-india.htm", method = RequestMethod.GET)
	public String buymobilerechargeindia(Model model, HttpSession session, HttpServletRequest request) {

		return "mobilerechargeindia";

	}
	
	@RequestMapping(value = "/buy-crypto-currency-world-wide.htm", method = RequestMethod.GET)
	public String buycryptoindiaworldwide(Model model, HttpSession session, HttpServletRequest request) {

		return "cryptoworldwide";

	}
	
	@RequestMapping(value = "/buy-bitcoin-with-neft-india.htm", method = RequestMethod.GET)
	public String buybitcoinwithneftbank(Model model, HttpSession session, HttpServletRequest request) {

		return "bitcoinimps";

	}

	@RequestMapping(value = "/buy-bitcoin-with-upi-india.htm", method = RequestMethod.GET)
	public String buybitcoinwithupiindia(Model model, HttpSession session, HttpServletRequest request) {

		return "bitcoinimps";

	}

	@RequestMapping(value = "/buy-bitcoin-with-unified-payment-interface-india.htm", method = RequestMethod.GET)
	public String buybitcoinwithupi(Model model, HttpSession session, HttpServletRequest request) {

		return "bitcoinupi";

	}

	@RequestMapping(value = "/buy-bitcoin-with-airtel-money-india.htm", method = RequestMethod.GET)
	public String buybitcoinwithairtelmoney(Model model, HttpSession session, HttpServletRequest request) {

		return "bitcoinairtelmoney";

	}

	@RequestMapping(value = "/buy-bitcoin-with-pingit-united-kingdom.htm", method = RequestMethod.GET)
	public String buybitcoinwithpingit(Model model, HttpSession session, HttpServletRequest request) {

		return "bitcoinpingit";

	}

	@RequestMapping(value = "/buy-ethereum-with-bitcoin.htm", method = RequestMethod.GET)
	public String buyetherwithbitcoin(Model model, HttpSession session, HttpServletRequest request) {

		return "buyetherwithbitcoin";

	}
	
	@RequestMapping(value = "/buy-bitcoin-with-western-union-united-states.htm", method = RequestMethod.GET)
	public String buybitcoinwithwesternunionusa(Model model, HttpSession session, HttpServletRequest request) {

		return "bitcoinglobal";
	}
	
	@RequestMapping(value = "/buy-bitcoin-with-moneygram-united-states.htm", method = RequestMethod.GET)
	public String buybitcoinwithmoneygramusa(Model model, HttpSession session, HttpServletRequest request) {

		return "bitcoinglobal";
	}
	
	@RequestMapping(value = "/buy-bitcoin-with-bank-transfer-united-states.htm", method = RequestMethod.GET)
	public String buybitcoinwithbanktransferusa(Model model, HttpSession session, HttpServletRequest request) {

		return "bitcoinglobalbank";
	}
	
	
	@RequestMapping(value = "/buy-bitcoin-with-bank-transfer-europe.htm", method = RequestMethod.GET)
	public String buybitcoinwithbanktransfereurope(Model model, HttpSession session, HttpServletRequest request) {

		return "bitcoinglobalbank";
	}
	
	@RequestMapping(value = "/buy-bitcoin-with-transferwise.htm", method = RequestMethod.GET)
	public String buybitcoinwithtransferwise(Model model, HttpSession session, HttpServletRequest request) {

		return "bitcointransferwise";
	}
	
	@RequestMapping(value = "/buy-bitcoin-with-western-union-united-kingdom.htm", method = RequestMethod.GET)
	public String buybitcoinwithwesternunionuk(Model model, HttpSession session, HttpServletRequest request) {

		return "bitcoinglobal";
	}
	
	
	
	
	@RequestMapping(value = "/sell-bitcoin-for-online-bank-transfer-india.htm", method = RequestMethod.GET)
	public String sellbitcoinforbanktransfer(Model model, HttpSession session, HttpServletRequest request) {

		return "sellbitcoinbank";
	}
	
	
	@RequestMapping(value = "/sell-bitcoin-for-national-bank-transfer-United-Kingdom.htm", method = RequestMethod.GET)
	public String sellbitcoinforukbanktransfer(Model model, HttpSession session, HttpServletRequest request) {

		return "sellbitcoinukbank";
	}
	
	
	@RequestMapping(value = "/sell-bitcoin-for-moneygram.htm", method = RequestMethod.GET)
	public String sellbitcoinformoneygram(Model model, HttpSession session, HttpServletRequest request) {

		return "sellbitcoinmoneygram";
	}
	
	@RequestMapping(value = "/buy-amazon-giftcard-with-bitcoin.htm", method = RequestMethod.GET)
	public String buyamazongiftcard(Model model, HttpSession session, HttpServletRequest request) {

		return "buygiftcardwithbitcoin";
	}
	
	@RequestMapping(value = "/buy-flipkart-giftcard-with-bitcoin.htm", method = RequestMethod.GET)
	public String buyflipkartgiftcard(Model model, HttpSession session, HttpServletRequest request) {

		return "buygiftcardwithbitcoin";
	}
	
	@RequestMapping(value = "/buy-myntra-giftcard-with-bitcoin.htm", method = RequestMethod.GET)
	public String buyaamazongiftcard(Model model, HttpSession session, HttpServletRequest request) {

		return "buygiftcardwithbitcoin";
	}
	
	
	
	@RequestMapping(value = "/buy-bitcoin-with-western-union-australia.htm", method = RequestMethod.GET)
	public String buybitcoinwithwesternunionaustralia(Model model, HttpSession session, HttpServletRequest request) {

		return "bitcoinglobal";
	}
	
	@RequestMapping(value = "/buy-bitcoin-with-moneygram-australia.htm", method = RequestMethod.GET)
	public String buybitcoinwithmoneygramaustralia(Model model, HttpSession session, HttpServletRequest request) {

		return "bitcoinglobal";
	}
	@RequestMapping(value = "/buy-bitcoin-with-western-union-canada.htm", method = RequestMethod.GET)
	public String buybitcoinwithwesternunioncanada(Model model, HttpSession session, HttpServletRequest request) {

		return "bitcoinglobal";
	}
	
	@RequestMapping(value = "/buy-bitcoin-with-moneygram-united-kingdom.htm", method = RequestMethod.GET)
	public String buybitcoinwithmoneygramuk(Model model, HttpSession session, HttpServletRequest request) {

		return "bitcoinglobal";
	}
	
	@RequestMapping(value = "/buy-bitcoin-with-moneygram-canada.htm", method = RequestMethod.GET)
	public String buybitcoinwithmoneygramcanada(Model model, HttpSession session, HttpServletRequest request) {

		return "bitcoinglobal";
	}

	/*
	 * Bitcoin Buy URL mapping ends here
	 */

	/*
	 * Ether Buy URL mapping starts here
	 */
	@RequestMapping(value = "/buy-ether-with-paytm-india.htm", method = RequestMethod.GET)
	public String buyetherwithpaytm(Model model, HttpSession session, HttpServletRequest request) {

		return "etherpaytm";

	}

	@RequestMapping(value = "/buy-ether-with-paym-united-kingdom.htm", method = RequestMethod.GET)
	public String buyetherwithpaym(Model model, HttpSession session, HttpServletRequest request) {

		return "etherpaym";

	}

	@RequestMapping(value = "/buy-ether-with-national-bank-transfer-united-kingdom.htm", method = RequestMethod.GET)
	public String buyetherwithukbank(Model model, HttpSession session, HttpServletRequest request) {

		return "etherbankuk";

	}

	@RequestMapping(value = "/buy-ether-with-cash-deposit-united-kingdom.htm", method = RequestMethod.GET)
	public String buyetherwithukbankcash(Model model, HttpSession session, HttpServletRequest request) {

		return "etherbankukcash";

	}

	@RequestMapping(value = "/buy-ether-with-imps-india.htm", method = RequestMethod.GET)
	public String buyetherwithimpsindia(Model model, HttpSession session, HttpServletRequest request) {

		return "etherimps";

	}

	@RequestMapping(value = "/buy-ether-with-online-bank-transfer-india.htm", method = RequestMethod.GET)
	public String buyetherwithindiabank(Model model, HttpSession session, HttpServletRequest request) {

		return "etherneft";

	}

	@RequestMapping(value = "/buy-ether-with-neft-india.htm", method = RequestMethod.GET)
	public String buyetherwithneftbank(Model model, HttpSession session, HttpServletRequest request) {

		return "etherneft";

	}

	@RequestMapping(value = "/buy-ether-with-upi-india.htm", method = RequestMethod.GET)
	public String buyetherwithupiindia(Model model, HttpSession session, HttpServletRequest request) {

		return "etherupi";

	}

	@RequestMapping(value = "/buy-ether-with-unified-payment-interface-india.htm", method = RequestMethod.GET)
	public String buyetherwithupi(Model model, HttpSession session, HttpServletRequest request) {

		return "etherupi";

	}

	@RequestMapping(value = "/buy-ether-with-airtel-money-india.htm", method = RequestMethod.GET)
	public String buyetherwithairtelmoney(Model model, HttpSession session, HttpServletRequest request) {

		return "etherairtelmoney";

	}

	@RequestMapping(value = "/buy-ether-with-pingit-united-kingdom.htm", method = RequestMethod.GET)
	public String buyetherwithpingit(Model model, HttpSession session, HttpServletRequest request) {

		return "etherpingit";

	}

	/*
	 * Ether Buy URL mapping ends here
	 */

	/*
	 * Ripple Buy URL mapping starts here
	 */

	@RequestMapping(value = "/buy-ripple-with-paytm-india.htm", method = RequestMethod.GET)
	public String buyripplewithpaytm(Model model, HttpSession session, HttpServletRequest request) {

		return "ripplepaytm";

	}

	@RequestMapping(value = "/buy-ripple-with-paym-united-kingdom.htm", method = RequestMethod.GET)
	public String buyripplewithpaym(Model model, HttpSession session, HttpServletRequest request) {

		return "ripplepaym";

	}

	@RequestMapping(value = "/buy-ripple-with-national-bank-transfer-united-kingdom.htm", method = RequestMethod.GET)
	public String buyripplewithukbank(Model model, HttpSession session, HttpServletRequest request) {

		return "ripplebankuk";

	}

	@RequestMapping(value = "/buy-ripple-with-cash-deposit-united-kingdom.htm", method = RequestMethod.GET)
	public String buyripplewithukbankcash(Model model, HttpSession session, HttpServletRequest request) {

		return "ripplebankukcash";

	}

	@RequestMapping(value = "/buy-ripple-with-imps-india.htm", method = RequestMethod.GET)
	public String buyripplewithimpsindia(Model model, HttpSession session, HttpServletRequest request) {

		return "rippleimps";

	}

	@RequestMapping(value = "/buy-ripple-with-online-bank-transfer-india.htm", method = RequestMethod.GET)
	public String buyripplewithindiabank(Model model, HttpSession session, HttpServletRequest request) {

		return "rippleneft";

	}

	@RequestMapping(value = "/buy-ripple-with-neft-india.htm", method = RequestMethod.GET)
	public String buyripplewithneftbank(Model model, HttpSession session, HttpServletRequest request) {

		return "rippleneft";

	}

	@RequestMapping(value = "/buy-ripple-with-upi-india.htm", method = RequestMethod.GET)
	public String buyripplewithupiindia(Model model, HttpSession session, HttpServletRequest request) {

		return "rippleupi";

	}

	@RequestMapping(value = "/buy-ripple-with-unified-payment-interface-india.htm", method = RequestMethod.GET)
	public String buyripplewithupi(Model model, HttpSession session, HttpServletRequest request) {

		return "rippleupi";

	}

	@RequestMapping(value = "/buy-ripple-with-airtel-money-india.htm", method = RequestMethod.GET)
	public String buyripplewithairtelmoney(Model model, HttpSession session, HttpServletRequest request) {

		return "rippleairtelmoney";

	}

	@RequestMapping(value = "/buy-ripple-with-pingit-united-kingdom.htm", method = RequestMethod.GET)
	public String buyripplewithpingit(Model model, HttpSession session, HttpServletRequest request) {

		return "rippleripple";

	}

	@RequestMapping(value = "/buy-ripple-with-bitcoin.htm", method = RequestMethod.GET)
	public String buyetherwithripple(Model model, HttpSession session, HttpServletRequest request) {

		return "buyripplewithbitcoin";

	}

	/*
	 * Ripple Buy URL mapping ends here
	 */

	/*
	 * Electroneum Buy URL mapping starts here
	 */
	
	
	@RequestMapping(value = "/buy-electroneum-with-paytm-india.htm", method = RequestMethod.GET)
	public String buyelectroneumwithpaytm(Model model, HttpSession session, HttpServletRequest request) {

		return "electroneumpaytm";

	}
	
	
	@RequestMapping(value = "/buy-electroneum-with-online-bank-transfer-india.htm", method = RequestMethod.GET)
	public String buyelectroneumwithbanktransfer(Model model, HttpSession session, HttpServletRequest request) {

		return "electroneumbanktransfer";

	}
	

	@RequestMapping(value = "/buy-electroneum-in-india.htm", method = RequestMethod.GET)
	public String buyelectroneumininida(Model model, HttpSession session, HttpServletRequest request) {

		return "electroneumindia";

	}
	
	@RequestMapping(value = "/buy-electroneum-with-bitcoin.htm", method = RequestMethod.GET)
	public String buyelectroneumwithbitcoin(Model model, HttpSession session, HttpServletRequest request) {

		return "buyelectroneumwithbitcoin";

	}
	
	
	@RequestMapping(value = "/buy-electroneum-with-imps-india.htm", method = RequestMethod.GET)
	public String buyelectroneumwithimps(Model model, HttpSession session, HttpServletRequest request) {

		return "electroneumbanktransfer";

	}

	@RequestMapping(value = "/what-is-electronium-and-how-to-buy-electroneum.htm", method = RequestMethod.GET)
	public String buyelectroneumfaq(Model model, HttpSession session, HttpServletRequest request) {

		return "electroneumfaq";

	}
	
	/*
	 * Electroneum Buy URL mapping end here
	 */
	
	@RequestMapping(value = "/traceorder.htm", method = RequestMethod.GET)
	public String postTraceOrder(Model model, HttpSession session, HttpServletRequest request) {
		try {
			String orderNumber = request.getParameter("order");
			if (orderNumber == null)
				orderNumber = (String)request.getAttribute("order");

			if (orderNumber == null || orderNumber.length() != 10) {
				model.addAttribute("ExchangeOrder", null);
				model.addAttribute("SellOrder", null);
				model.addAttribute("BuyOrder", null);
			} else {

				Order order = homeService.getOrder(orderNumber);
				if (order != null) {
					session.setAttribute("resultMessage", "There is one order found with this order number");
					String orderDetail = "";
					if (order.getExchangeOrder() != null) {
						ExchangeOrder eo = order.getExchangeOrder();
						orderDetail += "<table style='width:59%; margin-left:10%;color:#302692' class='table table-bordered table-condensed'><tr><td>Order Type </td><td> Exchange-<g>"
								+ ((eo.getExchangeId() == null) ? "" : eo.getExchangeId()) + "</g></td></tr>";
						orderDetail += "<tr><td>Transaction Date </td><td> "
								+ ((eo.getExchangeDate() == null) ? "" : eo.getExchangeDate()) + "</td></tr>";
						orderDetail += "<tr><td>Email Id </td><td>"
								+ ((eo.getEmailId() == null) ? "" : eo.getEmailId()) + "</td></tr>";
						orderDetail += "<tr><td>Phone No </td><td> " + ((eo.getPhone() == null) ? "" : eo.getPhone())
								+ "</td></tr>";
						orderDetail += "<tr><td>E-Wallet Id </td><td> "
								+ ((eo.getEwalletId() == null) ? "" : eo.getEwalletId()) + "</td></tr>";
						orderDetail += "<tr><td>Sent Currency </td><td> "
								+ ((eo.getSentEcurrency() == null) ? "" : eo.getSentEcurrency()) + "<td></tr>";
						orderDetail += "<tr><td>E-Currency Amount </td><td> "
								+ ((eo.getExchangeAmount() == null) ? "" : eo.getExchangeAmount()) + "<td></tr>";
						orderDetail += "<tr><td>Received E-Currency </td><td> "
								+ ((eo.getReceivedEcurrency() == null) ? "" : eo.getReceivedEcurrency()) + "<td></tr>";
						orderDetail += "<tr><td>Received Amount </td><td> "
								+ ((eo.getReceivedAmount() == null) ? "" : eo.getReceivedAmount()) + "</td></tr>";
						if(eo.getStatus().equalsIgnoreCase("PENDING"))
						orderDetail += "<tr><td>Status </td><td> <r>"
								+ ((eo.getStatus() == null) ? "" : eo.getStatus()) + "</r></td></tr></table>";
						else
						orderDetail += "<tr><td>Status </td><td> <g>"
									+ ((eo.getStatus() == null) ? "" : eo.getStatus()) + "</g></td></tr></table>";
					
					} else if (order.getSellOrder() != null) {
						SellOrder so = order.getSellOrder();
						orderDetail += "<table style='width:59%; margin-left:10%;color:#302692' class='table table-bordered table-condensed'><tr><td>Order Type </td><td> Sell-<g>"
								+ ((so.getSellId() == null) ? "" : so.getSellId()) + "</g></td></tr>";
						orderDetail += "<tr><td>Transaction Date </td><td> "
								+ ((so.getSellDate() == null) ? "" : so.getSellDate()) + "</td></tr>";
						orderDetail += "<tr><td> Email Id </td><td> "
								+ ((so.getEmailId() == null) ? "" : so.getEmailId()) + "</td></tr>";

						orderDetail += "<tr><td> Phone No </td><td> " + ((so.getPhone() == null) ? "" : so.getPhone())
								+ "</td></tr>";
						orderDetail += "<tr><td> Bank Details </td><td> "
								+ ((so.getBankName() == null) ? "" : so.getBankName()) + " "
								+ ((so.getCity() == null) ? "" : so.getCity()) + " "
								+ ((so.getCountry() == null) ? "" : so.getCountry()) + "</td></tr>";
						orderDetail += "<tr><td> Card Details </td><td> "
								+ ((so.getCardType() == null) ? "" : so.getCardType()) + " "
								+ ((so.getCvv() == 0) ? "" : so.getCvv()) + "</td></tr>";
						orderDetail += "<tr><td> Local Currency </td><td> "
								+ ((so.getLocalCurrency() == null) ? "" : so.getLocalCurrency()) + "</td></tr>";
						orderDetail += "<tr><td> Sell Amount </td><td> "
								+ ((so.getSellAmount() == null) ? "" : so.getSellAmount()) + "</td></tr>";
						orderDetail += "<tr><td> Sell Currency </td><td> "
								+ ((so.getEcurrencyType() == null) ? "" : so.getEcurrencyType()) + "</td></tr>";
						orderDetail += "<tr><td> Received Amount </td><td> "
								+ ((so.getReceivedAmount() == null) ? "" : so.getReceivedAmount()) + "</td></tr>";
						if(so.getStatus().equalsIgnoreCase("PENDING"))
							orderDetail += "<tr><td>Status </td><td> <r>"
									+ ((so.getStatus() == null) ? "" : so.getStatus()) + "</r></td></tr></table>";
						else
							orderDetail += "<tr><td>Status </td><td> <g>"
										+ ((so.getStatus() == null) ? "" : so.getStatus()) + "</g></td></tr></table>";
					} else if (order.getBuyOrder() != null) {
						BuyOrder bo = order.getBuyOrder();
						orderDetail += "<table style='width:59%; margin-left:10%;color:#302692' class='table table-bordered table-condensed'><tr><td>Order Type </td><td> Buy-<g>"
								+ ((bo.getBuyId() == null) ? "" : bo.getBuyId()) + "</g></td></tr>";
						orderDetail += "<tr><td>Transaction Date </td><td> "
								+ ((bo.getBuyDate() == null) ? "" : bo.getBuyDate()) + "</td></tr>";
						orderDetail += "<tr><td> Email Id </td><td> "
								+ ((bo.getEmailId() == null) ? "" : bo.getEmailId()) + "</td></tr>";
						orderDetail += "<tr><td> Phone No </td><td> " + ((bo.getPhone() == null) ? "" : bo.getPhone())
								+ "</td></tr>";
						orderDetail += "<tr><td> Card Details </td><td> "
								+ ((bo.getCardType() == null) ? "" : bo.getCardType()) + " "
								+ ((bo.getCvv() == 0) ? "" : bo.getCvv()) + "</td></tr>";
						orderDetail += "<tr><td> E-Wallet Id </td><td> "
								+ ((bo.getEwalletId() == null) ? "" : bo.getEwalletId()) + "</td></tr>";
						orderDetail += "<tr><td> Local Currency </td><td> "
								+ ((bo.getLocalCurrency() == null) ? "" : bo.getLocalCurrency()) + "</td></tr>";
						orderDetail += "<tr><td> Buy Amount </td><td> "
								+ ((bo.getBuyAmount() == null) ? "" : bo.getBuyAmount()) + "</td></tr>";
						orderDetail += "<tr><td> Buy Currency </td><td> "
								+ ((bo.getEcurrencyType() == null) ? "" : bo.getEcurrencyType()) + "</td></tr>";
						orderDetail += "<tr><td> Paid Amount </td><td> "
								+ ((bo.getPaidAmount() == null) ? "" : bo.getPaidAmount()) + "</td></tr>";
						if(bo.getStatus().equalsIgnoreCase("PENDING"))
							orderDetail += "<tr><td>Status </td><td> <r>"
									+ ((bo.getStatus() == null) ? "" : bo.getStatus()) + "</r></td></tr></table>";
							else
							orderDetail += "<tr><td>Status </td><td> <g>"
										+ ((bo.getStatus() == null) ? "" : bo.getStatus()) + "</g></td></tr></table>";
					}
					model.addAttribute("orderDetail", orderDetail);
				} else {
					session
						.setAttribute("msg", "<r style='margin-left:10%;'>No order found with this order number</r>");
					model.addAttribute("ExchangeOrder", null);
					model.addAttribute("SellOrder", null);
					model.addAttribute("BuyOrder", null);

				}
			}
			return "traceorder";
		} catch (Exception e) {
			e.printStackTrace();
			return FAILURE;
		}
	}

	@RequestMapping(value = "/aboutus.htm", method = RequestMethod.GET)
	public String aboutUs(Model model, HttpSession session, HttpServletRequest request) {

		return "aboutus";
	}

	@RequestMapping(value = "/contactus.htm", method = RequestMethod.GET)
	public String contactUs(Model model, HttpSession session, HttpServletRequest request) {

		ContactUs contactUs = new ContactUs();
		model.addAttribute("ContactUs", contactUs);

		return "contactus";
	}

	@RequestMapping(value = "/termofservice.htm", method = RequestMethod.GET)
	public String termofservices(Model model, HttpSession session, HttpServletRequest request) {

		return "termofservice";
	}

	@RequestMapping(value = "/exchange.htm", method = RequestMethod.GET)
	public String exchange(Model model, HttpSession session, HttpServletRequest request) {

		String from = (String) request.getAttribute("from");
		String to = (String) request.getAttribute("from");
		String country = (String) request.getAttribute("country");
		String returnPage = "/";
		if(from == null || to == null || from.isEmpty() || to.isEmpty()) {
			return returnPage;
		}
		
		if(from.equalsIgnoreCase("INR") && to.equalsIgnoreCase(BITCOIN)) {
			returnPage = "bitcoinimps";	
		}
		
		if(from.equalsIgnoreCase("GBP") && to.equalsIgnoreCase(BITCOIN)) {
			returnPage = "bitcoinbankuk";	
		}
		
		if(from.equalsIgnoreCase("EUR") && to.equalsIgnoreCase(BITCOIN)) {
			returnPage = "bitcoinbankeurope";	
		}
		
		if(from.equalsIgnoreCase("USD") && to.equalsIgnoreCase(BITCOIN)) {
			returnPage = "bitcoinbankusa";	
		}
		
		if(from.equalsIgnoreCase("INR") && to.equalsIgnoreCase(ETHER)) {
			returnPage = "bitcoinimps";	
		}
		
		if(from.equalsIgnoreCase("GBP") && to.equalsIgnoreCase(ETHER)) {
			returnPage = "bitcoinbankuk";	
		}
		
		if(from.equalsIgnoreCase("EUR") && to.equalsIgnoreCase(ETHER)) {
			returnPage = "bitcoinbankeurope";	
		}
		
		if(from.equalsIgnoreCase("USD") && to.equalsIgnoreCase(ETHER)) {
			returnPage = "bitcoinbankusa";	
		}
		
		
		if(from.equalsIgnoreCase("INR") && to.equalsIgnoreCase(RIPPLE)) {
			returnPage = "bitcoinimps";	
		}
		
		if(from.equalsIgnoreCase("GBP") && to.equalsIgnoreCase(RIPPLE)) {
			returnPage = "bitcoinbankuk";	
		}
		
		if(from.equalsIgnoreCase("EUR") && to.equalsIgnoreCase(RIPPLE)) {
			returnPage = "bitcoinbankeurope";	
		}
		
		if(from.equalsIgnoreCase("USD") && to.equalsIgnoreCase(RIPPLE)) {
			returnPage = "bitcoinbankusa";	
		}
		
		return returnPage;
	}

	@RequestMapping(value = "/sell.htm", method = RequestMethod.GET)
	public String sell(Model model, HttpSession session, HttpServletRequest request) {

		return "sell";
	}

	@RequestMapping(value = "/buy.htm", method = RequestMethod.GET)
	public String buy(Model model, HttpSession session, HttpServletRequest request) {

		return "buy";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/faq.htm", method = RequestMethod.GET)
	public String faq(Model model, HttpSession session, HttpServletRequest request) {
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("status", "ACTIVE");
		List<SEOData> lstSEOData = genericService.getOrderedList(SEOData.class, valueMap, "createdDate", "desc");
		session.setAttribute("lstSEOData", lstSEOData);
		return "faq";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/recentsearches.htm", method = RequestMethod.GET)
	public String RecentSearches(Model model, HttpSession session, HttpServletRequest request) {
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("status", "ACTIVE");
		List<SEOData> lstSEOData = genericService.getOrderedList(SEOData.class, valueMap, "createdDate", "desc");
		session.setAttribute("lstSEOData", lstSEOData);
		return "recentsearches";
	}

	@RequestMapping(value = "/verifyotp.htm", method = RequestMethod.POST)
	public @ResponseBody String verifyOTP(HttpSession session, HttpServletRequest request) {

		try {

			String orderNumber = request.getParameter("ordernumber");
			String orderType = request.getParameter("ordertype");
			String verifyCode = request.getParameter("verifycode");

			if (orderType.equals("E")) {
				ExchangeOrder exchangeOrder = (ExchangeOrder)session.getAttribute(orderNumber);
				if (exchangeOrder.getVerifyCode().equalsIgnoreCase(verifyCode))
					return SUCCESS;
				else
					return FAILURE;
			}

			if (orderType.equals("S")) {
				SellOrder sellOrder = (SellOrder)session.getAttribute(orderNumber);
				if (sellOrder.getVerifyCode().equalsIgnoreCase(verifyCode))
					return SUCCESS;
				else
					return FAILURE;
			}

			if (orderType.equals("B")) {
				BuyOrder buyOrder = (BuyOrder)session.getAttribute(orderNumber);
				if (buyOrder.getVerifyCode().equalsIgnoreCase(verifyCode))
					return SUCCESS;
				else
					return FAILURE;
			}

			return FAILURE;
		} catch (Exception e) {
			e.printStackTrace();
			return FAILURE;
		}
	}

	@SuppressWarnings("static-access")
	@RequestMapping(value = "/buysuccess.htm", method = RequestMethod.POST)
	public @ResponseBody String buySuccess(@ModelAttribute("BuyOrder") BuyOrder bOrder, Model model,
			HttpSession session, HttpServletRequest request) {

		String orderNumber = request.getParameter("orderNumber");
		if (orderNumber == null) {
			return home(model, session, request);
		}

		BuyOrder buyOrder = (BuyOrder)session.getAttribute(orderNumber);

		if (buyOrder == null) {
			request.setAttribute("order", orderNumber);
			return postTraceOrder(model, session, request);
		}
		buyOrder.setBuyId(orderNumber);
		buyOrder.setBuyDate(new Date());
		buyOrder.setStatus("PENDING");
		boolean result = genericService.submit(buyOrder);
		session.removeAttribute(orderNumber);

		if (result) {

			String adminEmail = "support@" + SITE_NAME;
			String clientEmail = buyOrder.getEmailId();

			String adminPhone = applicationDataRepository.adminParamMap.get("adminphonenumber");
			String clientPhone = buyOrder.getPhone();

			String adminEmailSubject = "Buy order #[" + buyOrder.getBuyId() + "] from " + buyOrder.getLocalCurrency()
					+ " to " + buyOrder.getEcurrencyType() + " amount " + buyOrder.getBuyAmount();
			String clientEmailSubject = "Buy order #[" + buyOrder.getBuyId() + "] submitted successfull on "
					+ SITE_NAME + "";

			String adminMessage = "Hi Admin ," + "<br>" + "I have submitted a new buy order on " + SITE_NAME
					+ ".Please find the details below :-<br>" + "Order Number: " + buyOrder.getBuyId()
					+ "<br> Order amount: " + buyOrder.getBuyAmount() + "<br> Ecurreny Type: "
					+ buyOrder.getEcurrencyType() + "<br>Local Currency:" + buyOrder.getLocalCurrency()
					+ "<br> Ewallet :" + buyOrder.getEwalletId() + "<br> Paid Amount :" + buyOrder.getPaidAmount()
					+ "<br> Payment Method :" + buyOrder.getPaymentMethod() +"<br> Phone " + clientPhone +"<br> "+ Constants.httpsSiteName+"/service/updateorder/buy/"+ orderNumber+"/COMPLETED"
					+ "<br>Email id "+ clientEmail +"<br>  Thank You";

			String clientMessage = "Hi " + "," + "<br>"
					+ "Congratulations!!! You have succefully submitted buy order  on "
					+ SITE_NAME
					+ "<br>"
					+ "Order Number: "
					+ buyOrder.getBuyId()
					+ "<br>"
					+ "Order Amount: "
					+ buyOrder.getBuyAmount()
					+ "<br>"
					+ "Order Date: "
					+ buyOrder.getBuyDate()
					+ "<br>"
					+ "Sold Ecurrency: "
					+ buyOrder.getEcurrencyType()
					+ "<br>Local Currency:"
					+ buyOrder.getLocalCurrency()
					+ "<br> Ewallet :"
					+ buyOrder.getEwalletId()
					+ "<br> Paid Amount :"
					+ buyOrder.getPaidAmount()
					+ "<br> Payment Method :"
					+ buyOrder.getPaymentMethod()
					+ "<br> Order Status:Pending"
					+ "<br>"
					+ "Our account details will be shared soon in another email. Your order will be completed once "
					+ "your payment is confirm.You can trace your order at www."
					+ SITE_NAME
					+ "/traceorder.htm?order="
					+ buyOrder.getBuyId()
					+ "<br> if u face any problem please contact support."
					+ "<br>"
					+ "Thanks"
					+ "<br>" + "Support " + CONSTANTS.siteTag;

			String adminSMS = "New Buy order " + buyOrder.getBuyId() + " from " + buyOrder.getBuyAmount()+ " "+ buyOrder.getLocalCurrency() + " to "
					+ buyOrder.getPaidAmount() +" "+ buyOrder.getEcurrencyType() + " "+ buyOrder.getEwalletId()+" by "
					+ buyOrder.getEmailId() + " Phone " + buyOrder.getPhone();
			String clientSMS = "Please send WU or Moneygram to Name KALA SINGH City Delhi Country India for your Buy Order: "
					+ buyOrder.getBuyId();
			// Send Email to Admin.
			mailService.sendMail(adminEmailSubject, adminEmail, adminMessage, SITE_NAME);

			// Send Email to Client.
			mailService.sendMail(clientEmailSubject, clientEmail, clientMessage, SITE_NAME);

			// Send SMS to Admin.
			messagingService.sendSMS(adminPhone, adminSMS);

			// Send SMS to Client only if the payment mode is bank transfer or
			// western union
			if (buyOrder.getPaymentMethod().equals("Bank Transfer")
					|| buyOrder.getPaymentMethod().equals("Western Union")
					|| buyOrder.getPaymentMethod().equals("MoneyGram")) {
				
				if (buyOrder.getPaymentMethod().equals("Bank Transfer"))
				{clientSMS = "Please check your email for our bank details for your Buy Order: "
						+ buyOrder.getBuyId();
				}

				if (clientPhone != null && clientPhone.trim().length() > 5)
					{
					clientPhone = clientPhone.contains("+") ? clientPhone : "+" + clientPhone;
					messagingService.sendSMS(clientPhone, clientSMS);
					}
			}

			model.addAttribute("BuyOrder", buyOrder);
			model.addAttribute("msg", clientSMS);
			

			return SUCCESS;
		} else {
			session
				.setAttribute("resultMessage",
					"Error occured while submitting order Please try again or if problem persist then submit a support Order");
			return "Error occured while submitting order Please try again or if problem persist then submit a support Order";
		}
	}

	@SuppressWarnings("static-access")
	@RequestMapping(value = "/sellsuccess.htm", method = RequestMethod.POST)
	public @ResponseBody String sellSuccess(@ModelAttribute("SellOrder") SellOrder sOrder, Model model,
			HttpSession session, HttpServletRequest request) {
		String orderNumber = request.getParameter("orderNumber");
		if (orderNumber == null) {
			return home(model, session, request);
		}
		SellOrder sellOrder = (SellOrder)session.getAttribute(orderNumber);
		if (sellOrder == null) {
			request.setAttribute("order", orderNumber);
			return postTraceOrder(model, session, request);
		}
		// added
		String cardNo = "";
		if (sellOrder.getLocalCurrency().equals("Amazon Gift Card")
				|| sellOrder.getLocalCurrency().equals("Ebay Gift Card")
				|| sellOrder.getLocalCurrency().equals("Paysafecard")) {
			cardNo = sellOrder.getCardcode();
			sellOrder.setEcurrencyType("GIFTCARD");
		}
		sellOrder.setSellId(orderNumber);
		sellOrder.setStatus("PENDING");
		//
		boolean result = genericService.submit(sellOrder);
		session.removeAttribute(orderNumber);
		if (result) {
			String adminEmail = "support@" + SITE_NAME;
			String clientEmail = sellOrder.getEmailId();
			String adminPhone = applicationDataRepository.adminParamMap.get("adminphonenumber");
			String clientPhone = sellOrder.getPhone();
			String adminEmailSubject = "Sell order #[" + sellOrder.getSellId() + "] from "
					+ sellOrder.getEcurrencyType() + " to " + sellOrder.getLocalCurrency() + " amount "
					+ sellOrder.getSellAmount();
			String clientEmailSubject = "Sell order #[" + sellOrder.getSellId() + "] submitted successfull on "
					+ SITE_NAME + "";

			String adminEcurrencyAccount = applicationDataRepository.adminEcurrencyAccountIdMap.get(sellOrder
				.getEcurrencyType());

			String adminMessage = "Hi Admin ," + "<br>" + "I have submitted a new sell order on " + SITE_NAME
					+ ".Please find the details below :-<br>" + "Order Number: " + sellOrder.getSellId()
					+ "<br> Order amount: " + sellOrder.getSellAmount() + "<br> Ecurreny Type: "
					+ sellOrder.getEcurrencyType() + "<br>Received Amount: " + sellOrder.getReceivedAmount()
					+ "<br>Local Currency:" + sellOrder.getLocalCurrency() + "<br>Receive Through: "
					+ sellOrder.getPaymentMethod();

			if (sellOrder.getPaymentMethod().equals("Bank Transfer")) {
				adminMessage += "<br>Receiver Name: " + sellOrder.getAccountHolderName() + "<br>Bank Name: "
						+ sellOrder.getBankName() + "<br>Account Number: " + sellOrder.getAccountNumber()
						+ "<br>IFSC Code: " + sellOrder.getIfscCode() + "<br>SWIFT/IBn Code: "
						+ sellOrder.getSwiftCode() + "<br>Country: " + sellOrder.getCountry();
			} else if(sellOrder.getPaymentMethod().equals("Western Union") || sellOrder.getPaymentMethod().equals("MoneyGram"))
				adminMessage += "<br>Receiver Name: " + sellOrder.getAccountHolderName() + "<br>City: "
						+ sellOrder.getCity() + "<br>Country: " + sellOrder.getCountry();

			adminMessage += "<br>Phone " + clientPhone +"<br> "+ Constants.httpsSiteName+"/service/updateorder/sell/"+ orderNumber+"/COMPLETED" +  "<br>Email id "+ clientEmail +"<br>  Thank You";;

			String clientMessage = "Hi," + "<br>" + "Congratulations!!! You have succefully submitted sell order  on "
					+ SITE_NAME + "<br>" + "Order Number: " + sellOrder.getSellId() + "<br>" + "Order Amount: "
					+ sellOrder.getSellAmount() + "<br>" + "Order Date: " + sellOrder.getSellDate() + "<br>"
					+ "Sold Ecurrency: " + sellOrder.getEcurrencyType() + "<br>Local Currency:"
					+ sellOrder.getLocalCurrency() + "<br>Received Amount: " + sellOrder.getReceivedAmount();

			if (sellOrder.getPaymentMethod().equals("Bank Transfer")) {
				clientMessage += "<br>Receiver Name: " + sellOrder.getAccountHolderName() + "<br>Bank Name: "
						+ sellOrder.getBankName() + "<br>Account Number: " + sellOrder.getAccountNumber()
						+ "<br>IFSC Code: " + sellOrder.getIfscCode() + "<br>SWIFT/IBn Code: "
						+ sellOrder.getSwiftCode() + "<br>Country: " + sellOrder.getCountry();
			} else if(sellOrder.getPaymentMethod().equals("Western Union") || sellOrder.getPaymentMethod().equals("MoneyGram"))
				clientMessage += "<br>Receiver Name: " + sellOrder.getAccountHolderName() + "<br>City: "
						+ sellOrder.getCity() + "<br>Country: " + sellOrder.getCountry();

			clientMessage += "<br>Order Status:Pending" + "<br>Please send  " + sellOrder.getEcurrencyType()
					+ " to our account " + adminEcurrencyAccount
					+ ".Your order will be completed once your payment is confirmed .You can trace your order at www."
					+ SITE_NAME + "/traceorder.htm?order=" + sellOrder.getSellId()
					+ "<br> if u face any problem please contact support." + "<br>" + "Thanks" + "<br>" + "Support "
					+ CONSTANTS.siteTag;

			String clientSMS = "Please send " + sellOrder.getSellAmount() + " " + sellOrder.getEcurrencyType() + " to "
					+ adminEcurrencyAccount + " for Sell Order " + orderNumber +" on "+SITE_NAME;
			String adminSMS = "New Sell order " + sellOrder.getSellId() + " from " + sellOrder.getSellAmount()+ " "+ sellOrder.getEcurrencyType()
					+ "to " + sellOrder.getReceivedAmount()+ " "+sellOrder.getLocalCurrency() +" method "+ sellOrder.getPaymentMethod()+ " from email " + clientEmail + " phone " + clientPhone;

			// Send Email to Admin
			mailService.sendMail(adminEmailSubject, adminEmail, adminMessage, SITE_NAME);
			if (cardNo != "")
				mailService.sendMail(sellOrder.getLocalCurrency() + " Code", adminEmail, "The card code of "
						+ sellOrder.getLocalCurrency() + " is <b>" + cardNo + "</b>", SITE_NAME);
			// Send Email to Client.
			mailService.sendMail(clientEmailSubject, clientEmail, clientMessage, SITE_NAME);

			// Send SMS to Admin.
			messagingService.sendSMS(adminPhone, adminSMS);

			// Send SMS to Client.
			if (clientPhone != null && clientPhone.trim().length() > 5) {
				clientPhone = clientPhone.contains("+") ? clientPhone : "+" + clientPhone;
				messagingService.sendSMS(clientPhone, clientSMS);
			}

			sellOrder.setAdminAccount(adminEcurrencyAccount);
			model.addAttribute("SellOrder", sellOrder);
			model.addAttribute("msg", clientSMS);		

			return SUCCESS;
		} else {
			session
				.setAttribute("resultMessage",
					"Error occured while submitting order Please try again or if problem persist then submit a support Order");
			return "Error occured while submitting order Please try again or if problem persist then submit a support Order";
		}
	}

	@SuppressWarnings({ "static-access" })
	@RequestMapping(value = "/exchangesuccess.htm", method = RequestMethod.POST)
	public @ResponseBody String exchangeSuccess(@ModelAttribute("ExchangeOrder") ExchangeOrder eOrder, Model model,
			HttpSession session, HttpServletRequest request) {

		String orderNumber = request.getParameter("orderNumber");

		if (orderNumber == null) {
			return home(model, session, request);
		}
		ExchangeOrder exchangeOrder = (ExchangeOrder)session.getAttribute(orderNumber);

		String cardNo = "";
		exchangeOrder.setEcurrencyType("");
		if (exchangeOrder.getSentEcurrency().equals("Amazon Gift Card")
				|| exchangeOrder.getSentEcurrency().equals("Ebay Gift Card")
				|| exchangeOrder.getSentEcurrency().equals("Paysafecard")) {
			cardNo = exchangeOrder.getCardcode();
			exchangeOrder.setEcurrencyType("GIFTCARD");
		}
		/* added */
		exchangeOrder.setAdminAccount(applicationDataRepository.adminEcurrencyAccountIdMap.get(exchangeOrder
			.getSentEcurrency()));
		exchangeOrder.setExchangeId(orderNumber);
		exchangeOrder.setExchangeDate(new Date());
		exchangeOrder.setStatus("PENDING");
		if (exchangeOrder.getVerifyCode() == null)
			//
			if (exchangeOrder == null) {
				request.setAttribute("order", orderNumber);
				return postTraceOrder(model, session, request);
			}

		boolean submitExchangeOrder = genericService.submit(exchangeOrder);
		session.removeAttribute(orderNumber);

		if (exchangeOrder.getEcurrencyType().equals("GIFTCARD")) {
			GiftCard giftCard = new GiftCard();
			giftCard.setOrderId(orderNumber);
			giftCard.setType("GIFTCARD");
			giftCard.setStatus("ACTIVE");
			giftCard.setVoucher(exchangeOrder.getSentEcurrency());
			genericService.submit(giftCard);
		}

		if (submitExchangeOrder) {
			String adminEmail = "support@" + SITE_NAME;
			String clientEmail = exchangeOrder.getEmailId();

			String adminPhone = applicationDataRepository.adminParamMap.get("adminphonenumber");
			String clientPhone = exchangeOrder.getPhone();

			String adminEmailSubject = "Exchanger order #[" + exchangeOrder.getExchangeId() + "] from "
					+ exchangeOrder.getSentEcurrency() + " to " + exchangeOrder.getReceivedEcurrency() + " amount "
					+ exchangeOrder.getExchangeAmount();
			String clientEmailSubject = "Exchange order #[" + exchangeOrder.getExchangeId()
					+ "] submitted successfull on " + SITE_NAME + "";

			String amount = exchangeOrder.getExchangeAmount();
			/*
			 * Changed
			 */
			String adminEcurrencyAccount = applicationDataRepository.adminEcurrencyAccountIdMap.get(exchangeOrder
				.getSentEcurrency());

			String adminmessage = "Hi Admin ," + "<br>" + "I have submitted a new exchange order on " + SITE_NAME
					+ ".Please find the details below :-<br>" + "Order Number: " + orderNumber + "<br> Order amount: "
					+ amount + "<br>Sent Ecurrency: " + exchangeOrder.getSentEcurrency() + "<br> Receive Ecurrency: "
					+ exchangeOrder.getReceivedEcurrency() + "<br>Your Ewallet ID: " + exchangeOrder.getEwalletId()
					+ "<br>Received Amount: " + exchangeOrder.getReceivedAmount() +  "<br>Phone: " + clientPhone +"<br> "+ Constants.httpsSiteName+"/service/updateorder/exchange/"+ orderNumber+"/COMPLETED" + "<br>Email id "
					+ clientEmail+"<br>  Thank You";
			String clientMessage = "Hi," + "<br>"
					+ "Congratulations!!! You have succefully submitted exchange order  on "
					+ SITE_NAME
					+ ""
					+ "<br>"
					+ "Order Number: "
					+ orderNumber
					+ "<br>"
					+ "Order Amount: "
					+ amount
					+ "<br>Order Date: "
					+ exchangeOrder.getExchangeDate()
					+ "<br>Sent Ecurrency: "
					+ exchangeOrder.getSentEcurrency()
					+ "<br>"
					+ "Receive Ecurrency: "
					+ exchangeOrder.getReceivedEcurrency()
					+ "<br>Your Ewallet ID: "
					+ exchangeOrder.getEwalletId()
					+ "<br>Received Amount: "
					+ exchangeOrder.getReceivedAmount()
					+ "<br> Order Status:Pending"
					+ "<br>Please send "
					+ exchangeOrder.getSentEcurrency()
					+ " to our account  <b>"
					+ adminEcurrencyAccount
					+ "</b>.Your order will be completed once your payment is confirmed. You can trace your order at www."
					+ SITE_NAME
					+ "/traceorder.htm?order="
					+ exchangeOrder.getExchangeId()
					+ ".<br> if u face any problem please contact support."
					+ "<br>"
					+ "Thanks"
					+ "<br>"
					+ "Support "
					+ CONSTANTS.siteTag;

			String clientSMS = "Please send " + amount + " " + exchangeOrder.getSentEcurrency() + " to "
					+ adminEcurrencyAccount + " for Exchange Order " + orderNumber + " on " + SITE_NAME;
			String adminSMS = "New Exchanger order " + exchangeOrder.getExchangeId() + " from "					
					+ exchangeOrder.getExchangeAmount() + " "+exchangeOrder.getSentEcurrency()+ ", to  "+exchangeOrder.getReceivedAmount() +" "+exchangeOrder.getReceivedEcurrency()+ " "+ exchangeOrder.getEwalletId()+ " with email " + clientEmail + " phone " + clientPhone;

			// Send Email to Admin
			mailService.sendMail(adminEmailSubject, adminEmail, adminmessage, SITE_NAME);
			// Email card code in case of Amazon, Ebay and other card
			if (cardNo != "")
				mailService.sendMail(exchangeOrder.getSentEcurrency() + " Code", clientEmail, "The card code of "
						+ exchangeOrder.getSentEcurrency() + " is <b>" + cardNo + "</b>", SITE_NAME);

			// Send Email to Client.
			mailService.sendMail(clientEmailSubject, clientEmail, clientMessage, SITE_NAME);

			// Send SMS to Admin
			messagingService.sendSMS(adminPhone, adminSMS);
			// Send SMS to Client.
			if (clientPhone != null && clientPhone.trim().length() > 5)
				{
				clientPhone = clientPhone.contains("+") ? clientPhone : "+" + clientPhone;
				messagingService.sendSMS(clientPhone, clientSMS);
				}

			exchangeOrder.setAdminAccount(adminEcurrencyAccount);
			model.addAttribute("ExchangeOrder", exchangeOrder);
			model.addAttribute("msg", clientMessage);			

			return SUCCESS;
		} else {
			session
				.setAttribute("resultMessage",
					"Error occured while submitting order Please try again or if problem persist then submit a support Order");
			return "Error occured while submitting order Please try again or if problem persist then submit a support Order";
		}
	}

	@RequestMapping(value = "/exchangegiftcard.htm", method = RequestMethod.GET)
	public String getGiftCard(Model model, HttpSession session, HttpServletRequest request) {

		ExchangeOrder exchangeOrder = new ExchangeOrder();
		model.addAttribute("ExchangeOrder", exchangeOrder);

		return "exchangegiftcard";
	}

	@RequestMapping(value = "/buysuccess.htm", method = RequestMethod.GET)
	public String getBuySuccess(Model model, HttpSession session, HttpServletRequest request) {

		String orderNumber = request.getParameter("orderNumber");

		if (orderNumber == null) {
			return buy(model, session, request);
		} else {
			request.setAttribute("order", orderNumber);
			return postTraceOrder(model, session, request);
		}
	}

	@RequestMapping(value = "/sellsuccess.htm", method = RequestMethod.GET)
	public String getSellSuccess(Model model, HttpSession session, HttpServletRequest request) {

		String orderNumber = request.getParameter("orderNumber");

		if (orderNumber == null) {
			return sell(model, session, request);
		} else {
			request.setAttribute("order", orderNumber);
			return postTraceOrder(model, session, request);
		}
	}

	@RequestMapping(value = "/exchangesuccess.htm", method = RequestMethod.GET)
	public String getExchangeSuccess(Model model, HttpSession session, HttpServletRequest request) {

		String orderNumber = request.getParameter("orderNumber");

		if (orderNumber == null) {
			return exchange(model, session, request);
		} else {
			request.setAttribute("order", orderNumber);
			return postTraceOrder(model, session, request);
		}
	}

	@RequestMapping(value = "/news.htm", method = RequestMethod.GET)
	public String news(Model model, HttpSession session, HttpServletRequest request) {

		return "news";
	}

	@RequestMapping(value = "/contactus.htm", method = RequestMethod.POST)
	public @ResponseBody String ContactUs(@RequestBody String json, HttpSession session, HttpServletRequest request,
			Model model) {
		Gson gson = new Gson();
		ContactUs contactUs = gson.fromJson(json, ContactUs.class);
		String name = contactUs.getName();
		String adminEmail = "support@" + SITE_NAME;
		String email = contactUs.getEmailId();
		String message = contactUs.getMessage();
		String subject = contactUs.getSubject();
		boolean mailSent = false;

		// Validate Email of client.
		if (!validationService.validateEmail(email)) {
			return "invalidmail";
		}

		if (!GoogleRobotVerification.isHuman(request.getParameter("g-recaptcha-response"), request.getRemoteAddr())) {
			return "GoogleCaptchaVerificationFailed";
		}

		// Validating Mail Parameters.
		Validator validator = new Validator();
		boolean flag = validator.mailFieldValidator(SITE_NAME, email, message, subject);

		if (flag) {

			String adminMessage = "Hi Admin ," + "<br>" + message + "<br><br>" + "Thanks" + "<br>" + name + "<br>"
					+ email;

			// Send Email to Admin.
			mailSent = mailService.sendMail(subject, adminEmail, adminMessage, SITE_NAME);

			if (mailSent)
				logger.info("mail sent to admin");

			subject = "Response from " + SITE_NAME;
			message = "Hi " + name + "," + "<br>" + "Please do not reply. This is automated mail to your query:"
					+ "<br>" + message + "<br><br>" + "we will contact you soon " + "<br>" + "Thanks" + "<br>"
					+ CONSTANTS.siteTag + " Admin";

			// Send Email to Client.
			mailSent = mailService.sendMail(subject, email, message, SITE_NAME);

			if (mailSent) {
				logger.info("mail sent to client");
				message = "success";
				logger.info("HomeController->mail sent to both admin and client");
			} else {
				message = "failure";
				logger.error("HomeController->some error occurred to sent mail");
			}
		} else {
			message = "wrongfields";
			logger.error("HomeController->mail parameters are not correct");
		}

		// session.setAttribute("resultMessage", message);

		return message;
	}

	@RequestMapping(value = "/testimonial.htm", method = RequestMethod.GET)
	public String getTestimonial(Model model, HttpSession session, HttpServletRequest request) {

		if (session.getAttribute("user") != null) {

			Testimonial testimonial = new Testimonial();
			model.addAttribute("Testimonial", testimonial);
			return "testimonial";
		} else {
			LoginBean loginBean = new LoginBean();
			model.addAttribute("LoginBean", loginBean);

			return "login";
		}
	}

	@RequestMapping(value = "/testimonial.htm", method = RequestMethod.POST)
	public String postTestimonial(@ModelAttribute("Testimonial") Testimonial testimonial, HttpSession session,
			HttpServletRequest request, Model model) {

		String name = testimonial.getName();
		String adminEmail = "support@" + SITE_NAME;
		String email = testimonial.getEmail();
		String message = testimonial.getMessage();
		String subject = "New Testimonial submited";
		boolean result = false;

		/*
		 * if (!GoogleRobotVerification.isHuman(testimonial.getGrecaptcharesponse(), request.getRemoteAddr())) { return
		 * "GoogleCaptchaVerificationFailed"; }
		 */

		testimonial.setGrecaptcharesponse(null);

		// Validate Email of client.
		if (!validationService.validateEmail(email)) {
			model.addAttribute("errorMessage", "Invalid Email");
			return "addtestimonial";
		}
		System.out.println(testimonial.getMessage());
		// Validating Mail parameters.
		Validator validator = new Validator();
		boolean flag = validator.mailFieldValidator(SITE_NAME, email, message, subject);

		if (flag) {
			testimonial.setCreatedDate(new Timestamp((new Date()).getTime()));
			boolean isSaved = genericService.submit(testimonial);
			if (isSaved) {
				String adminMessage = "Hi Admin ," + "<br>" + message + "<br><br>" + "Thanks" + "<br>" + name + "<br>"
						+ email;

				// Send Email to Admin.
				result = mailService.sendMail(subject, adminEmail, adminMessage, SITE_NAME);

				if (result) {
					logger.info("mail sent to admin");
					message = "<g>Your testimonail has been sent successfully</g>";
					logger.info("HomeController->mail sent to both admin ");
				} else {
					message = "<r>Testimonial sending failed.Please try again or try after some time</r>";
					logger.error("HomeController->some error occurred to sent mail");
				}
			} else {
				message = "<r>Testimonial saving failed. Please try again or try after some time</r>";
				logger.error("HomeController->some error occurred during saving mail");
			}
		} else {
			message = "<r>Testimonail fileds are not correct.Please go back and correct the fields</r>";
			logger.error("HomeController->mail parameters are not correct");
		}

		session.setAttribute("msg1", message);

		return "addtestimonial";
	}

	@RequestMapping(value = "/paymentfailure.htm", method = RequestMethod.POST)
	public String paymentFailure(Model model, HttpSession session, HttpServletRequest request) {

		if (session.getAttribute("user") != null) {

			return "paymentfailure";
		} else {
			LoginBean loginBean = new LoginBean();
			model.addAttribute("LoginBean", loginBean);

			return "login";
		}
	}

	@RequestMapping(value = "/paymentfailure.htm", method = RequestMethod.GET)
	public String getPaymentFailure(Model model, HttpSession session, HttpServletRequest request) {

		if (session.getAttribute("user") != null) {

			return "paymentfailure";
		} else {
			LoginBean loginBean = new LoginBean();
			model.addAttribute("LoginBean", loginBean);

			return "login";
		}
	}

	@RequestMapping(value = "/paymentsuccess.htm", method = RequestMethod.POST)
	public String saveNewInvestment(Model model, HttpSession session, HttpServletRequest request) {

		if (session.getAttribute("user") != null) {

			return "paymentsuccess";
		} else {
			LoginBean loginBean = new LoginBean();
			model.addAttribute("LoginBean", loginBean);

			return "login";
		}
	}

	@RequestMapping(value = "/paymentsuccess.htm", method = RequestMethod.GET)
	public String getSuccessPayment(Model model, HttpSession session, HttpServletRequest request) {

		if (session.getAttribute("user") != null) {

			return "paymentsuccess";
		} else {
			LoginBean loginBean = new LoginBean();
			model.addAttribute("LoginBean", loginBean);

			return "login";
		}
	}

	@SuppressWarnings("static-access")
	@RequestMapping(value = "/createexchangeorder.htm", method = RequestMethod.POST)
	public @ResponseBody String createExchangeOrder(@RequestBody String json, Model model, HttpSession session,
			HttpServletRequest request) {

		Gson gson = new Gson();

		ExchangeOrder exchangeOrder = gson.fromJson(json, ExchangeOrder.class);

		if (!GoogleRobotVerification.isHuman(exchangeOrder.getGrecaptcharesponse(), request.getRemoteAddr())) {
			return "GoogleCaptchaVerificationFailed";
		}

		exchangeOrder.setGrecaptcharesponse(null);

		if (!validationService.validateEmail(exchangeOrder.getEmailId())) {
			return "InvalidEmail";
		}

		try {
			if (!exchangeOrder.getPhone().trim().equals(""))
				{String clientPhone = exchangeOrder.getPhone().contains("+") ? exchangeOrder.getPhone() : "+" + exchangeOrder.getPhone();
				exchangeOrder.setPhone(clientPhone);
				validationService.validatePhone(exchangeOrder.getPhone());
				}

			validationService.validateAccessControl(exchangeOrder.getEmailId(), exchangeOrder.getPhone(),
				request.getRemoteAddr());

		} catch (ExchangeException e) {
			e.printStackTrace();
			return e.getErrorMessage();
		}

		String orderId = generateOrderId();
		if (applicationDataRepository.servicesStatusMap.get("mailservice").equals("INACTIVE")
				&& applicationDataRepository.servicesStatusMap.get("messageservice").equals("INACTIVE")) {

			exchangeOrder.setExchangeId(orderId);
			exchangeOrder.setExchangeDate(new Date());
			exchangeOrder.setAdminAccount(applicationDataRepository.adminEcurrencyAccountIdMap.get(exchangeOrder
				.getSentEcurrency()));

			if (genericService.submit(exchangeOrder))
				return ORDER_SUCCESS + ":" + orderId;
			return ORDER_FAILED;
		}

		String OTP = generateOTP();

		boolean emailON = applicationDataRepository.servicesStatusMap.get("mailservice").equals("ACTIVE") ? true
				: false;
		boolean smsON = applicationDataRepository.servicesStatusMap.get("messageservice").equals("ACTIVE") ? true
				: false;

		boolean otpSent = sendOTP(emailON, exchangeOrder.getEmailId(), smsON, exchangeOrder.getPhone(), OTP,
			"Exchange Order", orderId);

		if (otpSent) {
			exchangeOrder.setVerifyCode(OTP);
			session.setAttribute(orderId, exchangeOrder);
			return OTP_SUCCESS + ":" + orderId;
		}

		return OTP_FAILED;
	}

	@SuppressWarnings("static-access")
	@RequestMapping(value = "/createsellorder.htm", method = RequestMethod.POST)
	public @ResponseBody String createSellOrder(@RequestBody String json, Model model, HttpSession session,
			HttpServletRequest request) throws ExchangeException {

		Gson gson = new Gson();
		SellOrder sellOrder = gson.fromJson(json, SellOrder.class);

		if (!GoogleRobotVerification.isHuman(sellOrder.getGrecaptcharesponse(), request.getRemoteAddr())) {
			return "GoogleCaptchaVerificationFailed";
		}

		sellOrder.setGrecaptcharesponse(null);

		if (!validationService.validateEmail(sellOrder.getEmailId())) {
			return "InvalidEmail";
		}

		try {
			if (!sellOrder.getPhone().trim().equals(""))
				{String clientPhone = sellOrder.getPhone().contains("+") ? sellOrder.getPhone() : "+" + sellOrder.getPhone();
				sellOrder.setPhone(clientPhone);
				validationService.validatePhone(sellOrder.getPhone());
				}

			validationService.validateAccessControl(sellOrder.getEmailId(), sellOrder.getPhone(),
				request.getRemoteAddr());

		} catch (ExchangeException e) {
			e.printStackTrace();
			return e.getErrorMessage();
		}

		String orderId = generateOrderId();

		if (applicationDataRepository.servicesStatusMap.get("mailservice").equals("INACTIVE")
				&& applicationDataRepository.servicesStatusMap.get("messageservice").equals("INACTIVE")) {

			sellOrder.setSellId(orderId);
			sellOrder.setSellDate(new Date());
			sellOrder.setAdminAccount(applicationDataRepository.adminEcurrencyAccountIdMap.get(sellOrder
				.getEcurrencyType()));

			if (genericService.submit(sellOrder))
				return ORDER_SUCCESS + ":" + orderId;
			return ORDER_FAILED;
		}

		String OTP = generateOTP();

		boolean emailON = applicationDataRepository.servicesStatusMap.get("mailservice").equals("ACTIVE") ? true
				: false;
		boolean smsON = applicationDataRepository.servicesStatusMap.get("messageservice").equals("ACTIVE") ? true
				: false;

		boolean otpSent = sendOTP(emailON, sellOrder.getEmailId(), smsON, sellOrder.getPhone(), OTP, "Sell Order",
			orderId);

		if (otpSent) {
			sellOrder.setVerifyCode(OTP);
			session.setAttribute(orderId, sellOrder);
			return OTP_SUCCESS + ":" + orderId;
		}

		return OTP_FAILED;
	}

	@SuppressWarnings("static-access")
	@RequestMapping(value = "/createbuyorder.htm", method = RequestMethod.POST)
	public @ResponseBody String createBuyOrder(@RequestBody String json, Model model, HttpSession session,
			HttpServletRequest request) throws ExchangeException {

		Gson gson = new Gson();
		BuyOrder buyOrder = gson.fromJson(json, BuyOrder.class);

		if (!GoogleRobotVerification.isHuman(buyOrder.getGrecaptcharesponse(), request.getRemoteAddr())) {
			return "GoogleCaptchaVerificationFailed";
		}
		buyOrder.setGrecaptcharesponse(null);

		if (!validationService.validateEmail(buyOrder.getEmailId())) {
			return "InvalidEmail";
		}

		try {
			if (!buyOrder.getPhone().trim().equals(""))
				{String clientPhone = buyOrder.getPhone().contains("+") ? buyOrder.getPhone() : "+" + buyOrder.getPhone();
				buyOrder.setPhone(clientPhone);
				validationService.validatePhone(buyOrder.getPhone());
				}

			validationService
				.validateAccessControl(buyOrder.getEmailId(), buyOrder.getPhone(), request.getRemoteAddr());

		} catch (ExchangeException e) {
			e.printStackTrace();
			return e.getErrorMessage();
		}

		String orderId = generateOrderId();

		if (applicationDataRepository.servicesStatusMap.get("mailservice").equals("INACTIVE")
				&& applicationDataRepository.servicesStatusMap.get("messageservice").equals("INACTIVE")) {

			buyOrder.setBuyId(orderId);
			buyOrder.setBuyDate(new Date());
			buyOrder.setAdminAccount(applicationDataRepository.adminEcurrencyAccountIdMap.get(buyOrder
				.getEcurrencyType()));

			if (genericService.submit(buyOrder))
				return ORDER_SUCCESS + ":" + orderId;
			return ORDER_FAILED;
		}

		String OTP = generateOTP();

		boolean emailON = applicationDataRepository.servicesStatusMap.get("mailservice").equals("ACTIVE") ? true
				: false;
		boolean smsON = applicationDataRepository.servicesStatusMap.get("messageservice").equals("ACTIVE") ? true
				: false;

		boolean otpSent = sendOTP(emailON, buyOrder.getEmailId(), smsON, buyOrder.getPhone(), OTP, "Buy Order", orderId);

		if (otpSent) {
			buyOrder.setVerifyCode(OTP);
			session.setAttribute(orderId, buyOrder);
			return OTP_SUCCESS + ":" + orderId;
		}

		return OTP_FAILED;
	}

	private String generateOrderId() {
		int length = 10;
		String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String orderId = "";

		for (int i = length; i > 0; --i) {
			orderId += chars.charAt((int)Math.round(Math.random() * (chars.length() - 1)));
		}
		orderId = orderId.toUpperCase();

		return orderId;
	}

	private String generateOTP() {

		return ((100000 + (int)(Math.random() * 900000)) + "");
	}

	private boolean sendOTP(boolean emailON, String emailRecipient, boolean smsON, String smsRecipient, String otp,
			String orderType, String orderId) {
		boolean emailSent = false, smsSent = false;

		String emailBody = "Dear client," + "<br>" + "The verification code for your " + orderType + " with order ID: "
				+ orderId + " is <b>" + otp + "</b><br>"
				+ "Please do not share this code with anyone. <br> Thanks <br>  www."
				+ com.exchangerpoint.exchangeservices.common.Constants.SITE_NAME;
		String emailSubject = "Verification code for " + orderType + " #[" + orderId + "]";
		String smsBody = "Your OTP " + otp + " for " + orderType + " " + orderId + " on "
				+ com.exchangerpoint.exchangeservices.common.Constants.SITE_NAME + " Please Do not share it";

		if (emailON)
			emailSent = mailService.sendMail(emailSubject, emailRecipient, emailBody,
				com.exchangerpoint.exchangeservices.common.Constants.SITE_NAME);

		if (!smsRecipient.isEmpty() && smsON)
			smsSent = messagingService.sendSMS(smsRecipient, smsBody);

		return (emailSent || smsSent);
	}

	public void setHomeService(HomeService homeService) {
		this.homeService = homeService;
	}

	public void setGenericService(GenericService genericService) {
		this.genericService = genericService;
	}

	public void setApplicationDataRepository(ApplicationDataRepository applicationDataRepository) {
		this.applicationDataRepository = applicationDataRepository;
	}

	public void setMessagingService(MessagingService messagingService) {
		this.messagingService = messagingService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public void setValidationService(ValidationService validationService) {
		this.validationService = validationService;
	}

	@RequestMapping(value = "/getexchangefees.htm", method = RequestMethod.POST)
	public @ResponseBody String getExchangeFees(HttpSession session, HttpServletRequest request) {
		try {
			String exgFrom = request.getParameter("from").replaceAll(" ", "").toLowerCase();
			String exgTo = request.getParameter("to").replaceAll(" ", "").toLowerCase();
			if (ApplicationDataRepository.exchangeRateMap.get(exgFrom + exgTo) != null)
				return SUCCESS + ":" + ApplicationDataRepository.exchangeRateMap.get(exgFrom + exgTo);
			return FAILURE;
		} catch (Exception e) {
			return FAILURE;
		}

	}

	@RequestMapping(value = "/addtestimonial.htm", method = RequestMethod.GET)
	public String openAddTestimonial(Model model, HttpSession session, HttpServletRequest request) {
		model.addAttribute("Testimonial", new Testimonial());
		return "addtestimonial";
	}

	@RequestMapping(value = "/reset.htm", method = RequestMethod.GET)
	public @ResponseBody String reset(HttpSession session, HttpServletRequest request) {
		try {
			applicationDataRepository.init();
			System.out.println("resetting the system");
			return SUCCESS;
		} catch (Exception e) {
			return FAILURE;
		}

	}

	@RequestMapping(value = "/shutdown.htm", method = RequestMethod.GET)
	public @ResponseBody String shutdown(HttpSession session, HttpServletRequest request) {
		try {
			int result = genericService.executeSQLUpdate("update ECURRENCY set STATUS_ID='Inactive'");
			applicationDataRepository.init();
			System.out.println("shutting down the system");
			if (result > 0)

				return SUCCESS;
			return FAILURE;
		} catch (Exception e) {
			return FAILURE;
		}

	}

	@RequestMapping(value = "/start.htm", method = RequestMethod.GET)
	public @ResponseBody String start(HttpSession session, HttpServletRequest request) {
		try {
			int result = genericService.executeSQLUpdate("update ECURRENCY set STATUS_ID='Active'");
			applicationDataRepository.init();
			System.out.println("starting  the system");
			if (result > 0)
				return SUCCESS;
			return FAILURE;
		} catch (Exception e) {
			return FAILURE;
		}

	}

	@RequestMapping(value = "/addscammer.htm", method = RequestMethod.GET)
	public String openAddScammer(Model model, HttpSession session, HttpServletRequest request) {
		model.addAttribute("Scammer", new Scammer());
		return "addscammer";
	}

	@RequestMapping(value = "/addscammer.htm", method = RequestMethod.POST)
	public String addScammer(@ModelAttribute("Scammer") Scammer scammer, Model model, HttpSession session,
			HttpServletRequest request) {

		try {
			if (!GoogleRobotVerification.isHuman(request.getParameter("g-recaptcha-response"), request.getRemoteAddr())) {
				return "GoogleCaptchaVerificationFailed";
			}
			String msg2 = "";
			if (!validationService.validateEmail(scammer.getEmail())) {
				msg2 = "<r>Please enter valid email address.</r>";
				model.addAttribute("error2", msg2);
				return "addscammer";
			}
			if (!validationService.validatePhone(scammer.getPhone())) {
				msg2 = "<r>Please enter valid email phone no.</r>";
				model.addAttribute("error2", msg2);
				return "addscammer";
			}
			if (genericService.submit(scammer)) {
				msg2 = "<g>Scammer added successfully..</g>";
				model.addAttribute("msg2", msg2);
				return "addscammer";
			}
			msg2 = "<r>Error while saving scammer. Please try again..</r>";
			model.addAttribute("error2", msg2);
			return "addscammer";
		} catch (Exception e) {
			return FAILURE;
		}

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/checkscammer.htm", method = RequestMethod.GET)
	public String opencheckscammer(Model model, HttpSession session, HttpServletRequest request) {
		model.addAttribute("Scammer", new Scammer());
		List<Scammer> lstScammer = genericService.getOrderedList(Scammer.class, "Name", "asc");
		int i = 0;
		String scammerList = "";
		for (Scammer obj : lstScammer) {
			scammerList += "<tr><td>" + (++i) + "</td><td>" + obj.getName() + "</td><td>" + obj.getEmail()
					+ "</td><td>" + obj.getPhone() + "</td><td>" + obj.getMessage() + "</td></tr>";
		}

		model.addAttribute("result", scammerList);

		return "checkscammer";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/checkscammer.htm", method = RequestMethod.POST)
	public String checkscammer(@ModelAttribute("Scammer") Scammer scammer, Model model, HttpSession session,
			HttpServletRequest request) {
		try {
			String msg3 = "";

			if (scammer.getEmail() != null && !scammer.getEmail().trim().equals("")
					&& !validationService.validateEmail(scammer.getEmail())) {
				msg3 = "<r>Please enter valid email address.</r>";
				model.addAttribute("err3", msg3);
				return "checkscammer";
			}
			if (scammer.getPhone() != null && !scammer.getPhone().trim().equals("")
					&& !validationService.validatePhone(scammer.getPhone())) {
				msg3 = "<r>Please enter valid email phone no.</r>";
				model.addAttribute("msg3", msg3);
				return "checkscammer";
			}
			List<Scammer> lstScammer = new ArrayList<Scammer>();
			if (scammer.getPhone().trim().equals("") && scammer.getEmail().trim().equals("")
					&& scammer.getName() != null && scammer.getName().trim().equals("")) {
				lstScammer = genericService.getOrderedList(Scammer.class, "Name", "asc");
			} else {
				HashMap<String, Object> valueMap = new HashMap<String, Object>();
				if (!scammer.getName().trim().equals(""))
					valueMap.put("Name", scammer.getName().trim());
				if (!scammer.getEmail().trim().equals(""))
					valueMap.put("Email", scammer.getEmail().trim());
				if (!scammer.getPhone().trim().equals(""))
					valueMap.put("Phone", scammer.getPhone().trim());
				if (valueMap.size() > 0) {
					lstScammer = genericService.getObjectsByEqualityConditions(Scammer.class, valueMap);
					if (lstScammer.size() == 0) {
						model.addAttribute("result1", "");
						return "checkscammer";
					}
				}
			}
			if (lstScammer.size() > 0) {
				int i = 0;
				String scammerList = "";
				for (Scammer obj : lstScammer) {
					scammerList += "<tr><td>" + (++i) + "</td><td>" + obj.getName() + "</td><td>" + obj.getEmail()
							+ "</td><td>" + obj.getPhone() + "</td><td>" + obj.getMessage() + "</td></tr>";
				}
				model.addAttribute("result", scammerList);
				return "checkscammer";

			}
			msg3 = "<r>Error while searching for scammer. Please try again..</r>";
			model.addAttribute("msg3", msg3);
			return "checkscammer";
		} catch (Exception e) {
			return FAILURE;
		}

	}
	

	@RequestMapping(value = "/mobile-recharge-with-bitcoin-in-india.htm", method = RequestMethod.GET)
	public String moibileRecharge(Model model, HttpSession session, HttpServletRequest request) {

		return "mobilerecharge";

	}
	
	
}
