package com.dhruvam.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dell on 12-04-2018.
 */

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.DataHolder> {



    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_card,parent,false);
        return new DataHolder(view);
    }

    @Override
    public void onBindViewHolder(DataHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class DataHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title, subTitle, firstLine;

        public DataHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cover);
            title = itemView.findViewById(R.id.title);
            subTitle = itemView.findViewById(R.id.subTitle);
            firstLine = itemView.findViewById(R.id.firstLine);
        }
    }
}
