package com.demo.apigateway.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.apigateway.models.AppResponse;
import com.demo.apigateway.payload.request.LoginRequest;
import com.demo.apigateway.payload.response.JwtResponse;
import com.demo.apigateway.repository.UserRepository;
import com.demo.apigateway.security.jwt.JwtUtils;
import com.demo.apigateway.security.services.UserDetailsImpl;
import com.demo.apigateway.utils.ApplicationConstants;
import com.demo.apigateway.utils.ApplicationURIConstants;
import com.demo.apigateway.validators.LoginRequestValidator;


@CrossOrigin
@RestController
@RequestMapping(ApplicationURIConstants.BASE_API)
public class AuthController extends BaseController {

	private final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	LoginRequestValidator userLoginValidator;

	@Autowired
	UserRepository userRepository;

	@Autowired
	JwtUtils jwtUtils;

	
	@PostMapping(ApplicationURIConstants.USER_AUTHENTICATION)
	public AppResponse<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest,final BindingResult bindingResult) {

		System.err.println(loginRequest.getEmail());
		System.err.println(loginRequest.getPassword());


		LOGGER.info(ApplicationConstants.METHOD_CALLED_LABEL);
		final Set<String> errorSet = new HashSet<>();
		final AppResponse<JwtResponse> response = new AppResponse<>();

		try {
			userLoginValidator.validate(loginRequest, bindingResult);
			if (bindingResult.hasErrors()) {
				bindingResult.getFieldErrors().forEach(er -> {
					errorSet.add(getMessage(er.getCodes()[0]));
				});
				response.setErrors(errorSet);
				response.setStatus(HttpStatus.BAD_REQUEST.value());
				return response;
			}
			if (userRepository.isUserActive(loginRequest.getEmail())>0) {
				Authentication authentication = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

				SecurityContextHolder.getContext().setAuthentication(authentication);
				String jwt = jwtUtils.generateJwtToken(authentication);

				UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
				List<String> roles = userDetails.getAuthorities().stream()
						.map(item -> item.getAuthority())
						.collect(Collectors.toList());


				final JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getUserid(),
						userDetails.getUsername(), userDetails.getEmail(), roles);

				response.setStatus(HttpStatus.OK.value());
				response.setMessage(ApplicationConstants.SUCCESS_LABEL);
				response.setData(jwtResponse);		
			}
			else {
				errorSet.add(ApplicationConstants.INACTIVE_USER);
				response.setErrors(errorSet);
				response.setStatus(HttpStatus.BAD_REQUEST.value());
				response.setMessage(ApplicationConstants.INACTIVE_USER);
			}

		} catch (final Exception exception) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.addError(ApplicationConstants.INVALID_CREDENTIALS);
			response.setMessage(ApplicationConstants.ERROR_LABEL);

			LOGGER.error(ApplicationConstants.METHOD_EXCEPTION_LABEL + exception);
		}

		LOGGER.info(ApplicationConstants.METHOD_EXIT_LABEL);
		return response;


	}
	
	
//	@GetMapping("/logout")
//    public ResponseEntity<?> login(Principal principal){
//        if(principal == null){
//            //This should be ok http status because this will be used for logout path.
//            return ResponseEntity.ok(principal);
//        }
////        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
////        User user = userService.findByUsername(authenticationToken.getName());
////        user.setToken(jwtTokenProvider.generateToken(authenticationToken));
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
	
	
//	
//	  public static void main(String[] args) { BCryptPasswordEncoder
//	  bCryptPasswordEncoder = new BCryptPasswordEncoder();
//	  System.out.println(bCryptPasswordEncoder.encode("Ashish@123"));
//	  System.out.println(bCryptPasswordEncoder.encode("Hello#123")); }
	 
	
	
//	

}
