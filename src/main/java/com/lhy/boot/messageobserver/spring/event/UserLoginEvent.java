package com.lhy.boot.messageobserver.spring.event;

import org.springframework.context.ApplicationEvent;

public class UserLoginEvent extends ApplicationEvent {

	public UserLoginEvent(String source) {
		super(source);
	}

}
