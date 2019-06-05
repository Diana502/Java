package net.yto.newmysql.rabbitMQ.hello;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import net.yto.newmysql.utils.ConnectionUtil;

/**
 * rabbitmq接受者
 * @author Shanuna
 *
 */
public class Receiver {
	
	//队列的名字
	private final static String QUEUE = "testHello";
	
	public static void main(String[] args) throws Exception{
		
		Connection conn = ConnectionUtil.getConnection();
		Channel channel = conn.createChannel();
		channel.queueDeclare(QUEUE,false,false,false,null);
		
		//定义一个消费者
		Consumer consumer = new DefaultConsumer(channel) {
			
			@Override
			public void handleDelivery(String consumerTag,Envelope envelope,
                    AMQP.BasicProperties properties,byte[] body)throws IOException{

				String message = new String(body,"UTF-8");
				System.out.println("消费者："+message);
			}
		};
		
		while(true) {
			//接收消息 参数2是否自动应答
			channel.basicConsume(QUEUE, true, consumer);
		}

		
		
	}
}
