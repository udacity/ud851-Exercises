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
package com.example.android.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * We couldn't come up with a good name for this class. Then, we realized
 * that this lesson is about RecyclerView.
 * <p>
 * RecyclerView... Recycling... Saving the planet? Being green? Anyone?
 * #crickets
 * <p>
 * Avoid unnecessary garbage collection by using RecyclerView and ViewHolders.
 * <p>
 * If you don't like our puns, we named this Adapter GreenAdapter because its
 * contents are green.
 */
// TODO (4) From GreenAdapter, extend RecyclerView.Adapter<NumberViewHolder>
public class GreenAdapter extends RecyclerView.Adapter<GreenAdapter.NumberViewHolder> {

    // TODO (1) Add a private int variable called mNumberItems
    private int mNumberItems;

    // TODO (2) Create a constructor for GreenAdapter that accepts an int as a parameter for numberOfItems
    // TODO (3) Store the numberOfItems parameter in mNumberItems

    public GreenAdapter(int numberOfItems) {
        this.mNumberItems = numberOfItems

    }

    // TODO (5) Override the onCreateViewHolder method
    // TODO (6) Create and return a new NumberViewHolder within this method

    // TODO (7) Override onBindViewHolder
    // TODO (8) Within onBindViewHolder, call holder.bind and pass in the position

    // TODO (9) Override getItemCount and return the number of items to display

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.number_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {

        return mNumberItems;
    }

    /**
     * Cache of the children views for a list item.
     */
    class NumberViewHolder extends RecyclerView.ViewHolder {

        // Will display the position in the list, ie 0 through getItemCount() - 1
        TextView listItemNumberView;

        /**
         * Constructor for our ViewHolder. Within this constructor, we get a reference to our
         * TextViews and set an onClickListener to listen for clicks. Those will be handled in the
         * onClick method below.
         *
         * @param itemView The View that you inflated in
         *                 {@link GreenAdapter#onCreateViewHolder(ViewGroup, int)}
         */
        public NumberViewHolder(View itemView) {
            super(itemView);

            listItemNumberView = (TextView) itemView.findViewById(R.id.tv_item_number);
        }

        /**
         * A method we wrote for convenience. This method will take an integer as input and
         * use that integer to display the appropriate text within a list item.
         *
         * @param listIndex Position of the item in the list
         */
        void bind(int listIndex) {
            listItemNumberView.setText(String.valueOf(listIndex));
        }
    }
}
