package com.lhy.boot.messageobserver.observer.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lhy.boot.messageobserver.Action;
import com.lhy.boot.messageobserver.Message;
import com.lhy.boot.messageobserver.annoation.Observer;
import com.lhy.boot.messageobserver.observavle.MessageObservable;
import com.lhy.boot.messageobserver.observer.MessageObserver;

@Observer(Action.userlogout)
@Component
public class UserLogoutMessageObserver implements MessageObserver {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void update(MessageObservable o, Message arg) {
		logger.info("UserLogoutMessageObserver:"+arg.getData());
	}

}
