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

import com.firebase.jobdispatcher.*;


// TODO (3) WaterReminderFirebaseJobService should extend from JobService

public class WaterReminderFirebaseJobService extends JobService {

    //needed to make a AsyncTask variable to store the
    private AsyncTask mBackGroundTask;




    // TODO (4) Override onStartJob
    // TODO (5) By default, jobs are executed on the main thread, so make an anonymous class extending
    //  AsyncTask called mBackgroundTask.
    // TODO (6) Override doInBackground
    // TODO (7) Use ReminderTasks to execute the new charging reminder task you made, use
    // this service as the context (WaterReminderFirebaseJobService.this) and return null
    // when finished.
    // TODO (8) Override onPostExecute and called jobFinished. Pass the job parameters
    // and false to jobFinished. This will inform the JobManager that your job is done
    // and that you do not want to reschedule the job.

    // TODO (9) Execute the AsyncTask
    // TODO (10) Return true

    @Override
    public boolean onStartJob(final JobParameters job) {
        //should have use a no params constructor since I'm just passing in a job
        mBackGroundTask = new AsyncTask(){
            @Override
            protected Object doInBackground(Object[] objects) {
                ReminderTasks.executeTask(WaterReminderFirebaseJobService.this, ReminderTasks.ACTION_CHARGING_REMINDER);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                jobFinished(job, false);
            }


        };

        mBackGroundTask.execute();


        return true;
    }

    // TODO (11) Override onStopJob
    // TODO (12) If mBackgroundTask is valid, cancel it
    // TODO (13) Return true to signify the job should be retried


    @Override
    public boolean onStopJob(JobParameters job) {
        if(mBackGroundTask != null){
            mBackGroundTask.cancel(true);
        }
        return true;
    }
}
