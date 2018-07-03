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
package com.example.android.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private static final int NUM_LIST_ITEMS = 100;

    /*
     * References to RecyclerView and Adapter to reset the list to its
     * "pretty" state when the reset menu item is clicked.
     */
    private GreenAdapter mAdapter;
    private RecyclerView mNumbersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * Using findViewById, we get a reference to our RecyclerView from xml. This allows us to
         * do things like set the adapter of the RecyclerView and toggle the visibility.
         */
        mNumbersList = (RecyclerView) findViewById(R.id.rv_numbers);

        /*
         * A LinearLayoutManager is responsible for measuring and positioning item views within a
         * RecyclerView into a linear list. This means that it can produce either a horizontal or
         * vertical list depending on which parameter you pass in to the LinearLayoutManager
         * constructor. By default, if you don't specify an orientation, you get a vertical list.
         * In our case, we want a vertical list, so we don't need to pass in an orientation flag to
         * the LinearLayoutManager constructor.
         *
         * There are other LayoutManagers available to display your data in uniform grids,
         * staggered grids, and more! See the developer documentation for more details.
         */
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mNumbersList.setLayoutManager(layoutManager);

        /*
         * Use this setting to improve performance if you know that changes in content do not
         * change the child layout size in the RecyclerView
         */
        mNumbersList.setHasFixedSize(true);

        /*
         * The GreenAdapter is responsible for displaying each item in the list.
         */
        mAdapter = new GreenAdapter(NUM_LIST_ITEMS);
        mNumbersList.setAdapter(mAdapter);
    }

    // COMPLETED Create a menu resource in res/menu/ called main.xml
    // COMPLETED Add one item to the menu with an ID of action_refresh
    // COMPLETED Set the title of the menu item to "Refresh" using strings.xml
    // COMPLETED Set the orderInCategory value to 1 to make sure this item is the first in the list
    // COMPLETED Set app:showAsAction to ifRoom to display the menu item in the ActionBar if there is room


    // COMPLETED Override onCreateOptionsMenu
    // COMPLETED Use getMenuInflater().inflate to inflate the menu
    // COMPLETED Return true to display this menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    // COMPLETED Override onOptionsItemSelected
    // COMPLETED Within this method, get the ID from the MenuItem
    // COMPLETED If the ID equals R.id.action_refresh, create and set a new adapter on the RecyclerView and return true
    // COMPLETED For now, for all other IDs, return super.onOptionsItemSelected

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_reset) {
            GreenAdapter greenAdapter = new GreenAdapter(NUM_LIST_ITEMS);
            mNumbersList.setAdapter(greenAdapter);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
