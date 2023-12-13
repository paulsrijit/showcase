package org.paulsr.showcase.brokag.kafka.producer;

import org.paulsr.showcase.brokag.event.BrokerEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class BrokAgProducer implements ApplicationListener<BrokerEvent> {

	private static final Logger LOG = LoggerFactory.getLogger(BrokAgProducer.class);

	public BrokAgProducer() {
		super();
		LOG.info("*******************Creating BrokAgProducer object.*******************");
	}

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String msg, String topicName) {
		LOG.info("*******************Publishing message: {} to topic: {}", msg, topicName);
		kafkaTemplate.send(topicName, msg);
	}

	@Override
	public void onApplicationEvent(BrokerEvent event) {
		LOG.info("*******************Caught Broker Event: {}", event);
		sendMessage(event.getMessage(), "PAULSR_DEMO_T001");
	}
}
