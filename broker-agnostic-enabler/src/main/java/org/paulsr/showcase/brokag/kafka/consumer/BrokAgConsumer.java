package org.paulsr.showcase.brokag.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class BrokAgConsumer {
	@KafkaListener(topics = "PAULSR_DEMO_T001", groupId = "kafka-cons-grp-001")
	public void listenGroupFoo(String message) {
		System.out.println("Received Message: " + message);
	}
}
