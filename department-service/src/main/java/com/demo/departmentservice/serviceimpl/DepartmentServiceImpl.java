package com.demo.departmentservice.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.departmentservice.dto.DepartmentDTO;
import com.demo.departmentservice.repository.DepartmentRepository;
import com.demo.departmentservice.utils.ApplicationConstants;

@Service
public class DepartmentServiceImpl implements com.demo.departmentservice.service.DepartmentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);

	@Autowired
	DepartmentRepository depRepository;

	@Override
	public List<DepartmentDTO> findAllCompany() {
		LOGGER.info(ApplicationConstants.METHOD_ENTER_LABEL);
		
				List<DepartmentDTO> list= depRepository.findAllDepartment();
				return list;
	}
}
