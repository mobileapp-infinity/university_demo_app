package com.infinity.infoway.atmiya.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.google.firebase.messaging.RemoteMessage;
import com.infinity.infoway.atmiya.R;

import org.json.JSONObject;

public class NotificationUtils {

    public NotificationUtils(Context context, RemoteMessage remoteMessage) {
        if (remoteMessage != null && remoteMessage.getData().size() > 0) {
            try {
                JSONObject json = new JSONObject(remoteMessage.getData().toString());
                handleDataMessages(context, json);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (remoteMessage.getNotification() != null) {
            handleNotificationMessages(remoteMessage.getNotification());
        }

    }


    private void handleNotificationMessages(RemoteMessage.Notification notification) {

    }

    private void handleDataMessages(Context context, JSONObject json) {
        try {
            JSONObject data = json.getJSONObject("data");
            String notificationTitle = data.getString("title");
            String notificationMessage = data.getString("message");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
