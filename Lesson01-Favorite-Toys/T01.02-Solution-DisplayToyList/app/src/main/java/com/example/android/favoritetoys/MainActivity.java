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
package com.example.android.favoritetoys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // COMPLETED (1) Declare a TextView variable called mToysListTextView
    private TextView mToysListTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // COMPLETED (3) Use findViewById to get a reference to the TextView from the layout
        /*
         * Using findViewById, we get a reference to our TextView from xml. This allows us to
         * do things like set the text of the TextView.
         */
        mToysListTextView = (TextView) findViewById(R.id.tv_toy_names);

        // COMPLETED (4) Use the static ToyBox.getToyNames method and store the names in a String array
        /*
         * This String array contains names of classic toys. After all, these are toy apps. We
         * wanted to create a way to break concepts down into smaller pieces that we thought might
         * be a little easier to understand. In each lesson, we'll demonstrate new concepts using a
         * toy app (no, sadly every one won't have ACTUAL toys in it) and then we'll guide you
         * through adding the functionality that you've just learned by having you use those
         * concepts in Sunshine! Let us know what you think! We're really excited to have you
         * taking this course.
         */
        String[] toyNames = ToyBox.getToyNames();

        // COMPLETED (5) Loop through each toy and append the name to the TextView (add \n for spacing)
        /*
         * Iterate through the array and append the Strings to the TextView. The reason why we add
         * the "\n\n\n" after the String is to give visual separation between each String in the
         * TextView. Later, we'll learn about a better way to display lists of data.
         */
        for (String toyName : toyNames) {
            mToysListTextView.append(toyName + "\n\n\n");
        }
    }
}