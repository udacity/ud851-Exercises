package com.example.android.boardingpass;

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

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.android.boardingpass.databinding.ActivityMainBinding;
import com.example.android.boardingpass.utilities.FakeDataUtils;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*
         * DataBindUtil.setContentView replaces our normal call of setContent view.
         * DataBindingUtil also created our ActivityMainBinding that we will eventually use to
         * display all of our data.
         */
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        BoardingPassInfo fakeBoardingInfo = FakeDataUtils.generateFakeBoardingPassInfo();
        displayBoardingPassInfo(fakeBoardingInfo);
    }

    private void displayBoardingPassInfo(BoardingPassInfo info) {

        mBinding.textViewPassengerName.setText(info.passengerName);
        // COMPLETED (7) Use the flightInfo attribute in mBinding below to get the appropiate text Views

        ((TextView)mBinding.flightInfo.findViewById(R.id.textViewOriginAirport)).setText(info.originCode);
        ((TextView)mBinding.flightInfo.findViewById(R.id.textViewFlightCode)).setText(info.flightCode);
       ((TextView)mBinding.flightInfo.findViewById(R.id.textViewDestinationAirport)).setText(info.destCode);

        SimpleDateFormat formatter = new SimpleDateFormat(getString(R.string.timeFormat), Locale.getDefault());
        String boardingTime = formatter.format(info.boardingTime);
        String departureTime = formatter.format(info.departureTime);
        String arrivalTime = formatter.format(info.arrivalTime);

       ((TextView)mBinding.flightInfo.findViewById(R.id.textViewBoardingTime)).setText(boardingTime);
        ((TextView)mBinding.flightInfo.findViewById(R.id.textViewDepartureTime)).setText(departureTime);
        ((TextView)mBinding.flightInfo.findViewById(R.id.textViewArrivalTime)).setText(arrivalTime);

        long totalMinutesUntilBoarding = info.getMinutesUntilBoarding();
        long hoursUntilBoarding = TimeUnit.MINUTES.toHours(totalMinutesUntilBoarding);
        long minutesLessHoursUntilBoarding =
                totalMinutesUntilBoarding - TimeUnit.HOURS.toMinutes(hoursUntilBoarding);

        String hoursAndMinutesUntilBoarding = getString(R.string.countDownFormat,
                hoursUntilBoarding,
                minutesLessHoursUntilBoarding);

        ((TextView)mBinding.flightInfo.findViewById(R.id.textViewBoardingInCountdown)).setText(hoursAndMinutesUntilBoarding);
        // COMPLETED (8) Use the boardingInfo attribute in mBinding below to get the appropiate text Views
        ((TextView)mBinding.boardingInfo.findViewById(R.id.textViewTerminal)).setText(info.departureTerminal);
        ((TextView)mBinding.boardingInfo.findViewById(R.id.textViewGate)).setText(info.departureGate);
        ((TextView)mBinding.boardingInfo.findViewById(R.id.textViewSeat)).setText(info.seatNumber);
    }
}

