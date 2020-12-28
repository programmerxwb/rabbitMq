package com.wenbin.mq.work;

import com.rabbitmq.client.*;
import com.wenbin.mq.util.RabbitMqUtil;

import java.io.IOException;

public class ConsumerB {
    public static void main(String[] args) throws IOException {
        Connection conn = RabbitMqUtil.getConn();
        final Channel channel = conn.createChannel();
        channel.basicQos(1);
        channel.queueDeclare("work", true, false, false, null);

        channel.basicConsume("work", false, new DefaultConsumer(channel) {
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body)
                    throws IOException {
                System.out.println("消费者B，message：" + new String(body));

                // 参数1：具体要确认的具体消息  参数2：是否开启多个消息同时确认
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
