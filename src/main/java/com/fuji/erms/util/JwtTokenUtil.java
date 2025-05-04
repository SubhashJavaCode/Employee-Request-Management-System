package com.fuji.erms.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Subhash This class contain Util methods for JWT control class
 */
@Component
public class JwtTokenUtil implements Serializable {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(JwtTokenUtil.class);

	private static final long serialVersionUID = -2550185165626007488L;

	@Value("${jwt.secret}")
	private String secret1;

	private String secret = "Hrushal";
/*
	@Value("${jwt.tokenValidity:15}")
	private String jwtTokenValidity;
*/
	private String jwtTokenValidity = "15";

	public String getUsernameFromToken(String token) {
		LOGGER.info("JwtTokenUtil: getUsernameFromToken: Method Started");
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		LOGGER.info("JwtTokenUtil: getExpirationDateFromToken: Method Started");
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token,
			Function<Claims, T> claimsResolver) {
		LOGGER.info("JwtTokenUtil: getClaimFromToken: Method Started");
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		LOGGER.info("JwtTokenUtil: getAllClaimsFromToken: Method Started");
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
				.getBody();
	}

	private Boolean isTokenExpired(String token) {
		LOGGER.info("JwtTokenUtil: isTokenExpired: Method Started");
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken(UserDetails userDetails) {
		LOGGER.info("JwtTokenUtil: generateToken: Method Started");
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	public String doGenerateToken(Map<String, Object> claims, String subject) {
		LOGGER.info("JwtTokenUtil: doGenerateToken: Method Started");

		return Jwts.builder().setClaims(claims).setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()
						+ (Integer.valueOf(jwtTokenValidity) * 60000)))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		LOGGER.info("JwtTokenUtil: validateToken: Method Started");
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername())
				&& !isTokenExpired(token));
	}
}