package com.lhy.boot.messageobserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.lhy.boot.messageobserver.annoation.Observer;

@Component
public class MessageManager implements ApplicationContextAware{
	
	private List<MessageObserver> syncMessageObservers = new ArrayList<MessageObserver>();
	
	private List<MessageObserver> asyncMessageObservers = new ArrayList<MessageObserver>();

	private final ExecutorService ayncThreadPool = Executors.newFixedThreadPool(5);

	@Autowired
	private MessageObservable observable;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		Map<String, Object> beans = applicationContext.getBeansWithAnnotation(Observer.class);
		for (String beanName : beans.keySet()) {
			Object object = beans.get(beanName);
			if(!(object instanceof MessageObserver)){
				continue;
			}
			Observer annotation = object.getClass().getAnnotation(Observer.class);
			if (annotation.async()) {
				asyncMessageObservers.add((MessageObserver)object);
			}else{
				syncMessageObservers.add((MessageObserver)object);
			}
		}
	}
	
	public void notice(Message message){
		observable.deleteObservers();
		String action = message.getAction();
		notifyAsync(action);
		notifySync(action);
	}
	
	private void notifyAsync(String action){
		for (MessageObserver messageObserver : syncMessageObservers) {
			String[] actions =getAction(messageObserver);
			for (String a : actions) {
				if (action.equals(a)) {
					ayncThreadPool.execute(new Runnable() {
						@Override
						public void run() {
							//observable.notifyObservers();
						}
					});
					continue;
				}
			}
		}
	
		
	}
	
	private void notifySync(String action){
		observable.deleteObservers();
		for (MessageObserver messageObserver : asyncMessageObservers) {
			String[] actions = getAction(messageObserver);
			for (String a : actions) {
				if (action.equals(a)) {
					//observable.notifyObservers();
					continue;
				}
			}
		}
	}
	
	private String[] getAction(Object object){
		Observer annotation = object.getClass().getAnnotation(Observer.class);
		String[] actions = annotation.value();
		if (actions== null|| actions.length == 0) {
			actions = annotation.action();
		}
		if (actions== null|| actions.length == 0) {
			throw new NullPointerException("messageObserver:"+object+"无action");
		}
		return actions;
	}
	
	
}
