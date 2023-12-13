package org.paulsr.showcase.misv.publisher.event;

import org.paulsr.showcase.brokag.annotation.BrokerEvent;
import org.springframework.context.ApplicationEvent;

@BrokerEvent
public class SampleEvent extends ApplicationEvent {

	private String message;

	public SampleEvent(Object source, String message) {
		super(source);
		this.message = message;
	}

	@Override
	public String toString() {
		return "SampleEvent [message=" + message + "]";
	}

}
