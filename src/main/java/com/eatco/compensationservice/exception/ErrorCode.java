package com.eatco.compensationservice.exception;

/**
 * <p>
 * Handle child statistics error code.
 * </p>
 * 
 * @author Rajasankar
 *
 */
public enum ErrorCode implements ErrorHandle {
	EATCO2_MANAGEMENT_1001(1001, "Something went wrong. Please contact the administrator."),
	EATCO2_MANAGEMENT_1002(1002, "Username already exists!"),
	EATCO2_MANAGEMENT_1003(1003,"Invalid username/password supplied"),
	EATCO2_MANAGEMENT_1004(1004,"Failed to create reset token"),
	EATCO2_MANAGEMENT_1005(1005,"Expired or invalid JWT token"),
	EATCO2_MANAGEMENT_1006(1006,"Email id already exists"),
	EATCO2_MANAGEMENT_1007(1007,"Error in sending mail"),
	EATCO2_MANAGEMENT_1008(1008,"Category id not found"),
	EATCO2_MANAGEMENT_1009(1009,"Invalid Compensation details! Compensation_id not found in Database"),
	EATCO2_MANAGEMENT_1010(1010,"Invalid Item details! Not found in Database"),
	EATCO2_MANAGEMENT_1011(1011,"Failed to update password"),
	EATCO2_MANAGEMENT_1012(1012,"Invalid User details! Not found in Database"),
	EATCO2_MANAGEMENT_1013(1013, "Error in updating fcm token"),
	EATCO2_MANAGEMENT_1014(1014,"Error in saving user permissions"),
	EATCO2_MANAGEMENT_1015(1015,"Error fetching user permissions"),
	EATCO2_MANAGEMENT_1016(1016,"Email id not found"),
	EATCO2_MANAGEMENT_2001(2001, "Something went wrong. Please contact the administrator."),
	EATCO2_MANAGEMENT_2002(2002, "Please provide valid business profile details."),
	EATCO2_MANAGEMENT_2003(2003, "Please provide valid business opening hours."),
	EATCO2_MANAGEMENT_2004(2004, "Please provide valid business address details."),
	EATCO2_MANAGEMENT_2005(2005, "Email address already present.Please provide valid email address."),
	EATCO2_MANAGEMENT_2006(2006, "Please provide valid email address."),
	EATCO2_MANAGEMENT_2007(2007, "Please provide valid business profile id."),
	EATCO2_MANAGEMENT_2008(2008, "Please provide valid business category details."),
	EATCO2_MANAGEMENT_2009(2009, "The given business category is already exists. Please provide valid details."),
	EATCO2_MANAGEMENT_2010(2010, "Please provide valid country details"),
	EATCO2_MANAGEMENT_2011(2011, "The given country name already exists. Please validate it."),
	EATCO2_MANAGEMENT_2012(2012, "Please provide fileName and file in the given input."),
	EATCO2_MANAGEMENT_2013(2013, "Please provide valid cashback type name."),
	EATCO2_MANAGEMENT_2014(2014, "Given cashback type already exists."),
	EATCO2_MANAGEMENT_2015(2015, "Please provide valid cashback details for cashback creation."),
	EATCO2_MANAGEMENT_2016(2016, "Cashback detail not exist for given cashback. Please provide valid cashback id."),
	EATCO2_MANAGEMENT_2017(2017,"Error in rating business"),
	EATCO2_MANAGEMENT_2018(2018,"Invalid rating"),
	EATCO2_MANAGEMENT_2019(2019, "Please provide valid cashback category.");

	private final int code;
	private final String message;

	ErrorCode(int errorCode, String message) {
		this.code = errorCode;
		this.message = message;
	}

	@Override
	public int getErrorCode() {
		return this.code;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

}
