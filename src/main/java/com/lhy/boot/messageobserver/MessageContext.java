package com.lhy.boot.messageobserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.lhy.boot.messageobserver.annoation.Observer;
import com.lhy.boot.messageobserver.observavle.AsyncMessageObservable;
import com.lhy.boot.messageobserver.observavle.MessageObservable;
import com.lhy.boot.messageobserver.observavle.SyncMessageObservable;
import com.lhy.boot.messageobserver.observer.MessageObserver;

@Component
public class MessageContext implements ApplicationContextAware {

	/**
	 * 每一个动作有多个观察者观察
	 */
	private Map<Action, List<MessageObserver>> syncMessageObserverMap = new HashMap<Action, List<MessageObserver>>();

	/**
	 * 每一个动作有多个观察者观察
	 */
	private Map<Action, List<MessageObserver>> asyncMessageObserverMap = new HashMap<Action, List<MessageObserver>>();

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// 得到所有动作
		Action[] values = Action.values();

		Map<String, Object> beans = applicationContext.getBeansWithAnnotation(Observer.class);
		for (Action action : values) {
			List<MessageObserver> syncMessageObserverList = new ArrayList<MessageObserver>();
			List<MessageObserver> asyncMessageObserverList = new ArrayList<MessageObserver>();
			for (String beanName : beans.keySet()) {
				Object object = beans.get(beanName);
				if (!(object instanceof MessageObserver)) {
					continue;
				}
				MessageObserver messageObserver = (MessageObserver) object;
				Observer annotation = messageObserver.getClass().getAnnotation(Observer.class);
				Action[] actions = getAction(messageObserver);
				for (Action action1 : actions) {
					if (action.equals(action1)) {
						if (annotation.async()) {
							asyncMessageObserverList.add(messageObserver);
						} else {
							syncMessageObserverList.add(messageObserver);
						}
					}
				}
			}
			if (!syncMessageObserverList.isEmpty()) {
				syncMessageObserverMap.put(action, syncMessageObserverList);
			}
			if (!asyncMessageObserverList.isEmpty()) {
				asyncMessageObserverMap.put(action, asyncMessageObserverList);
			}
		}
	}

	public void notice(Message message) {
		this.notice(message, new SyncMessageObservable(), syncMessageObserverMap);
		this.notice(message, new AsyncMessageObservable(), asyncMessageObserverMap);
	}

	private void notice(Message message, MessageObservable messageObservable, Map<Action, List<MessageObserver>> map) {
		for (Action action : map.keySet()) {
			if (message.getAction().equals(action)) {
				List<MessageObserver> list = map.get(action);
				for (MessageObserver messageObserver : list) {
					messageObservable.addObserver(messageObserver);
				}
			}
		}
		messageObservable.notifyObservers(message);
	}

	private Action[] getAction(Object object) {
		Observer annotation = object.getClass().getAnnotation(Observer.class);
		Action[] actions = annotation.value();
		if (actions == null || actions.length == 0) {
			actions = annotation.action();
		}
		if (actions == null || actions.length == 0) {
			throw new NullPointerException("messageObserver:" + object + "无action");
		}
		return actions;
	}

}
