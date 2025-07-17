package com.exchangerpoint.exchangeservices.service;

import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.exchangerpoint.exchangeservices.common.ApplicationDataRepository;

public class LocationService {

	private static final Logger LOG = Logger.getLogger(LocationService.class);

	private String region;
	private String country;
	private String latitude;
	private String longitude;

	@Autowired
	private ApplicationDataRepository applicationDataRepository;

	@SuppressWarnings("static-access")
	private Map<String, String> adminParamMap = applicationDataRepository.adminParamMap;
	private String locationAPIURL = adminParamMap.get("locationapiurl");

	public void updateLocationDetails(String ipAddress) {
		String result = new RestTemplate().getForObject(locationAPIURL + ipAddress, String.class);

		LOG.info("JSON Recieved from Location API: " + result);

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject)jsonParser.parse(result);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (jsonObject != null && jsonObject.size() != 0) {
			this.region = (String)jsonObject.get("city");
			this.country = (String)jsonObject.get("country");
			this.longitude = jsonObject.get("lon").toString();
			this.latitude = jsonObject.get("lat").toString();
		}
	}

	public String getRegion() {
		return region;
	}

	public String getCountry() {
		return country;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setApplicationDataRepository(ApplicationDataRepository applicationDataRepository) {
		this.applicationDataRepository = applicationDataRepository;
	}
}