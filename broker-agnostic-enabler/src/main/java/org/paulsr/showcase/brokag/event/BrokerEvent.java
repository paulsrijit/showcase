package org.paulsr.showcase.brokag.event;

import org.springframework.context.ApplicationEvent;

public class BrokerEvent extends ApplicationEvent {

	private String message;

	public BrokerEvent(Object source, String message) {
		super(source);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "BrokerEvent [message=" + message + "]";
	}
}
