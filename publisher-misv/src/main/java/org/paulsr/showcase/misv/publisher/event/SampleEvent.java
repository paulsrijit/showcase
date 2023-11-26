package org.paulsr.showcase.misv.publisher.event;

import org.paulsr.showcase.brokag.event.BrokerEvent;

public class SampleEvent extends BrokerEvent {

	private String message;

	public SampleEvent(Object source, String message) {
		super(source, message);
	}

}
