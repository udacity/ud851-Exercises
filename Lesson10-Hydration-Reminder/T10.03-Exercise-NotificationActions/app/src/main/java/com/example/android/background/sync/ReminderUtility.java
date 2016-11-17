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

public class ReminderUtility {

    /*
     * Interval at which to remind the user to drink water. Use TimeUnit for convenience, rather
     * than writing out a bunch of multiplication ourselves and risk making a silly mistake.
     */
    private static final int REMINDER_INTERVAL_MINUTES = 15;
    private static final int REMINDER_INTERVAL_SECONDS = (int) TimeUnit.MINUTES.toSeconds(REMINDER_INTERVAL_MINUTES);
    private static final int SYNC_FLEXTIME_SECONDS = REMINDER_INTERVAL_SECONDS;

    private static boolean sInitialized;

    synchronized public static void initialize(@NonNull final Context context) {

        if (sInitialized) return;

        scheduleWifiReminder(context);
        scheduleChargingReminder(context);

        sInitialized = true;
    }

    private static void scheduleWifiReminder(@NonNull final Context context) {
        int wifiConstraint = Constraint.ON_UNMETERED_NETWORK;
        scheduleReminder(context, wifiConstraint);
    }

    private static void scheduleChargingReminder(@NonNull final Context context) {
        int chargingConstraint = Constraint.DEVICE_CHARGING;
        scheduleReminder(context, chargingConstraint);
    }

    /**
     * Schedules a repeating reminder to drink water using FirebaseJobDispatcher.
     *
     * @param context Context used to create the GooglePlayDriver that powers the
     *                FirebaseJobDispatcher
     */
    private static void scheduleReminder(@NonNull final Context context, int constraint) {

        String syncAction;

        switch (constraint) {
            case Constraint.DEVICE_CHARGING:
                syncAction = ReminderTasks.ACTION_CHARGING_REMINDER;
                break;
            case Constraint.ON_UNMETERED_NETWORK:
                syncAction = ReminderTasks.ACTION_WIFI_REMINDER;
                break;
            default:
                throw new IllegalArgumentException("Constraint with value " + constraint + " not supported.");
        }

        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);

        /* Create the Job to periodically create reminders to drink water */
        Job constraintReminderJob = dispatcher.newJobBuilder()
                /* The Service that will be used to write to preferences */
                .setService(WaterReminderFirebaseJobService.class)
                /*
                 * Set the UNIQUE tag used to identify this Job. This tag will be used to determine
                 * which reminder count to increment.
                 */
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
                .setConstraints(constraint)
                /*
                 * setLifetime sets how long this job should persist. The options are to keep the
                 * Job "forever" or to have it die the next time the device boots up.
                 */
                .setLifetime(Lifetime.FOREVER)
                /*
                 * We want these reminders to continuously happen, so we tell this Job to recur.
                 */
                .setRecurring(true)
                /*
                 * We want the reminders to happen every 15 minutes or so. The first argument for
                 * Trigger class's static executionWindow method is the start of the time frame
                 * when the
                 * job should be performed. The second argument is the latest point in time at
                 * which the data should be synced. Please note that this end time is not
                 * guaranteed, but is more of a guideline for FirebaseJobDispatcher to go off of.
                 */
                .setTrigger(Trigger.executionWindow(
                        REMINDER_INTERVAL_SECONDS,
                        REMINDER_INTERVAL_SECONDS + SYNC_FLEXTIME_SECONDS))
                /*
                 * If a Job with the tag with provided already exists, this new job will replace
                 * the old one.
                 */
                .setReplaceCurrent(true)
                /* Once the Job is ready, call the builder's build method to return the Job */
                .build();

        /* Schedule the Job with the dispatcher */
        dispatcher.schedule(constraintReminderJob);
    }
}