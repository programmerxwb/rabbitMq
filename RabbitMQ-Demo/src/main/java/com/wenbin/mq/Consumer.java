package com.wenbin.mq;

import com.rabbitmq.client.*;
import com.wenbin.mq.util.RabbitMqUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = RabbitMqUtil.getConn();

        Channel channel = connection.createChannel();
        // 是否持久化，是否独占队列，是否自动删除，其他属性
        channel.queueDeclare("hello", true, false, false, null);

        channel.basicConsume("hello", true, new DefaultConsumer(channel) {
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
            }
        });
    }
}
