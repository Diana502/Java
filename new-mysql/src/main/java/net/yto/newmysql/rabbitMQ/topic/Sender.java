package net.yto.newmysql.rabbitMQ.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import net.yto.newmysql.utils.ConnectionUtil;

/**
 *广播（通配服务）模式的生产者
 * @author Shansuna
 *
 */
public class Sender {
	
	private final static String EXCHANGE_NAME = "testTopic";
	
	public static void main(String[] args) throws Exception {
		
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(EXCHANGE_NAME, "topic");
		channel.basicPublish(EXCHANGE_NAME, "key.1", null, "topic模式发送的信息".getBytes());
		channel.close();
		connection.close();
		
	}
}
