package com.demo.apigateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.demo.apigateway.security.jwt.AuthEntryPointJwt;
import com.demo.apigateway.security.jwt.AuthTokenFilter;
import com.demo.apigateway.security.services.UserDetailsServiceImpl;

import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeRequests().antMatchers("/api/auth/**").permitAll()
		.antMatchers(HttpMethod.OPTIONS, "/**")
		.permitAll()
		.antMatchers("/department/api/adddepartment/**").hasRole("EMPLOYEE")
		.antMatchers("/department/api/getlistofdepartment/**").hasRole("EMPLOYEE")
		.antMatchers("/department/api/updatedepartment/**").hasRole("EMPLOYEE")
		.antMatchers("/department/api/viewdepartment/**").hasRole("EMPLOYEE")
		.antMatchers("/department/api/deletedepartment/**").hasRole("EMPLOYEE")
		.antMatchers("/category/api/addcategory/**").hasRole("EMPLOYEE")
		.antMatchers("/category/api/getlistofcategory/**").hasRole("EMPLOYEE")
		.antMatchers("/category/api/updatecategory/**").hasRole("EMPLOYEE")
		.antMatchers("/category/api/viewcategory/**").hasRole("EMPLOYEE")
		.antMatchers("/category/api/deletecategory/**").hasRole("EMPLOYEE")
		.antMatchers("/api/logout/**").permitAll()
		.antMatchers("/employee/api/addemployee/**").hasRole("EMPLOYEE")
		.antMatchers("/employee/api/getlistofemployee/**").hasRole("EMPLOYEE")
		.anyRequest().authenticated();
		http.addFilterAfter(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}

}
