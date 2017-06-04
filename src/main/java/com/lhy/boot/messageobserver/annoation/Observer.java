package com.lhy.boot.messageobserver.annoation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Observer {
	
	@AliasFor("action")
	String[] value() default {};
	
	@AliasFor("value")
	String[] action() default {};
	
	boolean async() default false;
	
}
