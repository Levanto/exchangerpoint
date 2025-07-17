package com.exchangerpoint.exchangeservices.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Pattern;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.exchangerpoint.databaseservices.entity.AccessControl;
import com.exchangerpoint.exchangeservices.common.ApplicationDataRepository;
import com.exchangerpoint.exchangeservices.common.ErrorCodes;
import com.exchangerpoint.exchangeservices.exception.AccessControlValidationException;
import com.exchangerpoint.exchangeservices.exception.DateTimestampException;
import com.exchangerpoint.exchangeservices.exception.ExchangeException;
import com.exchangerpoint.exchangeservices.exception.UserBlockedException;
import com.exchangerpoint.exchangeservices.utility.DateTimestampUtil;
import com.exchangerpoint.genericservices.exception.GenericException;
import com.exchangerpoint.genericservices.service.GenericService;
import com.exchangerpoint.genericservices.util.PropertyConditionMatcher;

@Transactional
public class ValidationServiceImpl implements ValidationService {

	@Autowired
	private ApplicationDataRepository applicationDataRepository;

	@Autowired
	@Qualifier(com.exchangerpoint.genericservices.util.Constants.GENERIC_SERVICE)
	private GenericService genericService;

	public boolean validateEmail(String email) {

		String[] arr = new String[] {".com", ".net", ".org", ".biz", ".coop", ".info", ".museum", ".name", ".pro" , ".edu", ".gov", ".int", ".mil", ".ac", ".ad",
				".ae", ".af", ".ag", ".ai", ".al", ".am", ".an", ".ao", ".aq", ".ar", ".as", ".at", ".au", ".aw", ".az", ".ba", ".bb", ".bd", ".be", ".bf", 
				".bg", ".bh", ".bi", ".bj", ".bm", ".bn", ".bo", ".br", ".bs", ".bt", ".bv", ".bw", ".by", ".bz", ".ca", ".cc", ".cd", ".cf", ".cg", ".ch", 
				".ci", ".ck", ".cl", ".cm", ".cn", ".co", ".cr", ".cu", ".cv", ".cx", ".cy", ".cz", ".de", ".dj", ".dk", ".dm", ".do", ".dz", ".ec", ".ee", 
				".eg", ".eh", ".er", ".es", ".et", ".fi", ".fj", ".fk", ".fm", ".fo", ".fr", ".ga", ".gd", ".ge", ".gf", ".gg", ".gh", ".gi", ".gl", ".gm", 
				".gn", ".gp", ".gq", ".gr", ".gs", ".gt", ".gu", ".gv", ".gy", ".hk", ".hm", ".hn", ".hr", ".ht", ".hu", ".id", ".ie", ".il", ".im", ".in", 
				".io", ".iq", ".ir", ".is", ".it", ".je", ".jm", ".jo", ".jp", ".ke", ".kg", ".kh", ".ki", ".km", ".kn", ".kp", ".kr", ".kw", ".ky", ".kz", 
				".la", ".lb", ".lc", ".li", ".lk", ".lr", ".ls", ".lt", ".lu", ".lv", ".ly", ".ma", ".mc", ".md", ".mg", ".mh", ".mk", ".ml", ".mm", ".mn", 
				".mo", ".mp", ".mq", ".mr", ".ms", ".mt", ".mu", ".mv", ".mw", ".mx", ".my", ".mz", ".na", ".nc", ".ne", ".nf", ".ng", ".ni", ".nl", ".no", 
				".np", ".nr", ".nu", ".nz", ".om", ".pa", ".pe", ".pf", ".pg", ".ph", ".pk", ".pl", ".pm", ".pn", ".pr", ".ps", ".pt", ".pw", ".py", ".qa", 
				".re", ".ro", ".rw", ".ru", ".sa", ".sb", ".sc", ".sd", ".se", ".sg", ".sh", ".si", ".sj", ".sk", ".sl", ".sm", ".sn", ".so", ".sr", ".st", 
				".sv", ".sy", ".sz", ".tc", ".td", ".tf", ".tg", ".th", ".tj", ".tk", ".tm", ".tn", ".to", ".tp", ".tr", ".tt", ".tv", ".tw", ".tz", ".ua", 
				".ug", ".uk", ".um", ".us", ".uy", ".uz", ".va", ".vc", ".ve", ".vg", ".vi", ".vn", ".vu", ".ws", ".wf", ".ye", ".yt", ".yu", ".za", ".zm", 
				".zw",".aol"};

		String mai = email.toLowerCase();
		boolean val = true;

		int dot = mai.lastIndexOf(".");
		String ext = mai.substring(dot, mai.length());
		int at = mai.indexOf("@");
		
		if(dot > 5 && at > 1) {
			for (int i = 0; i < arr.length; i++) {
				if (ext.equals(arr[i])) {
					val = true;break;
				}
				else {
					val = false;
				}
			}

			if (val == false) {
				return false;
			}
		}
		else {
			return false;
		}
		return true;
	}

