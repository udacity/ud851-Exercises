package com.example.android.background.sync;

import android.content.Context;

import com.example.android.background.utilities.NotificationUtils;
import com.example.android.background.utilities.PreferenceUtilities;

public class ReminderTasks {

    public static final String ACTION_INCREMENT_WATER_COUNT = "increment-water-count";
    //  COMPLETED (48) Declare and initialize a static final String variable called CHARGING_TAG
    static final String ACTION_CHARGING_REMINDER = "charging-reminder-tag";
    //  COMPLETED (49) Declare and initialize a static final String variable called WIFI_TAG
    static final String ACTION_WIFI_REMINDER = "wifi-reminder-tag";

    public static void executeTask(Context context, String action) {
        if (ACTION_INCREMENT_WATER_COUNT.equals(action)) {
            incrementWaterCount(context);
//      COMPLETED (54) If the action is the charging reminder action, call issueChargingReminder
        } else if (ACTION_CHARGING_REMINDER.equals(action)) {
            issueChargingReminder(context);
//      COMPLETED (55) If the action is the WiFi reminder action, call issueWifiReminder
        } else if (ACTION_WIFI_REMINDER.equals(action)) {
            issueWifiReminder(context);
        }
    }

    private static void incrementWaterCount(Context context) {
        PreferenceUtilities.incrementWaterCount(context);
    }

//  COMPLETED (50) Create a private static void method call issueWifiReminder
//  COMPLETED (51) Within this method, increment the proper reminder count and remind the user with the WiFi reminder
    private static void issueWifiReminder(Context context) {
        PreferenceUtilities.incrementWifiReminderCount(context);
        NotificationUtils.remindUserBecauseWifi(context);
    }

//  COMPLETED (52) Create a private static void method call issueChargingReminder
//  COMPLETED (53) Within this method, increment the proper reminder count and remind the user with the charging reminder
    private static void issueChargingReminder(Context context) {
        PreferenceUtilities.incrementChargingReminderCount(context);
        NotificationUtils.remindUserBecauseCharging(context);
    }
}