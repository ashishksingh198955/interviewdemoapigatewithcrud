package com.demo.apigateway;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.demo.apigateway.filters.ErrorFilter;
import com.demo.apigateway.filters.PostFilter;
import com.demo.apigateway.filters.PreFilter;
import com.demo.apigateway.filters.RouteFilter;
import com.demo.apigateway.models.User;
import com.demo.apigateway.repository.UserRepository;

import brave.sampler.Sampler;
import javassist.expr.NewArray;

@EnableZuulProxy //act as Zuul proxy
@EnableEurekaServer//for making this application as eureka server
@SpringBootApplication
public class ApiGatewayApplication {

	private static final Logger LOG = Logger.getLogger(ApiGatewayApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);

		System.err.println("Zuul API gatway server is calling");
		LOG.log(Level.INFO, "Zuul API gatway server is calling");
	}

	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}


	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}
	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}
	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}
	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}
	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {
			if(!userRepository.existsByEmail("assing231@gmail.com")) {
				User userObj=new User();
				userObj.setFirstname("Ashish");
				userObj.setMiddlename("Kumar");
				userObj.setLastname("Singh");
				userObj.setEmail("assing231@gmail.com");
				userObj.setPassword("$2a$10$JrCai0ZJwXQCqBn5wU3qg.eA8/w3chChB8npDPjOhz.t39o0lbVp.");
				userObj.setStatus(true);
				userObj.setGender("male");
				userObj.setDivision("IT");
				userObj.setPosition("Sr.Software Engineer");
				userObj.setContact("8056723410");
				userObj.setCreatedby(1);			
			    userRepository.save(userObj);
			}
			else {
				System.err.println("Found Duplicate Email");
			}
		};

	}
}
