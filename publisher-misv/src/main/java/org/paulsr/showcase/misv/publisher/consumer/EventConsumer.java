package org.paulsr.showcase.misv.publisher.consumer;

import org.paulsr.showcase.misv.publisher.event.SampleEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer implements ApplicationListener<SampleEvent> {

	private static final Logger LOG = LoggerFactory.getLogger(EventConsumer.class);

	@Override
	public void onApplicationEvent(SampleEvent event) {
		LOG.info("Received sample event {}", event);

	}

}
