package com.demo.service.users.jwtutils;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtUtils {

	@Value("${efbs.service.users.app.jwtSecret}")
	private String jwtSecret;

	public String getUserEmailFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public Claims getAllClaimsFromToken(String token) {
	    return Jwts.parser()
	            .setSigningKey(jwtSecret)
	            .parseClaimsJws(token)
	            .getBody();
	}
}
