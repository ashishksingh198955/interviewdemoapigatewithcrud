package com.demo.departmentservice.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.demo.departmentservice.dto.DepartmentDTO;
import com.demo.departmentservice.models.AppResponse;
import com.demo.departmentservice.models.Department;
import com.demo.departmentservice.repository.DepartmentRepository;
import com.demo.departmentservice.utils.ApplicationConstants;
import com.demo.departmentservice.utils.ApplicationURIConstants;
import com.demo.departmentservice.validators.DepartmentValidator;





@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApplicationURIConstants.BASE_API)
public class DepartmentController extends BaseController {

	private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

	@Autowired
	public DepartmentValidator departmentValidator;

	@Autowired
	DepartmentRepository departRepository;

	@PostMapping(ApplicationURIConstants.ADD_DEPARTMENT)
	public AppResponse<Long> registerCompany(@Valid @RequestBody Department companyInfo,final BindingResult bindingResult) {


		final AppResponse<Long> response = new AppResponse<>();
		LOGGER.info(ApplicationConstants.METHOD_CALLED_LABEL);
		final Set<String> errorSet = new HashSet<>();
		try {

			departmentValidator.validate(companyInfo, bindingResult);
			if (bindingResult.hasErrors()) {
				bindingResult.getFieldErrors().forEach(er -> {
					errorSet.add(getMessage(er.getCodes()[0]));
				});
				response.setErrors(errorSet);
				response.setStatus(HttpStatus.BAD_REQUEST.value());
				return response;
			}

			Department company=departRepository.save(companyInfo);
			if (company.getDepartmentid() !=null)
			{
				response.setStatus(HttpStatus.OK.value());
				response.setMessage(ApplicationConstants.DEPARTMENT_ADD_SUCCESS);
			}

		}
		catch (final Exception exception) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage(ApplicationConstants.ERROR_LABEL);
			response.addError("Please verify the request format. It contains invalid value for one of the field.");
			LOGGER.info(ApplicationConstants.METHOD_EXCEPTION_LABEL + exception);
		}
		LOGGER.info(ApplicationConstants.METHOD_EXIT_LABEL);
		return response;
	}

	@GetMapping(value = ApplicationURIConstants.LIST_OF_DEPARTMENT)
	@ResponseBody
	public AppResponse<Object> getCompanyList() {
		LOGGER.info(ApplicationConstants.METHOD_ENTER_LABEL);
		final AppResponse<Object> response = new AppResponse<>();
		try {

			final List<DepartmentDTO> riList = getServiceRegistry().getCompanyService().findAllCompany();

			System.out.println(riList.toString());
			response.setData(riList);
			response.setStatus(HttpStatus.OK.value());

		} catch (final Exception exception) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage(ApplicationConstants.ERROR_LABEL);
			LOGGER.info(ApplicationConstants.METHOD_EXCEPTION_LABEL + exception);
		}
		LOGGER.info(ApplicationConstants.METHOD_EXIT_LABEL);
		return response;
	}
	
	@GetMapping(value = ApplicationURIConstants.VIEW_DEPARTMENT)
	public AppResponse<Object> viewCategoryById(@PathVariable(ApplicationConstants.ID) final String departmentId) {

		LOGGER.info(ApplicationConstants.METHOD_ENTER_LABEL);
		final AppResponse<Object> response = new AppResponse<>();

		try {
			final Optional<Department> departDTO =departRepository.findById(Long.parseLong(departmentId));
			response.setData(departDTO);
			response.setStatus(HttpStatus.OK.value());

		} catch (final Exception exception) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setMessage(com.demo.departmentservice.utils.ApplicationConstants.HTTP_ERROR_MESSAGE_500);
			LOGGER.error(ApplicationConstants.METHOD_EXCEPTION_LABEL, exception);
		}

		LOGGER.info(ApplicationConstants.METHOD_EXIT_LABEL);
		return response;
	}
	
	@PutMapping(value = ApplicationURIConstants.UPDATE_DEPARTMENT)
	public AppResponse<Long> updateDepartment(@Valid @RequestBody final Department departmentData, final BindingResult bindingResult) {

		LOGGER.info(ApplicationConstants.METHOD_ENTER_LABEL);
		final AppResponse<Long> response = new AppResponse<>();

		try {

			departmentValidator.validate(departmentData, bindingResult);
			final Set<String> errorSet = new HashSet<>();

			if (bindingResult.hasErrors()) {
				bindingResult.getFieldErrors().forEach(er -> errorSet.add(getMessage(er.getCodes()[0])));
				response.setErrors(errorSet);
				response.setStatus(HttpStatus.BAD_REQUEST.value());
				return response;
			}

			if (departmentData != null && departmentData.getDepartmentid() != null) {

				final Department catDTO =departRepository.save(departmentData);
				System.err.println(departmentData.toString());

				if(catDTO !=null) {
					response.setMessage(ApplicationConstants.DEPARTMENT_EDIT_SUCCESS);
					response.setStatus(HttpStatus.OK.value());
				}

			} else {
				response.setMessage(ApplicationConstants.INVALID_USER);
				response.setStatus(HttpStatus.BAD_REQUEST.value());
			}

			return response;

		} catch (final Exception exception) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setMessage(com.demo.departmentservice.utils.ApplicationConstants.HTTP_ERROR_MESSAGE_500);
			LOGGER.error(ApplicationConstants.METHOD_EXCEPTION_LABEL, exception);
		}
		LOGGER.info(ApplicationConstants.METHOD_EXIT_LABEL);
		return response;
	}

	@PutMapping(value = ApplicationURIConstants.DELETE_DEPARTMENT)
	public AppResponse<Long> deleteDepartmentById(@PathVariable(ApplicationConstants.ID) final String departmentId) {

		LOGGER.info(ApplicationConstants.METHOD_CALLED_LABEL);
		final AppResponse<Long> response = new AppResponse<>();

		response.setStatus(HttpStatus.BAD_REQUEST.value());
		try {
			if (departmentId !=null && !departmentId.isEmpty()) {
				departRepository.deleteById(Long.parseLong(departmentId));
				response.setStatus(HttpStatus.OK.value());
				response.setMessage(ApplicationConstants.DEPARTMENT_DELETE_SUCCESS);
			} else {
				response.setStatus(HttpStatus.OK.value());
				response.setMessage(ApplicationConstants.RECORD_NOT_FOUND);
			}

		} catch (final Exception exception) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setMessage(com.demo.departmentservice.utils.ApplicationConstants.HTTP_ERROR_MESSAGE_500);
			LOGGER.error(ApplicationConstants.METHOD_EXCEPTION_LABEL, exception);
		}
		LOGGER.info(ApplicationConstants.METHOD_EXIT_LABEL);
		return response;
	}
}



