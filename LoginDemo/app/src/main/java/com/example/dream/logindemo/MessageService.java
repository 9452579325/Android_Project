package com.example.dream.logindemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

public class MessageService extends FirebaseMessagingService {
    public MessageService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage)
    {
        super.onMessageReceived(remoteMessage);
        Log.d("TAG_NOTIFICATION",remoteMessage.getData().get("message"));
       //NotificationCompat.InboxStyle inboxStyle=new NotificationCompat().InboxStyle
        NotificationCompat.Builder nBuilder=new NotificationCompat.Builder(getApplicationContext());
        nBuilder.setTicker("CHAT MESSAGE RECEIVE");
        //nBuilder.setStyle(inboxStyle);
        nBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
        nBuilder.setContentTitle(remoteMessage.getData().get("user"));
        nBuilder.setContentText(remoteMessage.getData().get("message"));
        Notification notification=nBuilder.build();
        NotificationManager nManager=(NotificationManager)getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
        nManager.notify((new Random()).nextInt(99999),notification);


    }
}
