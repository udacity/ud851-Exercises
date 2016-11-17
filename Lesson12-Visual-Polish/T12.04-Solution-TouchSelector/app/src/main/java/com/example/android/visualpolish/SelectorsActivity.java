package com.example.android.visualpolish;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


public class SelectorsActivity extends AppCompatActivity
        implements SelectorItemsAdapter.ListItemClickListener{

    private static final String TAG = SelectorsActivity.class.getSimpleName();

    // recycler view and adapter
    RecyclerView mRecyclerView;
    SelectorItemsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectors_activity);

        // Reference the recycler view with a call to findViewById
        mRecyclerView = (RecyclerView) findViewById(R.id.mainRecyclerView);

        // The linear layout manager will position list items in a vertical list
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);


        // The adapter is responsible for displaying each item in the list
        mAdapter = new SelectorItemsAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

    }

    /**
     * This is where we receive our callback from the clicklistener in the adapter
     * This callback is invoked when you click on an item in the list.
     *
     * @param clickedItemIndex Index in the list of the item that was clicked.
     */
    @Override
    public void onListItemClick(int clickedItemIndex) {

        // In here, handle what happens when an item is clicked
        // In this case, I am just logging the index of the item clicked

        Log.v(TAG, "List item clicked at index: " + clickedItemIndex);

    }

}