package com.wenbin.mq.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMqUtil {
    private static ConnectionFactory connectionFactory;
    static{
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("xwb");
        connectionFactory.setPassword("xwb");
        connectionFactory.setVirtualHost("/emd");

    }
    public static Connection getConn(){
        try{
            return connectionFactory.newConnection();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void closeConn(Channel channel, Connection connection){
        try{
            if (channel != null) channel.close();
            if (connection!=null) connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }




}
