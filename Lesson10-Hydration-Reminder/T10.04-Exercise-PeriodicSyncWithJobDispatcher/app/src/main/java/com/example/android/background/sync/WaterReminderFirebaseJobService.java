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

import android.os.AsyncTask;

import com.firebase.jobdispatcher.JobService;
import com.firebase.jobdispatcher.JobParameters;

// COMPLETED WaterReminderFirebaseJobService should extend from JobService
public class WaterReminderFirebaseJobService extends JobService {

    AsyncTask mBackgroudTask;

    // COMPLETED Override onStartJob
    @Override
    public boolean onStartJob(final JobParameters params) {

        // COMPLETED By default, jobs are executed on the main thread, so make an anonymous class extending
        //  AsyncTask called mBackgroundTask.
        mBackgroudTask = new AsyncTask() {
            @Override
            // COMPLETED Override doInBackground
            // COMPLETED Use ReminderTasks to execute the new charging reminder task you made, use
            // this service as the context (WaterReminderFirebaseJobService.this) and return null
            // when finished.
            protected Object doInBackground(Object[] objects) {
                ReminderTasks.executeTask(WaterReminderFirebaseJobService.this,
                        ReminderTasks.ACTION_CHARGING_REMINDER_NOTIFICATION);
                return null;
            }

            @Override
            // COMPLETED Override onPostExecute and called jobFinished. Pass the job parameters
            // and false to jobFinished. This will inform the JobManager that your job is done
            // and that you do not want to reschedule the job.
            protected void onPostExecute(Object o) {
                jobFinished(params, false);
            }
        };

        // COMPLETED Execute the AsyncTask
        mBackgroudTask.execute();
        // COMPLETED Return true
        return true;
    }


    // COMPLETED Override onStopJob
    @Override
    public boolean onStopJob(JobParameters params) {
        // COMPLETED If mBackgroundTask is valid, cancel it
        if (mBackgroudTask != null) {
            mBackgroudTask.cancel(true);
        }
        // COMPLETED Return true to signify the job should be retried
        return true;
    }
}
