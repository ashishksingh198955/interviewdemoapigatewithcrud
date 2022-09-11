package com.demo.categoryservice.controllers;

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
import org.springframework.web.bind.annotation.RestController;

import com.demo.categoryservice.dto.CategoryDTO;
import com.demo.categoryservice.models.AppResponse;
import com.demo.categoryservice.models.Category;
import com.demo.categoryservice.repository.CategoryRepository;
import com.demo.categoryservice.utils.ApplicationConstants;
import com.demo.categoryservice.utils.ApplicationURIConstants;
import com.demo.categoryservice.validators.CategoryValidator;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApplicationURIConstants.BASE_API)
public class CategoryController extends BaseController {

	private final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	public CategoryValidator categoryValidator;

	@Autowired
	CategoryRepository categoryRepository;

	@PostMapping(ApplicationURIConstants.ADDCATEGORY)
	public AppResponse<Long> addCategory(@Valid @RequestBody Category cateInfo,final BindingResult bindingResult) {


		final AppResponse<Long> response = new AppResponse<>();
		LOGGER.info(ApplicationConstants.METHOD_CALLED_LABEL);
		final Set<String> errorSet = new HashSet<>();
		try {

			categoryValidator.validate(cateInfo, bindingResult);
			if (bindingResult.hasErrors()) {
				bindingResult.getFieldErrors().forEach(er -> {
					errorSet.add(getMessage(er.getCodes()[0]));
				});
				response.setErrors(errorSet);
				response.setStatus(HttpStatus.BAD_REQUEST.value());
				return response;
			}

			Category company=categoryRepository.save(cateInfo);
			if (company.getCategoryid() !=null)
			{
				response.setStatus(HttpStatus.OK.value());
				response.setMessage(ApplicationConstants.CATEGORY_ADD_SUCCESS);
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


	@GetMapping(value = ApplicationURIConstants.VIEW_CATEGORY)
	public AppResponse<Object> viewCategoryById(@PathVariable(ApplicationConstants.ID) final String categoryId) {

		LOGGER.info(ApplicationConstants.METHOD_ENTER_LABEL);
		final AppResponse<Object> response = new AppResponse<>();

		try {
			final Optional<Category> catDTO =categoryRepository.findById(Long.parseLong(categoryId));
			response.setData(catDTO);
			response.setStatus(HttpStatus.OK.value());

		} catch (final Exception exception) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setMessage(com.demo.categoryservice.utils.ApplicationConstants.HTTP_ERROR_MESSAGE_500);
			LOGGER.error(ApplicationConstants.METHOD_EXCEPTION_LABEL, exception);
		}

		LOGGER.info(ApplicationConstants.METHOD_EXIT_LABEL);
		return response;
	}


	@GetMapping(value = ApplicationURIConstants.LIST_OF_CATEGORY)
	public AppResponse<Object> getCategoryList() {
		LOGGER.info(ApplicationConstants.METHOD_ENTER_LABEL);
		final AppResponse<Object> response = new AppResponse<>();
		try {

			final List<CategoryDTO> riList = getServiceRegistry().getCategoryService().findAllCategory();

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

	@PutMapping(value = ApplicationURIConstants.UPDATE_CATEGORY)
	public AppResponse<Long> updateCategory(@Valid @RequestBody final Category categoryData, final BindingResult bindingResult) {

		LOGGER.info(ApplicationConstants.METHOD_ENTER_LABEL);
		final AppResponse<Long> response = new AppResponse<>();

		try {

			categoryValidator.validate(categoryData, bindingResult);
			final Set<String> errorSet = new HashSet<>();

			if (bindingResult.hasErrors()) {
				bindingResult.getFieldErrors().forEach(er -> errorSet.add(getMessage(er.getCodes()[0])));
				response.setErrors(errorSet);
				response.setStatus(HttpStatus.BAD_REQUEST.value());
				return response;
			}

			if (categoryData != null && categoryData.getCategoryid() != null) {

				final Category catDTO =categoryRepository.save(categoryData);
				System.err.println(categoryData.toString());

				if(catDTO !=null) {
					response.setMessage(ApplicationConstants.CATEGORY_EDIT_SUCCESS);
					response.setStatus(HttpStatus.OK.value());
				}



			} else {
				response.setMessage(ApplicationConstants.INVALID_USER);
				response.setStatus(HttpStatus.BAD_REQUEST.value());
			}

			return response;

		} catch (final Exception exception) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setMessage(com.demo.categoryservice.utils.ApplicationConstants.HTTP_ERROR_MESSAGE_500);
			LOGGER.error(ApplicationConstants.METHOD_EXCEPTION_LABEL, exception);
		}
		LOGGER.info(ApplicationConstants.METHOD_EXIT_LABEL);
		return response;
	}

	@PutMapping(value = ApplicationURIConstants.DELETE_CATGORY)
	public AppResponse<Long> deleteCategoryById(@PathVariable(ApplicationConstants.ID) final String categoryId) {

		LOGGER.info(ApplicationConstants.METHOD_CALLED_LABEL);
		final AppResponse<Long> response = new AppResponse<>();

		response.setStatus(HttpStatus.BAD_REQUEST.value());
		try {
			if (categoryId !=null && !categoryId.isEmpty()) {
				categoryRepository.deleteById(Long.parseLong(categoryId));
				response.setStatus(HttpStatus.OK.value());
				response.setMessage(ApplicationConstants.CATEGORY_DELETE_SUCCESS);
			} else {
				response.setStatus(HttpStatus.OK.value());
				response.setMessage(ApplicationConstants.RECORD_NOT_FOUND);
			}

		} catch (final Exception exception) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setMessage(com.demo.categoryservice.utils.ApplicationConstants.HTTP_ERROR_MESSAGE_500);
			LOGGER.error(ApplicationConstants.METHOD_EXCEPTION_LABEL, exception);
		}
		LOGGER.info(ApplicationConstants.METHOD_EXIT_LABEL);
		return response;
	}
}



