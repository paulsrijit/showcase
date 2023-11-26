package org.paulsr.showcase.brokag.aop;

import java.lang.annotation.Annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.paulsr.showcase.brokag.event.BrokerEvent;
import org.paulsr.showcase.brokag.kafka.producer.BrokAgProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class BrokAgAdvice {

	private static final Logger LOG = LoggerFactory.getLogger(BrokAgAdvice.class);
	
	@Autowired
	BrokAgProducer brokAgProd;

	public BrokAgAdvice() {
		super();
		LOG.info("Init BrokAgAdvice");
	}

	@Around("execution(void org.springframework.context.ApplicationEventPublisher.publishEvent(org.springframework.context.ApplicationEvent))")
	public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
		
		LOG.info("Caught spring event from Around Advice for method {}", pjp.getSignature().toLongString());

		Object[] args = pjp.getArgs();
		if(args != null && args.length > 0 && args[0] != null && args[0] instanceof BrokerEvent) {
			BrokerEvent evt = (BrokerEvent) args[0];
			
				brokAgProd.sendMessage(evt.getMessage(), "PAULSR_DEMO_T001");
			
		}
		LOG.info("Caught spring event from Around Advice with args {}", pjp.getArgs());
		return pjp.proceed(pjp.getArgs());
	}

//	private boolean isBrokerEvent(ApplicationEvent evt) {
//		Annotation[] annotations = evt.getClass().getgetAnnotations();
//		if(annotations != null && annotations.length > 0) {
//			for (Annotation ant : annotations) {
//				if(ant.getClass().equals(BrokerEvent.class)) {
//					return true;
//				}
//			}
//		}
//		return false;
		
//		}
		
//	}
}
