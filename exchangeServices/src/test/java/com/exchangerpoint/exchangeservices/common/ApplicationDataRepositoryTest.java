package com.exchangerpoint.exchangeservices.common;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.exchangerpoint.databaseservices.entity.Currency;
import com.exchangerpoint.databaseservices.entity.Ecurrency;
import com.exchangerpoint.exchangeservices.common.ApplicationDataRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@ContextConfiguration(locations = { "classpath:application-test-context.xml" })
public class ApplicationDataRepositoryTest {

	/*
	 * @Autowired private ApplicationDataRepository applicationDataRepository;
	 */
	@Before
	public void setup() {

	}

	//@Ignore("Tested")
	@Test
	public void testGetBothLists() {

		System.out.println("******Executing test case: ApplicationDataRepositoryTest:testGetBothLists******");
		System.out.println("*******************************************************************************");
		List<Currency> currencyList = ApplicationDataRepository.currencyList;

		Assert.assertTrue("Currency List size is zero", (currencyList.size() != 0));
		System.out.println("Currency List size: " + currencyList.size());
		for (int i = 0; i < currencyList.size(); i++) {
			System.out.println(currencyList.get(i).getCurrency());
		}

		System.out.println("*******************************************************************************");
		List<Ecurrency> ecurrencyList = ApplicationDataRepository.ecurrencyList;

		Assert.assertTrue("Ecurrency List size is zero", (ecurrencyList.size() != 0));
		System.out.println("Ecurrency List size: " + ecurrencyList.size());
		for (int i = 0; i < ecurrencyList.size(); i++) {
			System.out.println(ecurrencyList.get(i).getEcurrency());
		}

		System.out.println("*******************************************************************************");
		Map<String, Float> exchangeRateMap = ApplicationDataRepository.exchangeRateMap;

		System.out.println("*********########### Value for fake key vikrant: " + exchangeRateMap.get("vikrant"));
		Assert.assertTrue("Exchange Rate Map size is zero", (exchangeRateMap.size() != 0));
		System.out.println("Exchange Rate Map size: " + exchangeRateMap.size());
		Set<String> exchangeRateKeySet = exchangeRateMap.keySet();
		Iterator<String> exchangeRateMapIterator = exchangeRateKeySet.iterator();
		while (exchangeRateMapIterator.hasNext()) {
			String key = exchangeRateMapIterator.next();
			System.out.println("Value for key " + key + ": " + exchangeRateMap.get(key));
		}
		System.out.println("*******************************************************************************");

		/*
		 * System.out.println("*******************************************************************************");
		 * List<Testimonial> testimonialList = ApplicationDataRepository.testimonialList;
		 * Assert.assertTrue("Testimonial List size is zero", (testimonialList.size() != 0));
		 * System.out.println("Testimonial List size: " + testimonialList.size()); for (int i = 0; i <
		 * testimonialList.size(); i++) { System.out.println(testimonialList.get(i).getName()); }
		 */

		/*
		 * System.out.println("*******************************************************************************");
		 * Map<String, String> adminParamMap = ApplicationDataRepository.adminParamMap;
		 * Assert.assertTrue("Admin Params Map size is zero", (adminParamMap.size() != 0));
		 * System.out.println("Admin Params Map size: " + adminParamMap.size()); Set<String> adminParamsKeySet =
		 * adminParamMap.keySet(); Iterator<String> adminParamsMapIterator = adminParamsKeySet.iterator(); while
		 * (adminParamsMapIterator.hasNext()) { String key = adminParamsMapIterator.next();
		 * System.out.println("Value for key " + key + ": " + adminParamMap.get(key)); }
		 * System.out.println("*******************************************************************************");
		 * Map<String, Float> exchangeRateMap = ApplicationDataRepository.exchangeRateMap;
		 * Assert.assertTrue("Exchange Rate Map size is zero", (exchangeRateMap.size() != 0));
		 * System.out.println("Exchange Rate Map size: " + exchangeRateMap.size()); Set<String> exchangeRateKeySet =
		 * exchangeRateMap.keySet(); Iterator<String> exchangeRateMapIterator = exchangeRateKeySet.iterator(); while
		 * (exchangeRateMapIterator.hasNext()) { String key = exchangeRateMapIterator.next();
		 * System.out.println("Value for key " + key + ": " + exchangeRateMap.get(key)); }
		 * System.out.println("*******************************************************************************");
		 * List<AccessControl> accessControlLost = ApplicationDataRepository.blockedUsersList;
		 * Assert.assertTrue("Access Control List size is zero", (accessControlLost.size() != 0));
		 * System.out.println("Access Control List size: " + accessControlLost.size()); for (int i = 0; i <
		 * accessControlLost.size(); i++) { System.out.println(accessControlLost.get(i).getIpAddress()); }
		 */

		System.out.println("*******************************************************************************");
	}

	/*
	 * public void setApplicationDataRepository(ApplicationDataRepository applicationDataRepository) {
	 * this.applicationDataRepository = applicationDataRepository; }
	 */
}
