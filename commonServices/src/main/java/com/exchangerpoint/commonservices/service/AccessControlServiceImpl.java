package com.exchangerpoint.commonservices.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.exchangerpoint.commonservices.errorcode.ServiceErrorCode;
import com.exchangerpoint.commonservices.exception.AccessControlException;
import com.exchangerpoint.databaseservices.entity.AccessControl;
import com.exchangerpoint.genericservices.exception.GenericException;
import com.exchangerpoint.genericservices.service.GenericService;
import com.exchangerpoint.genericservices.util.PropertyConditionMatcher;

@Transactional
public class AccessControlServiceImpl implements AccessControlService {

	protected final Log logger = LogFactory.getLog(AccessControlServiceImpl.class);

	@Autowired
	private GenericService genericService;

	public void validateAccessControl(String email, String phone, String ipAddress) throws AccessControlException {

		List<AccessControl> accessControlRecordList = null;
		try {
			accessControlRecordList = getAaccessControlRecords(email, phone, ipAddress);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (accessControlRecordList != null && accessControlRecordList.size() >= 1) {
			logger.error("Error Code : " + ServiceErrorCode.ACCESS_DENIED.getErrorCode() + " Error Message : "
					+ ServiceErrorCode.ACCESS_DENIED.getErrorMessage());
			throw new AccessControlException(ServiceErrorCode.ACCESS_DENIED.getErrorMessage(),
					ServiceErrorCode.ACCESS_DENIED.getErrorCode());
		}

	}

	@SuppressWarnings({ "unchecked" })
	private List<AccessControl> getAaccessControlRecords(String email, String phone, String ipAddress)
			throws GenericException {
        
		Criteria criteria = genericService.createBlankCriteria(AccessControl.class);
		criteria = genericService.addORConditionToCriteria(criteria, prepareORConditionsList(email, phone, ipAddress));
		List<AccessControl> accessControlRecordList = criteria.list();
		return accessControlRecordList;
	}

	private List<PropertyConditionMatcher> prepareORConditionsList(String email, String phone, String ipAddress) {
		PropertyConditionMatcher emailCondition = new PropertyConditionMatcher("email", "==", email);
		PropertyConditionMatcher phoneCondition = new PropertyConditionMatcher("phone", "==", phone);
		PropertyConditionMatcher ipAddressCondition = new PropertyConditionMatcher("ipAddress", "==", ipAddress);
	

		List<PropertyConditionMatcher> conditionsList = new ArrayList<PropertyConditionMatcher>();
		conditionsList.add(emailCondition);
		conditionsList.add(phoneCondition);
		conditionsList.add(ipAddressCondition);
		

		return conditionsList;
	}

	public void setGenericService(GenericService genericService) {
		this.genericService = genericService;
	}
}
