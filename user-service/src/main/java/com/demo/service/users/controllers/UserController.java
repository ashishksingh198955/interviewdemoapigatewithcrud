package com.demo.service.users.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.demo.service.users.dto.UserDTO;
import com.demo.service.users.jwtutils.JwtUtils;
import com.demo.service.users.models.AppResponse;
import com.demo.service.users.models.Role;
import com.demo.service.users.models.User;
import com.demo.service.users.models.UserRole;
import com.demo.service.users.payload.response.MessageResponse;
import com.demo.service.users.repository.RoleRepository;
import com.demo.service.users.repository.UserRepository;
import com.demo.service.users.repository.UserRolesRepository;
import com.demo.service.users.utils.ApplicationConstants;
import com.demo.service.users.utils.ApplicationURIConstants;
import com.demo.service.users.validators.AddUserValidator;

import io.jsonwebtoken.Claims;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApplicationURIConstants.BASE_API)
public class UserController extends BaseController{


	private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);


	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	public AddUserValidator addUserValidator;

	MessageResponse messageResponse;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	UserRolesRepository userRolesRepository;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@PostMapping(ApplicationURIConstants.ADD_USER)
	public AppResponse<Long> registerUserOrEmployee(@Valid @RequestBody User userDetails,final BindingResult bindingResult) {

		System.err.println(userDetails.toString());
		final AppResponse<Long> response = new AppResponse<>();
		LOGGER.info(ApplicationConstants.METHOD_CALLED_LABEL);
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();


		String token = request.getHeader("Authorization").split(" ")[1];
		final Claims claims = jwtUtils.getAllClaimsFromToken(token);
		long userProfileId=claims.get("User Id", Long.class);	
		boolean flag = false;
		final Set<String> errorSet = new HashSet<>();
		try {
			addUserValidator.validate(userDetails, bindingResult);
			if (bindingResult.hasErrors()) {
				bindingResult.getFieldErrors().forEach(er -> {
					errorSet.add(getMessage(er.getCodes()[0]));
				});
				response.setErrors(errorSet);
				response.setStatus(HttpStatus.BAD_REQUEST.value());
				return response;
			}

			if (userRepository.existsByEmail(userDetails.getEmail())) {
				flag=true;
			}

			if(!flag) {
				// Creating new user's account
				if(userDetails.getMiddlename() == null || userDetails.getMiddlename().isEmpty() )
				{
					userDetails.setMiddlename("");
				}
				userDetails.setPassword(encoder.encode(userDetails.getPassword()));
				userDetails.setCreatedby(userProfileId);
				userDetails.setStatus(true);
				Set<String> strRoles = userDetails.getRole();
				List<Role> roles = new ArrayList<>();
				if (strRoles == null) {
					Role userRole = roleRepository.findByRolename("ROLE_EMPLOYEE")
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				} else {                    
					strRoles.forEach(role -> {
						switch (role) {
						case "EMPLOYEE":
							Role empRole = roleRepository.findByRolename("ROLE_EMPLOYEE")
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
							roles.add(empRole);
							break;

						default:
							Role userRole =roleRepository.findByRolename("ROLE_EMPLOYEE")
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
							roles.add(userRole);

						}
					});
				}


				User user=	userRepository.save(userDetails);
				response.setStatus(HttpStatus.OK.value());
				response.setMessage(ApplicationConstants.USER_ADD_SUCCESS);
			}
			else {
				errorSet.add(ApplicationConstants.USER_EMAIL_EXIST);
				response.setErrors(errorSet);
				response.setStatus(HttpStatus.BAD_REQUEST.value());
				response.setMessage(ApplicationConstants.USER_EMAIL_EXIST);
			}

		} catch (final Exception exception) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage(ApplicationConstants.ERROR_LABEL);
			response.addError("Please verify the request format. It contains invalid value for one of the field.");
			LOGGER.info(ApplicationConstants.METHOD_EXCEPTION_LABEL + exception);
		}
		LOGGER.info(ApplicationConstants.METHOD_EXIT_LABEL);
		return response;

	}

	@GetMapping(value = ApplicationURIConstants.LIST_OF_EMPLOYEE)
	@ResponseBody
	public AppResponse<Object> getEmployeeList() {
		LOGGER.info(ApplicationConstants.METHOD_ENTER_LABEL);
		final AppResponse<Object> response = new AppResponse<>();
		try {

			final List<UserDTO> riList = getServiceRegistry().getUserService().findAllEmployee();
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



}
