package net.yto.newmysql.rabbitMQ.publish;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import net.yto.newmysql.utils.ConnectionUtil;

public class Sender {

	private final static String EXCHANGE_NAME = "testExchange";
	
	public static void main(String[] args) throws Exception{
		
		Connection connection = ConnectionUtil.getConnection();
		
		Channel channel = connection.createChannel();
		
		//声明交换机,定义一个类型是发布订阅模式的交换机
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		//发布订阅模式，消息先发布到交换机中，交换机无法保存，如果无消费者，信息会消失
		channel.basicPublish(EXCHANGE_NAME, "", null, "发布的订阅信息".getBytes());
		
		channel.close();
		connection.close();
		
		
	}
} 
