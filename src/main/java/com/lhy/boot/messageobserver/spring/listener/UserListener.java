package com.lhy.boot.messageobserver.spring.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.lhy.boot.messageobserver.spring.event.UserLoginEvent;
@Component
public class UserListener implements ApplicationListener<UserLoginEvent> {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void onApplicationEvent(UserLoginEvent event) {
		logger.info("user loging"+event.getSource());
	}

}
