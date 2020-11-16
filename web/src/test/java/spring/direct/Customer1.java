package spring.direct;

import com.rabbitmq.client.*;
import spring.util.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Customer1 {

    public static void main(String[] args) throws IOException, TimeoutException {

        //获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        String exchangeName="logs_direct";

        //绑定交换机
        channel.exchangeDeclare(exchangeName,"direct");
        //创建临时消息队列
        String queue =channel.queueDeclare().getQueue();
        //将临时队列绑定exchange
        channel.queueBind(queue,exchangeName,"errer");
        //处理消息

        channel.basicConsume(queue,true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1"+new String(body));
            }
        });

        //由于这个消费任务是异步的所有不能把这个关闭掉、如果就会在线程启动之前就会把消费任务close掉倒是异步不能正确执行
        //RabbitMQUtils.closeConnectionAndChannel(connection,channel);
    }
}
