package spring.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQUtils {
    private static ConnectionFactory connectionFactory;
    static {
        //创建连接mq的连接工厂对象
        connectionFactory=new ConnectionFactory();
        //设置连接rabbitmq主机
        connectionFactory.setHost("193.112.76.40");
        //设置端口号
        connectionFactory.setPort(5672);
        //设置连接哪个虚拟主机
        connectionFactory.setVirtualHost("ems");
        //设置访问虚拟主机的用户密码
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("jinghanv5");
    }

    /**
     * 定义提供链接对象的方法
     * @return
     */
    public static Connection getConnection(){
        try {
            return  connectionFactory.newConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    /**
     * 关闭提供链接对象的方法
     * @param connection
     * @param channel
     */
    public static void closeConnectionAndChannel(Connection connection, Channel channel) {
        try {
            if(channel!=null) channel.close();
            if (connection != null) connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
