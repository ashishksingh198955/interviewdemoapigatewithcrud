package com.demo.apigateway.validators;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;



public class BaseValidator {

	private Errors errors;

	protected static final String NOTEMPTY = "NotEmpty";
	protected static final String NOTNULL = "NotNull";
	protected static final String LENGTH = "Length";
	protected static final String POSSIBLEVALUE = "PossibleValue";
	protected static final String PATTERN = "Pattern";
	protected void setErrors(final Errors errors) {

		this.errors = errors;
	}

	protected Errors getErrors() {

		return errors;
	}

	/**
	 * @param field
	 * @param currentlength
	 * @param maxLength
	 */
	protected void validateMaxLength(final String field, final int currentlength, final int maxLength) {

		if (currentlength > maxLength) {
			getErrors().rejectValue(field, LENGTH);
		}
	}

	/**
	 * @param fieldname
	 * @param field
	 * @param maxLength
	 */
	protected void validateMaxLengthIfNotNull(final String fieldname, final String field, final int maxLength) {

		if (field != null && !field.isEmpty()) {
			if (field.length() > maxLength) {
				getErrors().rejectValue(fieldname, LENGTH);
			}
		}
	}

	/**
	 * @param field
	 * @param currentlength
	 * @param minLength
	 */
	protected void validateMinLength(final String field, final int currentlength, final int minLength) {

		if (currentlength < minLength) {
			getErrors().rejectValue(field, LENGTH);
		}
	}

	/**
	 * @param fieldname
	 * @param field
	 * @param minLength
	 */
	protected void validateMinLengthIfNotNull(final String fieldname, final String field, final int minLength) {

		if (field != null && !field.isEmpty()) {
			if (field.length() < minLength) {
				getErrors().rejectValue(fieldname, LENGTH);
			}
		}
	}

	/**
	 * This method is responsible for validating the field against the regex pattern
	 *
	 * @param pattern
	 * @param fieldname
	 * @param message
	 */
	protected void validateAgainstPattern(final Pattern pattern, final CharSequence input, final String fieldname) {

		if (!pattern.matcher(input).matches()) {
			getErrors().rejectValue(fieldname, PATTERN);
		}
	}

}