package com.fuji.erms.config;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Subhash
 *
 */
@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(WebSecurityConfig.class);
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		LOGGER.info("WebSecurityConfig:  configureGlobal method :Started");
		auth.userDetailsService(jwtUserDetailsService)
				.passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		LOGGER.info("WebSecurityConfig:  passwordEncoder method :Started");
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		LOGGER.info(
				"WebSecurityConfig:  authenticationManagerBean method :Started");
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		LOGGER.info("WebSecurityConfig:  configure method :Started");

		httpSecurity.csrf().disable().cors().disable()

				.authorizeRequests().antMatchers("/authenticate").permitAll()
				.antMatchers("/register").permitAll().antMatchers("/emc/*")
				.permitAll().antMatchers("/rmc/*").permitAll().anyRequest()
				.authenticated().and().exceptionHandling()
				.authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.addFilterBefore(jwtRequestFilter,
				UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}
}