package com.infosys.project.order.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrdersValidator {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public static String validateOrder(String address) {
		if (!isValidAddress(address)) {
			return "Too Large Address. Length of Address should be below 100 characters";
		}
		
		return "ok";
	}

	private static Boolean isValidAddress(String address) {
		if (address.length()<=100)
			return true;
		return false;
	}
}
