package org.paulsr.showcase.misv.publisher.controller;

import org.paulsr.showcase.misv.publisher.producer.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublisherMisvController {

	@Autowired
	private EventProducer producer;

	@PostMapping("/produce-spring-event")
	public String produceSpringEvent(@RequestBody String message) {
		producer.publishSpringEvent(message);
		return "Published spring event: " + message;
	}

	@PostMapping("/produce-broker-event")
	public String produceBrokerEvent(@RequestBody String message) {
		producer.publishBrokerEvent(message, "PAULSR_DEMO_T001");
		return "Published broker event: " + message;
	}

}
