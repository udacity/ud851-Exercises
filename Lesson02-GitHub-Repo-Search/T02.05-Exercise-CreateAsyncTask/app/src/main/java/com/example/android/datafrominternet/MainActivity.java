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

import android.os.*;
import android.support.v7.app.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import com.example.android.datafrominternet.utilities.*;
import java.io.*;
import java.net.*;

public class MainActivity extends AppCompatActivity {

    private EditText mSearchBoxEditText;

    private TextView mUrlDisplayTextView;

    private TextView mSearchResultsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchBoxEditText = (EditText) findViewById(R.id.et_search_box);

        mUrlDisplayTextView = (TextView) findViewById(R.id.tv_url_display);
        mSearchResultsTextView = (TextView) findViewById(R.id.tv_github_search_results_json);
    }

    /**
     * This method retrieves the search text from the EditText, constructs the URL
     * (using {@link NetworkUtils}) for the github repository you'd like to find, displays
     * that URL in a TextView, and finally fires off an AsyncTask to perform the GET request using
     * our (not yet created) {@link GithubQueryTask}
     */
    private void makeGithubSearchQuery() {
        String githubQuery = mSearchBoxEditText.getText().toString();
        URL githubSearchUrl = NetworkUtils.buildUrl(githubQuery);
        mUrlDisplayTextView.setText(githubSearchUrl.toString());
//        String githubSearchResults = null;
//        try {
//            githubSearchResults = NetworkUtils.getResponseFromHttpUrl(githubSearchUrl);
//            mSearchResultsTextView.setText(githubSearchResults);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        // TODO (4) Create a new GithubQueryTask and call its execute method, passing in the url to query
		new GithubQueryTask().execute(githubSearchUrl);
    }

    // TODO (1) Create a class called GithubQueryTask that extends AsyncTask<URL, Void, String>
    // TODO (2) Override the doInBackground method to perform the query. Return the results. (Hint: You've already written the code to perform the query)
    // TODO (3) Override onPostExecute to display the results in the TextView
	private class GithubQueryTask extends AsyncTask<URL, Void, String>
	{

		@Override
		protected String doInBackground(URL[] url)
		{
			// TODO: Implement this method
			if (url == null){
				return null;
			}
			try
			{
				return NetworkUtils.getResponseFromHttpUrl(url[0]);
			}
			catch (IOException e)
			{
				Log.e("", e.getMessage());
				return null;
			}
			
		}

		@Override
		protected void onPostExecute(String result)
		{
			// TODO: Implement this method
			if (result != null){
				mSearchResultsTextView.setText(result);
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
