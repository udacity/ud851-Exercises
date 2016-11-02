package com.example.android.waitlist;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.waitlist.data.WaitlistContract;


public class GuestListAdapter extends RecyclerView.Adapter<GuestListAdapter.GuestViewHolder> {

    // COMPLETED (1) Replace the mCount with a new Cursor field called mCursor
    // Holds on to the cursor to display the waitlist
    private Cursor mCursor;
    private Context mContext;

    // COMPLETED (2) Modify the constructor to accept a cursor rather than an integer
    /**
     * Constructor using the context and the db cursor
     * @param context the calling context/activity
     * @param cursor the db cursor with waitlist data to display
     */
    public GuestListAdapter(Context context, Cursor cursor) {
        this.mContext = context;
        // COMPLETED (3) Set the local mCursor to be equal to cursor
        this.mCursor = cursor;
    }

    @Override
    public GuestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get the RecyclerView item layout
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.guest_list_item, parent, false);
        return new GuestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GuestViewHolder holder, int position) {
        // COMPLETED (5) Move the cursor to the passed in position, return if moveToPosition returns false
        // Move the mCursor to the position of the item to be displayed
        if (!mCursor.moveToPosition(position))
            return; // bail if returned null
        // COMPLETED (6) Call getString on the cursor to get the guest's name
        String name = mCursor.getString(mCursor.getColumnIndex(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME));
        // COMPLETED (7) Call getInt on the cursor to get the party size
        int partySize = mCursor.getInt(mCursor.getColumnIndex(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE));
        // COMPLETED (8) Set the holder's nameTextView text to the guest's name
        // Display the guest name
        holder.nameTextView.setText(name);
        // COMPLETED (9) Set the holder's partySizeTextView text to the party size
        // Display the party count
        holder.partySizeTextView.setText(String.valueOf(partySize));
    }

    @Override
    public int getItemCount() {
        // COMPLETED (4) Update the getItemCount to return the getCount of the cursor
        return mCursor.getCount();
    }
    
    /**
     * Inner class to hold the views needed to display a single item in the recycler-view
     */
    class GuestViewHolder extends RecyclerView.ViewHolder {

        // Will display the guest name
        TextView nameTextView;
        // Will display the party size number
        TextView partySizeTextView;

        /**
         * Constructor for our ViewHolder. Within this constructor, we get a reference to our
         * TextViews
         *
         * @param itemView The View that you inflated in
         *                 {@link GuestListAdapter#onCreateViewHolder(ViewGroup, int)}
         */
        public GuestViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.name_text_view);
            partySizeTextView = (TextView) itemView.findViewById(R.id.party_size_text_view);
        }

    }
}