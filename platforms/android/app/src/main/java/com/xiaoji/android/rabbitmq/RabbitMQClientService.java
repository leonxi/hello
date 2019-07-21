package com.xiaoji.android.rabbitmq;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.StrictMode;
import com.rabbitmq.client.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class RabbitMQClientService extends Service {

    private Connection conn = null;
    private Channel channel = null;

    private void connect() {
        ConnectionFactory factory = new ConnectionFactory();
        try {
            factory.setHost("pluto.guobaa.com");
            factory.setPort(5672);
            factory.setUsername("gtd_mq");
            factory.setPassword("gtd_mq");
            factory.setVirtualHost("/");

            this.conn = factory.newConnection();

            this.channel = this.conn.createChannel();

            this.channel.basicConsume("queueName", true, "myConsumerTag", new DefaultConsumer(channel) {

                @Override
                public void handleDelivery(String consumerTag,
                                           Envelope envelope,
                                           AMQP.BasicProperties properties,
                                           byte[] body)
                        throws IOException {
                    JSONObject payload = null;
                    try {
                        payload = new JSONObject(new String(body, "utf-8"));
                        System.out.println(payload.toString());
                        Intent sendIntent = new Intent();
                        sendIntent.putExtra("mwxing", new String(body, "utf-8"));
                        sendIntent.setAction("com.xiaoji.rabbitmq.MESSAGE_RECEIVED");
                        startActivity(sendIntent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RabbitMQClientService() {
        System.out.println("RabbitMQ");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("Kathy onStartCommand - startId = " + startId + ", Thread ID = " + Thread.currentThread().getId());
        connect();
        return super.onStartCommand(intent, flags, startId);
    }

    private MyBinder binder = new MyBinder();

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return binder;
    }

    public class MyBinder extends Binder {

    }
}
