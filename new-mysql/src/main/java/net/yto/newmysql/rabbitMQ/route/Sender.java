package net.yto.newmysql.rabbitMQ.route;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import net.yto.newmysql.utils.ConnectionUtil;

/**
 * 路由模式的生产者
 * @author Shansuna
 *
 */
public class Sender {
	
	private final static String EXCHANGE_NAME = "testRoute";
	
	public static void main(String[] args) throws Exception {
		
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(EXCHANGE_NAME, "direct");
		channel.basicPublish(EXCHANGE_NAME, "key1", null, "路由模式发送的信息".getBytes());
		channel.close();
		connection.close();
		
	}
}
