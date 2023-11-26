package org.paulsr.showcase.brokag.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@Configuration
public class AopConfig {
	
	private static final Logger LOG = LoggerFactory.getLogger(AopConfig.class);
	
	@Bean
	public BrokAgAdvice brokAgAdvice() {
		LOG.info("Creating brokAgAdvice bean");
		return new BrokAgAdvice();
	}
	
}
