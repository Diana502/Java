package net.yto.newmysql.rabbitMQ.persist;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import net.yto.newmysql.utils.ConnectionUtil;

/**
 * 消息持久化
 * @author Shansuna
 *
 */
public class Sender {
	
	private final static String EXCHANGE_NAME = "exchange";
	
	public static void main(String[] args) throws Exception {
		
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();
		//声明持久化的交换机
		channel.exchangeDeclare(EXCHANGE_NAME, "direct", true,false,null);
		channel.basicPublish(EXCHANGE_NAME, "abc", MessageProperties.PERSISTENT_TEXT_PLAIN, "消息持久化".getBytes());
		channel.close();
		connection.close();
	}
}
