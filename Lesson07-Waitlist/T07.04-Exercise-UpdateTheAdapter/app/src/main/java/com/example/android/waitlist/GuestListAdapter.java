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

    private Context mContext;
    // COMPLETED Replace the mCount with a Cursor field called mCursor
    private Cursor mCursor;

    /**
     * Constructor using the context and the db cursor
     * @param context the calling context/activity
     */
    // COMPLETED Modify the constructor to accept a cursor rather than an integer
    public GuestListAdapter(Context context, Cursor cursor) {
        this.mContext = context;
        // COMPLETED Set the local mCursor to be equal to cursor
        mCursor = cursor;
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
        // COMPLETED Move the cursor to the passed in position, return if moveToPosition returns false
        if (!mCursor.moveToPosition(position)) {
            return;
        }

        // COMPLETED Call getString on the cursor to get the guest's name
        // COMPLETED Call getInt on the cursor to get the party size
        // COMPLETED Set the holder's nameTextView text to the guest's name
        holder.nameTextView.setText(
                mCursor.getString(mCursor.getColumnIndex(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME)));
        // COMPLETED Set the holder's partySizeTextView text to the party size
        holder.partySizeTextView.setText(
                String.valueOf(mCursor.getInt(mCursor.getColumnIndex(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE))));
    }

    @Override
    public int getItemCount() {
        // COMPLETED Update the getItemCount to return the getCount of mCursor
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