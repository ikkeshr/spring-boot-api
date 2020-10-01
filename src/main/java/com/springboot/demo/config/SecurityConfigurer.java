package com.springboot.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springboot.demo.filters.JwtRequestFilter;
import com.springboot.demo.services.impl.UserServiceImpl;

/**
 * This class extends the WebSecurityConfigurerAdapter. 
 * This is a convenience class that allows customization to both WebSecurity and HttpSecurity.
 * 
 * @author ikkesh.ramanna
 */
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
	
	/**
	 * <p>
	 * {@link UserServiceImpl} instance.
	 * </p>
	 */
	@Autowired
	private UserServiceImpl userDetailsService;

	/**
	 * <p>
	 * {@link JwtRequestFilter} instance.
	 * </p>
	 */
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	/**
	 * <p>
	 * Specifies the user authentication define in the {@link UserServiceImpl}
	 * and the password encoder used to verify passwords.
	 * </p>
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	/**
	 * <p>
	 * Configures HTTP security
	 * </p>
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()

				// dont authenticate these particular request
				.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/authenticate").permitAll()
				.antMatchers(HttpMethod.GET, "/dictionary/**").permitAll()
				.antMatchers(HttpMethod.POST, "/user").permitAll()
				
				// all other requests need to be authenticated
				.anyRequest().authenticated().and()

				// make sure we use stateless session; session won't be used to
				// store user's state.
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		

		// Add a filter to validate the tokens with every request
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
		// Enable CORS
		http.cors();
	}
	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//	    web.ignoring().antMatchers("/authenticate");
//	    web.ignoring().antMatchers(HttpMethod.GET, "/dictionary/**");
//	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}
	
	/**
	 * <p>
	 * Defines {@link PasswordEncoder} to use
	 * to check if a plain-text password matched an encrypted password.
	 * </p>
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
