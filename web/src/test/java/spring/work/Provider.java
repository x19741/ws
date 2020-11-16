package spring.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
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

        //通道绑定对应消息队列
        //参数1：队列名称 如果队列不存在自动创建
        //参数2：用来定义队列特性是否要持久化 true 持久化队列 false 持久化队列
        //参数3：exclusive 是否独占队列  true 独占队列 false 不独占队列
        //参数4：autoDalete 是否在消费完成后自动删除队列 true 自动删除 false 不自动删除
        //参数5：额外附加参数
        channel.queueDeclare("work",false,false,false,null);
        for (int i = 1; i <=20 ; i++) {
            //发布消息
            //参数1:交换机名称 参数2：队列名称 参数3：传递消息的额外设置 参数4：消息的具体内容
            channel.basicPublish ("","work", null,(i+" hello work quene").getBytes());
        }
        RabbitMQUtils.closeConnectionAndChannel(connection,channel);
    }
}
