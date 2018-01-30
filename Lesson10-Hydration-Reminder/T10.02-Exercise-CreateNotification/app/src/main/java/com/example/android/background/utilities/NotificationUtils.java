package com.example.android.background.utilities;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.example.android.background.MainActivity;
import com.example.android.background.R;

/**
 * Utility class for creating hydration notifications
 */
public class NotificationUtils {

    private static int WATER_DRINK_NOTIFICATION_ID = 999;
    private static String WATER_DRINK_NOTIFICATION_CHANNEL = "water-drink-notification-channel";

    // COMPLETED Create a method called remindUserBecauseCharging which takes a Context.
    public static void remindUserBecauseCharging(Context context) {
        // This method will create a notification for charging. It might be helpful
        // to take a look at this guide to see an example of what the code in this method will look like:
        // https://developer.android.com/training/notify-user/build-notification.html
        // COMPLETED Get the NotificationManager using context.getSystemService
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // COMPLETED Create a notification channel for Android O devices
        if (Build.VERSION.SDK_INT >=  Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(WATER_DRINK_NOTIFICATION_CHANNEL,
                    context.getString(R.string.water_notification_channel_name),
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        // COMPLETED In the remindUser method use NotificationCompat.Builder to create a notification
        // that:
        // - has a color of R.colorPrimary - use ContextCompat.getColor to get a compatible color
        // - has ic_drink_notification as the small icon
        // - uses icon returned by the largeIcon helper method as the large icon
        // - sets the title to the charging_reminder_notification_title String resource
        // - sets the text to the charging_reminder_notification_body String resource
        // - sets the style to NotificationCompat.BigTextStyle().bigText(text)
        // - sets the notification defaults to vibrate
        // - uses the content intent returned by the contentIntent helper method for the contentIntent
        // - automatically cancels the notification when the notification is clicked
        NotificationCompat.Builder notificationBuilder = new  NotificationCompat.Builder(context, WATER_DRINK_NOTIFICATION_CHANNEL)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_drink_notification)
                .setLargeIcon(largeIcon(context))
                .setContentTitle(context.getString(R.string.charging_reminder_notification_title))
                .setContentText(context.getString(R.string.charging_reminder_notification_body))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(context.getString(R.string.charging_reminder_notification_body)))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context))
                .setAutoCancel(true);

        // COMPLETED If the build version is greater than JELLY_BEAN and lower than OREO,
        // set the notification's priority to PRIORITY_HIGH.
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN &&
                Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            notificationBuilder.setPriority(NotificationManager.IMPORTANCE_HIGH);
        }
        // COMPLETED Trigger the notification by calling notify on the NotificationManager.
        notificationManager.notify(WATER_DRINK_NOTIFICATION_ID, notificationBuilder.build());
        // Pass in a unique ID of your choosing for the notification and notificationBuilder.build()
    }

    // COMPLETED Create a helper method called contentIntent with a single parameter for a Context. It
    private static PendingIntent contentIntent(Context context) {
        // should return a PendingIntent. This method will create the pending intent which will trigger when
        // the notification is pressed. This pending intent should open up the MainActivity.
        // COMPLETED Create an intent that opens up the MainActivity
        Intent mainActivityIntent = new Intent(context, MainActivity.class);
        // COMPLETED Create a PendingIntent using getActivity that:
        // - Take the context passed in as a parameter
        // - Takes an unique integer ID for the pending intent (you can create a constant for
        //   this integer above
        // - Takes the intent to open the MainActivity you just created; this is what is triggered
        //   when the notification is triggered
        // - Has the flag FLAG_UPDATE_CURRENT, so that if the intent is created again, keep the
        // intent but update the data
        return PendingIntent.getActivity(context,
                WATER_DRINK_NOTIFICATION_ID,
                mainActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }


    // COMPLETED Create a helper method called largeIcon which takes in a Context as a parameter and
    private static Bitmap largeIcon(Context context) {
        // returns a Bitmap. This method is necessary to decode a bitmap needed for the notification.
        // COMPLETED Get a Resources object from the context.
        Resources resources = context.getResources();
        // COMPLETED Create and return a bitmap using BitmapFactory.decodeResource, passing in the
        // resources object and R.drawable.ic_local_drink_black_24px
        return BitmapFactory.decodeResource(resources, R.drawable.ic_local_drink_black_24px);
    }

}
