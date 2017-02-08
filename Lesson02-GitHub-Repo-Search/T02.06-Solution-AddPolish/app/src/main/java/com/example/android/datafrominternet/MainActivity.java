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
package com.example.android.datafrominternet;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.datafrominternet.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText mSearchBoxEditText;

    private TextView mUrlDisplayTextView;

    private TextView mSearchResultsTextView;

    // COMPLETED (18) Create a variable to store a reference to the error message TextView
    private TextView mErrorMessageDisplay;

    // COMPLETED (20) Create a ProgressBar variable to store a reference to the ProgressBar
    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchBoxEditText = (EditText) findViewById(R.id.et_search_box);

        mUrlDisplayTextView = (TextView) findViewById(R.id.tv_url_display);
        mSearchResultsTextView = (TextView) findViewById(R.id.tv_github_search_results_json);

        // COMPLETED (19) Get a reference to the error TextView using findViewById
        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);

        // COMPLETED (21) Get a reference to the ProgressBar using findViewById
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
    }

    /**
     * This method retrieves the search text from the EditText, constructs the
     * URL (using {@link NetworkUtils}) for the github repository you'd like to find, displays
     * that URL in a TextView, and finally fires off an AsyncTask to perform the GET request using
     * our {@link GithubQueryTask}
     */
    private void makeGithubSearchQuery() {
        String githubQuery = mSearchBoxEditText.getText().toString();
        URL githubSearchUrl = NetworkUtils.buildUrl(githubQuery);
        mUrlDisplayTextView.setText(githubSearchUrl.toString());
        new GithubQueryTask().execute(githubSearchUrl);
    }

    // COMPLETED (22) Create a method called showJsonDataView to show the data, hide the error and progress bar
    /**
     * This method will make the View for the JSON data visible and
     * hide the error message and progress bar.
     * <p>
     * Since it is okay to redundantly set the visibility of a View, we don't
     * need to check whether each view is currently visible or invisible.
     */
    private void showJsonDataView() {
        // First, make sure the error is invisible
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        // Hide the progress bar
        mProgressBar.setVisibility(View.INVISIBLE);
        // Then, make sure the JSON data is visible
        mSearchResultsTextView.setVisibility(View.VISIBLE);
    }

    // COMPLETED (23) Create a method called showErrorMessage to show the error, and hide the data and progress bar
    /**
     * This method will make the error message visible and hide the JSON
     * View and progress bar.
     * <p>
     * Since it is okay to redundantly set the visibility of a View, we don't
     * need to check whether each view is currently visible or invisible.
     */
    private void showErrorMessage() {
        // First, hide the currently visible data
        mSearchResultsTextView.setVisibility(View.INVISIBLE);
        // Hide the progress bar
        mProgressBar.setVisibility(View.INVISIBLE);
        // Then, show the error
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }
    
    // COMPLETED (24) Create a method called showProgressBar to show the progress bar, and hide the data or error
    /**
     * This method will make the progress bar visible and hide the JSON
     * View and the error message.
     * <p>
     * Since it is okay to redundantly set the visibility of a View, we don't
     * need to check whether each view is currently visible or invisible.
     */
    private void showProgressBar() {
        // First, hide the currently visible data
        mSearchResultsTextView.setVisibility(View.INVISIBLE);
        // Or error messages
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        // Show the progress bar
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public class GithubQueryTask extends AsyncTask<URL, Void, String> {

        // COMPLETED (25) Override onPreExecute and call showProgressBar to display the loading indicator
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressBar();
        }

        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String githubSearchResults = null;
            try {
                githubSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return githubSearchResults;
        }

        @Override
        protected void onPostExecute(String githubSearchResults) {
            if (githubSearchResults != null && !githubSearchResults.equals("")) {
                // COMPLETED (26) Call showJsonDataView if we have valid, non-null results
                showJsonDataView();
                mSearchResultsTextView.setText(githubSearchResults);
            } else {
                // COMPLETED (27) Call showErrorMessage if the result is null in onPostExecute
                showErrorMessage();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_search) {
            makeGithubSearchQuery();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
