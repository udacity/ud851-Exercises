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

package com.udacity.example.quizexample;

import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.udacity.example.droidtermsprovider.DroidTermsExampleContract;

/**
 * Gets the data from the ContentProvider and shows a series of flash cards.
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName() ;
    // The current state of the app
    private int mCurrentState;

    // TODO (3) Create an instance variable storing a Cursor called mData
    private Button mButton;
    private Cursor mData;
    private TextView mDefinition;
    private TextView mWord;

    // This state is when the word definition is hidden and clicking the button will therefore
    // show the definition
    private final int STATE_HIDDEN = 0;

    // This state is when the word definition is shown and clicking the button will therefore
    // advance the app to the next word
    private final int STATE_SHOWN = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the views
        mButton = (Button) findViewById(R.id.button_next);
        mDefinition = findViewById(R.id.text_view_definition);
        mWord = findViewById(R.id.text_view_word);
        // TODO (5) Create and execute your AsyncTask here
        Uri droidinfo = DroidTermsExampleContract.CONTENT_URI;
        Log.d(TAG, "onCreate: +++++++++++++" + droidinfo.toString());
       new GetDroidTerms().execute();
    }

    /**
     * This is called from the layout when the button is clicked and switches between the
     * two app states.
     * @param view The view that was clicked
     */
    public void onButtonClick(View view) {

        // Either show the definition of the current word, or if the definition is currently
        // showing, move to the next word.
        switch (mCurrentState) {
            case STATE_HIDDEN:
                showDefinition();
                break;
            case STATE_SHOWN:
                nextWord();
                break;
        }
    }

    public void nextWord() {

        // Change button text
        mButton.setText(getString(R.string.show_definition));

        mCurrentState = STATE_HIDDEN;

    }

    public void showDefinition() {

        // Change button text
        mButton.setText(getString(R.string.next_word));


        if(mData.moveToNext() == true){
            mWord.setText(mData.getString(DroidTermsExampleContract.COLUMN_INDEX_WORD));
            mDefinition.setText(mData.getString(DroidTermsExampleContract.COLUMN_INDEX_DEFINITION));
        }else{
            mData.moveToFirst();
        }

        mCurrentState = STATE_SHOWN;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mData.close();
    }

    // TODO (1) Create AsyncTask with the following generic types <Void, Void, Cursor>

    private class GetDroidTerms extends AsyncTask<Void, Void, Cursor>{

        @Override
        protected Cursor doInBackground(Void... voids) {
            Cursor cursor = getContentResolver()
                    .query(DroidTermsExampleContract.CONTENT_URI,
                            null,
                            null,
                            null,
                            null);
            return cursor;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            mData = cursor;



        }
    }
    // TODO (2) In the doInBackground method, write the code to access the DroidTermsExample
    // provider and return the Cursor object
    // TODO (4) In the onPostExecute method, store the Cursor object in mData

}
