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

import android.content.Context;

import com.example.android.background.utilities.PreferenceUtilities;

// COMPLETED (1) Create a class called ReminderTasks
public class ReminderTasks {

    //  COMPLETED (2) Create a public static constant String called ACTION_INCREMENT_WATER_COUNT
    public static final String ACTION_INCREMENT_WATER_COUNT = "increment-water-count";

    //  COMPLETED (6) Create a public static void method called executeTask
//  COMPLETED (7) Add a Context called context and String parameter called action to the parameter list
    public static void executeTask(Context context, String action) {
// COMPLETED (8) If the action equals ACTION_INCREMENT_WATER_COUNT, call this class's incrementWaterCount
        if (ACTION_INCREMENT_WATER_COUNT.equals(action)) {
            incrementWaterCount(context);
        }
    }

    //  COMPLETED (3) Create a private static void method called incrementWaterCount
//  COMPLETED (4) Add a Context called context to the argument list
    private static void incrementWaterCount(Context context) {
//      COMPLETED (5) From incrementWaterCount, call the PreferenceUtility method that will ultimately update the water count
        PreferenceUtilities.incrementWaterCount(context);
    }
}