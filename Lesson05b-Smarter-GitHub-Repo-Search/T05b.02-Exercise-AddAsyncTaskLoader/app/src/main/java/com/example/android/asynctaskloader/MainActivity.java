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
package com.example.android.asynctaskloader;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Loader;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.asynctaskloader.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;

// COMPLETED implement LoaderManager.LoaderCallbacks<String> on MainActivity
public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    /* A constant to save and restore the URL that is being displayed */
    private static final String SEARCH_QUERY_URL_EXTRA = "query";

    // COMPLETED Remove the key for storing the search results JSON
    /* A constant to save and restore the JSON that is being displayed */

    // COMPLETED Create a constant int to uniquely identify your loader. Call it GITHUB_SEARCH_LOADER
    private static final int GITHUB_SEARCH_LOADER = 999;

    private EditText mSearchBoxEditText;

    private TextView mUrlDisplayTextView;
    private TextView mSearchResultsTextView;

    private TextView mErrorMessageDisplay;

    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchBoxEditText = (EditText) findViewById(R.id.et_search_box);

        mUrlDisplayTextView = (TextView) findViewById(R.id.tv_url_display);
        mSearchResultsTextView = (TextView) findViewById(R.id.tv_github_search_results_json);

        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);

        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        if (savedInstanceState != null) {
            String queryUrl = savedInstanceState.getString(SEARCH_QUERY_URL_EXTRA);

            // COMPLETED Remove the code that retrieves the JSON

            mUrlDisplayTextView.setText(queryUrl);
            // COMPLETED Remove the code that displays the JSON
        }

        // COMPLETED Initialize the loader with GITHUB_SEARCH_LOADER as the ID, null for the bundle, and this for the context
        getLoaderManager().initLoader(GITHUB_SEARCH_LOADER, null, this);
    }

    /**
     * This method retrieves the search text from the EditText, constructs the
     * URL (using {@link NetworkUtils}) for the github repository you'd like to find, displays
     * that URL in a TextView, and finally request that an AsyncTaskLoader performs the GET request.
     */
    private void makeGithubSearchQuery() {
        String githubQuery = mSearchBoxEditText.getText().toString();

        // COMPLETED If no search was entered, indicate that there isn't anything to search for and return
        if (TextUtils.isEmpty(githubQuery)) {
            mSearchResultsTextView.setText("Please enter text to search!");
            return;
        }

        URL githubSearchUrl = NetworkUtils.buildUrl(githubQuery);
        mUrlDisplayTextView.setText(githubSearchUrl.toString());

        // COMPLETED Remove the call to execute the AsyncTask

        // COMPLETED Create a bundle called queryBundle
        // COMPLETED Use putString with SEARCH_QUERY_URL_EXTRA as the key and the String value of the URL as the value
        Bundle queryBundle = new Bundle();
        queryBundle.putString(SEARCH_QUERY_URL_EXTRA, githubSearchUrl.toString());

        // COMPLETED Call getSupportLoaderManager and store it in a LoaderManager variable
        // COMPLETED Get our Loader by calling getLoader and passing the ID we specified
        // COMPLETED If the Loader was null, initialize it. Else, restart it.
        LoaderManager loaderManager = getLoaderManager();
        Loader<Object> gitHubLoader = loaderManager.getLoader(GITHUB_SEARCH_LOADER);
        if (gitHubLoader != null) {
            loaderManager.restartLoader(GITHUB_SEARCH_LOADER, queryBundle,this);
        }
        else {
            loaderManager.initLoader(GITHUB_SEARCH_LOADER, queryBundle, this);
        }

    }

    /**
     * This method will make the View for the JSON data visible and
     * hide the error message.
     * <p>
     * Since it is okay to redundantly set the visibility of a View, we don't
     * need to check whether each view is currently visible or invisible.
     */
    private void showJsonDataView() {
        /* First, make sure the error is invisible */
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        /* Then, make sure the JSON data is visible */
        mSearchResultsTextView.setVisibility(View.VISIBLE);
    }

    /**
     * This method will make the error message visible and hide the JSON
     * View.
     * <p>
     * Since it is okay to redundantly set the visibility of a View, we don't
     * need to check whether each view is currently visible or invisible.
     */
    private void showErrorMessage() {
        /* First, hide the currently visible data */
        mSearchResultsTextView.setVisibility(View.INVISIBLE);
        /* Then, show the error */
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    @Override
    // COMPLETED Override onCreateLoader
    // COMPLETED Return a new AsyncTaskLoader<String> as an anonymous inner class with this as the constructor's parameter
    public Loader<String> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<String>(this) {

            @Override
            // COMPLETED Override onStartLoading
            protected void onStartLoading() {
                // COMPLETED If args is null, return.
                if (args == null) {
                    return;
                }
                // COMPLETED Show the loading indicator
                mLoadingIndicator.setVisibility(View.VISIBLE);
                // COMPLETED Force a load
                forceLoad();
            }

            @Override
            // COMPLETED Override loadInBackground
            public String loadInBackground() {

                // COMPLETED Get the String for our URL from the bundle passed to onCreateLoader
                String gitHubSearchUrl = args.getString(SEARCH_QUERY_URL_EXTRA);

                // COMPLETED If the URL is null or empty, return null
                if (TextUtils.isEmpty(gitHubSearchUrl)) {
                    return null;
                }

                // COMPLETED Copy the try / catch block from the AsyncTask's doInBackground method
                try {
                    URL searchUrl = new URL(gitHubSearchUrl);
                    String githubSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
                    return githubSearchResults;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }

    @Override
    // COMPLETED Override onLoadFinished
    public void onLoadFinished(Loader<String> loader, String githubSearchResults) {
        // COMPLETED Hide the loading indicator
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        // COMPLETED Use the same logic used in onPostExecute to show the data or the error message
        if (githubSearchResults != null && !githubSearchResults.equals("")) {
            showJsonDataView();
            mSearchResultsTextView.setText(githubSearchResults);
        } else {
            showErrorMessage();
        }
    }

    @Override
    // COMPLETED Override onLoaderReset as it is part of the interface we implement, but don't do anything in this method
    public void onLoaderReset(Loader<String> loader) {

    }

    // COMPLETED Delete the AsyncTask class

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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        String queryUrl = mUrlDisplayTextView.getText().toString();
        outState.putString(SEARCH_QUERY_URL_EXTRA, queryUrl);

        // COMPLETED Remove the code that persists the JSON
    }
}