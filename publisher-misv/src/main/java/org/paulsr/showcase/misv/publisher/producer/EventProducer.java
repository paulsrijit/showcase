package org.paulsr.showcase.misv.publisher.producer;

import org.paulsr.showcase.brokag.kafka.producer.BrokAgProducer;
import org.paulsr.showcase.misv.publisher.event.SampleEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventProducer {

	private static final Logger LOG = LoggerFactory.getLogger(EventProducer.class);

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Autowired
	private BrokAgProducer baProducer;

	public void publishSpringEvent(final String message) {
		LOG.info("Publishing spring event {}", message);
		SampleEvent customSpringEvent = new SampleEvent(this, message);
		eventPublisher.publishEvent(customSpringEvent);
	}

	public void publishBrokerEvent(final String message, final String topic) {
		LOG.info("Publishing broker event destination: {}, message: {}", topic, message);
		baProducer.sendMessage(message, topic);
	}

}
