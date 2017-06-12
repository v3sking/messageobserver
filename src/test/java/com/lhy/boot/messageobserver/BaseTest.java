package com.lhy.boot.messageobserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lhy.boot.messageobserver.spring.event.UserLoginEvent;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)  
public class BaseTest {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MessageContext messageContext;
	
	@Test
	public void testUserLogin() {
		
		Message msg = new Message(Action.userlogin, "haha userlogin");
		logger.info("userlogin event");
		messageContext.notice(msg);
		
		Message msg1 = new Message(Action.userlogout, "haha userlogout");
		logger.info("userlogout event");
		messageContext.notice(msg1);
		
		Message msg2 = new Message(Action.userRegister, "haha userRegister");
		logger.info("userRegister event");
		messageContext.notice(msg2);
		
		Message msg3 = new Message(Action.userUnRegister, "haha userUnRegister");
		logger.info("userUnRegister event");
		messageContext.notice(msg3);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Test
	public void testEvent(){
		applicationContext.publishEvent(new UserLoginEvent("zhagsan"));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
