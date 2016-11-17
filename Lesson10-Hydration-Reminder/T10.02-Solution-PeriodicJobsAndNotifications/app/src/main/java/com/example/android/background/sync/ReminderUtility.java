/*
 * Copyright (C) 2015 The Android Open Source Project
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
import android.support.annotation.NonNull;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

import java.util.concurrent.TimeUnit;

// COMPLETED (56) Create a class called ReminderUtility
public class ReminderUtility {

    /*
     * Interval at which to remind the user to drink water. Use TimeUnit for convenience, rather
     * than writing out a bunch of multiplication ourselves and risk making a silly mistake.
     */
//  COMPLETED (57) Declare a REMINDER_INTERVAL_SECONDS constant at 15 minutes
    private static final int REMINDER_INTERVAL_MINUTES = 15;
    private static final int REMINDER_INTERVAL_SECONDS = (int) TimeUnit.MINUTES.toSeconds(REMINDER_INTERVAL_MINUTES);
//  COMPLETED (58) Declare a SYNC_FLEXTIME_SECONDS constant at 15 minutes (equivalent to the reminder interval)
    private static final int SYNC_FLEXTIME_SECONDS = REMINDER_INTERVAL_SECONDS;

//  COMPLETED (59) Declare a private static boolean named sInitialized
    private static boolean sInitialized;

//  COMPLETED (81) Create a synchronized public static void method called initialize
    synchronized public static void initialize(@NonNull final Context context) {

//      COMPLETED (82) If the reminders have already been initialized, return
        if (sInitialized) return;

//      COMPLETED (83) Schedule the reminder to drink water when on WiFi
        scheduleWifiReminder(context);
//      COMPLETED (84) Schedule the reminder to drink water when the device is charging
        scheduleChargingReminder(context);

//      COMPLETED (85) Set sInitialized to true
        sInitialized = true;
    }

//  COMPLETED (77) Create a private static void method called scheduleWiFiReminder
    private static void scheduleWifiReminder(@NonNull final Context context) {
//      COMPLETED (78) Call scheduleReminder with the proper proper constraint
        int wifiConstraint = Constraint.ON_UNMETERED_NETWORK;
        scheduleReminder(context, wifiConstraint);
    }


//  COMPLETED (79) Create a private static void method called scheduleChargingReminder
    private static void scheduleChargingReminder(@NonNull final Context context) {
//      COMPLETED (80) Call scheduleReminder with the proper proper constraint
        int chargingConstraint = Constraint.DEVICE_CHARGING;
        scheduleReminder(context, chargingConstraint);
    }

//  COMPLETED (60) Create a private static void method called schedule reminder
//  COMPLETED (61) Add an int called constraint to the argument list to use in scheduleReminder
    /**
     * Schedules a repeating reminder to drink water using FirebaseJobDispatcher.
     *
     * @param context Context used to create the GooglePlayDriver that powers the
     *                FirebaseJobDispatcher
     */
    private static void scheduleReminder(@NonNull final Context context, int constraint) {

//      COMPLETED (62) Create a String variable called syncAction
        String syncAction;
//      COMPLETED (63) Assign the sync action to the proper ACTION constant from ReminderTasks
//      (hint - you can determine this from the constraint passed in)
        switch (constraint) {
            case Constraint.DEVICE_CHARGING:
                syncAction = ReminderTasks.ACTION_CHARGING_REMINDER;
                break;
            case Constraint.ON_UNMETERED_NETWORK:
                syncAction = ReminderTasks.ACTION_WIFI_REMINDER;
                break;
            default:
//              COMPLETED (64) Throw an IllegalArgumentException if we don't support the constraint
                throw new IllegalArgumentException("Constraint with value " + constraint + " not supported.");
        }

//      COMPLETED (65) Instantiate a new GooglePlayDriver
        Driver driver = new GooglePlayDriver(context);
//      COMPLETED (66) Instantiate a new FirebaseJobDispatcher
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);

//      COMPLETED (67) Create a new Job using the FirebaseJobDispatcher.newJobBuilder API
        /* Create the Job to periodically create reminders to drink water */
        Job constraintReminderJob = dispatcher.newJobBuilder()
                /* The Service that will be used to write to preferences */
//               COMPLETED (68) Set the Service to WaterReminderFirebaseJobService.class
                .setService(WaterReminderFirebaseJobService.class)
                /*
                 * Set the UNIQUE tag used to identify this Job. This tag will be used to determine
                 * which reminder count to increment.
                 */
//               COMPLETED (69) Set the proper action (using setTag)
                .setTag(syncAction)
                /*
                 * Network constraints on which this Job should run. In this app, the constraint
                 * is passed in to this method, as we are creating two reminders. One reminder
                 * will occur when the user is charging their device, and the other reminder will
                 * occur when the user is connected to WiFi.
                 * We choose to run on any
                 *
                 * In a normal app, it might be a good idea to include a preference for this,
                 * as different users may have different preferences on when you should be
                 * syncing your application's data. ( think $$$ here $-) )
                 */
//               COMPLETED (70) Set the proper constraint
                .setConstraints(constraint)
                /*
                 * setLifetime sets how long this job should persist. The options are to keep the
                 * Job "forever" or to have it die the next time the device boots up.
                 */
//               COMPLETED (71) Make sure this job will live throughout device reboots
                .setLifetime(Lifetime.FOREVER)
                /*
                 * We want these reminders to continuously happen, so we tell this Job to recur.
                 */
//               COMPLETED (72) Specify that this job should recur
                .setRecurring(true)
                /*
                 * We want the reminders to happen every 15 minutes or so. The first argument for
                 * Trigger class's static executionWindow method is the start of the time frame
                 * when the
                 * job should be performed. The second argument is the latest point in time at
                 * which the data should be synced. Please note that this end time is not
                 * guaranteed, but is more of a guideline for FirebaseJobDispatcher to go off of.
                 */
//               COMPLETED (73) Trigger this job according to the reminder interval you defined above
                .setTrigger(Trigger.executionWindow(
                        REMINDER_INTERVAL_SECONDS,
                        REMINDER_INTERVAL_SECONDS + SYNC_FLEXTIME_SECONDS))
                /*
                 * If a Job with the tag with provided already exists, this new job will replace
                 * the old one.
                 */
//               COMPLETED (74) Replace the current job if it exists
                .setReplaceCurrent(true)
                /* Once the Job is ready, call the builder's build method to return the Job */
//               COMPLETED (75) Build the job
                .build();

        /* Schedule the Job with the dispatcher */
//      COMPLETED (76) Schedule the job with the dispatcher
        dispatcher.schedule(constraintReminderJob);
    }
}