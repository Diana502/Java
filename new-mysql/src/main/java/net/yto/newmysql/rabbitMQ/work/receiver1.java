package net.yto.newmysql.rabbitMQ.work;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import net.yto.newmysql.utils.ConnectionUtil;

public class receiver1 {
	
	//队列的名字
	private final static String QUEUE = "testWork";
	
	public static void main(String[] args) throws Exception{
		
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclareNoWait(QUEUE, false, false, false, null);
		//在我未完成任务前，不要给我发新消息
		channel.basicQos(1);
		DefaultConsumer consumer = new DefaultConsumer(channel) {
			
			@Override
			public void handleDelivery(String consumerTag,Envelope envelope,
                    AMQP.BasicProperties properties,byte[] body)throws IOException{

				String message = new String(body,"UTF-8");
				System.out.println("消费者1："+message);
				/*try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				//参数2为false：确认收到消息，true：拒接消息
				channel.basicAck(envelope.getDeliveryTag(), false);
			}
		};
		//注册消费者
		//参数2为false：需要手动应答
		channel.basicConsume(QUEUE, false, consumer);
	}
}
