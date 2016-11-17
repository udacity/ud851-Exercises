
/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.background.utilities;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.example.android.background.MainActivity;
import com.example.android.background.R;
// COMPLETED (18) Create a public class called NotificationUtils
public class NotificationUtils {

    /*
     * This notification ID can be used to access our notification after we've displayed it. This
     * can be handy when we need to cancel the notification, or perhaps update it. This number is
     * arbitrary and can be set to whatever you like. 1138 is in no way significant.
     */
//  COMPLETED (19) Create a constant int called WATER_REMINDER_NOTIFICATION_ID
    private static final int WATER_REMINDER_NOTIFICATION_ID = 1138;

//  COMPLETED (40) Create a method called remindUserBecauseCharging
    public static void remindUserBecauseCharging(Context context) {

//      COMPLETED (41) Get a reference to the charging notification title
        String chargingTitle = context.getString(R.string.charging_reminder_notification_title);
//      COMPLETED (42) Get a reference to the charging notification text body
        String chargingText = context.getString(R.string.charging_reminder_notification_body);

//      COMPLETED (43) Call the remindUser method with the proper parameters
        remindUser(context, chargingTitle, chargingText);
    }

//  COMPLETED (44) Create a method called remindUserBecauseWifi
    public static void remindUserBecauseWifi(Context context) {
//      COMPLETED (45) Get a reference to the WiFi notification title
        String wifiTitle = context.getString(R.string.wifi_reminder_notification_title);
//      COMPLETED (46) Get a reference to the WiFi notification text body
        String wifiText = context.getString(R.string.wifi_reminder_notification_body);

//      COMPLETED (47) Call the remindUser method with the proper parameters
        remindUser(context, wifiTitle, wifiText);
    }

//  COMPLETED (27) Create a public static method called remindUser
//  COMPLETED (28) Add a String title and a String text to the argument list
    private static void remindUser(Context context, String title, String text) {

//      COMPLETED (29) Use NotificationCompat.Builder to build a notification
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
//               COMPLETED (30) Set the color to colorPrimary using ContextCompat
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
//               COMPLETED (31) Set the small icon to R.mipmap.ic_launcher
                .setSmallIcon(R.mipmap.ic_launcher)
//               COMPLETED (32) Set the large icon using your static largeIcon method
                .setLargeIcon(largeIcon(context))
//               COMPLETED (33) Set the title to the argument title
                .setContentTitle(title)
//               COMPLETED (34) Set the text to the argument text
                .setContentText(text)
//               COMPLETED (35) Allow the notification to expand
                .setStyle(new NotificationCompat.BigTextStyle().bigText(text))
//               COMPLETED (36) Set the content intent using our static contentIntent method
                .setContentIntent(contentIntent(context))
//               COMPLETED (37) Set auto cancel to be true
                .setAutoCancel(true);

//      COMPLETED (38) Get a reference to the NotificationManager using context.getSystemService
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);


//      COMPLETED (39) Notify user using the notification ID defined above and the built notification
        /* WATER_REMINDER_NOTIFICATION_ID allows you to update or cancel the notification later on */
        notificationManager.notify(WATER_REMINDER_NOTIFICATION_ID, notificationBuilder.build());
    }

//  COMPLETED (20) Create a private static method called contentIntent that returns a PendingIntent
    private static PendingIntent contentIntent(Context context) {
//      COMPLETED (21) Create an Intent to start the MainActivity
        Intent startActivityIntent = new Intent(context, MainActivity.class);
//      COMPLETED (22) Return a PendingIntent using PendingIntent.getActivity
        return PendingIntent.getActivity(
                context,
                3417,
                startActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

//  COMPLETED (23) Create a private static method called largeIcon that returns a Bitmap
    private static Bitmap largeIcon(Context context) {
//      COMPLETED (24) Get a reference to the resources
        Resources res = context.getResources();
//      COMPLETED (25) Create a Bitmap of the glass icon using BitmapFactory.decodeResource
        Bitmap largeIcon = BitmapFactory.decodeResource(res, R.drawable.ic_local_drink_black_24px);
//      COMPLETED (26) Return the Bitmap
        return largeIcon;
    }
}