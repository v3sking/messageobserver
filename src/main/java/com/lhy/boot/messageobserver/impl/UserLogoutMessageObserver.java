package com.lhy.boot.messageobserver.impl;

import java.util.Observable;

import org.springframework.stereotype.Component;

import com.lhy.boot.messageobserver.Message;
import com.lhy.boot.messageobserver.MessageObserver;
import com.lhy.boot.messageobserver.annoation.Observer;

@Observer({"userlogin","userlogout"})
@Component
public class UserLogoutMessageObserver implements MessageObserver {

	@Override
	public void update(Observable o, Object arg) {
		Message msg = (Message)arg;
		System.out.println("UserLogoutMessageObserver:"+msg.getData());
	}

}
