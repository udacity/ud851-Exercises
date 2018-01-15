package com.example.android.background.sync;

// TODO DONE (1) Create a class called ReminderTasks

// TODO DONE (2) Create a public static constant String called ACTION_INCREMENT_WATER_COUNT

// TODO DONE (6) Create a public static void method called executeTask
// TODO DONE (7) Add a Context called context and String parameter called action to the parameter list

// TODO DONE (8) If the action equals ACTION_INCREMENT_WATER_COUNT, call this class's incrementWaterCount

// TODO DONE (3) Create a private static void method called incrementWaterCount
// TODO DONE (4) Add a Context called context to the argument list
// TODO DONE (5) From incrementWaterCount, call the PreferenceUtility method that will ultimately update the water count

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.android.background.utilities.PreferenceUtilities;

import java.util.Objects;

public  class ReminderTasks {

    public static String ACTION_INCREMENT_WATER_COUNT;

    private static void incrementWaterCount(Context context) {
        PreferenceUtilities.incrementWaterCount(context);
    }

    public static void executeTask(Context context, String action) {
        if (Objects.equals(action, ACTION_INCREMENT_WATER_COUNT)) incrementWaterCount(context);
    }
}