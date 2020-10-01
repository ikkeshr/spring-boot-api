package com.springboot.demo.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springboot.demo.utils.JwtUtil;
import com.springboot.demo.services.impl.UserServiceImpl;

/**
 * <p>
 * {@link JwtRequestFilter} extends the Spring Web Filter {@link OncePerRequestFilter}
 * class. For any incoming request, this Filter class gets executed. It checks
 * if the request has a valid JWT token. If it has a valid JWT Token, then it
 * sets the authentication in context to specify that the current user is
 * authenticated.
 * </p>
 * 
 * @author ikkesh.ramanna
 **/

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	/**
	 * <p>
	 * {@link UserServiceImpl} instance.
	 * </p>
	 */
	@Autowired
	private UserServiceImpl userService;

	/**
	 * <p>
	 * {@link JwtUtil} instance.
	 * </p>
	 */
	@Autowired
	private JwtUtil jwtUtil;

	/**
	 * <p>
	 * Override {@link OncePerRequest} doFilterInternal method
	 * Defines the validation of each request to secure endpoints of the API
	 * </p>
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String authorizationHeader = request.getHeader("Authorization");

		String username = null;
		String jwt = null;

		// JWT Token is in the form "Bearer token". Remove Bearer word and get
		// only the Token
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			
			try {
				username = jwtUtil.getUsernameFromToken(jwt);
			} catch (Exception e) {
				System.out.println("****JWT EXCEPTION*****");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}

		// Once we get the token validate it.
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.userService.loadUserByUsername(username);

			// if token is valid configure Spring Security to manually set
			// authentication
			if (jwtUtil.validateToken(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the
				// Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}

		chain.doFilter(request, response);

	}

}
