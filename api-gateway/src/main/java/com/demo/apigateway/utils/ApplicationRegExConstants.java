package com.demo.apigateway.utils;

/**
 * The <code>ApplicationRegExConstants</code> class holds application RegEx details used the application.
 *
 * @author Ashish Kumar Singh
 *
 */
public final class ApplicationRegExConstants {
	
	public static final String EMAIL_REGEX = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
			+ "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
			+ "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
			+ "([a-zA-Z0-9]+[\\w-]+\\.)+[a-zA-Z]{1}[a-zA-Z0-9-]{1,23})| |$";

	public static final String PASSWORD_REGEX = "((?=.*[a-zA-Z])(?=.*[\\d!\"#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]).{8,32})";

	private ApplicationRegExConstants() {

	}
}