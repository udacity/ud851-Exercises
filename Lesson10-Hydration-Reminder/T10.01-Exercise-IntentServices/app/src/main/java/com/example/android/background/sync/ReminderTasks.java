package com.example.android.background.sync;

import android.content.Context;

import com.example.android.background.utilities.PreferenceUtilities;

// COMPLETED (1) Create a class called ReminderTasks
public class ReminderTasks {
    // COMPLETED (2) Create a public static constant String called ACTION_INCREMENT_WATER_COUNT
    public static final String ACTION_INCREMENT_WATER_COUNT = "Increment_Water_Count";
    // COMPLETED (6) Create a public static void method called executeTask
    // COMPLETED (7) Add a Context called context and String parameter called action to the parameter list
    public static void executeTask(Context context, String action) {
    // COMPLETED (8) If the action equals ACTION_INCREMENT_WATER_COUNT, call this class's incrementWaterCount
        if (action.equals(ACTION_INCREMENT_WATER_COUNT)){
            incrementWaterCount(context);
        }
    }

    // COMPLETED (3) Create a private static void method called incrementWaterCount
    // COMPLETED (4) Add a Context called context to the argument list
    // COMPLETED (5) From incrementWaterCount, call the PreferenceUtility method that will ultimately update the water count
    private static void incrementWaterCount(Context context){
        PreferenceUtilities.incrementWaterCount(context);
        PreferenceUtilities.incrementChargingReminderCount(context);
    }
}