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
import android.os.AsyncTask;

import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.JobParameters;
// COMPLETED (2) Make sure you've imported the jobdispatcher.JobService, not job.JobService
import com.firebase.jobdispatcher.JobService;
import com.firebase.jobdispatcher.RetryStrategy;

// COMPLETED (3) Create a class called MyFirebaseJobService that extends JobService
public class WaterReminderFirebaseJobService extends JobService {

    // COMPLETED (4) Declare a private AsyncTask called mBackgroundTask
    private AsyncTask mBackgroundTask;

    /**
     * The entry point to your Job. Implementations should offload work to another thread of
     * execution as soon as possible.
     *
     * This is called by the Job Dispatcher to tell us we should start our job. Keep in mind this
     * method is run on the application's main thread, so we need to offload work to a background
     * thread.
     *
     * @return whether there is more work remaining.
     */
//  COMPLETED (5) Override onStartJob
    @Override
    public boolean onStartJob(final JobParameters jobParameters) {

//      COMPLETED (6) Store the job's tag in a final variable called jobAction
        final String jobAction = jobParameters.getTag();

//      COMPLETED (7) Instantiate mBackgroundTask with a new AsyncTask
        mBackgroundTask = new AsyncTask() {

//          COMPLETED (8) Override doInBackground and execute the task for the job's action using ReminderTasks
            @Override
            protected Object doInBackground(Object[] params) {
                Context context = WaterReminderFirebaseJobService.this;
                ReminderTasks.executeTask(context, jobAction);
                return null;
            }

//          COMPLETED (9) Override onPostExecute and to call jobFinished, passing in the job's parameters and false
            @Override
            protected void onPostExecute(Object o) {
                jobFinished(jobParameters, false);
            }
        };

//      COMPLETED (10) Execute the AsyncTask
        mBackgroundTask.execute();

//      COMPLETED (11) Return true as the AsyncTask will need some amount of time to complete its execution
        return true;
    }

    /**
     * Called when the scheduling engine has decided to interrupt the execution of a running job,
     * most likely because the runtime constraints associated with the job are no longer satisfied.
     *
     * @return whether the job should be retried
     * @see Job.Builder#setRetryStrategy(RetryStrategy)
     * @see RetryStrategy
     */
//  COMPLETED (12) Override onStopJob
    @Override
    public boolean onStopJob(JobParameters jobParameters) {
//      COMPLETED (13) Cancel mBackgroundTask if it isn't null
        if (mBackgroundTask != null) mBackgroundTask.cancel(true);
//      COMPLETED (14) Return true to designate we'd like our job rescheduled if it was stopped
        return true;
    }
}