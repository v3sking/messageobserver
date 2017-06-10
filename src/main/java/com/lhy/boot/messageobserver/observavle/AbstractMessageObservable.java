package com.lhy.boot.messageobserver.observavle;

import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lhy.boot.messageobserver.Message;
import com.lhy.boot.messageobserver.observer.MessageObserver;

public abstract class AbstractMessageObservable implements MessageObservable {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected List<MessageObserver> messageObservers;
	
	public AbstractMessageObservable(){
		this.messageObservers = new Vector<MessageObserver>();
	}
	
	@Override
	public void notifyObservers() {
		this.notifyObservers(null);
	}
	
	@Override
	public abstract void notifyObservers(Message message);

	public void addObserver(MessageObserver messageObserver) {
		messageObservers.add(messageObserver);
	}

	@Override
	public void deleteObserver(MessageObserver messageObserver) {
		messageObservers.remove(messageObserver);
	}
	@Override
	public List<MessageObserver> getObservers() {
		return messageObservers;
	}
	
	@Override
	public int countObservers() {
		return messageObservers.size();
	}

}
