package com.exchangerpoint.commonservices.utility;

import java.util.Random;

public class Utility {

	public static String generateVerifyCode() {
		Random rnd = new Random();
	    int number = rnd.nextInt(999999);

	    return String.format("%06d", number);
	}
	
	public static String generateOrderId() {
		int length = 10;
		String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String orderId = "";

		for (int i = length; i > 0; --i) {
			orderId += chars.charAt((int)Math.round(Math.random() * (chars.length() - 1)));
		}
		orderId = orderId.toUpperCase();

		return orderId;
	}
	
	public static String generateVerifyToken() {
		int length = 15;
		String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String orderId = "";

		for (int i = length; i > 0; --i) {
			orderId += chars.charAt((int)Math.round(Math.random() * (chars.length() - 1)));
		}
		orderId = orderId.toLowerCase();

		return orderId;
	}
}