	public boolean validatePhone(String mobile) throws ExchangeException {
		
		if (mobile.contains("+"))
			mobile = mobile.replace("+", "");
		
		if (!validateNumber(mobile))
			throw new ExchangeException("Invalid Mobile Number. Only numbers allowed.", ErrorCodes.INVALID_NUMBER);
		
		if (mobile.length() < 10 || mobile.length() > 15)
			throw new ExchangeException("Invalid length of Mobile Number.", ErrorCodes.INVALID_MOBILE_LENGTH);
		
		return true;
	}

	public boolean validateAccessControl(String email, String mobile, String ipAddress) throws ExchangeException {

		@SuppressWarnings("static-access")
		List<AccessControl> accessControlList = applicationDataRepository.blockedUsersList;

		for (AccessControl accessControl: accessControlList) {
			if (accessControl.getIpAddress().equals(ipAddress) 
					|| accessControl.getEmail().equals(email) 
					|| accessControl.getPhone().equals(mobile)) {
				throw new UserBlockedException("User is blocked.", ErrorCodes.USER_BLOCKED);
			}
		}

		try {
			if (fetchRecordsForCurrentUser(ipAddress, email, mobile).size() >= 5) {
				boolean blocked = fullBlock(ipAddress, email, mobile);

				if (blocked)
					throw new UserBlockedException("User has been blocked due to malicious activities.", ErrorCodes.USER_BLOCKED);
			}
		} catch (GenericException e) {
			e.printStackTrace();
			throw new AccessControlValidationException(e.getMessage(), ErrorCodes.DATA_FETCH_ERROR);
		}
		return true;
	}
	
	private boolean validateNumber(String number) {
		String regex = "\\d+";
		Pattern pattern = Pattern.compile(regex);
				
		return pattern.matcher(number).matches();
	}

	@SuppressWarnings("rawtypes")
	private List fetchRecordsForCurrentUser(String ipAddress, String email, String phone) throws GenericException {
		
		Criteria criteria = genericService.createBlankCriteria(AccessControl.class);
		try {
			criteria = genericService.addORConditionToCriteria(criteria, prepareORConditionsList(ipAddress, email, phone));
			criteria.add(prepareBetweenCondition());
		} catch (GenericException e) {
			e.printStackTrace();
			throw new GenericException("Problem occured while looking for current user data in database.");
		}
		return genericService.executeCriteria(criteria);
	}

	private List<PropertyConditionMatcher> prepareORConditionsList(String ipAddress, String email, String phone) {
		PropertyConditionMatcher ipAddressCondition = new PropertyConditionMatcher("ipAddress", "==", ipAddress);
		PropertyConditionMatcher emailCondition = new PropertyConditionMatcher("email", "==", email);
		PropertyConditionMatcher phoneCondition = new PropertyConditionMatcher("phone", "==", phone);

		List<PropertyConditionMatcher> conditionsList = new ArrayList<PropertyConditionMatcher>();
		conditionsList.add(ipAddressCondition);
		conditionsList.add(emailCondition);
		conditionsList.add(phoneCondition);

		return conditionsList;
	}

	private Criterion prepareBetweenCondition() {

		Timestamp currentTimeStamp = DateTimestampUtil.getCurrentTimestamp();

		Timestamp oneMinuteBefore = null;
		try {
			oneMinuteBefore = DateTimestampUtil.getTimestampFromCurrentTime(GregorianCalendar.MINUTE, -1);
		} catch (DateTimestampException e) {
			e.printStackTrace();
		}

		return Restrictions.between("createdDate", oneMinuteBefore, currentTimeStamp);
	}

	private boolean fullBlock(String ipAddress, String email, String phone) {

		AccessControl accessControl = new AccessControl();
		accessControl.setIpAddress(ipAddress);
		accessControl.setEmail(email);
		accessControl.setPhone(phone);
		accessControl.setStatus(com.exchangerpoint.exchangeservices.common.Constants.USER_BLOCKED);

		boolean submitAction = genericService.submit(accessControl);

		if(submitAction) {
			applicationDataRepository.init();
		}

		return submitAction;
	}

	public void setApplicationDataRepository(ApplicationDataRepository applicationDataRepository) {
		this.applicationDataRepository = applicationDataRepository;
	}

	public void setGenericService(GenericService genericService) {
		this.genericService = genericService;
	}
}
