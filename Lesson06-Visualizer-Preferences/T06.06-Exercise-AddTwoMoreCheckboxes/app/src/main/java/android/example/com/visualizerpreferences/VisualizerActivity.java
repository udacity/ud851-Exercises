package android.example.com.visualizerpreferences;

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

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.example.com.visualizerpreferences.AudioVisuals.AudioInputReader;
import android.example.com.visualizerpreferences.AudioVisuals.VisualizerView;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class VisualizerActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final int MY_PERMISSION_RECORD_AUDIO_REQUEST_CODE = 88;
    private VisualizerView mVisualizerView;
    private AudioInputReader mAudioInputReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizer);
        mVisualizerView = (VisualizerView) findViewById(R.id.activity_visualizer);
        setupSharedPreferences();
        setupPermissions();
    }

    // TODO (4) Update the code in setupSharedPreferences and onSharedPreferenceChanged to update
    // mVisualizerView to show the current values stored in shared preferences.
    private void setupSharedPreferences() {
        // Get all of the values from shared preferences to set it up
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mVisualizerView.setShowBass(sharedPreferences.getBoolean(getString(R.string.pref_show_bass_key),
                getResources().getBoolean(R.bool.pref_show_bass_default)));
        mVisualizerView.setShowMid(true);
        mVisualizerView.setShowTreble(true);
        mVisualizerView.setMinSizeScale(1);
        mVisualizerView.setColor(getString(R.string.pref_color_red_value));
        // Register the listener
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.pref_show_bass_key))) {
            mVisualizerView.setShowBass(sharedPreferences.getBoolean(key, getResources().getBoolean(R.bool.pref_show_bass_default)));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregister VisualizerActivity as an OnPreferenceChangedListener to avoid any memory leaks.
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    /**
     * Methods for setting up the menu
     **/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
        MenuInflater inflater = getMenuInflater();
        /* Use the inflater's inflate method to inflate our visualizer_menu layout to this menu */
        inflater.inflate(R.menu.visualizer_menu, menu);
        /* Return true so that the visualizer_menu is displayed in the Toolbar */
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent startSettingsActivity = new Intent(this, SettingsActivity.class);
            startActivity(startSettingsActivity);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Below this point is code you do not need to modify; it deals with permissions
     * and starting/cleaning up the AudioInputReader
     **/

    /**
     * onPause Cleanup audio stream
     **/
    @Override
    protected void onPause() {
        super.onPause();
        if (mAudioInputReader != null) {
            mAudioInputReader.shutdown(isFinishing());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAudioInputReader != null) {
            mAudioInputReader.restart();
        }
    }
    
    /**
     * App Permissions for Audio
     **/
    private void setupPermissions() {
        // If we don't have the record audio permission...
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            // And if we're on SDK M or later...
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // Ask again, nicely, for the permissions.
                String[] permissionsWeNeed = new String[]{ Manifest.permission.RECORD_AUDIO };
                requestPermissions(permissionsWeNeed, MY_PERMISSION_RECORD_AUDIO_REQUEST_CODE);
            }
        } else {
            // Otherwise, permissions were granted and we are ready to go!
            mAudioInputReader = new AudioInputReader(mVisualizerView, this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_RECORD_AUDIO_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // The permission was granted! Start up the visualizer!
                    mAudioInputReader = new AudioInputReader(mVisualizerView, this);

                } else {
                    Toast.makeText(this, "Permission for audio not granted. Visualizer can't run.", Toast.LENGTH_LONG).show();
                    finish();
                    // The permission was denied, so we can show a message why we can't run the app
                    // and then close the app.
                }
            }
            // Other permissions could go down here

        }
    }
}