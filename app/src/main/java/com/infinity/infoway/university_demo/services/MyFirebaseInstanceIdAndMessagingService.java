package com.infinity.infoway.university_demo.services;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.infinity.infoway.university_demo.utils.AppController;
import com.infinity.infoway.university_demo.utils.MySharedPreferences;
import com.infinity.infoway.university_demo.utils.NotificationUtils;

public class MyFirebaseInstanceIdAndMessagingService extends FirebaseMessagingService {

    MySharedPreferences mySharedPreferences;


    @Override
    public void onNewToken(@NonNull String fcmToken) {
        super.onNewToken(fcmToken);
        try {
            mySharedPreferences = new MySharedPreferences(AppController.getAppContext());
            if (fcmToken != null && !fcmToken.isEmpty()) {
                mySharedPreferences.setFCMToken(fcmToken);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        try {
            mySharedPreferences = new MySharedPreferences(AppController.getAppContext());

            new NotificationUtils(remoteMessage);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
