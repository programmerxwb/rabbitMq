package com.wenbin.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.wenbin.mq.util.RabbitMqUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Provider {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMqUtil.getConn();


        Channel channel = connection.createChannel();
        channel.queueDeclare("hello", true, false, false, null);
        channel.basicPublish("", "hello", null, "Hello World".getBytes());
        RabbitMqUtil.closeConn(channel, connection);
    }
}
