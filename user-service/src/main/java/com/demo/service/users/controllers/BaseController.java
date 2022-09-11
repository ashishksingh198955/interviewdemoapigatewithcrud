package com.demo.service.users.controllers;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;

import com.demo.service.users.utils.ServiceRegistry;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The <code>AppConfig</code> class acts as a driver class for <b>This</b> application.
 *
 * @author Ashish Kumar Singh
 *
 */

@Controller
public class BaseController {

	@Autowired
	private ServiceRegistry serviceRegistry;

	@Autowired
	private MessageSource messageSource;

	private ObjectMapper objectMapper;

	protected ObjectMapper getObjectMapper() {

		if (objectMapper == null) {
			objectMapper = new ObjectMapper().configure(DeserializationFeature.ACCEPT_FLOAT_AS_INT, false);
		}
		return objectMapper;
	}

	protected String getMessage(final String key) {

		System.err.println("KEY:" + key);
		System.err.println("test:" + getMessageSource().getMessage(key, null, Locale.getDefault()));
		return getMessageSource().getMessage(key, null, Locale.getDefault());
	}

	public MessageSource getMessageSource() {

		return messageSource;
	}

	public ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}	
}
