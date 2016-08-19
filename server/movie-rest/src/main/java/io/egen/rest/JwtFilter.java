package io.egen.rest;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import io.egen.rest.entity.UserInfo;
import io.egen.rest.exception.NoAuthHeaderFound;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Component
public class JwtFilter {
	
	public String createToken(UserInfo existing){
		return (Jwts.builder()
				.setSubject(existing.getUserName())
				.claim("role", existing)
				.setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey")
				.compact());
	}

	public String getUserName(String authHeader){
		String token = authHeader.substring(7);
		
		Claims claims = Jwts.parser()
				.setSigningKey("secretkey")
				.parseClaimsJws(token)
				.getBody();
		
		
		if(claims == null){
			throw new NoAuthHeaderFound("Invalid Request: No Authorization");
		}
		
		return (String) ((Map<String, Object>) claims.get("role")).get("userName");
	}

}
