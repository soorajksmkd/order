package com.infosys.project.order.validator;

public class StatusValidator {

	public static Boolean validateStatus(String status) {
		
		if (status.equalsIgnoreCase("placed")||status.equalsIgnoreCase("shipping")||
				status.equalsIgnoreCase("dispatched")||status.equalsIgnoreCase("order placed")||
				status.equalsIgnoreCase("delivered"))
			return true;
		return false;
	}

}
