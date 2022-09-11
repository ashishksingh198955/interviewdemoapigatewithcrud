package com.demo.categoryservice.service;

import java.util.List;

import com.demo.categoryservice.dto.CategoryDTO;


public interface CategoryService {

	/**
	 * The <code>CompanyService</code> interface responsible to communicate with database for User module.
	 *
	 * @author Ashish Kumar Singh
	 */

	public List<CategoryDTO> findAllCategory();
	
}
