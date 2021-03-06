package net.yto.newmysql.rabbitMQ.topic;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

import net.yto.newmysql.utils.ConnectionUtil;

/**
 * “.”分隔的就是一个单词
 * “*”精确匹配一个单词
 * “#”多个单词，也可以是0个
 * @author 8
 *
 */
public class Consumer1 {
	
	private final static String EXCHANGE_NAME = "testTopic";
	private final static String QUEUE_NAME = "testTopicQueue1";
	
	public static void main(String[] args) throws Exception {
		
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		//参数3 标记 只有和他标记一样的消息才会被该消费者消费
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "key.*");
		//如果需要接收多个标记的信息，就再执行一次
		channel.queueBind("testPublishQueue1", EXCHANGE_NAME, "abc.#");
		channel.basicQos(1);
		DefaultConsumer consumer1 = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				super.handleDelivery(consumerTag, envelope, properties, body);
				System.out.println(new String(body,"UTF-8"));
				channel.basicAck(envelope.getDeliveryTag(), false);
			}
		};
		channel.basicConsume(QUEUE_NAME, consumer1);
		
	}

}
