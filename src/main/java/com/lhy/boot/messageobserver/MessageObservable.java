package com.lhy.boot.messageobserver;

import java.util.Observable;

import org.springframework.stereotype.Component;

@Component
public class MessageObservable extends Observable{
	
	@Override
	public void notifyObservers(Object message) {
		super.setChanged();
		super.notifyObservers(message);
	}
	
	public void notifyObserversAsync(Object message) {
	}
	
	
	
}
