package com.lhy.boot.messageobserver.spring.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.lhy.boot.messageobserver.spring.event.UserLoginEvent;

@Component
public class UserGroupListener implements SmartApplicationListener {
	
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	@Async
	public void onApplicationEvent(ApplicationEvent event) {
		logger.info("user loging "+event.getSource());
	}

	@Override
	public int getOrder() {
		return 1;
	}

	@Override
	public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
		return eventType == UserLoginEvent.class;
	}

	@Override
	public boolean supportsSourceType(Class<?> sourceType) {
		return sourceType == String.class;
	}

}
