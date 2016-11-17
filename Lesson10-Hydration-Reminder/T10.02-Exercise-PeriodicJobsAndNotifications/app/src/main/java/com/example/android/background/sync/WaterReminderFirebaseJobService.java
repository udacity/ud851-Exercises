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
// TODO (2) Make sure you've imported the jobdispatcher.JobService, not job.JobService
// TODO (3) Create a class called MyFirebaseJobService that extends JobService

//  TODO (4) Declare a private AsyncTask called mBackgroundTask
//  TODO (5) Override onStartJob

//      TODO (6) Store the job's tag in a final variable called jobAction

//      TODO (7) Instantiate mBackgroundTask with a new AsyncTask

//          TODO (8) Override doInBackground and execute the task for the job's action using ReminderTasks

//          TODO (9) Override onPostExecute and to call jobFinished, passing in the job's parameters and false

//      TODO (10) Execute the AsyncTask

//      TODO (11) Return true as the AsyncTask will need some amount of time to complete its execution


//  TODO (12) Override onStopJob
//      TODO (13) Cancel mBackgroundTask if it isn't null
//      TODO (14) Return true to designate we'd like our job rescheduled if it was stopped