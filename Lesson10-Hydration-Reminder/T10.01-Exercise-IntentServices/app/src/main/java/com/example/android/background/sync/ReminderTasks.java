package com.example.android.background.sync;

import android.content.Context;

import com.example.android.background.utilities.PreferenceUtilities;

// COMPLETED Create a class called ReminderTasks
public class ReminderTasks {
// COMPLETED Create a public static constant String called ACTION_INCREMENT_WATER_COUNT
    public static final String ACTION_INCREMENT_WATER_COUNT = "increment-water-count";
// COMPLTED Create a public static void method called executeTask
// COMPLTED Add a Context called context and String parameter called action to the parameter list
    public static final void executeTask (Context context, String action) {
// COMPLETED If the action equals ACTION_INCREMENT_WATER_COUNT, call this class's incrementWaterCount
        if (ACTION_INCREMENT_WATER_COUNT.equals(action)) {
            incrementWaterCount(context);
        }
    }

// COMPLETED Create a private static void method called incrementWaterCount
// COMPLETED Add a Context called context to the argument list
// COMPLETED From incrementWaterCount, call the PreferenceUtility method that will ultimately update the water count
    private static void incrementWaterCount(Context context) {
        PreferenceUtilities.incrementWaterCount(context);
    }
}