package org.paulsr.showcase.brokag.aop;

import java.lang.annotation.Annotation;
import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.paulsr.showcase.brokag.event.BrokerEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;


@Aspect
public class BrokAgAdvice {

	private static final Logger LOG = LoggerFactory.getLogger(BrokAgAdvice.class);	
	
	private ApplicationEventPublisher eventPublisher;
	
	public BrokAgAdvice() {
		super();
		LOG.info("*******************Init BrokAgAdvice *******************");
	}
	
	public void setEventPublisher(ApplicationEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

	@Around("execution(void org.springframework.context.ApplicationEventPublisher.publishEvent(org.springframework.context.ApplicationEvent))")
	public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
		
		LOG.info("Caught spring event from Around Advice for method {}", pjp.getSignature().toLongString());

		Object[] args = pjp.getArgs();
		
		if (args != null 
				&& args.length > 0 
				&& args[0] != null) {
			
			Object argObj = args[0];
			
			if(argObj instanceof BrokerEvent) {
				LOG.info("Inside BrokerEvent IF block {}", argObj);
			}
			
			if(argObj instanceof ApplicationEvent) {
				LOG.info("Inside ApplicationEvent IF block {}", argObj);
			}
			
			if(isEligibleForBroker((ApplicationEvent) argObj)) {
				LOG.info("Inside isEligibleForBroker IF block {}", argObj);
			}
			
			if(!(argObj instanceof BrokerEvent) 
					&& argObj instanceof ApplicationEvent 
					&& isEligibleForBroker((ApplicationEvent) argObj)) {
				
				ApplicationEvent springEvt = (ApplicationEvent) argObj;
				
				BrokerEvent evt = new BrokerEvent(springEvt.getSource(), springEvt.toString());
				
				LOG.info("Republishing spring event: {} as Broker Event: {}", springEvt, evt);
				//eventPublisher.publishEvent(evt);	
				args[0] = evt;
			}
			
		}
		
		LOG.info("Caught spring event from Around Advice with args {}", pjp.getArgs());
		return pjp.proceed(args);
	}

	private boolean isEligibleForBroker(ApplicationEvent evt) {
		Annotation[] annotations = evt.getClass().getAnnotations();
		
		LOG.info("All annotations: {}", Arrays.asList(annotations));
		
		if (annotations != null && annotations.length > 0) {
			for (Annotation ant : annotations) {
				LOG.info("Single Annotation: {}, Class: {}", ant, ant.getClass());
				if (ant instanceof org.paulsr.showcase.brokag.annotation.BrokerEvent) {
					LOG.info("Annotation: {} check success", ant);
					return true;
				}
			}
		}
		return false;

	}
}
