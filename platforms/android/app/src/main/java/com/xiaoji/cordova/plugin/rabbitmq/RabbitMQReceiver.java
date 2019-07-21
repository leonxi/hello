package com.xiaoji.cordova.plugin.rabbitmq;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.json.JSONException;
import org.json.JSONObject;

public class RabbitMQReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
        try {
          String extra = intent.getStringExtra("mwxing");
          JSONObject message = new JSONObject(extra);
          RabbitMQPlugin.transmitMessageReceived(message);
        } catch (Exception e) {
          e.printStackTrace();
        }
    }
}
