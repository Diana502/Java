package net.yto.newmysql.rabbitMQ.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import net.yto.newmysql.utils.ConnectionUtil;

/**
 * rabbitmq生产者
 * @author Shansuna
 *
 */
public class Sender {
	
	//队列的名字
	private final static String QUEUE = "testWork";
	
	public static void main(String[] args) throws Exception{
		
		//获取连接
		Connection conn = ConnectionUtil.getConnection();
		//创建通道
		Channel channel = conn.createChannel();
		/**
		 * 声明队列，队列不存在则创建，存在则啥也不做
		 * 参数1 队列的名字
		 * 参数2 是否持久化队列，队列默认保存在内存中，重启rabbitmq会丢失，设置为true则会保存到erlang自带的数据库中，
		 *           重启rabbitmq会自动读取
		 * 参数3 是否排外，连接关闭后自动删除队列；队列是否私有，其他通道不能访问当前队列，仅限一个用户时使用
		 * 参数4 是否自动删除
		 * 参数5 其他参数
		 */
		channel.queueDeclare(QUEUE,false,false,false,null);
		for (int i = 0; i < 20; i++) {
			//发送消息
			channel.basicPublish("", QUEUE, null, ("你好，接收者"+i).getBytes());
		}
		//关闭通道
		channel.close();
		//关闭连接
		conn.close();
	}
	
	
}
