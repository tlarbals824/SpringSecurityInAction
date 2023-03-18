package com.sim.ch05ex2.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableAsync
public class ProjectConfig{

	/**
	 * 스레드 안전하지 않다. 따라서 개발자가 동시 접근에 대해 해결해야 한다.
	 */
	@Bean
	public InitializingBean initializingBean(){
		return ()-> SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
	}

}
