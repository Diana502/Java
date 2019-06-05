package net.yto.newmysql.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 用于创建连接的工具类
 * @author Shansuna
 *
 */
public class ConnectionUtil {
	
	/**
	 * 创建rabbitmq的连接
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception{
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		//设置服务器的地址
		connectionFactory.setHost("172.16.81.145");
		//设置服务器的端口号
		connectionFactory.setPort(5672);
		//设置用户名和密码
		connectionFactory.setUsername("shansuna");
		connectionFactory.setPassword("shansuna");
		//设置虚拟主机
		connectionFactory.setVirtualHost("/newsql");
		return connectionFactory.newConnection();
	}
}
