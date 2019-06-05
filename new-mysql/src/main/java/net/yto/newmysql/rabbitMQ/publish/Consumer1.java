package net.yto.newmysql.rabbitMQ.publish;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

import net.yto.newmysql.utils.ConnectionUtil;

public class Consumer1 {
	
	private final static String EXCHANGE_NAME = "testExchange";
	
	public static void main(String[] args) throws Exception {
		
		Connection connection = ConnectionUtil.getConnection();
		
		Channel channel = connection.createChannel();
		
		channel.queueDeclare("testPublishQueue1", false, false, false, null);
		
		channel.queueBind("testPublishQueue1", EXCHANGE_NAME, "");
		
		channel.basicQos(1);
		
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				super.handleDelivery(consumerTag, envelope, properties, body);
				System.out.println("发布订阅模式consumer2："+new String(body));
			}
		};
		
		channel.basicConsume("testPublishQueue1",false,consumer);
		
	}
}
