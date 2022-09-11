package com.demo.departmentservice.service;

import java.util.List;

import com.demo.departmentservice.dto.DepartmentDTO;



public interface DepartmentService {

	/**
	 * The <code>CompanyService</code> interface responsible to communicate with database for User module.
	 *
	 * @author Ashish Kumar Singh
	 */

	public List<DepartmentDTO> findAllCompany();
}
