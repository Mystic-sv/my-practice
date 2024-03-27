package com.techymeet.mytodo.securityconfig;

import java.util.Date;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.techymeet.mytodo.service.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtTokenUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtils.class);

	@Value("${jwtsecret}")
	private String jwtSecret;
	
	
	private int jwtExpirationMs=86400000;

	public String generateJwtToken(Authentication authentication) {

		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
		SecretKey secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
		return Jwts.builder()
				.setSubject((userPrincipal.getUsername()))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(secret,SignatureAlgorithm.HS512)
				.compact();
	}

	public String getUserNameFromJwtToken(String token) {
		SecretKey secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
		return Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			SecretKey secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
			Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(authToken);
			return true;
			
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}
}
