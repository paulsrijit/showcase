package org.paulsr.showcase.brokag.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class BrokAgProducer {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String msg, String topicName) {
		kafkaTemplate.send(topicName, msg);
	}
}
