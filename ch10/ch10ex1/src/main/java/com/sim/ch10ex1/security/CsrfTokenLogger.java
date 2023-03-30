package com.sim.ch10ex1.security;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.web.csrf.CsrfToken;

public class CsrfTokenLogger implements Filter {

	private Logger logger = Logger.getLogger(CsrfTokenLogger.class.getName());

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
		IOException,
		ServletException {
		CsrfToken csrf = (CsrfToken)request.getAttribute("_csrf");

		logger.info("CSRF TOKEN "+csrf.getToken());

		chain.doFilter(request,response);
	}
}
