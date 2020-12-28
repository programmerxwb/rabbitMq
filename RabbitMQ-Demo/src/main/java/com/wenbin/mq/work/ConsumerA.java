package com.wenbin.mq.work;

import com.rabbitmq.client.*;
import com.wenbin.mq.util.RabbitMqUtil;

import java.io.IOException;

public class ConsumerA {
    public static void main(String[] args) throws IOException {
        Connection conn = RabbitMqUtil.getConn();
        Channel channel = conn.createChannel();
        channel.basicQos(1);
        channel.queueDeclare("work", true, false, false, null);

        // 参数2
        channel.basicConsume("work", true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body)
                    throws IOException {
                System.out.println("消费者A Message：" + new String(body));
            }
        });


    }
}
