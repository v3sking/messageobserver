package com.lhy.boot.messageobserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageHelper{
	
	@Autowired
	private MessageContext  messageContext;

	public void notice(Action action, Object data){
		Message msg = new Message(action, data);
		messageContext.notice(msg);
	}
	
}
