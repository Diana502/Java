package net.yto.newmysql.rabbitMQ.persist;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

import net.yto.newmysql.utils.ConnectionUtil;

public class Consumer1 {
	
	private final static String EXCHANGE_NAME = "exchange";
	private final static String QUEUE_NAME = "queue";
	
	public static void main(String[] args) throws Exception {
		
		Connection connection = ConnectionUtil.getConnection();
		
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(EXCHANGE_NAME, "direct", true, false, null);
		channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "abc");
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				super.handleDelivery(consumerTag, envelope, properties, body);
				System.out.println(new String(body));
			}
		};
		
		channel.basicConsume(QUEUE_NAME,true,consumer);
		
	}
}
