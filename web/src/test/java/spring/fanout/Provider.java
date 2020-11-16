package spring.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;
import spring.util.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Provider {
    //生产消息
    @Test
    public void testSendMessage() throws IOException, TimeoutException {
        //获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        //声明交换机
        channel.exchangeDeclare("logs","fanout");

        //发布消息
        channel.basicPublish("logs","",null,"hello".getBytes());


        RabbitMQUtils.closeConnectionAndChannel(connection,channel);
    }
}
