package net.yto.newmysql.rabbitMQ.spring;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

	public static void main(String[] args) {
		
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:ApplicationContext.xml");
		RabbitTemplate rabbitTemplate = context.getBean(RabbitTemplate.class);
		rabbitTemplate.convertAndSend("好好学习，天天向上");
		context.destroy();
		
	}
}
