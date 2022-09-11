package com.demo.categoryservice.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.categoryservice.dto.CategoryDTO;
import com.demo.categoryservice.repository.CategoryRepository;


@Service
public class CategoryServiceImpl implements com.demo.categoryservice.service.CategoryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Autowired
	CategoryRepository companyRepository;

	@Override
	public List<CategoryDTO> findAllCategory() {
		// TODO Auto-generated method stub
		List<CategoryDTO> list= companyRepository.findAllCompany();
		return list;
	}


}
