package com.example.android.waitlist;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.android.waitlist.data.TestUtil;
import com.example.android.waitlist.data.WaitlistContract;
import com.example.android.waitlist.data.WaitlistDbHelper;


public class MainActivity extends AppCompatActivity {

    private GuestListAdapter mAdapter;
    private SQLiteDatabase mDb;

    // COMPLETED Create local EditText members for mNewGuestNameEditText and mNewPartySizeEditText
    private EditText mNewGuestNameEditText;
    private EditText mNewPartySizeEditText;

    // COMPLETED Create a constant string LOG_TAG that is equal to the class.getSimpleName()
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView waitlistRecyclerView;

        // Set local attributes to corresponding views
        waitlistRecyclerView = (RecyclerView) this.findViewById(R.id.all_guests_list_view);

        // COMPLETED Set the Edit texts to the corresponding views using findViewById
        mNewGuestNameEditText = (EditText) findViewById(R.id.person_name_edit_text);
        mNewPartySizeEditText = (EditText) findViewById(R.id.party_count_edit_text);

        // Set layout for the RecyclerView, because it's a list we are using the linear layout
        waitlistRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        // Create a DB helper (this will create the DB if run for the first time)
        WaitlistDbHelper dbHelper = new WaitlistDbHelper(this);

        // Keep a reference to the mDb until paused or killed. Get a writable database
        // because you will be adding restaurant customers
        mDb = dbHelper.getWritableDatabase();

        // COMPLETED Remove this fake data call since we will be inserting our own data now

        // Get all guest info from the database and save in a cursor
        Cursor cursor = getAllGuests();

        // Create an adapter for that cursor to display the data
        mAdapter = new GuestListAdapter(this, cursor);

        // Link the adapter to the RecyclerView
        waitlistRecyclerView.setAdapter(mAdapter);

    }

    /**
     * This method is called when user clicks on the Add to waitlist button
     *
     * @param view The calling view (button)
     */
    public void addToWaitlist(View view) {

        // COMPLETED First thing, check if any of the EditTexts are empty, return if so
        if(TextUtils.isEmpty(mNewGuestNameEditText.getText())
                || TextUtils.isEmpty(mNewPartySizeEditText.getText())) {
            return;
        }

        // COMPLETED Create an integer to store the party size and initialize to 1
        // COMPLETED Use Integer.parseInt to parse mNewPartySizeEditText.getText to an integer
        // COMPLETED Make sure you surround the Integer.parseInt with a try catch and log any exception
        Integer partySize = 1;

        if (TextUtils.isDigitsOnly(mNewPartySizeEditText.getText())) {
            partySize = Integer.parseInt(mNewPartySizeEditText.getText().toString());
        }
        else {
            Log.e(LOG_TAG, "Incorrect value of party size " + mNewPartySizeEditText.getText());
        }


        // COMPLETED call addNewGuest with the guest name and party size
        long id = addGuest(mNewGuestNameEditText.getText().toString(), partySize);

        if (id != -1) {
            Log.d(LOG_TAG, "Successfully inserted guest with ID " + id);
        }
        else {
            Log.e(LOG_TAG, "Error during insert guest with name " + mNewGuestNameEditText.getText() +
                    " and partySize " + partySize);
        }

        // COMPLETED call mAdapter.swapCursor to update the cursor by passing in getAllGuests()
        mAdapter.swapCursor(getAllGuests());

        // COMPLETED To make the UI look nice, call .getText().clear() on both EditTexts, also call clearFocus() on mNewPartySizeEditText
        mNewPartySizeEditText.getText().clear();
        mNewGuestNameEditText.getText().clear();
        mNewPartySizeEditText.clearFocus();
    }



    /**
     * Query the mDb and get all guests from the waitlist table
     *
     * @return Cursor containing the list of guests
     */
    private Cursor getAllGuests() {
        return mDb.query(
                WaitlistContract.WaitlistEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                WaitlistContract.WaitlistEntry.COLUMN_TIMESTAMP
        );
    }

    // COMPLETED Create a new addGuest method
    // COMPLETED Inside, create a ContentValues instance to pass the values onto the insert query
    // COMPLETED call put to insert the name value with the key COLUMN_GUEST_NAME
    // COMPLETED call put to insert the party size value with the key COLUMN_PARTY_SIZE
    // COMPLETED call insert to run an insert query on TABLE_NAME with the ContentValues created
    private long addGuest(String guestName, Integer partySize) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME, guestName);
        contentValues.put(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE, partySize);
        return mDb.insert(WaitlistContract.WaitlistEntry.TABLE_NAME, null, contentValues);
    }

}