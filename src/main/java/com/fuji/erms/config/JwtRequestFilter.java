package com.fuji.erms.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fuji.erms.serviceimpl.JwtUserDetailsServiceImpl;
import com.fuji.erms.util.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;

/**
 * @author Subhash
 *
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(JwtRequestFilter.class);
	@Autowired
	private JwtUserDetailsServiceImpl jwtUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	/**
	 * @author Subhash
	 * @return void
	 * @param HttpServletRequest
	 *            request, HttpServletResponse response, FilterChain chain
	 * 
	 *            This method is used to filter.
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");

		String userName = null;
		String jwtToken = null;

		if (requestTokenHeader != null
				&& requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				userName = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				LOGGER.error("Unable to get JWT Token");

			} catch (ExpiredJwtException e) {
				LOGGER.error("JWT Token has expired");

			}
		} else {
			LOGGER.warn("JWT Token does not begin with Bearer String");
		}

		if (userName != null && SecurityContextHolder.getContext()
				.getAuthentication() == null) {

			UserDetails userDetails = this.jwtUserDetailsService
					.loadUserByUsername(userName);

			if (Boolean.TRUE.equals(
					jwtTokenUtil.validateToken(jwtToken, userDetails))) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource()
								.buildDetails(request));

				SecurityContextHolder.getContext()
						.setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		chain.doFilter(request, response);
	}

}
