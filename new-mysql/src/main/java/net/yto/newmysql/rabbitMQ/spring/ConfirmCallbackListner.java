package net.yto.newmysql.rabbitMQ.spring;


import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.stereotype.Component;

/**
 * 
 * @author 8
 *
 */
@Component("confirmCallbackListner")
public class ConfirmCallbackListner implements ConfirmCallback{

	/**
	 * 确认回调后执行的方法
	 */
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		System.out.println("ack="+ack+",cause="+cause);
	}

}
