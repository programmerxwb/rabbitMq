package com.wenbin.mq.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.wenbin.mq.util.RabbitMqUtil;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        Connection conn = RabbitMqUtil.getConn();
        Channel channel = conn.createChannel();
        channel.queueDeclare("work", true, false, false, null);
        for (int a = 0; a < 10; a++) {

            channel.basicPublish("", "work", null, "Hello Work Queue".getBytes());
        }
        RabbitMqUtil.closeConn(channel, conn);
    }
}
