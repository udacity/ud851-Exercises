package com.example.android.background.sync;

import android.content.Context;

import com.example.android.background.utilities.PreferenceUtilities;

public class ReminderTasks {

    public static final String ACTION_INCREMENT_WATER_COUNT = "increment-water-count";
    //  TODO (48) Declare and initialize a static final String variable called ACTION_CHARGING_REMINDER
    //  TODO (49) Declare and initialize a static final String variable called ACTION_WIFI_REMINDER

    public static void executeTask(Context context, String action) {
        if (ACTION_INCREMENT_WATER_COUNT.equals(action)) {
            incrementWaterCount(context);
        }
//      TODO (54) If the action is the charging reminder action, call issueChargingReminder
//      TODO (55) If the action is the WiFi reminder action, call issueWifiReminder
    }

    private static void incrementWaterCount(Context context) {
        PreferenceUtilities.incrementWaterCount(context);
    }

//  TODO (50) Create a private static void method call issueWifiReminder
//  TODO (51) Within this method, increment the proper reminder count and remind the user with the WiFi reminder

//  TODO (52) Create a private static void method call issueChargingReminder
//  TODO (53) Within this method, increment the proper reminder count and remind the user with the charging reminder
}