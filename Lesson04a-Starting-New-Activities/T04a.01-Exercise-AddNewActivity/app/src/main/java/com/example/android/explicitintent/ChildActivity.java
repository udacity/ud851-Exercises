package com.example.android.explicitintent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ChildActivity extends AppCompatActivity {

    // COMPLETED Create a TextView field to display your message
    // COMPLETED Get a reference to your TextView in Java

    private TextView mDisplayTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);
        mDisplayTextView = (TextView) findViewById(R.id.tv_display);
    }
}
