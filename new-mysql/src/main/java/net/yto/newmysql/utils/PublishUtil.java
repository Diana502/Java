package net.yto.newmysql.utils;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("publishUtil")
public class PublishUtil {

	@Autowired
	private AmqpTemplate amqpTemplate;
	
	public void send(String exchange,String routingKey,Object message ) {
		amqpTemplate.convertAndSend(exchange,routingKey,message);
	}
}
