package com.example.jessie.rjt_cw_9_13_notifications;

import android.app.AutomaticZenRule;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent (MainActivity.this, SecondActivity.class);
                PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 1234, i, PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_LOW);

                    // Configure the notification channel.
                    notificationChannel.setDescription("Channel description");
                    notificationChannel.enableLights(true);
                    notificationChannel.setLightColor(Color.RED);
                    notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
                    notificationChannel.enableVibration(true);
                    notificationManager.createNotificationChannel(notificationChannel);
                    //notificationManager.addAutomaticZenRule();

                }

                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(MainActivity.this, NOTIFICATION_CHANNEL_ID);

                notificationBuilder.setAutoCancel(true)
                        .setDefaults(Notification.DEFAULT_ALL)
                        //.setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setTicker("Hearty365")
                        //     .setPriority(Notification.PRIORITY_MAX)
                        .setContentTitle("Default notification")
                        .setContentText("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
                        .setContentInfo("Info");

                notificationManager.notify(/*notification id*/1, notificationBuilder.build());




//                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
//                builder.setContentTitle("My Notification");
//                builder.setContentText("This is my text");
//                builder.setSmallIcon(R.drawable.ic_launcher_background);
//                builder.setContentIntent(pi);
//                builder.setAutoCancel(true);
//                builder.addAction(R.drawable.ic_launcher_foreground, "show Map", pi);
//                builder.addAction(R.drawable.ic_launcher_background, "Show Text", pi);
//
//                NotificationManager managerCompat = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//                managerCompat.notify(1234, builder.build());
            }
        });

    }
}
