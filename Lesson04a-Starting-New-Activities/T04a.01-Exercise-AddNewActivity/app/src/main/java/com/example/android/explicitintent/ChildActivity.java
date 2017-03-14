package com.example.android.explicitintent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ChildActivity extends AppCompatActivity {

    // Do steps 6 & 7 in ChildActivity.java
    // TODO COMPLETED (6) Create a TextView field to display your message
    // TODO COMPLETED (7) Get a reference to your TextView in Java

    private TextView mTvDisplayTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);
        mTvDisplayTextView = (TextView) findViewById(R.id.tv_display);
    }
}
