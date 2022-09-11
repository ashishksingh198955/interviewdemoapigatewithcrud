package com.demo.departmentservice.validators;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.demo.departmentservice.models.Department;
import com.demo.departmentservice.utils.ApplicationConstants;
import com.demo.departmentservice.utils.ApplicationRegExConstants;




/**
 * The <code>AddCompanyValidator</code> class is responsible for validating company that is added for employee.
 *
 * @author Ashish Kumar Singh
 *
 */
@Component
public class DepartmentValidator extends BaseValidator implements Validator {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentValidator.class);

	private static final String DEPARTMENT_NAME = "departmentname";
	private static final String DEPARTMENT_CODE= "departmentCode";
	private static final String ORGANIZATION_NAME = "organizationName";


	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		LOGGER.info(com.demo.departmentservice.utils.ApplicationConstants.METHOD_CALLED_LABEL);

		final Pattern patternForALphabets = Pattern.compile(ApplicationRegExConstants.ALPHABETS_ONLY);
		final Pattern patternForDigit = Pattern.compile(ApplicationRegExConstants.DIGITS_ONLY);


		final Department depart = (Department) target;

		// Step 1 Set the errors object in parent
		setErrors(errors);

		// Step 2 Validate Not Empty and Not Null Values
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, DEPARTMENT_NAME, NOTEMPTY);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, DEPARTMENT_CODE, NOTEMPTY);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, ORGANIZATION_NAME, NOTEMPTY);

	

		if (!errors.hasErrors()) {

			validateAgainstPattern(patternForALphabets, depart.getDepartmentname(), DEPARTMENT_NAME);
			validateAgainstPattern(patternForALphabets, depart.getDepartmentCode(), DEPARTMENT_CODE);
			validateAgainstPattern(patternForALphabets, depart.getOrganizationName(), ORGANIZATION_NAME);

		}
		LOGGER.info(ApplicationConstants.METHOD_EXIT_LABEL);
	}

}
