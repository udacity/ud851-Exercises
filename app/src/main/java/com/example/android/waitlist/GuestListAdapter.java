package com.example.android.waitlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class GuestListAdapter extends RecyclerView.Adapter<GuestListAdapter.GuestViewHolder> {

    private Context mContext;
    // TODO (8) Add a new local variable mCount to store the count of items to be displayed in the recycler view

    /**
     * Constructor using the context and the db cursor
     *
     * @param context the calling context/activity
     */
    // TODO (9) Update the Adapter constructor to accept an integer for the count along with the context
    public GuestListAdapter(Context context) {
        this.mContext = context;
        // TODO (10) Set the local mCount to be equal to count
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

    }


    // TODO (11) Modify the getItemCount to return the mCount value rather than 0
    @Override
    public int getItemCount() {
        return 0;
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