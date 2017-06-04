package com.lhy.boot.messageobserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)  
public class BaseTest {

	@Autowired
	private MessageManager messageObserverManager;
	
	@Test
	public void testUserLogin() {
		Message msg = new Message("userlogin", "haha userlogin");
		System.out.println("userlogin event");
		messageObserverManager.notice(msg);
		
		Message msg1 = new Message("userlogout", "haha userlogout");
		System.out.println("userlogout event");
		messageObserverManager.notice(msg1);
	}
	
}
