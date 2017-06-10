package com.lhy.boot.messageobserver.observavle;

import com.lhy.boot.messageobserver.Message;
import com.lhy.boot.messageobserver.observer.MessageObserver;

public class SyncMessageObservable extends AbstractMessageObservable {

	@Override
	public void notifyObservers(Message message) {
		for (MessageObserver messageObserver : messageObservers) {
			messageObserver.update(this, message);
		}
	}

}
