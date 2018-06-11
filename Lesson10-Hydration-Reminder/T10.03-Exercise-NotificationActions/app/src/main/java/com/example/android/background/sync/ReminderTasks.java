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
package com.example.android.background.sync;

import android.app.NotificationManager;
import android.content.Context;

import com.example.android.background.utilities.NotificationUtils;
import com.example.android.background.utilities.PreferenceUtilities;

public class ReminderTasks {

    public static final String ACTION_INCREMENT_WATER_COUNT = "increment-water-count";
    //  COMPLETED (2) Add a public static constant called ACTION_DISMISS_NOTIFICATION
    public static final String ACTION_DISMISS_NOTIFICATION = "dismiss_notification";
    public static void executeTask(Context context, String action) {
        if (ACTION_INCREMENT_WATER_COUNT.equals(action)) {
            incrementWaterCount(context);
        }
        //      COMPLETED (3) If the user ignored the reminder, clear the notification
        if (ACTION_DISMISS_NOTIFICATION.equals(action)) {
            NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            nm.cancel(NotificationUtils.WATER_REMINDER_NOTIFICATION_ID);
        }
    }

    private static void incrementWaterCount(Context context) {
        int count = PreferenceUtilities.getWaterCount(context);
        PreferenceUtilities.incrementWaterCount(context);
        //      COMPLETED (4) If the water count was incremented, clear any notifications
        if (PreferenceUtilities.getWaterCount(context) > count){
            NotificationUtils.clearNotifications(context);
        }
    }
}