package com.lhy.boot.messageobserver.observer;

import com.lhy.boot.messageobserver.Message;
import com.lhy.boot.messageobserver.observavle.MessageObservable;

public interface MessageObserver{
	
	 void update(MessageObservable o, Message message);
}
