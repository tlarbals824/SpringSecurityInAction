package com.sim.ch09ex1.security;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class AuthenticationLoggingFilter implements Filter {

	private final Logger logger = Logger.getLogger(AuthenticationLoggingFilter.class.getName());

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
		IOException,
		ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;

		String requestId = httpRequest.getHeader("Request-Id");

		logger.info("Successfully authenticated Request-Id: " + requestId);

		chain.doFilter(request, response);
	}
}
