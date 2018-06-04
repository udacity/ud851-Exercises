package com.example.android.background.utilities;


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
import android.support.v4.app.NotificationManagerCompat;

import com.example.android.background.MainActivity;
import com.example.android.background.R;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Utility class for creating hydration notifications
 */
public class NotificationUtils {

    public static final int PENDING_INTENT_ID = 51;
    public static final String CHANNEL_ID = "channel_id";
    public static final int NOTIFICATION_ID = 15;

    // TODO (7) Create a method called remindUserBecauseCharging which takes a Context.
    // This method will create a notification for charging. It might be helpful
    // to take a look at this guide to see an example of what the code in this method will look like:
    // https://developer.android.com/training/notify-user/build-notification.html
        // TODO (8) Get the NotificationManager using context.getSystemService
        // TODO (9) Create a notification channel for Android O devices
        // TODO (10) In the remindUser method use NotificationCompat.Builder to create a notification
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
        // TODO (11) If the build version is greater than JELLY_BEAN and lower than OREO,
        // set the notification's priority to PRIORITY_HIGH.
        // TODO (12) Trigger the notification by calling notify on the NotificationManager.
        // Pass in a unique ID of your choosing for the notification and notificationBuilder.build()

    public static void remindUserBecauseCharging(Context context){

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = context.getString(R.string.charging_reminder_notification_title);
            String description = context.getString(R.string.charging_reminder_notification_body);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(description);
            mChannel.setVibrationPattern(new long[] {1000, 1000, 1000});
            notificationManager.createNotificationChannel(mChannel);
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_drink_notification)
                .setContentTitle(context.getString(R.string.charging_reminder_notification_title))
                .setContentText(context.getString(R.string.charging_reminder_notification_body))
                .setStyle(new NotificationCompat.BigTextStyle()
                .bigText(context.getString(R.string.charging_reminder_notification_body)))
                .setVibrate(new long[] {1000, 1000, 1000});
        if((Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) && (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) ){
            mBuilder.setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setContentIntent(contentIntent(context))
                    .setAutoCancel(true);
        }else {
            mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(contentIntent(context))
                    .setAutoCancel(true);
        }

        notificationManager.notify(NOTIFICATION_ID, mBuilder.build());


    }

    // TODO (1) Create a helper method called contentIntent with a single parameter for a Context. It
    // should return a PendingIntent. This method will create the pending intent which will trigger when
    // the notification is pressed. This pending intent should open up the MainActivity.
        // TODO (2) Create an intent that opens up the MainActivity
        // TODO (3) Create a PendingIntent using getActivity that:
            // - Take the context passed in as a parameter
            // - Takes an unique integer ID for the pending intent (you can create a constant for
            //   this integer above
            // - Takes the intent to open the MainActivity you just created; this is what is triggered
            //   when the notification is triggered
            // - Has the flag FLAG_UPDATE_CURRENT, so that if the intent is created again, keep the
            // intent but update the data
    public static PendingIntent contentIntent(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, PENDING_INTENT_ID, intent, FLAG_UPDATE_CURRENT);

        return pendingIntent;
    }


    // TODO (4) Create a helper method called largeIcon which takes in a Context as a parameter and
    // returns a Bitmap. This method is necessary to decode a bitmap needed for the notification.
        // TODO (5) Get a Resources object from the context.
        // TODO (6) Create and return a bitmap using BitmapFactory.decodeResource, passing in the
        // resources object and R.drawable.ic_local_drink_black_24px
    public static Bitmap largeIcon(Context context){
        Resources resources = context.getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_local_drink_black_24px);
        return bitmap;
    }

}
