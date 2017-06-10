package com.lhy.boot.messageobserver.observavle;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.lhy.boot.messageobserver.Message;
import com.lhy.boot.messageobserver.observer.MessageObserver;

public class AsyncMessageObservable extends AbstractMessageObservable {
	
	private Executor executor;
	
	public AsyncMessageObservable(){
		this(5);
	}
	
	public AsyncMessageObservable(int threads){
		executor = Executors.newFixedThreadPool(threads);
	}
	
	@Override
	public void notifyObservers(Message message) {
		MessageObservable messageObservable = this;
		
		for (MessageObserver messageObserver : messageObservers) {
//			executor.execute(new Runnable() {
//				@Override
//				public void run() {
//					messageObserver.update(messageObservable, message);
//				}
//			});
			executor.execute(()->{
				logger.info("notice:{},message:{}",messageObservable,message);
				messageObserver.update(messageObservable, message);
			});
		}
	}

}
