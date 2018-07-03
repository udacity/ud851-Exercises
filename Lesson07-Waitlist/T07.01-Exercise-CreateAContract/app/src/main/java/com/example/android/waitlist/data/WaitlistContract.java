package com.example.android.waitlist.data;

import android.provider.BaseColumns;

public class WaitlistContract {



    // COMPLETED Create an inner class named WaitlistEntry class that implements the BaseColumns interface
    public static final class WaitListEntry implements BaseColumns {
        // COMPLETED Inside create a static final members for the table name and each of the db columns
        // TABLE_NAME -> waitlist;
        public static final String TABLE_NAME = "waitlist";
        // COLUMN_GUEST_NAME -> guestName
        public static final String COLUMN_GUEST_NAME = "guestName";
        // COLUMN_PARTY_SIZE -> partySize
        public static final String COLUMN_PART_SIZE = "partySize";
        // COLUMN_TIMESTAMP -> timestamp
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }

}
