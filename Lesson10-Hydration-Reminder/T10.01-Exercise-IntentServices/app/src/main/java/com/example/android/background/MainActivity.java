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
package com.example.android.background;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.background.utilities.PreferenceUtilities;

public class MainActivity extends AppCompatActivity implements
        SharedPreferences.OnSharedPreferenceChangeListener {

    private TextView mWaterCountDisplay;
    private TextView mChargingCountDisplay;
    private TextView mWifiReminderCountDisplay;

    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWaterCountDisplay = (TextView) findViewById(R.id.tv_water_count);
        mChargingCountDisplay = (TextView) findViewById(R.id.tv_charging_reminder_count);
        mWifiReminderCountDisplay = (TextView) findViewById(R.id.tv_wifi_reminder_count);

        updateFormattedWaterCount();
        updateChargingReminderCount();
        updateWiFiReminderCount();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);
    }

    private void updateFormattedWaterCount() {
        int waterCount = PreferenceUtilities.getWaterCount(this);
        String waterCountDisplayFormat = getString(R.string.water_count_format);
        String formattedWaterCount = String.format(
                waterCountDisplayFormat,
                waterCount);

        mWaterCountDisplay.setText(formattedWaterCount);
    }

    private void updateChargingReminderCount() {
        int chargingReminders = PreferenceUtilities.getChargingReminderCount(this);
        String chargingReminderCountFormat = getString(R.string.charging_reminder_count_format);
        String formattedChargingReminders = String.format(
                chargingReminderCountFormat,
                chargingReminders);

        mChargingCountDisplay.setText(formattedChargingReminders);
    }

    private void updateWiFiReminderCount() {
        int wifiReminders = PreferenceUtilities.getWifiReminderCount(this);
        String wiFiReminderCountFormat = getString(R.string.wifi_reminder_count_format);
        String formattedWifiReminderCount = String.format(
                wiFiReminderCountFormat,
                wifiReminders);

        mWifiReminderCountDisplay.setText(formattedWifiReminderCount);
    }

    public void incrementWater(View view) {
        if (mToast != null) mToast.cancel();
        mToast = Toast.makeText(this, R.string.water_chug, Toast.LENGTH_SHORT);
        mToast.show();

//      TODO (15) Start WaterReminderIntentService to increment the water count when the button is pressed
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        if (PreferenceUtilities.KEY_WATER_COUNT.equals(key)) {
            updateFormattedWaterCount();
        } else if (PreferenceUtilities.KEY_CHARGING_REMINDER_COUNT.equals(key)) {
            updateChargingReminderCount();
        } else if (PreferenceUtilities.KEY_WIFI_REMINDER_COUNT.equals(key)) {
            updateWiFiReminderCount();
        }
    }
}