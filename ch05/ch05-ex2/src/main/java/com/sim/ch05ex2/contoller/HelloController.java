package com.sim.ch05ex2.contoller;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/hello")
	public String hello(Authentication authentication) {
		// SecurityContext context = SecurityContextHolder.getContext();
		// Authentication authentication = context.getAuthentication();

		return "Hello, " + authentication.getName();
	}

	/**
	 * SecurityContextHolder 전략이 MODE_THREADLOCAL인 경우 스레드가 다른 경우에는 SecurityContext가 공유되지 않는다.
	 * java.lang.NullPointerException:
	 * Cannot invoke "org.springframework.security.core.Authentication.getName()" because the return value of "org.springframework.security.core.context.SecurityContext.getAuthentication()" is null
	 */
	@GetMapping("/bye")
	@Async
	public void goodbye() {
		SecurityContext context = SecurityContextHolder.getContext();
		String username = context.getAuthentication().getName();
	}

	@GetMapping("/ciao")
	public String ciao() throws Exception {
		Callable<String> task = () -> {
			SecurityContext context = SecurityContextHolder.getContext();
			return context.getAuthentication().getName();
		};

		ExecutorService executorService = Executors.newCachedThreadPool();
		try {
			DelegatingSecurityContextCallable<String> contextTask =
				new DelegatingSecurityContextCallable<>(task);
			return "Ciao, " + executorService.submit(contextTask).get();
		} finally {
			executorService.shutdown();
		}
	}

	@GetMapping("/hola")
	public String hola() throws Exception {
		Callable<String> task = () -> {
			SecurityContext context = SecurityContextHolder.getContext();
			return context.getAuthentication().getName();
		};

		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService = new DelegatingSecurityContextExecutorService(executorService);
		try {
			return "Hola, " + executorService.submit(task).get();
		} finally {
			executorService.shutdown();
		}
	}

}
