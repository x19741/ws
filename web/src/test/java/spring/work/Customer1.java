package spring.work;

import com.rabbitmq.client.*;
import spring.util.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Customer1 {

    public static void main(String[] args) throws IOException, TimeoutException {

        //获取连接对象
        Connection connection = RabbitMQUtils.getConnection();

        Channel channel = connection.createChannel();

        //通道绑定对应消息队列
        //参数1：队列名称 如果队列不存在自动创建
        //参数2：用来定义队列特性是否要持久化 true 持久化队列 false 持久化队列
        //参数3：exclusive 是否独占队列  true 独占队列 false 不独占队列
        //参数4：autoDalete 是否在消费完成后自动删除队列 true 自动删除 false 不自动删除
        //参数5：额外附加参数
        channel.queueDeclare("work", false, false, false, null);
        //一次只能执行一个消息
        channel.basicQos(1);
        channel.basicConsume("work", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(new String(body));
                //任务执行完毕确认消息
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });

        //由于这个消费任务是异步的所有不能把这个关闭掉、如果就会在线程启动之前就会把消费任务close掉倒是异步不能正确执行
        //RabbitMQUtils.closeConnectionAndChannel(connection,channel);
    }
}
