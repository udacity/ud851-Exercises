package com.example.android.background.sync;

import android.content.Context;

import com.example.android.background.utilities.PreferenceUtilities;

// TODO (1) Create a class called ReminderTasks CHECKED
public class ReminderTasks
{
    public static final String ACTION_INCREMENT_WATER_COUNT="increment-water-count";


// TODO (2) Create a public static constant String called ACTION_INCREMENT_WATER_COUNT CHECKED

// TODO (6) Create a public static void method called executeTask CHECKED
// TODO (7) Add a Context called context and String parameter called action to the parameter list CHECKED

    // TODO (8) If the action equals ACTION_INCREMENT_WATER_COUNT, call this class's incrementWaterCount CHECKED
    public static void executeTask(Context mContext, String action)
    {
        if(action.equals(ACTION_INCREMENT_WATER_COUNT))
        {
            incrementWaterCount(mContext);
        }
    }
// TODO (3) Create a private static void method called incrementWaterCount CHECKED
// TODO (4) Add a Context called context to the argument list CHECKED
// TODO (5) From incrementWaterCount, call the PreferenceUtility method that will ultimately update the water count CHECKED
    private static void incrementWaterCount(Context context)
    {
        PreferenceUtilities.incrementWaterCount(context);
    }

}


