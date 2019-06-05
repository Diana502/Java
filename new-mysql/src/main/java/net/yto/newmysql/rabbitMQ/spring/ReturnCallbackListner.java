package net.yto.newmysql.rabbitMQ.spring;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.stereotype.Component;

@Component("returnCallbackListner")
public class ReturnCallbackListner implements ReturnCallback{

	@Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
		System.out.println("======================================");
		System.out.println("失败：" + new String(message.getBody()));
		System.out.println("1.replyCode"+replyCode);
		System.out.println("2.replyText"+replyText);
		System.out.println("3.exchange"+exchange);
		System.out.println("4.routingKey"+routingKey);
		System.out.println("======================================");
	}

}
