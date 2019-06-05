package net.yto.newmysql.rabbitMQ.spring;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.yto.newmysql.utils.PublishUtil;

public class TestMain {
	
	
	private final static String EXCHANGE_NAME = "fanoutExchange";
	private final static String QUEUE_NAME = "myQueue";
	
	public static void main(String[] args) {
		
		 PublishUtil publishUtil = new PublishUtil();
		
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:ApplicationContext2.xml");
		String message = "当前时间是："+System.currentTimeMillis();
		publishUtil.send(EXCHANGE_NAME, QUEUE_NAME, message);
		context.destroy();
	}
}
