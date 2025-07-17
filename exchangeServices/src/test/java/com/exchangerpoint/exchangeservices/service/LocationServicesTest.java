package com.exchangerpoint.exchangeservices.service;

import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.exchangerpoint.exchangeservices.service.LocationService;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@ContextConfiguration(locations = {"classpath:application-test-context.xml"})
public class LocationServicesTest {

	@Autowired
	private LocationService locationService;
	
	private String ipAddress;
	
	@Before
	public void setup() {
		ipAddress = "123.201.182.215";
	}
	
	@Ignore("Tested")
	@Test
	public void testLocationServices() {
		//String URL = "http://api.hostip.info/get_json.php?ip=";
		//String ipAddress = "122.248.183.2";
	/*	String result = new RestTemplate().getForObject("https://freegeoip.net/json/122.248.183.1/", String.class, ipAddress);
		
		System.out.println("Location Result: " + result);
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) jsonParser.parse(result);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Set jsonKeySet = jsonObject.keySet();
		Iterator<Object> iter = jsonKeySet.iterator();
		while(iter.hasNext()) {
			Object object = iter.next();
			System.out.println("Key: " + object + "; Value: " + jsonObject.get(object));
		}*/		
		locationService.updateLocationDetails(ipAddress);
		
		Assert.assertTrue("Details not found", (!locationService.getCountry().isEmpty() || !locationService.getLongitude().isEmpty()));
		
		System.out.println("Region: " + locationService.getRegion());
		System.out.println("Country: " + locationService.getCountry());
		System.out.println("Longitude: " + locationService.getLongitude());
		System.out.println("Latitude: " + locationService.getLatitude());
	}

	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}
}
