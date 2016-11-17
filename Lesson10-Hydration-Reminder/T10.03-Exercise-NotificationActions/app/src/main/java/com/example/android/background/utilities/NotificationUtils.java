
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

public class NotificationUtils {

    /*
     * This notification ID can be used to access our notification after we've displayed it. This
     * can be handy when we need to cancel the notification, or perhaps update it. This number is
     * arbitrary and can be set to whatever you like. 1138 is in no way significant.
     */

    private static final int WATER_REMINDER_NOTIFICATION_ID = 1138;

    public static void remindUserBecauseCharging(Context context) {

        String chargingTitle = context.getString(R.string.charging_reminder_notification_title);
        String chargingText = context.getString(R.string.charging_reminder_notification_body);

        remindUser(context, chargingTitle, chargingText);
    }

    public static void remindUserBecauseWifi(Context context) {
        String wifiTitle = context.getString(R.string.wifi_reminder_notification_title);
        String wifiText = context.getString(R.string.wifi_reminder_notification_body);

        remindUser(context, wifiTitle, wifiText);
    }

//  TODO (1) Create a method to clear all notifications

    private static void remindUser(Context context, String title, String text) {

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(largeIcon(context))
                .setContentTitle(title)
                .setContentText(text)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(text))
                .setContentIntent(contentIntent(context))
//              TODO (17) Add the drink water action using the drinkWaterAction method
//              TODO (18) Add the ignore reminder action using the ignoreReminderAction method
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        /* WATER_REMINDER_NOTIFICATION_ID allows you to update or cancel the notification later on */
        notificationManager.notify(WATER_REMINDER_NOTIFICATION_ID, notificationBuilder.build());
    }

//  TODO (5) Add a static method called drinkWaterAction

//      TODO (6) Create an Intent to launch MyIntentService
//      TODO (7) Set the action of the intent to designate you want to dismiss the notification

//      TODO (8) Create a PendingIntent from the intent to launch MyIntentService

//      TODO (9) Create an Action for the user to ignore the notification (and dismiss it)

//      TODO (10) Return the action


//  TODO (11) Add a static method called drinkWaterAction

//      TODO (12) Create an Intent to launch MyIntentService
//      TODO (13) Set the action of the intent to designate you want to increment the water count

//      TODO (14) Create a PendingIntent from the intent to launch MyIntentService

//      TODO (15) Create an Action for the user to tell us they've had a glass of water

//      TODO (16) Return the action

    private static PendingIntent contentIntent(Context context) {
        Intent startActivityIntent = new Intent(context, MainActivity.class);
        return PendingIntent.getActivity(
                context,
                3417,
                startActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private static Bitmap largeIcon(Context context) {
        Resources res = context.getResources();
        Bitmap largeIcon = BitmapFactory.decodeResource(res, R.drawable.ic_local_drink_black_24px);
        return largeIcon;
    }
}