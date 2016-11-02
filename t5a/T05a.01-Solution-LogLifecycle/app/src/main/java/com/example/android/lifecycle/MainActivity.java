package com.example.android.lifecycle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /*
     * This tag will be used for logging. It is best practice to use the class's name using
     * getSimpleName as that will greatly help to identify the location from which your logs are
     * being posted.
     */
    private static final String TAG = MainActivity.class.getSimpleName();

    /* Constant values for the names of each respective lifecycle callback */
    private static final String ON_CREATE = "onCreate";
    private static final String ON_START = "onStart";
    private static final String ON_RESUME = "onResume";
    private static final String ON_PAUSE = "onPause";
    private static final String ON_STOP = "onStop";
    private static final String ON_RESTART = "onRestart";
    private static final String ON_DESTROY = "onDestroy";
    private static final String ON_SAVE_INSTANCE_STATE = "onSaveInstanceState";

    /*
     * This TextView will contain a running log of every lifecycle callback method called from this
     * Activity. This TextView can be reset to its default state by clicking the Button labeled
     * "Reset Log"
     */
    private TextView mLifecycleDisplay;

    /**
     * Called when the activity is first created. This is where you should do all of your normal
     * static set up: create views, bind data to lists, etc.
     *
     * Always followed by onStart().
     *
     * @param savedInstanceState The Activity's previously frozen state, if there was one.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLifecycleDisplay = (TextView) findViewById(R.id.tv_lifecycle_events_display);

        // COMPLETED (1) Use logAndAppend within onCreate
        logAndAppend(ON_CREATE);
    }

    // COMPLETED (2) Override onStart, call super.onStart, and call logAndAppend with ON_START
    /**
     * Called when the activity is becoming visible to the user.
     *
     * Followed by onResume() if the activity comes to the foreground, or onStop() if it becomes
     * hidden.
     */
    @Override
    protected void onStart() {
        super.onStart();

        logAndAppend(ON_START);
    }

    // COMPLETED (3) Override onResume, call super.onResume, and call logAndAppend with ON_RESUME
    /**
     * Called when the activity will start interacting with the user. At this point your activity
     * is at the top of the activity stack, with user input going to it.
     *
     * Always followed by onPause().
     */
    @Override
    protected void onResume() {
        super.onResume();

        logAndAppend(ON_RESUME);
    }

    // COMPLETED (4) Override onPause, call super.onPause, and call logAndAppend with ON_PAUSE
    /**
     * Called when the system is about to start resuming a previous activity. This is typically
     * used to commit unsaved changes to persistent data, stop animations and other things that may
     * be consuming CPU, etc. Implementations of this method must be very quick because the next
     * activity will not be resumed until this method returns.
     *
     * Followed by either onResume() if the activity returns back to the front, or onStop() if it
     * becomes invisible to the user.
     */
    @Override
    protected void onPause() {
        super.onPause();

        logAndAppend(ON_PAUSE);
    }

    // COMPLETED (5) Override onStop, call super.onStop, and call logAndAppend with ON_STOP
    /**
     * Called when the activity is no longer visible to the user, because another activity has been
     * resumed and is covering this one. This may happen either because a new activity is being
     * started, an existing one is being brought in front of this one, or this one is being
     * destroyed.
     *
     * Followed by either onRestart() if this activity is coming back to interact with the user, or
     * onDestroy() if this activity is going away.
     */
    @Override
    protected void onStop() {
        super.onStop();

        logAndAppend(ON_STOP);
    }

    // COMPLETED (6) Override onRestart, call super.onRestart, and call logAndAppend with ON_RESTART
    /**
     * Called after your activity has been stopped, prior to it being started again.
     *
     * Always followed by onStart()
     */
    @Override
    protected void onRestart() {
        super.onRestart();

        logAndAppend(ON_RESTART);
    }

    // COMPLETED (7) Override onDestroy, call super.onDestroy, and call logAndAppend with ON_DESTROY
    /**
     * The final call you receive before your activity is destroyed. This can happen either because
     * the activity is finishing (someone called finish() on it, or because the system is
     * temporarily destroying this instance of the activity to save space. You can distinguish
     * between these two scenarios with the isFinishing() method.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();

        logAndAppend(ON_DESTROY);
    }

    /**
     * Logs to the console and appends the lifecycle method name to the TextView so that you can
     * view the series of method callbacks that are called both from the app and from within
     * Android Studio's Logcat.
     *
     * @param lifecycleEvent The name of the event to be logged.
     */
    private void logAndAppend(String lifecycleEvent) {
        Log.d(TAG, "Lifecycle Event: " + lifecycleEvent);

        mLifecycleDisplay.append(lifecycleEvent + "\n");
    }

    /**
     * This method resets the contents of the TextView to its default text of "Lifecycle callbacks"
     *
     * @param view The View that was clicked. In this case, it is the Button from our layout.
     */
    public void resetLifecycleDisplay(View view) {
        mLifecycleDisplay.setText("Lifecycle callbacks:\n");
    }
}
