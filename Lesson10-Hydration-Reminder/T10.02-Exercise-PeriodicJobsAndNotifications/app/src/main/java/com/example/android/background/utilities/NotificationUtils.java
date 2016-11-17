// TODO (18) Create a public class called NotificationUtils

//  TODO (19) Create a constant int called WATER_REMINDER_NOTIFICATION_ID

//  TODO (40) Create a method called remindUserBecauseCharging
//      TODO (41) Get a reference to the charging notification title
//      TODO (42) Get a reference to the charging notification text body
//      TODO (43) Call the remindUser method with the proper parameters


//  TODO (44) Create a method called remindUserBecauseWifi
//      TODO (45) Get a reference to the WiFi notification title
//      TODO (46) Get a reference to the WiFi notification text body
//      TODO (47) Call the remindUser method with the proper parameters


//  TODO (27) Create a public static method called remindUser
//  TODO (28) Add a String title and a String text to the argument list

//      TODO (29) Use NotificationCompat.Builder to build a notification

//               TODO (30) Set the color to colorPrimary using ContextCompat
//               TODO (31) Set the small icon to R.mipmap.ic_launcher
//               TODO (32) Set the large icon using your static largeIcon method
//               TODO (33) Set the title to the argument title
//               TODO (34) Set the text to the argument text
//               TODO (35) Allow the notification to expand
//               TODO (36) Set the content intent using our static contentIntent method
//               TODO (37) Set auto cancel to be true
//      TODO (38) Get a reference to the NotificationManager using context.getSystemService

//      TODO (39) Notify user using the notification ID defined above and the built notification


//  TODO (20) Create a private static method called contentIntent that returns a PendingIntent
//      TODO (21) Create an Intent to start the MainActivity
//      TODO (22) Return a PendingIntent using PendingIntent.getActivity


//  TODO (23) Create a private static method called largeIcon that returns a Bitmap
//      TODO (24) Get a reference to the resources
//      TODO (25) Create a Bitmap of the glass icon using BitmapFactory.decodeResource
//      TODO (26) Return the Bitmap