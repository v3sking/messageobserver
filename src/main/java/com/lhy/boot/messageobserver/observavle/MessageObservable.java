package com.lhy.boot.messageobserver.observavle;

import java.util.List;

import com.lhy.boot.messageobserver.Message;
import com.lhy.boot.messageobserver.observer.MessageObserver;

public interface MessageObservable {
	
	void notifyObservers();
	
	 void notifyObservers(Message message);
	
	 void addObserver(MessageObserver messageObserver);
	
	 void deleteObserver(MessageObserver messageObserver);
	
	 List<MessageObserver> getObservers();
	
	int countObservers();
	
}
