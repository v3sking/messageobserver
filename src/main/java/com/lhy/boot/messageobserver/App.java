package com.lhy.boot.messageobserver;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 如果不配置scanBasePackages默认扫描当前包目录
 * @author luanhy
 *
 */
@SpringBootApplication
@EnableAsync
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
