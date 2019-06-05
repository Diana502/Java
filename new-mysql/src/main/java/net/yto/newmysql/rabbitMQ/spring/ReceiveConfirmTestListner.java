package net.yto.newmysql.rabbitMQ.spring;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

/**
 * 
 * @author Shansuna
 *
 */
@Component("receiveConfirmTestListner")
public class ReceiveConfirmTestListner implements ChannelAwareMessageListener{

	/**
	 * 收到消息时执行的监听
	 */
	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		try {
			System.out.println("消费者收到了信息"+message);
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		}
	}

}
