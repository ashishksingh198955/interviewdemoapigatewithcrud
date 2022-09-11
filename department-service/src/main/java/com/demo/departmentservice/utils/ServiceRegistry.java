
package com.demo.departmentservice.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.departmentservice.service.DepartmentService;

/**
 * The <code>ServiceRegistry</code> class responsible for @Autowired all the Service interfaces.
 *
 * @author Ashish Kumar Singh
 */
@Component
public class ServiceRegistry {

	@Autowired
	private DepartmentService companyService;

	public DepartmentService getCompanyService() {
		return companyService;
	}

	
}
