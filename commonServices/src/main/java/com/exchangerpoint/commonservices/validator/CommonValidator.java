package com.exchangerpoint.commonservices.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.routines.InetAddressValidator;
import org.springframework.beans.factory.annotation.Autowired;


import com.exchangerpoint.commonservices.service.AccessControlService;

public class CommonValidator {
	
	
	
	final static String REGEX_ONLY_LETTER = "^[a-zA-Z]+$";
	final static String REGEX_ONLY_DIGIT = "^[0-9]+$";
	final static String REGEX_PHONE_NUMBER ="^[+0-9]+$";
	final static String REGEX_NUMBER_AND_LETTER ="^[0-9a-zA-Z]+$";
	final static String REGEX_SPECIAL_CHAR = "^[0-9a-zA-Z$!@#%&^*~]+$";
		
	
	public static boolean isBlank(String input) {
		
		if(input == null || input.trim().length() <=0)
			return true;
		
		return false;
	}
	
	public static boolean letterValidator(String input) {
		return input.matches(REGEX_ONLY_LETTER);
		
	}
	
	public static boolean digitValidator(String input) {
		return input.matches(REGEX_ONLY_DIGIT);
		
	}
	
	public static boolean phoneValidator(String input) {
		return input.matches(REGEX_PHONE_NUMBER);
		
	}

	public static boolean letterAndNumberValidator(String input) {
		return input.matches(REGEX_NUMBER_AND_LETTER);
	}

	public static boolean specialCharValidator(String input) {
		return input.matches(REGEX_SPECIAL_CHAR);
	}
	
	
	
	public static String getCustomerRequestIpAddress(HttpServletRequest request) {
	    String xfHeader = request.getHeader("X-Forwarded-For");
	    if (xfHeader == null){
	        return request.getRemoteAddr();
	    }
	    return xfHeader.split(",")[0];
	}
	
	public static boolean validateEmail(String email) {

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
	
	
	
	
	
}
