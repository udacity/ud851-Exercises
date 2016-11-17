package com.example.android.background.sync;

import android.content.Context;

import com.example.android.background.utilities.NotificationUtils;
import com.example.android.background.utilities.PreferenceUtilities;

public class ReminderTasks {

    public static final String ACTION_INCREMENT_WATER_COUNT = "increment-water-count";
    static final String ACTION_WIFI_REMINDER = "wifi-reminder-tag";
    static final String ACTION_CHARGING_REMINDER = "charging-reminder-tag";

    //  TODO (2) Add a public static constant called ACTION_DISMISS_NOTIFICATION

    public static void executeTask(Context context, String action) {
        if (ACTION_INCREMENT_WATER_COUNT.equals(action)) {
            incrementWaterCount(context);
        } else if (ACTION_CHARGING_REMINDER.equals(action)) {
            issueChargingReminder(context);
        } else if (ACTION_WIFI_REMINDER.equals(action)) {
            issueWifiReminder(context);
        }
//      TODO (3) If the user ignored the reminder, clear the notification
    }

    private static void incrementWaterCount(Context context) {
        PreferenceUtilities.incrementWaterCount(context);
//      TODO (4) If the water count was incremented, clear any notifications
    }

    private static void issueWifiReminder(Context context) {
        PreferenceUtilities.incrementWifiReminderCount(context);
        NotificationUtils.remindUserBecauseWifi(context);
    }

    private static void issueChargingReminder(Context context) {
        PreferenceUtilities.incrementChargingReminderCount(context);
        NotificationUtils.remindUserBecauseCharging(context);
    }
}