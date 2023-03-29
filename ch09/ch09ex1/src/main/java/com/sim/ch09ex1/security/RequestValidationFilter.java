package com.sim.ch09ex1.security;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestValidationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
		IOException,
		ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;

		String requestId = httpServletRequest.getHeader("Request-Id");

		if(Objects.isNull(requestId)||requestId.isBlank()){
			httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		chain.doFilter(request, response);
	}
}
