// TODO (56) Create a class called ReminderUtility

//  TODO (57) Declare a REMINDER_INTERVAL_SECONDS constant at 15 minutes
//  TODO (58) Declare a SYNC_FLEXTIME_SECONDS constant at 15 minutes (equivalent to the reminder interval)


//  TODO (59) Declare a private static boolean named sInitialized


//  TODO (81) Create a synchronized public static void method called initialize
//      TODO (82) If the reminders have already been initialized, return
//      TODO (83) Schedule the reminder to drink water when on WiFi
//      TODO (84) Schedule the reminder to drink water when the device is charging
//      TODO (85) Set sInitialized to true


//  TODO (77) Create a private static void method called scheduleWiFiReminder
//      TODO (78) Call scheduleReminder with the proper proper constraint


//  TODO (79) Create a private static void method called scheduleChargingReminder
//      TODO (80) Call scheduleReminder with the proper proper constraint


//  TODO (60) Create a private static void method called schedule reminder
//  TODO (61) Add an int called constraint to the argument list to use in scheduleReminder
//      TODO (62) Create a String variable called syncAction
//      TODO (63) Assign the sync action to the proper ACTION constant from ReminderTasks
//      TODO (64) Throw an IllegalArgumentException if we don't support the constraint
//      TODO (65) Instantiate a new GooglePlayDriver
//      TODO (66) Instantiate a new FirebaseJobDispatcher
//      TODO (67) Create a new Job using the FirebaseJobDispatcher.newJobBuilder API
//               TODO (68) Set the Service to WaterReminderFirebaseJobService.class
//               TODO (69) Set the proper action (using setTag)
//               TODO (70) Set the proper constraint
//               TODO (71) Make sure this job will live throughout device reboots
//               TODO (72) Specify that this job should recur
//               TODO (73) Trigger this job according to the reminder interval you defined above
//               TODO (74) Replace the current job if it exists
//               TODO (75) Build the job
//      TODO (76) Schedule the job with the dispatcher